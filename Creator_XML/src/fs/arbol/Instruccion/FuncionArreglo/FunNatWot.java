/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionArreglo;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Operacion.Operador;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Declaracion.Arreglo;
import fs.arbol.Instruccion.Declaracion.Objeto;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;
import otros.Constante;

/**
 * Esta clase almacena la funcin nativa sin retorno
 * por eso el nombre
 * Fun          -funcion
 * Nat          -nativa
 * Wot          -With out return
 * @author Jhona
 */
public class FunNatWot implements Instruccion{
    
    public String id1, id2;
    public Operador op;
    public int line, colm;
    
    public FunNatWot(String id1, String id2, Operador op, int line, int colm)
    {
        this.id1 = id1.toLowerCase();
        this.id2 = id2.toLowerCase();
        this.op = op;
        this.line = line;
        this.colm = colm;
    }
    
    public FunNatWot(String id1, Operador op, int line, int colm)
    {
        this.id1 = id1.toLowerCase();
        this.id2 = null;
        this.op = op;
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Simbolo s = ent.getSimbolo_Ent(id1);
        
        if(s != null)
        {
            if(id2 != null)
            {
                /*ID.ID*/
                if(s.tipo == Tipo.OBJETO)
                {
                    Objeto tmp = (Objeto) s.valor;
                    s = tmp.entorno.getSimbolo_Ent(id2);

                    if(s != null)
                    {
                        /*ID2*/
                        return execute(ent, s, false);
                    }
                    else
                    {
                        /*ID2 NO EXISTE EN ID1*/
                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                                "El atributo \""+id2+"\" no existe en el OBJETO \""+id1+"\"", Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
                    }
                }
                else
                {
                    /*ID1 NO ES UN OBJETO*/
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "La variable \""+id1+"\" no es un OBJETO", Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
            }
            else
            {
                /*ID1*/
                return execute(ent, s, true);
            }
        }
        else
        {
            /*ID1 NO EXISTE*/
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "La variable \""+id1+"\" no existe", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return null;
    }
    
    /**
     * Valida si el simbolo es un arreglo ok para realizar la operacion
     * @param ent
     * @param s
     * @param isId1
     * @return 
     */
    public Object execute(Entorno ent, Simbolo s, boolean isId1)
    {
        if(s.tipo == Tipo.ARREGLO)
        {
            Arreglo arr = (Arreglo) s.valor;
            
            if(arr.tam != 0)
            {
                if(!arr.hayUndefined && arr.tipo == Tipo.HOMOGENEO)
                {
                    return execute_valores(ent, arr);
                }
                else if (arr.hayUndefined)
                {
                    /*NO SE PUEDE OPERAR DEBIDO HAY QUE HAY POR LO MENOS 1 VALOR UNEDINED*/
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "No se puede ordenar debido que hay valor(es) " + Tipo.UNDEFINED, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
                else
                {
                    /*EL ARREGLO NO SE PUEDE ORDER POR SER DE TIPO HETEROGENEO*/
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "El arreglo no se puede ordenar por ser de tipo "+Tipo.HETEROGENEO, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
            }
            /*SI ES VACIO NO HACER NADA*/
        }
        else
        {
            if(isId1)
            {
                /*ID1 NO ES UN ARREGLO*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "La variable \""+id1+"\" no es un arreglo", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
            else
            {
                /*ID2 NO ES UN ARREGLO*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "El atributo \""+id2+"\" no es un arreglo", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        return null;
    }
    
    /**
     * Realiza la operacion de ASCDESC
     * @param ent
     * @param arr
     * @return 
     */
    public Object execute_valores(Entorno ent, Arreglo arr)
    {
        LinkedList<Literal> tmp = arr.valores;
        
        /*POR LO MENOS HAY UN LITERAL*/
        Tipo aux = tmp.get(0).tipo;
        
        if(aux == Tipo.ENTERO || aux == Tipo.DECIMAL || aux == Tipo.CADENA)
        {
            switch(op)
            {
                case ASCENDENTE:
                    executeAscendente(tmp, arr);
                    break;
                case DESCENDENTE:
                    executeDescendente(tmp, arr);
                    break;
                default:
                    executeInvertir(tmp, arr);
                    break;
            }
        }
        else
        {
            /*EL ARREGLO DEBE SER DE TIPO NUMERO O CADENA*/
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "El arreglo debe ser de tipo NUMERO o CADENA", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        
        return null;
    }
    
    public void executeAscendente(LinkedList<Literal> tmp, Arreglo arr)
    {
        Tipo auxj, auxj1;
        String valj, valj1;
        double vj, vj1;
        
        for (int i = 0; i < arr.tam-1; i++) {
            for (int j = 0; j < arr.tam-1; j++) {
                auxj = tmp.get(j).tipo;
                auxj1 = tmp.get(j+1).tipo;
                
                valj = String.valueOf(tmp.get(j).valor);
                valj1 = String.valueOf(tmp.get(j+1).valor);
                    
                vj = (auxj == Tipo.ENTERO || auxj == Tipo.DECIMAL)? Double.parseDouble(valj): (double) Operacion.getAsciiWord(valj);
                vj1 = (auxj1 == Tipo.ENTERO || auxj1 == Tipo.DECIMAL)? Double.parseDouble(valj1): (double) Operacion.getAsciiWord(valj1);
                
                if(auxj == Tipo.CADENA)
                {
                    if(valj.compareToIgnoreCase(valj1) < 0)
                    {
                        tmp.get(j+1).valor = valj;
                        tmp.get(j).valor = valj1;
                    }
                }
                else
                {
                    if(vj < vj1)
                    {
                        tmp.get(j+1).valor = valj;
                        tmp.get(j).valor = valj1;
                    }
                }
            }
        }
    }
    
    public void executeDescendente(LinkedList<Literal> tmp, Arreglo arr)
    {
        Tipo auxj, auxj1;
        String valj, valj1;
        double vj, vj1;
        
        for (int i = 0; i < arr.tam-1; i++) {
            for (int j = 0; j < arr.tam-1; j++) {
                auxj = tmp.get(j).tipo;
                auxj1 = tmp.get(j+1).tipo;
                
                valj = String.valueOf(tmp.get(j).valor);
                valj1 = String.valueOf(tmp.get(j+1).valor);
                    
                vj = (auxj == Tipo.ENTERO || auxj == Tipo.DECIMAL)? Double.parseDouble(valj): (double) Operacion.getAsciiWord(valj);
                vj1 = (auxj1 == Tipo.ENTERO || auxj1 == Tipo.DECIMAL)? Double.parseDouble(valj1): (double) Operacion.getAsciiWord(valj1);
                
                if(auxj == Tipo.CADENA)
                {
                    if(valj.compareToIgnoreCase(valj1) > 0)
                    {
                        tmp.get(j+1).valor = valj;
                        tmp.get(j).valor = valj1;
                    }
                }
                else
                {
                    if(vj > vj1)
                    {
                        tmp.get(j+1).valor = valj;
                        tmp.get(j).valor = valj1;
                    }
                }
            }
        }
    }
    
    public void executeInvertir(LinkedList<Literal> tmp, Arreglo arr)
    {
        int posini = 0, posfin = tmp.size()-1;
        
        String valini, valfin;
        
        while(posini < posfin)
        {
            valini = String.valueOf(tmp.get(posini).valor);
            valfin = String.valueOf(tmp.get(posfin).valor);
            
            tmp.get(posini).valor = valfin;
            tmp.get(posfin).valor = valini;
            posini++;
            posfin--;
        }
    }
}
