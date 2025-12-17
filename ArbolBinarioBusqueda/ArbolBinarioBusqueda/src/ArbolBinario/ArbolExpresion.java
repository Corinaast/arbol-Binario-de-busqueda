package ArbolBinario;

import tipopila.PilaLista;
import ArbolBinario.Ordenado.Comparador;

public class ArbolExpresion extends ArbolBinario {
    
    public ArbolExpresion() {
        super();
    }
    
    public ArbolExpresion(Nodo raiz) {
        super(raiz);
    }

    public void construirDesdePostfija(String expresion) {
        crearDesdePostfija(expresion);
    }
    
    // Construir árbol de expresión desde notación postfija
    public void crearDesdePostfija(String expresion) {
        PilaLista pila = new PilaLista();
        String[] tokens = expresion.trim().split("\\s+"); 
        
        if (tokens.length == 0 || (tokens.length == 1 && tokens[0].isEmpty())) {
            throw new IllegalArgumentException("La expresión postfija no puede estar vacía.");
        }
        
        for (String token : tokens) {
            if (esOperador(token)) {
                Nodo dcho = (Nodo) pila.quitar();
                Nodo izdo = (Nodo) pila.quitar();
                
                if (dcho == null || izdo == null) {
                    throw new IllegalArgumentException("Expresión postfija incorrecta: faltan operandos para el operador '" + token + "'.");
                }
                
                Nodo nuevo = new Nodo(izdo, token, dcho);
                pila.insertar(nuevo);
            } else {
                pila.insertar(new Nodo(token));
            }
        }
        
        this.raiz = (Nodo) pila.quitar();
        if (!pila.esVacia()) {
            throw new IllegalArgumentException("Expresión postfija incorrecta: Sobran elementos o faltan operadores.");
        }
    }
    
    private boolean esOperador(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"); //
    }

    // ===================================
    //        EVALUACIÓN
    // ===================================
    public double evaluar() {
        return evaluarRec(raiz);
    }
    
    private double evaluarRec(Nodo r) {
        if (r == null) {
            return 0;
        }

        String datoStr = r.valorNodo().toString();
        
        if (!esOperador(datoStr)) {
            try {
                return Double.parseDouble(datoStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Operando '" + datoStr + "' no es un número válido.", e);
            }
        } else {
            double a = evaluarRec(r.subarbolIzdo());
            double b = evaluarRec(r.subarbolDcho());
            
            switch (datoStr) {
                case "+": return a + b;
                case "-": return a - b;
                case "*": return a * b;
                case "/": 
                    if (b == 0) {
                        throw new ArithmeticException("División por cero detectada.");
                    }
                    return a / b;
                default:
                    throw new UnsupportedOperationException("Operador desconocido: " + datoStr);
            }
        }
    }
    
    // ===================================
    //        INFIJA (CON PARÉNTESIS)
    // ===================================
    
    public String inordenConParentesis() {
        if (esVacio()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        inordenConParentesis(raiz, sb);
        return sb.toString().trim();
    }
    
    // Traversal for infix expression with necessary parentheses
    private void inordenConParentesis(Nodo r, StringBuilder sb) {
        if (r != null) {
            String datoStr = r.valorNodo().toString();
            
            // Colocar paréntesis solo si es un operador y tiene ambos subárboles (hijos)
            boolean necesitaParentesis = esOperador(datoStr) && 
                                         (r.subarbolIzdo() != null && r.subarbolDcho() != null); 
            
            if (necesitaParentesis) {
                sb.append("(");
            }
            
            inordenConParentesis(r.subarbolIzdo(), sb);
            
            sb.append(datoStr).append(" ");
            
            inordenConParentesis(r.subarbolDcho(), sb);

            if (necesitaParentesis) {
                // Quitar el último espacio antes de cerrar el paréntesis
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append(") ");
            }
        }
    }
}