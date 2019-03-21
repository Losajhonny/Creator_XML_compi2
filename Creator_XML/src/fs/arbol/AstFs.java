/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Variable;
import fs.arbol.Instruccion.Declaracion.Declaracion;
import fs.arbol.Instruccion.Detener;
import fs.arbol.Instruccion.Funcion.CallFuncion;
import fs.arbol.Instruccion.Funcion.Funcion;
import fs.arbol.Instruccion.FuncionInterfaz.CrearArrayDesdeArchivo;
import fs.arbol.Instruccion.FuncionInterfaz.CrearVentana;
import fs.arbol.Instruccion.FuncionInterfaz.LeerGxml;
import fs.arbol.Instruccion.FuncionInterfaz.Variable_Funcion;
import fs.arbol.Instruccion.Instruccion;
import fs.arbol.Instruccion.Retornar;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class AstFs implements Instruccion {
    
    public LinkedList<Importacion> importaciones;
    public LinkedList<Object> elementos;
    
    public AstFs (LinkedList<Importacion> importaciones, LinkedList<Object> elementos)
    {
        this.importaciones = importaciones;
        this.elementos = elementos;
    }
    
    public AstFs (LinkedList<Object> elementos)
    {
        this.importaciones = new LinkedList<>();
        this.elementos = elementos;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        
        /*PRIMERO DEBO DE GUARDAR LAS FUNCIONES Y DECLARACIONES*/
        obtener_funcion();
        
        /*DESPUES DEBO DE IMPORTAR LAS OTRAS FUNCIONES*/
        execute_imports();
        
        //obtener_declaracion();
        
        /*DEBO DE EJECUTAR LAS INSTRUCCIONES*/
        
        for (Object obj : elementos) {
            if(!(obj instanceof Funcion || obj instanceof CallFuncion || obj instanceof Variable
                    || obj instanceof Variable_Funcion || obj instanceof LeerGxml/*|| obj instanceof Declaracion*/))
            {
                Instruccion tmp = (Instruccion)obj;
                
                Object aux = tmp.ejecutar(Constante.global_dec);
                if(aux instanceof Detener || aux instanceof Retornar)
                {
                    Detener tmpd = (aux instanceof Detener)? (Detener)aux:null;
                    Retornar tmpr = (aux instanceof Retornar)? (Retornar)aux:null;
                    String msg = "No se permite las Sentencias DETENER y RETORNAR en el ambito actual";
                    otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", Constante.global_dec.ambito, msg, Constante.archivo,
                            (tmpd != null)? tmpd.line: tmpr.line, (tmpd != null)? tmpd.colm: tmpr.colm);
                    otros.Error.agregarError(err);
                }
            }
            else if (obj instanceof CallFuncion)
            {
                CallFuncion exp = (CallFuncion)obj;
                exp.evaluar(Constante.global_dec);
            }
            else if (obj instanceof Variable_Funcion)
            {
                Variable_Funcion varf = (Variable_Funcion) obj;
                
                if(!(varf.objeto instanceof CrearArrayDesdeArchivo ||
                        varf.objeto instanceof CrearVentana))
                {
                    Expresion exp = (Expresion) obj;
                    exp.evaluar(ent);
                }
                else
                {
                    if(varf.objeto instanceof CrearVentana)
                    {
                        CrearVentana l = (CrearVentana) varf.objeto;
                        /*error*/
                        otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito,
                                "No se esperaba la funcion CrearVentana", Constante.archivo, l.line, l.colm);
                        otros.Error.agregarError(err);
                    }
                    else if(varf.objeto instanceof CrearArrayDesdeArchivo)
                    {
                        CrearArrayDesdeArchivo l = (CrearArrayDesdeArchivo) varf.objeto;
                        /*error*/
                        otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito,
                                "No se esperaba la funcion CrearArrayDesdeArchivo", Constante.archivo, l.line, l.colm);
                        otros.Error.agregarError(err);
                    }
                }
                
            }
            else if (obj instanceof LeerGxml)
            {
                LeerGxml l = (LeerGxml) obj;
                /*error*/
                otros.Error err = new otros.Error(Constante.FS, Constante.SINTACTICO, "", ent.ambito,
                        "No se esperaba la funcion LeerGxml", Constante.archivo, l.line, l.colm);
                otros.Error.agregarError(err);
            }
        }
        
        return null;
    }
    
    public void execute_imports()
    {
        for (Importacion imp : importaciones) {
            imp.ejecutar(Constante.global_dec);
        }
    }
    
    public void obtener_declaracion()
    {
        for (Object obj : elementos) {
            if(obj instanceof Declaracion)
            {
                Declaracion tmp = (Declaracion)obj;
                tmp.ejecutar(Constante.global_dec);
            }
        }
    }
    
    public void obtener_funcion()
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
