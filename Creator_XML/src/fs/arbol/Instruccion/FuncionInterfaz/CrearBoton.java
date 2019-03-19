/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionInterfaz;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Variable;
import fs.arbol.Instruccion.Funcion.CallFuncion;
import gxml.arbol.Elemento;
import gxml.arbol.componente.Boton;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class CrearBoton extends FunNativaInterfaz implements Expresion {

    public Boton boton;
    
    public CrearBoton(LinkedList<Expresion> exps, int line, int colm) {
        super(exps, line, colm);
        this.boton = null;
    }

    @Override
    public Object evaluar(Entorno ent) {
        
        if(exps.size() == 9)
        {
            Literal e1 = (Literal) exps.get(0).evaluar(ent);     //cadena
            Literal e2 = (Literal) exps.get(1).evaluar(ent);     //entero o decimal
            Literal e3 = (Literal) exps.get(2).evaluar(ent);     //cadena
            Literal e4 = (Literal) exps.get(3).evaluar(ent);     //entero o decimal
            Literal e5 = (Literal) exps.get(4).evaluar(ent);     //entero o decimal
            Object e6 = exps.get(5);     //referencia
            Literal e7 = (Literal) exps.get(6).evaluar(ent);     //cadena
            Literal e8 = (Literal) exps.get(7).evaluar(ent);     //entero o decimal
            Literal e9 = (Literal) exps.get(8).evaluar(ent);     //entero o decimal
            
            if(e1.tipo == Operacion.Tipo.CADENA
                    && (e2.tipo == Operacion.Tipo.ENTERO || e2.tipo == Operacion.Tipo.DECIMAL)
                    && e3.tipo == Operacion.Tipo.CADENA 
                    && (e4.tipo == Operacion.Tipo.ENTERO || e3.tipo == Operacion.Tipo.DECIMAL)
                    && (e5.tipo == Operacion.Tipo.ENTERO || e5.tipo == Operacion.Tipo.DECIMAL)
                    && (e6 instanceof Variable || e6 instanceof CallFuncion || e6 instanceof Literal)
                    && e7.tipo == Operacion.Tipo.CADENA
                    && (e8.tipo == Operacion.Tipo.ENTERO || e8.tipo == Operacion.Tipo.DECIMAL)
                    && (e9.tipo == Operacion.Tipo.ENTERO || e9.tipo == Operacion.Tipo.DECIMAL))
            {
                boton = new Boton(new LinkedList<>(), (String) e7.valor, line, colm);
                
                boton.elementos.add(new Elemento("fuente", Operacion.Tipo.CADENA, String.valueOf(e1.valor), line, colm));
                boton.elementos.add(new Elemento("tam", Operacion.Tipo.DECIMAL, String.valueOf(e2.valor), line, colm));
                boton.elementos.add(new Elemento("color", Operacion.Tipo.CADENA, String.valueOf(e3.valor), line, colm));
                boton.elementos.add(new Elemento("x", Operacion.Tipo.DECIMAL, String.valueOf(e4.valor), line, colm));
                boton.elementos.add(new Elemento("y", Operacion.Tipo.DECIMAL, String.valueOf(e5.valor), line, colm));
                
                boton.elementos.add(new Elemento("alto", Operacion.Tipo.DECIMAL, String.valueOf(e8.valor), line, colm));
                boton.elementos.add(new Elemento("ancho", Operacion.Tipo.DECIMAL, String.valueOf(e9.valor), line, colm));
                
                if(e6 instanceof CallFuncion)
                {
                    boton.reference = (CallFuncion) e6;
                }else
                {
                    boton.reference = null;
                }
                if(e6 instanceof Variable)
                {
                    boton.ident_vent = (Variable) e6;
                }
                else
                {
                    boton.ident_vent = null;
                }
                boton.generarBoton(ent);
                
                return new Literal(Operacion.Tipo.BOTON, boton, line, colm);
            }
            else
            {
                /*ERROR DE TIPOS EN LOS VALORES*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "Se esperaba que los tipos de datos de los valores sean (CADENA, NUMERO, CADENA, NUMERO, NUMERO, BOOLEANO, BOOLEANO, CADENA)", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            /*LA FUNCION CREAR TEXTO SOLO RECIBE 6 VALORES*/
            otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito, 
                    "La funcion CrearTexto solo recibe 9 valores", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
        
        
    }
    
}
