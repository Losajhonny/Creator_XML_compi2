/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion;

import entorno.*;
import fs.arbol.Expresion.Operaciones.Operacion;

/**
 *
 * @author Jhona
 */
public class Literal extends Operacion implements Expresion {
    
    public Tipo tipo;
    public Object valor;
    public Object val_ant;
    
    public Literal(Tipo tipo, Object valor, int line, int colm) {
        super(line, colm);
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public Object evaluar(Entorno ent) {
        return this;
    }
}
