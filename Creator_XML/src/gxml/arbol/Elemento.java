/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol;

/**
 *
 * @author Jhona
 */
public class Elemento {
    public String id;
    public int tipo;
    public Object valor;
    
    public int line;
    public int colm;
    
    public Elemento(String id, int tipo, Object valor, int line, int colm){
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.line = line;
        this.colm = colm;
    }
}
