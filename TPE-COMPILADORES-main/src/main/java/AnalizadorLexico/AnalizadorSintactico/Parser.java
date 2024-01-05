//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 1 "gramatica_final.y"
package AnalizadorLexico.AnalizadorSintactico;
import java.util.Iterator;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.FileHandler;
import AnalizadorLexico.ElemFuncion;
import AnalizadorLexico.GeneradorAssembler;
import AnalizadorLexico.Token;
import AnalizadorLexico.Parametro;
import AnalizadorLexico.TablaSimbolos;
import java.util.List;
import java.util.ArrayList;
import AnalizadorLexico.ArbolSintactico.*;
import java.util.HashMap;
import java.util.Stack;

;

//#line 35 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short THEN=258;
public final static short ELSE=259;
public final static short END_IF=260;
public final static short OUT=261;
public final static short FUN=262;
public final static short RETURN=263;
public final static short BREAK=264;
public final static short WHEN=265;
public final static short DEFER=266;
public final static short UI8=267;
public final static short F64=268;
public final static short ID=269;
public final static short CONST=270;
public final static short CADENA=271;
public final static short TOF64=272;
public final static short CONTINUE=273;
public final static short FOR=274;
public final static short LLAVE_AB=275;
public final static short LLAVE_CE=276;
public final static short MENOR=277;
public final static short MAYOR=278;
public final static short IGUAL=279;
public final static short MENOR_IGUAL=280;
public final static short MAYOR_IGUAL=281;
public final static short MENOS=282;
public final static short SUM=283;
public final static short MULT=284;
public final static short DIV=285;
public final static short PARE_AB=286;
public final static short PARE_CIE=287;
public final static short PUNTO_COMA=288;
public final static short DOS_PUNTOS=289;
public final static short COMA=290;
public final static short ASIGNACION=291;
public final static short DISTINTO=292;
public final static short DOBLE=293;
public final static short ENTERO=294;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    2,    0,    3,    3,    3,    4,    4,    4,   10,   10,
   10,   10,   10,   10,    5,    5,    5,    5,    5,    5,
   17,   17,   17,   17,   17,   17,   17,   19,   19,   19,
   20,   20,   20,   20,   20,   20,   24,   24,   24,   24,
   24,   24,   26,   26,   26,   26,   27,   27,   27,   27,
   27,   28,   28,   28,   28,   28,   29,   29,   29,   29,
   29,   30,   30,   30,   30,   30,   31,   31,   31,   31,
   31,    1,    1,    1,    1,    1,   32,   32,   32,   32,
   33,   33,   33,   33,   33,   34,   34,   34,   34,   34,
   35,   35,   35,   35,   35,   36,   36,   36,   36,   36,
   37,   37,   37,    7,    7,   39,   40,   40,   41,   41,
   41,   42,   43,   43,   43,   44,    8,   45,   45,   45,
   47,   47,    9,   46,   46,   48,   48,   49,   49,   49,
   49,   13,   11,   11,   11,   16,   16,   51,   51,   51,
   51,   53,   53,   53,   53,   54,   54,   55,   55,   25,
   25,   25,   25,   25,   56,   56,   57,   57,   18,   18,
   18,   18,   18,   58,   58,   59,   59,   22,   22,   22,
   22,   22,   60,   60,   61,   61,   14,   14,   14,   14,
   14,   14,   62,   62,   12,   12,   63,   63,   63,   64,
   64,   64,   65,   65,   65,   65,   50,   50,   50,   50,
   38,   38,   52,   52,   52,   52,   52,   52,   66,   66,
   66,   66,   67,   67,   68,   68,   68,   68,   69,   69,
   69,   70,   70,   70,   71,   71,   72,   15,   73,   15,
   74,   74,   23,   75,   23,   76,   76,   76,   77,   77,
   77,   77,   77,    6,    6,   21,   21,
};
final static short yylen[] = {                            2,
    0,    3,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    2,    2,    2,    1,    1,    1,    2,    2,    1,
    1,    1,    1,    3,    2,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    2,    2,    1,    1,    1,    3,
    1,    1,    2,    2,    3,    3,    2,    2,    3,    3,
    1,    2,    2,    3,    3,    1,    2,    2,    3,    3,
    1,    2,    2,    3,    2,    1,    2,    2,    3,    3,
    2,    3,    3,    3,    1,    2,    3,    3,    3,    2,
    3,    3,    3,    3,    2,    3,    3,    3,    3,    2,
    3,    3,    3,    3,    2,    3,    3,    3,    3,    2,
    1,    1,    3,    2,    2,    2,    1,    3,    3,    2,
    3,    2,    2,    2,    1,    0,    5,    3,    3,    3,
    1,    3,    2,    1,    1,    1,    1,    5,    3,    2,
    3,    2,    3,    3,    3,    2,    2,    3,    3,    3,
    3,    3,    3,    3,    3,    1,    2,    1,    2,    7,
    5,    7,    7,    7,    1,    2,    1,    2,    7,    5,
    7,    7,    7,    1,    2,    1,    2,    7,    5,    7,
    7,    7,    1,    2,    1,    2,    7,    5,    7,    7,
    7,    5,    1,    1,    4,    3,    1,    1,    1,    1,
    3,    3,    1,    3,    3,    2,    4,    1,    4,    4,
    1,    1,    1,    1,    1,    1,    1,    1,    3,    3,
    3,    3,    1,    1,    2,    1,    2,    2,    3,    3,
    3,    7,    7,    7,    1,    1,    2,    3,    0,    5,
    1,    1,    3,    0,    5,    1,    1,    1,    5,    5,
    5,    5,    3,    4,    4,    4,    4,
};
final static short yydefred[] = {                         0,
    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  201,  202,    0,    0,    0,    0,    2,    0,    3,    4,
    5,    6,    7,    8,   15,    9,   10,   11,   16,   17,
   20,    0,    0,    0,  229,    0,    0,    0,    0,    0,
  112,  137,    0,    0,    0,  124,  125,  188,  189,  136,
  190,    0,    0,    0,    0,    0,    0,   12,   13,   14,
   18,   19,  102,  101,    0,    0,  227,    0,    0,  132,
    0,  121,    0,    0,    0,   76,    0,   44,   43,    0,
    0,    0,    0,    0,   73,    0,    0,    0,    0,    0,
    0,    0,  186,  184,  183,    0,    0,    0,    0,    0,
    0,    0,    0,  238,  236,  237,    0,    0,    0,    0,
    0,  134,    0,  126,  130,  127,    0,  135,  133,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   27,
    0,   21,   23,  226,   22,  225,  228,   74,   72,   46,
   45,    0,  110,    0,    0,    0,  115,    0,  116,    0,
  204,  203,  205,  207,  206,  208,    0,    0,    0,  143,
  145,  144,  142,    0,    0,    0,  173,    0,    0,  185,
    0,    0,  191,  192,    0,    0,    0,  243,    0,    0,
  245,  244,  131,  129,    0,  103,  119,  120,  118,  122,
    0,    0,    0,    0,   56,    0,    0,    0,   25,    0,
    0,   85,    0,  111,  106,    0,  109,  114,  113,    0,
    0,  139,  140,  141,  138,   51,    0,    0,   80,    0,
  174,    0,  182,    0,    0,  178,  199,  200,  197,    0,
    0,    0,    0,  220,  221,  219,    0,    0,    0,    0,
   53,   52,   84,   82,    0,    0,    0,   24,   83,   81,
  108,    0,    0,  117,  230,   48,   47,   78,    0,   79,
   77,    0,  175,    0,    0,    0,  240,  241,  242,  239,
  128,    0,    0,    0,    0,    0,    0,   55,   54,    0,
  155,    0,    0,   61,    0,    0,    0,    0,   28,   31,
   34,    0,   29,   30,   32,   33,    0,  234,   90,    0,
   50,   49,  176,  180,  179,  181,  177,  210,  211,  212,
  209,    0,  214,  213,  216,    0,    0,    0,    0,  156,
    0,    0,    0,  160,    0,    0,   35,   36,    0,   58,
   57,   89,   87,    0,    0,   88,   86,  217,  218,  215,
  223,  224,  222,    0,  157,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   42,    0,   39,   37,
  232,   38,  231,  233,   60,   59,    0,  158,  162,  161,
  163,  159,    0,    0,    0,  164,    0,    0,  247,  246,
    0,    0,    0,    0,    0,  100,    0,    0,   66,    0,
    0,   95,    0,  165,    0,    0,    0,  169,   71,   68,
   67,   99,   97,    0,    0,    0,   40,   98,   96,  235,
   63,   62,   65,   94,   92,    0,   93,   91,    0,  166,
    0,    0,    0,   70,   69,    0,  146,    0,    0,   64,
  167,  171,  170,  172,  168,  147,    0,    0,    0,  151,
    0,  148,    0,    0,    0,  149,  153,  152,  154,  150,
};
final static short yydgoto[] = {                          2,
   17,    3,   18,   19,   20,   21,   22,   23,   24,   25,
   26,   27,   28,   29,   30,   31,  344,  135,  292,  293,
  294,  295,  296,  441,  362,   32,  218,  197,  297,  391,
  383,  263,  345,  254,  420,  442,   69,   33,  145,  146,
   83,   34,  149,  210,   72,   49,   73,  117,   70,   87,
   88,  157,   39,  428,  443,  282,  346,  377,  421,  168,
  264,   96,   51,   52,   53,  239,  316,  317,  125,   75,
  137,   35,   84,  364,  335,  107,   56,
};
final static short yysindex[] = {                      -246,
    0,    0,  629,  798, -223, -258, -150, -179, -206,  200,
    0,    0,   21, -101,  -48,  644,    0, -231,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  798, -148,  -25,    0,  660,   75,   84,  184,  163,
    0,    0,  -21, -149,  -76,    0,    0,    0,    0,    0,
    0, -175, -123, -167, -114,  208,  239,    0,    0,    0,
    0,    0,    0,    0,  385, -245,    0,  102, -145,    0,
  -28,    0,  -12,   19,  676,    0,  427,    0,    0, -212,
 -145,  146, -229,   49,    0,  801,   14,   50,  796, -213,
  691,  691,    0,    0,    0,   62,  333,  333, -175,  -76,
  -76,  -76,  -76,    0,    0,    0,  801,   67,  166,  629,
  629,    0,   72,    0,    0,    0,   27,    0,    0,  106,
 -118, -239, -101,   48, -209,  752, -223,  -80, -107,    0,
  491,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   94,    0,  118,  100,  110,    0,  -57,    0,  -48,
    0,    0,    0,    0,    0,    0,  385,  385,  107,    0,
    0,    0,    0,  766,  532,  115,    0,  -55,  -58,    0,
  217,  -59,    0,    0, -175, -175, -167,    0, -167, -167,
    0,    0,    0,    0,  111,    0,    0,    0,    0,    0,
  385,  116, -136, -136,    0, -204,  497,  247,    0,  139,
  206,    0,  518,    0,    0,  -52,    0,    0,    0,  -13,
  676,    0,    0,    0,    0,    0, -158,  721,    0,  546,
    0,  691,    0,  691,  691,    0,    0,    0,    0,  124,
  154, -183,  172,    0,    0,    0,  801,  171,  179, -138,
    0,    0,    0,    0, -125,  676,  676,    0,    0,    0,
    0,  737,  443,    0,    0,    0,    0,    0,  -92,    0,
    0,  234,    0,  236,  272, -234,    0,    0,    0,    0,
    0,  385,  385,  143, -248, -248, -248,    0,    0,  258,
    0,  291,  299,    0, -223, -206,  233,  -48,    0,    0,
    0,  -53,    0,    0,    0,    0,  459,    0,    0,  475,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -118,    0,    0,    0, -181,  269,  273,  287,    0,
  676,  676,  676,    0,  277,  305,    0,    0,  699,    0,
    0,    0,    0,  -24,  279,    0,    0,    0,    0,    0,
    0,    0,    0,  290,    0,  321,  324,   76,  714,  714,
  -13,  -13,  774, -223,  302,  317,    0,  554,    0,    0,
    0,    0,    0,    0,    0,    0,  -48,    0,    0,    0,
    0,    0,  788,  568,  326,    0,  358,  327,    0,    0,
  356,   13,  576,  353,  350,    0,  590,  699,    0,  403,
  599,    0,  613,    0,  714,  714,  714,    0,    0,    0,
    0,    0,    0,   23,  699,  699,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  347,    0,    0,  354,    0,
  389,  391,  175,    0,    0,  359,    0,  396,  374,    0,
    0,    0,    0,    0,    0,    0,  699,  699,  699,    0,
  370,    0,  402,  405,  271,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  199,    0,    0,    0,    0,    0,    0,    0,
    0,  232,  344,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   31,    0,
    0,    0,   46,    0,    0,    0,    0,    0,    0,    0,
   47,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  260,    0,
    0,    0,    0,    0,    0,    0,    0,  360,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  381,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  288,  316,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  387,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  134,    0,    5, -232,    9,    0,    0,    0,    0,  -75,
   -5,   -4,   -6,  666,    3,   33,  -65,    0,   73, -137,
    0,  395, -218, -300,    0,  351,  520,  555,  434,  328,
  340,  205,  -63,   66,  104, -289,  674,  -67,  508,    0,
    0,    0,    0,    0,  595,  -36,    0,  538,    0,   -1,
  692,  -71, -124,  362,  -74,  487,  266,  400,  249,  647,
  404,    0,  570,  -31,  583,  552,    0,  397,    0, -141,
  545,  -45,    0,  371,    0,   15,  476,
};
final static int YYTABLESIZE=1093;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                        132,
   75,   48,  198,   60,   58,   59,   50,  312,  211,  134,
  113,  136,   62,   99,  144,  159,  188,  106,  106,  289,
  289,  306,    1,  114,   78,  307,  147,   40,  361,  116,
   48,   48,   37,  313,  314,  177,   80,  180,   48,  363,
   80,  115,  162,  140,   46,   47,  193,   46,   47,   54,
  132,  241,  382,   46,   47,  132,   79,  382,   48,  148,
  196,   48,   38,  112,  289,  196,  119,  289,  328,  109,
  175,  176,  269,  163,  339,  141,   42,  133,  194,   55,
  209,   80,  404,  242,  187,  189,  404,  361,  104,   43,
   48,   48,   44,   48,   48,   48,   48,  256,  363,  166,
  166,  105,   45,  270,  426,  426,   97,   63,  100,  101,
  360,   46,   47,   46,   47,  427,  427,  276,   41,  237,
   64,  132,   60,   58,   59,   46,   47,  132,  133,  257,
  278,  245,  238,  133,  360,  132,   98,  245,  144,  360,
  106,  108,  106,  106,  120,  134,  329,  136,  116,  277,
   48,   48,   48,    6,  105,  212,  213,  215,  102,  103,
  325,  201,  279,  301,  360,  272,  274,   71,  360,  360,
  132,  132,  217,  217,   46,   47,  290,  290,   46,   47,
  280,  280,  281,  281,   48,   48,  360,  360,  199,  234,
  236,  230,   43,  231,  232,  302,  228,  224,  208,  133,
  225,  226,  330,  222,  223,  133,  298,  298,  200,   11,
   12,  375,  375,  133,   11,   12,   46,   47,  360,  360,
  360,  290,  102,  103,  290,  388,  259,  229,  259,  384,
  262,  365,  262,  262,  331,  390,  390,   74,  315,  315,
  315,  298,  252,  181,  182,  132,  132,  132,  133,  133,
  121,  298,  413,  416,  298,  416,   75,  419,  419,  419,
   82,  253,  122,  366,   66,   48,   48,   48,  400,  158,
  308,  309,  311,  290,  290,  338,   63,  123,  424,  340,
   60,   58,   59,  298,  291,  291,  105,  124,   75,   64,
  151,  152,  153,  154,  155,  167,  167,  290,  290,   65,
  401,  123,  104,  298,  298,  156,   66,  298,   26,   67,
  425,   68,  298,  184,  290,  290,  185,  290,  105,  290,
  290,  290,  150,  133,  133,  133,  191,  298,  298,  291,
   86,  371,  291,  123,  104,  372,  160,  298,  192,   89,
   26,  298,  298,   43,  298,  298,   44,  298,  170,  298,
  298,  298,   43,  178,   36,   44,   45,  118,  183,  298,
  298,  359,  214,  444,  445,   45,   77,   46,   47,  334,
   43,  235,  334,   44,  186,   43,   46,   47,   44,  114,
  204,  291,  291,   45,   43,  359,  205,   44,   45,  206,
  359,  298,  298,  298,   46,   47,  207,   45,  310,   46,
   47,  142,  221,   46,   47,  291,  291,  248,   46,   47,
  267,   43,   11,   12,   44,  359,  379,  380,   93,  359,
  359,  179,  291,  291,   45,  291,  273,  291,  291,  291,
  434,   94,  143,   95,  435,   46,   47,  359,  359,   91,
  268,   92,  151,  152,  153,  154,  155,  151,  152,  153,
  154,  155,  376,  376,  187,  187,    5,  156,  271,  187,
    6,  187,  156,  110,  187,  111,  275,  187,   57,  359,
  359,  359,  187,   15,  187,  187,  187,  187,  187,  187,
  187,  187,  187,  187,   65,  187,  187,  193,  193,  285,
  187,   66,  193,    6,  193,  304,   68,  193,  102,  103,
  193,   57,  246,  227,  247,  193,  288,  193,  193,  193,
  193,  193,  193,  193,  193,  196,  196,   65,  193,  193,
  196,  303,  196,  193,   66,  196,  449,   67,  196,   68,
  450,  305,  349,  196,  350,  196,  196,  196,  196,  196,
  196,  196,  196,  195,  195,  320,  196,  196,  195,  321,
  195,  196,  367,  195,  322,  341,  195,  323,  324,  342,
  351,  195,  352,  195,  195,  195,  195,  195,  195,  195,
  195,  194,  194,  343,  195,  195,  194,  368,  194,  195,
  369,  194,  396,  370,  194,  397,  398,  347,  348,  194,
  385,  194,  194,  194,  194,  194,  194,  194,  194,  198,
  198,   43,  194,  194,  198,   67,  198,  194,  405,  198,
  406,  399,  198,  394,   45,  238,  395,  198,  407,  198,
  198,  198,  198,  198,  198,   46,   47,  265,  266,  438,
  198,  198,  439,  440,  430,  198,  238,  238,  238,  238,
  238,  431,   41,   41,  422,  423,  436,   41,  432,   41,
  433,  238,   41,   43,  437,   41,   44,  446,  411,  285,
   41,  447,   41,    6,  448,    8,   45,  107,  287,  173,
  174,   57,  318,  319,   41,   61,  288,   46,   47,  171,
  172,  327,  138,    5,  220,  203,  300,    6,    7,    8,
  412,    9,   10,   11,   12,   13,   14,  387,  284,  285,
   15,  393,  139,    6,    7,    8,   81,  286,  287,   11,
   12,   13,   14,  251,  332,  285,  288,  190,  299,    6,
    7,    8,  233,  286,  287,   11,   12,   13,   14,   90,
  336,  285,  288,  283,  333,    6,    7,    8,  169,  286,
  287,   11,   12,   13,   14,  240,  195,  127,  288,  378,
  337,    6,  243,  127,  128,  255,  129,    6,  410,   57,
  128,  326,  129,  130,   15,   57,  202,  429,    0,  130,
   15,    0,  244,  249,  127,    0,    0,    0,    6,    0,
    0,  128,    0,  129,    0,    0,   57,  216,    5,    0,
  130,   15,    6,  250,    8,    0,    0,   10,    0,    0,
   57,  260,    5,    0,    0,   15,    6,  219,    8,  381,
  354,   10,    0,    0,   57,    0,    8,  355,    0,   15,
    0,  261,  356,  389,  285,    0,  357,  288,    6,  386,
    8,  402,  354,  287,    0,    0,   57,    0,    8,  355,
    0,  288,    0,  392,  356,  408,  354,    0,  357,  288,
    0,  403,    8,  355,  414,  285,    0,    0,  356,    6,
    0,    8,  357,  288,  287,  409,    0,   57,  417,  285,
    0,    0,  288,    6,  415,    8,    0,    0,  287,    0,
    0,   57,    0,    0,    4,    5,  288,    0,  418,    6,
    7,    8,    0,    9,   10,   11,   12,   13,   14,    0,
    5,    0,   15,   16,    6,    7,    8,    0,    9,   10,
   11,   12,   13,   14,    0,    0,    5,   15,    0,   76,
    6,    7,    8,    0,    9,   10,   11,   12,   13,   14,
    0,  126,  127,   15,    0,   85,    6,    0,    0,  128,
    0,  129,    0,    0,   57,    0,  164,    5,  130,   15,
  131,    6,    0,    8,  353,  354,   10,    0,    0,   57,
    0,    8,  355,    0,   15,  165,    0,  356,    0,  373,
  285,  357,  288,  358,    6,    0,    8,    5,    0,  287,
    0,    6,   57,    8,    0,    0,   10,  288,  374,   57,
    0,    0,  284,  285,   15,    0,  258,    6,    7,    8,
    0,  286,  287,   11,   12,   13,   14,  195,  127,    0,
  288,    0,    6,    0,    0,  128,    0,  129,    0,    0,
   57,  216,    5,    0,  130,   15,    6,    0,    8,  381,
  354,   10,    0,    0,   57,    0,    8,  355,    0,   15,
    0,    0,  356,  389,  285,    0,  357,  288,    6,    0,
    8,    0,    0,  287,    5,    0,   57,    0,    6,    7,
    8,  288,    9,   10,   11,   12,   13,   14,    0,    0,
    0,   15,  151,  152,  153,  154,  155,  151,  152,  153,
  154,  155,  161,    0,    0,    0,    0,  156,    0,    0,
    0,    0,  156,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         75,
    0,    8,  127,   10,   10,   10,    8,  256,  150,   75,
  256,   75,   10,   45,   82,   87,  256,   54,   55,  252,
  253,  256,  269,  269,  256,  260,  256,  286,  329,   66,
   37,   38,  256,  282,  283,  107,   32,  109,   45,  329,
   36,  287,  256,  256,  293,  294,  256,  293,  294,  256,
  126,  256,  353,  293,  294,  131,  288,  358,   65,  289,
  126,   68,  286,   65,  297,  131,   68,  300,  287,   55,
  102,  103,  256,  287,  256,  288,  256,   75,  288,  286,
  148,   77,  383,  288,  121,  122,  387,  388,  256,  269,
   97,   98,  272,  100,  101,  102,  103,  256,  388,   91,
   92,  269,  282,  287,  405,  406,  256,  256,  284,  285,
  329,  293,  294,  293,  294,  405,  406,  256,  269,  256,
  269,  197,  129,  129,  129,  293,  294,  203,  126,  288,
  256,  197,  269,  131,  353,  211,  286,  203,  206,  358,
  177,  256,  179,  180,  290,  211,  288,  211,  185,  288,
  157,  158,  159,  261,  269,  157,  158,  159,  282,  283,
  285,  269,  288,  256,  383,  237,  238,  269,  387,  388,
  246,  247,  164,  165,  293,  294,  252,  253,  293,  294,
  246,  247,  246,  247,  191,  192,  405,  406,  269,  191,
  192,  177,  269,  179,  180,  288,  256,  256,  256,  197,
  259,  260,  256,  259,  260,  203,  252,  253,  289,  267,
  268,  349,  350,  211,  267,  268,  293,  294,  437,  438,
  439,  297,  282,  283,  300,  367,  218,  287,  220,  354,
  222,  256,  224,  225,  288,  373,  374,  286,  275,  276,
  277,  287,  256,  110,  111,  321,  322,  323,  246,  247,
  279,  297,  390,  391,  300,  393,  256,  395,  396,  397,
  286,  275,  291,  288,  286,  272,  273,  274,  256,  256,
  272,  273,  274,  349,  350,  312,  256,  290,  256,  316,
  287,  287,  287,  329,  252,  253,  256,  269,  288,  269,
  277,  278,  279,  280,  281,   91,   92,  373,  374,  279,
  288,  256,  256,  349,  350,  292,  286,  353,  256,  289,
  288,  291,  358,  287,  390,  391,  290,  393,  288,  395,
  396,  397,  274,  321,  322,  323,  279,  373,  374,  297,
  256,  256,  300,  288,  288,  260,  287,  383,  291,  256,
  288,  387,  388,  269,  390,  391,  272,  393,  287,  395,
  396,  397,  269,  287,    4,  272,  282,  256,  287,  405,
  406,  329,  256,  438,  439,  282,   16,  293,  294,  297,
  269,  256,  300,  272,  269,  269,  293,  294,  272,  269,
  287,  349,  350,  282,  269,  353,  269,  272,  282,  290,
  358,  437,  438,  439,  293,  294,  287,  282,  256,  293,
  294,  256,  288,  293,  294,  373,  374,  269,  293,  294,
  287,  269,  267,  268,  272,  383,  351,  352,  256,  387,
  388,  256,  390,  391,  282,  393,  256,  395,  396,  397,
  256,  269,  287,  271,  260,  293,  294,  405,  406,  256,
  287,  258,  277,  278,  279,  280,  281,  277,  278,  279,
  280,  281,  349,  350,  256,  257,  257,  292,  287,  261,
  261,  263,  292,  256,  266,  258,  288,  269,  269,  437,
  438,  439,  274,  274,  276,  277,  278,  279,  280,  281,
  282,  283,  284,  285,  279,  287,  288,  256,  257,  257,
  292,  286,  261,  261,  263,  260,  291,  266,  282,  283,
  269,  269,  256,  287,  258,  274,  274,  276,  277,  278,
  279,  280,  281,  282,  283,  256,  257,  279,  287,  288,
  261,  288,  263,  292,  286,  266,  256,  289,  269,  291,
  260,  260,  256,  274,  258,  276,  277,  278,  279,  280,
  281,  282,  283,  256,  257,  288,  287,  288,  261,  259,
  263,  292,  274,  266,  256,  287,  269,  259,  260,  287,
  256,  274,  258,  276,  277,  278,  279,  280,  281,  282,
  283,  256,  257,  287,  287,  288,  261,  288,  263,  292,
  260,  266,  256,  260,  269,  259,  260,  322,  323,  274,
  289,  276,  277,  278,  279,  280,  281,  282,  283,  256,
  257,  269,  287,  288,  261,  289,  263,  292,  256,  266,
  258,  256,  269,  288,  282,  256,  259,  274,  269,  276,
  277,  278,  279,  280,  281,  293,  294,  224,  225,  256,
  287,  288,  259,  260,  288,  292,  277,  278,  279,  280,
  281,  288,  256,  257,  396,  397,  288,  261,  260,  263,
  260,  292,  266,  269,  259,  269,  272,  288,  256,  257,
  274,  260,  276,  261,  260,  263,  282,  287,  266,  100,
  101,  269,  276,  277,  288,   10,  274,  293,  294,   97,
   98,  287,  256,  257,  165,  131,  253,  261,  262,  263,
  288,  265,  266,  267,  268,  269,  270,  358,  256,  257,
  274,  374,  276,  261,  262,  263,   33,  265,  266,  267,
  268,  269,  270,  206,  256,  257,  274,  123,  276,  261,
  262,  263,  185,  265,  266,  267,  268,  269,  270,   38,
  256,  257,  274,  247,  276,  261,  262,  263,   92,  265,
  266,  267,  268,  269,  270,  194,  256,  257,  274,  350,
  276,  261,  256,  257,  264,  211,  266,  261,  388,  269,
  264,  286,  266,  273,  274,  269,  276,  406,   -1,  273,
  274,   -1,  276,  256,  257,   -1,   -1,   -1,  261,   -1,
   -1,  264,   -1,  266,   -1,   -1,  269,  256,  257,   -1,
  273,  274,  261,  276,  263,   -1,   -1,  266,   -1,   -1,
  269,  256,  257,   -1,   -1,  274,  261,  276,  263,  256,
  257,  266,   -1,   -1,  269,   -1,  263,  264,   -1,  274,
   -1,  276,  269,  256,  257,   -1,  273,  274,  261,  276,
  263,  256,  257,  266,   -1,   -1,  269,   -1,  263,  264,
   -1,  274,   -1,  276,  269,  256,  257,   -1,  273,  274,
   -1,  276,  263,  264,  256,  257,   -1,   -1,  269,  261,
   -1,  263,  273,  274,  266,  276,   -1,  269,  256,  257,
   -1,   -1,  274,  261,  276,  263,   -1,   -1,  266,   -1,
   -1,  269,   -1,   -1,  256,  257,  274,   -1,  276,  261,
  262,  263,   -1,  265,  266,  267,  268,  269,  270,   -1,
  257,   -1,  274,  275,  261,  262,  263,   -1,  265,  266,
  267,  268,  269,  270,   -1,   -1,  257,  274,   -1,  276,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,  270,
   -1,  256,  257,  274,   -1,  276,  261,   -1,   -1,  264,
   -1,  266,   -1,   -1,  269,   -1,  256,  257,  273,  274,
  275,  261,   -1,  263,  256,  257,  266,   -1,   -1,  269,
   -1,  263,  264,   -1,  274,  275,   -1,  269,   -1,  256,
  257,  273,  274,  275,  261,   -1,  263,  257,   -1,  266,
   -1,  261,  269,  263,   -1,   -1,  266,  274,  275,  269,
   -1,   -1,  256,  257,  274,   -1,  276,  261,  262,  263,
   -1,  265,  266,  267,  268,  269,  270,  256,  257,   -1,
  274,   -1,  261,   -1,   -1,  264,   -1,  266,   -1,   -1,
  269,  256,  257,   -1,  273,  274,  261,   -1,  263,  256,
  257,  266,   -1,   -1,  269,   -1,  263,  264,   -1,  274,
   -1,   -1,  269,  256,  257,   -1,  273,  274,  261,   -1,
  263,   -1,   -1,  266,  257,   -1,  269,   -1,  261,  262,
  263,  274,  265,  266,  267,  268,  269,  270,   -1,   -1,
   -1,  274,  277,  278,  279,  280,  281,  277,  278,  279,
  280,  281,  287,   -1,   -1,   -1,   -1,  292,   -1,   -1,
   -1,   -1,  292,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=294;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"IF","THEN","ELSE","END_IF","OUT","FUN","RETURN","BREAK","WHEN",
