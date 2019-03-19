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
public class Relacional extends Operacion implements Expresion {
    
    public Relacional(Expresion izq, Expresion der, Operador op, int line, int colm) {
        super(izq, der, op, line, colm);
    }

    @Override
    public Object evaluar(Entorno ent) {
        Literal nizq = (izq != null)? (Literal)izq.evaluar(ent) : null;
        Literal nder = (der != null)? (Literal)der.evaluar(ent) : null;
        
        switch(op){
            case IGUALIGUAL:
                return igual(ent, nizq, nder);
            case DIFERENTE:
                return diferente(ent, nizq, nder);
            case MAYOR:
                return mayor(ent, nizq, nder);
            case MENOR:
                return menor(ent, nizq, nder);
            case MAYORIGUAL:
                return mayorigual(ent, nizq, nder);
            default:
                return menorigual(ent, nizq, nder);
        }
    }
    
    public Literal igual(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        if((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(String.valueOf(izq.valor));
            double v2 = Double.parseDouble(String.valueOf(der.valor));
            String res = (v1 == v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA){
            int v1 = getAsciiWord(String.valueOf(izq.valor));
            int v2 = getAsciiWord(String.valueOf(der.valor));
            String res = (v1 == v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            String res = (v1 == v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA || izq.tipo == Tipo.DECIMAL)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA || der.tipo == Tipo.DECIMAL)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = String.valueOf(izq.valor);
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(String.valueOf(der.valor));
//                    String res = (v1 == v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '==' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = String.valueOf(der.valor);
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(String.valueOf(izq.valor));
//                    double v2 = (double)getAsciiWord(tmp);
//                    String res = (v1 == v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '==' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        /*
        
        
        
        
            VALIDAR CON EL TIPO NULO
        
        
        
        
        */
        else if (izq.tipo == Tipo.UNDEFINED && der.tipo == Tipo.UNDEFINED)
        {
            if(izq.tipo == Tipo.UNDEFINED)
            {
                if(izq.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
            else
            {
                if(der.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
        }
        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL || izq.tipo == Tipo.UNDEFINED) 
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.UNDEFINED || der.tipo == Tipo.DECIMAL))
        {
            if(izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
            {
                if(izq.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
            else
            {
                if(der.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
        }
        else if ((izq.tipo == Tipo.CADENA || izq.tipo == Tipo.NULO) && (der.tipo == Tipo.CADENA || der.tipo == Tipo.NULO)
                && !(izq.tipo == Tipo.NULO && der.tipo == Tipo.NULO))
        {
            if(izq.tipo == Tipo.CADENA)
            {
                if(izq.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
            else
            {
                if(der.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '==' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }

    public Literal diferente(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        if((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(String.valueOf(izq.valor));
            double v2 = Double.parseDouble(String.valueOf(der.valor));
            String res = (v1 != v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA){
            int v1 = getAsciiWord(String.valueOf(izq.valor));
            int v2 = getAsciiWord(String.valueOf(der.valor));
            String res = (v1 != v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            String res = (v1 != v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA || izq.tipo == Tipo.DECIMAL)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA || der.tipo == Tipo.DECIMAL)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = String.valueOf(izq.valor);
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(String.valueOf(der.valor));
//                    String res = (v1 != v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '!=' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = String.valueOf(der.valor);
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(String.valueOf(izq.valor));
//                    double v2 = (double)getAsciiWord(tmp);
//                    String res = (v1 != v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '!=' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        /*
        
        
        
        
            VALIDAR CON EL TIPO NULO
        
        
        
        
        */
        else if (izq.tipo == Tipo.UNDEFINED && der.tipo == Tipo.UNDEFINED)
        {
            if(izq.tipo == Tipo.UNDEFINED)
            {
                if(!izq.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
            else
            {
                if(!der.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
        }
        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL || izq.tipo == Tipo.UNDEFINED) 
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.UNDEFINED || der.tipo == Tipo.DECIMAL))
        {
            if(izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
            {
                if(!izq.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
            else
            {
                if(!der.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
        }
        else if ((izq.tipo == Tipo.CADENA || izq.tipo == Tipo.NULO) && (der.tipo == Tipo.CADENA || der.tipo == Tipo.NULO)
                && !(izq.tipo == Tipo.NULO && der.tipo == Tipo.NULO))
        {
            if(izq.tipo == Tipo.CADENA)
            {
                if(!izq.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
            else
            {
                if(!der.valor.toString().equals(Constante.NULO)){
                    return new Literal(tipo, getBooleano(1), line, colm);
                }
                else
                {
                    return new Literal(tipo, getBooleano(0), line, colm);
                }
            }
        }
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '!=' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    public Literal mayor(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        if((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(String.valueOf(izq.valor));
            double v2 = Double.parseDouble(String.valueOf(der.valor));
            String res = (v1 > v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA){
            int v1 = getAsciiWord(String.valueOf(izq.valor));
            int v2 = getAsciiWord(String.valueOf(der.valor));
            String res = (v1 > v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            String res = (v1 > v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA || izq.tipo == Tipo.DECIMAL)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA || der.tipo == Tipo.DECIMAL)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = String.valueOf(izq.valor);
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(String.valueOf(der.valor));
//                    String res = (v1 > v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '>' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = String.valueOf(der.valor);
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(String.valueOf(izq.valor));
//                    double v2 = (double)getAsciiWord(tmp);
//                    String res = (v1 > v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg =  "Tipos de operandos incorrectos para el operador '>' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        /*
        
        
        
        
            VALIDAR CON EL TIPO NULO
        
        
        
        
        */
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg =   "Tipos de operandos incorrectos para el operador '>' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    public Literal menor(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        if((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(String.valueOf(izq.valor));
            double v2 = Double.parseDouble(String.valueOf(der.valor));
            String res = (v1 < v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA){
            int v1 = getAsciiWord(String.valueOf(izq.valor));
            int v2 = getAsciiWord(String.valueOf(der.valor));
            String res = (v1 < v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            String res = (v1 < v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA || izq.tipo == Tipo.DECIMAL)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA || der.tipo == Tipo.DECIMAL)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = String.valueOf(izq.valor);
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(String.valueOf(der.valor));
//                    String res = (v1 < v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg =   "Tipos de operandos incorrectos para el operador '<' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = String.valueOf(der.valor);
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(String.valueOf(izq.valor));
//                    double v2 = (double)getAsciiWord(tmp);
//                    String res = (v1 < v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                     String msg =   "Tipos de operandos incorrectos para el operador '<' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        /*
        
        
        
        
            VALIDAR CON EL TIPO NULO
        
        
        
        
        */
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg =   "Tipos de operandos incorrectos para el operador '<' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    public Literal mayorigual(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        if((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(String.valueOf(izq.valor));
            double v2 = Double.parseDouble(String.valueOf(der.valor));
            String res = (v1 >= v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA){
            int v1 = getAsciiWord(String.valueOf(izq.valor));
            int v2 = getAsciiWord(String.valueOf(der.valor));
            String res = (v1 >= v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            String res = (v1 >= v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA || izq.tipo == Tipo.DECIMAL)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA || der.tipo == Tipo.DECIMAL)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = String.valueOf(izq.valor);
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(String.valueOf(der.valor));
//                    String res = (v1 >= v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '>=' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = String.valueOf(der.valor);
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(String.valueOf(izq.valor));
//                    double v2 = (double)getAsciiWord(tmp);
//                    String res = (v1 >= v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg =  "Tipos de operandos incorrectos para el operador '>=' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        /*
        
        
        
        
            VALIDAR CON EL TIPO NULO
        
        
        
        
        */
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '>=' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }
    
    public Literal menorigual(Entorno ent, Literal izq, Literal der){
        Tipo tipo = Tipo.BOOLEANO;
        if((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.DECIMAL)
                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.DECIMAL)){
            double v1 = Double.parseDouble(String.valueOf(izq.valor));
            double v2 = Double.parseDouble(String.valueOf(der.valor));
            String res = (v1 <= v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.CADENA && der.tipo == Tipo.CADENA){
            int v1 = getAsciiWord(String.valueOf(izq.valor));
            int v2 = getAsciiWord(String.valueOf(der.valor));
            String res = (v1 <= v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
        else if (izq.tipo == Tipo.BOOLEANO && der.tipo == Tipo.BOOLEANO){
            int v1 = getBooleano(String.valueOf(izq.valor));
            int v2 = getBooleano(String.valueOf(der.valor));
            String res = (v1 <= v2)? getBooleano(1): getBooleano(0);
            return new Literal(tipo, res, line, colm);
        }
//        else if ((izq.tipo == Tipo.ENTERO || izq.tipo == Tipo.CADENA || izq.tipo == Tipo.DECIMAL)
//                && (der.tipo == Tipo.ENTERO || der.tipo == Tipo.CADENA || der.tipo == Tipo.DECIMAL)){
//            if (izq.tipo == Tipo.CADENA){
//                String tmp = String.valueOf(izq.valor);
//                if (tmp.length() == 1){
//                    double v1 = (double) getAsciiWord(tmp);
//                    double v2 = Double.parseDouble(String.valueOf(der.valor));
//                    String res = (v1 <= v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '<=' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//            else{
//                String tmp = String.valueOf(der.valor);
//                if (tmp.length() == 1){
//                    double v1 = Double.parseDouble(String.valueOf(izq.valor));
//                    double v2 = (double)getAsciiWord(tmp);
//                    String res = (v1 <= v2)? getBooleano(1): getBooleano(0);
//                    return new Literal(tipo, res, line, colm);
//                }
//                else{
//                    String msg = "Tipos de operandos incorrectos para el operador '<=' primer tipo: "
//                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
//                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
//                    otros.Error.agregarError(err);
//                    return new Literal(Tipo.ERROR, "", line, colm);
//                }
//            }
//        }
        /*
        
        
        
        
            VALIDAR CON EL TIPO NULO
        
        
        
        
        */
        else{
            if (!(izq.tipo == Tipo.ERROR || der.tipo == Tipo.ERROR)){
                String msg = "Tipos de operandos incorrectos para el operador '<=' primer tipo: "
                        + Constante.getTipo(izq.tipo) + " segundo tipo: " + Constante.getTipo(der.tipo);
                    otros.Error err = new otros.Error(Constante.FS, Constante.SEMANTICO, "", Constante.ambito, msg, Constante.archivo, line, colm);
                    otros.Error.agregarError(err);
            }
            return new Literal(Tipo.ERROR, "", line, colm);
        }
    }

}
