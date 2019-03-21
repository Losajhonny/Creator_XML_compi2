/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdato.arbol;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Instruccion.Declaracion.Arreglo;
import fs.arbol.Instruccion.Declaracion.Objeto;
import java.util.LinkedList;

/**
 *
 * @author Jhona
 */
public class AstGdato implements Instrucciong {
    
    public LinkedList<Principal> mains;
    public int line, colm;
    
    public AstGdato(LinkedList<Principal> mains, int line, int colm)
    {
        this.mains = mains;
        this.line = line;
        this.colm = colm;
    }
    
    public AstGdato(int line, int colm)
    {
        this.mains = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }
    
    public AstGdato()
    {
        this.mains = new LinkedList<>();
    }

    @Override
    public String generarCadena() {
        
        String cadena = "";
        
        cadena += "<lista>\n";
        
        for (Principal p : mains) {
            cadena += p.generarCadena();
        }
        
        cadena += "</lista>\n";
        
        return cadena;
    }

    @Override
    public Object generarArreglo() {
        
        LinkedList<Expresion> exps = new LinkedList();
        
        for (Principal p : mains) {
            
            Object aux = p.generarArreglo();
            
            if(aux instanceof Objeto)
            {
                exps.add(new Literal(Operacion.Tipo.OBJETO, aux, line, colm));
            }
            
        }
            
        Arreglo arr = new Arreglo(exps);
        arr.execute(new Entorno(null));
        
        return new Literal(Operacion.Tipo.ARREGLO, arr, line, colm);
    }
    
}
