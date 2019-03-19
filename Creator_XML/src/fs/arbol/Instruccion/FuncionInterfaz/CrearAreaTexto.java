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
import gxml.arbol.Elemento;
import gxml.arbol.componente.Controlador;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class CrearAreaTexto extends FunNativaInterfaz implements Expresion {
    
    public Controlador componente;

    public CrearAreaTexto(LinkedList<Expresion> exps, int line, int colm) {
        super(exps, line, colm);
    }

    @Override
    public Object evaluar(Entorno ent) {
        if(exps.size() == 11)
        {
            Literal e1 = (Literal) exps.get(0).evaluar(ent);     //entero o decimal
            Literal e2 = (Literal) exps.get(1).evaluar(ent);     //entero o decimal
            Literal e3 = (Literal) exps.get(2).evaluar(ent);     //cadena
            Literal e4 = (Literal) exps.get(3).evaluar(ent);     //entero o decimal
            Literal e5 = (Literal) exps.get(4).evaluar(ent);     //cadena
            Literal e6 = (Literal) exps.get(5).evaluar(ent);     //entero o decimal
            Literal e7 = (Literal) exps.get(6).evaluar(ent);     //entero o decimal
            Literal e8 = (Literal) exps.get(7).evaluar(ent);     //booleano
            Literal e9 = (Literal) exps.get(8).evaluar(ent);     //booleano
            Literal e10 = (Literal) exps.get(9).evaluar(ent);     //cadena o undefined
            Literal e11 = (Literal) exps.get(10).evaluar(ent);     //cadena
            
            
            if((e1.tipo == Operacion.Tipo.ENTERO || e1.tipo == Operacion.Tipo.DECIMAL)
                    && (e2.tipo == Operacion.Tipo.ENTERO || e2.tipo == Operacion.Tipo.DECIMAL)
                    && e3.tipo == Operacion.Tipo.CADENA 
                    && (e4.tipo == Operacion.Tipo.ENTERO || e4.tipo == Operacion.Tipo.DECIMAL)
                    && e5.tipo == Operacion.Tipo.CADENA
                    && (e6.tipo == Operacion.Tipo.ENTERO || e6.tipo == Operacion.Tipo.DECIMAL)
                    && (e7.tipo == Operacion.Tipo.ENTERO || e7.tipo == Operacion.Tipo.DECIMAL)
                    && e8.tipo == Operacion.Tipo.BOOLEANO
                    && e9.tipo == Operacion.Tipo.BOOLEANO
                    && (e10.tipo == Operacion.Tipo.CADENA || e10.tipo == Operacion.Tipo.UNDEFINED)
                    && e11.tipo == Operacion.Tipo.CADENA)
            {
                componente = new Controlador(new LinkedList<>(), new LinkedList<>(), line, colm);
                
                componente.elementos.add(new Elemento("alto", Operacion.Tipo.DECIMAL, String.valueOf(e1.valor), line, colm));
                componente.elementos.add(new Elemento("ancho", Operacion.Tipo.DECIMAL, String.valueOf(e2.valor), line, colm));
                componente.elementos.add(new Elemento("fuente", Operacion.Tipo.CADENA, String.valueOf(e3.valor), line, colm));
                componente.elementos.add(new Elemento("tam", Operacion.Tipo.DECIMAL, String.valueOf(e4.valor), line, colm));
                componente.elementos.add(new Elemento("color", Operacion.Tipo.CADENA, String.valueOf(e5.valor), line, colm));
                
                componente.elementos.add(new Elemento("x", Operacion.Tipo.DECIMAL, String.valueOf(e6.valor), line, colm));
                componente.elementos.add(new Elemento("y", Operacion.Tipo.DECIMAL, String.valueOf(e7.valor), line, colm));
                componente.elementos.add(new Elemento("negrita", Operacion.Tipo.BOOLEANO, String.valueOf(e8.valor), line, colm));
                componente.elementos.add(new Elemento("cursiva", Operacion.Tipo.BOOLEANO, String.valueOf(e9.valor), line, colm));
                
                if(e10.tipo != Operacion.Tipo.UNDEFINED)
                {
                    componente.defecto = (String) e10.valor;
                }
                componente.elementos.add(new Elemento("nombre", Operacion.Tipo.CADENA, String.valueOf(e11.valor), line, colm));
                componente.elementos.add(new Elemento("tipo", Operacion.Tipo.CADENA, "texto", line, colm));
                componente.elementos.add(new Elemento("defecto", Operacion.Tipo.CADENA, componente.defecto, line, colm));
                
                componente.generarAreaTexto(null);
                
                return new Literal(Operacion.Tipo.CONTROLADOR_AREA, componente, line, colm);
            }
            else
            {
                /*ERROR DE TIPOS EN LOS VALORES*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "Se esperaba que los tipos de datos de los valores son \n(NUMERO, NUMERO, CADENA, NUMERO, CADENA, NUMERO, NUMERO, BOOLEANO, BOOLEANO, CADENA, CADENA)", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            /*LA FUNCION CREAR TEXTO SOLO RECIBE 6 VALORES*/
            otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito, 
                    "La funcion CrearCajaTexto solo recibe 11 valores", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
}
