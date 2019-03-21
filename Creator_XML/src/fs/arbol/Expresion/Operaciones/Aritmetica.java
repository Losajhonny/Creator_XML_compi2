/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Expresion.Operaciones;

import entorno.*;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Aritmetica extends Operacion implements Expresion {
    
    public Aritmetica(Expresion izq, Expresion der, Operador op, int line, int colm) {
        super(izq, der, op, line, colm);
    }
    
    public Aritmetica(Expresion izq, Operador op, int line, int colm){
        super(izq, op, line, colm);
    }

    @Override
    public Object evaluar(Entorno ent) {
        Literal nizq = (izq != null)? (Literal)izq.evaluar(ent): null;
        Literal nder = (der != null)? (Literal)der.evaluar(ent): null;
        
        switch(op){
            case MAS:
                return suma(ent, nizq, nder);
            case MENOS:
                if(der != null){
                    return resta(ent, nizq, nder);
                }
                else {
                    return umenos(ent, nizq);
                }
            case POR:
                return multiplicacion(ent, nizq, nder);
            case DIV:
                return division(ent, nizq, nder);
            case POT:
                return potencia(ent, nizq, nder);
            default:
                return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    /*
    Duda: En el documento de aclaraciones esta que se elimino el tipo de dato caracter
    entonces se puede realizar esta operacion "a" + 10?
    se devuelve un entero o una cadena
    En la explicacion del proyecto
    se menciono que si es podia realizar y resultado es un numero
    */
    
    public Literal suma(Entorno ent, Literal izq, Literal der){
        Tipo tipo;
        if (izq.tipo == Tipo.ENTERO && der.tipo == Tipo.ENTERO){
            long v1 = Long.parseLong(izq.valor.toString());
            long v2 = Long.parseLong(der.valor.toString());
            long v = v1 + v2;
            tipo = Tipo.ENTERO;
            return new Literal(tipo, String.valueOf(v), line, colm);
        }
        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL) &&
                (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(izq.valor.toString());
            double v2 = Double.parseDouble(der.valor.toString());
            double v = v1 + v2;
            tipo = Tipo.DECIMAL;
            return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
        }
        else if ((izq.tipo == Tipo.BOOLEANO || izq.tipo == Tipo.CADENA)
                && (der.tipo == Tipo.BOOLEANO || der.tipo == Tipo.CADENA)
                && !(izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO)){
            String v = izq.valor.toString() + der.valor.toString();
            tipo = Tipo.CADENA;
            return new Literal(tipo, String.valueOf(v), line, colm);
        }
        else if ((izq.tipo == Tipo.CADENA || izq.tipo == Tipo.DECIMAL) &&
                (der.tipo == Tipo.CADENA || der.tipo == Tipo.DECIMAL)){
            
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(der.valor.toString());
//                    tipo = Tipo.DECIMAL;
//                    double v = v1 + v2;
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
                    String v = izq.valor.toString() + der.valor.toString();
                    tipo = Tipo.CADENA;
                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(izq.valor.toString());
//                    double v2 = (double) getAsciiWord(tmp);
//                    tipo = Tipo.DECIMAL;
//                    double v = v1 + v2;
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
//                    String v = izq.valor.toString() + der.valor.toString();
//                    tipo = Tipo.CADENA;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//            }
        }
        else if ((izq.tipo == Tipo.CADENA || izq.tipo == Tipo.ENTERO) &&
                (der.tipo == Tipo.CADENA || der.tipo == Tipo.ENTERO)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 = getAsciiWord(tmp);
//                    long v2 = Long.parseLong(der.valor.toString());
//                    tipo = Tipo.ENTERO;
//                    long v = v1 + v2;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
                    String v = izq.valor.toString() + der.valor.toString();
                    tipo = Tipo.CADENA;
                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 = Long.parseLong(izq.valor.toString());
//                    long v2 = getAsciiWord(tmp);
//                    tipo = Tipo.ENTERO;
//                    long v = v1 + v2;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
//                    String v = izq.valor.toString() + der.valor.toString();
//                    tipo = Tipo.CADENA;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//            }
        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '+' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }

    public Literal resta(Entorno ent, Literal izq, Literal der){
        Tipo tipo;
        if (izq.tipo == Tipo.ENTERO && der.tipo == Tipo.ENTERO){
            long v1 = Long.parseLong(izq.valor.toString());
            long v2 = Long.parseLong(der.valor.toString());
            long v = v1 - v2;
            tipo = Tipo.ENTERO;
            return new Literal(tipo, String.valueOf(v), line, colm);
        }
        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(izq.valor.toString());
            double v2 = Double.parseDouble(der.valor.toString());
            double v = v1 - v2;
            tipo = Tipo.DECIMAL;
            return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA)
//                && !(izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 =  getAsciiWord(tmp);
//                    long v2 = Long.parseLong(der.valor.toString());
//                    tipo = Tipo.ENTERO;
//                    long v = v1 - v2;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '-' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 = Long.parseLong(izq.valor.toString());
//                    long v2 =  getAsciiWord(tmp);
//                    tipo = Tipo.ENTERO;
//                    long v = v1 - v2;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '-' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
//        else if ((izq.tipo == Tipo.DECIMAL || izq.tipo == Tipo.CADENA)
//                && (der.tipo == Tipo.DECIMAL || der.tipo == Tipo.CADENA)
//                && !(izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(der.valor.toString());
//                    tipo = Tipo.DECIMAL;
//                    double v = v1 - v2;
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '-' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(izq.valor.toString());
//                    double v2 = (double) getAsciiWord(tmp);
//                    tipo = Tipo.DECIMAL;
//                    double v = v1 - v2;
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '-' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '-' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }

    public Literal umenos(Entorno ent, Literal izq){
        switch(izq.tipo){
            case ENTERO:
                long ve = Long.parseLong(izq.valor.toString());
                ve = ve * -1;
                return new Literal(izq.tipo, String.valueOf(ve), line, colm);
            case DECIMAL:
                double vd = Double.parseDouble(izq.valor.toString());
                vd = vd * -1;
                return new Literal(izq.tipo, String.valueOf(Constante.redondear(vd)), line, colm);
            default:
                if (!(izq.tipo == Tipo.ERROR)){
                    String msg = "Tipos de operandos incorrectos para el operador '-' unario primer tipo: "
                                    + Constante.getTipo(izq.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                }
                return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    public Literal multiplicacion(Entorno ent, Literal izq, Literal der){
        Tipo tipo;
        if (izq.tipo == Tipo.ENTERO && der.tipo == Tipo.ENTERO){
            long v1 = Long.parseLong(izq.valor.toString());
            long v2 = Long.parseLong(der.valor.toString());
            long v = v1 * v2;
            tipo = Tipo.ENTERO;
            return new Literal(tipo, String.valueOf(v), line, colm);
        }
        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(izq.valor.toString());
            double v2 = Double.parseDouble(der.valor.toString());
            double v = v1 * v2;
            tipo = Tipo.DECIMAL;
            return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA)
//                && !(izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 =  getAsciiWord(tmp);
//                    long v2 = Long.parseLong(der.valor.toString());
//                    tipo = Tipo.ENTERO;
//                    long v = v1 * v2;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '*' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 = Long.parseLong(izq.valor.toString());
//                    long v2 =  getAsciiWord(tmp);
//                    tipo = Tipo.ENTERO;
//                    long v = v1 * v2;
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '*' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
//        else if ((izq.tipo == Tipo.DECIMAL || izq.tipo == Tipo.CADENA)
//                && (der.tipo == Tipo.DECIMAL || der.tipo == Tipo.CADENA)
//                && !(izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(der.valor.toString());
//                    tipo = Tipo.DECIMAL;
//                    double v = v1 * v2;
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '*' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(izq.valor.toString());
//                    double v2 = (double) getAsciiWord(tmp);
//                    tipo = Tipo.DECIMAL;
//                    double v = v1 * v2;
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '*' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '*' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }

    public Literal division(Entorno ent, Literal izq, Literal der){
        Tipo tipo;
        if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(izq.valor.toString());
            double v2 = Double.parseDouble(der.valor.toString());
            if(v2 != 0.0){
                double v = v1 / v2;
                tipo = Tipo.DECIMAL;
                return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
            }
            else{
                String msg = "La expresion no se puede dividir por 0 ";
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
                return new Literal(Tipo.ERROR, "", line, colm);
            }
        }
//        else if ((izq.tipo == Tipo.DECIMAL || izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA)
//                && (der.tipo == Tipo.DECIMAL || der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA)
//                && !(izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(der.valor.toString());
//                    if(v2 != 0.0){
//                        double v = v1 / v2;
//                        tipo = Tipo.DECIMAL;
//                        return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                    }
//                    else{
//                        String msg = "La expresion no se puede dividir por 0 ";
//                        otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                        Constante.errores.add(err);
//                        return new Literal(Tipo.ERROR, "", line, colm);
//                    }
//                }
//                else{
//                    String msg =  "Tipos de operandos incorrectos para el operador '/' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(izq.valor.toString());
//                    double v2 = (double) getAsciiWord(tmp);
//                    if(v2 != 0.0){
//                        double v = v1 / v2;
//                        tipo = Tipo.DECIMAL;
//                        return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                    }
//                    else{
//                        String msg =  "La expresion no se puede dividir por 0 ";
//                        otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                        Constante.errores.add(err);
//                        return new Literal(Tipo.ERROR, "", line, colm);
//                    }
//                }
//                else{
//                    String msg =  "Tipos de operandos incorrectos para el operador '/' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg =  "Tipos de operandos incorrectos para el operador '/' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }

    public Literal potencia(Entorno ent, Literal izq, Literal der){
        Tipo tipo;
        if (izq.tipo == Tipo.ENTERO && der.tipo == Tipo.ENTERO){
            long v1 = Long.parseLong(izq.valor.toString());
            long v2 = Long.parseLong(der.valor.toString());
            long v = (long) Math.pow(v1, v2);
            tipo = Tipo.ENTERO;
            return new Literal(tipo, String.valueOf(v), line, colm);
        }
        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(izq.valor.toString());
            double v2 = Double.parseDouble(der.valor.toString());
            double v = Math.pow(v1, v2);
            tipo = Tipo.DECIMAL;
            return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA)
//                && !(izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 =  getAsciiWord(tmp);
//                    long v2 = Long.parseLong(der.valor.toString());
//                    tipo = Tipo.ENTERO;
//                    long v = (long) Math.pow(v1, v2);
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
//                    String msg =  "Tipos de operandos incorrectos para el operador '^' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    long v1 = Long.parseLong(izq.valor.toString());
//                    long v2 =  getAsciiWord(tmp);
//                    tipo = Tipo.ENTERO;
//                    long v = (long) Math.pow(v1, v2);
//                    return new Literal(tipo, String.valueOf(v), line, colm);
//                }
//                else{
//                     String msg =  "Tipos de operandos incorrectos para el operador '^' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
//        else if ((izq.tipo == Tipo.DECIMAL || izq.tipo == Tipo.CADENA)
//                && (der.tipo == Tipo.DECIMAL || der.tipo == Tipo.CADENA)
//                && !(izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = izq.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(der.valor.toString());
//                    tipo = Tipo.DECIMAL;
//                    double v = Math.pow(v1, v2);
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
//                    String msg =  "Tipos de operandos incorrectos para el operador '^' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = der.valor.toString();
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(izq.valor.toString());
//                    double v2 = (double) getAsciiWord(tmp);
//                    tipo = Tipo.DECIMAL;
//                    double v = Math.pow(v1, v2);
//                    return new Literal(tipo, String.valueOf(Constante.redondear(v)), line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '^' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    Constante.errores.add(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '^' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
}
