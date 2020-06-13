package effects;
import lib.StdAudio;
public class LpFilter extends AbstractAffector{

    // settings[0] is the cutoff frequency

    public LpFilter(double hz){
        super(); // just turns it on
        this.settings = new double[]{hz};
    }

    //TODO: constrain output
    @Override
    public double[] process(double[] input){
        //System.out.println("LP processing");
        if(!this.on) return input;

        // CONSTANT VARIABLES
        double dt = 1.0/StdAudio.SAMPLE_RATE;
        //RC is ohm times farad // this is based on an electronic implementation of an HP filter
        double R = 1.0; // 1 ohm
        //this.settings[0] is the cutoff frequency
        double RC = 1.0 / (2.0 * Math.PI * this.settings[0] * R);  // RC filter
        double alpha = dt / (RC+dt); 

        double[] output = new double[input.length];
        output[0] = input[0];

        for(int i = 1; i < input.length; i++){
            output[i] = output[i-1] + (alpha * input[i] - output[i-1]); //FIXME: not sure ab this one
        }

        return output;
    }
}