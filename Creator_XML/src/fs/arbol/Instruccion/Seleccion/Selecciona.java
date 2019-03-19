/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Seleccion;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Operacion.Operador;
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
public class Selecciona implements Instruccion{
    
    public Expresion exp;
    public LinkedList<Caso> casos;
    public Caso defecto;
    public int line, colm;
    
    public Selecciona(Expresion exp, LinkedList<Caso> casos, Caso defecto, int line, int colm)
    {
        this.exp = exp;
        this.casos = casos;
        this.defecto = defecto;
        this.line = line;
        this.colm = colm;
    }
    
    public Selecciona(Expresion exp, LinkedList<Caso> casos, int line, int colm)
    {
        this.exp = exp;
        this.casos = casos;
        this.defecto = null;
        this.line = line;
        this.colm = colm;
    }
    
    public Selecciona(Expresion exp, int line, int colm)
    {
        this.exp = exp;
        this.casos = new LinkedList<>();
        this.defecto = null;
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object ejecutar(Entorno ent) 
    {
        Entorno local = new Entorno(ent);
        /*OBTENER EL VALOR PARA EVALUAR*/
        Literal res = (Literal) exp.evaluar(ent);
        boolean paso = false;
        
        for (int i = 0; i < casos.size(); i++) {
            /*OBTENGO EL CASO A EVALUAR*/
            Caso caso = casos.get(i);
            
            if(!paso)
            {
                /*OBTENER EL VALOR DEL CASO A COMPARAR*/
                if(caso.exp instanceof Literal)
                {
                    Literal resCaso = (Literal) caso.exp;
                
                    //Literal resCaso = (Literal) caso.exp.evaluar(ent);
                    
                    if (((res.tipo == Tipo.ENTERO || res.tipo == Tipo.DECIMAL) && (resCaso.tipo == Tipo.ENTERO || resCaso.tipo == Tipo.DECIMAL))
                            || (res.tipo == resCaso.tipo))
                    {
                        Relacional rel = new Relacional(res, resCaso, Operador.IGUALIGUAL, line, colm);
                        Literal cond = (Literal) rel.evaluar(ent);

                        if(cond.tipo == Tipo.BOOLEANO)
                        {
                            int v = Operacion.getBooleano(String.valueOf(cond.valor));
                            paso = (v == 1);
                        }
                        else
                        {
                            /*ERROR QUE LA CONDICION NO ES BOOLEANO*/
                            String msg = "Ocurrio un error al comparar la expresion del Caso con la expresion del Selecciona. Tipo devuelto " + Tipo.UNDEFINED;
                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", local.ambito, msg, Constante.archivo, caso.line, caso.colm);
                            otros.Error.agregarError(err);
                        }
                    }
                    else
                    {
                        Tipo t1 = (res.tipo == Tipo.ERROR)? Tipo.UNDEFINED: res.tipo;
                        Tipo t2 = (resCaso.tipo == Tipo.ERROR)? Tipo.UNDEFINED: resCaso.tipo;
                        
                        /*LA COMPARACION DEBE DE SER DEL MISMO TIPO*/
                        String msg = "El valor del Selecciona y Caso deben de ser el mismo tipo. Selecciona " + t1 + " Caso "+t2;
                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", local.ambito, msg, Constante.archivo, caso.line, caso.colm);
                        otros.Error.agregarError(err);
                    }
                }
                else
                {
                    //ERROR LA EXP EN EL CASO DE SER UN VALOR Y NO UNA EXPRESION
                    String msg = "El valor de un Caso debe ser un VALOR y no una EXPRESION";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", local.ambito, msg, Constante.archivo, caso.line, caso.colm);
                    otros.Error.agregarError(err);
                }
            }
            
            if(paso)
            {
                Object obj = caso.ejecutar(local);
                if(obj instanceof Detener) { return this; }
                if(obj instanceof Retornar) { return obj; }
            }
        }
        
        if(defecto != null)
        {
            Object obj = defecto.ejecutar(local);
            if(obj instanceof Detener) { return this; }
            if(obj instanceof Retornar) { return obj; }
        }
        
        return this;
    }
    
}
