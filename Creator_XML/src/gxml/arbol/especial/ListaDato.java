/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.especial;

import entorno.Entorno;
import gxml.arbol.Ejecutar;
import java.util.LinkedList;

/**
 *
 * @author Jhona
 */
public class ListaDato extends Especial implements Ejecutar {
    public LinkedList<String> datos;
    
    public ListaDato(LinkedList<String> datos, int line, int colm){
        this.datos = datos;
        this.line = line;
        this.colm = colm;
        this.cadena = "";
        this.tipo = "";
    }

    @Override
    public String generarFs(Entorno ent) {
        /*validar y crear un array de los datos que tiene listadato*/
        cadena += "var " + control.nombre.valor + "_" + control.contenedor.ventana.id.valor + " = [ ";
        
        int i;
        
        for(i = 0; i < datos.size()-1; i++){
            //try{
            //    Double.parseDouble(datos.get(i));
            //    cadena += datos.get(i) + ", ";
            //}catch(NumberFormatException ex){
                cadena += "\"" + datos.get(i) + "\", ";
            //}
        }
        
        //try{
        //    Double.parseDouble(datos.get(i));
        //    cadena += datos.get(i) + " ] ;\n";
        //}catch(NumberFormatException ex){
            cadena += "\"" + datos.get(i) + "\" ] ;\n";
        //}
        return cadena;
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
