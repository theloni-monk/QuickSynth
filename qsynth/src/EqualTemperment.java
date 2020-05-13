
public class EqualTemperment {
    public enum Note {
        Af(0.943877069),
        A(1),
        As(1.05946),
        Bf(1.05946),
        B(1.1224554916000002),
        C(1.1891966951305362),
        Cs(1.259906330622998),
        Df(1.259906330622998),
        D(1.3348203610418417),
        Ds(1.4141887797093895),
        Ef(1.4141887797093895),
        E(1.49827644455091),
        F(1.587363961943907),
        Fs(1.6817486231210919),
        Gf(1.6817486231210919),
        G(1.7817453962518721),
        Gs(1.8876879775130087);

        private final double value;
        private Note(double value) {
            this.value = value;
        }
        public double getValue() {
                return value;
        }
    }
    

    final static double baseFrequency = 110; // HARDCODED FOR NOW


    // returns hz value for note formated letter than octave i.e. F4
    // WRITME
    public static double getHz(String noteName){
        return 0.0;
    } 

    public static double getHz(Note note, int octave){
        return (baseFrequency * octave) * note.getValue();
    }
}