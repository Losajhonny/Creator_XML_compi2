/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Seleccion;

import entorno.Entorno;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Logica;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Expresion.Operaciones.Relacional;
import fs.arbol.Instruccion.Detener;
import fs.arbol.Instruccion.Instruccion;
import fs.arbol.Instruccion.Retornar;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Si implements Instruccion {
    
    public SSi ssi;
    public LinkedList<SSi> sinosi;
    public Instruccion sino;
    
    public Si (SSi ssi, LinkedList<SSi> sinosi, Instruccion sino)
    {
        this.ssi = ssi;
        this.sinosi = sinosi;
        this.sino = sino;
    }
    
    public Si (SSi ssi, LinkedList<SSi> sinosi)
    {
        this.ssi = ssi;
        this.sinosi = sinosi;
        this.sino = null;
    }
    
    public Si (SSi ssi, Instruccion sino)
    {
        this.ssi = ssi;
        this.sinosi = new LinkedList<>();
        this.sino = sino;
    }
    
    public Si (SSi ssi)
    {
        this.ssi = ssi;
        this.sinosi = new LinkedList<>();
        this.sino = null;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        
        Object obj = executeSi(ent, ssi);
        if(obj instanceof Detener || obj instanceof Retornar) { return obj; }
        boolean nopaso = !((boolean) obj);  //regresa si ejecuto el if      //y lo niego para ejutar los demas
        
        for (int i = 0; (i < sinosi.size() && nopaso); i++) {
            SSi sSi = sinosi.get(i);
            
            obj = executeSi(ent, sSi);
            if(obj instanceof Detener || obj instanceof Retornar) { return obj; }
            nopaso = !((boolean) obj);
        }
        
        if (sino != null && nopaso)
        {
            obj = sino.ejecutar(ent);
            if(obj instanceof Detener || obj instanceof Retornar) { return obj; }
        }
        
        return this;
    }
    
    public Object executeSi(Entorno ent, SSi ssi)
    {
        Entorno local = new Entorno(ent);
        
        if( ssi.condicion instanceof Logica || ssi.condicion instanceof Relacional /*res.tipo == Tipo.BOOLEANO /*|| res.tipo == Tipo.ERROR*/)
        {
            Literal res = (Literal) ssi.condicion.evaluar(local);
            int v = (res.tipo == Tipo.BOOLEANO)? Operacion.getBooleano(String.valueOf(res.valor)): 0;
            boolean paso = (v == 1);
            
            if(paso)
            {
                for (Instruccion inst : ssi.instrucciones) {
                    Object obj = inst.ejecutar(local);
                    if(obj instanceof Detener || obj instanceof Retornar) { return obj; }
                }
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            String msg = "La expresion debe ser LOGICA O RELACIONAL";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, ssi.line, ssi.colm);
            otros.Error.agregarError(err);
        }
        return false;
    }
}
