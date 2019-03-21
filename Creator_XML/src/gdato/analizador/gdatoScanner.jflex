package gdato.analizador;

import java_cup.runtime.Symbol;
import otros.Constante;

%%
%class gdatoScanner
%public
%line
%char
%cup
%unicode
%ignorecase

%state CADENA
%state CARACTER

%init{
    yyline = 1;
    yychar = 1;
%init}

%{
    private String cadena = "";

    /**
    * Retorna el simbolo con sym.value
    * Con la linea, columna y texto de flex
    */
    private Symbol symbol(int value){
        return new Symbol(value, yyline, yychar, yytext());
    }

    /**
    * Retorna el simbolo con sym.id y valor asignado
    * Con la linea, columna de flex
    */
    private Symbol symbol(int id, Object value){
        return new Symbol(id, yyline, yychar, value);
    }
%}

ESPACIOS        =   [ \r\n\t\f]
ENTERO          =   [0-9]+
DECIMAL         =   [0-9]+"."[0-9]+
ID              =   [A-Za-z_][A-Za-z0-9_]*

NUMERO          =   {ENTERO}|{DECIMAL}

UNICOMMENT      =   "##".*[\r\n|\n|\r]
MULCOMMENT      =   "#$""#"*([^$#]|[^$]"#"|"$"[^#])*"$"*"$#"

%%

<YYINITIAL>
{
    {UNICOMMENT}        {}
    {MULCOMMENT}        {}

    /*estados*/
    "\""                { yybegin(CADENA); cadena=""; }
    "\'"                { yybegin(CARACTER); cadena=""; }
    
    /*simbolos*/
    "<"                 { return symbol(sym.open); }
    "/"                 { return symbol(sym.slash); }
    ">"                 { return symbol(sym.close); }

    /*componentes o etiquetas*/
    "lista"             { return symbol(sym.pr_lista); }
    "principal"         { return symbol(sym.pr_principal); }

    \n                  { yychar=1; }

    {NUMERO}            { return symbol(sym.numero); }
    {ID}                { return symbol(sym.id); }
    
    {ESPACIOS}          {}

    .                   {
                            otros.Error err = new otros.Error(Constante.GDATO, Constante.LEXICO, yytext(), "", "Este es un error lexico: "+yytext(), Constante.archivo, yyline, yychar);
                            Constante.errores.add(err);
                        }
}

<CADENA>
{
    \"             { yybegin(YYINITIAL); return symbol(sym.cadena, cadena); }
    [^\n\r\\\"\']+ { cadena+=String.valueOf(yytext()); }
    \'             { cadena+=String.valueOf("\'"); }
    \\\"           { cadena+=String.valueOf("\""); }
    \\\'           { cadena+=String.valueOf("\'"); }
    \\t            { cadena+=String.valueOf("\t"); }
    \\n            { cadena+=String.valueOf("\n"); }
    \\r            { cadena+=String.valueOf("\r"); }
    \              { }
    \\             { cadena+=String.valueOf("\\"); }
    \n             { 
                        otros.Error err = new otros.Error(Constante.GDATO, Constante.LEXICO, "Este es un error lexico: \\n", yyline, yychar); 
                        otros.Error.agregarError(err);
                        yychar=1;
                    }
}

<CARACTER>
{
    \'             { yybegin(YYINITIAL);  return symbol(sym.cadena, cadena); }
    [^\n\r\\\'\"]+ { cadena+=String.valueOf(yytext()); }
    \"             { cadena+=String.valueOf("\""); }
    \\\"           { cadena+=String.valueOf("\""); }
    \\\'           { cadena+=String.valueOf("\'"); }
    \\t            { cadena+=String.valueOf("\t"); }
    \\n            { cadena+=String.valueOf("\n"); }
    \\r            { cadena+=String.valueOf("\r"); }
    \              { }
    \\             { cadena+=String.valueOf("\\"); }
    \n             { 
                        otros.Error err = new otros.Error(Constante.GDATO, Constante.LEXICO, "Este es un error lexico: \\n", yyline, yychar);
                        otros.Error.agregarError(err); 
                        yychar=1;
                    }    
}