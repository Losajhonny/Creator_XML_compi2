/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.componente;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import gxml.arbol.Ejecutar;
import gxml.arbol.Elemento;
import gxml.arbol.contenedor.Contenedor;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Multimedia implements Ejecutar {
    public LinkedList<Elemento> elementos;
    
    
    public Elemento path;
    public Elemento tipo;
    public Elemento nombre;
    public Elemento x;
    public Elemento y;
    
    public Elemento alto;
    public Elemento ancho;
    public Elemento auto_reproduccion;
    
    public String cadena;
    public String cad_funcion;
    public Contenedor contenedor;
    public int line, colm;
    
    public Multimedia(LinkedList<Elemento> elementos, int line, int colm){
        this.elementos = elementos;
        
        this.path = null;
        this.tipo = null;
        this.nombre = null;
        this.x = null;
        this.y = null;
        this.alto = null;
        this.ancho = null;
        this.auto_reproduccion = null;
        
        this.line = line;
        this.colm = colm;
        this.cadena = "";
    }

    @Override
    public String generarFs(Entorno ent) {
        /*validar elementos obligatorios*/
        boolean auxpaso = validarElementos(ent);

        if(path == null){
                String msg = "El elemento \"path\" falta en la etiqueta \"multimedia\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(tipo == null){
                String msg = "El elemento \"tipo\" falta en la etiqueta \"multimedia\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(nombre == null){
                String msg = "El elemento \"nombre\" falta en la etiqueta \"multimedia\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(x == null){
                String msg = "El elemento \"x\" falta en la etiqueta \"multimedia\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(y == null){
                String msg = "El elemento \"y\" falta en la etiqueta \"multimedia\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(auxpaso){
            /*validar si ya existe en el entorno*/
            String n = nombre.valor + "_" + contenedor.ventana.id.valor;
            Simbolo s = ent.getSimbolo_EntActual(n.toLowerCase());
            
            if (s == null){
                String aux_alto = (alto != null)? alto.valor: Constante.ALTO_MULTIMEDIA;
                String aux_ancho = (ancho != null)? ancho.valor: Constante.ANCHO_MULTIMEDIA;
                String aux_auto = (auto_reproduccion != null)? auto_reproduccion.valor: Constante.AUTO_REPRODUCCION;
                
                switch (tipo.valor.toLowerCase()){
                    case "musica":
                        generarMusica(path.valor, x.valor, y.valor, aux_auto, aux_alto, aux_ancho);
                        break;
                    case "video":
                        generarVideo(path.valor, x.valor, y.valor, aux_auto, aux_alto, aux_ancho);
                        break;
                    case "imagen":
                        generarImagen(path.valor, x.valor, y.valor, aux_alto, aux_ancho);
                        break;
                    default:
                        //error de tipo de musica
                        String msg = "El tipo " + "\"" + tipo.valor + "\" de la etiqueta multimedia \"" + nombre.valor + "\" no es valido";
                        otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
                        break;
                }

                /*agregar multimedia a la tabla de simbolos*/
                s = new Simbolo(n.toLowerCase(), Tipo.MULTIMEDIA, this);
                ent.agregar(n.toLowerCase(), s);
            }
            else{
                //error que ya existe el texto
                        String msg = "La etiqueta multimedia \"" + nombre.valor + "\" ya existe en el ambito actual";
                        otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
            }
        }
        
        return cadena;
    }

    @Override
    public boolean validarElementos(Entorno ent) {
        for (Elemento elemento : elementos) {
            switch (elemento.id.toLowerCase()) {
                case "path":
                    path = elemento;
                    break;
                case "tipo":
                    tipo = elemento;
                    break;
                case "nombre":
                    nombre = elemento;
                    break;
                case "x":
                    x = elemento;
                    break;
                case "y":
                    y = elemento;
                    break;
                    
                        case "alto":
                            alto = elemento;
                            break;
                        case "ancho":
                            ancho = elemento;
                            break;
                        case "auto-reproduccion":
                            auto_reproduccion = elemento;
                            break;
                        default:
                            break;
            }
        }
        
        return (path != null && tipo != null && nombre != null && x != null && y != null);
    }

    public void generarImagen(String ruta, String x, String y, String alto, String ancho){
        cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + 
                ".crearImagen( \"" + ruta + "\", " + x + ", " + y + ", " + alto + ", " + ancho + " ) ;\n";
    }
    
    public void generarVideo(String ruta, String x, String y, String auto, String alto, String ancho){
        cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + 
                ".crearVideo( \"" + ruta + "\", " + x + ", " + y + ", " + auto + ", " + alto + ", " + ancho + " ) ;\n";
    }
    
    public void generarMusica(String ruta, String x, String y, String auto, String alto, String ancho){
        cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + 
                ".crearReproductor( \"" + ruta + "\", " + x + ", " + y + ", " + auto + ", " + alto + ", " + ancho + " ) ;\n";
    }

    @Override
    public Object getStruct(Entorno ent) {
        
        boolean paso = validarElementos(ent);
        
        if(paso)
        {
            Simbolo s = ent.getSimbolo_Ent(nombre.valor.toLowerCase());
            
            if(s == null)
            {
                ent.agregar(nombre.valor.toLowerCase(), new Simbolo(nombre.valor.toLowerCase(), Tipo.MULTIMEDIA, this));
                return this;
            }
            
        }
        
        return Constante.NULO;
    }
}
