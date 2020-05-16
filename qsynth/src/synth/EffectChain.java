package synth;
import java.util.ArrayList;
import effects.AbstractAffector;
public class EffectChain {
    protected ArrayList<AbstractAffector> effects;

    public EffectChain(){

    }

    public double[] process(double[] input){
        double[] output = new double[input.length]; // it might make more sense to have it be longer than input for things like reverb. hmmm...
        for(AbstractAffector effect: this.effects){
            output = effect.process(output);
        }
        return output;
    }

    public void attach(AbstractAffector effect){
        effects.add(effect);
    }

    public void remove(int index){
        effects.remove(index);
    }
}