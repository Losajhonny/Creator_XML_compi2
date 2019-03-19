package gxml.analizador;

import java_cup.runtime.Symbol;
import otros.Constante;

%%
%class gxmlScanner
%public
%line
%char
%cup
%unicode
%ignorecase

%state CADENA
%state CARACTER
%state LLAMADA
%state TODO
%state UNI_COMMENT1
%state MUL_COMMENT1

%init{
    yyline = 1;
    yychar = 1;
%init}

%{
    private String cadena = "";
    private int ban = 0;

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
    "{"                 { yybegin(LLAMADA); cadena=""; }
    ">"                 { yybegin(TODO); ban=0; cadena=""; }
    
    /*simbolos*/
    "<"                 { return symbol(sym.open); }
    "/"                 { return symbol(sym.slash); }
    "="                 { return symbol(sym.igual); }

    /*componentes o etiquetas*/
    "importar"          { return symbol(sym.pr_importar); }
    "ventana"           { return symbol(sym.pr_ventana); }
    "contenedor"        { return symbol(sym.pr_contenedor); }
    "texto"             { return symbol(sym.pr_texto); }
    "control"           { return symbol(sym.pr_control); }
    "dato"              { return symbol(sym.pr_dato); }
    "listadato"         { return symbol(sym.pr_listadato); }
    "defecto"           { return symbol(sym.pr_defecto); }
    "multimedia"        { return symbol(sym.pr_multimedia); }
    "boton"             { return symbol(sym.pr_boton); }
    "enviar"            { return symbol(sym.pr_enviar); }

    /*atributos*/
    "id"                { return symbol(sym.pr_id); }
    "tipo"              { return symbol(sym.pr_tipo); }
    "color"             { return symbol(sym.pr_color); }
    "accioninicial"     { return symbol(sym.pr_accioninicial); }
    "accionfinal"       { return symbol(sym.pr_accionfinal); }
    "x"                 { return symbol(sym.pr_x); }
    "y"                 { return symbol(sym.pr_y); }
    "alto"              { return symbol(sym.pr_alto); }
    "ancho"             { return symbol(sym.pr_ancho); }
    "borde"             { return symbol(sym.pr_borde); }
    "nombre"            { return symbol(sym.pr_nombre); }
    "fuente"            { return symbol(sym.pr_fuente); }
    "tam"               { return symbol(sym.pr_tam); }
    "negrita"           { return symbol(sym.pr_negrita); }
    "cursiva"           { return symbol(sym.pr_cursiva); }
    "maximo"            { return symbol(sym.pr_maximo); }
    "minimo"            { return symbol(sym.pr_minimo); }
    "accion"            { return symbol(sym.pr_accion); }
    "referencia"        { return symbol(sym.pr_referencia); }
    "path"              { return symbol(sym.pr_path); }
    "auto-reproduccion" { return symbol(sym.pr_autoreproduccion); }

    "verdadero"         { return symbol(sym.pr_verdadero); }
    "falso"             { return symbol(sym.pr_falso); }

    \n                  { yychar=1; }

    {ENTERO}            { return symbol(sym.entero); }
    {DECIMAL}           { return symbol(sym.decimal); }
    
    {ESPACIOS}          {}

    .                   {
                            otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, yytext(), "", "Este es un error lexico: "+yytext(), Constante.archivo, yyline, yychar);
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
                        otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, "Este es un error lexico: \\n", yyline, yychar); 
                        otros.Error.agregarError(err);
                        yychar=1;
                    }
    .                   {
                            otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, yytext(), Constante.ent_temporal.ambito, 
                            "El caracter: "+yytext()+" no pertenece al lenguaje", 
                            Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
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
                        otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, "Este es un error lexico: \\n", yyline, yychar);
                        otros.Error.agregarError(err); 
                        yychar=1;
                    }
    .                   {
                            otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, yytext(), Constante.ent_temporal.ambito, 
                            "El caracter: "+yytext()+" no pertenece al lenguaje", 
                            Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
                        }     
}

<TODO>
{
    "<"                { yybegin(YYINITIAL); if(ban==0) { return symbol(sym.nada, cadena); } else { return symbol(sym.todo, cadena); } }
    <<EOF>>            { yybegin(YYINITIAL); return symbol(sym.fin, cadena); }
    "#"                { ban=1; cadena+=String.valueOf(yytext()); }
    "##"               { yybegin(UNI_COMMENT1); }
    "#$"               { yybegin(MUL_COMMENT1); }
    [\r\t\f]           { }
    [ ]                { cadena+=String.valueOf(yytext()); }
    \"                 { ban=1; cadena+="\\\""; }
    \n                 { yychar=1; }
    [^"<""#" \n\r\t\f\"]+ { ban=1; cadena+=String.valueOf(yytext()); }
    .                   {
                            otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, yytext(), Constante.ent_temporal.ambito, 
                            "El caracter: "+yytext()+" no pertenece al lenguaje", 
                            Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
                        }
}

<LLAMADA>
{
    "}"             { yybegin(YYINITIAL); return symbol(sym.llamada, cadena); }
    [^"}"\n]+       { cadena+=String.valueOf(yytext()); }
    \n              { yychar=1; }
    .                   {
                            otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, yytext(), Constante.ent_temporal.ambito, 
                            "El caracter: "+yytext()+" no pertenece al lenguaje", 
                            Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
                        }
}

<UNI_COMMENT1>
{
    [\r\n|\n|\r]    { yybegin(TODO); }
    .               {}
}

<MUL_COMMENT1>
{
    "$#"            { yybegin(TODO); }
    \n              { yychar=1; }
    .               { }
}
