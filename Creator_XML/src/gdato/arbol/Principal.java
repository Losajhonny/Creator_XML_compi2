/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdato.arbol;

import entorno.Entorno;
import fs.arbol.Instruccion.Declaracion.Objeto;
import fs.arbol.Instruccion.Declaracion.Par;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Principal implements Instrucciong {
    
    public LinkedList<Valor> valores;
    public int line, colm;
    
    public Principal(LinkedList<Valor> valores, int line, int colm)
    {
        this.valores = valores;
        this.line = line;
        this.colm = colm;
    }
    
    public Principal(int line, int colm)
    {
        this.valores = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }
    
    public Principal()
    {
        this.valores = new LinkedList<>();
    }

    @Override
    public String generarCadena() {
        
        String cadena = "";
        
        cadena += "\t<principal>\n";
        
        for (Valor val : valores) {
            cadena += val.generarCadena();
        }
        
        cadena += "\t</principal>\n";
        
        return cadena;
        
    }

    @Override
    public Object generarArreglo() {
        
        LinkedList<Par> pares = new LinkedList();
        
        for (Valor val : valores) {
            Object aux = val.generarArreglo();
            
            if(aux instanceof Par)
            {
                pares.add((Par)aux);
            }
        }
        
        if(pares.size() > 0)
        {
            Objeto obj = new Objeto(pares);
            Entorno ent = new Entorno(null);
            ent.ambito = "global";
            obj.ejecutar(ent);
            
            return obj;
        }
        return Constante.NULO;
    }
    
}
