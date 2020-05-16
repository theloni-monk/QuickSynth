package synth;
import java.util.ArrayList;
import effects.AbstractAffector;
public class EffectChain {
    protected ArrayList<AbstractAffector> effects;

    public EffectChain(){
        this.effects = new ArrayList<AbstractAffector>();
    }

    public double[] process(double[] input){
        double[] output = input; //needs deep copy? // it might make more sense to have it be longer than input for things like reverb. hmmm...
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