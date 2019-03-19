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
public class Par {
    public String id;
    public Object valor;
    public int line, colm;
    
    public Par(String id, Object valor, int line, int colm){
        this.id = id.toLowerCase();
        this.valor = valor;
        this.line = line;
        this.colm = colm;
    }
}
