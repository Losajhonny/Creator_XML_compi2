/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol;

import entorno.Entorno;
import fs.analizador.fsParser;
import fs.analizador.fsScanner;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Funcion.Funcion;
import fs.arbol.Instruccion.Instruccion;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Importacion implements Instruccion {
    
//    public Entorno global_fun;
    public Expresion exp;
    public int line, colm;
    public AstFs arbol;
    
    public Importacion(Expresion exp, int line, int colm){
        this.exp = exp;
        this.line = line;
        this.colm = colm;
//        this.global_fun = null;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        
        arbol = null;
        Literal res = (Literal) exp.evaluar(ent);
        
        if(res.tipo == Tipo.CADENA)
        {
            String ruta = String.valueOf(res.valor);
            String aux = ruta.toLowerCase();
            
            if(aux.endsWith(".fs"))
            {
                String entrada = creator_xml.Creator_XML.leerArchivo(Constante.path_relativa + ruta);
                fsScanner lexico = new fsScanner(new BufferedReader(new StringReader(entrada)));
                fsParser parser = new fsParser(lexico);
                fsParser.Syntax_tree = null;
                
                Entorno tmp = Constante.ent_temporal;
                Constante.ent_temporal = ent;
                
                String aux_archivo = Constante.archivo;
                Constante.archivo = ruta;
                try{
                    /*aqui debo de guardar las funciones recorrer el Syntax_tree*/
                    parser.parse();
                    
                    arbol = fsParser.Syntax_tree;
                    if(arbol != null)
                    {
                        obtener_funcion(arbol.elementos);
                        
                        for (Importacion imp : arbol.importaciones) {
                            imp.ejecutar(ent);
                        }
                    }
                    
                }catch(Exception ex){}
                
                Constante.archivo = aux_archivo;
                Constante.ent_temporal = tmp;
            }
            else
            {
                String msg = "La ruta debe ser de extension FuncionScript fs";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La expresion debe ser de tipo CADENA";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        
        return this;
    }
    
    public void obtener_funcion(LinkedList<Object> elementos)
    {
        for (Object obj : elementos) {
            if(obj instanceof Funcion)
            {
                Funcion tmp = (Funcion)obj;
                tmp.execute(Constante.global_fun);
            }
        }
    }
}
