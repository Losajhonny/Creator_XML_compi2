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
import gxml.arbol.Elemento;
import gxml.arbol.componente.Boton;
import gxml.arbol.componente.Controlador;
import gxml.arbol.componente.Enviar;
import gxml.arbol.componente.Texto;
import gxml.arbol.contenedor.Contenedor;
import gxml.arbol.contenedor.Ventana;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Objeto implements Instruccion{
    public LinkedList<Par> pares;
    public Entorno entorno;
    
    public Literal element_interfaz;
    
    public Objeto(LinkedList<Par> pares){
        this.pares = pares;
    }
    
    public void crearEntorno(Entorno ant){
        entorno = new Entorno(ant);
    }
    
    public static Objeto crearObjeto(Literal element_interfaz)
    {
        if(element_interfaz != null)
        {
            Objeto tmp = new Objeto(new LinkedList<>());
            if(element_interfaz.tipo == Tipo.VENTANA)
            {
                tmp.element_interfaz = element_interfaz;
                Ventana vent = (Ventana) element_interfaz.valor;
                
                for(Elemento element: vent.elementos)
                {
                    Literal valor = new Literal(element.tipo, element.valor, 0, 0);
                    Par par = new Par(element.id, valor, 0, 0);
                    tmp.pares.add(par);
                }
                return tmp;
            }
            else if (element_interfaz.tipo == Tipo.CONTENEDOR)
            {
                tmp.element_interfaz = element_interfaz;
                Contenedor cont = (Contenedor) element_interfaz.valor;
                
                for(Elemento element: cont.elementos)
                {
                    Literal valor = new Literal(element.tipo, element.valor, 0, 0);
                    Par par = new Par(element.id, valor, 0, 0);
                    tmp.pares.add(par);
                }
                return tmp;
            }
            else if (element_interfaz.tipo == Tipo.TEXTO)
            {
                tmp.element_interfaz = element_interfaz;
                Texto text = (Texto) element_interfaz.valor;
                
                for(Elemento element: text.elementos)
                {
                    Literal valor = new Literal(element.tipo, element.valor, 0, 0);
                    Par par = new Par(element.id, valor, 0, 0);
                    tmp.pares.add(par);
                }
                return tmp;
            }
            else if (element_interfaz.tipo == Tipo.BOTON)
            {
                tmp.element_interfaz = element_interfaz;
                Boton text = (Boton) element_interfaz.valor;
                
                for(Elemento element: text.elementos)
                {
                    Literal valor = new Literal(element.tipo, element.valor, 0, 0);
                    Par par = new Par(element.id, valor, 0, 0);
                    tmp.pares.add(par);
                }
                return tmp;
            }
            else if (element_interfaz.tipo == Tipo.ENVIAR)
            {
                tmp.element_interfaz = element_interfaz;
                Enviar text = (Enviar) element_interfaz.valor;
                
                for(Elemento element: text.elementos)
                {
                    Literal valor = new Literal(element.tipo, element.valor, 0, 0);
                    Par par = new Par(element.id, valor, 0, 0);
                    tmp.pares.add(par);
                }
                return tmp;
            }
            else if (element_interfaz.tipo == Tipo.CONTROLADOR_TEXTO
                    || element_interfaz.tipo == Tipo.CONTROLADOR_AREA
                    || element_interfaz.tipo == Tipo.CONTROLADOR_NUMERICO
                    || element_interfaz.tipo == Tipo.CONTROLADOR)
            {
                tmp.element_interfaz = element_interfaz;
                Controlador cont = (Controlador) element_interfaz.valor;
                
                for(Elemento element: cont.elementos)
                {
                    Literal valor = new Literal(element.tipo, element.valor, 0, 0);
                    Par par = new Par(element.id, valor, 0, 0);
                    tmp.pares.add(par);
                }
                return tmp;
            }
            else if (element_interfaz.tipo == Tipo.CONTROLADOR_DESPLEGABLE)
            {
                tmp.element_interfaz = element_interfaz;
                Controlador cont = (Controlador) element_interfaz.valor;
                
                for(Elemento element: cont.elementos)
                {
                    Literal valor = new Literal(element.tipo, element.valor, 0, 0);
                    Par par = new Par(element.id, valor, 0, 0);
                    tmp.pares.add(par);
                }
                Par par = new Par("lista", cont.arreglo.valor, 0, 0);
                tmp.pares.add(par);
                
                return tmp;
            }
        }
        return null;
    }

    /**
     * Asigna valores a los atributos
     * Si un atributo es un arreglo entonces se ejcutara y se asignara
     * Si un atributo es una exp entonces debo de ejecutar y asignarlo
     * @param ent
     * @return 
     */
    @Override
    public Object ejecutar(Entorno ent)
    {
        crearEntorno(ent);
        for (Par pare : pares)
        {   
            /*ejecuta la expresion*/
            Literal literal = (pare.valor instanceof Expresion)? (Literal)((Expresion)pare.valor).evaluar(entorno): null;
            
            /*le seteo el ambito*/
            Arreglo arr = (pare.valor instanceof Arreglo)? (Arreglo) pare.valor: null;
            
            /*lo ejecuto*/
            arr = (arr != null)? (Arreglo)arr.ejecutar(entorno): null;
            
            Simbolo s = entorno.getSimbolo_EntActual(pare.id);
            
            if(s == null && literal != null){
                literal.valor = (literal.tipo == Tipo.ERROR)? Constante.NULO: literal.valor;
                literal.tipo = (literal.tipo == Tipo.ERROR)? Tipo.UNDEFINED: literal.tipo;
                
                literal.valor = (literal.tipo == Tipo.OBJETO)? Constante.NULO: literal.valor;
                
                if(literal.tipo == Tipo.OBJETO)
                {
                    String msg = "El atributo \"" + pare.id + "\" no se le puede asignar un OBJETO de valor";
                    otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, pare.line, pare.colm);
                    otros.Error.agregarError(err);
                }
                literal.tipo = (literal.tipo == Tipo.OBJETO)? Tipo.UNDEFINED: literal.tipo;
                
                s = new Simbolo(pare.id, Tipo.NORMAL, literal);
                entorno.agregar(pare.id, s);
            }
            else if (s == null && arr != null){
                s = new Simbolo(pare.id, Tipo.ARREGLO, arr);
                s.tam_arreglo = arr.tam;
                entorno.agregar(pare.id, s);
            }
            else{
                String msg = "El atributo \"" + pare.id + "\" ya existe en el objeto actual";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, pare.line, pare.colm);
                otros.Error.agregarError(err);
            }
        }
        
        return this;
    }
}
