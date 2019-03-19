/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionInterfaz;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;

/**
 *
 * @author Jhona
 */
public class AlCerrar extends FunNativaInterfaz implements Expresion {

    public Expresion exp;
    
    public AlCerrar(Expresion exp, int line, int colm) {
        super(line, colm);
        this.exp = exp;
    }
    
    public AlCerrar(int line, int colm)
    {
        super(line, colm);
        this.exp = null;
    }

    @Override
    public Object evaluar(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}