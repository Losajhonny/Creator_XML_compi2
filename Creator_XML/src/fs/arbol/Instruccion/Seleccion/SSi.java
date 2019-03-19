/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Seleccion;

import fs.arbol.Expresion.Expresion;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author Jhona
 */
public class SSi {
    
    public Expresion condicion;
    public LinkedList<Instruccion> instrucciones;
    public int line, colm;
    
    public SSi(Expresion condicion, LinkedList<Instruccion> instrucciones, int line, int colm)
    {
        this.condicion = condicion;
        this.instrucciones = instrucciones;
        this.line = line;
        this.colm = colm;
    }
    
    public SSi(Expresion condicion, int line, int colm)
    {
        this.condicion = condicion;
        this.instrucciones = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }
    
}
