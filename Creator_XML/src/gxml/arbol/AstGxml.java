/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol;

import entorno.Entorno;
import gxml.analizador.gxmlParser;
import gxml.analizador.gxmlScanner;
import gxml.arbol.contenedor.Importar;
import gxml.arbol.contenedor.Ventana;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class AstGxml implements Ejecutar {
    public LinkedList<Importar> importaciones;
    public LinkedList<Ventana> ventanas;
    
    public String imports;
    public String cadena;
    public String cad_funcion;
    public String cad_principal;
    
    
    public AstGxml(LinkedList<Importar> importaciones, LinkedList<Ventana> ventanas){
        this.importaciones = importaciones;
        this.ventanas = ventanas;
        this.imports = "";
        this.cadena = "";
        this.cad_funcion = "";
        this.cad_principal = "";
    }
    
    public AstGxml(LinkedList<Ventana> ventanas){
        this.importaciones = new LinkedList<>();
        this.ventanas = ventanas;
        this.imports = "";
        this.cadena = "";
        this.cad_funcion = "";
        this.cad_principal = "";
    }

    @Override
    public String generarFs(Entorno ent) {
        
        generarTodo(ent, this.ventanas, this.importaciones, true, true);
        
        cadena = imports + "\n" + cadena + "\n" + cad_funcion + "\n" + cad_principal;
        
        return cadena;
    }
    
    public void generarTodo(Entorno ent, LinkedList<Ventana> vtns, LinkedList<Importar> impts, boolean ban_principal, boolean isFirst){
        /*ban_aux me detecta cuando ya hay una ventana principal*/
        boolean ban_aux = true;
        
        /*1. Realizar la generacion al archivo principal pero todavia no a los importaciones
        *    Entonces debo de obtener las ventanas*/
        
        /*necesito saber de que tipo la ventana*/
        int principal = 0;
        int yline = 0, ycolm = 0;
        
        if(vtns != null){
            for (Ventana vent : vtns) {
                vent.isFirst = isFirst;
                cadena += vent.generarFs(ent);
                cad_funcion += vent.cad_fun;
                
                if(vent.tipo != null){
                    yline = vent.tipo.line;
                    ycolm = vent.tipo.colm;
                    
                    if(vent.tipo.valor.toLowerCase().equals("principal") && ban_principal){
                        cad_principal = vent.id.valor + ".alCargar() ;\n";
                        principal += 1;
                        ban_aux = false;
                    }
                }
                
            }
        }
        
        if(principal > 1){
            String msg = "Debe haber solo una ventana de tipo \"principal\"";
            otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, yline, ycolm);
            otros.Error.agregarError(err);
            cad_principal = "";
        }
        
        
        /*2. Realizar las importaciones para eso debo de validar que tipo de formato
        *    tiene el archivo en el caso de "gxml" se debe ejecutar el archivo concatenar
        *    la generacion del codigo, de lo contrario si es "fs" se debe solo
        *    de realizar un importar del lenguaje "fs"*/
        
        for (Importar importacion : impts) {
            if(importacion.cadena.toLowerCase().endsWith(".gxml")){
                //aqui debo de leer el archivo gxml
                String entrada = creator_xml.Creator_XML.leerArchivo(Constante.path_relativa + importacion.cadena);
                
                String tmp_archivo = Constante.archivo;
                Constante.archivo = importacion.cadena;
                
                Entorno tmp = Constante.ent_temporal;
                Constante.ent_temporal = ent;
                
                AstGxml Syntax_tree = analizarGXML(entrada);
                    
                if(Syntax_tree != null){
                    
                    /*indicando el ambito del ast*/
                    //Syntax_tree.ambito = "Tipo: \"global\" Nombre: \"" + importacion.cadena + "\"";
                    /*indicando el archivo del ast*/
                    
                    
                    /*vuelvo a generar mas codigo*/
                    generarTodo(ent, Syntax_tree.ventanas, Syntax_tree.importaciones, ban_aux, false);
                }
                
                Constante.ent_temporal = tmp;
                Constante.archivo = tmp_archivo;
            }
            else if (importacion.cadena.toLowerCase().endsWith(".fs")){
                imports += "importar ( \"" + importacion.cadena + "\" );\n";
            }
            else if ( isFirst ) {
                String msg = "Solo se permite las extenciones .gxml o .fs";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, importacion.line, importacion.colm);
                otros.Error.agregarError(err);
            }
        }
    }
    
    
