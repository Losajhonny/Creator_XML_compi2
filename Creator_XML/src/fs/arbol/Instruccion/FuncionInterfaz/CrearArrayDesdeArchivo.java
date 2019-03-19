/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionInterfaz;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Instruccion.Instruccion;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class CrearArrayDesdeArchivo extends FunNativaInterfaz implements Expresion, Instruccion {

    public Expresion exp;
    public Literal res;
    
    public CrearArrayDesdeArchivo(Expresion exp, int line, int colm) {
        super(line, colm);
        this.exp = exp;
    }
    
    public CrearArrayDesdeArchivo(int line, int colm) {
        super(line, colm);
        this.exp = null;
    }

    @Override
    public Object evaluar(Entorno ent) {
        return execute(ent);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        return execute(ent);
    }
    
    public Object execute(Entorno ent)
    {
        if(exp != null)
        {
            return exp.evaluar(ent);
        }
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
}
