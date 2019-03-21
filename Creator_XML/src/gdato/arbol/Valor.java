/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdato.arbol;

import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Instruccion.Declaracion.Par;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Valor implements Instrucciong {
    
    public String tagini;
    public String tagfin;
    public Tgdato tipo;
    public String valor;
    public int line, colm;
    
    public Valor(String tagini, String valor, Tgdato tipo, String tagfin, int line, int colm)
    {
        this.tagini = tagini;
        this.tagfin = tagfin;
        this.valor = valor;
        this.line = line;
        this.colm = colm;
        this.tipo = tipo;
    }
    
    public Valor(String tagini, String valor, Tgdato tipo, String tagfin)
    {
        this.tagini = tagini;
        this.tagfin = tagfin;
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public String generarCadena() {
        
        String cadena = "";
        
        cadena += "\t\t<" + tagini + ">" + ((tipo == Tgdato.CADENA)? "\"" + valor + "\"": valor);
        cadena += "</" + tagfin + ">\n";
        
        return cadena;
    }

    @Override
    public Object generarArreglo() {
        
        if(tagini.toLowerCase().equals(tagfin.toLowerCase()))
        {
            Operacion.Tipo t = (tipo == Tgdato.CADENA)? Operacion.Tipo.CADENA: Operacion.Tipo.DECIMAL;
            
            return new Par(tagini, new Literal(t, valor, line, colm), line, colm);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.GDATO, Constante.SINTACTICO, "", "global", 
                    "El nombre de los tags debe de conincidir tagini:" + tagini + " tagfin:" + tagfin
                    , Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return Constante.NULO;
        
    }
    
    public enum Tgdato
    {
        CADENA,
        NUMERO
    }
}
