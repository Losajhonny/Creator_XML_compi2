/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Funcion;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Instruccion;
import fs.arbol.Instruccion.Retornar;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class CallFuncion implements Expresion, Instruccion {
    
    public String id;
    public LinkedList<Expresion> valores;
    public int line, colm;
    
    public CallFuncion(String id, LinkedList<Expresion> valores, int line, int colm) {
        this.line = line;
        this.colm = colm;
        this.id = id.toLowerCase();
        this.valores = valores;
    }
    
    public CallFuncion(String id, int line, int colm) {
        this.line = line;
        this.colm = colm;
        this.id = id.toLowerCase();
        this.valores = new LinkedList<>();
    }

    @Override
    public Object evaluar(Entorno ent) {
        return execute(ent);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        return execute(ent);
    }
    
    public Object execute(Entorno ent){
        /*BUSCAR LA FUNCION*/
        Simbolo s = Constante.global_fun.getSimbolo_EntActual(id);
        
        if(s != null)
        {
            if(s.no_parametros == valores.size())
            {
                Funcion fun = (Funcion) s.valor;
                
                for (int i = 0; i < s.no_parametros; i++) {
                    fun.parametros.get(i).exp = valores.get(i);
                }
                
                /*Si sirve*/
//                Funcion fun = (Funcion) s.valor;
//                /*PASO DE PARAMETROS*/
//                for (int i = 0; i < s.no_parametros; i++) {
//                    Literal res = (Literal) valores.get(i).evaluar(ent);
//                    Literal aux = new Literal(res.tipo, res.valor, res.line, res.colm);
//                    
//                    fun.parametros.get(i).exp = aux;
//                    
//                    if(res.tipo == Tipo.UNDEFINED || res.tipo == Tipo.ERROR)
//                    {
//                        return new Literal(Tipo.UNDEFINED, Constante.NULO, line, colm);
//                    }
//                }
                
                Object res = fun.ejecutar(ent);
                if(res instanceof Retornar) { Retornar ret = (Retornar)res; if(ret.literal != null) { return ret.literal; } }
            }
            else
            {
                /*EL NUMERO DE PARAMETROS NO CONCIDE CON LOS VALORES*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "El numero de parametros no coincide con el de la funcion \"" + id + "\"", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            /*LA FUNCION NO EXISTE*/
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "La funcion \"" + id + "\" no existe en el ambito actual", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return new Literal(Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
//    /*DEBO DE BUSCAR LA FUNCION EN EL AMBITO GLOBAL*/
//        Simbolo s = AstFs.global_fun.getSimbolo_EntActual(id);
//        
//        if(s != null)
//        {
//            if(s.no_parametros == valores.size())
//            {
//                Funcion tmp = (Funcion) s.valor;
//                
//                /*PASO DE PARAMETROS*/
//                for(int i = 0; i < valores.size(); i++)
//                {
//                    Literal res = (Literal) valores.get(i).evaluar(ent);
//                    Declaracion param = tmp.parametros.get(i);
//                    param.exp = res;
//                }
//                
//                Object obj = tmp.ejecutar(ent);
//                if(obj instanceof Retornar)
//                {
//                    Retornar ret = (Retornar) obj;
//                    if(ret.literal != null)
//                    {
//                        return ret.literal;
//                    }
//                }
//            }
//            else
//            {
//                /*EL NUMERO DE PARAMETROS NO CONCIDE CON LOS VALORES*/
//                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
//                    "El numero de parametros no coincide con el de la funcion", Constante.archivo, line, colm);
//                otros.Error.agregarError(err);
//            }
//        }
//        else
//        {
//            /*LA FUNCION NO EXISTE*/
//            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
//                    "La funcion no existe en el ambito actual", Constante.archivo, line, colm);
//            otros.Error.agregarError(err);
//        }
//        
//        /*RETORNAR UN LITERAL DE UN ARREGLO VACIO*/
//        /*RETORNAR UN LITERAL DE UN ARREGLO VACIO*/
//        Arreglo vacio = new Arreglo();
//        vacio.ejecutar(ent);
//        Literal res = new Literal(Tipo.ARREGLO, vacio, line, colm);
//        return res;
}
