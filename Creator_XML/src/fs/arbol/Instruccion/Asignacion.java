/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Operaciones.Aritmetica;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Operador;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Declaracion.Arreglo;
import fs.arbol.Instruccion.Declaracion.Objeto;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Asignacion implements Instruccion {
    public String id1, id2;
    public Operador op;
    public Expresion indice, valor;
    public Arreglo arr;
    public Objeto obj;
    public int line, colm;
    
    public Asignacion (String id1, Expresion indice, Operador op, Expresion valor, int line, int colm){
        // ID1[EXP] = EXP
        this.id1 = id1.toLowerCase();
        this.id2 = null;
        this.op = op;
        this.indice = indice;
        this.valor = valor;
        this.line = line;
        this.colm = colm;
        this.arr = null;
        this.obj = null;
    }
    
    public Asignacion (String id1, Operador op, Expresion valor, int line, int colm){
        // ID1 = EXP
        this.id1 = id1.toLowerCase();
        this.id2 = null;
        this.op = op;
        this.indice = null;
        this.valor = valor;
        this.line = line;
        this.colm = colm;
        this.arr = null;
        this.obj = null;
    }
    
    public Asignacion (String id1, Operador op, Arreglo valor, int line, int colm){
        // ID1 = EXP
        this.id1 = id1.toLowerCase();
        this.id2 = null;
        this.op = op;
        this.indice = null;
        this.valor = null;
        this.arr = valor;
        this.line = line;
        this.colm = colm;
        this.obj = null;
    }
    
    public Asignacion (String id1, Operador op, Objeto valor, int line, int colm){
        // ID1 = EXP
        this.id1 = id1.toLowerCase();
        this.id2 = null;
        this.op = op;
        this.indice = null;
        this.valor = null;
        this.obj = valor;
        this.line = line;
        this.colm = colm;
        this.arr = null;
    }
    
    public Asignacion (String id1, String id2, Expresion indice, Operador op, Expresion valor, int line, int colm){
        // ID1.ID2[EXP] = EXP
        this.id1 = id1.toLowerCase();
        this.id2 = id2.toLowerCase();
        this.op = op;
        this.indice = indice;
        this.valor = valor;
        this.arr = null;
        this.obj = null;
        this.line = line;
        this.colm = colm;
    }
    
    public Asignacion (String id1, String id2, Operador op, Expresion valor, int line, int colm){
        // ID1.ID2 = EXP
        this.id1 = id1.toLowerCase();
        this.id2 = id2.toLowerCase();
        this.op = op;
        this.indice = null;
        this.valor = valor;
        this.arr = null;
        this.obj = null;
        this.line = line;
        this.colm = colm;
    }
    
    public Asignacion (String id1, String id2, Operador op, Arreglo valor, int line, int colm){
        // ID1.ID2 = EXP
        this.id1 = id1.toLowerCase();
        this.id2 = id2.toLowerCase();
        this.op = op;
        this.indice = null;
        this.valor = null;
        this.arr = valor;
        this.obj = null;
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        /*BUSCAR EL ID1*/
        Simbolo s = ent.getSimbolo_Ent(id1);
        
        if (s != null)
        {
            if(id2 != null)
            {
                if(s.tipo == Tipo.OBJETO)
                {
                    Objeto objeto = (Objeto) s.valor;
                    s = objeto.entorno.getSimbolo_EntActual(id2);
                    
                    if (s != null)
                    {
                        /*ID1.ID2 | ID1.ID2[EXP]*/
                        changeValor(ent, s, false);
                    }
                    else
                    {
                        String msg = "No se pudo encontrar el atributo \"" + id2 + "\" en el objeto \"" + id1 + "\"";
                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
                    }
                }
                else
                {
                    String msg = "La variable \"" + id1 + "\" no es un objeto para poder obtener el valor de los atributos";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
            }
            else
            {
                changeValor(ent, s, true);
            }
        }
        else
        {
            //NO EXISTE EL ID1
            String msg = "No se pudo encontrar la variable \"" + id1 + "\"";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return this;
    }
    
    public void changeValor(Entorno ent, Simbolo s, boolean isId1)
    {
        /*ID | ID[EXP]*/
        if(indice != null)
        {
            if(s.tipo == Tipo.ARREGLO)
            {   //NO SE LE PUEDE ASIGNAR UN ARREGLO Y OBJETO
                Arreglo arreglo = (Arreglo) s.valor;
                Literal index = (Literal) indice.evaluar(ent);

                if(index.tipo == Tipo.ENTERO || index.tipo == Tipo.DECIMAL)
                {
                    int i = Integer.parseInt(String.valueOf(index.valor));

                    if(i < arreglo.tam)
                    {   
                        switch(op)
                        {
                            case IGUAL:
                                Literal L = (Literal) valor.evaluar(ent);

                                if(L.tipo == Tipo.ARREGLO)
                                {
                                    String msg = "En el arreglo no se le puede asignar un ARREGLO de valor";
                                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, L.line, L.colm);
                                    otros.Error.agregarError(err);
                                }
                                else if(L.tipo == Tipo.OBJETO)
                                {
                                    String msg = "En el arreglo no se le puede asignar un OBJETO de valor";
                                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, L.line, L.colm);
                                    otros.Error.agregarError(err);
                                }
                                L.valor = (L.tipo == Tipo.ERROR)? Constante.NULO: L.valor;
                                L.tipo = (L.tipo == Tipo.ERROR)? Tipo.UNDEFINED: L.tipo;
                                L.valor = (L.tipo == Tipo.ARREGLO)? Constante.NULO: L.valor;
                                L.tipo = (L.tipo == Tipo.ARREGLO)? Tipo.UNDEFINED: L.tipo;
                                L.valor = (L.tipo == Tipo.OBJETO)? Constante.NULO: L.valor;
                                L.tipo = (L.tipo == Tipo.OBJETO)? Tipo.UNDEFINED: L.tipo;


                                arreglo.valores.set(i, L);
                                break;
                            default:
                                Literal val = arreglo.valores.get(i);
                                Aritmetica opArit = new Aritmetica(val, valor, Operador.MAS, line, colm);

                                Literal LArit;
                                switch(op)
                                {
                                    case MASIGUAL:
                                        opArit.op = Operador.MAS;
                                        LArit = (Literal) opArit.evaluar(ent);
                                        break;
                                    case MENIGUAL:
                                        opArit.op = Operador.MENOS;
                                        LArit = (Literal) opArit.evaluar(ent);
                                        break;
                                    case PORIGUAL:
                                        opArit.op = Operador.POR;
                                        LArit = (Literal) opArit.evaluar(ent);
                                        break;
                                    default:
                                        opArit.op = Operador.DIV;
                                        LArit = (Literal) opArit.evaluar(ent);
                                        break;
                                }

                                if(LArit.tipo == Tipo.ARREGLO)
                                {
                                    String msg = "En el arreglo no se le puede asignar un ARREGLO de valor";
                                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, LArit.line, LArit.colm);
                                    otros.Error.agregarError(err);
                                }
                                else if(LArit.tipo == Tipo.OBJETO)
                                {
                                    String msg = "En el arreglo no se le puede asignar un OBJETO de valor";
                                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, LArit.line, LArit.colm);
                                    otros.Error.agregarError(err);
                                }
                                LArit.valor = (LArit.tipo == Tipo.ERROR)? Constante.NULO: LArit.valor;
                                LArit.tipo = (LArit.tipo == Tipo.ERROR)? Tipo.UNDEFINED: LArit.tipo;
                                LArit.valor = (LArit.tipo == Tipo.ARREGLO)? Constante.NULO: LArit.valor;
                                LArit.tipo = (LArit.tipo == Tipo.ARREGLO)? Tipo.UNDEFINED: LArit.tipo;
                                LArit.valor = (LArit.tipo == Tipo.OBJETO)? Constante.NULO: LArit.valor;
                                LArit.tipo = (LArit.tipo == Tipo.OBJETO)? Tipo.UNDEFINED: LArit.tipo;


                                arreglo.valores.set(i, LArit);
                                break;
                        }

                    }
                    else
                    {
                        if(isId1)
                        {
                            String msg = "El indice esta fuera de rango del arreglo \"" + id1 + "\"";
                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                            otros.Error.agregarError(err);
                        }
                        else
                        {
                            String msg = "El indice esta fuera de rango del arreglo \"" + id2 + "\"";
                            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                            otros.Error.agregarError(err);
                        }
                    }
                }
                else
                {
                    String msg = "El indice debe de ser de tipo \"NUMERO\"";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
            }
            else
            {
                if(isId1)
                {
                    String msg = "La variable \"" + id1 + "\" no es un ARREGLO para poder asignar un valor";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
                else
                {
                    String msg = "La variable \"" + id2 + "\" no es un ARREGLO para poder asignar un valor";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
            }
        }
        else
        {
            if(valor != null)
            {
                switch(op)
                {
                    case IGUAL:
                        Literal L = (Literal) valor.evaluar(ent);
                        L.valor = (L.tipo == Tipo.ERROR)? Constante.NULO: L.valor;
                        L.tipo = (L.tipo == Tipo.ERROR)? Tipo.UNDEFINED: L.tipo;

                        if(isId1)
                        {
                            if(L.tipo == Tipo.VENTANA || L.tipo == Tipo.CONTENEDOR
                                    || L.tipo == Tipo.TEXTO || L.tipo == Tipo.CONTROLADOR_TEXTO
                                    || L.tipo == Tipo.CONTROLADOR_AREA
                                    || L.tipo == Tipo.CONTROLADOR_NUMERICO
                                    || L.tipo == Tipo.CONTROLADOR_DESPLEGABLE
                                    || L.tipo == Tipo.BOTON
                                    || L.tipo == Tipo.ENVIAR
                                    || L.tipo == Tipo.CONTROLADOR)
                            {
                                Objeto objeto = Objeto.crearObjeto(L);
                                objeto = (Objeto) objeto.ejecutar(ent);
                                s.tipo = Tipo.OBJETO;
                                s.componente = L.valor;
                                s.valor = objeto;
                            }
                            else
                            {
                                s.tipo = L.tipo;
                                s.tam_arreglo = (L.valor instanceof Arreglo)? ((Arreglo)L.valor).tam: 0;
                                s.valor = (L.valor instanceof Arreglo)? L.valor: (L.valor instanceof Objeto)? L.valor : L;
                            }
                        }
                        else
                        {
                            if(L.tipo == Tipo.OBJETO || L.tipo == Tipo.VENTANA || L.tipo == Tipo.CONTENEDOR
                                    || L.tipo == Tipo.TEXTO)
                            {
                                String msg = "En el atributo no se le puede asignar un OBJETO de valor";
                                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, L.line, L.colm);
                                otros.Error.agregarError(err);
                            }
                            L.valor = (L.tipo == Tipo.OBJETO)? Constante.NULO: L.valor;
                            L.tipo = (L.tipo == Tipo.OBJETO)? Tipo.UNDEFINED: L.tipo;
                            s.tipo = L.tipo;
                            s.valor = (L.valor instanceof Arreglo)? L.valor : L;
                            s.tam_arreglo = (L.valor instanceof Arreglo)? ((Arreglo)L.valor).tam : 0;
                        }
                        break;
                    default:
                        Literal Lmasigual = (s.tipo == Tipo.ARREGLO)? new Literal(Tipo.ARREGLO, s.valor, line, colm): 
                                (s.tipo == Tipo.OBJETO)? new Literal(Tipo.OBJETO, s.valor, line, colm): (Literal)s.valor;
                        Aritmetica opArit = new Aritmetica(Lmasigual, valor, Operador.MAS, line, colm);

                        Literal LArit;
                        switch(op)
                        {
                            case MASIGUAL:
                                opArit.op = Operador.MAS;
                                LArit = (Literal) opArit.evaluar(ent);
                                break;
                            case MENIGUAL:
                                opArit.op = Operador.MENOS;
                                LArit = (Literal) opArit.evaluar(ent);
                                break;
                            case PORIGUAL:
                                opArit.op = Operador.POR;
                                LArit = (Literal) opArit.evaluar(ent);
                                break;
                            default:
                                opArit.op = Operador.DIV;
                                LArit = (Literal) opArit.evaluar(ent);
                                break;
                        }

                        LArit.valor = (LArit.tipo == Tipo.ERROR)? Constante.NULO: LArit.valor;
                        LArit.tipo = (LArit.tipo == Tipo.ERROR)? Tipo.UNDEFINED: LArit.tipo;

                        if(isId1)
                        {
                            s.tipo = LArit.tipo;
                            s.tam_arreglo = (LArit.valor instanceof Arreglo)? ((Arreglo)LArit.valor).tam: 0;
                            s.valor = (LArit.valor instanceof Arreglo)? LArit.valor: (LArit.valor instanceof Objeto)? LArit.valor : LArit;
                        }
                        else
                        {
                            if(LArit.tipo == Tipo.OBJETO)
                            {
                                String msg = "En el atributo no se le puede asignar un OBJETO de valor";
                                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, LArit.line, LArit.colm);
                                otros.Error.agregarError(err);
                            }
                            LArit.valor = (LArit.tipo == Tipo.OBJETO)? Constante.NULO: LArit.valor;
                            LArit.tipo = (LArit.tipo == Tipo.OBJETO)? Tipo.UNDEFINED: LArit.tipo;
                            s.tipo = LArit.tipo;
                            s.valor = (LArit.valor instanceof Arreglo)? LArit.valor : LArit;
                            s.tam_arreglo = (LArit.valor instanceof Arreglo)? ((Arreglo)LArit.valor).tam : 0;
                        }
                        break;

                }
            }
            else if (arr != null)
            {
                arr = (Arreglo)arr.ejecutar(ent);
                s.tipo = Tipo.ARREGLO;
                s.tam_arreglo = arr.tam;
                s.valor = arr;
            }
            else
            {
                if(isId1)
                {
                    obj = (Objeto)obj.ejecutar(ent);
                    s.tipo = Tipo.OBJETO;
                    s.valor = obj;
                }
                else
                {
                    String msg = "El atributo \"" + id2 + "\" no se le puede asignar un OBJETO";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
            }
        }
    }
}
