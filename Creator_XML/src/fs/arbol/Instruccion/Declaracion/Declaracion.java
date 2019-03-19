/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Declaracion;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Declaracion implements Instruccion {
    public Tipo tipo;
    public LinkedList<Id> ids;
    public Expresion exp;
    public Arreglo arreglo;
    public Objeto objeto;
    public int line, colm;
    
    public boolean isImport;
    private Simbolo sLast;
    
    public Declaracion(String id, int line, int colm)
    {
        this.tipo = Tipo.NORMAL;
        this.exp = null;
        this.arreglo = null;
        this.objeto = null;
        this.line = line;
        this.colm = colm;
        this.ids = new LinkedList<>();
        this.isImport = false;
        Id ident = new Id(id.toLowerCase(), line, colm);
        ids.add(ident);
        
    }
    
    public Declaracion(LinkedList<Id> ids, Expresion exp, int line, int colm){
        this.tipo = Tipo.NORMAL;
        this.ids = ids;
        this.exp = exp;
        this.arreglo = null;
        this.objeto = null;
        this.line = line;
        this.colm = colm;
        this.isImport = false;
    }
    
    public Declaracion(LinkedList<Id> ids, int line, int colm){
        this.tipo = Tipo.NORMAL;
        this.ids = ids;
        this.exp = null;
        this.arreglo = null;
        this.objeto = null;
        this.line = line;
        this.colm = colm;
        this.isImport = false;
    }
    
    public Declaracion(LinkedList<Id> ids, Arreglo arreglo, int line, int colm){
        this.tipo = Tipo.ARREGLO;
        this.ids = ids;
        this.exp = null;
        this.arreglo = arreglo;
        this.objeto = null;
        this.line = line;
        this.colm = colm;
        this.isImport = false;
    }
    
    public Declaracion(LinkedList<Id> ids, Objeto objeto, int line, int colm){
        this.tipo = Tipo.OBJETO;
        this.ids = ids;
        this.exp = null;
        this.arreglo = null;
        this.objeto = objeto;
        this.line = line;
        this.colm = colm;
        this.isImport = false;
    }
    
    @Override
    public Object ejecutar(Entorno ent) 
    {
        /*GUARDO LAS VARIABLES EN LA TABLA DE SIMBOLOS*/
        guardarVariables(ent);
        /*OBTENGO EL ID DEL ULTIMO VALOR*/
        switch(tipo){
            case NORMAL:
                if(sLast != null && exp != null)
                {
                    Literal L = (Literal) exp.evaluar(ent);
                    
                    L.valor = (L.tipo == Tipo.ERROR)? Constante.NULO: L.valor;
                    L.tipo = (L.tipo == Tipo.ERROR)? Tipo.UNDEFINED: L.tipo;
                    
                    if(L.tipo == Tipo.VENTANA || L.tipo == Tipo.CONTENEDOR
                            || L.tipo == Tipo.TEXTO || L.tipo == Tipo.CONTROLADOR_TEXTO
                            || L.tipo == Tipo.CONTROLADOR_AREA
                            || L.tipo == Tipo.CONTROLADOR_NUMERICO
                            || L.tipo == Tipo.CONTROLADOR_DESPLEGABLE
                            || L.tipo == Tipo.BOTON
                            || L.tipo == Tipo.ENVIAR
                            || L.tipo == Tipo.CONTROLADOR)
                    {
                        objeto = Objeto.crearObjeto(L);
                        objeto = (Objeto) objeto.ejecutar(ent);
                        sLast.tipo = Tipo.OBJETO;
                        sLast.componente = L.valor;
                        sLast.valor = objeto;
                    }
                    else
                    {
                        sLast.tipo = L.tipo;
                        sLast.tam_arreglo = (L.valor instanceof Arreglo)? ((Arreglo)L.valor).tam: 0;
                        sLast.valor = (L.valor instanceof Arreglo)? L.valor: (L.valor instanceof Objeto)? L.valor : L;
                    }
                }
                break;
            case ARREGLO:
                if(sLast != null && arreglo != null)
                {
                    arreglo = (Arreglo)arreglo.ejecutar(ent);
                    sLast.tipo = Tipo.ARREGLO;
                    sLast.tam_arreglo = arreglo.tam;
                    sLast.valor = arreglo;
                }
                break;
            default://OBJETO
                if(sLast != null && objeto != null)
                {
                    objeto = (Objeto)objeto.ejecutar(ent);
                    sLast.tipo = Tipo.OBJETO;
                    sLast.valor = objeto;
                }
                break;
        }
        
        return this;
    }
    
//    public void declaracion_normal(Entorno ent){
//        if(sLast != null && exp != null){
//            /*guardo en la tabla de simbolos un literal*/
//            Operacion op = (Operacion) exp;
//            op.ambito = ambito;
//            
//            Literal L = (Literal) exp.evaluar(ent);
//            L.valor = (L.tipo == Tipo.ERROR)? Constante.NULO: L.valor;
//            L.tipo = (L.tipo == Tipo.ERROR)? Tipo.UNDEFINED: L.tipo;
//            
//            switch(L.tipo)
//            {
//                case ARREGLO:
//                    Arreglo arr = (Arreglo) L.valor;
//                    sLast.tam_arreglo = arr.tam;
//                    sLast.tipo = Tipo.ARREGLO;
//                    break;
//                case OBJETO:
//                    sLast.tipo = Tipo.OBJETO;
//                    break;
//                default:
//                    sLast.tipo = L.tipo;
//                    break;
//            }
//            sLast.valor = L;
//        }
//    }
//    
    public void guardarVariables(Entorno ent){
        for (Id id : ids) {
            Simbolo s = ent.getSimbolo_EntActual(id.id);
            
            if (s == null){
                s = new Simbolo(id.id, Tipo.UNDEFINED, new Literal(Tipo.UNDEFINED, Constante.NULO, 0, 0));
                ent.agregar(id.id, s);
                sLast = s;
            }
            else{
                if(!isImport)
                {
                    String msg = "La variable \"" + id.id + "\" ya existe en el ambito actual";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, id.line, id.colm);
                    otros.Error.agregarError(err);
                }
                sLast = null;
            }
        }
    }
//
//    public void declaracion_arreglo(Entorno ent){
//        if(sLast != null){
//            arreglo.ambito = ambito;
//            Arreglo arr = (Arreglo)arreglo.ejecutar(ent);
//            sLast.tipo = Tipo.ARREGLO;
//            sLast.valor = new Literal(Tipo.ARREGLO, arr, line, colm);
//            sLast.tam_arreglo = arr.tam;
//        }
//    }
//    
//    public void declaracion_objeto(Entorno ent){
//        if(sLast != null){
//            Objeto obj = objeto;
//            obj.ambito = "Tipo: \"objeto\" Nombre: \"" + sLast.id + "\"";
//            obj = (Objeto)obj.ejecutar(ent);
//            sLast.tipo = Tipo.OBJETO;
//            sLast.valor = new Literal(Tipo.OBJETO, obj, line, colm);
//        }
//    }

}
