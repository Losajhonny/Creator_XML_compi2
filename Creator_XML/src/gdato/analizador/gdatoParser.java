
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package gdato.analizador;

import entorno.Entorno;
import java_cup.runtime.*;
import gdato.arbol.*;
import gdato.arbol.Valor.Tgdato;
import otros.Constante;
import java.util.LinkedList;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class gdatoParser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public gdatoParser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public gdatoParser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public gdatoParser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\017\000\002\002\004\000\002\002\003\000\002\002" +
    "\002\000\002\003\012\000\002\003\011\000\002\004\004" +
    "\000\002\004\003\000\002\005\012\000\002\005\011\000" +
    "\002\005\007\000\002\006\004\000\002\006\003\000\002" +
    "\007\012\000\002\007\012\000\002\007\007" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\065\000\006\002\uffff\004\004\001\002\000\004\007" +
    "\010\001\002\000\004\002\007\001\002\000\004\002\000" +
    "\001\002\000\004\002\001\001\002\000\004\005\011\001" +
    "\002\000\006\003\013\004\014\001\002\000\006\003\013" +
    "\004\063\001\002\000\004\004\057\001\002\000\006\006" +
    "\017\010\016\001\002\000\006\003\ufffb\004\ufffb\001\002" +
    "\000\004\005\022\001\002\000\004\007\020\001\002\000" +
    "\004\005\021\001\002\000\004\002\ufffd\001\002\000\006" +
    "\003\023\004\024\001\002\000\004\004\053\001\002\000" +
    "\006\006\050\013\032\001\002\000\006\003\ufff6\004\ufff6" +
    "\001\002\000\006\003\023\004\027\001\002\000\006\006" +
    "\031\013\032\001\002\000\006\003\ufff7\004\ufff7\001\002" +
    "\000\004\010\046\001\002\000\004\005\033\001\002\000" +
    "\006\011\034\012\035\001\002\000\004\004\042\001\002" +
    "\000\004\004\036\001\002\000\004\006\037\001\002\000" +
    "\004\013\040\001\002\000\004\005\041\001\002\000\006" +
    "\003\ufff4\004\ufff4\001\002\000\004\006\043\001\002\000" +
    "\004\013\044\001\002\000\004\005\045\001\002\000\006" +
    "\003\ufff5\004\ufff5\001\002\000\004\005\047\001\002\000" +
    "\006\003\ufffa\004\ufffa\001\002\000\004\010\051\001\002" +
    "\000\004\005\052\001\002\000\006\003\ufff9\004\ufff9\001" +
    "\002\000\004\006\054\001\002\000\004\013\055\001\002" +
    "\000\004\005\056\001\002\000\006\003\ufff3\004\ufff3\001" +
    "\002\000\004\006\060\001\002\000\004\010\061\001\002" +
    "\000\004\005\062\001\002\000\006\003\ufff8\004\ufff8\001" +
    "\002\000\006\006\065\010\016\001\002\000\006\003\ufffc" +
    "\004\ufffc\001\002\000\004\007\066\001\002\000\004\005" +
    "\067\001\002\000\004\002\ufffe\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\065\000\006\002\004\003\005\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\004\011\005\014\001\001\000" +
    "\004\005\063\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\006\025\007\024" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\007\027\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$gdatoParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$gdatoParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$gdatoParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    public static AstGdato Syntaxtree = null;

    /**
     * Método al que se llama automáticamente ante algún error sintactico.
     **/ 
    public void syntax_error(Symbol s) {
        StringBuilder m = new StringBuilder("Error Sintactico");
        int yline = s.left, ycol = s.right;

        if(s.left >= 0) {
        	m.append(" en la Linea " + (s.left));
        	if(s.right >= 0) {
        		m.append(" Columna " + (s.right));
        	}
        	m.append(". No se esperaba este componente: " + s.value + ".");
        }

        Object valor = (s.value != null)? s.value: "Fin de archivo";
            otros.Error err = new otros.Error(Constante.GDATO, Constante.SINTACTICO, "", Constante.ent_temporal.ambito, " No se esperaba este componente: " + valor, Constante.archivo, s.left, s.right);
            otros.Error.agregarError(err);

        //Constante.consola += String.valueOf(m) + "\n";
    }

    /**
     * Método al que se llama automáticamente ante algún error sintáctico 
     * en el que ya no es posible una recuperación de errores.
     **/ 
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception {
        syntax_error(s);
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$gdatoParser$actions {
  private final gdatoParser parser;

  /** Constructor */
  CUP$gdatoParser$actions(gdatoParser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$gdatoParser$do_action_part00000000(
    int                        CUP$gdatoParser$act_num,
    java_cup.runtime.lr_parser CUP$gdatoParser$parser,
    java.util.Stack            CUP$gdatoParser$stack,
    int                        CUP$gdatoParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$gdatoParser$result;

      /* select the action based on the action number */
      switch (CUP$gdatoParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= S EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).value;
		RESULT = start_val;
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$gdatoParser$parser.done_parsing();
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // S ::= LISTA 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).right;
		AstGdato n = (AstGdato)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.peek()).value;
		Syntaxtree = n;
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("S",0, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // S ::= 
            {
              Object RESULT =null;
		Syntaxtree = new AstGdato();
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("S",0, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // LISTA ::= open pr_lista close LPRINCIPAL open slash pr_lista close 
            {
              AstGdato RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).right;
		String n1 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).value;
		int n2left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).left;
		int n2right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).right;
		LinkedList<Principal> n2 = (LinkedList<Principal>)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).value;
		RESULT = new AstGdato(n2, n1left, n1right);
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("LISTA",1, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-7)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // LISTA ::= open pr_lista close open slash pr_lista close 
            {
              AstGdato RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-5)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-5)).right;
		String n1 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-5)).value;
		RESULT = new AstGdato(n1left, n1right);
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("LISTA",1, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // LPRINCIPAL ::= LPRINCIPAL PRINCIPAL 
            {
              LinkedList<Principal> RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).right;
		LinkedList<Principal> n1 = (LinkedList<Principal>)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).value;
		int n2left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).left;
		int n2right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).right;
		Principal n2 = (Principal)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.peek()).value;
		RESULT = n1; if(n2 != null) { RESULT.add(n2); }
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("LPRINCIPAL",2, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // LPRINCIPAL ::= PRINCIPAL 
            {
              LinkedList<Principal> RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).right;
		Principal n1 = (Principal)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.peek()).value;
		RESULT = new LinkedList(); if(n1 != null) { RESULT.add(n1); }
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("LPRINCIPAL",2, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // PRINCIPAL ::= open pr_principal close LVALORES open slash pr_principal close 
            {
              Principal RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).right;
		String n1 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).value;
		int n2left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).left;
		int n2right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).right;
		LinkedList<Valor> n2 = (LinkedList<Valor>)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).value;
		RESULT = new Principal(n2, n1left, n1right);
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("PRINCIPAL",3, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-7)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // PRINCIPAL ::= open pr_principal close open slash pr_principal close 
            {
              Principal RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-5)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-5)).right;
		String n1 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-5)).value;
		RESULT = new Principal(n1left, n1right);
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("PRINCIPAL",3, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // PRINCIPAL ::= error open slash pr_principal close 
            {
              Principal RESULT =null;

              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("PRINCIPAL",3, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // LVALORES ::= LVALORES VALOR 
            {
              LinkedList<Valor> RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).right;
		LinkedList<Valor> n1 = (LinkedList<Valor>)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).value;
		int n2left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).left;
		int n2right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).right;
		Valor n2 = (Valor)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.peek()).value;
		RESULT = n1; if(n2 != null) { RESULT.add(n2); }
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("LVALORES",4, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // LVALORES ::= VALOR 
            {
              LinkedList<Valor> RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()).right;
		Valor n1 = (Valor)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.peek()).value;
		RESULT = new LinkedList(); if(n1 != null) { RESULT.add(n1); }
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("LVALORES",4, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // VALOR ::= open id close numero open slash id close 
            {
              Valor RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).right;
		String n1 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).value;
		int n2left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).left;
		int n2right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).right;
		String n2 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).value;
		int n3left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).left;
		int n3right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).right;
		String n3 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).value;
		RESULT = new Valor(n1, n2, Tgdato.NUMERO, n3, n1left, n1right);
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("VALOR",5, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-7)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // VALOR ::= open id close cadena open slash id close 
            {
              Valor RESULT =null;
		int n1left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).left;
		int n1right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).right;
		String n1 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-6)).value;
		int n2left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).left;
		int n2right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).right;
		String n2 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)).value;
		int n3left = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).left;
		int n3right = ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).right;
		String n3 = (String)((java_cup.runtime.Symbol) CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-1)).value;
		RESULT = new Valor(n1, n2, Tgdato.CADENA, n3, n1left, n1right);
              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("VALOR",5, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-7)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // VALOR ::= error open slash id close 
            {
              Valor RESULT =null;

              CUP$gdatoParser$result = parser.getSymbolFactory().newSymbol("VALOR",5, ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.elementAt(CUP$gdatoParser$top-4)), ((java_cup.runtime.Symbol)CUP$gdatoParser$stack.peek()), RESULT);
            }
          return CUP$gdatoParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$gdatoParser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$gdatoParser$do_action(
    int                        CUP$gdatoParser$act_num,
    java_cup.runtime.lr_parser CUP$gdatoParser$parser,
    java.util.Stack            CUP$gdatoParser$stack,
    int                        CUP$gdatoParser$top)
    throws java.lang.Exception
    {
              return CUP$gdatoParser$do_action_part00000000(
                               CUP$gdatoParser$act_num,
                               CUP$gdatoParser$parser,
                               CUP$gdatoParser$stack,
                               CUP$gdatoParser$top);
    }
}

}
