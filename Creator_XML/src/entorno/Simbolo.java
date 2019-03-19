/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

import fs.arbol.Expresion.Operaciones.Operacion.Tipo;

/**
 *
 * @author Jhona
 */
public class Simbolo {
    public String id;
    
    public Tipo tipo;           //tipo de dato      entero, decimal, booleano, objeto, arreglo
    public Tipo tipo_nodo;      //tipo de nodo      declaracion, funcion
    public int tam_arreglo;
    public int no_parametros;
    
    public String tipo_simbolo;
    public Object valor;
    
    public Object componente;

    public Simbolo(String id, String tipo, Object valor){
        this.id = id;
        this.tipo_simbolo = tipo;
	this.valor = valor;
        this.componente = null;
    }
    
    public Simbolo(String id, Tipo tipo, Tipo tipo_nodo, Object valor){
        this.id = id;
        this.tipo = tipo;
        this.tipo_nodo = tipo_nodo;
        this.valor = valor;
        this.componente = null;
    }
    
     public Simbolo(String id, Tipo tipo, Object valor){
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.componente = null;
    }
}
