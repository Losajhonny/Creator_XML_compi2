/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Instruccion.Declaracion.Arreglo;
import fs.arbol.Instruccion.Declaracion.Objeto;
import fs.arbol.Instruccion.FuncionArreglo.FunNativaArreglo;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Variable extends Operacion implements Expresion, Instruccion {
    public String id1;  //variable
    public String id2;  //variable del objeto
    
    public Object componente;

    public Variable(String id1, String id2, Expresion izq, int line, int colm) {
        super(line, colm);
        this.izq = izq;
        this.id1 = id1.toLowerCase();
        this.id2 = id2.toLowerCase();
        //this.nativa_arreglo = null;
        this.componente = null;
    }
    
    public Variable(String id1, Expresion izq, int line, int colm) {
        super(line, colm);
        this.izq = izq;
        this.id1 = id1.toLowerCase();
        this.id2 = null;
        //this.nativa_arreglo = null;
        this.componente = null;
    }
    
    public Variable(String id1, String id2, Expresion izq, Object nativa, int line, int colm) {
        super(line, colm);
        this.izq = izq;
        this.id1 = id1.toLowerCase();
        this.id2 = id2.toLowerCase();
        //this.nativa_arreglo = nativa;
        this.componente = nativa;
    }
    
    public Variable(String id1, Expresion izq, Object nativa, int line, int colm) {
        super(line, colm);
        this.izq = izq;
        this.id1 = id1.toLowerCase();
        this.id2 = null;
        //this.nativa_arreglo = nativa;
        this.componente = nativa;
    }

    @Override
    public Object evaluar(Entorno ent)
    {
        return execute(ent);
    }
    
    public Object execute(Entorno ent)
    {
        /*BUSCAR ID1*/
        Simbolo s = ent.getSimbolo_Ent(id1);
        
        if (s != null)
        {
            if(id2 != null)
            {
                /*BUSCAR ID2*/
                if(s.tipo == Tipo.OBJETO)
                {
                    Objeto obj = (Objeto) s.valor;
                    s = obj.entorno.getSimbolo_EntActual(id2);
                    
                    if (s != null)
                    {
                        /*ID1.ID2 | ID1.ID2[EXP]*/
                        if (/*nativa_arreglo == null*/ componente == null)
                        {
                            return execute_variable(ent, s, false);
                        }
                        else
                        {
                            
                                /*EXECUTE NATIVA*/
                                return execute_nativas(ent, s, false);
                        }
                    }
                    else
                    {
                        String msg = "No se pudo encontrar el atributo \"" + id2 + "\" en el objeto \"" + id1 + "\"";
                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                    }
                }
                else
                {
                    String msg = "La variable \"" + id1 + "\" no es un objeto para poder obtener el valor de los atributos";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                }
            }
            else
            {
                /*ID1 | ID1[EXP]*/
                if(/*nativa_arreglo == null*/componente == null)
                {
                    return execute_variable(ent, s, true);
                }
                else
                {
                    return execute_nativas(ent, s, true);
                }
            }
        }
        else
        {
            String msg = "No se pudo encontrar la variable \"" + id1 + "\"";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
    
    public Object execute_variable(Entorno ent, Simbolo s, boolean isId1)
    {
                /*ID1 | ID1[EXP]*/
                if(izq != null)
                {
                    /*ID1[EXP]*/
                    if(s.tipo == Tipo.ARREGLO)
                    {
                        Arreglo arr = (Arreglo) s.valor;
                        Literal index = (Literal) izq.evaluar(ent);

                        if (index.tipo == Tipo.ENTERO || index.tipo == Tipo.DECIMAL)
                        {
                            int indice = Integer.parseInt(String.valueOf(index.valor));
                            
                            if(indice < arr.tam)
                            {
                                Literal l = arr.valores.get(indice);
                                if(l.valor instanceof Arreglo || l.valor instanceof Objeto)
                                {
                                    return new Literal(s.tipo, l.valor, line, colm);
                                }
                                else
                                {
                                    return l;
                                }
                            }
                            else
                            {
                                if(isId1)
                                {
                                    String msg = "El indice esta fuera de rango del arreglo \"" + id1 + "\"";
                                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                                    otros.Error.agregarError(err);
                                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                                }
                                else
                                {
                                    String msg = "El indice esta fuera de rango del arreglo \"" + id2 + "\"";
                                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                                    otros.Error.agregarError(err);
                                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                                }
                            }
                        }
                        else
                        {
                            String msg = "El indice debe de ser de tipo \"NUMERO\"";
                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                            otros.Error.agregarError(err);
                            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                        }
                    }
                    else
                    {
                        if(isId1)
                        {
                            String msg = "La variable \"" + id1 + "\" no es un arreglo para poder obtener el valor";
                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                            otros.Error.agregarError(err);
                            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                        }
                        else
                        {
                            String msg = "El atributo \"" + id2 + "\" no es un arreglo para poder obtener el valor";
                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                            otros.Error.agregarError(err);
                            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                        }
                    }
                }
                else
                {
                    /*ID1*/
                    if(s.valor instanceof Arreglo || s.valor instanceof Objeto)
                    {
                        return new Literal(s.tipo, s.valor, line, colm);
                    }
                    else
                    {
                        return s.valor;
                    }
                }
    }
    
    public Object execute_nativas(Entorno ent, Simbolo s, boolean isId1)
    {
        /*AQUI TODAS LAS EXPRESIONES SON NATIVAS POR LO TANTO TIENEN HEREDADO FUN NATIVA ARREGLO*/
        
        if (s.tipo == Tipo.ARREGLO)
        {
            Arreglo arr = (Arreglo) s.valor;
            
            if (!arr.hayUndefined)
            {
                if (arr.tipo == Tipo.HOMOGENEO)
                {
                    Literal arreglo = new Literal(Tipo.ARREGLO, arr, line, colm);
                    return RealizarFuncion(ent, arreglo, 0);
                }
                else
                {
                    /*EL ARREGLO DEBE DE SER TIPO HOMOGENEO*/
                    if(isId1)
                    {
                        String msg = "El arreglo \"" + id1 + "\" no es de tipo " + Tipo.HOMOGENEO;
                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                    }
                    else
                    {
                        String msg = "El arreglo \"" + id2 + "\" no es de tipo " + Tipo.HOMOGENEO;
                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                    }
                }
            }
            else
            {
                /*HAY ELEMENTOS UNDEFINED EN EL ARREGLO*/
                if(isId1)
                {
                    String msg = "Hay elementos sin definir en la variable \"" + id1 + "\"";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                }
                else
                {
                    String msg = "Hay elementos sin definir en el atributo \"" + id2 + "\"";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
                }
            }
        }
        else
        {
            /*LA VARIABLE O ATRIBUTO NO ES UN ARREGLO PARA REALIZAR LAS FUNCIONES NATIVAS DE ARREGLOS*/
            if(isId1)
            {
                String msg = "No se pudo encontrar la variable \"" + id1 + "\"";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
            }
            else
            {
                String msg = "No se pudo encontrar la el atributo \"" + id2 + "\" en el objeto \""+ id1 + "\"";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
            }
        }
    }
    
    public Object RealizarFuncion(Entorno ent, Literal arreglo, int pos)
    {
        LinkedList<Expresion> na = (LinkedList) componente;
        
        if(pos < na.size())
        {
            FunNativaArreglo fna = (FunNativaArreglo) na.get(pos);
            fna.arr = (arreglo.valor instanceof Arreglo)? (Arreglo) arreglo.valor: null;
            
            Object obj = na.get(pos).evaluar(ent);
            return RealizarFuncion(ent, (Literal) obj, pos+1);
        }
        return arreglo;
    }
    
//    if(izq != null)
//                {
//                    /*ID1[EXP]*/
//                    if(s.tipo == Tipo.ARREGLO)
//                    {
//                        Arreglo arr = (Arreglo) s.valor;
//                        Operacion optmp = (Operacion) izq;  optmp.ambito = ambito;
//                        Literal index = (Literal) izq.evaluar(ent);
//
//                        if (index.tipo == Tipo.ENTERO || index.tipo == Tipo.DECIMAL)
//                        {
//                            int indice = Integer.parseInt(String.valueOf(index.valor));
//                            
//                            if(indice < arr.tam)
//                            {
//                                Literal l = arr.valores.get(indice);
//                                if(l.valor instanceof Arreglo || l.valor instanceof Objeto)
//                                {
//                                    return new Literal(s.tipo, l.valor, line, colm);
//                                }
//                                else
//                                {
//                                    return l;
//                                }
//                            }
//                            else
//                            {
//                                String msg = "El indice esta fuera de rango del arreglo \"" + id1 + "\"";
//                                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                                otros.Error.agregarError(err);
//                                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                            }
//                        }
//                        else
//                        {
//                            String msg = "El indice debe de ser de tipo \"NUMERO\"";
//                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                            otros.Error.agregarError(err);
//                            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                        }
//                    }
//                    else
//                    {
//                        String msg = "La variable \"" + id1 + "\" no es un arreglo para poder obtener el valor";
//                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                        otros.Error.agregarError(err);
//                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                    }
//                }
//                else
//                {
//                    /*ID1*/
//                    if(s.valor instanceof Arreglo || s.valor instanceof Objeto)
//                    {
//                        return new Literal(s.tipo, s.valor, line, colm);
//                    }
//                    else
//                    {
//                        return s.valor;
//                    }
//                }
    
    
    
//        if(id2 != null)
//        {   //es una variable de un objeto
//            return var_objeto_(ent);
//        }
//        else
//        {   //es una variable normal
//            Simbolo s = ent.getSimbolo_Ent(id1);
//            if(s != null)
//            {
//                return var_normal(ent, s, true);
//            }
//            else
//            {
//                String msg = "No se pudo encontrar la variable \"" + id1 + "\"";
//                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                otros.Error.agregarError(err);
//                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//            }
//        }
    
//    /**
//     * El paramtro s ya debe ser validado que sea diferente de nulo
//     * @param ent
//     * @param s
//     * @param isId1
//     * @return 
//     */
//    public Object var_normal(Entorno ent, Simbolo s, boolean isId1)
//    {
//        if(izq != null)
//        {   //id[ izq ]
//            if (s.tipo_nodo == Tipo.DECLARACION && s.tipo == Tipo.ARREGLO)
//            {   //es un arreglo
//                Literal nizq = (Literal) izq.evaluar(ent);
//                /*validar el tipo que debe ser numerico*/
//                if (nizq.tipo == Tipo.ENTERO || nizq.tipo == Tipo.DECIMAL)
//                {
//                    int indice = Integer.parseInt(String.valueOf(nizq.valor));
//                    if(indice < s.tam_arreglo)
//                    {
//                        Literal tmp = (Literal) s.valor;
//
//                        Arreglo arr = (Arreglo) tmp.valor;
//                        return arr.valores.get(indice);
//                    }
//                    else
//                    {
//                        String msg = "El indice:" + String.valueOf(indice) + " debe ser menor que el tamaño del arreglo :" + String.valueOf(s.tam_arreglo);
//                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                        otros.Error.agregarError(err);
//                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                    }
//                }
//                else
//                {
//                    String msg = "El indice debe de ser de tipo: \"NUMERO\"";
//                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                }
//            }
//            else if (isId1)
//            {
//                String msg = "La variable \"" + id1 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                        + " y de Tipo: \"" + s.tipo + "\"";
//                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                otros.Error.agregarError(err);
//                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//            }
//            else
//            {
//                String msg = "La variable \"" + id2 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                        + " y de Tipo: \"" + s.tipo + "\"";
//                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                otros.Error.agregarError(err);
//                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//            }
//        }
//        else
//        {   //id
//            //solo es una variable          ejemplo: var b = a;     solo retorno el valor de a
//            return s.valor;
//        }
//    }
//    
//    public Object var_objeto_(Entorno ent)
//    {
//            /*encontrar la primera variable*/
//            Simbolo s = ent.getSimbolo_Ent(id1);
//            if (s != null)
//            {   //id.id
//                if(s.tipo_nodo == Tipo.DECLARACION && s.tipo == Tipo.OBJETO)
//                {
//                    //ahora debo de encontrar el atributo del objeto
//                    Literal tmp = (Literal) s.valor;
//                    Objeto obj = (Objeto) tmp.valor;
//                    s = obj.entorno.getSimbolo_EntActual(id2);
//                    
//                    if(s != null)
//                    {
//                        return var_normal(ent, s, false);
//                    }
//                    else
//                    {
//                        String msg = "No se pudo encontrar el atributo \"" + id2 + "\" en el objeto \"" + id1 + "\"";
//                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                        otros.Error.agregarError(err);
//                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                    }
//                }
//                else
//                {
//                    String msg = "La variable \"" + id1 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                            + " y de Tipo: \"" + s.tipo + "\"";
//                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                }
//            }
//            else
//            {
//                String msg = "No se pudo encontrar la variable \"" + id1 + "\"";
//                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                otros.Error.agregarError(err);
//                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//            }
//    }
//    
//    public Object var_objeto(Entorno ent)
//    {
//            /*encontrar la primera variable*/
//            Simbolo s = ent.getSimbolo_Ent(id1);
//            
//            if (s != null)
//            {
//                if(s.tipo_nodo == Tipo.DECLARACION && s.tipo == Tipo.OBJETO)
//                {
//                    //ahora debo de encontrar el atributo del objeto
//                    Objeto obj = (Objeto) s.valor;
//                    
//                    s = obj.entorno.getSimbolo_EntActual(id2);
//                    
//                    if(izq != null && s != null)
//                    {   //es un arreglo
//                        
//                            if (s.tipo_nodo == Tipo.DECLARACION && s.tipo == Tipo.ARREGLO)
//                            {
//                                Literal nizq = (Literal) izq.evaluar(ent);
//                                
//                                /*validar el tipo que debe ser numerico*/
//                                if (nizq.tipo == Tipo.ENTERO || nizq.tipo == Tipo.DECIMAL)
//                                {
//                                    /*obtener el indice*/
//                                    int indice = Integer.parseInt(String.valueOf(nizq.valor));
//                                    if(indice < s.tam_arreglo)
//                                    {
//                                        Arreglo arr = (Arreglo) s.valor;
//                                        return arr.valores.get(indice);
//                                    }
//                                    else
//                                    {
//                                        String msg = "El indice:" + String.valueOf(indice) + " debe ser menor que el tamaño del arreglo :" + String.valueOf(s.tam_arreglo);
//                                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                                        otros.Error.agregarError(err);
//                                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                                    }
//                                }
//                                else
//                                {
//                                    String msg = "El indice debe de ser de tipo: \"NUMERO\"";
//                                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                                    otros.Error.agregarError(err);
//                                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                                }
//                            }
//                            else
//                            {
//                                String msg = "La variable \"" + id2 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                                        + " y de Tipo: \"" + s.tipo + "\"";
//                                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                                otros.Error.agregarError(err);
//                                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                            }
//                    }
//                    else if (izq == null && s != null)
//                    {
//                            /*verificar que sea una declaracion y de tipo normal*/
//                            if (s.tipo_nodo == Tipo.DECLARACION && s.tipo == Tipo.NORMAL)
//                            {
//                                /*retorno el literal que estaba almacenado en la variable*/
//                                return s.valor;
//                            }
//                            else
//                            {
//                                String msg = "La variable \"" + id2 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                                        + " y de Tipo: \"" + s.tipo + "\"";
//                                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                                otros.Error.agregarError(err);
//                                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                            }
//                    }
//                    else
//                    {
//                            String msg = "No se pudo encontrar el atributo \"" + id2 + "\" en el objeto \"" + id1 + "\"";
//                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                            otros.Error.agregarError(err);
//                            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                    }
//                }
//                else
//                {
//                        String msg = "La variable \"" + id1 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                                + " y de Tipo: \"" + s.tipo + "\"";
//                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                        otros.Error.agregarError(err);
//                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                }
//            }
//            else
//            {
//                    String msg = "No se pudo encontrar la variable \"" + id1 + "\"";
//                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//            }
//    }
//    
//    public Object var_normal(Entorno ent){
//            Simbolo s = ent.getSimbolo_Ent(id1);
//            
//            if(izq != null && s != null)
//            {   //es un arreglo
//                    if (s.tipo_nodo == Tipo.DECLARACION && s.tipo == Tipo.ARREGLO)
//                    {
//                        Literal nizq = (Literal) izq.evaluar(ent);
//                        /*validar el tipo que debe ser numerico*/
//                        if (nizq.tipo == Tipo.ENTERO || nizq.tipo == Tipo.DECIMAL)
//                        {
//                            /*obtener el indice*/
//                            int indice = Integer.parseInt(String.valueOf(nizq.valor));
//                            if(indice < s.tam_arreglo)
//                            {
//                                Arreglo arr = (Arreglo) s.valor;
//                                return arr.valores.get(indice);
//                            }
//                            else
//                            {
//                                String msg = "El indice:" + String.valueOf(indice) + " debe ser menor que el tamaño del arreglo :" + String.valueOf(s.tam_arreglo);
//                                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                                otros.Error.agregarError(err);
//                                return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                            }
//                        }
//                        else
//                        {
//                            String msg = "El indice debe de ser de tipo: \"NUMERO\"";
//                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                            otros.Error.agregarError(err);
//                            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                        }
//                    }
//                    else
//                    {
//                        String msg = "La variable \"" + id1 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                                + " y de Tipo: \"" + s.tipo + "\"";
//                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                        otros.Error.agregarError(err);
//                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                    }
//            }
//            else if (izq == null && s != null)
//            {
//                    /*verificar que sea una declaracion y de tipo normal*/
//                    if (s.tipo_nodo == Tipo.DECLARACION && s.tipo == Tipo.NORMAL)
//                    {
//                        /*retorno el literal que estaba almacenado en la variable*/
//                        return s.valor;
//                    }
//                    else
//                    {
//                        String msg = "La variable \"" + id1 + "\" no es una declaracion aceptada, por ser una instruccion: \"" + s.tipo_nodo + "\""
//                                + " y de Tipo: \"" + s.tipo + "\"";
//                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                        otros.Error.agregarError(err);
//                        return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//                    }
//            }
//            else
//            {
//                    String msg = "No se pudo encontrar la variable \"" + id1 + "\"";
//                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
//            }
//    }

    @Override
    public Object ejecutar(Entorno ent) {
        return execute(ent);
    }

}
