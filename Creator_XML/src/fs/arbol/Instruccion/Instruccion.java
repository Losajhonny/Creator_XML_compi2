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
public interface Instruccion {
    
    /*
    Este metodo realizara la ejecucion
    de las instrucciones que se realizaran
    en el lenguaje
    */
    
    public Object ejecutar(Entorno ent);
    
}
