/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxml.arbol.contenedor;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Funcion.CallFuncion;
import gxml.arbol.Ejecutar;
import gxml.arbol.Elemento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Ventana implements Ejecutar {
    
    public LinkedList<Elemento> elementos;
    public LinkedList<Contenedor> contenedores;
    
    public JFrame vent;
    public JScrollPane jsp;
    public JPanel panel;
    
    public boolean isFirst = true;
    
    public CallFuncion cargando;
    public CallFuncion cerrando;
    
    public Elemento id;
    public Elemento tipo;
    public Elemento color;
    public Elemento accionini;
    public Elemento accionfin;
    
    public Elemento ancho;
    public Elemento alto;
    
    public String cadena;
    public String cad_fun;
    
    public int line, colm;
    
    public Ventana(LinkedList<Elemento> elementos, LinkedList<Contenedor> contenedores, int line, int colm){
        this.elementos = elementos;
        this.contenedores = contenedores;
        
        init_elements();
        this.cadena = "";
        this.cad_fun = "";
        this.line = line;
        this.colm = colm;
    }
    
    public Ventana(LinkedList<Elemento> elementos, int line, int colm){
        this.elementos = elementos;
        this.contenedores = new LinkedList<>();
        
        init_elements();
        
        this.cadena = "";
        this.cad_fun = "";
        this.line = line;
        this.colm = colm;
    }
    
    private void init_elements(){
        this.id = null;
        this.tipo = null;
        this.color = null;
        this.accionini = null;
        this.accionfin = null;
        this.alto = null;
        this.ancho = null;
    }

    @Override
    public String generarFs(Entorno ent) {
        boolean auxpaso = validarElementos(ent);
        
        if(id == null && isFirst){
                String msg = "El elemento \"id\" falta en la etiqueta \"ventana\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        
        if(tipo == null && isFirst){
            String msg = "El elemento \"tipo\" falta en la etiqueta \"ventana\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
        }
        else{
            if(!(tipo.valor.toLowerCase().equals("principal") || tipo.valor.toLowerCase().equals("secundaria"))  && isFirst){
                String msg = "El elemento \"tipo\" no permite el valor \""+tipo.valor+"\"";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SINTACTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
                tipo = null;
            }
        }
        
        if(auxpaso){
            /*validar si ya existe la ventana en la tabla de simbolos*/
            Simbolo s = ent.getSimbolo_EntActual(id.valor.toLowerCase());

            if(s == null){
                /*extrayendo la informacion de las etiquetas*/
                String aux_color = (color != null)? color.valor:Constante.COLOR_BLANCO;
                
                cadena += "////////////////////////////////////////////////////////////////\n"
                        + "//                   INICIO " + id.valor + "\n"
                        + "////////////////////////////////////////////////////////////////\n";
                
                cadena += "var " + id.valor + " = " + "crearVentana( \"" + aux_color + "\", ";
                cadena += Constante.ALTO_VENTANA + ", " + Constante.ANCHO_VENTANA  + ", \"" + id.valor + "\") ;\n";
                cadena += (accionini != null)? id.valor + ".alCargar( " + accionini.valor + " ) ;\n": "";
                cadena += (accionfin != null)? id.valor + ".alCerrar( " + accionfin.valor + " ) ;\n": "";
                
                /*operaciones antes de los contenedores*/
                
                /*extreaer cadena de los contenedores*/
                Entorno actual = new Entorno(ent);
                actual.ambito = id.valor;
                
                for (Contenedor contenedor : contenedores) {
                    contenedor.ventana = this;
                    cadena += contenedor.generarFs(actual);
                    cad_fun += contenedor.cad_fun;
                }
                
                /*operaciones despues de los contenedores*/

                cadena += "\n///////////////////////////////////////////////////////////////\n"
                        + "//                 FIN " + id.valor + "\n"
                        + "///////////////////////////////////////////////////////////////\n\n";
                
                /*agregando ventana a la tabla de simbolos*/
                s = new Simbolo(id.valor.toLowerCase(), Tipo.VENTANA, this);
                ent.agregar(id.valor.toLowerCase(), s);
                
            }
            else if ( isFirst ) {
                //error que ya existe la ventana
                String msg = "La ventana \"" + id.valor + "\" ya existe en el ambito global";
                otros.Error err = new otros.Error(Constante.GXML, Constante.SEMANTICO, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        
        return cadena;
    }

    @Override
    public boolean validarElementos(Entorno ent) {
        for (Elemento elemento : elementos) {
            switch(elemento.id.toLowerCase()){
                case "id":
                    id = elemento;
                    break;
                case "tipo":
                    tipo = elemento;
                    break;
                        case "color":
                            color = elemento;
                            break;
                        case "accioninicial":
                            accionini = elemento;
                            break;
                        case "accionfinal":
                            accionfin = elemento;
                            break;
                        case "alto":
                            alto = elemento;
                            break;
                        case "ancho":
                            ancho = elemento;
                            break;
                        default:
                            break;
            }
        }
        
        return (id != null && tipo != null);
    }
    
    public Object generarVentana(Entorno ent)
    {
        validarElementos(null);
        String nalto = (alto != null)? alto.valor: Constante.ALTO_VENTANA;
        String nancho = (ancho != null)? ancho.valor: Constante.ANCHO_VENTANA;
        String ncolor = (color != null)? color.valor: Constante.COLOR_BLANCO;
        try{
            double nnalto = Double.parseDouble(nalto);
            double nnancho = Double.parseDouble(nancho);
            
            vent = new JFrame();
            vent.setTitle(id.valor);
            vent.setPreferredSize(new Dimension((int)nnancho, (int)nnalto));
            
            JPanel contentPane = (JPanel) vent.getContentPane();
            
            panel = new JPanel(null);
            panel.setPreferredSize(new Dimension(1500, 1500));
            panel.setBackground(Color.decode(ncolor));
            
            jsp = new JScrollPane(panel);
            jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jsp.setBounds(0, 0, (int)nnancho, (int)nnalto);
            
            contentPane.add(jsp);
            vent.pack();
            
            vent.addWindowListener(new WindowAdapter() {
                @Override
                public void windowActivated(java.awt.event.WindowEvent evt) {
                    cargar(ent);
                }
                @Override
                public void windowDeactivated(java.awt.event.WindowEvent evt) {
                    cerrar(ent);
                }
            });
            return this;
        }catch(Exception ex){}
        
        return Constante.NULO;
    }
    
    public void cargar(Entorno ent)
    {
        if(cargando != null)
        {
            cargando.ejecutar(ent);
        }
    }
    
    public void cerrar(Entorno ent)
    {
        if(cerrando != null)
        {
            cerrando.ejecutar(ent);
        }
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
        
                for (Contenedor cont : contenedores) {

                    Object aux = cont.getStruct(ent);

                    if(!(aux instanceof String))
                    {
                        Contenedor tmp = (Contenedor)aux;
                        objetos.add(tmp);
                        
                        for (Object obj : tmp.objetos) {
                            objetos.add(obj);
                        }
                        
                    }
                }
                
                return this;
            }
        }
        
        return Constante.NULO;
        
//        boolean paso = validarElementos(ent);
//        
//        if(paso)
//        {
//            Simbolo s = ent.getSimbolo_Ent(id.valor.toLowerCase());
//            
//            if(s == null)
//            {
//                ent.agregar(id.valor.toLowerCase(), new Simbolo(id.valor.toLowerCase(), Tipo.VENTANA, this));
//                return this;
//            }
//        }
//        
//        return Constante.NULO;
    }
}
