package synth;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import lib.StdAudio;

import util.EqualTemperment;
import effects.*;

public class MonoSynth {

    final static int chunkSize = (int)(StdAudio.SAMPLE_RATE / 10); // ~10ms chunk length

    public static Oscillator[] Oscs;
    
    public static EffectChain chain; 

    private static LinkedBlockingQueue<double[]> playFrameBuffer; // contains info only for next however many frames
    private static class PlayerThread extends Thread{
        @Override
        public void run(){
            while(true){
                double[] frame;
                try{frame = playFrameBuffer.take();}
                catch (InterruptedException e){return;}
                StdAudio.play(frame); //chain.process(frame)); // effects chain processes in real-ish time
            }
        }
    }

    private static PlayerThread playerThread; // consumes and plays data from playFrameBuffer
    
    static {init();}

    private static void init(){
        Oscs = new Oscillator[] {new Oscillator(WaveType.TRIANGLE, 1)}; //, new Oscillator(WaveType.SIN, 0.7)};
        chain = new EffectChain();
        chain.attach(new HpFilter(20));
        chain.attach(new LpFilter(20000));

        playFrameBuffer = new LinkedBlockingQueue<double[]>();

        playerThread = new PlayerThread();
        playerThread.start();
    }
    
    //writes to playBuffer;
    public static void play(String note, double duration, double velocity){
        double hz = EqualTemperment.getHz(note);
        double[] output = new double[(int)(StdAudio.SAMPLE_RATE*duration)];
        for(Oscillator osc: Oscs){
            double[] wave = osc.gen_wave(hz, duration,velocity);
            for(int i = 0; i<output.length; i++){output[i]+=wave[i];} // combine oscillators
        }

        // processes note through effect chain
        output = chain.process(output);

        playFrameBuffer.clear(); // cancel whatever sound is playing and have the output start playing
        for(double[] frame: chunkIntoFrames(output)){
            playFrameBuffer.add(frame);
        }
    }

    // chunks data into frames to be put ont the playFrameBuffer; note: stolen from stackoverflow
    private static double[][] chunkIntoFrames(double[] soundData){
        int rest = soundData.length % chunkSize;  // if rest>0 then our last array will have less elements than the others 
        int chunks = soundData.length / chunkSize + (rest > 0 ? 1 : 0); // we may have to add an additional array for the 'rest'
        // now we know how many arrays we need and create our result array
        double[][] arrays = new double[chunks][];
        // we create our resulting arrays by copying the corresponding 
        // part from the input array. If we have a rest (rest>0), then
        // the last array will have less elements than the others. This 
        // needs to be handled separately, so we iterate 1 times less.
        for(int i = 0; i < (rest > 0 ? chunks - 1 : chunks); i++){
            // this copies 'chunk' times 'chunkSize' elements into a new array
            arrays[i] = Arrays.copyOfRange(soundData, i * chunkSize, i * chunkSize + chunkSize);
        }
        if(rest > 0){ // only when we have a rest
            // we copy the remaining elements into the last chunk
            arrays[chunks - 1] = Arrays.copyOfRange(soundData, (chunks - 1) * chunkSize, (chunks - 1) * chunkSize + rest);
        }
        return arrays; // that's it
    }
}

