package effects;
import java.util.ArrayList;

public abstract class AbstractAffector {
    protected boolean on;

    protected ArrayList<Double> settings;

    protected AbstractAffector nextEffect;

    public void connect(AbstractAffector nextEffect){
        this.nextEffect = nextEffect;
    }

    public void toggle(){
        this.on = !this.on;
    }

    public abstract double[] process(double[] input);
}