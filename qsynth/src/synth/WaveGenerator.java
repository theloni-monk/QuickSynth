package synth;
import java.lang.Math;
import javax.sound.sampled.Clip;
import lib.StdAudio;

public class WaveGenerator {

    public WaveGenerator(){
    }

    //WRITEME: wave sythesis
    public void gen_wavelet(Clip sample, double hz, double duration, double amplitude) {
    }

    public double[] gen_square(double hz, double duration, double amplitude){
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        int counter = 0;
        int flip = 0;
        for (int i = 0; i <= n; i++){
            if ((counter * hz / StdAudio.SAMPLE_RATE) > 1){
                flip = (flip + 1) % 2;
                counter = 0;
            }
            else {counter++;}
            a[i] = amplitude * (int) flip;
        }
        return a;
    }

    public double[] gen_sin(double hz, double duration, double amplitude){
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++)
            a[i] = amplitude * Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        return a;
    }

    public double[] gen_saw(double hz, double duration, double amplitude) {
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        int counter = 0;
        for (int i = 0; i <= n; i++){
            if ((counter * hz / StdAudio.SAMPLE_RATE) > 1){
                counter = 0;
            }
            counter++;
            a[i] = amplitude * (counter * hz / StdAudio.SAMPLE_RATE);
        }
        return a;
    }

    public double[] gen_triangle(double hz, double duration, double amplitude){
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        int counter = 0;
        boolean flip = false;
        for (int i = 0; i <= n; i++){
            if ((counter * hz / StdAudio.SAMPLE_RATE) > 1){
                counter = 0;
                flip = !flip;
            }
            counter++;
            // flip determines up slope or down slope
            double scalar = flip ? (counter * hz / StdAudio.SAMPLE_RATE) : 1 - (counter * hz / StdAudio.SAMPLE_RATE);
            a[i] = amplitude * scalar;
        }
        return a;
    }

    //WRITEME: saw and triangle waves, and pwm 
    
}