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
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class ObtenerPorNombre extends FunNativaInterfaz implements Expresion {

    public Expresion exp1;
    public Expresion exp2;
    
    public Literal res1;
    public Literal res2;
    
    public ObtenerPorNombre(Expresion exp1, Expresion exp2, int line, int colm) {
        super(line, colm);
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Object evaluar(Entorno ent) {
        
        res1 = (Literal) exp1.evaluar(ent);
        res2 = (Literal) exp2.evaluar(ent);
        
        if(res1.tipo == Operacion.Tipo.CADENA && res2.tipo == Operacion.Tipo.CADENA)
        {
            return this;
        }
        else
        {
            String msg = "Las expresiones debe ser de tipo CADENA";
            otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
        
    }
    
}
