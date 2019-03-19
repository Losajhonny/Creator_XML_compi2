package fs.analizador;

import java_cup.runtime.Symbol;
import otros.Constante;

%%
%class fsScanner
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
ID              =   [A-Za-z_][A-Za-z0-9_]*
ENTERO          =   [0-9]+
DECIMAL         =   [0-9]+"."[0-9]+

UNICOMMENT      =   "//".*[\r\n|\n|\r]
MULCOMMENT      =   "/*""/"*([^*/]|[^*]"/"|"*"[^/])*"*"*"*/"

%%

<YYINITIAL>
{
    {UNICOMMENT}        {}
    {MULCOMMENT}        {}

    /*estados*/
    "\""                { yybegin(CADENA); cadena=""; }
    "\'"                { yybegin(CARACTER); cadena=""; }

    /*simbolos*/
    "{"                 { return symbol(sym.llai); }
    "}"                 { return symbol(sym.llad); }
    "["                 { return symbol(sym.cori); }
    "]"                 { return symbol(sym.cord); }
    "("                 { return symbol(sym.pari); }
    ")"                 { return symbol(sym.pard); }

    "."                 { return symbol(sym.punto); }
    ";"                 { return symbol(sym.ptcoma); }
    ":"                 { return symbol(sym.dospuntos); }
    ","                 { return symbol(sym.coma); }
    "?"                 { return symbol(sym.inter); }
    "="                 { return symbol(sym.igual); }

    /*operadores aritmeticos*/
    "+"                 { return symbol(sym.mas); }
    "-"                 { return symbol(sym.menos); }
    "*"                 { return symbol(sym.por); }
    "/"                 { return symbol(sym.div); }
    "^"                 { return symbol(sym.pot); }
    "++"                { return symbol(sym.masmas); }
    "--"                { return symbol(sym.menmen); }

    /*asignacion agrupacion*/
    "+="                { return symbol(sym.masigual); }
    "-="                { return symbol(sym.menigual); }
    "*="                { return symbol(sym.porigual); }
    "/="                { return symbol(sym.divigual); }

    /*operadores relacionales*/
    ">"                 { return symbol(sym.mayor); }
    "<"                 { return symbol(sym.menor); }
    ">="                { return symbol(sym.mayorigual); }
    "<="                { return symbol(sym.menorigual); }
    "=="                { return symbol(sym.igualigual); }
    "!="                { return symbol(sym.diferente); }

    /*operadores logicos*/
    "&&"                { return symbol(sym.and); }
    "||"                { return symbol(sym.or); }
    "!"                 { return symbol(sym.not); }

    /*palabras reservadas*/
    "var"               { return symbol(sym.pr_var); }
    "importar"          { return symbol(sym.pr_importar); }
    "imprimir"          { return symbol(sym.pr_imprimir); }
    "detener"           { return symbol(sym.pr_detener); }
    "retornar"          { return symbol(sym.pr_retornar); }
    "selecciona"        { return symbol(sym.pr_selecciona); }
    "caso"              { return symbol(sym.pr_caso); }
    "defecto"           { return symbol(sym.pr_defecto); }
    "si"                { return symbol(sym.pr_si); }
    "sino"              { return symbol(sym.pr_sino); }
    "funcion"           { return symbol(sym.pr_funcion); }
    "descendente"       { return symbol(sym.pr_descendente); }
    "ascendente"        { return symbol(sym.pr_ascendente); }
    "creararraydesdearchivo" { return symbol(sym.pr_creararraydesdearchivo); }
    "invertir"          { return symbol(sym.pr_invertir); }
    "maximo"            { return symbol(sym.pr_maximo); }
    "minimo"            { return symbol(sym.pr_minimo); }
    "filtrar"           { return symbol(sym.pr_filtrar); }
    "buscar"            { return symbol(sym.pr_buscar); }
    "map"               { return symbol(sym.pr_map); }
    "reduce"            { return symbol(sym.pr_reduce); }
    "todos"             { return symbol(sym.pr_todos); }
    "alguno"            { return symbol(sym.pr_alguno); }
    "leergxml"          { return symbol(sym.pr_leergxml); }
    "obtenerporetiqueta" { return symbol(sym.pr_obtenerporetiqueta); }
    "obtenerporid"      { return symbol(sym.pr_obtenerporid); }
    "obtenerpornombre"  { return symbol(sym.pr_obtenerpornombre); }
    "crearventana"      { return symbol(sym.pr_crearventana); }
    "crearcontenedor"   { return symbol(sym.pr_crearcontenedor); }
    "creartexto"        { return symbol(sym.pr_creartexto); }
    "crearcajatexto"    { return symbol(sym.pr_crearcajatexto); }
    "crearareatexto"    { return symbol(sym.pr_crearareatexto); }
    "crearcontrolnumerico" { return symbol(sym.pr_crearcontrolnumerico); }
    "creardesplegable"  { return symbol(sym.pr_creardespegable); }
    "crearboton"        { return symbol(sym.pr_crearboton); }
    "crearimagen"       { return symbol(sym.pr_crearimagen); }
    "crearreproductor"  { return symbol(sym.pr_crearreproductor); }
    "crearvideo"        { return symbol(sym.pr_crearvideo); }
    "alclic"            { return symbol(sym.pr_alclic); }
    "alcargar"          { return symbol(sym.pr_alcargar); }
    "alcerrar"          { return symbol(sym.pr_alcerrar); }
    "verdadero"         { return symbol(sym.pr_verdadero); }
    "falso"             { return symbol(sym.pr_falso); }
    "nulo"              { return symbol(sym.pr_nulo); }

    \n                  { yychar=1; }

    {ENTERO}            { return symbol(sym.entero); }
    {DECIMAL}           { return symbol(sym.decimal); }
    {ID}                { return symbol(sym.id); }
    
    {ESPACIOS}          {}

    .                   {
                            otros.Error err = new otros.Error(Constante.FS, Constante.LEXICO, yytext(), Constante.ent_temporal.ambito, 
                            "El caracter: "+yytext()+" no pertenece al lenguaje", 
                            Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
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

    .                   {
                            otros.Error err = new otros.Error(Constante.FS, Constante.LEXICO, yytext(), Constante.ent_temporal.ambito, 
                            "El caracter: "+yytext()+" no pertenece al lenguaje", 
                            Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
                        }
}

<CARACTER>
{
    \'             { yybegin(YYINITIAL); return symbol(sym.cadena, cadena); }
    [^\n\r\\\'\"]+ { cadena+=String.valueOf(yytext()); }
    \"             { cadena+=String.valueOf("\""); }
    \\\"           { cadena+=String.valueOf("\""); }
    \\\'           { cadena+=String.valueOf("\'"); }
    \\t            { cadena+=String.valueOf("\t"); }
    \\n            { cadena+=String.valueOf("\n"); }
    \\r            { cadena+=String.valueOf("\r"); }
    \              { }
    \\             { cadena+=String.valueOf("\\"); }


    .                   {
                            otros.Error err = new otros.Error(Constante.FS, Constante.LEXICO, yytext(), Constante.ent_temporal.ambito, 
                            "El caracter: "+yytext()+" no pertenece al lenguaje", 
                            Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
                        }
}

