/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionInterfaz;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Instruccion;
import gxml.arbol.AstGxml;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class LeerGxml extends FunNativaInterfaz implements Expresion, Instruccion {
    
    public Expresion exp;
    
    public LeerGxml(Expresion exp, int line, int colm)
    {
        super(line, colm);
        this.exp = exp;
    }

    @Override
    public Object evaluar(Entorno ent) {
        
        Literal res = (Literal) exp.evaluar(ent);
        
        if (res.tipo == Tipo.CADENA)
        {
            String ruta = String.valueOf(res.valor);
            
            if(ruta.toLowerCase().endsWith(".gxml"))
            {
                String archivo = Constante.archivo;
                Constante.archivo = ruta;
                String entrada = creator_xml.Creator_XML.leerArchivo(Constante.path_relativa + ruta);
                Constante.archivo = archivo;
                
                AstGxml Syntaxtree = AstGxml.analizarGXML(entrada);
                
                
                Entorno local_gxml = new Entorno(null);
                Object obj = Syntaxtree.getStruct(local_gxml);
                
                
                return new Literal(Tipo.STRUCT, obj, line, colm);
            }
            else
            {
                /*El archivo debe ser en LENGUAJE GXML*/
                String msg = "El archivo \'"+ruta+"\' debe ser de EXTENSION GXML";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            /*ERRO DEBE SER DE TIPO CADENA*/
            String msg = "El tipo de dato esperado es una CADENA";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
        }
        return new Literal(Tipo.UNDEFINED, Constante.NULO, line, colm);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        return this;
    }
}
