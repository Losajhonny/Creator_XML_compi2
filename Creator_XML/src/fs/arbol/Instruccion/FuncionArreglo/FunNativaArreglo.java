/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionArreglo;

import fs.arbol.Expresion.Expresion;
import fs.arbol.Instruccion.Declaracion.Arreglo;

/**
 *
 * @author Jhona
 */
public class FunNativaArreglo {
    
    public String id;
    public Expresion exp;
    public Arreglo arr;
    public int line, colm;
    
    public FunNativaArreglo(int line, int colm)
    {
        this.line = line;
        this.colm = colm;
    }
    
    public FunNativaArreglo(String id, int line, int colm)
    {
        this.id = id.toLowerCase();
        this.line = line;
        this.colm = colm;
    }
}
