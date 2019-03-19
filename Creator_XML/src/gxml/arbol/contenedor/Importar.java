/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.contenedor;

/**
 *
 * @author Jhona
 */
public class Importar {
    public String cadena;
    public int line;
    public int colm;
    
    public Importar(String cadena, int line, int colm){
        this.cadena = cadena;
        this.line = line;
        this.colm = colm;
    }
}
