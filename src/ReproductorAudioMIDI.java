import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import entidades.Figura;
import entidades.NotaMusical;

public class ReproductorAudioMIDI {
    private static final int VELOCIDAD = 100; 
    private static int TEMPO = 160; 

    public static void reproducirMelodia(Melodia melodia) {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel canal = synth.getChannels()[0]; 

            Nodo actual = melodia.getCabeza();
            while (actual != null) {
                reproducirNota(actual.getNotaMusical(), canal);
                actual = actual.siguiente;
            }

            synth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reproducirNota(NotaMusical notaMusical, MidiChannel canal) {
        try {
            int notaMidi = notaMusical.getNota().ordinal() + 60 + (notaMusical.getOctava() - 4) * 12;
            int duracion = calcularDuracion(notaMusical.getFigura());

            canal.noteOn(notaMidi, VELOCIDAD);
            Thread.sleep((int)(duracion * 0.9));
            canal.noteOff(notaMidi);
            Thread.sleep((int)(duracion * 0.1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int calcularDuracion(Figura figura) {
        int negra = 60000 / TEMPO;
        switch(figura) {
            case REDONDA:    return negra * 4;
            case BLANCA:     return negra * 2;
            case NEGRA:      return negra;
            case CORCHEA:    return negra / 2;
            case SEMICORCHEA: return negra / 4;
            case FUSA:       return negra / 8;
            case SEMIFUSA:   return negra / 16;
            default:         return negra;
        }
    }

    // Nuevo método para cambiar el tempo
    public static void setTempo(int nuevoTempo) {
        if (nuevoTempo > 0) {
            TEMPO = nuevoTempo;
        }
    }

    // Nuevo método para obtener el tempo actual
    public static int getTempo() {
        return TEMPO;
    }
}