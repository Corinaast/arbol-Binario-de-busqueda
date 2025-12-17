package controlador;

import ArbolBinario.ArbolBinario;
import vista.VistaArbol;
import ArbolBinario.ArbolExpresion;
import ArbolBinario.Ordenado.ArbolBinarioBusqueda;  
import ArbolBinario.Ordenado.Estudiante;
import ArbolBinario.Nodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ControladorArbol implements ActionListener {
    private VistaArbol vista;
    private ArbolBinario arbolGeneral;
    private ArbolExpresion arbolExpresion;
    private ArbolBinarioBusqueda arbolBusqueda;  
    
    public ControladorArbol(VistaArbol vista) {
        this.vista = vista;
        this.arbolGeneral = null;
        this.arbolExpresion = null;
        // Inicializar el ABB para empezar a insertar estudiantes
        this.arbolBusqueda = new ArbolBinarioBusqueda(); 
        
        configurarListeners();
        vista.limpiarResultado(); // Limpiar el área al inicio
        vista.actualizarResultado("Bienvenido al Simulador de Árboles Binarios. Seleccione una operación.\n");
    }
    
    private void configurarListeners() {
        // Árbol General
        vista.getBtnCrearJoyanes().addActionListener(this);
        vista.getBtnMostrarRecorridos().addActionListener(this);
        vista.getBtnMostrarArbol().addActionListener(this);
        
        // Árbol de Expresión (Actualizado con botones de VistaArbol.java)
        vista.getBtnCrearExpresion().addActionListener(this);
        vista.getBtnEvaluarExpresion().addActionListener(this);
        vista.getBtnMostrarInfija().addActionListener(this);
        vista.getBtnMostrarArbolExpresion().addActionListener(this);
        
        // Árbol Binario de Búsqueda (Actualizado con botones de VistaArbol.java)
        vista.getBtnInsertarEstudiante().addActionListener(this);
        vista.getBtnBuscarEstudiante().addActionListener(this);
        vista.getBtnEliminarEstudiante().addActionListener(this);
        vista.getBtnMostrarInorden().addActionListener(this);
        vista.getBtnMinimoMaximo().addActionListener(this);
        vista.getBtnMostrarABB().addActionListener(this); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        vista.limpiarResultado();

        // Lógica Árbol General
        if (source == vista.getBtnCrearJoyanes()) {
            crearArbolJoyanes();
        } else if (source == vista.getBtnMostrarRecorridos()) {
            mostrarRecorridos();
        } else if (source == vista.getBtnMostrarArbol()) {
            mostrarArbolGeneral();
        
        // Lógica Árbol Expresión
        } else if (source == vista.getBtnCrearExpresion()) {
            crearArbolExpresion();
        } else if (source == vista.getBtnEvaluarExpresion()) {
            evaluarArbolExpresion();
        } else if (source == vista.getBtnMostrarInfija()) {
            mostrarInfija();
        } else if (source == vista.getBtnMostrarArbolExpresion()) {
            mostrarArbolExpresion();
            
        // Lógica ABB
        } else if (source == vista.getBtnInsertarEstudiante()) {
            insertarEstudiante();
        } else if (source == vista.getBtnBuscarEstudiante()) {
            buscarEstudiante();
        } else if (source == vista.getBtnEliminarEstudiante()) {
            eliminarEstudiante();
        } else if (source == vista.getBtnMostrarInorden()) {
            mostrarInordenABB();
        } else if (source == vista.getBtnMinimoMaximo()) {
            mostrarMinimoMaximo();
        } else if (source == vista.getBtnMostrarABB()) {
            mostrarEstructuraABB();
        }
    }

    // ===================================
    //        LÓGICA ÁRBOL GENERAL
    // ===================================
    private void crearArbolJoyanes() {
       
        Nodo g = new Nodo('G');
        Nodo e = new Nodo('E');
        Nodo h = new Nodo('H');
        Nodo b = new Nodo(e, 'B', g);
        Nodo f = new Nodo(null, 'F', h);
        Nodo c = new Nodo(b, 'C', f);
        Nodo d = new Nodo('D');
        Nodo a = new Nodo(c, 'A', d);
        
        arbolGeneral = new ArbolBinario(a);
        
        vista.actualizarResultado(" Árbol Binario General de Joyanes creado.\n");
        mostrarEstructura(arbolGeneral, "ESTRUCTURA ÁRBOL GENERAL (Vertical)");
    }
    
    private void mostrarRecorridos() {
        if (arbolGeneral == null) {
            vista.actualizarResultado(" Primero debe crear el Árbol Binario General.\n");
            return;
        }
        vista.actualizarResultado("=== RECORRIDOS ÁRBOL GENERAL ===\n");
        vista.actualizarResultado("Preorden:  " + arbolGeneral.preorden() + "\n"); //
        vista.actualizarResultado("Inorden:   " + arbolGeneral.inorden() + "\n"); //
        vista.actualizarResultado("Postorden: " + arbolGeneral.postorden() + "\n"); //
    }
    
    private void mostrarArbolGeneral() {
        mostrarEstructura(arbolGeneral, "ESTRUCTURA ÁRBOL GENERAL (Vertical)");
    }
    
    // ===================================
    //        LÓGICA ÁRBOL EXPRESIÓN
    // ===================================
    private void crearArbolExpresion() {
        String expresionPostfija = vista.getExpresionPostfija();
        
        if (expresionPostfija.isEmpty()) {
            vista.actualizarResultado(" Ingrese una expresión postfija válida (ej: 4 5 + 3 /).\n");
            arbolExpresion = null;
            return;
        }
        
        try {
            arbolExpresion = new ArbolExpresion();
            arbolExpresion.construirDesdePostfija(expresionPostfija); //
            vista.actualizarResultado("✅ Árbol de Expresión Binario creado con éxito a partir de: " + expresionPostfija + "\n");
            mostrarArbolExpresion();
        } catch (IllegalArgumentException e) {
            vista.actualizarResultado(" Error: Expresión postfija inválida. " + e.getMessage() + "\n");
            arbolExpresion = null;
        } catch (Exception e) {
            vista.actualizarResultado(" Error inesperado al crear el árbol: " + e.getMessage() + "\n");
            arbolExpresion = null;
        }
    }
    
    private void evaluarArbolExpresion() {
        if (arbolExpresion == null || arbolExpresion.esVacio()) {
            vista.actualizarResultado(" Primero debe crear el Árbol de Expresión.\n");
            return;
        }
        
        try {
            double resultado = arbolExpresion.evaluar(); //
            vista.actualizarResultado(" Resultado de la evaluación: " + resultado + "\n");
        } catch (ArithmeticException e) {
            vista.actualizarResultado(" Error de Aritmética: " + e.getMessage() + "\n");
        } catch (IllegalArgumentException e) {
             vista.actualizarResultado(" Error: " + e.getMessage() + "\n");
        }
    }
    
    private void mostrarInfija() {
        if (arbolExpresion == null || arbolExpresion.esVacio()) {
            vista.actualizarResultado(" Primero debe crear el Árbol de Expresión.\n");
            return;
        }
        vista.actualizarResultado("=== EXPRESIÓN INFIJA (Con paréntesis para precedencia) ===\n");
        // Llama al método que debe ser añadido a ArbolExpresion (ver sección 2)
        vista.actualizarResultado("Infija: " + arbolExpresion.inordenConParentesis() + "\n"); 
    }

    private void mostrarArbolExpresion() {
         if (arbolExpresion == null || arbolExpresion.esVacio()) {
            vista.actualizarResultado(" Primero debe crear el Árbol de Expresión.\n");
            return;
        }
        mostrarEstructura(arbolExpresion, "ESTRUCTURA ÁRBOL EXPRESIÓN (Vertical)");
    }
    
    // ===================================
    //        LÓGICA ABB (Estudiantes)
    // ===================================
    private void insertarEstudiante() {
        String matriculaStr = vista.getMatricula();
        String nombre = vista.getNombreEstudiante();
        
        if (matriculaStr.isEmpty() || nombre.isEmpty()) {
            vista.actualizarResultado(" Debe ingresar la Matrícula y el Nombre del estudiante.\n");
            return;
        }
        
        try {
            int matricula = Integer.parseInt(matriculaStr);
            Estudiante nuevoEstudiante = new Estudiante(matricula, nombre);
            
            arbolBusqueda.insertar(nuevoEstudiante); //
            
            vista.actualizarResultado(" Estudiante insertado: " + nuevoEstudiante.toString() + "\n");
            
            // CORRECCIÓN del error: Usar métodos de limpieza de la Vista
            vista.limpiarMatricula();
            vista.limpiarNombreEstudiante();
            
        } catch (NumberFormatException e) {
            vista.actualizarResultado(" Error: La Matrícula debe ser un número entero válido.\n");
        } catch (Exception e) {
            vista.actualizarResultado(" Error al insertar (Posiblemente duplicado): " + e.getMessage() + "\n");
        }
    }
    
    private void buscarEstudiante() {
        String matriculaStr = vista.getMatricula();
        
        if (matriculaStr.isEmpty()) {
            vista.actualizarResultado(" Ingrese la matrícula a buscar.\n");
            return;
        }
        
        if (arbolBusqueda.esVacio()) {
            vista.actualizarResultado(" El árbol de búsqueda está vacío.\n");
            return;
        }
        
        try {
            int matricula = Integer.parseInt(matriculaStr);
            Estudiante estudianteBuscado = new Estudiante(matricula);
            
            // Usamos la búsqueda recursiva
            Nodo nodoEncontrado = arbolBusqueda.buscarRecursivo(estudianteBuscado); 
            
            if (nodoEncontrado != null) {
                vista.actualizarResultado(" Estudiante Encontrado: " + nodoEncontrado.valorNodo().toString() + "\n");
            } else {
                vista.actualizarResultado(" Estudiante con matrícula " + matricula + " NO encontrado.\n");
            }
        } catch (NumberFormatException e) {
            vista.actualizarResultado(" Error: La Matrícula debe ser un número entero válido.\n");
        }
    }
    
    private void eliminarEstudiante() {
        String matriculaStr = vista.getMatricula();
        
        if (matriculaStr.isEmpty()) {
            vista.actualizarResultado(" Ingrese la matrícula a eliminar.\n");
            return;
        }
        
        if (arbolBusqueda.esVacio()) {
            vista.actualizarResultado(" El árbol de búsqueda está vacío.\n");
            return;
        }
        
        try {
            int matricula = Integer.parseInt(matriculaStr);
            Estudiante estudianteAEliminar = new Estudiante(matricula);
            
            arbolBusqueda.eliminar(estudianteAEliminar); //
            
            vista.actualizarResultado(" Estudiante con matrícula " + matricula + " eliminado correctamente.\n");
            
            // CORRECCIÓN del error: Usar método de limpieza de la Vista
            vista.limpiarMatricula();
            
        } catch (NumberFormatException e) {
            vista.actualizarResultado(" Error: La Matrícula debe ser un número entero válido.\n");
        } catch (Exception e) {
            vista.actualizarResultado(" Error al eliminar: " + e.getMessage() + "\n");
        }
    }
    
    private void mostrarInordenABB() {
        if (arbolBusqueda == null || arbolBusqueda.esVacio()) {
            vista.actualizarResultado(" El árbol de búsqueda está vacío. Inserte un estudiante primero.\n");
            return;
        }
        vista.actualizarResultado("=== RECORRIDO INORDEN (Ordenado por Matrícula) ===\n");
        vista.actualizarResultado("Inorden:   " + arbolBusqueda.inorden() + "\n"); //
    }
    
    private void mostrarMinimoMaximo() {
        if (arbolBusqueda == null || arbolBusqueda.esVacio()) {
            vista.actualizarResultado(" El árbol de búsqueda está vacío. Inserte un estudiante primero.\n");
            return;
        }
        vista.actualizarResultado("=== EXTREMOS Y ESTADÍSTICAS BÁSICAS ===\n");
        vista.actualizarResultado("Elemento mínimo (menor matrícula): " + arbolBusqueda.encontrarMinimo() + "\n"); //
        vista.actualizarResultado("Elemento máximo (mayor matrícula): " + arbolBusqueda.encontrarMaximo() + "\n"); //
        vista.actualizarResultado("Número de Nodos: " + arbolBusqueda.numeroNodos() + "\n");
        vista.actualizarResultado("Altura del Árbol: " + arbolBusqueda.alturaArbol() + "\n");
    }
    
    private void mostrarEstructuraABB() {
        if (arbolBusqueda == null || arbolBusqueda.esVacio()) {
            vista.actualizarResultado(" El árbol de búsqueda está vacío. Inserte un estudiante primero.\n");
            return;
        }
        vista.actualizarResultado("\n=== ESTRUCTURA ÁRBOL BÚSQUEDA (Rotada 90°) ===\n");
        vista.actualizarResultado(arbolBusqueda.mostrarArbol());
    }
    
    // Método auxiliar para mostrar estructura vertical (Joyanes y Expresión)
    private void mostrarEstructura(ArbolBinario arbol, String titulo) {
        if (arbol == null || arbol.esVacio()) {
            vista.actualizarResultado(" El árbol está vacío.\n");
            return;
        }
        vista.actualizarResultado("\n=== " + titulo + " ===\n");
        vista.actualizarResultado(arbol.mostrarArbolConLineas()); //
    }
}