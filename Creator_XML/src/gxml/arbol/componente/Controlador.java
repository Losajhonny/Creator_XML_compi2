/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.componente;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Declaracion.Arreglo;
import gxml.arbol.Ejecutar;
import gxml.arbol.Elemento;
import gxml.arbol.contenedor.Contenedor;
import gxml.arbol.especial.Defecto;
import gxml.arbol.especial.Especial;
import gxml.arbol.especial.ListaDato;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Controlador implements Ejecutar {
    public LinkedList<Elemento> elementos;
    public LinkedList<Especial> especiales;
    
    public JTextField cajaTexto;
    
    public JScrollPane jareaTexto;
    public JTextArea areaTexto;
    
    public JComboBox desplegable;
    public Literal arreglo;
    
    public JSpinner numerico;
    
    public String defecto = "";
    
    public Elemento tipo;
    public Elemento nombre;
    public Elemento x;
    public Elemento y;
    
    public Elemento alto;
    public Elemento ancho;
    public Elemento fuente;
    public Elemento tam;
    public Elemento color;
    public Elemento negrita;
    public Elemento cursiva;
    public Elemento maximo;
    public Elemento minimo;
    public Elemento accion;
    
    public String cadena;
    public String cad_funcion;
    public Contenedor contenedor;
    public int line, colm;
    
    public Controlador(LinkedList<Elemento> elementos, LinkedList<Especial> especiales, int line, int colm){
        this.elementos = elementos;
        this.especiales = especiales;
        
        init_elements();
        this.line = line;
        this.colm = colm;
    }
    
    public Controlador(LinkedList<Elemento> elementos, int line, int colm){
        this.elementos = elementos;
        this.especiales = null;
        
        init_elements();
        this.line = line;
        this.colm = colm;
    }
    
    private void init_elements(){
        this.tipo = null;
        this.nombre = null;
        this.x = null;
        this.y = null;
        this.alto = null;
        this.ancho = null;
        this.fuente = null;
        this.tam = null;
        this.color = null;
        this.negrita = null;
        this.cursiva = null;
        this.maximo = null;
        this.minimo = null;
        this.accion = null;
        this.cadena = "";
        this.cad_funcion = "";
    }

    @Override
    public String generarFs(Entorno ent) {
        boolean auxpaso = validarElementos(ent);
        
        if(tipo == null){
                String msg = "El elemento \"tipo\" falta en la etiqueta \"control\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(nombre == null){
                String msg = "El elemento \"nombre\" falta en la etiqueta \"control\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(x == null){
                String msg = "El elemento \"x\" falta en la etiqueta \"control\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(y == null){
                String msg = "El elemento \"y\" falta en la etiqueta \"control\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(auxpaso){
            /*validar si ya existe el control en la tabla de simbolos*/
            String n = nombre.valor + "_" + contenedor.ventana.id.valor;
            Simbolo s = ent.getSimbolo_EntActual(n.toLowerCase());
            
            if (s == null){
                /*extreaer los valores de las etiquetas opcionales*/
                String aux_alto = (alto != null)? alto.valor: Constante.ALTO_CONTROL;
                String aux_ancho = (ancho != null)? ancho.valor: Constante.ANCHO_CONTROL;
                String aux_fuente = (fuente != null)? fuente.valor: Constante.FUENTE;
                String aux_tam = (tam != null)? tam.valor: Constante.TAM;
                String aux_color = (color != null)? color.valor: Constante.COLOR_NEGRO;
                String aux_negrita = (negrita != null)? negrita.valor: Constante.NEGRITA;
                String aux_cursiva = (cursiva != null)? cursiva.valor: Constante.CURSIVA;
                String aux_maximo = (maximo != null)? maximo.valor: Constante.NULO;
                String aux_minimo = (minimo != null)? minimo.valor: Constante.NULO;
                
                /*realizando la traduccion segun el tipo de la etiqueta*/
                switch (tipo.valor.toLowerCase()){
                    case "texto":
                        generarCajaTexto(aux_alto, aux_ancho, aux_fuente, aux_tam, aux_color, aux_negrita, aux_cursiva);
                        break;
                    case "numerico":
                        generarNumerico(aux_alto, aux_ancho, aux_maximo, aux_minimo);
                        break;
                    case "textoarea":
                        generarAreaTexto(aux_alto, aux_ancho, aux_fuente, aux_tam, aux_color, aux_negrita, aux_cursiva);
                        break;
                    case "desplegable":
                        generarDesplegable(aux_alto, aux_ancho);
                        break;
                    default:
                        //error del tipo del control no permitido
                        break;
                }
                
                /*agregando el texto a la tabla de simbolos*/
                s = new Simbolo(n.toLowerCase(), Tipo.CONTROLADOR, this);
                ent.agregar(n.toLowerCase(), s);
            }
            else{
                String msg = "El controlador \"" + nombre.valor + "\" ya existe en el ambito actual";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        
        return cadena;
    }

    @Override
    public boolean validarElementos(Entorno ent) {
        /*obteniendo obligatorios*/
        for (Elemento elemento : elementos) {
            switch(elemento.id.toLowerCase()){
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
                case "maximo":
                    maximo = elemento;
                    break;
                case "minimo":
                    minimo = elemento;
                    break;
                case "accion":
                    accion = elemento;
                    break;
                default:
                    break;
            }
        }
        
        return (tipo != null && nombre != null && x != null && y != null);
    }
    
    public Object generarCajaTexto(Entorno ent)
    {
        validarElementos(null);
        
        try{
            String nnalto = (alto != null)? alto.valor: Constante.ALTO_CONTROL;
            String nnancho = (ancho != null)? ancho.valor: Constante.ANCHO_CONTROL;
            String nnfuente = (fuente != null)? fuente.valor: Constante.FUENTE;
            String nntam = (tam != null)? tam.valor: Constante.TAM;
            String nncolor = (color != null)? color.valor: Constante.COLOR_NEGRO;
            String nnnegrita = (negrita != null)? negrita.valor: Constante.NEGRITA;
            String nncursiva = (cursiva != null)? cursiva.valor: Constante.CURSIVA;
            
            double nalto = Double.parseDouble(nnalto);
            double nancho = Double.parseDouble(nnancho);
            double ntam = Double.parseDouble(nntam);
            double nx = Double.parseDouble(x.valor);
            double ny = Double.parseDouble(y.valor);
            boolean nnegrita = (Operacion.getBooleano(nnnegrita) == 1);
            boolean ncursiva = (Operacion.getBooleano(nncursiva) == 1);
            
            cajaTexto = new JTextField(defecto);
            
            if(nnegrita && ncursiva)
            {
                cajaTexto.setFont(new Font(nnfuente, Font.BOLD | Font.ITALIC, (int)ntam));
            }
            else if(nnegrita)
            {
                cajaTexto.setFont(new Font(nnfuente, Font.BOLD, (int)ntam));
            }
            else if(ncursiva)
            {
                cajaTexto.setFont(new Font(nnfuente, Font.ITALIC, (int)ntam));
            }
            else
            {
                cajaTexto.setFont(new Font(nnfuente, Font.PLAIN, (int)ntam));
            }
            cajaTexto.setBounds((int)nx, (int)ny, (int)nancho, (int)nalto);
            cajaTexto.setForeground(Color.decode(nncolor));
            
        }catch(Exception ex){}
        return null;
    }
    
    public void generarCajaTexto(String alto, String ancho, String fuente, String tam, String color, String negrita, String cursiva){
        cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + ".crearCajaTexto ( ";
        cadena += alto + ", ";
        cadena += ancho + ", ";
        cadena += "\"" + fuente + "\", ";
        cadena += tam + ", ";
        cadena += "\"" + color + "\", ";
        cadena += x.valor + ", ";
        cadena += y.valor + ", ";
        cadena += negrita + ", ";
        cadena += cursiva + ", ";

        Defecto tmp = null;
        if(especiales != null){
            /*obtener defecto y revisar que no haya listaDato*/
            int cont_listadato = 0;
            
            for ( Especial especial : especiales ) {
                if(especial instanceof Defecto){
                    tmp = (Defecto)especial;
                }
                else if (especial instanceof ListaDato){
                    cont_listadato += 1;
                }
            }
            
            if(cont_listadato != 0){
                String msg = "La etiqueta listaDato no es permitido para el tipo de dato \"texto\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", "", msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        if(tmp != null){
            defecto = tmp.generarFs(null);
            cadena += "\"" + defecto + "\", ";
        }
        else{
            cadena += Constante.NULO + ", ";
        }
        
//        cadena += "\"" + nombre.valor + "_" + contenedor.ventana.id.valor + "\" ) ;\n";
          cadena += "\"" + nombre.valor + "\" ) ;\n";
    }
    
    public Object generarAreaTexto(Entorno ent)
    {
        validarElementos(null);
        
        try{
            String nnalto = (alto != null)? alto.valor: Constante.ALTO_CONTROL;
            String nnancho = (ancho != null)? ancho.valor: Constante.ANCHO_CONTROL;
            String nnfuente = (fuente != null)? fuente.valor: Constante.FUENTE;
            String nntam = (tam != null)? tam.valor: Constante.TAM;
            String nncolor = (color != null)? color.valor: Constante.COLOR_NEGRO;
            String nnnegrita = (negrita != null)? negrita.valor: Constante.NEGRITA;
            String nncursiva = (cursiva != null)? cursiva.valor: Constante.CURSIVA;;
            
            double nalto = Double.parseDouble(nnalto);
            double nancho = Double.parseDouble(nnancho);
            double ntam = Double.parseDouble(nntam);
            double nx = Double.parseDouble(x.valor);
            double ny = Double.parseDouble(y.valor);
            boolean nnegrita = (Operacion.getBooleano(nnnegrita) == 1);
            boolean ncursiva = (Operacion.getBooleano(nncursiva) == 1);
            
            areaTexto = new JTextArea(defecto);
            jareaTexto = new JScrollPane(areaTexto);
            
            if(nnegrita && ncursiva)
            {
                areaTexto.setFont(new Font(nnfuente, Font.BOLD | Font.ITALIC, (int)ntam));
            }
            else if(nnegrita)
            {
                areaTexto.setFont(new Font(nnfuente, Font.BOLD, (int)ntam));
            }
            else if(ncursiva)
            {
                areaTexto.setFont(new Font(nnfuente, Font.ITALIC, (int)ntam));
            }
            else
            {
                areaTexto.setFont(new Font(nnfuente, Font.PLAIN, (int)ntam));
            }
            areaTexto.setBounds((int)nx, (int)ny, (int)nancho, (int)nalto);
            areaTexto.setForeground(Color.decode(nncolor));
            jareaTexto.setBounds((int)nx, (int)ny, (int)nancho, (int)nalto);
        }catch(Exception ex){}
        return null;
    }
    
    public void generarAreaTexto(String alto, String ancho, String fuente, String tam, String color, String negrita, String cursiva){
        cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + ".crearAreaTexto ( ";
        cadena += alto + ", ";
        cadena += ancho + ", ";
        cadena += "\"" + fuente + "\", ";
        cadena += tam + ", ";
        cadena += "\"" + color + "\", ";
        cadena += x.valor + ", ";
        cadena += y.valor + ", ";
        cadena += negrita + ", ";
        cadena += cursiva + ", ";
        Defecto tmp = null;
        if(especiales != null){
            /*obtener defecto y revisar que no haya listaDato*/
            int cont_listadato = 0;
            
            for ( Especial especial : especiales ) {
                if(especial instanceof Defecto){
                    tmp = (Defecto)especial;
                }
                else if (especial instanceof ListaDato){
                    cont_listadato += 1;
                }
            }
            
            if(cont_listadato != 0){
                String msg = "La etiqueta listaDato no es permitido para el tipo de dato \"textoArea\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", "", msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        if(tmp != null){
            defecto = tmp.generarFs(null);
            cadena += "\"" + defecto + "\", ";
        }
        else{
            cadena += Constante.NULO + ", ";
        }
        
//        cadena += "\"" + nombre.valor + "_" + contenedor.ventana.id.valor + "\" ) ;\n";
        cadena += "\"" + nombre.valor + "\" ) ;\n";
    }
    
    public Object generarDesplegable(Entorno ent)
    {
        validarElementos(null);
        
        try{
            String nnalto = (alto != null)? alto.valor: Constante.ALTO_CONTROL;
            String nnancho = (ancho != null)? ancho.valor: Constante.ANCHO_CONTROL;
            String nnfuente = (fuente != null)? fuente.valor: Constante.FUENTE;
            String nntam = (tam != null)? tam.valor: Constante.TAM;
            String nncolor = (color != null)? color.valor: Constante.COLOR_NEGRO;
            
            double nalto = Double.parseDouble(nnalto);
            double nancho = Double.parseDouble(nnancho);
            double ntam = Double.parseDouble(nntam);
            double nx = Double.parseDouble(x.valor);
            double ny = Double.parseDouble(y.valor);
            
            Arreglo arr = (Arreglo) arreglo.valor;
            String[] lista = arr.getValores();
            
            desplegable = new JComboBox(lista);
            desplegable.setFont(new Font(nnfuente, Font.PLAIN, (int)ntam));
            desplegable.setBounds((int)nx, (int)ny, (int)nancho, (int)nalto);
            desplegable.setForeground(Color.decode(nncolor));
            
            if (!defecto.toLowerCase().equals(Constante.NULO.toLowerCase()))
            {
                if(arr.isValor(lista, defecto))
                {
                    desplegable.setSelectedItem(defecto);
                }
            }
            
        }catch(Exception ex){}
        
        return null;
    }
    
    public void generarDesplegable(String alto, String ancho){
        Defecto tmp = null;
        ListaDato ld = null;
        if(especiales != null){
            for ( Especial especial : especiales ) {
                if(especial instanceof Defecto){
                    tmp = (Defecto)especial;
                }
                else if (especial instanceof ListaDato){
                    ld = (ListaDato)especial;
                }
            }
        }
        
        if(ld != null) { ld.control = this; cadena += ld.generarFs(null) + "\n"; }
        
        cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + ".crearDesplegable ( ";
        cadena += alto + ", ";
        cadena += ancho + ", ";
        
        if(ld != null){
            cadena += nombre.valor + "_" + contenedor.ventana.id.valor + " , ";
        }
        else{
            cadena += Constante.NULO + ", ";
        }
        
        cadena += x.valor + ", ";
        cadena += y.valor + ", ";
        if(tmp != null){
            defecto = tmp.generarFs(null);
            cadena += "\"" + defecto + "\", ";
        }
        else{
            cadena += Constante.NULO + ", ";
        }
        cadena += "\"" + nombre.valor + "\" ) ;\n";
//        cadena += "\"" + nombre.valor + "_" + contenedor.ventana.id.valor + "\" ) ;\n";
    }
    
    public Object generarNumerico(Entorno ent)
    {
        validarElementos(null);
        
        try{
            String nnalto = (alto != null)? alto.valor: Constante.ALTO_CONTROL;
            String nnancho = (ancho != null)? ancho.valor: Constante.ANCHO_CONTROL;
            String nnfuente = (fuente != null)? fuente.valor: Constante.FUENTE;
            String nntam = (tam != null)? tam.valor: Constante.TAM;
            String nncolor = (color != null)? color.valor: Constante.COLOR_NEGRO;
            
            double nalto = Double.parseDouble(nnalto);
            double nancho = Double.parseDouble(nnancho);
            double ntam = Double.parseDouble(nntam);
            double nx = Double.parseDouble(x.valor);
            double ny = Double.parseDouble(y.valor);
            
            numerico = new JSpinner();
            numerico.setFont(new Font(nnfuente, Font.PLAIN, (int)ntam));
            numerico.setBounds((int)nx, (int)ny, (int)nancho, (int)nalto);
            numerico.setForeground(Color.decode(nncolor));
            
            SpinnerNumberModel snm = new SpinnerNumberModel();
            snm.setStepSize((double)1.0);
            
            if (!defecto.toLowerCase().equals(Constante.NULO.toLowerCase()))
            {
                snm.setValue(Double.parseDouble(defecto));
            }
            else
            {
                snm.setValue((double)0);
            }
            if (!maximo.valor.toLowerCase().equals(Constante.NULO.toLowerCase()))
            {
                snm.setMaximum(Double.parseDouble(maximo.valor));
            }
            if (!minimo.valor.toLowerCase().equals(Constante.NULO.toLowerCase()))
            {
                snm.setMinimum(Double.parseDouble(minimo.valor));
            }
            
            //numerico = new JSpinner(new SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(-1.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
            numerico.setModel(snm);
        }catch(Exception ex){}
        return null;
    }
    
    public void generarNumerico(String alto, String ancho, String maximo, String minimo){
        cadena += contenedor.id.valor + "_" + contenedor.ventana.id.valor + ".crearControlNumerico ( ";
        cadena += alto + ", ";
        cadena += ancho + ", ";
        cadena += maximo + ", ";
        cadena += minimo + ", ";
        cadena += x.valor + ", ";
        cadena += y.valor + ", ";
        
        Defecto tmp = null;
        if(especiales != null){
            /*obtener defecto y revisar que no haya listaDato*/
            int cont_listadato = 0;
            
            for ( Especial especial : especiales ) {
                if(especial instanceof Defecto){
                    tmp = (Defecto)especial;
                }
                else if (especial instanceof ListaDato){
                    cont_listadato += 1;
                }
            }
            
            if(cont_listadato != 0){
                String msg = "La etiqueta listaDato no es permitido para el tipo de dato \"numerico\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", "", msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        if(tmp != null){
            defecto = tmp.generarFs(null);
            cadena += defecto + ", ";
        }
        else{
            cadena += Constante.NULO + ", ";
        }
        
        cadena += "\"" + nombre.valor + "\" ) ;\n";
//        cadena += "\"" + nombre.valor + "_" + contenedor.ventana.id.valor + "\" ) ;\n";
    }

    @Override
    public Object getStruct(Entorno ent) {
        
        boolean paso = validarElementos(ent);
        
        if(paso)
        {
            Simbolo s = ent.getSimbolo_Ent(nombre.valor.toLowerCase());
            
            if(s == null)
            {
                ent.agregar(nombre.valor.toLowerCase(), new Simbolo(nombre.valor.toLowerCase(), Tipo.CONTROLADOR, this));
                return this;
            }
            
        }
        
        return Constante.NULO;
    }
}
