package effects;

public abstract class AbstractAffector {
    protected boolean on;

    protected double[] settings;

    public AbstractAffector(){
        this.on = true;
    }

    public void toggle(){
        this.on = !this.on;
    }
    
    public double constrain(double d){
        return Math.min(Math.max(d,-1), 1);
    }

    // overrided by child classes
    public double[] process(double[] input){
        return input;
    };
}