//    public Object generarImport(Entorno ent)
//    {   
//        for (Importar imp : importaciones) {
//            
//            if(imp.cadena.toLowerCase().endsWith(".fs"))
//            {
//                imports += "importar ( \"" + imp.cadena + "\" );\n";
//            }
//            else if (imp.cadena.toLowerCase().endsWith(".gxml"))
//            {
//                String entrada = creator_xml.Creator_XML.leerArchivo(Constante.path_relativa + imp.cadena);
//                
//                String tmp_archivo = Constante.archivo;
//                Constante.archivo = imp.cadena;
//                
//                Entorno tmp = Constante.ent_temporal;
//                Constante.ent_temporal = ent;
//                
//                AstGxml Syntax_tree = analizarGXML(entrada);
//                    
//                if(Syntax_tree != null){
//                    
//                    /*vuelvo a generar mas codigo*/
//                    generarTodo(ent, Syntax_tree.ventanas, Syntax_tree.importaciones, ban_aux, false);
//                    
//                }
//                
//                Constante.ent_temporal = tmp;
//                Constante.archivo = tmp_archivo;
//            }
//            
//        }
//        return null;
//    }
    
    /**
     * Este metodo se accionara al momento que el archivo principal tenga importaciones de tipo gxml
     * y devolvera el arbol a ese archivo
     * @param entrada
     * @return 
     */
    public static AstGxml analizarGXML(String entrada){
        //analizaremos la entrada
        gxmlScanner lex = new gxmlScanner(new BufferedReader(new StringReader(entrada)));
        gxmlParser parser = new gxmlParser(lex);
        gxmlParser.Syntax_tree = null;
        
        try{
            parser.parse();
            
            AstGxml SyntaxTree = gxmlParser.Syntax_tree;
            return SyntaxTree;
            
        }catch(Exception ex){
            Constante.consola = ex.getMessage();
        }
        return null;
    }
    
    @Override
    public boolean validarElementos(Entorno ent) {
        
        return true;
        
    }

    @Override
    public Object getStruct(Entorno ent) {
        
        
        LinkedList<Object> objetos = new LinkedList<>();
        
        for(Ventana vent : ventanas)
        {
            Object aux = vent.getStruct(ent);
            
            if(!(aux instanceof String))
            {
                Ventana tmp = (Ventana)aux;
                objetos.add((Ventana)tmp);
                
                for (Object obj : tmp.objetos) {
                    objetos.add(obj);
                }
            }
            
        }
        
        for (Importar imports : importaciones) {
            
            if(imports.cadena.toLowerCase().endsWith(".gxml"))
            {
                String entrada = creator_xml.Creator_XML.leerArchivo(Constante.path_relativa + imports.cadena);
                AstGxml st = analizarGXML(entrada);

                LinkedList<Object> aux_vents = (LinkedList) st.getStruct(ent);


                for (Object tmp : aux_vents) {
                    objetos.add(tmp);
                }
            }
            
        }
        
        return objetos;
        
        
//        LinkedList<Ventana> vents = new LinkedList<>();
//        
//        for (Ventana vent : ventanas) {
//            
//            Object obj = vent.getStruct(ent);
//            
//            if(obj instanceof Ventana)
//            {
//                vents.add((Ventana) obj);
//            }
//            
//        }
//        

//        
//        return vents;
    }
}
