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
public class Reduce extends FunNativaArreglo implements Expresion, Instruccion {
    
    public Reduce(String id, int line, int colm) {
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
                Literal acumulador = (Literal) valores.get(0);

                for (int i = 1; i < valores.size(); i++) {
                    LinkedList<Expresion> exps = new LinkedList<>();
                    exps.add(acumulador);
                    exps.add(valores.get(i));

                    CallFuncion cf = new CallFuncion(id, exps, line, colm);
                    Literal res = (Literal) cf.evaluar(ent);

    //                if(res.tipo == Tipo.ARREGLO || res.tipo == Tipo.UNDEFINED || res.tipo == Tipo.ERROR)
    //                {
    //                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
    //                            "La funcion nativa REDUCE termino con problemas sobre la llamada de la funcion \""+id+"\"", Constante.archivo, line, colm);
    //                    otros.Error.agregarError(err);
    //                    return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    //                }
                    acumulador = res;
                }

                return acumulador;
            }
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
                    "La funcion nativa REDUCE debe de recibir un arreglo", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
    
}
