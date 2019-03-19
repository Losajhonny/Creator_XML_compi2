/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Seleccion;

import entorno.Entorno;
import fs.arbol.Instruccion.Detener;
import fs.arbol.Instruccion.Instruccion;
import fs.arbol.Instruccion.Retornar;
import java.util.LinkedList;

/**
 *
 * @author Jhona
 */
public class Sino implements Instruccion{
    
    public LinkedList<Instruccion> instrucciones;
    public int line, colm;
    
    public Sino(LinkedList<Instruccion> instrucciones, int line, int colm)
    {
        this.instrucciones = instrucciones;
        this.line = line;
        this.colm = colm;
    }
    
    public Sino(int line, int colm)
    {
        this.instrucciones = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Entorno local = new Entorno(ent);
        
        for (Instruccion inst : instrucciones) {
            Object obj = inst.ejecutar(local);
            
            if(obj instanceof Detener) { return obj; }
            if(obj instanceof Retornar) { return obj; }
        }
        
        return this;
    }
}
