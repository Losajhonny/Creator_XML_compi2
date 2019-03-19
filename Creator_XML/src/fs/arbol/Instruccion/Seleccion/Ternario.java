/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Seleccion;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Logica;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Relacional;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Ternario extends Operacion implements Expresion{
    
    public Expresion condicion;
    public Expresion etrue, efalse;
    
    public Ternario(Expresion condicion, Expresion etrue, Expresion efalse, int line, int colm)
    {
        super(line, colm);
        this.condicion = condicion;
        this.etrue = etrue;
        this.efalse = efalse;
    }

    @Override
    public Object evaluar(Entorno ent) {
        
        /*EVALUAR LA CONDICION*/
        //Literal con = (Literal) condicion.evaluar(ent);
        if(condicion instanceof Logica || condicion instanceof Relacional/*con.tipo == Tipo.BOOLEANO*/)
        {
            Literal con = (Literal) condicion.evaluar(ent);
            int valor = (con.tipo == Tipo.BOOLEANO)? Operacion.getBooleano(String.valueOf(con.valor)): 0;
            boolean paso = (valor == 1);
            
            if(paso)
            {   /*SI ES VERDADERO DEBO EVALUAR ETRUE*/
                Literal ltrue = (Literal) etrue.evaluar(ent);
                return ltrue;
            }
            else
            {   /*SI ES VERDADERO DEBO EVALUAR EFALSE*/
                Literal lfalse = (Literal) efalse.evaluar(ent);
                return lfalse;
            }
        }
        else
        {
            String msg = "La condicion debe ser de tipo: " + Tipo.BOOLEANO;
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Tipo.ERROR, Constante.NULO, line, colm);
        }
    }
    
}
