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
import gxml.arbol.contenedor.Contenedor;
import gxml.arbol.contenedor.Ventana;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class CrearContenedor extends FunNativaInterfaz implements Expresion {

    public Contenedor cont;
    public Ventana ventana;
    
    public CrearContenedor(LinkedList<Expresion> exps, int line, int colm) {
        super(exps, line, colm);
    }

    @Override
    public Object evaluar(Entorno ent) {
        
        if(exps.size() == 6)
        {
            Literal e1 = (Literal) exps.get(0).evaluar(ent);     //entero o decimal
            Literal e2 = (Literal) exps.get(1).evaluar(ent);     //entero o decimal
            Literal e3 = (Literal) exps.get(2).evaluar(ent);     //cadena
            Literal e4 = (Literal) exps.get(3).evaluar(ent);     //booleano
            Literal e5 = (Literal) exps.get(4).evaluar(ent);     //entero o decimal
            Literal e6 = (Literal) exps.get(5).evaluar(ent);     //entero o decimal
            
            if((e1.tipo == Operacion.Tipo.ENTERO || e1.tipo == Operacion.Tipo.DECIMAL) 
                    && (e2.tipo == Operacion.Tipo.ENTERO || e2.tipo == Operacion.Tipo.DECIMAL)
                    && e3.tipo == Operacion.Tipo.CADENA 
                    && e4.tipo == Operacion.Tipo.BOOLEANO
                    && (e5.tipo == Operacion.Tipo.ENTERO || e5.tipo == Operacion.Tipo.DECIMAL)
                    && (e6.tipo == Operacion.Tipo.ENTERO || e6.tipo == Operacion.Tipo.DECIMAL))
            {
                cont = new Contenedor(new LinkedList<>(), line, colm);
                cont.ventana = ventana;
                cont.elementos.add(new Elemento("alto", Operacion.Tipo.DECIMAL, String.valueOf(e1.valor), line, colm));
                cont.elementos.add(new Elemento("ancho", Operacion.Tipo.DECIMAL, String.valueOf(e2.valor), line, colm));
                cont.elementos.add(new Elemento("color", Operacion.Tipo.CADENA, String.valueOf(e3.valor), line, colm));
                cont.elementos.add(new Elemento("borde", Operacion.Tipo.BOOLEANO, String.valueOf(e4.valor), line, colm));
                cont.elementos.add(new Elemento("x", Operacion.Tipo.DECIMAL, String.valueOf(e5.valor), line, colm));
                cont.elementos.add(new Elemento("y", Operacion.Tipo.DECIMAL, String.valueOf(e6.valor), line, colm));
                
                cont.generarContenedor(null);
                
                return new Literal(Operacion.Tipo.CONTENEDOR, cont, line, colm);
            }
            else
            {
                /*ERROR DE TIPOS EN LOS VALORES*/
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, 
                    "Se esperaba que los tipos de datos de los valores sean (NUMERO, NUMERO, CADENA, BOOLEANO, NUMERO, NUMERO)", Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            /*LA FUNCION CREAR CONTENEDOR SOLO RECIBE 6 VALORES*/
            otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito, 
                    "La funcion CrearContenedor solo recibe 6 valores", Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
}
