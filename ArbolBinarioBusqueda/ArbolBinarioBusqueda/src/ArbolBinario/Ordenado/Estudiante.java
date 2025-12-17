package ArbolBinario.Ordenado;

import ArbolBinario.Nodo; // Necesario para el contexto de un árbol genérico

public class Estudiante implements Comparador {
    private int matricula; 
    private String nombre; 

    public Estudiante(int matricula, String nombre) {
        this.matricula = matricula;
        this.nombre = nombre;
    }
    
    public Estudiante(int matricula) {
        this.matricula = matricula;
        this.nombre = "N/A"; // Valor temporal para búsquedas
    }
    
    public int getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    // Métodos de la interfaz Comparador (Tu lógica original es correcta)
    @Override
    public boolean igualQue(Object q) {
        return this.matricula == ((Estudiante) q).matricula;
    }

    @Override
    public boolean menorQue(Object q) {
        return this.matricula < ((Estudiante) q).matricula;
    }

    @Override
    public boolean menorIgualQue(Object q) {
        return this.matricula <= ((Estudiante) q).matricula;
    }

    @Override
    public boolean mayorQue(Object q) {
        return this.matricula > ((Estudiante) q).matricula;
    }

    @Override
    public boolean mayorIgualQue(Object q) {
        return this.matricula >= ((Estudiante) q).matricula;
    }

    @Override
    public String toString() {
        return matricula + " [" + nombre + "]";
    }
}