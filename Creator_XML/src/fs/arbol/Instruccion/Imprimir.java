/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Imprimir implements Instruccion{
    public Expresion exp;
    public int line, colm;
    
    public Imprimir(Expresion exp, int line, int colm){
        this.exp = exp;
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        
        Literal L = (Literal) exp.evaluar(ent);
        
        /*aqui puede venir un objeto, arreglo o literal
        necesito arreglarlo*/
        
        if (L.tipo == Tipo.ARREGLO || L.tipo == Tipo.OBJETO || L.tipo == Tipo.UNDEFINED 
                || L.tipo == Tipo.ERROR || L.tipo == Tipo.STRUCT
                || L.tipo == Tipo.STRUCT_BOTON
                || L.tipo == Tipo.STRUCT_CONTENEDOR
                || L.tipo == Tipo.STRUCT_CONTROL
                || L.tipo == Tipo.STRUCT_ENVIAR
                || L.tipo == Tipo.STRUCT_VENTANA
                || L.tipo == Tipo.STRUCT_TEXTO
                || L.tipo == Tipo.STRUCT_MULTIMEDIA)
        {
            Constante.consola += L.tipo + "\n";
        }
        else{
//            if(L.tipo != Tipo.ERROR)
//            {
//                //error
//                String msg = "Tipos incompatibles: \"" + L.tipo + "\" no puede ser convertido a \"" + Tipo.CADENA + "\"";
//                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                otros.Error.agregarError(err);
//            }
//            else
//            {

                if(Constante.consolaTexto.getText().equals(""))
                {
                    Constante.consola = "";
                }
                
                Constante.consola += String.valueOf(L.valor) + "\n";
                Constante.consolaTexto.setText(Constante.consola);
//                String print = Constante.consolaTexto.getText() + String.valueOf(L.valor) + "\n";
//                Constante.consolaTexto.setText(print);
//            }
        }
        return this;
    }
}
