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
import fs.arbol.Instruccion.Funcion.CallFuncion;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Buscar extends FunNativaArreglo implements Expresion, Instruccion {
    
    public Buscar(String id, int line, int colm) {
        super(id, line, colm);
    }

    @Override
    public Object evaluar(Entorno ent)
    {
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
                /**/
                LinkedList<Literal> valores = arr.valores;

                for (Literal val : valores)
                {
                    LinkedList<Expresion> exps = new LinkedList<>();
                    exps.add(val);

                    CallFuncion cf = new CallFuncion(id, exps, line, colm);
                    Literal res = (Literal) cf.evaluar(ent);

                    if(res.tipo == Tipo.BOOLEANO)
                    {
                        int v = Operacion.getBooleano(String.valueOf(res.valor));
                        if(v == 1) { return val; }
                    }
    //                else
    //                {
    ////                    if(res.tipo != Tipo.ERROR )
    ////                    {
    //                        /*ES UN ERROR REPORTAR Y RETORNAR UN LITERAL ERROR*/
    //                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
    //                                "En la funcion nativa BUSCAR. El valor devuelto por la llamada al metodo \""+id+"\""+
    //                                " no es un BOOLEANO" , Constante.archivo, line, colm);
    //                        otros.Error.agregarError(err);
    ////                    }
    //                    break;
    //                }
                }
                return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
            }
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
                    "La funcion nativa BUSCAR debe de recibir un arreglo", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
}



//var b = [32, 33, 16, 40];
//
//
//funcion probar ( var b )
//{
//	retornar b >= 18;
//}
//
//var c = b.buscar(probar);
//
//imprimir ( c );