package ArbolBinario;

public class RecorridosArbol {

    // 1. RECORRIDO INORDEN (Imprime directamente)
    public static void inOrden(Nodo r) {
        if (r != null) {
            inOrden(r.subarbolIzdo());
            System.out.print(r.valorNodo() + " ");
            inOrden(r.subarbolDcho());
        }
    }

    // 2. RECORRIDO PREORDEN (Imprime directamente)
    public static void preOrden(Nodo r) {
        if (r != null) {
            System.out.print(r.valorNodo() + " ");
            preOrden(r.subarbolIzdo());
            preOrden(r.subarbolDcho());
        }
    }

    // 3. RECORRIDO POSTORDEN (Imprime directamente)
    public static void postOrden(Nodo r) {
        if (r != null) {
            postOrden(r.subarbolIzdo());
            postOrden(r.subarbolDcho());
            System.out.print(r.valorNodo() + " ");
        }
    }

    // ==================================
    //        VISUALIZACIÓN HORIZONTAL
    // ==================================
    public static void mostrarHorizontal(Nodo raiz, int nivel) {
        if (raiz == null) {
            return;
        }
        
        // El subárbol DERECHO se muestra primero para que quede "arriba"
        mostrarHorizontal(raiz.subarbolDcho(), nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("   "); // Espacios para indentación
        }
        System.out.println(raiz.valorNodo());

        mostrarHorizontal(raiz.subarbolIzdo(), nivel + 1);
    }
    
    // ==================================
    //        VISUALIZACIÓN VERTICAL
    // ==================================
    public static void mostrarVertical(Nodo raiz) {
        System.out.println("\n--- Estructura Vertical (Jerarquía) ---");
        imprimirVertical(raiz, 0);
    }

    private static void imprimirVertical(Nodo raiz, int nivel) {
        if (raiz == null) {
            return;
        }

        // 1. Imprimir la raíz (con indentación para mostrar el nivel)
        for (int i = 0; i < nivel; i++) {
            System.out.print("|  ");
        }
        System.out.println("|- " + raiz.valorNodo());

        // 2. Recorrer subárboles
        imprimirVertical(raiz.subarbolIzdo(), nivel + 1);
        imprimirVertical(raiz.subarbolDcho(), nivel + 1);
    }
}