package synth;
import lib.StdAudio;
public class Oscillator extends WaveGenerator{
    protected WaveType type;
    protected double level;

    public Oscillator(WaveType t, double l){
        this.type = t;
        this.level = l;
    }

    public void setLevel(double l){
        this.level = l;
    }

    public double[] gen_wave(double hz, double duration, double amplitude){
        double[] output = new double[(int) (StdAudio.SAMPLE_RATE * duration)];
        switch(this.type){
            case SIN:
                output = gen_sin(hz, duration, amplitude * this.level);
                break;

            case SQUARE:
                output = gen_square(hz, duration, amplitude * this.level);
                break;
            case SAW:
                output = gen_saw(hz, duration, amplitude * this.level);
                break;
            //TODO: implement other wave types
            // other cases unimplemented
            default:
                return output;
        }

        return output;
    }

}