/* The following code was generated by JFlex 1.4.1 on 11/03/19 06:40 PM */

package fs.analizador;

import java.io.*;   
import javax.swing.text.Segment;   
   
import org.fife.ui.rsyntaxtextarea.*;   
   

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 11/03/19 06:40 PM from the specification file
 * <tt>src/fs/analizador/fsSyntax.jflex</tt>
 */
public class FsSyntax extends AbstractJFlexCTokenMaker {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int MLC = 1;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\7\1\10\1\0\1\7\1\6\22\0\1\7\1\43\1\4"+
    "\1\0\1\0\1\44\1\46\1\3\2\13\1\12\1\47\1\6\1\50"+
    "\1\6\1\11\12\2\1\43\1\6\1\51\1\45\1\52\1\43\1\0"+
    "\1\26\1\35\1\17\1\23\1\24\1\14\1\34\1\42\1\20\1\37"+
    "\1\1\1\27\1\31\1\16\1\21\1\32\1\40\1\30\1\22\1\25"+
    "\1\15\1\33\1\1\1\36\1\41\1\1\1\13\1\5\1\13\1\43"+
    "\1\1\1\0\1\26\1\35\1\17\1\23\1\24\1\14\1\34\1\42"+
    "\1\20\1\37\1\1\1\27\1\31\1\16\1\21\1\32\1\40\1\30"+
    "\1\22\1\25\1\15\1\33\1\1\1\36\1\41\1\1\1\13\1\53"+
    "\1\13\1\43\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\2\1\1\2\1\3\1\4\1\5\1\6\2\7"+
    "\1\10\16\1\7\7\1\11\1\12\2\11\1\13\1\14"+
    "\1\3\1\0\1\15\2\4\1\16\1\17\11\1\1\20"+
    "\14\1\1\21\1\22\1\23\22\1\1\20\207\1";

