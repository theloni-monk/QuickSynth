package test;
import util.EqualTemperment;
import util.EqualTemperment.Note;
import synth.MonoSynth;

class MonoSynthT {
    public static void main(String[] args) throws Exception{
        MonoSynth synth = new MonoSynth();
        synth.play(Note.C, 4, 5, 0.8);
        Thread.sleep(1000);
        synth.play(Note.G, 4, 10, 1);
    }
    
}