"DEFER","UI8","F64","ID","CONST","CADENA","TOF64","CONTINUE","FOR","LLAVE_AB",
"LLAVE_CE","MENOR","MAYOR","IGUAL","MENOR_IGUAL","MAYOR_IGUAL","MENOS","SUM",
"MULT","DIV","PARE_AB","PARE_CIE","PUNTO_COMA","DOS_PUNTOS","COMA","ASIGNACION",
"DISTINTO","DOBLE","ENTERO",
};
final static String yyrule[] = {
"$accept : program",
"$$1 :",
"program : ID $$1 bloq_general",
"sentencia : sentencia_d",
"sentencia : sentencia_e_gen",
"sentencia : when",
"sentencia_d : d_var",
"sentencia_d : d_fun",
"sentencia_d : const",
"sentencia_e : asignacion",
"sentencia_e : out",
"sentencia_e : invocacion_fun",
"sentencia_e : DEFER asignacion",
"sentencia_e : DEFER out",
"sentencia_e : DEFER invocacion_fun",
"sentencia_e_gen : sentencia_e",
"sentencia_e_gen : if",
"sentencia_e_gen : for",
"sentencia_e_gen : DEFER if",
"sentencia_e_gen : DEFER for",
"sentencia_e_gen : return",
"sentencia_e_for : sentencia_e",
"sentencia_e_for : if_for",
"sentencia_e_for : for",
"sentencia_e_for : BREAK DOS_PUNTOS ID",
"sentencia_e_for : BREAK ID",
"sentencia_e_for : BREAK",
"sentencia_e_for : CONTINUE",
"sentencia_fun : sentencia_d",
"sentencia_fun : sentencia_e_fun",
"sentencia_fun : when_fun",
"sentencia_e_fun : sentencia_e",
"sentencia_e_fun : if_fun",
"sentencia_e_fun : for_fun",
"sentencia_e_fun : return",
"sentencia_e_fun : DEFER if_fun",
"sentencia_e_fun : DEFER for_fun",
"sentencia_e_for_fun : for_fun",
"sentencia_e_for_fun : if_for_fun",
"sentencia_e_for_fun : return",
"sentencia_e_for_fun : BREAK DOS_PUNTOS ID",
"sentencia_e_for_fun : BREAK",
"sentencia_e_for_fun : CONTINUE",
"bloq_sentencias : sentencia PUNTO_COMA",
"bloq_sentencias : sentencia error",
"bloq_sentencias : bloq_sentencias sentencia PUNTO_COMA",
"bloq_sentencias : bloq_sentencias sentencia error",
"bloq_sentencias_e : sentencia_e_gen PUNTO_COMA",
"bloq_sentencias_e : sentencia_e_gen error",
"bloq_sentencias_e : bloq_sentencias_e sentencia_e_gen PUNTO_COMA",
"bloq_sentencias_e : bloq_sentencias_e sentencia_e_gen error",
"bloq_sentencias_e : error",
"bloq_sentencias_e_for : sentencia_e_for PUNTO_COMA",
"bloq_sentencias_e_for : sentencia_e_for error",
"bloq_sentencias_e_for : bloq_sentencias_e_for sentencia_e_for PUNTO_COMA",
"bloq_sentencias_e_for : bloq_sentencias_e_for sentencia_e_for error",
"bloq_sentencias_e_for : error",
"bloq_sentencias_fun : sentencia_fun PUNTO_COMA",
"bloq_sentencias_fun : sentencia_fun error",
"bloq_sentencias_fun : bloq_sentencias_fun sentencia_fun PUNTO_COMA",
"bloq_sentencias_fun : bloq_sentencias_fun sentencia_fun error",
"bloq_sentencias_fun : error",
"bloq_sentencias_e_fun : sentencia_e_fun PUNTO_COMA",
"bloq_sentencias_e_fun : sentencia_e_fun error",
"bloq_sentencias_e_fun : bloq_sentencias_e_fun sentencia_e_fun PUNTO_COMA",
"bloq_sentencias_e_fun : sentencia_e_fun sentencia_e_fun",
"bloq_sentencias_e_fun : error",
"bloq_sentencias_e_for_fun : sentencia_e_for_fun PUNTO_COMA",
"bloq_sentencias_e_for_fun : sentencia_e_for_fun error",
"bloq_sentencias_e_for_fun : bloq_sentencias_e_for_fun sentencia_e_for_fun PUNTO_COMA",
"bloq_sentencias_e_for_fun : bloq_sentencias_e_for_fun sentencia_e_for_fun error",
"bloq_sentencias_e_for_fun : error error",
"bloq_general : LLAVE_AB bloq_sentencias LLAVE_CE",
"bloq_general : error bloq_sentencias LLAVE_CE",
"bloq_general : LLAVE_AB bloq_sentencias error",
"bloq_general : bloq_sentencias",
"bloq_general : LLAVE_AB LLAVE_CE",
"bloq_ejecutable : LLAVE_AB bloq_sentencias_e LLAVE_CE",
"bloq_ejecutable : error bloq_sentencias_e LLAVE_CE",
"bloq_ejecutable : LLAVE_AB bloq_sentencias_e error",
"bloq_ejecutable : LLAVE_AB LLAVE_CE",
"bloq_ejecutable_for : LLAVE_AB bloq_sentencias_e_for LLAVE_CE",
"bloq_ejecutable_for : error bloq_sentencias_e_for LLAVE_CE",
"bloq_ejecutable_for : LLAVE_AB bloq_sentencias_e_for error",
"bloq_ejecutable_for : error bloq_sentencias_e_for error",
"bloq_ejecutable_for : LLAVE_AB LLAVE_CE",
"bloq_general_fun : LLAVE_AB bloq_sentencias_fun LLAVE_CE",
"bloq_general_fun : error bloq_sentencias_fun LLAVE_CE",
"bloq_general_fun : LLAVE_AB bloq_sentencias_fun error",
"bloq_general_fun : error bloq_sentencias_fun error",
"bloq_general_fun : LLAVE_AB LLAVE_CE",
"bloq_ejecutable_fun : LLAVE_AB bloq_sentencias_e_fun LLAVE_CE",
"bloq_ejecutable_fun : error bloq_sentencias_e_fun LLAVE_CE",
"bloq_ejecutable_fun : LLAVE_AB bloq_sentencias_e_fun error",
"bloq_ejecutable_fun : error bloq_sentencias_e_fun error",
"bloq_ejecutable_fun : LLAVE_AB LLAVE_CE",
"bloq_ejecutable_for_fun : LLAVE_AB bloq_sentencias_e_for_fun LLAVE_CE",
"bloq_ejecutable_for_fun : error bloq_sentencias_e_for_fun LLAVE_CE",
"bloq_ejecutable_for_fun : LLAVE_AB bloq_sentencias_e_for_fun error",
"bloq_ejecutable_for_fun : error bloq_sentencias_e_for_fun error",
"bloq_ejecutable_for_fun : LLAVE_AB LLAVE_CE",
"l_variables : ID",
"l_variables : error",
"l_variables : l_variables COMA ID",
"d_var : tipo l_variables",
"d_var : ID l_variables",
"parametro_form : tipo ID",
"l_parametros_form : parametro_form",
"l_parametros_form : parametro_form COMA parametro_form",
"parametros_fun : PARE_AB l_parametros_form PARE_CIE",
"parametros_fun : PARE_AB PARE_CIE",
"parametros_fun : PARE_AB error PARE_CIE",
"cabeceraFun : FUN ID",
"retornoFun : DOS_PUNTOS tipo",
"retornoFun : DOS_PUNTOS error",
"retornoFun : error",
"$$2 :",
"d_fun : cabeceraFun parametros_fun retornoFun $$2 bloq_general_fun",
"asignacion_const : ID ASIGNACION constante_num",
"asignacion_const : ID IGUAL constante_num",
"asignacion_const : ID ASIGNACION error",
"l_const : asignacion_const",
"l_const : l_const COMA asignacion_const",
"const : CONST l_const",
"constante_num : DOBLE",
"constante_num : ENTERO",
"parametro_real : ID",
"parametro_real : constante_num",
"l_parametros_reales : PARE_AB parametro_real COMA parametro_real PARE_CIE",
"l_parametros_reales : PARE_AB parametro_real PARE_CIE",
"l_parametros_reales : PARE_AB PARE_CIE",
"l_parametros_reales : PARE_AB error PARE_CIE",
"invocacion_fun : ID l_parametros_reales",
"asignacion : ID ASIGNACION op_aritemtica_cast",
"asignacion : ID IGUAL op_aritemtica_cast",
"asignacion : ID ASIGNACION error",
"return : RETURN op_aritemtica_cast",
"return : RETURN error",
"cond_if : op_aritemtica_cast comp op_aritemtica_cast",
"cond_if : error comp op_aritemtica_cast",
"cond_if : op_aritemtica_cast error op_aritemtica_cast",
"cond_if : op_aritemtica_cast comp error",
"cond : PARE_AB cond_if PARE_CIE",
"cond : error cond_if PARE_CIE",
"cond : PARE_AB cond_if error",
"cond : PARE_AB error PARE_CIE",
"bloque_then_for_fun : bloq_ejecutable_for_fun",
"bloque_then_for_fun : sentencia_e_for_fun PUNTO_COMA",
"bloque_else_for_fun : bloq_ejecutable_for_fun",
"bloque_else_for_fun : sentencia_e_for_fun PUNTO_COMA",
"if_for_fun : IF cond THEN bloque_then_for_fun ELSE bloque_else_for_fun END_IF",
"if_for_fun : IF cond THEN bloque_then_for_fun END_IF",
"if_for_fun : IF cond THEN bloque_then_for_fun error bloque_else_for_fun END_IF",
"if_for_fun : IF cond error bloque_then_for_fun ELSE bloque_else_for_fun END_IF",
"if_for_fun : IF cond THEN bloque_then_for_fun ELSE bloque_else_for_fun error",
"bloque_then_for : bloq_ejecutable_for",
"bloque_then_for : sentencia_e_for PUNTO_COMA",
"bloque_else_for : bloq_ejecutable_for",
"bloque_else_for : sentencia_e_for PUNTO_COMA",
"if_for : IF cond THEN bloque_then_for ELSE bloque_else_for END_IF",
"if_for : IF cond THEN bloque_then_for END_IF",
"if_for : IF cond THEN bloque_then_for error bloque_else_for END_IF",
"if_for : IF cond error bloque_then_for ELSE bloque_else_for END_IF",
"if_for : IF cond THEN bloque_then_for ELSE bloque_else_for error",
"bloque_then_fun : bloq_ejecutable_fun",
"bloque_then_fun : sentencia_e_fun PUNTO_COMA",
"bloque_else_fun : bloq_ejecutable_fun",
"bloque_else_fun : sentencia_e_fun PUNTO_COMA",
"if_fun : IF cond THEN bloque_then_fun ELSE bloque_else_fun END_IF",
"if_fun : IF cond THEN bloque_then_fun END_IF",
"if_fun : IF cond THEN bloque_then_fun error bloque_else_fun END_IF",
"if_fun : IF cond error bloque_then_fun ELSE bloque_else_fun END_IF",
"if_fun : IF cond THEN bloque_then_fun ELSE bloque_else_fun error",
"bloque_then : bloq_ejecutable",
"bloque_then : sentencia_e_gen PUNTO_COMA",
"bloque_else : bloq_ejecutable",
"bloque_else : sentencia_e_gen PUNTO_COMA",
"if : IF cond THEN bloque_then ELSE bloque_else END_IF",
"if : IF cond THEN bloque_then END_IF",
"if : IF cond THEN bloque_then error bloque_else END_IF",
"if : IF cond error bloque_then ELSE bloque_else END_IF",
"if : IF cond THEN bloque_then ELSE bloque_else error",
"if : IF cond error bloque_then END_IF",
"cont_cadena : CADENA",
"cont_cadena : ID",
"out : OUT PARE_AB cont_cadena PARE_CIE",
"out : OUT PARE_AB error",
"factor : ID",
"factor : invocacion_fun",
"factor : constante_num",
"termino : factor",
"termino : termino MULT factor",
"termino : termino DIV factor",
"e_aritmetica : termino",
"e_aritmetica : e_aritmetica SUM termino",
"e_aritmetica : e_aritmetica MENOS termino",
"e_aritmetica : MENOS termino",
"op_aritemtica_cast : TOF64 PARE_AB e_aritmetica PARE_CIE",
"op_aritemtica_cast : e_aritmetica",
"op_aritemtica_cast : TOF64 error e_aritmetica PARE_CIE",
"op_aritemtica_cast : TOF64 PARE_AB e_aritmetica error",
"tipo : UI8",
"tipo : F64",
"comp : MAYOR",
"comp : MENOR",
"comp : IGUAL",
"comp : MAYOR_IGUAL",
"comp : MENOR_IGUAL",
"comp : DISTINTO",
"cond_for : ID comp op_aritemtica_cast",
"cond_for : error comp op_aritemtica_cast",
"cond_for : ID error op_aritemtica_cast",
"cond_for : ID comp error",
"signo : SUM",
"signo : MENOS",
"crecim_for : signo constante_num",
"crecim_for : constante_num",
"crecim_for : error constante_num",
"crecim_for : signo error",
"asignacion_for : ID ASIGNACION op_aritemtica_cast",
"asignacion_for : ID IGUAL op_aritemtica_cast",
"asignacion_for : ID ASIGNACION error",
"clausula_for : PARE_AB asignacion_for PUNTO_COMA cond_for PUNTO_COMA crecim_for PARE_CIE",
"clausula_for : PARE_AB asignacion_for error cond_for PUNTO_COMA crecim_for PARE_CIE",
"clausula_for : PARE_AB asignacion_for PUNTO_COMA cond_for error crecim_for PARE_CIE",
"cuerpoFor : bloq_ejecutable_for",
"cuerpoFor : sentencia_e_for",
"tag : ID DOS_PUNTOS",
"for : FOR clausula_for cuerpoFor",
"$$3 :",
"for : tag $$3 FOR clausula_for cuerpoFor",
"cuerpoForFun : bloq_ejecutable_for_fun",
"cuerpoForFun : sentencia_e_for_fun",
"for_fun : FOR clausula_for cuerpoForFun",
"$$4 :",
"for_fun : tag $$4 FOR clausula_for cuerpoForFun",
"const_when : ID",
"const_when : constante_num",
"const_when : error",
"cond_when : PARE_AB const_when comp const_when PARE_CIE",
"cond_when : error const_when comp const_when PARE_CIE",
"cond_when : PARE_AB const_when error const_when PARE_CIE",
"cond_when : PARE_AB const_when comp const_when error",
"cond_when : PARE_AB error PARE_CIE",
"when : WHEN cond_when THEN bloq_general",
"when : WHEN cond_when error bloq_general",
"when_fun : WHEN cond_when THEN bloq_general_fun",
"when_fun : WHEN cond_when error bloq_general_fun",
};

