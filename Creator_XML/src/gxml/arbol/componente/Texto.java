/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.componente;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import gxml.arbol.Ejecutar;
import gxml.arbol.Elemento;
import gxml.arbol.contenedor.Contenedor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JLabel;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Texto extends JLabel implements Ejecutar {
    public LinkedList<Elemento> elementos;
    public String valor;
    public boolean isContenedor;
    
    public JLabel label;
    
    public Elemento nombre;
    public Elemento x;
    public Elemento y;
    
    public Elemento fuente;
    public Elemento tam;
    public Elemento color;
    public Elemento negrita;
    public Elemento cursiva;
    
    public String cadena;
    public String cad_funcion;
    public Contenedor contenedor;
    public int line, colm;
    
    public Texto(LinkedList<Elemento> elementos, String valor, int line, int colm){
        this.elementos = elementos;
        this.valor = valor;
        
        this.line = line;
        this.colm = colm;
        this.cadena = "";
        init_elements();
    }

    public Texto(LinkedList<Elemento> elementos, int line, int colm){
        this.elementos = elementos;
        this.valor = "";

        this.line = line;
        this.colm = colm;
        this.cadena = "";
        init_elements();
    }

    public Texto() {
        this.valor = "";
        init_elements();
    }

    private void init_elements(){
        this.nombre = null;
        this.x = null;
        this.y = null;
        this.fuente = null;
        this.tam = null;
        this.color = null;
        this.negrita = null;
        this.cursiva = null;
    }

    @Override
    public String generarFs(Entorno ent) {
        /*validar elementos obligatorios*/
        boolean aux_paso = validarElementos(ent);
        
        if(nombre == null){
                String msg = "El elemento \"nombre\" falta en la etiqueta \"texto\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(x == null && isContenedor){
                String msg = "El elemento \"x\" falta en la etiqueta \"texto\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(y == null && isContenedor){
                String msg = "El elemento \"y\" falta en la etiqueta \"texto\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(aux_paso){
            String n = nombre.valor + "_" + contenedor.ventana.id.valor;
            Simbolo s = ent.getSimbolo_EntActual(n.toLowerCase());
            
            if ( s == null ){
                fuente = (fuente == null)? new Elemento("fuente", Tipo.CADENA, Constante.FUENTE, line, colm): fuente;
                tam = (tam == null)? new Elemento("tam", Tipo.ENTERO, Constante.TAM, line, colm): tam;
                color = (color == null)? new Elemento("color", Tipo.CADENA, Constante.COLOR_NEGRO, line, colm): color;
                negrita = (negrita == null)? new Elemento("negrita", Tipo.BOOLEANO, Constante.NEGRITA, line, colm): negrita;
                cursiva = (cursiva == null)? new Elemento("cursiva", Tipo.BOOLEANO, Constante.CURSIVA, line, colm): cursiva;
                
                if(isContenedor){
                    /*traduciendo el texto*/
                    cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + ".crearTexto( ";
                    cadena += "\"" + fuente.valor + "\"" + ", ";
                    cadena += tam.valor + ", ";
                    cadena += "\"" + color.valor + "\"" + ", ";
                    cadena += x.valor + ", ";
                    cadena += y.valor + ", ";
                    cadena += negrita.valor + ", ";
                    cadena += cursiva.valor + ", ";
                    cadena += "\"" + valor + "\" );\n";
                }
                
                /*agregando el texto a la tabla de simbolos*/
                s = new Simbolo(n.toLowerCase(), Tipo.TEXTO, this);
                ent.agregar(n.toLowerCase(), s);
            }
            else {
                //error que ya existe el texto
                String msg = "El texto \"" + nombre.valor + "\" ya existe en el ambito actual";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        
        return cadena;
    }

    public Object generarTexto(Entorno ent)
    {
        validarElementos(ent);
        
        try{
            String nfuente = fuente.valor;
            double ntam = Double.parseDouble(tam.valor);
            String ncolor = color.valor;
            double nx = Double.parseDouble(x.valor);
            double ny = Double.parseDouble(y.valor);
            boolean nnegrita = (Operacion.getBooleano(negrita.valor) == 1);
            boolean ncursiva = (Operacion.getBooleano(cursiva.valor) == 1);
            
            label = new JLabel(valor);
            
            if(nnegrita && ncursiva)
            {
                label.setFont(new Font(nfuente, Font.BOLD | Font.ITALIC, (int)ntam));
            }
            else if(nnegrita)
            {
                label.setFont(new Font(nfuente, Font.BOLD, (int)ntam));
            }
            else if(ncursiva)
            {
                label.setFont(new Font(nfuente, Font.ITALIC, (int)ntam));
            }
            else
            {
                label.setFont(new Font(nfuente, Font.PLAIN, (int)ntam));
            }
            
            Dimension ssize = label.getPreferredSize();
            label.setBounds((int)nx, (int)ny, ssize.width, ssize.height);
            
            label.setForeground(Color.decode(ncolor));
            
        }catch(Exception ex) {  }
        
        return null;
    }
    
    @Override
    public boolean validarElementos(Entorno ent) {
        /*elementos obligatorios*/
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
        
        if (isContenedor){
            return (nombre != null && x != null && y != null);
        }
        else{
            return (nombre != null);
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
                ent.agregar(nombre.valor.toLowerCase(), new Simbolo(nombre.valor.toLowerCase(), Tipo.TEXTO, this));
                return this;
            }
            
        }
        
//        if(aux_paso){
//            String n = nombre.valor + "_" + contenedor.ventana.id.valor;
//            Simbolo s = ent.getSimbolo_EntActual(n.toLowerCase());
//            
//            if ( s == null ){
//                fuente = (fuente == null)? new Elemento("fuente", Tipo.CADENA, Constante.FUENTE, line, colm): fuente;
//                tam = (tam == null)? new Elemento("tam", Tipo.ENTERO, Constante.TAM, line, colm): tam;
//                color = (color == null)? new Elemento("color", Tipo.CADENA, Constante.COLOR_NEGRO, line, colm): color;
//                negrita = (negrita == null)? new Elemento("negrita", Tipo.BOOLEANO, Constante.NEGRITA, line, colm): negrita;
//                cursiva = (cursiva == null)? new Elemento("cursiva", Tipo.BOOLEANO, Constante.CURSIVA, line, colm): cursiva;
//                
//                String nfuente = fuente.valor;
//                double tamanio = Double.parseDouble(tam.valor);
//                double xx = Integer.parseInt(x.valor);
//                double yy = Integer.parseInt(y.valor);
//                String ncolor = color.valor;
//                
//                if(Operacion.getBooleano(negrita.valor) == 1 && Operacion.getBooleano(cursiva.valor) == 1)
//                {
//                    this.setFont(new Font(nfuente, Font.BOLD | Font.ITALIC, (int)tamanio));
//                }
//                else if (Operacion.getBooleano(negrita.valor) == 1)
//                {
//                    this.setFont(new Font(nfuente, Font.BOLD, (int)tamanio));
//                }
//                else
//                {
//                    this.setFont(new Font(nfuente, Font.ITALIC, (int)tamanio));
//                }
//                
//                this.setForeground(Color.decode(ncolor));
//                this.setBounds((int)xx, (int)yy, WIDTH, HEIGHT);
//                this.setText(valor);
//                
//                /*agregando el texto a la tabla de simbolos*/
//                s = new Simbolo(n.toLowerCase(), Tipo.TEXTO, this);
//                ent.agregar(n.toLowerCase(), s);
//            }
//        }
        
        return Constante.NULO;
    }
}
