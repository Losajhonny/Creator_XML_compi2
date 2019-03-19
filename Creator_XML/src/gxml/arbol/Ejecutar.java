/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol;

import entorno.Entorno;

/**
 *
 * @author Jhona
 */
public interface Ejecutar {
    
    /**
     * Genera una cadena de traduccion al lenguaje FuncionScript
     * @param ent
     * @return 
     */
    String generarFs(Entorno ent);
    
    /**
     * Valida que los elementos obligatorios esten presentes en la ventana
     * Caso 1: Cuando si existen los elementos obligatorios retorna true
     * Caso 2: Cuando estan incompletos retorna false
     * @param ent
     * @return 
     */
    boolean validarElementos(Entorno ent);
    
    
    /**
     * Retorna una estructura de un archivo analizado para el lenguaje
     * Funcion Script
     * @param ent
     * @return 
     */
    public Object getStruct(Entorno ent);
    
}