//#line 1129 "gramatica_final.y"
 



  static Iterator<Token> iterador;
  int lineaUltimoToken;
  static List<String> estructurasReconocidas = new ArrayList<String>();
  public static String erroresSintacticos = "";
  private List<Parametro> listaParametrosReales = new ArrayList<Parametro>();
  private List<Token> declaracionVarActual = new ArrayList<Token>();
  PilaAmbitos pila;
  public static String erroresSemanticos = "";
  private static TablaSimbolos ts = TablaSimbolos.getInstancia();
  static AnalizadorLexico lexer;
  static ArbolSintactico arbolPrograma;
  private boolean dentroWhen = false;
  private Stack<List<String>> identificadoresWhen = new Stack();
  private Stack<String> etiquetasAnidadas = new Stack();

   private static HashMap<String, ElemFuncion> funciones = new HashMap<String, ElemFuncion>();
  //private static HashMap<String, Boolean> retornoFunc = new HashMap<String, Boolean>();
  //private static HashMap<String, ArbolSintactico> funciones = new HashMap<String, ArbolSintactico>();
  //private static HashMap<String, ArbolSintactico> sentenciasDiferidas = new HashMap<String, ArbolSintactico>();


  int yylex() {

    if (!iterador.hasNext()) {
      System.out.println("Fin de archivo.");
      return -1;
    } else {
      Token a = iterador.next();
      lineaUltimoToken = a.getNroLinea();
      // System.out.println("SIGUIENTE TOKEN : " + a.getId());
      this.yylval = new ParserVal(a);

      return a.getId();
    }

  }

   private float convertirValor(String ref) { // Se trata de convertir la constante en un int, si falla significa que es
                                           // una referencia a una constante en la tabla (Revisado previamente) por lo
                                           // que se pide su valor.
    float resultado = -1;
    if (erroresSemanticos.isEmpty()) { // Si hay error semantico puede causar que la información pasada por referencia
                                       // sea erronea , por ej constante inexistente, valor usado para variable, tipo
                                       // fuera de ui8 etc
      if (ts.getTipo(ref).equals("ui8")) {
        try {
          resultado = Integer.parseInt(ref);
        } catch (Exception e) {
          resultado = Integer.parseInt(ts.getValor(ref));

        }
      } else{
        float valor;
        try {
          resultado = Float.parseFloat(ref);
        } catch (Exception e) {
          resultado = Float.parseFloat(ts.getValor(ref));

        }
      }
    }
    return resultado;
  }

  private boolean comparacion(float op1, float op2, String lexema) {
    switch (lexema) {
      case "<":
        return op1 < op2;
      case "<=":
        return op1 <= op2;
      case ">=":
        return op1 >= op2;
      case ">":
        return op1 > op2;
      case "=":
        return op1 == op2;
    }
    return false;
  }


  void yyerror(String s) {
    if (!s.equals("syntax error")) {
      // System.out.println(s);
      erroresSintacticos = erroresSintacticos.concat(s + "\n");
    }
  }

  public static void main(String[] args) {

    if (args.length > 0 && FileHandler.existeArchivo(args[0])) {
      System.out.println("Analizando codigo en " + args[0]);
      lexer = new AnalizadorLexico(args[0]);
      List<Token> resultadoLexico = AnalizadorLexico.getTokenReconocidos();
      FileHandler.resultadoLexico(resultadoLexico, args[0]);
      iterador = resultadoLexico.iterator();

      System.out.println("Errores lexicos");
      System.out.println(lexer.erroresLexicos);

      Parser parserTest = new Parser();

      parserTest.run();

      System.out.println("Estructuras sintacticas reconocidas ---------------------------------------");
      verEstructurasReconocidas();

      System.out.println("Errores sintacticos ---------------------------------------");
      System.out.println(erroresSintacticos);

      System.out.println("Errores semanticos encontrados -------------------------------------------------");
      System.out.println(erroresSemanticos);

      if (sinErrores()) {
		//System.out.println("Arbol principal:");
		//arbolPrograma.recorrerArbol("");
        System.out.println("Ambitos:");
        for (var entry : funciones.entrySet()) {
          System.out.println("Funcion: " + entry.getKey());
          if (entry.getValue().getCuerpoFuncion()!= null)
            entry.getValue().getCuerpoFuncion().recorrerArbol("");
          else
            System.out.println("Arbol de ejecución vacío.");
        }

        System.out.println("Tabla de simbolos");
        System.out.println(ts);

		GeneradorAssembler asm = new GeneradorAssembler(parserTest);
		asm.generarSalida(args[0]+ "-Assembler");

		
      }else{
        System.out.println("Hasta no resolver todos los problemas del codigo no se genera código intermedio ni assembler.");
      }
      FileHandler.resultadoSintactico(estructurasReconocidas, args[0]);
    } else
      System.err.println("Revisar los parametros de entrada del Jar.");

  }

  private static void verEstructurasReconocidas() {
    for (String a : estructurasReconocidas) {
      System.out.println(a);
    }
  }

  public String getFunciones() {

  String salida = "\n";
  for (String s : funciones.keySet()) {
      if (!s.equals("main")) {
        ArbolSintactico a = funciones.get(s).getCuerpoFuncion();
        salida += funciones.get(s).getId().replace(".","_") + ":" + "\n"
	           + a.getAssembler() 
               + "\n";
        }
      }
    return salida; 
  }

  private static boolean sinErrores() {

    return erroresSintacticos.isEmpty() && lexer.erroresLexicos.isEmpty() && erroresSemanticos.isEmpty();

  }

  void agregarResultado(String s) {
    // Para ignorar el error automatico del yacc
    // System.out.println(s);
    estructurasReconocidas.add(s);

  }

  void declararTipos(String tipo) {
    for (Token idAct : declaracionVarActual) {
      if (ts.getUso(idAct.getRef()) != null && ts.getUso(idAct.getRef()).equals("Variable"))
        ts.setTipo(idAct.getRef(), tipo);
    }
    declaracionVarActual.clear();
  }

  void AgregarErrorSemantico(String s) {
    if (!s.equals("syntax error")) {
      // System.out.println(s);
      erroresSemanticos = erroresSemanticos.concat(s + "\n");
    }
  }


  
  private void setPrograma(Token token) {
    ts.disminuir(token.getRef());
    pila = new PilaAmbitos("main");
	funciones.put("main" , new ElemFuncion());
  }


  private boolean existeIdEnAmbito(Token variable, String uso) {

    if (pila.estaEnAmbitoGlobal()) {

      String aux = ts.getUso("id_" + variable.getLexema() + "." + pila.getAmbitoActual());
      boolean result = false;
      if ((aux != null) && (aux.equals(uso))) {
		ts.disminuir(variable.getRef());
		variable.setRef("id_" + variable.getLexema() + "." + pila.getAmbitoActual());
        result = true;
      }
      return result;
    } else { // Estamos en ambito que no es global
      String aux = ts.getUso("id_" + variable.getLexema() + "." + pila.getAmbitoActual());
      if (aux != null && aux.equals(uso)) {
		ts.disminuir(variable.getRef());
        variable.setRef("id_" + variable.getLexema() + "." + pila.getAmbitoActual());
		return true;
      } else {
        String guardarAmbito = pila.ultimoAmbito();
        pila.eliminarUltimoAmbito();
        boolean result = existeIdEnAmbito(variable, uso);
        pila.agregarAmbito(guardarAmbito);
        return result;
      }
    }
  }

  private boolean revisarTipoRetorno(String tipoExpAritm) {
    String ambitoActual = pila.ultimoAmbito();
    boolean result = false;
    pila.eliminarUltimoAmbito();
    if (ts.getTipo("id_" + ambitoActual + "." + pila.getAmbitoActual())!=null) // Se desapila un ambito para poder agarrar el nombre de la función
      result = ts.getTipo("id_" + ambitoActual + "." + pila.getAmbitoActual()).equals(tipoExpAritm);
    pila.agregarAmbito(ambitoActual);
    return result;
  }

 
  public void verificarDeclaracionVariables() {
    for (Token idAct : declaracionVarActual) {
      altaDeVariable(idAct);
    }

  }


  private void altaDeVariable(Token id) {
    String IdNuevo = id.getRef() + "." + pila.getAmbitoActual();
    //System.out.println("Alta de variable, ID: " + id.getLexema() + ". IdNuevo: " + IdNuevo);
    if (ts.existeEnTS(IdNuevo)) {
      ts.disminuir(id.getRef());
      AgregarErrorSemantico("Identificador utilizado para variable existente en ambito. " + lineaUltimoToken);
    } else {
      ts.disminuir(id.getRef());
      id.setRef(IdNuevo);
      ts.altaEnTS(IdNuevo, "", "Variable");
	  if(dentroWhen)
	  	identificadoresWhen.peek().add(IdNuevo);	
    }

  }

 private void altaDeTag(Token id) {
    System.out.println("TAG " + id.getRef());
    String IdNuevo = id.getRef()+ "." + pila.getAmbitoActual();
    System.out.println("Alta de TAG, ID: " + id.getRef() + ". IdNuevo: " + IdNuevo);
    if (ts.existeEnTS(IdNuevo)) {
      ts.disminuir(id.getRef());
      AgregarErrorSemantico("Identificador utilizado para tag ya existente en ambito. " + lineaUltimoToken);
    } else {
      ts.disminuir(id.getRef());
      id.setRef(IdNuevo);
      ts.altaEnTS(IdNuevo, "", "Tag");
	  if(dentroWhen)
	  	identificadoresWhen.peek().add(IdNuevo);
    }
  }

  private void altaDeConstante(Token id) {
    String IdNuevo = id.getRef() + "." + pila.getAmbitoActual();
    System.out.println("Alta de constate, ID: " + id.getLexema() + ". IdNuevo: " + IdNuevo);
    if (ts.existeEnTS(IdNuevo)) {
      ts.disminuir(id.getRef());
      AgregarErrorSemantico("Identificador utilizado para constante existente en ambito. " + lineaUltimoToken);
    } else {
      ts.disminuir(id.getRef());
      id.setRef(IdNuevo);
      ts.altaEnTS(IdNuevo, "", "Constante");
	  if(dentroWhen)
	  	identificadoresWhen.peek().add(IdNuevo);
    }

  }

  private void altaDeParametro(Token idAct, String tipo) {

    String IdNuevo = idAct.getRef() + "." + pila.getAmbitoActual();
    if (ts.existeEnTS(IdNuevo)) {
      AgregarErrorSemantico("Identificador ya existente en ambito. " + lineaUltimoToken);
    } else {
      ts.disminuir(idAct.getRef());
      idAct.setRef(IdNuevo);

      ts.altaEnTS(IdNuevo, tipo, "Parametro");
	   if(dentroWhen)
	  	identificadoresWhen.peek().add(IdNuevo);
    }
   
  }

  private boolean cumplenParametros(String idFun, List<Parametro> params) {
    System.out.println(idFun);
    switch (ts.cantidadParametros(idFun)) {
      case 2:
        if (params.size() == 2) {
          return (ts.getParametro1(idFun).getTipo().equals(params.get(0).getTipo())) &&
              (ts.getParametro2(idFun).getTipo().equals(params.get(1).getTipo()));
        } else {
          AgregarErrorSemantico("Esperados 2 parametros para llamado a funcion. Linea " + lineaUltimoToken);
          return false;
        }
      case 1:
        if (params.size() == 1) {
          return (ts.getParametro1(idFun).getTipo().equals(params.get(0).getTipo()));
        } else {
          AgregarErrorSemantico("Esperado 1 parametros para llamado a funcion. Linea " + lineaUltimoToken);
          return false;
        }
      case 0:
        if (params.size() != 0) {
          AgregarErrorSemantico("Esperado 0 parametros para función. Linea " + lineaUltimoToken);
          return false;
        }
        return true;
    }

    return false;

  }

  private void altaDeFuncion(Token idAct) {

    String IdNuevo = idAct.getRef() + "." + pila.getAmbitoActual();
    if (ts.existeEnTS(IdNuevo)) {
      AgregarErrorSemantico("Identificador utilizado para función ya existente en ambito. " + lineaUltimoToken);
    } else {
      ts.disminuir(idAct.getRef());
      ts.altaEnTS(IdNuevo, "", "Funcion");
      idAct.setRef(IdNuevo);
	  if(dentroWhen)
	  	identificadoresWhen.peek().add(IdNuevo);
      pila.agregarAmbito(idAct.getLexema()); // se recorta el substring para que no aparezca el 'id_'

    }
}

