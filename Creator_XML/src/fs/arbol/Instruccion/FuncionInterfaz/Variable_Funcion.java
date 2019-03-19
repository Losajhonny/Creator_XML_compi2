/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.FuncionInterfaz;

import entorno.Entorno;
import entorno.Simbolo;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion;
import fs.arbol.Instruccion.Funcion.CallFuncion;
import fs.arbol.Instruccion.Instruccion;
import gxml.arbol.Ejecutar;
import gxml.arbol.componente.Boton;
import gxml.arbol.componente.Controlador;
import gxml.arbol.componente.Enviar;
import gxml.arbol.componente.Multimedia;
import gxml.arbol.componente.Texto;
import gxml.arbol.contenedor.Contenedor;
import gxml.arbol.contenedor.Ventana;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Variable_Funcion implements Expresion, Instruccion {
    
    public String id;
    public Object objeto;
    public int line, colm;
    
    public Variable_Funcion(String id, Object objeto, int line, int colm)
    {
        this.id = id;
        this.objeto = objeto;
        this.line = line;
        this.colm = colm;
    }
    
    public Variable_Funcion(Object objeto, int line, int colm)
    {
        this.id = null;
        this.objeto = objeto;
        this.line = line;
        this.colm = colm;
    }

    @Override
    public Object evaluar(Entorno ent) {
        return execute(ent);
    }
    
    
    public Object execute(Entorno ent)
    {
        if(id != null && objeto != null)
        {
            Simbolo s = ent.getSimbolo_Ent(id.toLowerCase());
            
            if(s != null)
            {
                if(objeto instanceof CrearContenedor)
                {
                    return execute_crearCont(ent, s);
                }
                else if(objeto instanceof CrearTexto)
                {
                    return execute_crearText(ent, s);
                }
                else if(objeto instanceof CrearCajaTexto)
                {
                    return execute_crearCaja(ent, s);
                }
                else if(objeto instanceof CrearAreaTexto)
                {
                    return execute_areaTexto(ent, s);
                }
                else if(objeto instanceof CrearControlNumerico)
                {
                    return execute_numerico(ent, s);
                }
                else if(objeto instanceof CrearDesplegable)
                {
                    return execute_desplegable(ent, s);
                }
                else if(objeto instanceof CrearBoton)
                {
                    return execute_boton(ent, s);
                }
                else if(objeto instanceof AlCargar)
                {
                    return execute_Cargar(ent, s);
                }
                else if(objeto instanceof AlCerrar)
                {
                    return execute_Cerrar(ent, s);
                }
                else if(objeto instanceof AlClick)
                {
                    return execute_click(ent, s);
                }
                else if(objeto instanceof ObtenerPorEtiqueta)
                {
                    return execute_obtenerEtiqueta(ent, s);
                }
                else if(objeto instanceof ObtenerPorId)
                {
                    return execute_obtenerId(ent, s);
                }
                else if(objeto instanceof ObtenerPorNombre)
                {
                    return execute_obtenerNombre(ent, s);
                }
                else if(objeto instanceof CrearArrayDesdeArchivo)
                {
                    return execute_crearArchivo(ent, s);
                }
            }
            else
            {
                String msg = "No se pudo encontrar la variable \"" + id + "\"";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
                return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
            }
        }
        else if(id == null && objeto != null)
        {
            if(objeto instanceof CrearVentana)
            {
                CrearVentana cv = (CrearVentana) objeto;
                return cv.evaluar(ent);
            }
            else if (objeto instanceof CrearArrayDesdeArchivo)
            {
                return execute_crearArchivo(ent);
            }
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }
    
    public Object execute_crearCont(Entorno ent, Simbolo s)
    {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Ventana)
            {
                Ventana vent = (Ventana)s.componente;

                CrearContenedor cc = (CrearContenedor) objeto;
                cc.ventana = vent;
                cc.id = id;
                Object obj = cc.evaluar(ent);
                
                if(cc.cont != null)
                {
                    vent.contenedores.add(cc.cont);
                    vent.panel.add(cc.cont.panel);
                }
                
                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es una ventana para poder agregar un contenedor";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }

    private Object execute_crearText(Entorno ent, Simbolo s) {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Contenedor)
            {
                Contenedor cont = (Contenedor)s.componente;

                CrearTexto ct = (CrearTexto) objeto;
                ct.id = id;
                Object obj = ct.evaluar(ent);
                
                if(ct.text != null)
                {
                    cont.componentes.add((Ejecutar) ct.text);
                    cont.panel.add(ct.text.label);
                }
                
                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es un contenedor para poder agregar un componente";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }

    private Object execute_crearCaja(Entorno ent, Simbolo s) {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Contenedor)
            {
                Contenedor cont = (Contenedor)s.componente;

                CrearCajaTexto ct = (CrearCajaTexto) objeto;
                ct.id = id;
                Object obj = ct.evaluar(ent);
                
                if(ct.componente != null)
                {
                    cont.componentes.add((Ejecutar) ct.componente);
                    cont.panel.add(ct.componente.cajaTexto);
                }
                
                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es un contenedor para poder agregar un componente";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }

    private Object execute_areaTexto(Entorno ent, Simbolo s) {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Contenedor)
            {
                Contenedor cont = (Contenedor)s.componente;

                CrearAreaTexto ct = (CrearAreaTexto) objeto;
                ct.id = id;
                Object obj = ct.evaluar(ent);
                
                if(ct.componente != null)
                {
                    cont.componentes.add((Ejecutar) ct.componente);
                    cont.panel.add(ct.componente.jareaTexto);
                }
                
                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es un contenedor para poder agregar un componente";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }
    
    private Object execute_numerico(Entorno ent, Simbolo s) {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Contenedor)
            {
                Contenedor cont = (Contenedor)s.componente;

                CrearControlNumerico ct = (CrearControlNumerico) objeto;
                ct.id = id;
                Object obj = ct.evaluar(ent);
                
                if(ct.componente != null)
                {
                    cont.componentes.add((Ejecutar) ct.componente);
                    cont.panel.add(ct.componente.numerico);
                }
                
                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es un contenedor para poder agregar un componente";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }

    private Object execute_desplegable(Entorno ent, Simbolo s) {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Contenedor)
            {
                Contenedor cont = (Contenedor)s.componente;

                CrearDesplegable ct = (CrearDesplegable) objeto;
                ct.id = id;
                Object obj = ct.evaluar(ent);
                
                if(ct.componente != null)
                {
                    cont.componentes.add((Ejecutar) ct.componente);
                    cont.panel.add(ct.componente.desplegable);
                }
                
                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es un contenedor para poder agregar un componente";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        return execute(ent);
    }

    private Object execute_boton(Entorno ent, Simbolo s) {
        
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Contenedor)
            {
                Contenedor cont = (Contenedor)s.componente;

                CrearBoton ct = (CrearBoton) objeto;
                ct.id = id;
                Object obj = ct.evaluar(ent);
                
                if(ct.boton != null)
                {
                    cont.componentes.add((Ejecutar) ct.boton);
                    cont.panel.add(ct.boton.boton);
                }
                
                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es un contenedor para poder agregar un componente";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        
    }

    private Object execute_click(Entorno ent, Simbolo s) {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Boton)
            {
                AlClick ac = (AlClick) objeto;
                
                Boton btn = (Boton) s.componente;
                btn.click = (CallFuncion) ac.exp;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es un boton para poder asignarle el evento";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder acceder al elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }
    
    private Object execute_Cargar(Entorno ent, Simbolo s) {
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Ventana)
            {
                AlCargar ac = (AlCargar) objeto;
                
                if(ac.exp == null)
                {
                    if(Constante.ventana_actual != null)
                    {
                        Constante.ventana_actual.setVisible(false);
                    }
                    
                    Ventana vent = (Ventana) s.componente;
                    vent.vent.setVisible(true);
                    Constante.ventana_actual = vent.vent;
                }
                else
                {
                    Ventana vent = (Ventana) s.componente;
                    vent.cargando = (CallFuncion)ac.exp;
                }
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es una ventana para poder cargarla";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder acceder al elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
    }

    private Object execute_Cerrar(Entorno ent, Simbolo s) {
        
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Ventana)
            {
                AlCerrar ac = (AlCerrar) objeto;
                
                if(ac.exp == null)
                {
                    if(Constante.ventana_actual != null)
                    {
                        Constante.ventana_actual.setVisible(false);
                    }
                }
                else
                {
                    Ventana vent = (Ventana) s.componente;
                    vent.cerrando = (CallFuncion)ac.exp;
                }
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es una ventana para poder cerrarla";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder acceder al elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        
    }

    private Object execute_obtenerEtiqueta(Entorno ent, Simbolo s) {
        
        if(s.tipo == Operacion.Tipo.STRUCT)
        {
            ObtenerPorEtiqueta ope = (ObtenerPorEtiqueta) objeto;
            
            Literal resope = (Literal)ope.evaluar(ent);
            
            if(resope.tipo == Operacion.Tipo.CADENA)
            {
                Literal res = (Literal) s.valor;
                
                LinkedList<Object> objetos = (LinkedList) res.valor;
                
                switch(((String)resope.valor).toLowerCase())
                {
                    case "ventana":
                        LinkedList<Object> vents = new LinkedList<>();
                        for (Object obj : objetos) {
                            if(obj instanceof Ventana) { vents.add(obj); }
                        }
                        return new Literal(Operacion.Tipo.STRUCT_VENTANA, vents, line, colm);
                    case "contenedor":
                        LinkedList<Object> conts = new LinkedList<>();
                        for (Object obj : objetos) {
                            if(obj instanceof Contenedor) { conts.add(obj); }
                        }
                        return new Literal(Operacion.Tipo.STRUCT_CONTENEDOR, conts, line, colm);
                    case "boton":
                        LinkedList<Object> btns = new LinkedList<>();
                        for (Object obj : objetos) {
                            if(obj instanceof Boton) { btns.add(obj); }
                        }
                        return new Literal(Operacion.Tipo.STRUCT_BOTON, btns, line, colm);
                    case "control":
                        LinkedList<Object> ctrls = new LinkedList<>();
                        for (Object obj : objetos) {
                            if(obj instanceof Controlador) { ctrls.add(obj); }
                        }
                        return new Literal(Operacion.Tipo.STRUCT_CONTROL, ctrls, line, colm);
                    case "enviar":
                        LinkedList<Object> sends = new LinkedList<>();
                        for (Object obj : objetos) {
                            if(obj instanceof Enviar) { sends.add(obj); }
                        }
                        return new Literal(Operacion.Tipo.STRUCT_ENVIAR, sends, line, colm);
                    case "multimedia":
                        LinkedList<Object> views = new LinkedList<>();
                        for (Object obj : objetos) {
                            if(obj instanceof Multimedia) { views.add(obj); }
                        }
                        return new Literal(Operacion.Tipo.STRUCT_MULTIMEDIA, views, line, colm);
                    case "texto":
                        LinkedList<Object> texts = new LinkedList<>();
                        for (Object obj : objetos) {
                            if(obj instanceof Texto) { texts.add(obj); }
                        }
                        return new Literal(Operacion.Tipo.STRUCT_TEXTO, texts, line, colm);
                    default:
                        String msg = "La etiqueta " + ((String)resope.valor).toLowerCase() + " no se pudo encontrar";
                        otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                        otros.Error.agregarError(err);
                        return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
                }
            }
            else
            {
                String msg = "La expresion de la funcion OBTENER POR ETIQUETA debe ser de tipo CADENA";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
                return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es una estructura para obtener un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }

    private Object execute_obtenerId(Entorno ent, Simbolo s) {
        
        if(s.tipo == Operacion.Tipo.STRUCT
                || s.tipo == Operacion.Tipo.STRUCT_BOTON
                || s.tipo == Operacion.Tipo.STRUCT_CONTENEDOR
                || s.tipo == Operacion.Tipo.STRUCT_CONTROL
                || s.tipo == Operacion.Tipo.STRUCT_ENVIAR
                || s.tipo == Operacion.Tipo.STRUCT_MULTIMEDIA
                || s.tipo == Operacion.Tipo.STRUCT_TEXTO
                || s.tipo == Operacion.Tipo.STRUCT_VENTANA)
        {
            ObtenerPorId opi = (ObtenerPorId) objeto;
            
            Literal resopi = (Literal)opi.evaluar(ent);
            
            if(resopi.tipo == Operacion.Tipo.CADENA)
            {
                Literal res = (Literal) s.valor;
                
                LinkedList<Object> objetos = (LinkedList) res.valor;
                
                for (Object obj : objetos) {
                    if(obj instanceof Ventana)
                    {
                        Ventana v = (Ventana) obj;
                        if(v.id != null)
                        {
                            if(((String)resopi.valor).toLowerCase().equals(v.id.valor.toLowerCase()))
                            {
                                v.generarVentana(null);
                                return new Literal(Operacion.Tipo.VENTANA, v, line, colm);
                            }
                        }
                    }
                    else if (obj instanceof Contenedor)
                    {
                        Contenedor c = (Contenedor) obj;
                        if(c.id != null)
                        {
                            if(((String)resopi.valor).toLowerCase().equals(c.id.valor.toLowerCase()))
                            {
                                c.generarContenedor(null);
                                return new Literal(Operacion.Tipo.CONTENEDOR, c, line, colm);
                            }
                        }
                    }
                }
                return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
            }
            else
            {
                String msg = "La expresion de la funcion OBTENER POR ID debe ser de tipo CADENA";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
                return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es una estructura para obtener un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }

    private Object execute_obtenerNombre(Entorno ent, Simbolo s) {
        
        if(s.tipo == Operacion.Tipo.STRUCT
                || s.tipo == Operacion.Tipo.STRUCT_BOTON
                || s.tipo == Operacion.Tipo.STRUCT_CONTENEDOR
                || s.tipo == Operacion.Tipo.STRUCT_CONTROL
                || s.tipo == Operacion.Tipo.STRUCT_ENVIAR
                || s.tipo == Operacion.Tipo.STRUCT_MULTIMEDIA
                || s.tipo == Operacion.Tipo.STRUCT_TEXTO
                || s.tipo == Operacion.Tipo.STRUCT_VENTANA)
        {
            ObtenerPorNombre opn = (ObtenerPorNombre) objeto;
            opn.evaluar(ent);
            
            
            if(opn.res1.tipo == Operacion.Tipo.CADENA && opn.res2.tipo == Operacion.Tipo.CADENA)
            {
                Literal res = (Literal) s.valor;
                
                LinkedList<Object> objetos = (LinkedList) res.valor;
                
                for (Object obj : objetos) {
                    
                    if(obj instanceof Ventana)
                    {
                        Ventana v = (Ventana) obj;
                        if(v.id != null)
                        {
                            if(((String) opn.res2.valor).toLowerCase().equals(v.id.valor.toLowerCase()))
                            {
                                for (Contenedor cont : v.contenedores) {
                                    
                                    for (Object objcont : cont.objetos) {
                                        
                                        if(objcont instanceof Boton)
                                        {
                                            Boton comp = (Boton) objcont;
                                            
                                            if(comp.nombre != null)
                                            {
                                                if(((String) opn.res1.valor).toLowerCase().equals(comp.nombre.valor.toLowerCase()))
                                                {
                                                    comp.generarBoton(null);
                                                    return new Literal(Operacion.Tipo.BOTON, comp, line, colm);
                                                }
                                            }
                                        }
                                        else if(objcont instanceof Controlador)
                                        {
                                            Controlador comp = (Controlador) objcont;
                                            
                                            if(comp.nombre != null)
                                            {
                                                if(((String) opn.res1.valor).toLowerCase().equals(comp.nombre.valor.toLowerCase()))
                                                {
                                                    comp.generarAreaTexto(null);
                                                    comp.generarCajaTexto(null);
                                                    comp.generarDesplegable(null);
                                                    comp.generarNumerico(null);
                                                    return new Literal(Operacion.Tipo.CONTROLADOR, comp, line, colm);
                                                }
                                            }
                                        }
                                        if(objcont instanceof Enviar)
                                        {
                                            Enviar comp = (Enviar) objcont;
                                            
                                            if(comp.nombre != null)
                                            {
                                                if(((String) opn.res1.valor).toLowerCase().equals(comp.nombre.valor.toLowerCase()))
                                                {
                                                    comp.generarBoton(null);
                                                    return new Literal(Operacion.Tipo.ENVIAR, comp, line, colm);
                                                }
                                            }
                                        }
                                        if(objcont instanceof Multimedia)
                                        {
                                            Multimedia comp = (Multimedia) objcont;
                                        }
                                        if(objcont instanceof Texto)
                                        {
                                            Texto comp = (Texto) objcont;
                                            
                                            if(comp.nombre != null)
                                            {
                                                if(((String) opn.res1.valor).toLowerCase().equals(comp.nombre.valor.toLowerCase()))
                                                {
                                                    comp.generarTexto(null);
                                                    return new Literal(Operacion.Tipo.TEXTO, comp, line, colm);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                }
                return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
            }
            else
            {
                String msg = "Las expresiones de la funcion OBTENER POR NOMBRE debe ser de tipo CADENA";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
                return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es una estructura para obtener un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
    }

    private Object execute_crearArchivo(Entorno ent, Simbolo s) {
        
        if (s.tipo == Operacion.Tipo.OBJETO)
        {
            if(s.componente instanceof Ventana)
            {
                Ventana vent = (Ventana)s.componente;

                /*DEBO RECORRER LOS CONTENEDORES Y ENCONTRAR LOS CONTROLADORES*/
                
                String cadena = "";
                
                for (Contenedor cont : vent.contenedores) {
                    
                    for (Ejecutar comp : cont.componentes) {
                        
                        if(comp instanceof Controlador)
                        {
                            Controlador tmp = (Controlador) comp;
                            
                            tmp.validarElementos(null);
                            
                            if(tmp.nombre != null)
                            {
                                System.out.print("<" + tmp.nombre.valor + ">");
                                if(tmp.areaTexto != null)
                                {
                                    System.out.print("\"" + tmp.areaTexto.getText() + "\"");
                                }
                                else if (tmp.cajaTexto != null)
                                {
                                    System.out.print("\"" + tmp.cajaTexto.getText() + "\"");
                                }
                                else if (tmp.desplegable != null)
                                {
                                    System.out.print("\"" + tmp.desplegable.getSelectedItem().toString() + "\"");
                                }
                                else if (tmp.numerico != null)
                                {
                                    System.out.print(tmp.numerico.getValue().toString());
                                }
                                System.out.println("</" + tmp.nombre.valor + ">");
                            }
                        }
                        
                    }
                    
                }
                
//                return obj;
            }
            else
            {
                String msg = "La variable \"" + id + "\" no es una ventana para poder agregar un contenedor";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
                otros.Error.agregarError(err);
            }
        }
        else
        {
            String msg = "La variable \"" + id + "\" no es un objeto valido para poder crear un elemento";
            otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, line, colm);
            otros.Error.agregarError(err);
            return new Literal(Operacion.Tipo.ERROR, Constante.NULO, line, colm);
        }
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
    private Object execute_crearArchivo(Entorno ent)
    {
        
        System.out.println("Crear gdato");
        
        return new Literal(Operacion.Tipo.UNDEFINED, Constante.NULO, line, colm);
    }
    
}
