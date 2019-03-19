/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionArreglo;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Declaracion.Arreglo;
import fs.arbol.Instruccion.Funcion.CallFuncion;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Filtrar extends FunNativaArreglo implements Expresion, Instruccion {
    
    public Filtrar(String id, int line, int colm) {
        super(id, line, colm);
    }

    @Override
    public Object evaluar(Entorno ent) {
        return execute(ent);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        return execute(ent);
    }
    
    public Object execute(Entorno ent)
    {
        if(arr != null)
        {
            if(arr.tam > 0)
            {
                LinkedList<Literal> valores = arr.valores;
                LinkedList<Expresion> nuevo = new LinkedList<>();

                for (Literal val : valores)
                {
                    LinkedList<Expresion> exps = new LinkedList<>();
                    exps.add(val);

                    CallFuncion cf = new CallFuncion(id, exps, line, colm);
                    Literal res = (Literal) cf.evaluar(ent);

                    if(res.tipo == Operacion.Tipo.BOOLEANO)
                    {
                        int v = Operacion.getBooleano(String.valueOf(res.valor));
                        if(v == 1) { nuevo.add(val); }
                    }
                    else
                    {
                        nuevo.add(val);
                    }
    //                else
    //                {
    ////                    if(res.tipo != Tipo.ERROR )
    ////                    {
    //                        /*ES UN ERROR REPORTAR Y RETORNAR UN LITERAL ERROR*/
    //                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
    //                                "En la funcion nativa FILTRAR. El valor devuelto por la llamada al metodo \""+id+"\""+
    //                                " no es un BOOLEANO" , Constante.archivo, line, colm);
    //                        otros.Error.agregarError(err);
    //                        //return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    ////                    }
    //                }
                }

                Arreglo narr = new Arreglo(nuevo);
                narr.ejecutar(ent);
                return new Literal(Tipo.ARREGLO, narr, line, colm);
            }
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
                    "La funcion nativa FILTRAR debe de recibir un arreglo", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
    
}
//
//var a = [ 32, 33, 16, 40 ];
//
//funcion check(var age)
//{
//	retornar age >= 18;
//}
//
//var b = a.filtrar(check);
//
//imprimir ( b[0] );
//imprimir ( b[1] );
//imprimir ( b[2] );
//imprimir ( b[3] );
//imprimir ( b[4] );
//imprimir ( b[5] );