private boolean revisarRamasRetorno(ArbolSintactico a){
  try{
    switch(a.getLexema()){
      case "Retorno":
        return true;
      case "CuerpoIF":
        return revisarRamasRetorno(a.getIzq()) && revisarRamasRetorno(a.getDer());
      case "IF":
      case "For":
        return revisarRamasRetorno(a.getDer());
      case "Cuerpo For":
      case "Sentencia":
      return revisarRamasRetorno(a.getIzq());
      default:
        return false;
    
    }
  }catch(Exception e ){
    return false;
  }
}


public ArbolSintactico getArbProg() {
  return funciones.get("main").getCuerpoFuncion();
}

	private void insertarSentenciaDiferida(ArbolSintactico sentenciaNueva){
		ElemFuncion aux = funciones.get(pila.getAmbitoActual());
		if (aux.getSentenciasDiferidas()!=null){
			aux.setSentenciasDiferidas(new NodoComun("Sentencia Diferida", sentenciaNueva, aux.getSentenciasDiferidas()));
		}else{
			aux.setSentenciasDiferidas(sentenciaNueva);
		}
	}
//#line 1262 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 25 "gramatica_final.y"
{setPrograma(((Token)val_peek(0).obj)); }
break;
case 2:
//#line 26 "gramatica_final.y"
{
		funciones.get("main").setCuerpoFuncion((ArbolSintactico) val_peek(0).obj);
		agregarResultado("Programa terminado . Linea " + lineaUltimoToken);
		}
