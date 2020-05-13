import lib.StdAudio;
public class App {
    public static void main(String[] args) throws Exception {
        StdAudio master; // to init static audio class
        Synth TestSynth = new Synth();
        TestSynth.synth_square(EqualTemperment.Note.C, 4, 5.0, 1.0);
        TestSynth.synth_square(EqualTemperment.Note.G, 4, 5.0, 1.0);
    }
}