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
import fs.arbol.Instruccion.Instruccion;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Maximo extends FunNativaArreglo implements Expresion, Instruccion {
    
    public Maximo(int line, int colm) {
        super(line, colm);
    }

    /**
     * Realizar la funcion solo para arreglos homogeneas
     * @param ent
     * @return 
     */
    @Override
    public Object evaluar(Entorno ent) 
    {
        return execute(ent);
        
    }
    
    public Object execute_numero(Entorno ent, Tipo tipo)
    {
        int val = Integer.parseInt(String.valueOf(arr.valores.get(0).valor));
        int min;
        for(int i = 1; i < arr.valores.size(); i++)
        {
            min = Integer.parseInt(String.valueOf(arr.valores.get(i).valor));
            if(min > val)
            {
                val = min;
            }
        }
        return new Literal(tipo, val, line, colm);
    }
    
    public Object execute_cadena(Entorno ent, Tipo tipo)
    {
        String val = String.valueOf(arr.valores.get(0).valor);
        String min;
        for(int i = 1; i < arr.valores.size(); i++)
        {
            min = String.valueOf(arr.valores.get(i).valor);
            if(min.compareToIgnoreCase(val) > 0)
            {
                val = min;
            }
        }
        return new Literal(tipo, val, line, colm);
    }
    
    public Object execute_boolean(Entorno ent, Tipo tipo)
    {
        int val = Operacion.getBooleano(String.valueOf(arr.valores.get(0).valor));
        int min;
        for(int i = 1; i < arr.valores.size(); i++)
        {
            min = Operacion.getBooleano(String.valueOf(arr.valores.get(i).valor));
            if(min > val)
            {
                val = min;
            }
        }
        return new Literal(tipo, Operacion.getBooleano(val), line, colm);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        return execute(ent);
    }
    
    
    public Object execute(Entorno ent)
    {
        /*ANTES DE EVALUAR SE LE PASA LOS VALORES DE ARREGLOS*/
        if(arr != null)
        {
            if(arr.tam > 0)
            {
                Tipo aux = arr.valores.get(0).tipo;
                switch(aux)
                {
                    case ENTERO:
                    case DECIMAL:
                        return execute_numero(ent, aux);
                    case CADENA:
                        return execute_cadena(ent, aux);
                    default:
                        return execute_boolean(ent, aux);
                }
            }
            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
        }
        else
        {
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito,
                    "La funcion nativa MAXIMO debe de recibir un arreglo", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
    
}
