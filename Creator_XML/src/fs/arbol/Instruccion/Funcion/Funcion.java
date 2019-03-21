/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Funcion;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Declaracion.Declaracion;
import fs.arbol.Instruccion.Detener;
import fs.arbol.Instruccion.Instruccion;
import fs.arbol.Instruccion.Retornar;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Funcion implements Instruccion {
    
    public String id;
    public LinkedList<Declaracion> parametros;
    public LinkedList<Instruccion> instrucciones;
    public int line, colm;
    
    public Funcion(String id, LinkedList<Declaracion> parametros, LinkedList<Instruccion> instrucciones, int line, int colm)
    {
        this.id = id.toLowerCase();
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.line = line;
        this.colm = colm;
    }
    
    public Funcion(String id, LinkedList<Declaracion> parametros, int line, int colm, boolean isParams)
    {
        this.id = id.toLowerCase();
        this.parametros = parametros;
        this.instrucciones = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }
    
    public Funcion(String id, LinkedList<Instruccion> instrucciones, int line, int colm)
    {
        this.id = id.toLowerCase();
        this.parametros = new LinkedList<>();
        this.instrucciones = instrucciones;
        this.line = line;
        this.colm = colm;
    }
    
    public Funcion(String id, int line, int colm)
    {
        this.id = id.toLowerCase();
        this.parametros = new LinkedList<>();
        this.instrucciones = new LinkedList<>();
        this.line = line;
        this.colm = colm;
    }

    /**
     * Ejecuta las instrucciones de las funciones
     * @param ent           Entorno de las declaraciones
     * @return 
     */
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno local = new Entorno(ent);
        /*DEBO DE EJECUTAR LOS PARAMETROS*/
        for (int i = 0; i < parametros.size(); i++) {
            Declaracion dec = parametros.get(i);
            Expresion exp = (Expresion) dec.exp.evaluar(ent);
            dec.exp = exp;
            dec.ejecutar(local);
        }
        
        /*EJECUTAR LAS INSTRUCCIONES DE LA FUNCION*/
        for (Instruccion inst : instrucciones) {
            Object res = inst.ejecutar(local);
            
            if(res instanceof Retornar) { return res; }
            if(res instanceof Detener) {
                Detener det = (Detener) res;
                otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", local.ambito, 
                            "No se permite las Sentencias DETENER en el ambito actual", Constante.archivo,
                            det.line, det.colm);
                otros.Error.agregarError(err);
            }
        }
        return this;
    }
    
    /**
     * Realiza el proceso de guardarlo en la tabla de simbolos
     * de funciones
     * @param ent       Es el entorno global de funciones
     * @return 
     */
    public Object execute(Entorno ent)
    {
        Simbolo s = ent.getSimbolo_EntActual(id);
        if(s == null)
        {
            Simbolo n = new Simbolo(id, Tipo.FUNCION, this);
            n.no_parametros = parametros.size();
            ent.agregar(id, n);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "La funcion \"" + id + "\" ya existe en el ambito actual", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return null;
    }
    
    
    
    
//    Entorno local = new Entorno(ent);
//        local.ambito = id;
//        
//        for (Declaracion param : parametros) {
//            param.ejecutar(local);
//        }
//        
//        for (Instruccion inst : instrucciones) {
//            Object obj = inst.ejecutar(local);
//            
//            if(obj instanceof Detener)
//            {
//                Detener tmp = (Detener)obj;
//                String msg = "No se permite las Sentencias DETENER en el ambito actual";
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", local.ambito, msg, Constante.archivo,
//                            tmp.line, tmp.colm);
//                    otros.Error.agregarError(err);
//            }
//            else if (obj instanceof Retornar)
//            {
//                return obj;
//            }
//        }
//        
//        return this;
        
        
    
//    /*BUSCA SI EXISTE*/
//        Simbolo s = ent.getSimbolo_EntActual(id);
//        
//        if (s == null)
//        {
//            s = new Simbolo(id, Tipo.FUNCION, this);
//            s.no_parametros = parametros.size();
//            ent.agregar(id, s);
//        }
//        else
//        {
//            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
//                    "La funcion \"" + id + "\" ya existe en el ambito global", Constante.archivo, line, colm);
//            otros.Error.agregarError(err);
//        }
//        
//        return null;
        
}
