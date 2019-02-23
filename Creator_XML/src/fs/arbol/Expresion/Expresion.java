/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion;

import entorno.*;

/**
 *
 * @author Jhona
 */
public interface Expresion {
    
    public int getTipo(Literal izq, Literal der);
    
    public Object evaluar(Entorno ent);
    
}
