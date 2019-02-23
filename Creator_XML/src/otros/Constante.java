/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

/**
 *
 * @author Jhona
 */
public class Constante {
    
    public static String consola;
    
    public static final int ENTERO = 0;
    public static final int DECIMAL = 1;
    public static final int CADENA = 2;
    public static final int CARACTER = 3;
    public static final int BOOLEANO = 4;
    public static final int OBJETO = 5;
    public static final int ERROR = 6;
    
    public static final int[][] MAS = {
        //             ENTERO     DECIMAL     CADENA     CARACTER     BOOLEANO     OBJETO     ERROR
        /*ENTERO*/    {ENTERO,    DECIMAL,   CADENA,     ENTERO,      ERROR,       ERROR,     ERROR},
        /*DECIMAL*/   {DECIMAL,   DECIMAL,   CADENA,     DECIMAL,     ERROR,       ERROR,     ERROR},
        /*CADENA*/    {CADENA,    CADENA,    CADENA,     CADENA,      CADENA,      ERROR,     ERROR},
        /*CARACTER*/  {ENTERO,    DECIMAL,   CADENA,     ERROR,       ERROR,       ERROR,     ERROR},
        /*BOOLEANO*/  {ERROR,     ERROR,     CADENA,     ERROR,       ERROR,       ERROR,     ERROR},
        /*OBJETO*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*ERROR*/     {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR}
    };
    
    public static final int[][] MENOS = {
        //             ENTERO     DECIMAL     CADENA     CARACTER     BOOLEANO     OBJETO     ERROR
        /*ENTERO*/    {ENTERO,    DECIMAL,   ERROR,      ENTERO,      ERROR,       ERROR,     ERROR},
        /*DECIMAL*/   {DECIMAL,   DECIMAL,   ERROR,      DECIMAL,     ERROR,       ERROR,     ERROR},
        /*CADENA*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*CARACTER*/  {ENTERO,    DECIMAL,   ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*BOOLEANO*/  {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*OBJETO*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*ERROR*/     {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR}
    };
    
    public static final int[][] POR = {
        //             ENTERO     DECIMAL     CADENA     CARACTER     BOOLEANO     OBJETO     ERROR
        /*ENTERO*/    {ENTERO,    DECIMAL,   ERROR,      ENTERO,      ERROR,       ERROR,     ERROR},
        /*DECIMAL*/   {DECIMAL,   DECIMAL,   ERROR,      DECIMAL,     ERROR,       ERROR,     ERROR},
        /*CADENA*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*CARACTER*/  {ENTERO,    DECIMAL,   ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*BOOLEANO*/  {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*OBJETO*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*ERROR*/     {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR}
    };
    
    public static final int[][] DIV = {
        //             ENTERO     DECIMAL     CADENA     CARACTER     BOOLEANO     OBJETO     ERROR
        /*ENTERO*/    {DECIMAL,   DECIMAL,   ERROR,      DECIMAL,     ERROR,       ERROR,     ERROR},
        /*DECIMAL*/   {DECIMAL,   DECIMAL,   ERROR,      DECIMAL,     ERROR,       ERROR,     ERROR},
        /*CADENA*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*CARACTER*/  {DECIMAL,   DECIMAL,   ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*BOOLEANO*/  {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*OBJETO*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*ERROR*/     {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR}
    };
    
    public static final int[][] POT = {
        //             ENTERO     DECIMAL     CADENA     CARACTER     BOOLEANO     OBJETO     ERROR
        /*ENTERO*/    {ENTERO,    DECIMAL,   ERROR,      ENTERO,      ENTERO,      ERROR,     ERROR},
        /*DECIMAL*/   {DECIMAL,   DECIMAL,   ERROR,      DECIMAL,     DECIMAL,     ERROR,     ERROR},
        /*CADENA*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*CARACTER*/  {ENTERO,    DECIMAL,   ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*BOOLEANO*/  {ENTERO,    DECIMAL,   ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*OBJETO*/    {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR},
        /*ERROR*/     {ERROR,     ERROR,     ERROR,      ERROR,       ERROR,       ERROR,     ERROR}
    };
}