  private static int [] zzUnpackAction() {
    int [] result = new int[225];
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
    "\0\0\0\54\0\130\0\204\0\260\0\334\0\u0108\0\u0134"+
    "\0\130\0\u0160\0\u018c\0\130\0\u01b8\0\u01e4\0\u0210\0\u023c"+
    "\0\u0268\0\u0294\0\u02c0\0\u02ec\0\u0318\0\u0344\0\u0370\0\u039c"+
    "\0\u03c8\0\u03f4\0\130\0\u0420\0\u044c\0\u0478\0\u04a4\0\u04d0"+
    "\0\u04fc\0\u0528\0\130\0\130\0\u0554\0\u0580\0\130\0\u05ac"+
    "\0\u05d8\0\130\0\u0604\0\u0630\0\u065c\0\130\0\u0688\0\u06b4"+
    "\0\u06e0\0\u070c\0\u0738\0\u0764\0\u0790\0\u07bc\0\u07e8\0\u0814"+
    "\0\u0840\0\u086c\0\u0898\0\u08c4\0\u08f0\0\u091c\0\u0948\0\u0974"+
    "\0\u09a0\0\u09cc\0\u09f8\0\u0a24\0\130\0\130\0\130\0\u0a50"+
    "\0\u0a7c\0\u0aa8\0\u0ad4\0\u0b00\0\u0b2c\0\u0b58\0\u0b84\0\u0bb0"+
    "\0\u0bdc\0\u0c08\0\u0c34\0\u0c60\0\u0c8c\0\u0cb8\0\u0ce4\0\u0d10"+
    "\0\u0d3c\0\204\0\u0d68\0\u0d94\0\u0dc0\0\u0dec\0\u0e18\0\u0e44"+
    "\0\u0e70\0\u0e9c\0\u0ec8\0\u0ef4\0\u0f20\0\u0f4c\0\u0f78\0\u0fa4"+
    "\0\u0fd0\0\u0ffc\0\u1028\0\u0814\0\u1054\0\u1080\0\u10ac\0\u10d8"+
    "\0\u1104\0\u1130\0\u115c\0\u1188\0\u11b4\0\u11e0\0\u120c\0\u1238"+
    "\0\u1264\0\u1290\0\u12bc\0\u12e8\0\u1314\0\u1340\0\u136c\0\u1398"+
    "\0\u13c4\0\u13f0\0\u141c\0\u1448\0\u1474\0\u14a0\0\u14cc\0\u14f8"+
    "\0\u1524\0\u1550\0\u157c\0\u15a8\0\u15d4\0\u1600\0\u162c\0\u1658"+
    "\0\u1684\0\u16b0\0\u16dc\0\u1708\0\u1734\0\u1760\0\u178c\0\u17b8"+
    "\0\u17e4\0\u1810\0\u183c\0\u1868\0\u1894\0\u18c0\0\u18ec\0\u1918"+
    "\0\u1944\0\u1970\0\u199c\0\u19c8\0\u19f4\0\u1a20\0\u1a4c\0\u1a78"+
    "\0\u1aa4\0\u1ad0\0\u1afc\0\u1b28\0\u1b54\0\u1b80\0\u1bac\0\u1bd8"+
    "\0\u1c04\0\u1c30\0\u1c5c\0\u1c88\0\u1cb4\0\u1ce0\0\u1d0c\0\u1d38"+
    "\0\u1d64\0\u1d90\0\u1dbc\0\u1de8\0\u1e14\0\u1e40\0\u1e6c\0\u1e98"+
    "\0\u1ec4\0\u1ef0\0\u1f1c\0\u1f48\0\u1f74\0\u1fa0\0\u1fcc\0\u1ff8"+
    "\0\u2024\0\u2050\0\u207c\0\u20a8\0\u20d4\0\u2100\0\u212c\0\u2158"+
    "\0\u2184\0\u21b0\0\u21dc\0\u2208\0\u2234\0\u2260\0\u228c\0\u22b8"+
    "\0\u22e4\0\u2310\0\u233c\0\u2368\0\u2394\0\u23c0\0\u23ec\0\u2418"+
    "\0\u2444";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[225];
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
    "\1\3\1\4\1\5\1\6\1\7\2\3\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\4\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\4\1\24\1\25\1\26\1\27"+
    "\1\30\1\4\1\31\1\4\1\32\5\4\1\33\2\13"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\10\42\1\43"+
    "\1\44\1\45\41\42\55\0\2\4\11\0\27\4\11\0"+
    "\2\46\1\5\2\0\1\46\6\0\27\46\11\0\3\6"+
    "\1\47\1\6\1\50\2\6\1\51\43\6\4\7\1\52"+
    "\1\53\2\7\1\54\43\7\7\0\1\10\55\0\1\55"+
    "\1\56\32\0\1\33\53\0\1\33\7\0\2\4\11\0"+
    "\1\4\1\57\2\4\1\60\5\4\1\61\14\4\12\0"+
    "\2\4\11\0\1\4\1\62\25\4\12\0\2\4\11\0"+
    "\12\4\1\63\1\4\1\64\12\4\12\0\2\4\11\0"+
    "\2\4\1\65\12\4\1\66\11\4\12\0\2\4\11\0"+
    "\21\4\1\67\5\4\12\0\2\4\11\0\4\4\1\70"+
    "\3\4\1\71\16\4\12\0\2\4\11\0\10\4\1\72"+
    "\16\4\12\0\2\4\11\0\5\4\1\73\21\4\12\0"+
    "\2\4\11\0\6\4\1\74\4\4\1\75\13\4\12\0"+
    "\2\4\11\0\10\4\1\76\16\4\12\0\2\4\11\0"+
    "\10\4\1\77\16\4\12\0\2\4\11\0\4\4\1\100"+
    "\5\4\1\101\14\4\12\0\2\4\11\0\10\4\1\102"+
    "\1\4\1\103\14\4\12\0\2\4\11\0\1\4\1\104"+
    "\25\4\57\0\1\33\52\0\1\33\1\0\1\33\51\0"+
    "\1\33\2\0\1\33\54\0\1\13\54\0\1\13\54\0"+
    "\1\33\10\42\2\0\52\42\1\0\1\105\42\42\3\46"+
    "\2\0\1\46\6\0\27\46\11\0\3\50\1\106\4\50"+
    "\1\0\43\50\3\51\1\47\1\51\1\0\46\51\10\54"+
    "\1\0\47\54\1\107\1\53\46\54\10\55\1\0\43\55"+
    "\1\0\2\4\11\0\2\4\1\110\24\4\12\0\2\4"+
    "\11\0\13\4\1\111\13\4\12\0\2\4\11\0\13\4"+
    "\1\63\13\4\12\0\2\4\11\0\13\4\1\112\13\4"+
    "\12\0\2\4\11\0\6\4\1\112\20\4\12\0\2\4"+
    "\11\0\10\4\1\113\16\4\12\0\2\4\11\0\17\4"+
    "\1\114\7\4\12\0\2\4\11\0\16\4\1\115\10\4"+
    "\12\0\2\4\11\0\11\4\1\116\15\4\12\0\2\4"+
    "\11\0\2\4\1\112\24\4\12\0\2\4\11\0\13\4"+
    "\1\117\13\4\12\0\2\4\11\0\1\120\5\4\1\74"+
    "\2\4\1\121\15\4\12\0\2\4\11\0\7\4\1\122"+
    "\17\4\12\0\2\4\11\0\3\4\1\123\23\4\12\0"+
    "\2\4\11\0\3\4\1\124\14\4\1\125\6\4\12\0"+
    "\2\4\11\0\10\4\1\126\16\4\12\0\2\4\11\0"+
    "\7\4\1\127\1\4\1\130\15\4\12\0\2\4\11\0"+
    "\2\4\1\131\24\4\12\0\2\4\11\0\16\4\1\132"+
    "\3\4\1\131\4\4\12\0\2\4\11\0\14\4\1\133"+
    "\12\4\12\0\2\4\11\0\14\4\1\132\12\4\12\0"+
    "\2\4\11\0\6\4\1\134\20\4\12\0\2\4\11\0"+
    "\3\4\1\135\23\4\12\0\2\4\11\0\11\4\1\136"+
    "\15\4\12\0\2\4\11\0\5\4\1\132\21\4\12\0"+
    "\2\4\11\0\12\4\1\137\14\4\12\0\2\4\11\0"+
    "\10\4\1\140\16\4\12\0\2\4\11\0\5\4\1\141"+
    "\6\4\1\142\12\4\12\0\2\4\11\0\10\4\1\143"+
    "\16\4\12\0\2\4\11\0\10\4\1\144\16\4\12\0"+
    "\2\4\11\0\10\4\1\145\16\4\12\0\2\4\11\0"+
    "\10\4\1\146\16\4\12\0\2\4\11\0\5\4\1\147"+
    "\21\4\12\0\2\4\11\0\10\4\1\150\16\4\12\0"+
    "\2\4\11\0\10\4\1\151\1\4\1\152\1\153\13\4"+
    "\12\0\2\4\11\0\1\4\1\154\25\4\12\0\2\4"+
    "\11\0\14\4\1\155\12\4\12\0\2\4\11\0\1\4"+
    "\1\156\25\4\12\0\2\4\11\0\5\4\1\157\21\4"+
    "\12\0\2\4\11\0\4\4\1\160\22\4\12\0\2\4"+
    "\11\0\7\4\1\161\17\4\12\0\2\4\11\0\3\4"+
    "\1\162\23\4\12\0\2\4\11\0\4\4\1\163\22\4"+
    "\12\0\2\4\11\0\14\4\1\162\12\4\12\0\2\4"+
    "\11\0\14\4\1\164\12\4\12\0\2\4\11\0\14\4"+
    "\1\165\12\4\12\0\2\4\11\0\14\4\1\166\12\4"+
    "\12\0\2\4\11\0\4\4\1\167\22\4\12\0\2\4"+
    "\11\0\2\4\1\170\24\4\12\0\2\4\11\0\3\4"+
    "\1\171\23\4\12\0\2\4\11\0\3\4\1\172\23\4"+
    "\12\0\2\4\11\0\2\4\1\173\24\4\12\0\2\4"+
    "\11\0\6\4\1\132\20\4\12\0\2\4\11\0\2\4"+
    "\1\174\24\4\12\0\2\4\11\0\14\4\1\136\12\4"+
    "\12\0\2\4\11\0\14\4\1\175\12\4\12\0\2\4"+
    "\11\0\4\4\1\176\22\4\12\0\2\4\11\0\20\4"+
    "\1\177\6\4\12\0\2\4\11\0\3\4\1\200\23\4"+
    "\12\0\2\4\11\0\14\4\1\201\12\4\12\0\2\4"+
    "\11\0\15\4\1\112\11\4\12\0\2\4\11\0\12\4"+
    "\1\202\14\4\12\0\2\4\11\0\12\4\1\103\14\4"+
    "\12\0\2\4\11\0\5\4\1\203\21\4\12\0\2\4"+
    "\11\0\3\4\1\204\1\205\2\4\1\206\1\4\1\207"+
    "\1\210\1\4\1\211\2\4\1\212\1\4\1\213\5\4"+
    "\12\0\2\4\11\0\11\4\1\214\15\4\12\0\2\4"+
    "\11\0\11\4\1\162\15\4\12\0\2\4\11\0\15\4"+
    "\1\214\11\4\12\0\2\4\11\0\10\4\1\215\16\4"+
    "\12\0\2\4\11\0\3\4\1\216\23\4\12\0\2\4"+
    "\11\0\11\4\1\112\15\4\12\0\2\4\11\0\10\4"+
    "\1\103\16\4\12\0\2\4\11\0\7\4\1\217\17\4"+
    "\12\0\2\4\11\0\20\4\1\162\6\4\12\0\2\4"+
    "\11\0\3\4\1\132\23\4\12\0\2\4\11\0\22\4"+
    "\1\220\4\4\12\0\2\4\11\0\10\4\1\132\16\4"+
    "\12\0\2\4\11\0\2\4\1\162\24\4\12\0\2\4"+
    "\11\0\7\4\1\221\17\4\12\0\2\4\11\0\2\4"+
    "\1\132\24\4\12\0\2\4\11\0\5\4\1\222\4\4"+
    "\1\223\14\4\12\0\2\4\11\0\15\4\1\224\11\4"+
    "\12\0\2\4\11\0\10\4\1\225\16\4\12\0\2\4"+
    "\11\0\10\4\1\226\16\4\12\0\2\4\11\0\14\4"+
    "\1\227\12\4\12\0\2\4\11\0\10\4\1\230\16\4"+
    "\12\0\2\4\11\0\4\4\1\231\3\4\1\232\16\4"+
    "\12\0\2\4\11\0\5\4\1\233\21\4\12\0\2\4"+
    "\11\0\4\4\1\103\22\4\12\0\2\4\11\0\14\4"+
    "\1\234\12\4\12\0\2\4\11\0\4\4\1\235\22\4"+
    "\12\0\2\4\11\0\10\4\1\236\16\4\12\0\2\4"+
    "\11\0\15\4\1\237\11\4\12\0\2\4\11\0\10\4"+
    "\1\240\16\4\12\0\2\4\11\0\2\4\1\241\24\4"+
    "\12\0\2\4\11\0\23\4\1\242\3\4\12\0\2\4"+
    "\11\0\12\4\1\243\14\4\12\0\2\4\11\0\6\4"+
    "\1\244\20\4\12\0\2\4\11\0\22\4\1\172\4\4"+
    "\12\0\2\4\11\0\10\4\1\242\3\4\1\245\12\4"+
    "\12\0\2\4\11\0\16\4\1\246\10\4\12\0\2\4"+
    "\11\0\7\4\1\247\17\4\12\0\2\4\11\0\2\4"+
    "\1\250\24\4\12\0\2\4\11\0\11\4\1\163\15\4"+
    "\12\0\2\4\11\0\16\4\1\251\10\4\12\0\2\4"+
    "\11\0\5\4\1\252\21\4\12\0\2\4\11\0\2\4"+
    "\1\253\24\4\12\0\2\4\11\0\13\4\1\132\13\4"+
    "\12\0\2\4\11\0\14\4\1\112\12\4\12\0\2\4"+
    "\11\0\11\4\1\254\15\4\12\0\2\4\11\0\12\4"+
    "\1\255\14\4\12\0\2\4\11\0\20\4\1\256\6\4"+
    "\12\0\2\4\11\0\16\4\1\257\10\4\12\0\2\4"+
    "\11\0\12\4\1\260\14\4\12\0\2\4\11\0\14\4"+
    "\1\261\12\4\12\0\2\4\11\0\10\4\1\112\16\4"+
    "\12\0\2\4\11\0\11\4\1\262\15\4\12\0\2\4"+
    "\11\0\5\4\1\263\21\4\12\0\2\4\11\0\2\4"+
    "\1\264\24\4\12\0\2\4\11\0\11\4\1\200\15\4"+
    "\12\0\2\4\11\0\10\4\1\265\3\4\1\266\12\4"+
    "\12\0\2\4\11\0\11\4\1\207\15\4\12\0\2\4"+
    "\11\0\10\4\1\203\16\4\12\0\2\4\11\0\13\4"+
    "\1\267\13\4\12\0\2\4\11\0\25\4\1\270\1\4"+
    "\12\0\2\4\11\0\5\4\1\271\21\4\12\0\2\4"+
    "\11\0\12\4\1\252\14\4\12\0\2\4\11\0\14\4"+
    "\1\272\12\4\12\0\2\4\11\0\12\4\1\132\14\4"+
    "\12\0\2\4\11\0\2\4\1\273\24\4\12\0\2\4"+
    "\11\0\5\4\1\274\21\4\12\0\2\4\11\0\10\4"+
    "\1\275\16\4\12\0\2\4\11\0\7\4\1\276\17\4"+
    "\12\0\2\4\11\0\7\4\1\277\17\4\12\0\2\4"+
    "\11\0\2\4\1\300\1\4\1\301\3\4\1\302\16\4"+
    "\12\0\2\4\11\0\10\4\1\303\16\4\12\0\2\4"+
    "\11\0\13\4\1\304\13\4\12\0\2\4\11\0\20\4"+
    "\1\305\6\4\12\0\2\4\11\0\10\4\1\306\16\4"+
    "\12\0\2\4\11\0\1\4\1\307\25\4\12\0\2\4"+
    "\11\0\5\4\1\310\21\4\12\0\2\4\11\0\7\4"+
    "\1\132\17\4\12\0\2\4\11\0\11\4\1\311\15\4"+
    "\12\0\2\4\11\0\7\4\1\312\17\4\12\0\2\4"+
    "\11\0\2\4\1\313\24\4\12\0\2\4\11\0\12\4"+
    "\1\314\14\4\12\0\2\4\11\0\6\4\1\315\20\4"+
    "\12\0\2\4\11\0\3\4\1\316\23\4\12\0\2\4"+
    "\11\0\15\4\1\317\11\4\12\0\2\4\11\0\4\4"+
    "\1\320\22\4\12\0\2\4\11\0\5\4\1\103\21\4"+
    "\12\0\2\4\11\0\1\4\1\321\25\4\12\0\2\4"+
    "\11\0\21\4\1\322\5\4\12\0\2\4\11\0\7\4"+
    "\1\323\17\4\12\0\2\4\11\0\11\4\1\312\15\4"+
    "\12\0\2\4\11\0\21\4\1\324\5\4\12\0\2\4"+
    "\11\0\24\4\1\325\2\4\12\0\2\4\11\0\15\4"+
    "\1\326\11\4\12\0\2\4\11\0\13\4\1\200\13\4"+
    "\12\0\2\4\11\0\10\4\1\327\16\4\12\0\2\4"+
    "\11\0\14\4\1\200\12\4\12\0\2\4\11\0\1\4"+
    "\1\330\25\4\12\0\2\4\11\0\10\4\1\331\16\4"+
    "\12\0\2\4\11\0\12\4\1\332\14\4\12\0\2\4"+
    "\11\0\10\4\1\333\16\4\12\0\2\4\11\0\14\4"+
    "\1\334\12\4\12\0\2\4\11\0\14\4\1\335\12\4"+
    "\12\0\2\4\11\0\11\4\1\264\15\4\12\0\2\4"+
    "\11\0\4\4\1\336\22\4\12\0\2\4\11\0\3\4"+
    "\1\337\23\4\12\0\2\4\11\0\3\4\1\112\23\4"+
    "\12\0\2\4\11\0\26\4\1\340\12\0\2\4\11\0"+
    "\4\4\1\341\22\4\12\0\2\4\11\0\17\4\1\112"+
    "\7\4\11\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[9328];
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
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\5\1\1\11\2\1\1\11\16\1\1\11"+
    "\7\1\2\11\2\1\1\11\1\1\1\0\1\11\3\1"+
    "\1\11\26\1\3\11\232\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[225];
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

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

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

  /* user code: */
   
   /**   
    * Constructor.  This must be here because JFlex does not generate a   
    * no-parameter constructor.   
    */   
   public FsSyntax() {   
   }   
   
   /**   
    * Adds the token specified to the current linked list of tokens.   
    *   
    * @param tokenType The token's type.   
    * @see #addToken(int, int, int)   
    */   
   private void addHyperlinkToken(int start, int end, int tokenType) {   
      int so = start + offsetShift;   
      addToken(zzBuffer, start,end, tokenType, so, true);   
   }   
   
   /**   
    * Adds the token specified to the current linked list of tokens.   
    *   
    * @param tokenType The token's type.   
    */   
   private void addToken(int tokenType) {   
      addToken(zzStartRead, zzMarkedPos-1, tokenType);   
   }   
   
   /**   
    * Adds the token specified to the current linked list of tokens.   
    *   
    * @param tokenType The token's type.   
    * @see #addHyperlinkToken(int, int, int)   
    */   
   private void addToken(int start, int end, int tokenType) {   
      int so = start + offsetShift;   
      addToken(zzBuffer, start,end, tokenType, so, false);   
   }   
   
   /**   
    * Adds the token specified to the current linked list of tokens.   
    *   
    * @param array The character array.   
    * @param start The starting offset in the array.   
    * @param end The ending offset in the array.   
    * @param tokenType The token's type.   
    * @param startOffset The offset in the document at which this token   
    *        occurs.   
    * @param hyperlink Whether this token is a hyperlink.   
    */   
   public void addToken(char[] array, int start, int end, int tokenType,   
                  int startOffset, boolean hyperlink) {   
      super.addToken(array, start,end, tokenType, startOffset, hyperlink);   
      zzStartRead = zzMarkedPos;   
   }   
   
   /**   
    * Returns the text to place at the beginning and end of a   
    * line to "comment" it in a this programming language.   
    *   
    * @return The start and end strings to add to a line to "comment"   
    *         it out.   
    */   
   public String[] getLineCommentStartAndEnd() {   
      return new String[] { "##", null };   
   }   
   
   /**   
    * Returns the first token in the linked list of tokens generated   
    * from <code>text</code>.  This method must be implemented by   
    * subclasses so they can correctly implement syntax highlighting.   
    *   
    * @param text The text from which to get tokens.   
    * @param initialTokenType The token type we should start with.   
    * @param startOffset The offset into the document at which   
    *        <code>text</code> starts.   
    * @return The first <code>Token</code> in a linked list representing   
    *         the syntax highlighted text.   
    */   
   public Token getTokenList(Segment text, int initialTokenType, int startOffset) {   
   
      resetTokenList();   
      this.offsetShift = -text.offset + startOffset;   
   
      // Start off in the proper state.   
      int state = Token.NULL;   
      switch (initialTokenType) {   
                  case Token.COMMENT_MULTILINE:   
            state = MLC;   
            start = text.offset;   
            break;   
   
         /* No documentation comments */   
         default:   
            state = Token.NULL;   
      }   
   
      s = text;   
      try {   
         yyreset(zzReader);   
         yybegin(state);   
         return yylex();   
      } catch (IOException ioe) {   
         ioe.printStackTrace();   
         return new TokenImpl();   
      }   
   
   }   
   
   /**   
    * Refills the input buffer.   
    *   
    * @return      <code>true</code> if EOF was reached, otherwise   
    *              <code>false</code>.   
    */   
   private boolean zzRefill() {   
      return zzCurrentPos>=s.offset+s.count;   
   }   
   
   /**   
    * Resets the scanner to read from a new input stream.   
    * Does not close the old reader.   
    *   
    * All internal variables are reset, the old input stream    
    * <b>cannot</b> be reused (internal buffer is discarded and lost).   
    * Lexical state is set to <tt>YY_INITIAL</tt>.   
    *   
    * @param reader   the new input stream    
    */   
   public final void yyreset(Reader reader) {   
      // 's' has been updated.   
      zzBuffer = s.array;   
      /*   
       * We replaced the line below with the two below it because zzRefill   
       * no longer "refills" the buffer (since the way we do it, it's always   
       * "full" the first time through, since it points to the segment's   
       * array).  So, we assign zzEndRead here.   
       */   
      //zzStartRead = zzEndRead = s.offset;   
      zzStartRead = s.offset;   
      zzEndRead = zzStartRead + s.count - 1;   
      zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;   
      zzLexicalState = YYINITIAL;   
      zzReader = reader;   
      zzAtBOL  = true;   
      zzAtEOF  = false;   
   }   
   


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public FsSyntax(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public FsSyntax(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 186) {
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
//  private boolean zzRefill() throws java.io.IOException {
//
//    /* first: make room (if you can) */
//    if (zzStartRead > 0) {
//      System.arraycopy(zzBuffer, zzStartRead,
//                       zzBuffer, 0,
//                       zzEndRead-zzStartRead);
//
//      /* translate stored positions */
//      zzEndRead-= zzStartRead;
//      zzCurrentPos-= zzStartRead;
//      zzMarkedPos-= zzStartRead;
//      zzPushbackPos-= zzStartRead;
//      zzStartRead = 0;
//    }
//
//    /* is the buffer big enough? */
//    if (zzCurrentPos >= zzBuffer.length) {
//      /* if not: blow it up */
//      char newBuffer[] = new char[zzCurrentPos*2];
//      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
//      zzBuffer = newBuffer;
//    }
//
//    /* finally: fill the buffer with new input */
//    int numRead = zzReader.read(zzBuffer, zzEndRead,
//                                            zzBuffer.length-zzEndRead);
//
//    if (numRead < 0) {
//      return true;
//    }
//    else {
//      zzEndRead+= numRead;
//      return false;
//    }
//  }

    
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
   * @param reader   the new input stream 
   */
//  public final void yyreset(java.io.Reader reader) {
//    zzReader = reader;
//    zzAtBOL  = true;
//    zzAtEOF  = false;
//    zzEndRead = zzStartRead = 0;
//    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
//    yyline = yychar = yycolumn = 0;
//    zzLexicalState = YYINITIAL;
//  }


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
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public org.fife.ui.rsyntaxtextarea.Token yylex() throws java.io.IOException {
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

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
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
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 6: 
          { addNullToken(); return firstToken;
          }
        case 20: break;
        case 12: 
          { addToken(Token.LITERAL_CHAR);
          }
        case 21: break;
        case 15: 
          { start = zzMarkedPos-2; yybegin(MLC);
          }
        case 22: break;
        case 5: 
          { addToken(Token.WHITESPACE);
          }
        case 23: break;
        case 19: 
          { addToken(Token.ERROR_STRING_DOUBLE);
          }
        case 24: break;
        case 16: 
          { addToken(Token.RESERVED_WORD);
          }
        case 25: break;
        case 8: 
          { addToken(Token.SEPARATOR);
          }
        case 26: break;
        case 1: 
          { addToken(Token.IDENTIFIER);
          }
        case 27: break;
        case 3: 
          { addToken(Token.ERROR_CHAR); addNullToken(); return firstToken;
          }
        case 28: break;
        case 4: 
          { addToken(Token.ERROR_STRING_DOUBLE); addNullToken(); return firstToken;
          }
        case 29: break;
        case 17: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead+2-1, Token.COMMENT_MULTILINE);
          }
        case 30: break;
        case 18: 
          { addToken(Token.ERROR_CHAR);
          }
        case 31: break;
        case 13: 
          { addToken(Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 32: break;
        case 14: 
          { addToken(Token.COMMENT_EOL); addNullToken(); return firstToken;
          }
        case 33: break;
        case 11: 
          { addToken(Token.ERROR_NUMBER_FORMAT);
          }
        case 34: break;
        case 2: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 35: break;
        case 7: 
          { addToken(Token.OPERATOR);
          }
        case 36: break;
        case 9: 
          { 
          }
        case 37: break;
        case 10: 
          { addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
          }
        case 38: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 226: break;
            case MLC: {
              addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
            }
            case 227: break;
            default:
            return null;
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}