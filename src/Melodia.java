import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.fasterxml.jackson.core.type.TypeReference;
import entidades.NotaMusical;

public class Melodia {
    private Nodo cabeza;

    public Melodia() {
        cabeza = null;
    }

    public void agregar(Nodo n) {
        if (n != null) {
            if (cabeza == null) {
                cabeza = n;
            } else {
                Nodo apuntador = cabeza;
                while (apuntador.siguiente != null) {
                    apuntador = apuntador.siguiente;
                }
                apuntador.siguiente = n;
            }
            n.siguiente = null;
        }
    }

    public void eliminar(Nodo n) {
        if (n != null && cabeza != null) {
            boolean encontrado = false;
            Nodo apuntador = cabeza;
            Nodo anterior = null;
            while (apuntador != null && !encontrado) {
                if (apuntador == n) {
                    encontrado = true;
                } else {
                    anterior = apuntador;
                    apuntador = apuntador.siguiente;
                }
            }
            if (encontrado) {
                if (anterior == null) {
                    cabeza = apuntador.siguiente;
                } else {
                    anterior.siguiente = apuntador.siguiente;
                }
            }
        }
    }

    public int obtenerLongitud() {
        int totalNodos = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            totalNodos++;
            apuntador = apuntador.siguiente;
        }
        return totalNodos;
    }

    public Nodo obtenerNodo(int posicion) {
        int p = 0;
        Nodo apuntador = cabeza;
        while (apuntador != null && p != posicion) {
            apuntador = apuntador.siguiente;
            p++;
        }
        return (apuntador != null && p == posicion) ? apuntador : null;
    }

    public void guardarJSON(String nombreArchivo) {
        List<NotaMusical> notas = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            notas.add(actual.getNotaMusical()); 
            actual = actual.siguiente;
        }
        Archivo.guardarJson(nombreArchivo, notas);
    }
    
    public void desdeJSON(String nombreArchivo) {
        List<NotaMusical> notas = Archivo.leerJson(nombreArchivo, new TypeReference<List<NotaMusical>>() {});
        cabeza = null;
        if (notas != null) {
            for (NotaMusical nota : notas) {
                agregar(new Nodo(nota));
            }
        }
    }

    public static String[] encabezados = new String[] { "Nota", "Figura", "Octava" };

    public void mostrar(JTable tbl) {
        DefaultTableModel dtm = new DefaultTableModel(encabezados, 0);
        Nodo actual = cabeza;
        while (actual != null) {
            NotaMusical nota = actual.getNotaMusical();
            dtm.addRow(new Object[]{
                nota.getNota().toString(),
                nota.getFigura().toString(),
                nota.getOctava()
            });
            actual = actual.siguiente;
        }
        tbl.setModel(dtm);
    }

    public void reproducirMelodia() {
        ReproductorAudioMIDI.reproducirMelodia(this);
    }
    
    // Añade este método para acceder a la cabeza de la melodía
    public Nodo getCabeza() {
        return cabeza;
    }
}