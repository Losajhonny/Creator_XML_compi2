/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

/**
 *
 * @author Jhona
 */
public class Error {
    public String tipo;         //lexico, sintactico, semantico
    public String lenguaje;     //gxml, fs
    public String descripcion;
    public String lexema;
    public int line;
    public int colm;
    
    public Error(String lenguaje, String tipo, String lexema, String descripcion, int line, int colm){
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.lexema = lexema;
        this.descripcion = descripcion;
        this.line = line;
        this.colm = colm;
    }
    
    public Error(String lenguaje, String tipo, String descripcion, int line, int colm){
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.lexema = "";
        this.descripcion = descripcion;
        this.line = line;
        this.colm = colm;
    }
}
