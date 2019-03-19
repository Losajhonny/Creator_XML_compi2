/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.componente;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Expresion.Variable;
import fs.arbol.Instruccion.Funcion.CallFuncion;
import gxml.arbol.Ejecutar;
import gxml.arbol.Elemento;
import gxml.arbol.contenedor.Contenedor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Boton extends JButton implements Ejecutar {
    public LinkedList<Elemento> elementos;      //lista de elementos de la etiqueta
    public Texto texto;                         //componente texto para colocarlo en el boton
    public String valor;                        //valor para colocarlo en el boton
    
    public JButton boton;
    public Variable ident_vent;           /*ES UN IDENTIFICADOR DEL ID DE REFERENCIA*/
    public CallFuncion reference;        /*ES UNA LLAMADA A UNA REFERENCIA*/
    public CallFuncion click;        /*ES UNA LLAMADA AL CLICK*/
    
    public Elemento nombre;
    public Elemento x;
    public Elemento y;
    
    public Elemento alto;
    public Elemento ancho;
    public Elemento accion;
    public Elemento referencia;
    
    
    public Elemento fuente;
    public Elemento tam;
    public Elemento color;
    public Elemento negrita;
    public Elemento cursiva;
    
    public String cadena;
    public String cad_funcion;
    public Contenedor contenedor;
    public int line, colm;
    
    public Boton(LinkedList<Elemento> elementos, String valor, int line, int colm){
        this.elementos = elementos;
        this.valor = valor;
        this.texto = null;
        
        this.line = line;
        this.colm = colm;
        this.cadena = "";
        this.cad_funcion = "";
        init_elements();
    }
    
    public Boton(LinkedList<Elemento> elementos, Texto texto, int line, int colm){
        this.elementos = elementos;
        this.texto = texto;
        this.valor = "";
        
        this.line = line;
        this.colm = colm;
        this.cadena = "";
        this.cad_funcion = "";
        init_elements();
    }
    
    private void init_elements(){
        this.nombre = null;
        this.x = null;
        this.y = null;
        this.alto = null;
        this.ancho = null;
        this.referencia = null;
        this.accion = null;
    }

    @Override
    public String generarFs(Entorno ent) {
        /*validar elementos obligatorios*/
        boolean auxpaso = validarElementos(ent);
        
        if(nombre == null){
                String msg = "El elemento \"nombre\" falta en la etiqueta \"boton\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(x == null){
                String msg = "El elemento \"x\" falta en la etiqueta \"boton\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(y == null){
                String msg = "El elemento \"y\" falta en la etiqueta \"boton\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(auxpaso){
            /*validar si existe el boton en el contenedor*/
            String n = nombre.valor + "_" + contenedor.ventana.id.valor;
            Simbolo s = ent.getSimbolo_EntActual(n.toLowerCase());
            
            if (s == null){
                if(texto != null) { 
                    texto.contenedor = contenedor;      //heredando el contenedor donde esta la etiqueta
                    texto.isContenedor = false;         //solo se permite el nombre de la etiqueta
                    texto.generarFs(ent);               //en este caso no nesecito la traduccion del texto
                }
                
                /*extrayendo informacion de los elementos*/
                String aux_alto = (alto != null)? alto.valor : Constante.ALTO_BOTON;
                String aux_ancho = (ancho != null)? ancho.valor : Constante.ANCHO_BOTON;
                String aux_referencia = (referencia != null)? referencia.valor : Constante.NULO;
                String aux_accion = (accion != null)? accion.valor : null;
                String aux_fuente = ( texto != null)? texto.fuente.valor : Constante.FUENTE;
                String aux_tam = (texto != null)? texto.tam.valor : Constante.TAM;
                String aux_color = (texto != null)? texto.color.valor : Constante.COLOR_NEGRO;
                
                /*traduciendo el boton*/
                cadena += "var " + n + " = " + contenedor.id.valor + "_" + contenedor.ventana.id.valor + ".crearBoton( ";
                cadena += "\""  + aux_fuente + "\"" + ", ";
                cadena += aux_tam + ", ";
                cadena += "\"" + aux_color + "\"" + ", ";
                cadena += x.valor + ", ";
                cadena += y.valor + ", ";
                cadena += (referencia != null)? "Cargar_ventana_" + aux_referencia + "(), ": aux_referencia + ", ";
                cadena += "\"" + ((texto != null)? texto.valor: valor) + "\", ";
                cadena += aux_alto + ", ";
                cadena += aux_ancho + " ) ;\n";
                
                /*traduciendo la accion*/
                cadena += (aux_accion != null)? n + ".alClic( " + aux_accion + " ) ;\n":"";
                
                if(referencia != null){
                    s = Constante.global_gxm.getSimbolo_Ent(("Cargar_ventana_" + aux_referencia).toLowerCase());
                    
                    if(s == null){
                        cad_funcion += "\nfuncion Cargar_ventana_" + aux_referencia + "() {\n";
                        cad_funcion += "\t" + aux_referencia + ".alCargar() ;\n";
                        cad_funcion += "}\n";
                        
                        s = new Simbolo(("Cargar_ventana_" + aux_referencia).toLowerCase(), "", "");
                        Constante.global_gxm.agregar(("Cargar_ventana_" + aux_referencia).toLowerCase(), s);
                    }
                }
                
                /*agregando el boton a la tabla de simbolos*/
                s = new Simbolo(n.toLowerCase(), Tipo.BOTON, this);
                ent.agregar(n.toLowerCase(), s);
            }
            else{
                //error que ya existe el boton
                String msg = "El boton \"" + nombre.valor + "\" ya existe en el ambito actual";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        
        return cadena;
    }

    @Override
    public boolean validarElementos(Entorno ent) {
        /*validando elementos obligatorios*/
        for (Elemento elemento : elementos) {
            switch (elemento.id.toLowerCase()) {
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
                        case "referencia":
                            referencia = elemento;
                            break;
                        case "accion":
                            accion = elemento;
                            break;
                        case "fuente":
                            fuente = elemento;
                            break;
                        case "tam":
                            tam = elemento;
                            break;
                        case "color":
                            color = elemento;
                            break;
                        case "negrita":
                            negrita = elemento;
                            break;
                        case "cursiva":
                            cursiva = elemento;
                            break;
                        default:
                            break;
            }
        }
        
        return (nombre != null && x != null && y != null);
    }
    
    public Object generarBoton(Entorno ent)
    {
        validarElementos(ent);
        
        try{
            String nfuente = (fuente != null)? fuente.valor: Constante.FUENTE;
            String nntam = (tam != null)? tam.valor: Constante.TAM;
            String ncolor = (color != null)? color.valor: Constante.COLOR_NEGRO;
            String nnalto = (alto != null)? alto.valor: Constante.ALTO_BOTON;
            String nnancho = (ancho != null)? ancho.valor: Constante.ANCHO_BOTON;
            
            double ntam = Double.parseDouble(nntam);
            double nx = Double.parseDouble(x.valor);
            double ny = Double.parseDouble(y.valor);
            double nalto = Double.parseDouble(nnalto);
            double nancho = Double.parseDouble(nnancho);
            
            boton = new JButton(valor);
            boton.setFont(new Font(nfuente, Font.PLAIN, (int)ntam));
            boton.setBounds((int)nx, (int)ny, (int)nancho, (int)nalto);
            boton.setForeground(Color.decode(ncolor));
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    event_boton(ent);
                }
            });
            return this;
        }catch(Exception ex) {  }
        
        return Constante.NULO;
    }
    
    public void event_boton(Entorno ent)
    {
        /*ejecutar al click o referencia*/
        
        if(click != null)
        {   //EVENTO AL CLICK
            click.ejecutar(ent);
        }
        
        if(reference != null)
        {   //EVENTO REFERENCIA
            reference.ejecutar(ent);
        }
        else if (ident_vent != null)
        {
            
        }
    }
    
    @Override
    public Object getStruct(Entorno ent) {
        
        boolean paso = validarElementos(ent);
        
        if(paso)
        {
            Simbolo s = ent.getSimbolo_Ent(nombre.valor.toLowerCase());
            
            if(s == null)
            {
                ent.agregar(nombre.valor.toLowerCase(), new Simbolo(nombre.valor.toLowerCase(), Tipo.BOTON, this));
                return this;
            }
            
        }
        
        return Constante.NULO;
    }
}
