/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionInterfaz;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import gxml.arbol.Elemento;
import gxml.arbol.contenedor.Ventana;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class CrearVentana extends FunNativaInterfaz implements Expresion {
    
    public Ventana vent;

    public CrearVentana(LinkedList<Expresion> exps, int line, int colm) {
        super(exps, line, colm);
    }
    
    @Override
    public Object evaluar(Entorno ent) {
        
        if(exps.size() == 4)
        {
            Literal e1 = (Literal) exps.get(0).evaluar(ent);     //cadena
            Literal e2 = (Literal) exps.get(1).evaluar(ent);     //entero o decimal
            Literal e3 = (Literal) exps.get(2).evaluar(ent);     //entero o decimal
            Literal e4 = (Literal) exps.get(3).evaluar(ent);     //cadena
            
            if(e1.tipo == Tipo.CADENA && e4.tipo == Tipo.CADENA && (e2.tipo == Tipo.ENTERO || e2.tipo == Tipo.DECIMAL)
                    && (e3.tipo == Tipo.ENTERO || e3.tipo == Tipo.DECIMAL))
            {
                vent = new Ventana(new LinkedList<>(), line, colm);
                vent.elementos.add(new Elemento("color", Tipo.CADENA, String.valueOf(e1.valor), line, colm));
                vent.elementos.add(new Elemento("alto", Tipo.DECIMAL, String.valueOf(e2.valor), line, colm));
                vent.elementos.add(new Elemento("ancho", Tipo.DECIMAL, String.valueOf(e3.valor), line, colm));
                vent.elementos.add(new Elemento("id", Tipo.CADENA, String.valueOf(e4.valor), line, colm));
                vent.elementos.add(new Elemento("tipo", Tipo.CADENA, "secundaria", line, colm));
                
                vent.generarVentana(ent);
                
                return new Literal(Tipo.VENTANA, vent, line, colm);
            }
            else
            {
                /*ERROR DE TIPOS EN LOS VALORES*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "Se esperaba que los tipos de datos de los valores sean (CADENA, NUMERO, NUMERO, CADENA)", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            /*LA FUNCION CREAR VENTANA SOLO RECIBE 4 VALORES*/
            otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito, 
                    "La funcion CrearVentana solo recibe 4 valores", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        
        return new Literal(Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
}
