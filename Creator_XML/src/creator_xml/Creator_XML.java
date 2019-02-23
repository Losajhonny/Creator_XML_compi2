/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creator_xml;

import fs.analizador.fsParser;
import fs.analizador.fsScanner;
import gxml.analizador.gxmlParser;
import gxml.analizador.gxmlScanner;
import java.io.BufferedReader;
import java.io.Console;
import java.io.StringReader;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Creator_XML {
    
    public static LinkedList<Error> errores;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    //    analizarGXML(" < VENTANA ID=\"\" > < CONTENEDOR X=2 > < / CONTENEDOR > < / VENTANA > ");
        //analizarFS(" /** hola mundo *****/ 2+2-5*8/7^8==7>4<4>=3<=5!=4");
        
        principal p = new principal();
        p.setVisible(true);
    }
    
    public static void analizarGXML(String entrada){
        //analizaremos la entrada
        gxmlScanner lexico = new gxmlScanner(new BufferedReader(new StringReader(entrada)));
        gxmlParser parser = new gxmlParser(lexico);
        
        try{
            parser.parse();
        }catch(Exception ex){
            Constante.consola = ex.getMessage();
            //ex.printStackTrace();
        }
    }
    
    
    public static void analizarFS(String entrada){
        //analizaremos la entrada
        fsScanner lexico = new fsScanner(new BufferedReader(new StringReader(entrada)));
        fsParser parser = new fsParser(lexico);
        
        try{
            parser.parse();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
