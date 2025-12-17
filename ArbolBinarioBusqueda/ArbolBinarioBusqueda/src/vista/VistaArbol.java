package vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class VistaArbol extends JPanel {
    private JTextArea areaResultado;
    
    // Campos de texto para Estudiantes (ABB)
    private JTextField txtMatricula;
    private JTextField txtNombreEstudiante;
    
    // Campos de texto para Expresiones
    private JTextField txtExpresionPostfija;

    // Botones de Arbol Binario General
    private JButton btnCrearJoyanes;
    private JButton btnMostrarRecorridos;
    private JButton btnMostrarArbol;
    
    // Botones de Arbol de Expresión
    private JButton btnCrearExpresion;
    private JButton btnEvaluarExpresion;
    private JButton btnMostrarInfija;
    private JButton btnMostrarArbolExpresion;
    
    // Botones de Arbol Binario de Búsqueda (ABB)
    private JButton btnInsertarEstudiante;
    private JButton btnBuscarEstudiante;
    private JButton btnEliminarEstudiante;
    private JButton btnMostrarABB;
    private JButton btnMostrarInorden;
    private JButton btnMinimoMaximo;

    // Colores personalizados
    private final Color COLOR_PRIMARY = new Color(63, 81, 181);      
    private final Color COLOR_SECONDARY = new Color(92, 107, 192);   
    private final Color COLOR_ACCENT = new Color(255, 64, 129);      
    private final Color COLOR_SUCCESS = new Color(76, 175, 80);      
    private final Color COLOR_WARNING = new Color(255, 152, 0);      
    private final Color COLOR_BG_LIGHT = new Color(250, 250, 250);   
    private final Color COLOR_BG_PANEL = new Color(255, 255, 255);   
    private final Color COLOR_TEXT = new Color(33, 33, 33);          
    private final Color COLOR_BORDER = new Color(224, 224, 224);     

    public VistaArbol() {
        setPreferredSize(new Dimension(1100, 720));
        setBackground(COLOR_BG_LIGHT);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con título
        add(crearPanelTitulo(), BorderLayout.NORTH);
        
        // Panel central con resultados
        add(crearPanelResultados(), BorderLayout.CENTER);
        
        // Panel lateral con controles (CON SCROLL FUNCIONAL)
        add(crearPanelLateralConScroll(), BorderLayout.WEST);
    }
    
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel lblTitulo = new JLabel(" Simulador de Árboles Binarios");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Gestión y visualización de estructuras de datos");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitulo.setForeground(new Color(220, 220, 220));
        
        JPanel panelTexto = new JPanel(new GridLayout(2, 1, 0, 5));
        panelTexto.setBackground(COLOR_PRIMARY);
        panelTexto.add(lblTitulo);
        panelTexto.add(lblSubtitulo);
        
        panel.add(panelTexto, BorderLayout.WEST);
        
        return panel;
    }
    
    private JScrollPane crearPanelResultados() {
        areaResultado = new JTextArea();
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaResultado.setEditable(false);
        areaResultado.setBackground(COLOR_BG_PANEL);
        areaResultado.setForeground(COLOR_TEXT);
        areaResultado.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        areaResultado.setLineWrap(false);
        areaResultado.setText("=== BIENVENIDO AL SIMULADOR DE ÁRBOLES BINARIOS ===\n\n" +
                             "Instrucciones:\n" +
                             "1. Use el scroll del panel izquierdo para ver todas las opciones\n" +
                             "2. Complete los campos necesarios según la operación\n" +
                             "3. Los resultados se mostrarán aquí\n\n");
        
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                "📊 Consola de Resultados",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                COLOR_PRIMARY
            ),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        return scroll;
    }
    
    /**
     * MÉTODO CORREGIDO: Crea el panel lateral con scroll funcional
     */
    private JScrollPane crearPanelLateralConScroll() {
        // Panel contenedor principal
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(COLOR_BG_LIGHT);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        
        // Agregar todas las secciones
        panelContenido.add(crearPanelArbolGeneral());
        panelContenido.add(Box.createVerticalStrut(15));
        
        panelContenido.add(crearPanelArbolExpresion());
        panelContenido.add(Box.createVerticalStrut(15));
        
        panelContenido.add(crearPanelArbolBusqueda());
        panelContenido.add(Box.createVerticalStrut(20)); // Espacio final
        
        // Crear JScrollPane con el contenido
        JScrollPane scrollPane = new JScrollPane(panelContenido);
        scrollPane.setPreferredSize(new Dimension(400, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocidad de scroll suave
        
        return scrollPane;
    }
    
    private JPanel crearPanelArbolGeneral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_BG_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_PRIMARY, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(380, 250)); // Tamaño fijo para este panel

        JLabel titulo = new JLabel(" Árbol Binario General");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(COLOR_PRIMARY);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(12));

        btnCrearJoyanes = crearBotonEstilizado("Crear Árbol Joyanes", COLOR_PRIMARY);
        btnMostrarRecorridos = crearBotonEstilizado("Mostrar Recorridos", COLOR_SECONDARY);
        btnMostrarArbol = crearBotonEstilizado("Mostrar Estructura", COLOR_SECONDARY);
        
        panel.add(btnCrearJoyanes);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnMostrarRecorridos);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnMostrarArbol);
        
        return panel;
    }
    
    private JPanel crearPanelArbolExpresion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_BG_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_ACCENT, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(380, 350)); // Tamaño fijo

        JLabel titulo = new JLabel(" Árbol de Expresión");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(COLOR_ACCENT);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(12));

        // Campo de expresión
        txtExpresionPostfija = crearCampoTextoEstilizado(25);
        panel.add(crearGrupoInput("Expresión Postfija:", txtExpresionPostfija));
        
        JLabel lblEjemplo = new JLabel("Ejemplo: 2 3 * 5 +");
        lblEjemplo.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblEjemplo.setForeground(Color.GRAY);
        lblEjemplo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblEjemplo);
        panel.add(Box.createVerticalStrut(12));

        btnCrearExpresion = crearBotonEstilizado("Crear Árbol Expresión", COLOR_ACCENT);
        btnEvaluarExpresion = crearBotonEstilizado("Evaluar Expresión", COLOR_SUCCESS);
        btnMostrarInfija = crearBotonEstilizado("Mostrar Infija", COLOR_WARNING);
        btnMostrarArbolExpresion = crearBotonEstilizado("Visualizar Árbol", COLOR_SECONDARY);
        
        panel.add(btnCrearExpresion);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnEvaluarExpresion);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnMostrarInfija);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnMostrarArbolExpresion);
        
        return panel;
    }
    
    private JPanel crearPanelArbolBusqueda() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_BG_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_SUCCESS, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setMaximumSize(new Dimension(380, 480)); // Tamaño fijo para este panel

        JLabel titulo = new JLabel(" Árbol Binario de Búsqueda");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(COLOR_SUCCESS);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(12));

        // Campo Matrícula
        txtMatricula = crearCampoTextoEstilizado(15);
        panel.add(crearGrupoInput("Matrícula:", txtMatricula));
        panel.add(Box.createVerticalStrut(12));
        
        // Campo Nombre
        txtNombreEstudiante = crearCampoTextoEstilizado(20);
        panel.add(crearGrupoInput("Nombre del Estudiante:", txtNombreEstudiante));
        panel.add(Box.createVerticalStrut(15));

        btnInsertarEstudiante = crearBotonEstilizado(" Insertar Estudiante", COLOR_SUCCESS);
        btnBuscarEstudiante = crearBotonEstilizado(" Buscar Estudiante", COLOR_SECONDARY);
        btnEliminarEstudiante = crearBotonEstilizado(" Eliminar Estudiante", COLOR_ACCENT);
        btnMostrarInorden = crearBotonEstilizado(" Mostrar Inorden", COLOR_PRIMARY);
        btnMinimoMaximo = crearBotonEstilizado(" Mínimo/Máximo", COLOR_WARNING);
        btnMostrarABB = crearBotonEstilizado(" Visualizar Árbol ABB", new Color(120, 80, 200));
        
        panel.add(btnInsertarEstudiante);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnBuscarEstudiante);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnEliminarEstudiante);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnMostrarInorden);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnMinimoMaximo);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnMostrarABB);
        
        return panel;
    }
  
    private JPanel crearGrupoInput(String etiqueta, JTextField campo) {
        JPanel panelGrupo = new JPanel();
        panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.Y_AXIS));
        panelGrupo.setBackground(COLOR_BG_PANEL);
        panelGrupo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        campo.setMaximumSize(new Dimension(350, 35));
        campo.setPreferredSize(new Dimension(350, 35));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelGrupo.add(lbl);
        panelGrupo.add(Box.createVerticalStrut(5));
        panelGrupo.add(campo);
        
        panelGrupo.setMaximumSize(new Dimension(350, 60)); 
        
        return panelGrupo;
    }

    private JButton crearBotonEstilizado(String texto, Color colorBase) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorBase);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setMaximumSize(new Dimension(350, 38));
        boton.setMinimumSize(new Dimension(350, 38));
        boton.setPreferredSize(new Dimension(350, 38));
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase.darker());
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase.brighter());
            }
        });
        
        return boton;
    }
    
    private JTextField crearCampoTextoEstilizado(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDER, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return campo;
    }
    
    // ===================================
    //        MÉTODOS PÚBLICOS 
    // ===================================
    
    public void actualizarResultado(String mensaje) {
        areaResultado.append(mensaje);
        areaResultado.setCaretPosition(areaResultado.getDocument().getLength());
    }
    
    public void limpiarResultado() {
        areaResultado.setText("");
    }
    
    public void limpiarCampos() {
        txtMatricula.setText("");
        txtNombreEstudiante.setText("");
        txtExpresionPostfija.setText("");
    }
    
    public void limpiarMatricula() {
        txtMatricula.setText("");
    }
    
    public void limpiarNombreEstudiante() {
        txtNombreEstudiante.setText("");
    }
    
    public void limpiarExpresion() {
        txtExpresionPostfija.setText("");
    }
    
    // Getters para campos de texto
    public String getExpresionPostfija() { 
        return txtExpresionPostfija.getText().trim(); 
    }
    
    public String getMatricula() { 
        return txtMatricula.getText().trim(); 
    }
    
    public String getNombreEstudiante() { 
        return txtNombreEstudiante.getText().trim(); 
    }
    
    public JButton getBtnCrearJoyanes() { return btnCrearJoyanes; }
    public JButton getBtnMostrarRecorridos() { return btnMostrarRecorridos; }
    public JButton getBtnMostrarArbol() { return btnMostrarArbol; }
    public JButton getBtnCrearExpresion() { return btnCrearExpresion; }
    public JButton getBtnEvaluarExpresion() { return btnEvaluarExpresion; }
    public JButton getBtnMostrarInfija() { return btnMostrarInfija; }
    public JButton getBtnMostrarArbolExpresion() { return btnMostrarArbolExpresion; }
    public JButton getBtnInsertarEstudiante() { return btnInsertarEstudiante; }
    public JButton getBtnBuscarEstudiante() { return btnBuscarEstudiante; }
    public JButton getBtnEliminarEstudiante() { return btnEliminarEstudiante; }
    public JButton getBtnMostrarABB() { return btnMostrarABB; }
    public JButton getBtnMostrarInorden() { return btnMostrarInorden; }
    public JButton getBtnMinimoMaximo() { return btnMinimoMaximo; }
}