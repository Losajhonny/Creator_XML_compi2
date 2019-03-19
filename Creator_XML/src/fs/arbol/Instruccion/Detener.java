/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion;

import entorno.Entorno;

/**
 *
 * @author Jhona
 */
public class Detener implements Instruccion{
    
    public int line, colm;
    
    public Detener(int line, int colm){
        this.line = line;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        return this;
    }
    
}
