/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion;

import entorno.*;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Operacion implements Expresion {
    protected Operacion izq;
    protected Operacion der;
    
    protected int line;
    protected int colm;
    
    protected Operador op;
    
    public Operacion(Operacion izq, Operacion der, Operador op, int line, int colm){
        this.izq = izq;
        this.der = der;
        this.op = op;
        this.line = line;
        this.colm = colm;
    }
    
    public Operacion(Operacion izq, Operador op, int line, int colm){
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

    @Override
    public Object evaluar(Entorno ent) {
        return null;
    }

    @Override
    public int getTipo(Literal izq, Literal der) {
        return Constante.ERROR;
    }
}
