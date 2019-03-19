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
import fs.arbol.Instruccion.Funcion.CallFuncion;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;
import otros.Constante;


/**
 *
 * @author Jhona
 */
public class Alguno extends FunNativaArreglo implements Expresion, Instruccion {
    
    public Alguno(String id, int line, int colm) {
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
                Literal tmp = new Literal(Operacion.Tipo.BOOLEANO, Operacion.getBooleano(1), line, colm);

                for (Literal val : valores)
                {
                    LinkedList<Expresion> exps = new LinkedList<>();
                    exps.add(val);

                    CallFuncion cf = new CallFuncion(id, exps, line, colm);
                    Literal res = (Literal) cf.evaluar(ent);

                    if(res.tipo == Operacion.Tipo.BOOLEANO)
                    {
                        int v = Operacion.getBooleano(String.valueOf(res.valor));
                        if(v == 1) { return res; } else { tmp = res; }
                    }
                }
                return tmp;
            }
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
                    "La funcion nativa TODOS debe de recibir un arreglo", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
    
}
