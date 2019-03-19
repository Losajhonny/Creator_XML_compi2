/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

import java.util.Hashtable;

/**
 *
 * @author Jhona
 */
public class Entorno {
    public Entorno ant;
    public Hashtable tabla;
    public String ambito;
    
    public Entorno(Entorno ent){
        this.ant = ent;
        this.tabla = new Hashtable();
    }

    public void agregar(String key, Simbolo s){
    	tabla.put(key, s);
    }

    public Simbolo getSimbolo_EntActual(String key){
        Entorno e = this;
        Simbolo find = (Simbolo)e.tabla.get(key);
        return find;
    }
    
    public Simbolo getSimbolo_Ent(String key){
        for(Entorno e = this; e != null; e = e.ant){
            Simbolo find = (Simbolo)e.tabla.get(key);
            if(find != null) return find;
        }
        return null;
    }
}