break;
case 3:
//#line 35 "gramatica_final.y"
{yyval = val_peek(0);}
break;
case 4:
//#line 36 "gramatica_final.y"
{yyval = val_peek(0);}
break;
case 12:
//#line 49 "gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia asignación diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) val_peek(0).obj);
		}
break;
case 13:
//#line 53 "gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia out diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) val_peek(0).obj);
		}
break;
case 14:
//#line 57 "gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia invocación diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) val_peek(0).obj);
		}
break;
case 18:
//#line 66 "gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia if diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) val_peek(0).obj);
		}
break;
case 19:
//#line 70 "gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia for diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) val_peek(0).obj);
		}
break;
case 20:
//#line 74 "gramatica_final.y"
{
			yyerror("Sentencia de retorno fuera de función. Linea: " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
break;
case 24:
//#line 84 "gramatica_final.y"
{
				agregarResultado("Sentencia Break con TAG dentro de for. Linea " + lineaUltimoToken);
				Token id =(Token) val_peek(0).obj ;
				if(!existeIdEnAmbito(id,"Tag"))
					AgregarErrorSemantico("Tag no declarado. Linea " + lineaUltimoToken);
				else 
					if(!etiquetasAnidadas.contains(id.getLexema()))
						AgregarErrorSemantico("Tag declarado pero no se encuentra anidado en las sentencias de control. Linea " + lineaUltimoToken);

				
				yyval = new ParserVal(new NodoControl("Break con TAG", new NodoHoja(id.getLexema(), "", id.getLexema())));}
break;
case 25:
//#line 95 "gramatica_final.y"
{
				yyerror("':' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 26:
//#line 98 "gramatica_final.y"
{
				agregarResultado("Sentencia Break. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Break",""));
				}
break;
case 27:
//#line 102 "gramatica_final.y"
{
				agregarResultado("Sentencia CONTINUE dentro de for. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Continue",""));
				}
break;
case 35:
//#line 119 "gramatica_final.y"
{
							insertarSentenciaDiferida((ArbolSintactico) val_peek(0).obj);
			   }
break;
case 36:
//#line 122 "gramatica_final.y"
{
							insertarSentenciaDiferida((ArbolSintactico) val_peek(0).obj);
			   }
break;
case 40:
//#line 131 "gramatica_final.y"
{
					agregarResultado("Sentencia Break con . Linea " + lineaUltimoToken);
					Token id =(Token) val_peek(0).obj ;
					if(!existeIdEnAmbito(id,"Tag"))
						AgregarErrorSemantico("Tag no declarado. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Sentencia Break con TAG",""));}
break;
case 41:
//#line 137 "gramatica_final.y"
{
					agregarResultado("Sentencia Break. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Break",""));}
