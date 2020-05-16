package synth;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import lib.StdAudio;
import util.EqualTemperment;

public class MonoSynth {

    final int chunkSize = (int)(StdAudio.SAMPLE_RATE / 100); // ~10ms chunk length

    LinkedBlockingQueue<double[]> playFrameBuffer; // contains info only for next however many frames
    class PlayerThread extends Thread{
        @Override
        public void run(){
            while(true){
                try{StdAudio.play(playFrameBuffer.take());}
                catch (InterruptedException e){
                    return;
                }
            }
        }
    }
    private PlayerThread playerThread; // consumes and plays data from playFrameBuffer

    public Oscillator[] Oscs;
    
    public EffectChain chain; 

    public MonoSynth(){
        this.Oscs = new Oscillator[] {new Oscillator(WaveType.SAW, 1)}; //, new Oscillator(WaveType.SIN, 0.7)};
        this.chain = new EffectChain();

        this.playFrameBuffer = new LinkedBlockingQueue<double[]>();

        this.playerThread = new PlayerThread();
        this.playerThread.start();
    }
    
    //writes to playBuffer;
    public void play(EqualTemperment.Note note, int octave, double duration, double velocity){
        double[] output = new double[(int)(StdAudio.SAMPLE_RATE*duration)];
        for(Oscillator osc: this.Oscs){
            double[] wave = osc.gen_wave(EqualTemperment.getHz(note, octave),duration,velocity);
            for(int i = 0; i<output.length; i++){output[i]+=wave[i];} // combine oscillators
        }
        output = chain.process(output);

        playFrameBuffer.clear(); // cancel whatever sound is playing and have the output start playing
        for(double[] frame: chunkIntoFrames(output)){
            playFrameBuffer.add(frame);
        }
    }

    // chunks data into frames to be put ont the playFrameBuffer; note: stolen from stackoverflow
    public double[][] chunkIntoFrames(double[] soundData){
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

