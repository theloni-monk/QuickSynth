import java.lang.Math;
import javax.sound.sampled.Clip;
import lib.StdAudio;

public class Synth {

    //WRITME: wave sythesis
    public void synth_sample(Clip sample) {
    }

    private double[] gen_square(double hz, double duration, double amplitude){
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        int counter = 0;
        int flip = 0;
        for (int i = 0; i <= n; i++){
            if ((counter * hz / StdAudio.SAMPLE_RATE) > 1){
                flip = (flip + 1) % 2;
                counter = 0;
            }
            counter++;
            a[i] = amplitude * (int) flip;
        }
        return a;
    }

    public void synth_square(EqualTemperment.Note note, int octave, double duration, double velocity){
       StdAudio.play(gen_square(EqualTemperment.getHz(note, octave),duration,velocity));
    }

    private double[] gen_sin(double hz, double duration, double amplitude){
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++)
            a[i] = amplitude * Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        return a;
    }

    public void synth_sin(EqualTemperment.Note note, int octave, double duration, double velocity){
        StdAudio.play(gen_sin(EqualTemperment.getHz(note, octave),duration,velocity));
    }
}