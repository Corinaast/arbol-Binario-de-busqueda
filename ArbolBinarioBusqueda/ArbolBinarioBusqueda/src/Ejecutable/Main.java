package Ejecutable;

import javax.swing.*;

// <<<<<<<<<<<< ESTAS LÍNEAS SON LAS CLAVE >>>>>>>>>>>>
import vista.VistaArbol; 
import controlador.ControladorArbol; 
// <<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(" Simulador de Estructuras de Árbol Binario");
            
            VistaArbol vista = new VistaArbol(); 
            ControladorArbol controlador = new ControladorArbol(vista); 
            
            frame.add(vista); 
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 750); 
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true); 
        });
    }
}