package test;
import lib.StdAudio;
import synth.WaveGenerator;
import util.EqualTemperment;
class WaveGeneratorT{
    //public void synth_sin(){EqualTemperment.Note.C, 4, 1.0, 1.0);}
    public static void synth_sin(WaveGenerator synth, EqualTemperment.Note note, int octave, double duration, double velocity){
        StdAudio.play(synth.gen_sin(EqualTemperment.getHz(note, octave),duration,velocity));
    }
    
    public static void synth_square(WaveGenerator synth, EqualTemperment.Note note, int octave, double duration, double velocity){
        StdAudio.play(synth.gen_square(EqualTemperment.getHz(note, octave),duration,velocity));
    }
 

    public static void main(String[] args) throws Exception {
        // to init static audio class
        WaveGenerator TestSynth = new WaveGenerator();
        synth_sin(TestSynth,EqualTemperment.Note.C, 4, 1.0, 1.0);
        synth_square(TestSynth, EqualTemperment.Note.G, 4, 1.0, 1.0);
    }
}