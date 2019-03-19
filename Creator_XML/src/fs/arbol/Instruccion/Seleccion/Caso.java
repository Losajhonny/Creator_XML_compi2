/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Seleccion;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Instruccion.Detener;
import fs.arbol.Instruccion.Instruccion;
import fs.arbol.Instruccion.Retornar;
import java.util.LinkedList;

/**
 *
 * @author Jhona
 */
public class Caso implements Instruccion {
    
    public Expresion exp;
    public LinkedList<Instruccion> instrucciones;
    public int line, colm;
    
    public Caso(Expresion exp, LinkedList<Instruccion> instrucciones, int line, int colm)
    {
        this.exp = exp;
        this.instrucciones = instrucciones;
        this.line = line;
        this.colm = colm;
    }
    
    public Caso(Expresion exp, int line, int colm)
    {
        this.exp = exp;
        this.instrucciones = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }
    
    public Caso(int line, int colm)
    {
        this.exp = null;
        this.instrucciones = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }
    
    public Caso(LinkedList<Instruccion> instrucciones, int line, int colm)
    {
        this.exp = null;
        this.instrucciones = instrucciones;
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        
        for (Instruccion inst : instrucciones) {
            Object obj = inst.ejecutar(ent);
            
            if(obj instanceof Detener || obj instanceof Retornar) { return obj; }
        }
        
        return this;
    }
    
}
