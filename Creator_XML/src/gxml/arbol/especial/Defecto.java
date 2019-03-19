/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.especial;

import entorno.Entorno;
import gxml.arbol.Ejecutar;

/**
 *
 * @author Jhona
 */
public class Defecto extends Especial implements Ejecutar {
    public String texto;
    
    public Defecto(String texto){
        this.texto = texto;
    }

    @Override
    public String generarFs(Entorno ent) {
        return texto;
    }

    @Override
    public boolean validarElementos(Entorno ent) {
        return true;
    }

    @Override
    public Object getStruct(Entorno ent) {
        
        boolean tmp = validarElementos(ent);
        
        return this;
    }
}
