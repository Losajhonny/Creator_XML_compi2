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
public class CrearDesplegable extends FunNativaInterfaz implements Expresion {
    
    public Controlador componente;

    public CrearDesplegable(LinkedList<Expresion> exps, int line, int colm) {
        super(exps, line, colm);
        this.componente = null;
    }

    @Override
    public Object evaluar(Entorno ent) {
        
        if(exps.size() == 7)
        {
            Literal e1 = (Literal) exps.get(0).evaluar(ent);     //entero o decimal
            Literal e2 = (Literal) exps.get(1).evaluar(ent);     //entero o decimal
            Literal e3 = (Literal) exps.get(2).evaluar(ent);     //arreglo
            Literal e4 = (Literal) exps.get(3).evaluar(ent);     //entero o decimal
            Literal e5 = (Literal) exps.get(4).evaluar(ent);     //entero o decimal
            Literal e6 = (Literal) exps.get(5).evaluar(ent);     //cadena o undefined
            Literal e7 = (Literal) exps.get(6).evaluar(ent);     //cadena
            
            if((e1.tipo == Operacion.Tipo.ENTERO || e1.tipo == Operacion.Tipo.DECIMAL)
                    && (e2.tipo == Operacion.Tipo.ENTERO || e2.tipo == Operacion.Tipo.DECIMAL)
                    && e3.tipo == Operacion.Tipo.ARREGLO
                    && (e4.tipo == Operacion.Tipo.ENTERO || e4.tipo == Operacion.Tipo.DECIMAL)
                    && (e5.tipo == Operacion.Tipo.ENTERO || e5.tipo == Operacion.Tipo.DECIMAL)
                    && (e6.tipo == Operacion.Tipo.CADENA || e6.tipo == Operacion.Tipo.UNDEFINED)
                    && e7.tipo == Operacion.Tipo.CADENA)
            {
                componente = new Controlador(new LinkedList<>(), new LinkedList<>(), line, colm);
                
                componente.elementos.add(new Elemento("alto", Operacion.Tipo.DECIMAL, String.valueOf(e1.valor), line, colm));
                componente.elementos.add(new Elemento("ancho", Operacion.Tipo.DECIMAL, String.valueOf(e2.valor), line, colm));
                componente.arreglo = e3;
                componente.elementos.add(new Elemento("x", Operacion.Tipo.DECIMAL, String.valueOf(e4.valor), line, colm));
                componente.elementos.add(new Elemento("y", Operacion.Tipo.DECIMAL, String.valueOf(e5.valor), line, colm));
                componente.defecto = (String) e6.valor;
                componente.elementos.add(new Elemento("nombre", Operacion.Tipo.CADENA, String.valueOf(e7.valor), line, colm));
                componente.elementos.add(new Elemento("defecto", Operacion.Tipo.CADENA, componente.defecto, line, colm));
                
                componente.generarDesplegable(null);
                
                return new Literal(Operacion.Tipo.CONTROLADOR_DESPLEGABLE, componente, line, colm);
            }
            else
            {
                /*ERROR DE TIPOS EN LOS VALORES*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "Se esperaba que los tipos de datos de los valores son \n"
                            + "(NUMERO, NUMERO, NUMERO, NUMERO, NUMERO, NUMERO, NUMERO, CADENA)", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            /*LA FUNCION CREAR TEXTO SOLO RECIBE 6 VALORES*/
            otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito, 
                    "La funcion CrearControlNumerico solo recibe 8 valores", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
        
    }
    
}
