/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion.Operaciones;

import fs.arbol.Expresion.Expresion;

/**
 *
 * @author Jhona
 */
public class Operacion {
    protected Expresion izq;
    protected Expresion der;
    
    public int line;
    public int colm;
    
    public Operador op;
    
    public Operacion(Expresion izq, Expresion der, Operador op, int line, int colm){
        this.izq = izq;
        this.der = der;
        this.op = op;
        this.line = line;
        this.colm = colm;
    }
    
    public Operacion(Expresion izq, Operador op, int line, int colm){
        this.izq = izq;
        this.der = null;
        this.op = op;
        this.line = line;
        this.colm = colm;
    }
    
    public Operacion(int line, int colm){
        this.izq = null;
        this.der = null;
        this.op = null;
        this.line = line;
        this.colm = colm;
    }
    
    public static String getBooleano(int val){
        if(val == 1)
            return "verdadero";
        return "falso";
    }
    
    public static int getBooleano(String val){
        if(val.toLowerCase().equals("verdadero"))
            return 1;
        return 0;
    }
    
    public static int getAsciiWord(String palabra){
        char cadena[] = palabra.toCharArray();
        int sum_codigo = 0;
        
        for (int i = 0; i < cadena.length; i++) {
            sum_codigo += (int)cadena[i];
        }
        return sum_codigo;
    }
    
    public enum Tipo {
        ENTERO,
        DECIMAL,
        CADENA,
        BOOLEANO,
        OBJETO,
        
        
        ARREGLO,
        NORMAL,
        
        DECLARACION,
        FUNCION,
        HETEROGENEO,
        HOMOGENEO,
        
        NULO,
        UNDEFINED,
        
        
        VENTANA,
        CONTENEDOR,
        CONTROLADOR,
        CONTROLADOR_TEXTO,
        CONTROLADOR_AREA,
        CONTROLADOR_NUMERICO,
        CONTROLADOR_DESPLEGABLE,
        MULTIMEDIA,
        ENVIAR,
        BOTON,
        TEXTO,
        
        STRUCT,
        STRUCT_VENTANA,
        STRUCT_CONTENEDOR,
        STRUCT_BOTON,
        STRUCT_ENVIAR,
        STRUCT_CONTROL,
        STRUCT_TEXTO,
        STRUCT_MULTIMEDIA,
        
        ERROR
    }
    
    public enum Operador {
        MAS,
        MENOS,
        POR,
        DIV,
        POT,
        MASMAS,
        MENMEN,

        IGUALIGUAL,
        DIFERENTE,
        MAYOR,
        MENOR,
        MAYORIGUAL,
        MENORIGUAL,

        AND,
        OR,
        NOT,
        
        
        MASIGUAL,
        MENIGUAL,
        PORIGUAL,
        DIVIGUAL,
        IGUAL,
        
        DESCENDENTE,
        ASCENDENTE,
        INVERTIR
    }
}
