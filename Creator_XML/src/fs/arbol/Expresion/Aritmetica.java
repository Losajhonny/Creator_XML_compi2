/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion;

import entorno.*;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Aritmetica extends Operacion {
    
    public Aritmetica(Operacion izq, Operacion der, Operador op, int line, int colm) {
        super(izq, der, op, line, colm);
    }
    
    public Aritmetica(Operacion izq, Operador op, int line, int colm){
        super(izq, op, line, colm);
    }
    
    @Override
    public int getTipo(Literal izq, Literal der){
        switch(op){
            case MAS:
                return Constante.MAS[izq.tipo][der.tipo];
            case MENOS:
                return Constante.MENOS[izq.tipo][der.tipo];
            case POR:
                return Constante.POR[izq.tipo][der.tipo];
            case DIV:
                return Constante.DIV[izq.tipo][der.tipo];
            case POT:
                return Constante.POT[izq.tipo][der.tipo];
            default:
                return izq.tipo;
        }
    }

    @Override
    public Object evaluar(Entorno ent) {
        Literal nizq = (izq != null)? (Literal)izq.evaluar(ent):null;
        Literal nder = (der != null)? (Literal)der.evaluar(ent):null;
        
        int tipo = getTipo(nizq, nder);
        
        switch (op) {
            case MAS:
                if(nizq.tipo == Constante.ENTERO && nder.tipo == Constante.ENTERO){
                    int v1 = Integer.parseInt(String.valueOf(nizq.valor));
                    int v2 = Integer.parseInt(String.valueOf(nder.valor));
                    int v = v1 + v2;
                    return new Literal(tipo, v, line, colm);
                }
                else if ((nizq.tipo == Constante.ENTERO || nizq.tipo == Constante.DECIMAL)
                    && (nder.tipo == Constante.ENTERO || nder.tipo == Constante.DECIMAL)){
                    double v1 = Double.parseDouble(String.valueOf(nizq.valor));
                    double v2 = Double.parseDouble(String.valueOf(nder.valor));
                    double v = v1 + v2;
                    return new Literal(tipo, v, line, colm); 
                }
                else if ((nizq.tipo == Constante.ENTERO || nizq.tipo == Constante.CARACTER)
                        && (nder.tipo == Constante.ENTERO || nder.tipo == Constante.CARACTER)
                        && !(nizq.tipo == Constante.CARACTER && nder.tipo == Constante.CARACTER)){
                    
                    if(nizq.tipo == Constante.CARACTER){
                        if(isCaracter(String.valueOf(nizq.valor))){
                            int v1 = getAscii(String.valueOf(nizq.valor));
                            int v2 = Integer.parseInt(String.valueOf(nder.valor));
                            int v = v1 + v2;
                            return new Literal(tipo, v, line, colm);
                        }
                        else{
                            String v1 = String.valueOf(nizq.valor);
                            String v2 = String.valueOf(nder.valor);
                            String v = v1 + v2;
                            return new Literal(Constante.CADENA, v, line, colm);
                        }
                    }
                    else if (nder.tipo == Constante.CARACTER){
                        if(isCaracter(String.valueOf(nder.valor))){
                            int v1 = Integer.parseInt(String.valueOf(nizq.valor));
                            int v2 = getAscii(String.valueOf(nder.valor));
                            int v = v1 + v2;
                            return new Literal(tipo, v, line, colm);
                        }
                        else{
                            String v1 = String.valueOf(nizq.valor);
                            String v2 = String.valueOf(nder.valor);
                            String v = v1 + v2;
                            return new Literal(Constante.CADENA, v, line, colm);
                        }
                    }
                }
                else if ((nizq.tipo == Constante.ENTERO || nizq.tipo == Constante.CADENA)
                    && (nder.tipo == Constante.ENTERO || nder.tipo == Constante.CADENA)){
                    String v1 = String.valueOf(nizq.valor);
                    String v2 = String.valueOf(nder.valor);
                    String v = v1 + v2;
                    return new Literal(tipo, v, line, colm);
                }
                else if ((nizq.tipo == Constante.BOOLEANO || nizq.tipo == Constante.CADENA)
                    && (nder.tipo == Constante.BOOLEANO || nder.tipo == Constante.CADENA)
                    && !(nizq.tipo == Constante.BOOLEANO && nder.tipo == Constante.BOOLEANO)){
                    String v1 = String.valueOf(nizq.valor);
                    String v2 = String.valueOf(nder.valor);
                    String v = v1 + v2;
                    return new Literal(tipo, v, line, colm);
                }
                else if ((nizq.tipo == Constante.CARACTER || nizq.tipo == Constante.CADENA)
                    && (nder.tipo == Constante.CARACTER || nder.tipo == Constante.CADENA)){
                    String v1 = String.valueOf(nizq.valor);
                    String v2 = String.valueOf(nder.valor);
                    String v = v1 + v2;
                    return new Literal(tipo, v, line, colm);
                }

                
                
                break;
            case MENOS:
                break;
            case POR:
                break;
            default:
                break;
        }
        
        return null;
    }
    
    public int getAscii(String valor){
        return (int)valor.charAt(0);
    }

    public boolean isCaracter(String cadena){
        return cadena.length() == 1;
    }
}
