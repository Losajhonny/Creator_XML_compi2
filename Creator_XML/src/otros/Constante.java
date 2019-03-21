/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import entorno.Entorno;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import java.math.BigDecimal;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Jhona
 */
public class Constante {
    
    public static Entorno global_fun = new Entorno(null);
    public static Entorno global_dec = new Entorno(null);
    public static Entorno global_ven = new Entorno(null);
    public static Entorno global_gxm = new Entorno(null);
    
    public static Entorno ent_temporal = new Entorno(null);
    
    public static JFrame ventana_actual;
    
    public static String path_relativa = "archivos/";
    public static LinkedList<otros.Error> errores = new LinkedList<>();
    public static String consola = "";
    public static JTextArea consolaTexto = new JTextArea();
    public static String archivo = "";
    public static String ambito = "";
    
    public static String LEXICO = "Lexico";
    public static String SINTACTICO = "Sintactico";
    public static String SEMANTICO = "Semantico";
    public static String EJECUCION = "Semantico";
    
    public static final String[] OPTIONS_NAME = { "GenericXML", "FuncionScript" };
    public static final String[] OPTIONS_EXT =  { "gxml", "fs" };
    
    public static String GENERIC_XML = "GenericXML";
    public static String FUNCION_SCRIPT = "FuncionScript";
    
    public static String GXML = "gxml";
    public static String FS = "fs";
    public static String GDATO = "gdato";
    
    public static final String ALTO_VENTANA = "900";
    public static final String ANCHO_VENTANA = "900";
    public static final String ALTO_BOTON = "100";
    public static final String ALTO_CONTROL = "50";
    public static final String ANCHO_CONTROL = "50";
    public static final String ALTO_CONTENEDOR = "500";
    public static final String ALTO_MULTIMEDIA = "200";
    public static final String ANCHO_MULTIMEDIA = "200";
    public static final String ANCHO_CONTENEDOR = "500";
    public static final String ANCHO_BOTON = "100";
    public static final String COLOR_BLANCO = "#ffffff";
    public static final String COLOR_NEGRO = "#000000";
    public static final String FUENTE = "Arial";
    public static final String TAM = "14";
    public static final String NULO = "nulo";
    public static final String BORDE = "falso";
    public static final String NEGRITA = "falso";
    public static final String CURSIVA = "falso";
    public static final String AUTO_REPRODUCCION = "falso";
    
    public static final int ENTERO = 0;
    public static final int DECIMAL = 1;
    public static final int CADENA = 2;
    public static final int BOOLEANO = 3;
    public static final int OBJETO = 4;
    public static final int ERROR = 5;
    
    public static String getTipo(Tipo tipo){
        switch(tipo){
            case ENTERO:
            case DECIMAL:
                return "NUMERO";
            case CADENA:
                return "CADENA";
            case BOOLEANO:
                return "BOOLEANO";
            case UNDEFINED:
                return "UNDEFINED";
            default:
                return tipo.toString();
        }
    }
    
    public static double redondear(double num){
        BigDecimal res;
        res = new BigDecimal(num).setScale(12, BigDecimal.ROUND_HALF_DOWN);
        double resultado = res.doubleValue();
        return resultado;
    }
    
    public static String quitar_saltos(String cadena){
        return cadena;
    }
    
    public static String quitar_Tab(String cadena){
        return cadena;
    }
}
