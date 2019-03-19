/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.contenedor;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import gxml.arbol.Ejecutar;
import gxml.arbol.Elemento;
import gxml.arbol.componente.Boton;
import gxml.arbol.componente.Controlador;
import gxml.arbol.componente.Enviar;
import gxml.arbol.componente.Multimedia;
import gxml.arbol.componente.Texto;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JPanel;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Contenedor extends JPanel implements Ejecutar {
    public LinkedList<Elemento> elementos;
    public LinkedList<Ejecutar> componentes;
    
    public JPanel panel;
    
    public Elemento id;
    public Elemento x;
    public Elemento y;
    public Elemento alto;
    public Elemento ancho;
    public Elemento color;
    public Elemento borde;
    
    public Ventana ventana;
    public String cadena;
    public String cad_fun;
    public int line, colm;
    
    public Contenedor(LinkedList<Elemento> elementos, LinkedList<Ejecutar> componentes, int line, int colm){
        this.elementos = elementos;
        this.componentes = componentes;
        this.id = null;
        this.x = null;
        this.y = null;
        this.alto = null;
        this.ancho = null;
        this.color = null;
        this.borde = null;
        
        this.cadena = "";
        this.line = line;
        this.colm = colm;
        this.cad_fun = "";
    }
    
    public Contenedor(LinkedList<Elemento> elementos, int line, int colm){
        this.elementos = elementos;
        this.componentes = new LinkedList<>();
        this.id = null;
        this.x = null;
        this.y = null;
        this.alto = null;
        this.ancho = null;
        this.color = null;
        this.borde = null;
        
        this.cadena = "";
        this.line = line;
        this.colm = colm;
        this.cad_fun = "";
    }

    @Override
    public String generarFs(Entorno ent) {
        boolean auxpaso = validarElementos(ent);
        
        if(id == null){
                String msg = "El elemento \"id\" falta en la etiqueta \"contenedor\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(x == null){
                String msg = "El elemento \"x\" falta en la etiqueta \"contenedor\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(y == null){
                String msg = "El elemento \"y\" falta en la etiqueta \"contenedor\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }

        if(auxpaso){
            /*le agregao el nombre de la ventana para diferenciar    contenedor_ventana*/
            String n = id.valor + "_" + ventana.id.valor;
            /*validar si ya existe el contenedor en la tabla de simbolos*/
            Simbolo s = ent.getSimbolo_EntActual(n.toLowerCase());

            if(s == null){
                /*extrayendo la informacion de las etiquetas*/
                String aux_alto = (alto != null)? String.valueOf(alto.valor): Constante.ALTO_CONTENEDOR;
                String aux_ancho= (ancho!= null)? String.valueOf(ancho.valor): Constante.ANCHO_CONTENEDOR;
                String aux_color = (color != null)? String.valueOf(color.valor): (ventana.color != null)? String.valueOf(ventana.color.valor): Constante.COLOR_BLANCO;
                String aux_borde = (borde != null)? String.valueOf(borde.valor): Constante.BORDE;

                //cadena += "//////////////////// creando contenedor \n";
                cadena += "var " + n + " = ";
                cadena += ventana.id.valor + ".crearContenedor( ";
                cadena += aux_alto + ", " + aux_ancho + ", " + "\""+ aux_color + "\", " + aux_borde + ", " + x.valor + ", " + y.valor + " ) ;\n";

                /*extreaer cadena de los componentes*/
                for (Ejecutar componente : componentes) {
                    if(componente instanceof Texto){
                        Texto tmp = (Texto)componente;
                        tmp.contenedor = this;
                        tmp.isContenedor = true;
                        cadena += "\n";
                        cadena += tmp.generarFs(ent);
                    }
                    else if(componente instanceof Enviar){
                        Enviar tmp = (Enviar)componente;
                        tmp.contenedor = this;
                        cadena += "\n";
                        cadena += tmp.generarFs(ent);
                        cad_fun += tmp.cad_funcion;
                    }
                    else if(componente instanceof Boton){
                        Boton tmp = (Boton)componente;
                        tmp.contenedor = this;
                        cadena += "\n";
                        cadena += tmp.generarFs(ent);
                        cad_fun += tmp.cad_funcion;
                    }
                    else if(componente instanceof Multimedia){
                        Multimedia tmp = (Multimedia)componente;
                        tmp.contenedor = this;
                        cadena += "\n";
                        cadena += tmp.generarFs(ent);
                    }
                    else if(componente instanceof Controlador){
                        Controlador tmp = (Controlador)componente;
                        tmp.contenedor = this;
                        cadena += "\n";
                        cadena += tmp.generarFs(ent);
                    }
                }

                /*agregando contenedor a la tabla de simbolos*/
                s = new Simbolo(n.toLowerCase(), Tipo.CONTENEDOR, this);
                ent.agregar(n.toLowerCase(), s);
            }
            else{
                //error que ya existe el contenedor
                String msg = "El contenedor \"" + String.valueOf(id.valor) + "\" ya existe en el ambito actual";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }

        return cadena;
    }

    @Override
    public boolean validarElementos(Entorno ent) {
        for (Elemento elemento : elementos ) {
            switch (elemento.id.toLowerCase()) {
                case "id":
                    id = elemento;
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
                        case "color":
                            color = elemento;
                            break;
                        case "borde":
                            borde = elemento;
                            break;
                        default:
                            break;
            }
        }

        return (id != null && x != null && y != null);
    }

    public Object generarContenedor(Entorno ent)
    {
        validarElementos(null);
        
        try{
            String nnalto = (alto != null)? alto.valor: Constante.ALTO_CONTENEDOR;
            String nnancho = (ancho != null)? ancho.valor: Constante.ANCHO_CONTENEDOR;
            String nnborde = (borde != null)? borde.valor: Constante.BORDE;
            String nncolor = (color != null)? String.valueOf(color.valor): (ventana.color != null)? String.valueOf(ventana.color.valor): Constante.COLOR_BLANCO;

            double nalto = Double.parseDouble(nnalto);
            double nancho = Double.parseDouble(nnancho);
            double nx = Double.parseDouble(x.valor);
            double ny = Double.parseDouble(y.valor);
            boolean nborde = (Operacion.getBooleano(nnborde) == 1);
            
            panel = new JPanel(null);
            panel.setBounds((int)nx, (int)ny, (int)nancho, (int)nalto);
            if(nborde)
            {
                panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
            }
            panel.setBackground(Color.decode(nncolor));
            
            return this;
        }catch(Exception ex){}
        
        return Constante.NULO;
    }
    
    public LinkedList<Object> objetos = new LinkedList<>();
    
    @Override
    public Object getStruct(Entorno ent) {
        
        boolean paso = validarElementos(ent);
        
        if(paso)
        {
            Simbolo s = ent.getSimbolo_Ent(id.valor.toLowerCase());
            
            if(s == null)
            {
                objetos = new LinkedList<>();
        
                for (Ejecutar comp : componentes) {

                    Object aux = comp.getStruct(ent);

                    if(!(aux instanceof String))
                    {
                        objetos.add(aux);
                    }
                }
                
                return this;
            }
        }
        
        return Constante.NULO;
    }
}
