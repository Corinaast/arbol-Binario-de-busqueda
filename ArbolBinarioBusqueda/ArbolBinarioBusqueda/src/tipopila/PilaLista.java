package tipopila;

public class PilaLista {
    private NodoPila cima;

    public PilaLista() {
        cima = null;
    }

    public boolean esVacia() {
        return cima == null;
    }

    // Insertar (push)
    public void insertar(Object elemento) {
        NodoPila nuevo = new NodoPila(elemento);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    // Quitar (pop)
    public Object quitar() {
        if (esVacia()) {
            return null; // O lanzar excepción si se prefiere
        }
        Object aux = cima.dato;
        cima = cima.siguiente;
        return aux;
    }
    
    // Consultar (peek)
    public Object cimaPila() {
        if (esVacia()) {
            return null;
        }
        return cima.dato;
    }
}