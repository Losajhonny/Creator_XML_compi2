/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion;

/**
 *
 * @author Jhona
 */
public class Logica extends Operacion {
    
    public Logica(Operacion izq, Operacion der, Operador op, int line, int colm) {
        super(izq, der, op, line, colm);
    }
    
    public Logica(Operacion izq, Operador op, int line, int colm){
        super(izq, op, line, colm);
    }
    
}