break;
case 42:
//#line 140 "gramatica_final.y"
{
					agregarResultado("Sentencia CONTINUE dentro de for. Linea " + lineaUltimoToken);}
break;
case 43:
//#line 148 "gramatica_final.y"
{
				if(val_peek(1).obj != null)
					yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,null ));
			}
break;
case 44:
//#line 152 "gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 45:
//#line 156 "gramatica_final.y"
{
				if(val_peek(1).obj != null)
					yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,(ArbolSintactico) val_peek(2).obj ));
			}
break;
case 46:
//#line 160 "gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 47:
//#line 168 "gramatica_final.y"
{
				yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,null ));}
break;
case 48:
//#line 170 "gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 49:
//#line 174 "gramatica_final.y"
{
				yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,(ArbolSintactico) val_peek(2).obj ));}
break;
case 50:
//#line 176 "gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 51:
//#line 180 "gramatica_final.y"
{
				yyerror("Sentencia ejecutable esperada : Linea  " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 52:
//#line 187 "gramatica_final.y"
{
				yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,null ));
}
break;
case 53:
//#line 190 "gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 54:
//#line 194 "gramatica_final.y"
{
			yyval = new ParserVal(new NodoComun("Sentencia declarativa",(ArbolSintactico) val_peek(1).obj ,(ArbolSintactico) val_peek(2).obj ));	
			}
