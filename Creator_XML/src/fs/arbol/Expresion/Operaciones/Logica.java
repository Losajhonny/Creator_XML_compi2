/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion.Operaciones;

import entorno.Entorno;
import fs.arbol.Expresion.*;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Logica extends Operacion implements Expresion {
    
    public Logica(Expresion izq, Expresion der, Operador op, int line, int colm) {
        super(izq, der, op, line, colm);
    }
    
    public Logica(Expresion izq, Operador op, int line, int colm){
        super(izq, op, line, colm);
    }

    @Override
    public Object evaluar(Entorno ent) {
        Literal nizq = (izq != null)? (Literal)izq.evaluar(ent) : null;
        Literal nder = (der != null)? (Literal)der.evaluar(ent) : null;
        
        switch(op){
            case AND:
                return and(ent, nizq, nder);
            case OR:
                return or(ent, nizq, nder);
            default:
                return not(ent, nizq);
        }
    }
    
    public Literal and(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        
        if(izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            int v = v1 * v2;
            String res = (v == 1)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                 String msg =  "Tipos de operandos incorrectos para el operador '&&' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    public Literal or(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        
        if(izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            int v = v1 + v2;
            String res = (v >= 1)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '||' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    public Literal not(Entorno ent, Literal izq){
        Tipo tipo = Tipo.BOOLEANO;
        
        if(izq.tipo == Tipo.BOOLEANO){
            int v = getBooleano(String.valueOf(izq.valor));
            String res = (v == 1)? getBooleano(0): getBooleano(1);
            return new Literal(tipo, res, line, colm);
        }
        else{
            if (!(izq.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '!' primer tipo: "
                        + Constante.getTipo(izq.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
}
