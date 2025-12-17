package ArbolBinario;

public class ArbolBinario {
    protected Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }
    
    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }

    public Nodo raizArbol() {
        return raiz;
    }

    public boolean esVacio() {
        return raiz == null;
    }
    
    public static Nodo nuevoArbol(Nodo ramaIzdo, Object dato, Nodo ramaDcho) {
        return new Nodo(ramaIzdo, dato, ramaDcho);
    }
    
    // ===================================
    //        MÉTODOS DE RECORRIDO (String para GUI)
    // ===================================
    
    public String preorden() {
        StringBuilder sb = new StringBuilder();
        preorden(raiz, sb);
        return sb.toString().trim();
    }
    private void preorden(Nodo r, StringBuilder sb) {
        if (r != null) {
            sb.append(r.valorNodo()).append(" ");
            preorden(r.subarbolIzdo(), sb);
            preorden(r.subarbolDcho(), sb);
        }
    }
    
    public String inorden() {
        StringBuilder sb = new StringBuilder();
        inorden(raiz, sb);
        return sb.toString().trim();
    }
    private void inorden(Nodo r, StringBuilder sb) {
        if (r != null) {
            inorden(r.subarbolIzdo(), sb);
            sb.append(r.valorNodo()).append(" ");
            inorden(r.subarbolDcho(), sb);
        }
    }
    
    public String postorden() {
        StringBuilder sb = new StringBuilder();
        postorden(raiz, sb);
        return sb.toString().trim();
    }
    private void postorden(Nodo r, StringBuilder sb) {
        if (r != null) {
            postorden(r.subarbolIzdo(), sb);
            postorden(r.subarbolDcho(), sb);
            sb.append(r.valorNodo()).append(" ");
        }
    }
    
    // ===================================
    //        MÉTODOS DE ESTRUCTURA Y CONTEO
    // ===================================
    
    public int numeroNodos() {
        return numNodos(raiz);
    }
    private int numNodos(Nodo r) {
        if (r == null) {
            return 0;
        } else {
            return 1 + numNodos(r.subarbolIzdo()) + numNodos(r.subarbolDcho());
        }
    }
    
    public int alturaArbol() {
        return calcularAltura(raiz);
    }
    private int calcularAltura(Nodo r) {
        if (r == null) {
            return 0;
        } else {
            int alturaIzquierda = calcularAltura(r.subarbolIzdo());
            int alturaDerecha = calcularAltura(r.subarbolDcho());
            return 1 + Math.max(alturaIzquierda, alturaDerecha);
        }
    }
    
    public int contarHojas() {
        return contarHojas(raiz);
    }
    private int contarHojas(Nodo r) {
        if (r == null) {
            return 0;
        }
        if (r.subarbolIzdo() == null && r.subarbolDcho() == null) {
            return 1;
        } else {
            return contarHojas(r.subarbolIzdo()) + contarHojas(r.subarbolDcho());
        }
    }
    
    // ===================================
    //        MÉTODOS DE VISUALIZACIÓN
    // ===================================
    
    // Muestra el árbol de forma "horizontal" (útil para ABB)
    public String mostrarArbol() {
        StringBuilder sb = new StringBuilder();
        mostrarArbol(raiz, 0, sb);
        return sb.toString();
    }

    protected void mostrarArbol(Nodo nodo, int nivel, StringBuilder sb) {
        if (nodo != null) {
            mostrarArbol(nodo.subarbolDcho(), nivel + 1, sb);
            
            for (int i = 0; i < nivel; i++) {
                sb.append("    ");
            }
            sb.append(nodo.valorNodo()).append("\n");
            
            mostrarArbol(nodo.subarbolIzdo(), nivel + 1, sb);
        }
    }

    // Muestra el árbol con líneas (estructura vertical/jerárquica)
    public String mostrarArbolConLineas() {
        StringBuilder sb = new StringBuilder();
        mostrarArbolConLineas(raiz, "", true, sb);
        return sb.toString();
    }
    
    private void mostrarArbolConLineas(Nodo r, String prefijo, boolean esIzquierdo, StringBuilder sb) {
        if (r != null) {
            // Se invierte para que la raíz izquierda se muestre abajo
            mostrarArbolConLineas(r.subarbolDcho(), prefijo + (esIzquierdo ? "│   " : "    "), false, sb);

            sb.append(prefijo).append(esIzquierdo ? "└── " : "├── ").append(r.valorNodo()).append("\n");
            
            mostrarArbolConLineas(r.subarbolIzdo(), prefijo + (esIzquierdo ? "    " : "│   "), true, sb);
        }
    }
}