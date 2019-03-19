/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;

/**
 *
 * @author Jhona
 */
public class Retornar implements Instruccion{
    
    public Expresion exp;
    public Literal literal;
    public int line, colm;
    
    public Retornar (Expresion exp, int line, int colm)
    {
        this.exp = exp;
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        literal = (exp != null)? (Literal) exp.evaluar(ent): null;
        
        if(exp != null)
        {
            literal = (Literal) exp.evaluar(ent);
            literal = new Literal(literal.tipo, literal.valor, literal.line, literal.colm);
        }
        
        return this;
    }
}
