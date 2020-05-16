package effects;

//TODO: write some useful effects

public abstract class AbstractAffector {
    protected boolean on;

    protected double[] settings;

    public void toggle(){
        this.on = !this.on;
    }

    // overrided by child classes
    public double[] process(double[] input){
        return input;
    };
}