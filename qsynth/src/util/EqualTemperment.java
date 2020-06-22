package util;
import java.util.Map;
import java.util.HashMap;
public class EqualTemperment {
    public static enum Note {
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

    public static Map<String, Note> Map;
    
    final static double baseFrequency = 110; // HARDCODED FOR NOW A1

    static{makeMap();}
    private static void makeMap(){
        Map = new HashMap<String, Note>();
        Map.put("Af", Note.Af);
        Map.put("A", Note.A);
        Map.put("As", Note.As);
        Map.put("Bf", Note.Bf);
        Map.put("B", Note.B);
        Map.put("C", Note.C);
        Map.put("Cs", Note.Cs);
        Map.put("Df", Note.Df);
        Map.put("D", Note.D);
        Map.put("Ds", Note.Ds);
        Map.put("Ef", Note.Ef);
        Map.put("E", Note.E);
        Map.put("F", Note.F);
        Map.put("Fs", Note.Fs);
        Map.put("Gf", Note.Gf);
        Map.put("G", Note.G);
        Map.put("Gs", Note.Gs);
    }

    // returns hz value for note formated letter than octave i.e. F4 or Gs6; octave must be strictly between 0 and 10 (excluding both) 
    public static double getHz(String noteName){
        String name = noteName.toUpperCase();
        int octave = Integer.parseInt(name.substring(name.length() - 1));
        String note_string = name.substring(0, name.length() - 1);
        Note note = Map.get(note_string);
        
        return getHz(note, octave);
    } 

    public static double getHz(Note note, int octave){
        return (baseFrequency * octave) * note.getValue();
    }
}