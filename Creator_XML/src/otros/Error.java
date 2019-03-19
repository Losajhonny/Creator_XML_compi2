/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import creator_xml.principal;
import java.util.Date;

/**
 *
 * @author Jhona
 */
public class Error {
    public String lenguaje;     //gxml, fs
    public String tipo;         //lexico, sintactico, semantico
    public String lexema;       //caracter
    public String ambito;       // (ventana, contenedor)    ( identificador )
    public String descripcion;
    public String archivo;
    public int line;
    public int colm;
    public String fecha_hora;
    
    public Error(String lenguaje, String tipo, String lexema, String ambito, String descripcion, String archivo, int line, int colm){
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.lexema = lexema;
        this.ambito = ambito;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.line = line;
        this.colm = colm;
        
        Date fecha_ = new Date();
        fecha_hora = fecha_.getDate() + "/" + (fecha_.getMonth()+1) + "/" + (1900 + fecha_.getYear());
        fecha_hora += " " + fecha_.getHours() + ":" + fecha_.getMinutes();
    }
    
    public Error(String lenguaje, String tipo, String lexema, String descripcion, int line, int colm){
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.lexema = lexema;
        this.descripcion = descripcion;
        this.line = line;
        this.colm = colm;
        
        Date fecha_ = new Date();
        fecha_hora = fecha_.getDate() + "/" + (fecha_.getMonth()+1) + "/" + (1900 + fecha_.getYear());
        fecha_hora += " " + fecha_.getHours() + ":" + fecha_.getMinutes();
    }
    
    public Error(String lenguaje, String tipo, String descripcion, int line, int colm){
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.lexema = "";
        this.descripcion = descripcion;
        this.line = line;
        this.colm = colm;
        
        Date fecha_ = new Date();
        fecha_hora = fecha_.getDate() + "/" + (fecha_.getMonth()+1) + "/" + (1900 + fecha_.getYear());
        fecha_hora += " " + fecha_.getHours() + ":" + fecha_.getMinutes();
    }
    
    public Error(String lenguaje, String tipo, String descripcion, String ambito, int line, int colm, String tmp){
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.lexema = "";
        this.descripcion = descripcion;
        this.line = line;
        this.colm = colm;
        
        Date fecha_ = new Date();
        fecha_hora = fecha_.getDate() + "/" + (fecha_.getMonth()+1) + "/" + (1900 + fecha_.getYear());
        fecha_hora += " " + fecha_.getHours() + ":" + fecha_.getMinutes();
    }
    
    public static void agregarError(Error err)
    {
        Constante.errores.add(err);
        principal.actualizarTabla();
    }
}
