package effects;
import lib.StdAudio;

public class HpFilter extends AbstractAffector{

    // settings[0] is the cutoff frequency

    public HpFilter(double hz){
        super(); // just turns it on
        this.settings = new double[]{hz};
    }

    public void setCutoff(double hz){
        this.settings[0] = hz;
    }
    
    //FIXME: very weak
    @Override
    public double[] process(double[] input){
        //System.out.println("HP processing");
        if(!this.on) return input;

        // CONSTANT VARIABLES
        double dt = 1.0 / StdAudio.SAMPLE_RATE;
        //RC is ohm times farad // this is based on an electronic implementation of an HP filter
        double R = 1.0; // 1 ohm
        //this.settings[0] is the cutoff frequency
        double RC = 1.0 /(2.0 * Math.PI * this.settings[0] * R);  // RC filter
        double alpha = RC / (RC+dt); 

        double[] output = new double[input.length];
        output[0] = input[0];

        for(int i = 1; i < input.length; i++){
            output[i] = constrain(alpha * (output[i-1] + input[i] - input[i-1]));
        }

        return output;
    }
}