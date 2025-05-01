import entidades.NotaMusical;

public class Nodo {
    public NotaMusical nota; 
    public Nodo siguiente;   

    public Nodo(NotaMusical nota) {
        this.nota = nota;
        this.siguiente = null;
    }

    public NotaMusical getNotaMusical() {
        return nota;
    }
}