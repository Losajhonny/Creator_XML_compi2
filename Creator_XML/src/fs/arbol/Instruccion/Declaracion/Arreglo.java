/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fs.arbol.Instruccion.Declaracion;

import entorno.Entorno;
import fs.arbol.Expresion.Expresion;
import fs.arbol.Expresion.Literal;
import fs.arbol.Expresion.Operaciones.Operacion.Tipo;
import fs.arbol.Instruccion.Instruccion;
import java.util.LinkedList;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class Arreglo implements Instruccion{
    
    public LinkedList<Expresion> expresiones;
    public LinkedList<Literal> valores;
    
    public Tipo tipo;
    public int tam;
    public boolean hayUndefined;
    
    public Arreglo(){
        this.expresiones = new LinkedList<>();
        this.valores = new LinkedList<>();
        this.tam = 0;
        hayUndefined = false;
    }
    
    public Arreglo(LinkedList<Expresion> expresiones){
        this.expresiones = expresiones;
        this.valores = new LinkedList<>();
        this.tam = expresiones.size();
        hayUndefined = false;
    }
    
    public String[] getValores()
    {
        String[] val = new String[valores.size()];
        for (int i = 0; i < val.length; i++) {
            val[i] = (String) valores.get(i).valor;
        }
        return val;
    }
    
    public boolean isValor(String[] val, String v)
    {
        for (String valor : val) {
            if(valor.toLowerCase().equals(v.toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        valores.clear();
        
        Tipo tmp = Tipo.UNDEFINED;  /*tipo por default*/
        int cambio = 0;             /*para saber si un es homogeneo o heterogeneo*/
        
        for (Expresion exp : expresiones) {
            Literal aux = (Literal)exp.evaluar(ent);    //ejecuto la expresion con el entorno actual
            
            if(aux.tipo == Tipo.ARREGLO)
            {
                String msg = "En el arreglo no se le puede asignar un ARREGLO de valor";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, aux.line, aux.colm);
                otros.Error.agregarError(err);
            }
            else if(aux.tipo == Tipo.OBJETO)
            {
                String msg = "En el arreglo no se le puede asignar un OBJETO de valor";
                otros.Error err = new otros.Error(Constante.FS, Constante.EJECUCION, "", ent.ambito, msg, Constante.archivo, aux.line, aux.colm);
                otros.Error.agregarError(err);
            }
            
            aux.valor = (aux.tipo == Tipo.ERROR)? Constante.NULO: aux.valor;
            aux.tipo = (aux.tipo == Tipo.ERROR)? Tipo.UNDEFINED: aux.tipo;
            
            aux.valor = (aux.tipo == Tipo.ARREGLO)? Constante.NULO: aux.valor;
            aux.tipo = (aux.tipo == Tipo.ARREGLO)? Tipo.UNDEFINED: aux.tipo;
            
            aux.valor = (aux.tipo == Tipo.OBJETO)? Constante.NULO: aux.valor;
            aux.tipo = (aux.tipo == Tipo.OBJETO)? Tipo.UNDEFINED: aux.tipo;
            
            if(tmp == Tipo.UNDEFINED){
                tmp = aux.tipo;
            }
            else if (tmp != aux.tipo) {
                cambio += 1;
            }
            
            if(aux.tipo == Tipo.UNDEFINED) { hayUndefined = true; }
            
            valores.add(aux);
        }
        
        /*debo de calcular si es homogeneo*/
        tipo = (cambio == 0)? Tipo.HOMOGENEO: Tipo.HETEROGENEO;
        return this;
    }
}
