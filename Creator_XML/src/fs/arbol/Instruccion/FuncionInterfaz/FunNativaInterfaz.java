/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionInterfaz;

import fs.arbol.Expresion.Expresion;
import java.util.LinkedList;

/**
 *
 * @author Jhona
 */
public class FunNativaInterfaz {
    
    public String id;
    public LinkedList<Expresion> exps;
    public int line, colm;
    
    public FunNativaInterfaz(LinkedList<Expresion> exps, int line, int colm)
    {
        this.exps = exps;
        this.line = line;
        this.colm = colm;
    }
    
    public FunNativaInterfaz(int line, int colm)
    {
        this.line = line;
        this.colm = colm;
    }
    
}
