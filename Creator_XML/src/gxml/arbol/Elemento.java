/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol;

import fs.arbol.Expresion.Operaciones.Operacion.Tipo;

/**
 *
 * @author Jhona
 */
public class Elemento {
    public String id;
    public String valor;
    
    public Tipo tipo;
    public int line;
    public int colm;
    
    public Elemento(String id, Tipo tipo, String valor, int line, int colm){
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.line = line;
        this.colm = colm;
    }
}
