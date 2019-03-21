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
public class Map extends FunNativaArreglo implements Expresion, Instruccion {
    
    public Map(String id, int line, int colm) {
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
            /**/
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

                    if(res.tipo != Tipo.ARREGLO)
                    {
                        if(!(res.tipo == Tipo.UNDEFINED || res.tipo == Tipo.ERROR))
                        {
                            nuevo.add(res);
                        }
                    }
    //                else
    //                {
    //                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
    //                            "La funcion nativa MAP no recibe un arreglo", Constante.archivo, line, colm);
    //                    otros.Error.agregarError(err);
    //                    return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    //                }
                }

                Arreglo narr = new Arreglo(nuevo);
                narr.execute(ent);
                //narr.ejecutar(ent);
                return new Literal(Operacion.Tipo.ARREGLO, narr, line, colm);
            }
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
                    "La funcion nativa MAP debe de recibir un arreglo", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
}


//var ar = [1, 2, 3];
//
//funcion sumar ( var item )
//{
//	retornar iem * 10;
//}
//
//var ar1 = ar.map(sumar);
//
//imprimir ( ar1[0] );
//imprimir ( ar1[1] );
//imprimir ( ar1[2] );
//imprimir ( ar1[3] );
//imprimir ( ar1[4] );
//imprimir ( ar1[5] );
//imprimir ( ar1[6] );
//imprimir ( ar1[7] );