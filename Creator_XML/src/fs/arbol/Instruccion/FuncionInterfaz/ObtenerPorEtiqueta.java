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
public class ObtenerPorEtiqueta extends FunNativaInterfaz implements Expresion {

    public Expresion exp;
    
    public ObtenerPorEtiqueta(Expresion exp, int line, int colm) {
        super(line, colm);
        this.exp = exp;
    }

    @Override
    public Object evaluar(Entorno ent) {
        
        Literal res = (Literal) exp.evaluar(ent);
        
        if(res.tipo == Operacion.Tipo.CADENA)
        {
            return res;
        }
        else
        {
            String msg = "La expresion debe ser de tipo CADENA";
            otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
}
