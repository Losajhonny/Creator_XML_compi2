/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creator_xml;

import entorno.Entorno;
import fs.analizador.fsParser;
import fs.analizador.fsScanner;
import fs.arbol.AstFs;
import gxml.analizador.gxmlParser;
import gxml.analizador.gxmlScanner;
import gxml.arbol.AstGxml;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Creator_XML {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //principal p = new principal();
        //p.setVisible(true);
        String pendiente = "Pendientes:\n"
                + "En Aritmeticas ++, --\n"
                + "Entonces el caracter no esta debo de qutarlos \"a\" o 'a'\n"
                + "Solo tipo de dato numero"
                + "En Relacionales el tipo nulo a == nulo b != nulo\n";
        
        
//        System.out.println(pendiente);
//        System.out.println("");
//        System.out.println("Voy realizando la toma de errores la delclaracion");
//        
//        double a = Math.pow(100, 100);
//        
//        System.out.println(a);
        
        int b = 5;
        double c = fibonacci(19);
        
        System.out.println(c);
        
    }
    
    public static int fibonacci(int n)
    {
        if (n>1){
           return fibonacci(n-1) + fibonacci(n-2);  //función recursiva
        }
        else if (n==1) {  // caso base
            return 1;
        }
        else if (n==0){  // caso base
            return 0;
        }
        else{ //error
            System.out.println("Debes ingresar un tamaño mayor o igual a 1");
            return -1; 
        }
    }
//    
    public static double invertir ( double n )
    {
        return n < 10? n: modulo(n, 10) + invertir ( n/ 10) * 10;
    }
//    
    public static double modulo (double n, double p)
    {
        return n<p? n: modulo (n-p, p);
    }
//    
//    public static int as(int n)
//    {
//        if(n < 0)
//        {
//            return 0;
//        }
//        else
//        {
//            return (n==0)? 1: n-as(n-1);
//        }
//    }
//    
    public static int ackermann(int m, int n)
    {
        System.out.println(m + ":" + n);
        if(m == 0)
        {
            return (n+1);
        }
        else if (m > 0 && n == 0)
        {
            return ackermann(m-1, 1);
        }
        else
        {
            return ackermann(m-1, ackermann(m, n-1));
        }
    }
//    
//    public static int ident(int a)
//    {
//        if (a > 0)
//        {
//            System.out.println(a);
//            return ident(a-1)+5;
//        }
//        return a;
//    }
    
    public static String leerArchivo(String path){
        FileReader fr = null;
        BufferedReader br = null;
        String cadena_archivo = "";
        try{
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String linea = "";
            while((linea=br.readLine()) != null){
                cadena_archivo += linea + "\n";
            }
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                br.close();
                fr.close();
            }catch(Exception ex) {}
        }
        
        return cadena_archivo;
    }
    
    public static void crearArchivo(String path, String cadena){
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        try{
            f = new File(path);
            
            f.createNewFile();
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            
            bw.write(cadena);
        }
        catch(Exception ex){}
        finally{
            try{
                bw.close();
                fw.close();
            }catch(Exception ex){}
        }
    }
    
    public static void analizarGXML(String nombre, String entrada){
        //analizaremos la entrada
        gxmlScanner lexico = new gxmlScanner(new BufferedReader(new StringReader(entrada)));
        gxmlParser parser = new gxmlParser(lexico);
        gxmlParser.Syntax_tree = null;
        
        try{
            Constante.global_gxm = new Entorno(null);
            Constante.archivo = nombre;
            Constante.global_gxm.ambito = "global";
            
            Entorno tmp = Constante.ent_temporal;
            Constante.ent_temporal = Constante.global_gxm;
            
            parser.parse();
            
            AstGxml SyntaxTree = gxmlParser.Syntax_tree;
            
            if(SyntaxTree != null)
            {
                String name = Constante.path_relativa + nombre.replaceAll(".gxml", ".fs");
                
                String traducido = SyntaxTree.generarFs(Constante.global_gxm);
                crearArchivo(name, traducido);
            }
            
            Constante.ent_temporal = tmp;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    public static void analizarFS(String nombre, String entrada){
        //analizaremos la entrada
        fsScanner lexico = new fsScanner(new BufferedReader(new StringReader(entrada)));
        fsParser parser = new fsParser(lexico);
        fsParser.Syntax_tree = null;
        
        try{
            Constante.global_dec = new Entorno(null);
            Constante.global_fun = new Entorno(null);
            Constante.global_ven = new Entorno(null);
            Constante.archivo = nombre;
            Constante.global_ven.ambito = "global";
            Constante.global_dec.ambito = "global";
            Constante.global_fun.ambito = "global";
            
            Constante.ent_temporal = Constante.global_dec;
            parser.parse();
            
            AstFs SyntaxTree = fsParser.Syntax_tree;
            
            if(SyntaxTree != null)
            {
                SyntaxTree.ejecutar(Constante.global_dec);
            }
            
        }catch(Exception ex){ System.out.println(ex.getMessage()); }
    }
}