break;
case 55:
//#line 197 "gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 56:
//#line 201 "gramatica_final.y"
{
			yyerror("Sentencia ejecutable o de iteración esperada . Linea " + lineaUltimoToken);
			yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 57:
//#line 207 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,null ));}
break;
case 58:
//#line 208 "gramatica_final.y"
{
					yyerror("';' esperado en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
				   }
break;
case 59:
//#line 213 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,(ArbolSintactico) val_peek(2).obj ));}
break;
case 60:
//#line 214 "gramatica_final.y"
{
					yyerror("';' esperado en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
				   }
break;
case 61:
//#line 218 "gramatica_final.y"
{
					yyerror("Sentencia ejecutable o declarativa de función esperada . Linea " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 62:
//#line 224 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,null ));}
break;
case 63:
//#line 226 "gramatica_final.y"
{
					yyerror("';' esperado en linea: " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 64:
//#line 232 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,(ArbolSintactico) val_peek(2).obj ));}
break;
case 65:
//#line 234 "gramatica_final.y"
{
					yyerror("';' esperado en linea: " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 66:
//#line 237 "gramatica_final.y"
{
					yyerror("Sentencia ejecutable o de función esperada . Linea " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 67:
//#line 243 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,null ));}
break;
case 68:
//#line 244 "gramatica_final.y"
{
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
						 }
break;
case 69:
//#line 248 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) val_peek(1).obj ,(ArbolSintactico) val_peek(2).obj ));}
break;
case 70:
//#line 249 "gramatica_final.y"
{
							yyerror("';' esperado en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 71:
//#line 253 "gramatica_final.y"
{
							yyerror("Sentencia ejecutable, de iteración o de función esperada . Linea " + lineaUltimoToken);
							yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 72:
//#line 259 "gramatica_final.y"
{
				yyval = val_peek(1);
				}
break;
case 73:
//#line 262 "gramatica_final.y"
{
				yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 74:
//#line 265 "gramatica_final.y"
{
				yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 75:
//#line 268 "gramatica_final.y"
{
				yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 76:
//#line 271 "gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 77:
//#line 274 "gramatica_final.y"
{yyval= val_peek(1);}
break;
case 78:
//#line 275 "gramatica_final.y"
{
				yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 79:
//#line 278 "gramatica_final.y"
{
				yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 80:
//#line 284 "gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 81:
//#line 290 "gramatica_final.y"
{yyval=val_peek(1);}
break;
case 82:
//#line 291 "gramatica_final.y"
{
					yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 83:
//#line 294 "gramatica_final.y"
{
					yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 84:
//#line 297 "gramatica_final.y"
{
					yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 85:
//#line 300 "gramatica_final.y"
{
					yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 86:
//#line 304 "gramatica_final.y"
{ yyval = val_peek(1);}
break;
case 87:
//#line 305 "gramatica_final.y"
{yyerror("'{' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 88:
//#line 307 "gramatica_final.y"
{yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 89:
//#line 309 "gramatica_final.y"
{yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 90:
//#line 311 "gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 91:
//#line 314 "gramatica_final.y"
{yyval = val_peek(1);}
break;
case 92:
//#line 315 "gramatica_final.y"
{
							yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);}
break;
case 93:
//#line 317 "gramatica_final.y"
{
							yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 94:
//#line 320 "gramatica_final.y"
{
							yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 95:
//#line 323 "gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 96:
//#line 327 "gramatica_final.y"
{
	yyval = val_peek(1);
}
break;
case 97:
//#line 330 "gramatica_final.y"
{
							yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 98:
//#line 333 "gramatica_final.y"
{
							yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 99:
//#line 336 "gramatica_final.y"
{
							yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 100:
//#line 339 "gramatica_final.y"
{
							yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 101:
//#line 347 "gramatica_final.y"
{declaracionVarActual.add((Token)val_peek(0).obj);}
break;
case 102:
//#line 348 "gramatica_final.y"
{
		yyerror("Error en declaración de variables ',' esperada. Linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 103:
//#line 351 "gramatica_final.y"
{ declaracionVarActual.add((Token)val_peek(0).obj);}
break;
case 104:
//#line 354 "gramatica_final.y"
{verificarDeclaracionVariables();
						String tipo = ((Token)val_peek(1).obj).getLexema();
                        declararTipos(tipo);
						yyval = new ParserVal(null);
						declaracionVarActual.clear();
						}
break;
case 105:
//#line 360 "gramatica_final.y"
{
		yyerror("Error en declaración de variables Tipo desconocido. Linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
	  }
break;
case 106:
//#line 367 "gramatica_final.y"
{
	/*Alta de parametro, le paso referencia, uso "parametro" y tipo $1.getLexema()*/
	Token parametro = (Token)val_peek(0).obj;
	altaDeParametro(parametro,((Token)val_peek(1).obj).getLexema());
	yyval = new ParserVal(new Parametro(parametro.getLexema(),((Token)val_peek(1).obj).getLexema(), parametro.getRef()));

}
break;
case 107:
//#line 376 "gramatica_final.y"
{
				Parametro[] params = new Parametro[1]; /* Se devuelve arreglo de 1 es mas facil preguntar por longitud*/
				params[0] =(Parametro) val_peek(0).obj;
				yyval = new ParserVal(params);
				}
break;
case 108:
//#line 381 "gramatica_final.y"
{
				Parametro[] params = new Parametro[2];
				params[0] = (Parametro)val_peek(2).obj;
				params[1] =(Parametro) val_peek(0).obj;
				yyval = new ParserVal(params);
				}
break;
case 109:
//#line 389 "gramatica_final.y"
{
				yyval = val_peek(1);}
break;
case 110:
//#line 391 "gramatica_final.y"
{
				yyval = new ParserVal(new Parametro[0]);}
break;
case 111:
//#line 393 "gramatica_final.y"
{
				 yyerror("Error en parametros de función. Linea " + lineaUltimoToken);
			    yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 112:
//#line 400 "gramatica_final.y"
{
	Token ID = (Token) val_peek(0).obj;
	altaDeFuncion(ID);
	if(erroresSemanticos.isEmpty()){
	funciones.put(pila.getAmbitoActual(),new ElemFuncion());
	funciones.get(pila.getAmbitoActual()).setRetorno(false);
	}
	yyval = val_peek(0);
	
}
break;
case 113:
//#line 411 "gramatica_final.y"
{
			String tipo =((Token) val_peek(0).obj).getLexema();
			yyval = new ParserVal(tipo);
}
break;
case 114:
//#line 415 "gramatica_final.y"
{
			yyerror("Tipo esperado en declaración de funcion. Linea " + lineaUltimoToken);}
break;
case 115:
//#line 417 "gramatica_final.y"
{
			yyerror("Tipo esperado en declaración de funcion. Linea " + lineaUltimoToken);}
break;
case 116:
//#line 421 "gramatica_final.y"
{
			try{Token ID = (Token) val_peek(2).obj;
			Parametro[] a = (Parametro[]) val_peek(1).obj;
			if(a.length>1){
				ts.setParametro1(ID.getRef(), a[0]);
				ts.setParametro2(ID.getRef(), a[1]);
			}
			else if (a.length == 1) 
				ts.setParametro1(ID.getRef(), a[0]);

			if (ts.getUso(((Token) val_peek(2).obj).getRef()).equals("Funcion")) /* Se asigna tipo de función*/
				ts.setTipo(((Token) val_peek(2).obj).getRef(), val_peek(0).sval);
			}catch(Exception e){}
			
			}
break;
case 117:
//#line 435 "gramatica_final.y"
{
					agregarResultado("Declaracion FUNCIÓN. Linea " + lineaUltimoToken);
					Token ID = (Token) val_peek(4).obj;
					
					yyval = new ParserVal(null); /* Lo que se muestra en caso de estar en el arbol principal */
					if(erroresSemanticos.isEmpty()){
					if(!funciones.get(pila.getAmbitoActual()).isRetorno())
						AgregarErrorSemantico("Función sin sentencia de retorno asociada. Linea " + lineaUltimoToken);
					if(!revisarRamasRetorno(((ArbolSintactico) val_peek(0).obj).getIzq()))
						AgregarErrorSemantico("No existe una sentencia de retorno asociada por cada rama de ejecución. Linea " + lineaUltimoToken);
					funciones.get(pila.getAmbitoActual()).setCuerpoFuncion((ArbolSintactico) val_peek(0).obj);
					funciones.get(pila.getAmbitoActual()).setId(ID.getRef());
					pila.eliminarUltimoAmbito();}}
break;
case 118:
//#line 450 "gramatica_final.y"
{
							agregarResultado("Declaracion asignacion constante en linea " + lineaUltimoToken);				
                            Token id = ((Token)val_peek(2).obj);
				
							altaDeConstante(id);
                			if(ts.existeEnTS(id.getRef()) && ts.getUso(id.getRef()).equals("Constante")){
                				ts.setTipo(id.getRef(),((ArbolSintactico)val_peek(0).obj).getTipo());
								ts.setValor(id.getRef(),((ArbolSintactico)val_peek(0).obj).getLexema() );
                			}
							yyval = new ParserVal(new NodoHoja("Declaración de constantes", ts.getTipo(id.getRef()), id.getRef() ));}
break;
case 119:
//#line 460 "gramatica_final.y"
{
					yyerror("':' esperado en asignacion "  + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 120:
//#line 463 "gramatica_final.y"
{
					yyerror("error en valor de asignación, valor constante esperado. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 121:
//#line 470 "gramatica_final.y"
{yyval = val_peek(0);}
break;
case 122:
//#line 471 "gramatica_final.y"
{yyval = val_peek(0);}
break;
case 123:
//#line 474 "gramatica_final.y"
{
		agregarResultado("Declaracion CONST. Linea " + lineaUltimoToken);
		yyval = new ParserVal (null);
		}
break;
case 124:
//#line 481 "gramatica_final.y"
{
				String valor  = ((Token)val_peek(0).obj).getLexema();
				yyval = new ParserVal(new NodoHoja(valor, ts.getTipo(valor), valor));
				ts.setValor(valor, valor);
				yyval.sval = valor;}
break;
case 125:
//#line 486 "gramatica_final.y"
{
				String valor  = ((Token)val_peek(0).obj).getLexema();
				yyval = new ParserVal(new NodoHoja(valor, ts.getTipo(valor), valor));
				ts.setValor(valor, valor);
				yyval.sval = valor;
				}
break;
case 126:
//#line 495 "gramatica_final.y"
{
				Token ID = (Token) val_peek(0).obj; 
				if (existeIdEnAmbito(ID,"Variable") || existeIdEnAmbito(ID,"Constante")) {  
                    listaParametrosReales.add(new Parametro(ID.getLexema(), ts.getTipo(ID.getRef()), ID.getRef()));
                }
				}
break;
case 127:
//#line 501 "gramatica_final.y"
{
		   		listaParametrosReales.add(new Parametro(((ArbolSintactico)val_peek(0).obj).getLexema(),((ArbolSintactico)val_peek(0).obj).getTipo(), ((ArbolSintactico)val_peek(0).obj).getLexema()));
		   }
break;
case 130:
//#line 508 "gramatica_final.y"
{yyval = null;}
break;
case 131:
//#line 509 "gramatica_final.y"
{yyerror("Error en parametros invocación a funcion. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 132:
//#line 513 "gramatica_final.y"
{
	Token funcionInvocada = (Token)val_peek(1).obj;
	String tipoRetorno = "";
    if (existeIdEnAmbito(funcionInvocada,"Funcion")){
        if (!cumplenParametros(funcionInvocada.getRef(),listaParametrosReales))
            AgregarErrorSemantico("Tipos o cantidad de parametros erroneos"+ lineaUltimoToken);
		else 	
			tipoRetorno = ts.getTipo(funcionInvocada.getRef());
    }
    else 
        AgregarErrorSemantico("Función no declarada. Linea" + lineaUltimoToken);

	
	if(listaParametrosReales.size() == 2)
	{
		Parametro primero = listaParametrosReales.get(0);
        Parametro segundo = listaParametrosReales.get(1);

        NodoHoja primerParametro = new NodoHoja(primero.getId(), primero.getTipo(), primero.getReferencia());
        Parametro primerFormal = ts.getParametro1(funcionInvocada.getRef());
        if (primerFormal != null)
           primerParametro.setRefParametroFormal(primerFormal.getReferencia());

        Parametro segundoFormal = ts.getParametro2(funcionInvocada.getRef());
        NodoHoja segundoParametro = new NodoHoja(segundo.getId(), segundo.getTipo(), segundo.getReferencia());
        if (segundoFormal != null)
            segundoParametro.setRefParametroFormal(ts.getParametro2(funcionInvocada.getRef()).getReferencia());
		
		yyval = new ParserVal(new NodoComun("Invocación a función", new NodoHoja(funcionInvocada.getLexema(), tipoRetorno, funcionInvocada.getRef(), true),new NodoComun("Parametros", primerParametro, segundoParametro)));
	}
	else if(listaParametrosReales.size() == 1)
	{
		Parametro primero = listaParametrosReales.get(0);

            NodoHoja primerParametro = new NodoHoja(primero.getId(), primero.getTipo(), primero.getReferencia());
            Parametro primerFormal = ts.getParametro1(funcionInvocada.getRef());
            if (primerFormal != null)
              primerParametro.setRefParametroFormal(primerFormal.getReferencia());

		yyval = new ParserVal(new NodoComun("Invocación a función", new NodoHoja(funcionInvocada.getLexema(), tipoRetorno, funcionInvocada.getRef(),true), new NodoComun("Parametros",primerParametro, null)));
	}
	else {
		yyval = new ParserVal(new NodoComun("Invocación a función", new NodoHoja(funcionInvocada.getLexema(), tipoRetorno, funcionInvocada.getRef(), true), new NodoComun("Parametros",null, null)));
	}
	((ArbolSintactico)yyval.obj).setTipo(tipoRetorno);
	
	listaParametrosReales.clear();

}
break;
case 133:
//#line 564 "gramatica_final.y"
{agregarResultado("Asignacion en linea " + lineaUltimoToken);
							  Token id = (Token)val_peek(2).obj;
                              if (!existeIdEnAmbito(id, "Variable")) 
            					AgregarErrorSemantico("Variable no definida . Linea " + lineaUltimoToken);

							  yyval = new ParserVal(new NodoComun("=:", new NodoHoja(id.getLexema(), ts.getTipo(id.getRef()), id.getRef()),(ArbolSintactico)val_peek(0).obj));
							  
							  if(((ArbolSintactico) yyval.obj).getTipo().equals("error")) 
							  	AgregarErrorSemantico("Tipos no compatibles en asignación . Linea " + lineaUltimoToken);
							  }
break;
case 134:
//#line 574 "gramatica_final.y"
{
			yyerror("':' esperado en asignacion "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 135:
//#line 577 "gramatica_final.y"
{
			yyerror("error en valor de asignación. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 136:
//#line 584 "gramatica_final.y"
{
				agregarResultado("Sentencia RETURN. Linea " + lineaUltimoToken);
				if(!revisarTipoRetorno(((ArbolSintactico)val_peek(0).obj).getTipo())){
					AgregarErrorSemantico("Tipo erroneo en sentencia Return. Linea: " + lineaUltimoToken);
				}
				try{
				funciones.get(pila.getAmbitoActual()).setRetorno(true);
				ArbolSintactico sentenciasDiferidas = funciones.get(pila.getAmbitoActual()).getSentenciasDiferidas();
				if (sentenciasDiferidas!=null)
					yyval = new ParserVal(new NodoComun(("Sentencias Diferidas"), new NodoControl("Retorno",(ArbolSintactico) val_peek(0).obj) , sentenciasDiferidas));
				else
					yyval = new ParserVal(new NodoControl("Retorno",(ArbolSintactico) val_peek(0).obj));
				}
				catch(Exception e){
					yyval = new ParserVal(new NodoHoja("Error en retorno", ""));
				}
				}
break;
case 137:
//#line 601 "gramatica_final.y"
{
			yyerror("Error en valor de retorno. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
break;
case 138:
//#line 610 "gramatica_final.y"
{
		yyval = new ParserVal(new NodoComun( ((Token)val_peek(1).obj).getLexema(), (ArbolSintactico)val_peek(2).obj ,(ArbolSintactico)val_peek(0).obj));  
		if(((ArbolSintactico) yyval.obj).getTipo().equals("error")) 
							  	AgregarErrorSemantico("Tipos no compatibles en comparación . Linea " + lineaUltimoToken);
							
		}
break;
case 139:
//#line 616 "gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 140:
//#line 617 "gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 141:
//#line 618 "gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 142:
//#line 621 "gramatica_final.y"
{
		yyval = new ParserVal(new NodoControl("CondicionIF",(ArbolSintactico) val_peek(1).obj));
	}
break;
case 143:
//#line 624 "gramatica_final.y"
{
		yyerror("Esperado '(' en linea: " + lineaUltimoToken);
	}
break;
case 144:
//#line 627 "gramatica_final.y"
{
		yyerror("Esperado ')' en linea: " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 145:
//#line 630 "gramatica_final.y"
{
		yyerror("Error en condición, linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 150:
//#line 648 "gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) val_peek(3).obj, (ArbolSintactico) val_peek(1).obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(5).obj,cuerpo));
			
			}
break;
case 151:
//#line 655 "gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) val_peek(1).obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(3).obj,cuerpo));
	}
break;
case 152:
//#line 661 "gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 153:
//#line 664 "gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 154:
//#line 667 "gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 155:
//#line 673 "gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) val_peek(0).obj));}
break;
case 156:
//#line 675 "gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) val_peek(1).obj));
		   }
break;
case 157:
//#line 680 "gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) val_peek(0).obj));}
break;
case 158:
//#line 682 "gramatica_final.y"
{
		   	yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) val_peek(1).obj));}
break;
case 159:
//#line 687 "gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) val_peek(3).obj, (ArbolSintactico) val_peek(1).obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(5).obj,cuerpo));
			}
break;
case 160:
//#line 693 "gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) val_peek(1).obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(3).obj,cuerpo));
	
		}
break;
case 161:
//#line 701 "gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 162:
//#line 704 "gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 163:
//#line 707 "gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 168:
//#line 724 "gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) val_peek(3).obj, (ArbolSintactico) val_peek(1).obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(5).obj,cuerpo));
			
			}
break;
case 169:
//#line 731 "gramatica_final.y"
{
		agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
		NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) val_peek(1).obj);
		yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(3).obj,cuerpo));
	}
break;
case 170:
//#line 737 "gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 171:
//#line 740 "gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 172:
//#line 743 "gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 173:
//#line 749 "gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) val_peek(0).obj));}
break;
case 174:
//#line 751 "gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) val_peek(1).obj));
		   }
break;
case 175:
//#line 756 "gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) val_peek(0).obj));}
break;
case 176:
//#line 758 "gramatica_final.y"
{
		   	yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) val_peek(1).obj));}
break;
case 177:
//#line 763 "gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) val_peek(3).obj, (ArbolSintactico) val_peek(1).obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(5).obj,cuerpo));
			}
break;
case 178:
//#line 769 "gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) val_peek(1).obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) val_peek(3).obj,cuerpo));
	
		}
break;
case 179:
//#line 777 "gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 180:
//#line 780 "gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 181:
//#line 783 "gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 182:
//#line 786 "gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 183:
//#line 794 "gramatica_final.y"
{
				String lexema = ((Token)val_peek(0).obj).getLexema();
				yyval = new ParserVal(new NodoHoja(lexema, "str"));
}
break;
case 184:
//#line 798 "gramatica_final.y"
{      
				Token id = (Token)val_peek(0).obj;
                 if (!(existeIdEnAmbito(id, "Variable") || existeIdEnAmbito(id, "Constante") || existeIdEnAmbito(id, "Parametro") )) {
                  AgregarErrorSemantico("Identificador inexistente para sentencia de out. Linea " + lineaUltimoToken); 
                 }
				 yyval = new ParserVal(new NodoHoja(id.getRef(), ts.getTipo(id.getRef()), id.getRef()));
				}
break;
case 185:
//#line 808 "gramatica_final.y"
{
	agregarResultado("Sentencia OUT. Linea " + lineaUltimoToken);
	yyval = new ParserVal(new NodoControl("Sentencia out",(ArbolSintactico) val_peek(1).obj ));	
	}
break;
case 186:
//#line 813 "gramatica_final.y"
{
		yyerror("Error en contenido del OUT");
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 187:
//#line 820 "gramatica_final.y"
{
		Token id = (Token) val_peek(0).obj;
		if(!existeIdEnAmbito(id,"Variable") &&  !existeIdEnAmbito(id,"Parametro") && !existeIdEnAmbito(id,"Constante")){
			AgregarErrorSemantico("Variable no declarada en la expresión. Linea " + lineaUltimoToken);	
		}
		yyval = new ParserVal(new NodoHoja(id.getLexema(),ts.getTipo(id.getRef()), id.getRef()));}
break;
case 189:
//#line 827 "gramatica_final.y"
{ yyval = val_peek(0);}
break;
case 190:
//#line 830 "gramatica_final.y"
{yyval = val_peek(0);}
break;
case 191:
//#line 831 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("*", (ArbolSintactico)val_peek(2).obj, (ArbolSintactico)val_peek(0).obj));}
break;
case 192:
//#line 832 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("/", (ArbolSintactico)val_peek(2).obj, (ArbolSintactico)val_peek(0).obj));}
break;
case 193:
//#line 835 "gramatica_final.y"
{yyval = val_peek(0);}
break;
case 194:
//#line 836 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("+", (ArbolSintactico)val_peek(2).obj, (ArbolSintactico)val_peek(0).obj));}
break;
case 195:
//#line 837 "gramatica_final.y"
{yyval = new ParserVal(new NodoComun("-", (ArbolSintactico)val_peek(2).obj, (ArbolSintactico)val_peek(0).obj));}
break;
case 196:
//#line 838 "gramatica_final.y"
{yyval = new ParserVal( new NodoHoja("-"+((ArbolSintactico)val_peek(0).obj).getLexema(), ((ArbolSintactico)val_peek(0).obj).getTipo()));}
break;
case 197:
//#line 841 "gramatica_final.y"
{
					agregarResultado("Casteo explicito. Linea " + lineaUltimoToken);
					ArbolSintactico exp = (ArbolSintactico)val_peek(1).obj;
					ArbolSintactico cast = exp;
					if(exp.getTipo().equals("ui8")){
						exp.setTipo("f64");
						cast = new NodoControl("Casteo TOF64", exp);
					}else
						AgregarErrorSemantico("No se puede convertir a f64 desde un tipo que no sea ui8. Linea " + lineaUltimoToken);
					yyval = new ParserVal(cast);
}
break;
case 198:
//#line 852 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 199:
//#line 853 "gramatica_final.y"
{
			yyerror("'(' esperado en casteo explicito, linea :" + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 200:
//#line 856 "gramatica_final.y"
{
			yyerror("')' esperado en casteo explicito, linea :" + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 203:
//#line 865 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 204:
//#line 866 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 205:
//#line 867 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 206:
//#line 868 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 207:
//#line 869 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 208:
//#line 870 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 209:
//#line 874 "gramatica_final.y"
{
		Token ID = ((Token)val_peek(2).obj);
		if(!existeIdEnAmbito(ID,"Variable")){
			AgregarErrorSemantico("Variable inexistente en cond. del for");	
		}
		
		yyval = new ParserVal(new NodoComun( ((Token)val_peek(1).obj).getLexema(), new NodoHoja(ID.getLexema(),ts.getTipo(ID.getRef()), ID.getRef()) ,(ArbolSintactico)val_peek(0).obj)); 
		if(((ArbolSintactico) yyval.obj).getTipo().equals("error")) 
			AgregarErrorSemantico("Tipos no compatibles en comparación . Linea " + lineaUltimoToken);
		yyval.sval = ID.getRef();
}
break;
case 210:
//#line 885 "gramatica_final.y"
{
			yyerror("Error en condición del For , identificador  esperado. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 211:
//#line 888 "gramatica_final.y"
{
			yyerror("Error en condición del For , condición  esperada. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 212:
//#line 891 "gramatica_final.y"
{
			yyerror("Error en condición del For , expresión aritmetica esperada. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 213:
//#line 896 "gramatica_final.y"
{yyval = new ParserVal(((Token)val_peek(0).obj).getLexema());}
break;
case 214:
//#line 897 "gramatica_final.y"
{yyval = new ParserVal(((Token)val_peek(0).obj).getLexema());}
break;
case 215:
//#line 899 "gramatica_final.y"
{
			if(!((ArbolSintactico) val_peek(0).obj).getTipo().equals("ui8")) 
				AgregarErrorSemantico("Esperado tipo entero en sentencia de incremento / decremento . Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoControl("incr_decr", new NodoHoja(val_peek(1).sval+((ArbolSintactico)val_peek(0).obj).getLexema(),"")));
			}
break;
case 216:
//#line 904 "gramatica_final.y"
{
			if(!((ArbolSintactico) val_peek(0).obj).getTipo().equals("ui8")) 
				AgregarErrorSemantico("Esperado tipo entero en sentencia de incremento / decremento . Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoControl("incr_decr", new NodoHoja("+"+ ((ArbolSintactico)val_peek(0).obj).getLexema(),"")));
		  }
break;
case 217:
//#line 909 "gramatica_final.y"
{
			yyerror("Error en condición del for , '+' o '-' en clausula de crecimiento esperado. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 218:
//#line 913 "gramatica_final.y"
{
			yyerror("Error en condición del for , constante numerica esperada en crecimiento. Linea "  + lineaUltimoToken);
		  	yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 219:
//#line 919 "gramatica_final.y"
{
			agregarResultado("Asignacion dentro del for en linea " + lineaUltimoToken);		
			Token id = ((Token)val_peek(2).obj);
			altaDeVariable(id);
                			if(ts.existeEnTS(id.getRef()) && ts.getUso(id.getRef()).equals("Variable")){
                				ts.setTipo(id.getRef(),((ArbolSintactico)val_peek(0).obj).getTipo());
                			}else{
								AgregarErrorSemantico("Variable en el for, ya existente. Linea " + lineaUltimoToken);
							}
							yyval = new ParserVal(new NodoComun("=:", new NodoHoja(id.getLexema(), ts.getTipo(id.getRef()), id.getRef()),(ArbolSintactico)val_peek(0).obj));
							yyval.sval = id.getRef();}
break;
case 220:
//#line 931 "gramatica_final.y"
{
			yyerror("':' esperado en asignacion "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 221:
//#line 934 "gramatica_final.y"
{
			yyerror("error en valor de agnación. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 222:
//#line 939 "gramatica_final.y"
{
			if(!((ArbolSintactico)val_peek(5).obj).getTipo().equals("ui8"))
				AgregarErrorSemantico("Asignación dentro del for solo permite tipos enteros. Linea "+lineaUltimoToken);
			
			if(!((ArbolSintactico)val_peek(3).obj).getTipo().equals("ui8"))
				AgregarErrorSemantico("Condición dentro del for solo permite tipos enteros. Linea "+lineaUltimoToken);
				
			if(!val_peek(3).sval.equals(val_peek(5).sval))
				AgregarErrorSemantico("Se debe utilizar la misma variable de asignacion para la condición en for. Linea "+lineaUltimoToken);



			ArbolSintactico condicion= new NodoControl("Condicion", (ArbolSintactico) val_peek(3).obj);
			ArbolSintactico incr_decr = new NodoComun("Incr decr", (ArbolSintactico) val_peek(1).obj , condicion);
			ArbolSintactico cuerpo= new NodoComun("Cuerpo For", null, incr_decr);
			ArbolSintactico arbolFor = new NodoComun("For",(ArbolSintactico) val_peek(5).obj , cuerpo );

			yyval = new ParserVal(arbolFor);
}
break;
case 223:
//#line 958 "gramatica_final.y"
{
				yyerror("Esperado ';' en clausula del for. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 224:
//#line 961 "gramatica_final.y"
{
				yyerror("Esperado ';' en clausula del for. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 225:
//#line 966 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 226:
//#line 967 "gramatica_final.y"
{yyval=val_peek(0);}
break;
case 227:
//#line 970 "gramatica_final.y"
{
	yyval = val_peek(1);
}
break;
case 228:
//#line 976 "gramatica_final.y"
{
		agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)val_peek(0).obj;
		try{
		((ArbolSintactico) val_peek(1).obj).getDer().setIzq(cuerpo);
		}catch(Exception e){}
		yyval = val_peek(1);
		}
break;
case 229:
//#line 985 "gramatica_final.y"
{
		altaDeTag((Token)val_peek(0).obj);
		etiquetasAnidadas.push(((Token)val_peek(0).obj).getLexema());
	}
break;
case 230:
//#line 988 "gramatica_final.y"
{agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)val_peek(0).obj ;
		ArbolSintactico forCompleto = (ArbolSintactico) val_peek(1).obj;
		
		forCompleto.getDer().setIzq(cuerpo);
		forCompleto.setDer(new NodoComun("For con Tag" , new NodoHoja(((Token)val_peek(4).obj).getLexema(), ""), forCompleto.getDer()));
		etiquetasAnidadas.pop();
		yyval = val_peek(1);
		}
break;
case 233:
//#line 1003 "gramatica_final.y"
{
		agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)val_peek(0).obj;
		((ArbolSintactico) val_peek(1).obj).getDer().setIzq(cuerpo);
		yyval = val_peek(1);
		}
break;
case 234:
//#line 1009 "gramatica_final.y"
{
			altaDeTag((Token)val_peek(0).obj);
		etiquetasAnidadas.push(((Token)val_peek(0).obj).getLexema());
		}
break;
case 235:
//#line 1012 "gramatica_final.y"
{
			ArbolSintactico cuerpo = (ArbolSintactico)val_peek(0).obj ;
			ArbolSintactico forCompleto = (ArbolSintactico) val_peek(1).obj;
		
			forCompleto.getDer().setIzq(cuerpo);
			forCompleto.setDer(new NodoComun("For con Tag" , new NodoHoja(((Token)val_peek(4).obj).getLexema(), ""), forCompleto.getDer()));
			etiquetasAnidadas.pop();
		yyval = val_peek(1);
		}
break;
case 236:
//#line 1024 "gramatica_final.y"
{
		Token id =(Token)val_peek(0).obj;
		if(!existeIdEnAmbito(id,"Constante")){
			AgregarErrorSemantico("Esperada constante para comparación del when . Linea " + lineaUltimoToken);
		}
		yyval = new ParserVal(new NodoHoja(id.getLexema(), ts.getTipo(id.getRef())));
		yyval.sval = id.getRef();
	}
break;
case 237:
//#line 1032 "gramatica_final.y"
{
		yyval = val_peek(0);
	}
break;
case 238:
//#line 1035 "gramatica_final.y"
{
		yyerror("Error esperado constante en condición del when, linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
break;
case 239:
//#line 1042 "gramatica_final.y"
{ /* Solo se comparan dos constantes para poder */
			
		ArbolSintactico const1 = (ArbolSintactico) val_peek(3).obj;
		ArbolSintactico const2 = (ArbolSintactico) val_peek(1).obj;

		
		String ref1 = val_peek(3).sval;
		String ref2 = val_peek(1).sval;
		if(!ts.getTipo(ref1).equals(ts.getTipo(ref2)))
			AgregarErrorSemantico("Las dos constantes del when deben ser del mismo tipo");
		if (comparacion(convertirValor(ref1),convertirValor(ref2),((Token)val_peek(2).obj).getLexema()))
		{
			yyval = new ParserVal(new NodoControl("Cond",new NodoComun(((Token)val_peek(2).obj).getLexema(), const1 , const2)));
		}
		else
			yyval = null;
		dentroWhen = true;
		identificadoresWhen.push(new ArrayList<String>());

	}
break;
case 240:
//#line 1062 "gramatica_final.y"
{
		yyerror("Esperado '(' en linea: " + lineaUltimoToken);
	}
break;
case 241:
//#line 1064 "gramatica_final.y"
{
		yyerror("Esperado comparador en linea: " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 242:
//#line 1067 "gramatica_final.y"
{
		yyerror("Esperado ')' en linea: " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 243:
//#line 1070 "gramatica_final.y"
{
		yyerror("Error en condición del when, linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 244:
//#line 1078 "gramatica_final.y"
{
	agregarResultado("Sentencia WHEN. Linea " + lineaUltimoToken);
	if(val_peek(2) != null) /* Si el resultado del calculo de condición vino nulo significa que no se genera */
	{
		yyval = new ParserVal(new NodoComun("When",(ArbolSintactico)val_peek(2).obj , new NodoControl("CuerpoWhen",(ArbolSintactico)val_peek(0).obj  ))); /* De todas maneras el código intermedio de la condición solo se guarda*/
		List<String> aux= identificadoresWhen.pop();
    	if(!identificadoresWhen.empty()) 
      		identificadoresWhen.peek().addAll(aux);
	}																												/* para mostrar debido a que su condición en este punto fue calculada*/
	else
	{
		yyval = new ParserVal(new NodoHoja("When - condición erronea",""));
		for(String s : identificadoresWhen.pop()){
			ts.disminuir(s);
		}
	}
	if(identificadoresWhen.empty())
		dentroWhen = false;
	}
break;
case 245:
//#line 1097 "gramatica_final.y"
{
		yyerror("Then esperado en when. Linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 246:
//#line 1102 "gramatica_final.y"
{
	agregarResultado("Sentencia WHEN. Linea " + lineaUltimoToken);
	if(val_peek(2) != null) /* Si el resultado del calculo de condición vino nulo significa que no se genera */
	{
		yyval = new ParserVal(new NodoComun("When",(ArbolSintactico)val_peek(2).obj , new NodoControl("CuerpoWhen",(ArbolSintactico)val_peek(0).obj  ))); /* De todas maneras el código intermedio de la condición solo se guarda*/
		List<String> aux = identificadoresWhen.pop();
    	if(!identificadoresWhen.empty()) 
      		identificadoresWhen.peek().addAll(aux);
	}																												/* para mostrar debido a que su condición en este punto fue calculada*/
	else
	{
		yyval = new ParserVal(new NodoHoja("When - condición erronea",""));
		for(String s : identificadoresWhen.pop()){
      		ts.disminuir(s);
    	}
	}
	if(identificadoresWhen.empty())
	  dentroWhen = false;
	}
break;
case 247:
//#line 1121 "gramatica_final.y"
{
			yyerror("Falta 'then' en WHEN. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
//#line 2870 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
