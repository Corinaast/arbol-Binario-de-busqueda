package ArbolBinario.Ordenado;

import ArbolBinario.ArbolBinario;
import ArbolBinario.Nodo;

public class ArbolBinarioBusqueda extends ArbolBinario {

    public ArbolBinarioBusqueda() {
        super(); // Llama al constructor de ArbolBinario
    }

    // ===================================
    //        INSERCIÓN
    // ===================================
    public void insertar(Object valor) {
        if (!(valor instanceof Comparador)) {
            // Manejo de error si el objeto no se puede comparar
            throw new IllegalArgumentException("El objeto debe implementar la interfaz Comparador");
        }
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo raizSub, Object valor) {
        if (raizSub == null) {
            return new Nodo(valor);
        }

        Comparador compValor = (Comparador) valor;
        Comparador compNodo = (Comparador) raizSub.valorNodo();

        if (compValor.menorQue(compNodo)) {
            raizSub.ramaIzdo(insertarRec(raizSub.subarbolIzdo(), valor));
        } else if (compValor.mayorQue(compNodo)) {
            raizSub.ramaDcho(insertarRec(raizSub.subarbolDcho(), valor));
        }
        // Si son iguales (igualQue), no hace nada (no inserta duplicados)
        return raizSub;
    }

    // ===================================
    //        BÚSQUEDA
    // ===================================
    public Nodo buscarRecursivo(Object buscado) {
        Comparador dato = (Comparador) buscado;
        if (raiz == null)
            return null;
        else 
            return localizar(raizArbol(), dato);
    }
    
    protected Nodo localizar(Nodo raizSub, Comparador buscado) {
        if (raizSub == null)
            return null;
        else if (buscado.igualQue(raizSub.valorNodo()))
            return raizSub;
        
        else if (buscado.menorQue(raizSub.valorNodo()))
            return localizar(raizSub.subarbolIzdo(), buscado);
        else
            return localizar(raizSub.subarbolDcho(), buscado);
    }

    // ... (El método buscarIterativo es correcto y se mantiene aquí) ...

    // ===================================
    //        ELIMINACIÓN
    // ===================================
    public void eliminar(Object valor) throws Exception {
        Comparador dato = (Comparador) valor;
        raiz = eliminar(raiz, dato);
    }

    protected Nodo eliminar(Nodo raizSub, Comparador dato) throws Exception {
        if (raizSub == null)
            throw new Exception("No encontrado el nodo con la clave");
        
        if (dato.menorQue(raizSub.valorNodo())) {
            raizSub.ramaIzdo(eliminar(raizSub.subarbolIzdo(), dato));
        } else if (dato.mayorQue(raizSub.valorNodo())) {
            raizSub.ramaDcho(eliminar(raizSub.subarbolDcho(), dato));
        } else { // Nodo encontrado
            Nodo q = raizSub; 
            
            if (q.subarbolIzdo() == null)
                raizSub = q.subarbolDcho(); // Caso 1: Sin hijo izquierdo o es una hoja
            else if (q.subarbolDcho() == null)
                raizSub = q.subarbolIzdo(); // Caso 2: Sin hijo derecho
            else { 
                // Caso 3: Dos hijos (reemplazar por el menor del subárbol derecho)
                Nodo reemplazo = reemplazar(q);
                q = null; 
                return reemplazo; 
            }
            q = null;
        }
        return raizSub;
    }

    // Método de utilidad para el reemplazo en la eliminación
    private Nodo reemplazar(Nodo act) {
        Nodo a = act.subarbolDcho();
        Nodo p = act; // Padre del reemplazo

        while (a.subarbolIzdo() != null) {
            p = a;
            a = a.subarbolIzdo();
        }

        act.nuevoValor(a.valorNodo()); // Copiar valor del reemplazo al nodo actual

        if (p == act) { // El reemplazo es el hijo derecho directo
            p.ramaDcho(a.subarbolDcho());
        } else { // El reemplazo es un descendiente izquierdo
            p.ramaIzdo(a.subarbolDcho());
        }
        return act; // Devuelve el nodo actual con el nuevo valor
    }


    // ===================================
    //        MÍNIMO Y MÁXIMO
    // ===================================
    public Object encontrarMinimo() {
        if (raiz == null) return null;
        Nodo actual = raiz;
        while (actual.subarbolIzdo() != null) {
            actual = actual.subarbolIzdo();
        }
        return actual.valorNodo();
    }
    
    public Object encontrarMaximo() {
        if (raiz == null) return null;
        Nodo actual = raiz;
        while (actual.subarbolDcho() != null) {
            actual = actual.subarbolDcho();
        }
        return actual.valorNodo();
    }
}