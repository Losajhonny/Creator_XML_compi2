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
public class Literal extends Operacion implements Expresion {
    
    public int tipo;        //Tipo comun que es entero, decimal, booleano, caracter, cadena
    public Object valor;
    
    public Literal(int tipo, Object valor, int line, int colm) {
        super(line, colm);
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public Object evaluar(Entorno ent) {
        return this;
    }

    @Override
    public int getTipo(Literal izq, Literal der) {
        return tipo;
    }
}
