/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Declaracion;

/**
 *
 * @author Jhona
 */
public class Id {
    public String id;
    public int line, colm;
    
    public Id(String id, int line, int colm){
        this.id = id.toLowerCase();
        this.line = line;
        this.colm = colm;
    }
}
