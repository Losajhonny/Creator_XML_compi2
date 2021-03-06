/* The following code was generated by JFlex 1.6.1 */

package gxml.analizador;

import java_cup.runtime.Symbol;
import otros.Constante;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>src/gxml/analizador/gxmlScanner.jflex</tt>
 */
public class gxmlScanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int CADENA = 2;
  public static final int CARACTER = 4;
  public static final int LLAMADA = 6;
  public static final int TODO = 8;
  public static final int UNI_COMMENT1 = 10;
  public static final int MUL_COMMENT1 = 12;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6, 6
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\5\1\7\1\10\1\6\22\0\1\51\1\0\1\13"+
    "\1\4\1\12\2\0\1\14\5\0\1\47\1\3\1\20\12\2\2\0"+
    "\1\17\1\21\1\16\2\0\1\30\1\43\1\34\1\35\1\32\1\41"+
    "\1\46\1\45\1\22\2\0\1\37\1\23\1\33\1\25\1\24\1\0"+
    "\1\26\1\40\1\27\1\42\1\31\1\0\1\36\1\44\2\0\1\50"+
    "\4\0\1\30\1\43\1\34\1\35\1\32\1\41\1\46\1\45\1\22"+
    "\2\0\1\37\1\23\1\33\1\25\1\24\1\0\1\26\1\40\1\27"+
    "\1\42\1\31\1\0\1\36\1\44\1\0\1\15\1\11\1\52\7\0"+
    "\1\7\252\0\2\22\115\0\1\40\u1ea8\0\1\7\1\7\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\7\0\1\1\1\2\1\3\1\1\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\13\1\1\14\3\1"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\16\1\26\2\27\1\30\1\31\1\16\1\32"+
    "\1\2\4\0\1\33\30\0\1\34\1\35\1\36\1\37"+
    "\1\40\1\41\1\2\10\0\1\42\32\0\1\43\1\0"+
    "\1\44\3\0\1\45\11\0\1\46\7\0\1\2\5\0"+
    "\1\47\1\50\11\0\1\51\3\0\1\52\1\0\1\53"+
    "\1\54\1\0\1\55\1\56\2\0\1\57\3\0\1\60"+
    "\1\61\6\0\1\62\7\0\1\63\1\64\1\65\1\0"+
    "\1\66\1\67\1\0\1\70\15\0\1\71\1\0\1\72"+
    "\1\73\1\74\3\0\1\75\1\0\1\76\3\0\1\77"+
    "\4\0\1\100";

  private static int [] zzUnpackAction() {
    int [] result = new int[238];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\53\0\126\0\201\0\254\0\327\0\u0102\0\u012d"+
    "\0\u012d\0\u0158\0\u0183\0\u012d\0\u012d\0\u012d\0\u012d\0\u012d"+
    "\0\u012d\0\u012d\0\u012d\0\u01ae\0\u01d9\0\u0204\0\u022f\0\u025a"+
    "\0\u0285\0\u02b0\0\u02db\0\u0306\0\u0331\0\u035c\0\u012d\0\u0387"+
    "\0\u03b2\0\u03dd\0\u012d\0\u0408\0\u012d\0\u012d\0\u012d\0\u0433"+
    "\0\u012d\0\u012d\0\u012d\0\u045e\0\u012d\0\u0489\0\u04b4\0\u012d"+
    "\0\u012d\0\u012d\0\u012d\0\u04df\0\u050a\0\u0535\0\u0560\0\u058b"+
    "\0\u012d\0\u05b6\0\u05e1\0\u060c\0\u0637\0\u0662\0\u068d\0\u06b8"+
    "\0\u06e3\0\u070e\0\u0739\0\u0764\0\u078f\0\u07ba\0\u07e5\0\u0810"+
    "\0\u083b\0\u0866\0\u0891\0\u08bc\0\u08e7\0\u0912\0\u093d\0\u0968"+
    "\0\u0993\0\u012d\0\u012d\0\u012d\0\u012d\0\u012d\0\u050a\0\u0535"+
    "\0\u09be\0\u09e9\0\u0a14\0\u0a3f\0\u0a6a\0\u0a95\0\u0ac0\0\u0aeb"+
    "\0\u012d\0\u0b16\0\u0b41\0\u0b6c\0\u0b97\0\u0bc2\0\u0bed\0\u0c18"+
    "\0\u0c43\0\u0c6e\0\u0c99\0\u0cc4\0\u0cef\0\u0d1a\0\u0d45\0\u0d70"+
    "\0\u0d9b\0\u0dc6\0\u0df1\0\u0e1c\0\u0e47\0\u0e72\0\u0e9d\0\u0ec8"+
    "\0\u0ef3\0\u0f1e\0\u0f49\0\u012d\0\u0f74\0\u012d\0\u0f9f\0\u0fca"+
    "\0\u0ff5\0\u012d\0\u1020\0\u104b\0\u1076\0\u10a1\0\u10cc\0\u10f7"+
    "\0\u1122\0\u114d\0\u1178\0\u012d\0\u11a3\0\u11ce\0\u11f9\0\u1224"+
    "\0\u124f\0\u127a\0\u12a5\0\u12a5\0\u12d0\0\u12fb\0\u1326\0\u1351"+
    "\0\u137c\0\u012d\0\u012d\0\u13a7\0\u13d2\0\u13fd\0\u1428\0\u1453"+
    "\0\u147e\0\u14a9\0\u14d4\0\u14ff\0\u012d\0\u152a\0\u1555\0\u1580"+
    "\0\u012d\0\u15ab\0\u012d\0\u012d\0\u15d6\0\u012d\0\u012d\0\u1601"+
    "\0\u162c\0\u1657\0\u1682\0\u16ad\0\u16d8\0\u012d\0\u012d\0\u1703"+
    "\0\u172e\0\u1759\0\u1784\0\u17af\0\u17da\0\u012d\0\u1805\0\u1830"+
    "\0\u185b\0\u1886\0\u18b1\0\u18dc\0\u1907\0\u012d\0\u012d\0\u012d"+
    "\0\u1932\0\u012d\0\u012d\0\u195d\0\u012d\0\u1988\0\u19b3\0\u19de"+
    "\0\u1a09\0\u1a34\0\u1a5f\0\u1a8a\0\u1ab5\0\u1ae0\0\u1b0b\0\u1b36"+
    "\0\u1b61\0\u1b8c\0\u012d\0\u1bb7\0\u012d\0\u012d\0\u012d\0\u1be2"+
    "\0\u1c0d\0\u1c38\0\u012d\0\u1c63\0\u012d\0\u1c8e\0\u1cb9\0\u1ce4"+
    "\0\u012d\0\u1d0f\0\u1d3a\0\u1d65\0\u1d90\0\u012d";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[238];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\10\1\11\1\12\1\10\1\13\1\14\1\11\1\0"+
    "\1\11\2\10\1\15\1\16\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\10\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\10"+
    "\1\41\1\10\1\42\1\43\4\10\1\11\1\10\5\44"+
    "\1\45\1\0\4\44\1\46\1\47\33\44\1\50\7\44"+
    "\1\51\1\0\4\44\1\52\1\53\33\44\1\50\2\44"+
    "\5\54\1\14\44\54\1\55\1\56\1\11\2\56\1\57"+
    "\1\14\1\11\1\56\1\11\2\56\1\60\3\56\1\61"+
    "\31\56\1\62\1\56\5\11\2\63\2\0\1\63\46\11"+
    "\1\14\3\0\1\11\1\64\40\11\55\0\1\12\1\65"+
    "\53\0\1\66\5\0\1\67\63\0\1\70\11\0\1\71"+
    "\37\0\1\72\5\0\1\73\11\0\1\74\40\0\1\75"+
    "\54\0\1\76\42\0\1\77\5\0\1\100\1\0\1\101"+
    "\53\0\1\102\1\103\2\0\1\104\2\0\1\105\42\0"+
    "\1\106\53\0\1\107\44\0\1\110\4\0\1\111\45\0"+
    "\1\112\14\0\1\113\40\0\1\114\1\0\1\115\42\0"+
    "\1\116\60\0\1\117\11\0\1\120\35\0\1\121\25\0"+
    "\5\44\2\0\4\44\2\0\33\44\1\0\2\44\13\0"+
    "\1\52\1\47\11\0\1\122\1\123\3\0\1\124\17\0"+
    "\5\54\1\0\44\54\1\0\1\56\1\0\2\56\3\0"+
    "\1\56\1\0\2\56\1\0\3\56\1\0\31\56\1\0"+
    "\1\56\4\0\1\125\5\0\1\126\44\0\1\63\50\0"+
    "\1\127\50\0\5\66\2\11\2\0\1\130\41\66\12\67"+
    "\1\131\40\67\24\0\1\132\61\0\1\133\55\0\1\134"+
    "\53\0\1\135\42\0\1\136\64\0\1\137\35\0\1\140"+
    "\51\0\1\141\65\0\1\142\50\0\1\143\52\0\1\144"+
    "\45\0\1\145\52\0\1\146\51\0\1\147\4\0\1\150"+
    "\50\0\1\151\44\0\1\152\75\0\1\153\37\0\1\154"+
    "\3\0\1\155\41\0\1\156\53\0\1\157\64\0\1\160"+
    "\51\0\1\161\51\0\1\162\45\0\1\163\46\0\1\164"+
    "\1\165\23\0\4\166\1\11\5\166\1\167\40\166\25\0"+
    "\1\170\47\0\1\171\52\0\1\172\57\0\1\173\70\0"+
    "\1\174\37\0\1\175\45\0\1\176\54\0\1\177\70\0"+
    "\1\200\27\0\1\201\55\0\1\202\52\0\1\203\62\0"+
    "\1\204\44\0\1\205\45\0\1\206\73\0\1\207\35\0"+
    "\1\210\53\0\1\211\50\0\1\212\65\0\1\213\37\0"+
    "\1\214\57\0\1\215\47\0\1\216\63\0\1\217\45\0"+
    "\1\220\54\0\1\221\42\0\1\222\25\0\4\67\1\223"+
    "\5\67\1\131\44\67\1\224\5\67\1\131\40\67\26\0"+
    "\1\225\47\0\1\226\52\0\1\227\51\0\1\230\56\0"+
    "\1\231\51\0\1\232\52\0\1\233\52\0\1\234\74\0"+
    "\1\235\33\0\1\236\52\0\1\237\52\0\1\240\50\0"+
    "\1\241\46\0\1\242\56\0\1\243\3\0\1\244\46\0"+
    "\1\245\46\0\1\246\64\0\1\247\46\0\1\250\47\0"+
    "\1\251\54\0\1\252\55\0\1\253\53\0\1\254\23\0"+
    "\1\166\75\0\1\255\50\0\1\256\52\0\1\257\50\0"+
    "\1\260\61\0\1\261\53\0\1\262\45\0\1\263\61\0"+
    "\1\264\50\0\1\265\45\0\1\266\56\0\1\267\47\0"+
    "\1\270\50\0\1\271\60\0\1\272\50\0\1\273\50\0"+
    "\1\274\60\0\1\275\47\0\1\276\50\0\1\277\54\0"+
    "\1\300\53\0\1\301\41\0\1\302\16\0\1\303\43\0"+
    "\1\304\52\0\1\305\50\0\1\306\52\0\1\307\61\0"+
    "\1\310\45\0\1\311\50\0\1\312\47\0\1\313\55\0"+
    "\1\314\50\0\1\315\61\0\1\316\51\0\1\317\51\0"+
    "\1\320\41\0\1\321\54\0\1\322\54\0\1\323\61\0"+
    "\1\324\44\0\1\325\45\0\1\326\52\0\1\327\52\0"+
    "\1\330\63\0\1\331\45\0\1\332\51\0\1\333\52\0"+
    "\1\334\52\0\1\335\55\0\1\336\52\0\1\337\56\0"+
    "\1\340\46\0\1\341\47\0\1\342\53\0\1\343\46\0"+
    "\1\344\67\0\1\345\50\0\1\346\45\0\1\347\64\0"+
    "\1\350\47\0\1\351\47\0\1\352\52\0\1\353\40\0"+
    "\1\354\55\0\1\355\60\0\1\356\17\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7611];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\7\0\2\11\2\1\10\11\13\1\1\11\3\1\1\11"+
    "\1\1\3\11\1\1\3\11\1\1\1\11\2\1\4\11"+
    "\1\1\4\0\1\11\30\0\5\11\2\1\10\0\1\11"+
    "\32\0\1\11\1\0\1\11\3\0\1\11\11\0\1\11"+
    "\7\0\1\1\5\0\2\11\11\0\1\11\3\0\1\11"+
    "\1\0\2\11\1\0\2\11\2\0\1\1\3\0\2\11"+
    "\6\0\1\11\7\0\3\11\1\0\2\11\1\0\1\11"+
    "\15\0\1\11\1\0\3\11\3\0\1\11\1\0\1\11"+
    "\3\0\1\11\4\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[238];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
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


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public gxmlScanner(java.io.Reader in) {
      yyline = 1;
    yychar = 1;
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 210) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
            switch (zzLexicalState) {
            case TODO: {
              yybegin(YYINITIAL); return symbol(sym.fin, cadena);
            }
            case 239: break;
            default:
          { return new java_cup.runtime.Symbol(sym.EOF); }
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, yytext(), "", "Este es un error lexico: "+yytext(), Constante.archivo, yyline, yychar);
                            otros.Error.agregarError(err);
            }
          case 65: break;
          case 2: 
            { 
            }
          case 66: break;
          case 3: 
            { return symbol(sym.entero);
            }
          case 67: break;
          case 4: 
            { yychar=1;
            }
          case 68: break;
          case 5: 
            { yybegin(CADENA); cadena="";
            }
          case 69: break;
          case 6: 
            { yybegin(CARACTER); cadena="";
            }
          case 70: break;
          case 7: 
            { yybegin(LLAMADA); cadena="";
            }
          case 71: break;
          case 8: 
            { yybegin(TODO); ban=0; cadena="";
            }
          case 72: break;
          case 9: 
            { return symbol(sym.open);
            }
          case 73: break;
          case 10: 
            { return symbol(sym.slash);
            }
          case 74: break;
          case 11: 
            { return symbol(sym.igual);
            }
          case 75: break;
          case 12: 
            { return symbol(sym.pr_x);
            }
          case 76: break;
          case 13: 
            { return symbol(sym.pr_y);
            }
          case 77: break;
          case 14: 
            { cadena+=String.valueOf(yytext());
            }
          case 78: break;
          case 15: 
            { otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, "Este es un error lexico: \\n", yyline, yychar); 
                        otros.Error.agregarError(err);
                        yychar=1;
            }
          case 79: break;
          case 16: 
            { yybegin(YYINITIAL); return symbol(sym.cadena, cadena);
            }
          case 80: break;
          case 17: 
            { cadena+=String.valueOf("\'");
            }
          case 81: break;
          case 18: 
            { cadena+=String.valueOf("\\");
            }
          case 82: break;
          case 19: 
            { otros.Error err = new otros.Error(Constante.GXML, Constante.LEXICO, "Este es un error lexico: \\n", yyline, yychar);
                        otros.Error.agregarError(err); 
                        yychar=1;
            }
          case 83: break;
          case 20: 
            { cadena+=String.valueOf("\"");
            }
          case 84: break;
          case 21: 
            { yybegin(YYINITIAL);  return symbol(sym.cadena, cadena);
            }
          case 85: break;
          case 22: 
            { yybegin(YYINITIAL); return symbol(sym.llamada, cadena);
            }
          case 86: break;
          case 23: 
            { ban=1; cadena+=String.valueOf(yytext());
            }
          case 87: break;
          case 24: 
            { ban=1; cadena+="\\\"";
            }
          case 88: break;
          case 25: 
            { yybegin(YYINITIAL); if(ban==0) { return symbol(sym.nada, cadena); } else { return symbol(sym.todo, cadena); }
            }
          case 89: break;
          case 26: 
            { yybegin(TODO);
            }
          case 90: break;
          case 27: 
            { return symbol(sym.pr_id);
            }
          case 91: break;
          case 28: 
            { cadena+=String.valueOf("\r");
            }
          case 92: break;
          case 29: 
            { cadena+=String.valueOf("\t");
            }
          case 93: break;
          case 30: 
            { cadena+=String.valueOf("\n");
            }
          case 94: break;
          case 31: 
            { yybegin(UNI_COMMENT1);
            }
          case 95: break;
          case 32: 
            { yybegin(MUL_COMMENT1);
            }
          case 96: break;
          case 33: 
            { return symbol(sym.decimal);
            }
          case 97: break;
          case 34: 
            { return symbol(sym.pr_tam);
            }
          case 98: break;
          case 35: 
            { return symbol(sym.pr_path);
            }
          case 99: break;
          case 36: 
            { return symbol(sym.pr_tipo);
            }
          case 100: break;
          case 37: 
            { return symbol(sym.pr_alto);
            }
          case 101: break;
          case 38: 
            { return symbol(sym.pr_dato);
            }
          case 102: break;
          case 39: 
            { return symbol(sym.pr_texto);
            }
          case 103: break;
          case 40: 
            { return symbol(sym.pr_ancho);
            }
          case 104: break;
          case 41: 
            { return symbol(sym.pr_color);
            }
          case 105: break;
          case 42: 
            { return symbol(sym.pr_falso);
            }
          case 106: break;
          case 43: 
            { return symbol(sym.pr_borde);
            }
          case 107: break;
          case 44: 
            { return symbol(sym.pr_boton);
            }
          case 108: break;
          case 45: 
            { return symbol(sym.pr_minimo);
            }
          case 109: break;
          case 46: 
            { return symbol(sym.pr_maximo);
            }
          case 110: break;
          case 47: 
            { return symbol(sym.pr_accion);
            }
          case 111: break;
          case 48: 
            { return symbol(sym.pr_enviar);
            }
          case 112: break;
          case 49: 
            { return symbol(sym.pr_nombre);
            }
          case 113: break;
          case 50: 
            { return symbol(sym.pr_fuente);
            }
          case 114: break;
          case 51: 
            { return symbol(sym.pr_ventana);
            }
          case 115: break;
          case 52: 
            { return symbol(sym.pr_negrita);
            }
          case 116: break;
          case 53: 
            { return symbol(sym.pr_control);
            }
          case 117: break;
          case 54: 
            { return symbol(sym.pr_cursiva);
            }
          case 118: break;
          case 55: 
            { return symbol(sym.pr_defecto);
            }
          case 119: break;
          case 56: 
            { return symbol(sym.pr_importar);
            }
          case 120: break;
          case 57: 
            { return symbol(sym.pr_verdadero);
            }
          case 121: break;
          case 58: 
            { return symbol(sym.pr_listadato);
            }
          case 122: break;
          case 59: 
            { return symbol(sym.pr_multimedia);
            }
          case 123: break;
          case 60: 
            { return symbol(sym.pr_referencia);
            }
          case 124: break;
          case 61: 
            { return symbol(sym.pr_contenedor);
            }
          case 125: break;
          case 62: 
            { return symbol(sym.pr_accionfinal);
            }
          case 126: break;
          case 63: 
            { return symbol(sym.pr_accioninicial);
            }
          case 127: break;
          case 64: 
            { return symbol(sym.pr_autoreproduccion);
            }
          case 128: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
