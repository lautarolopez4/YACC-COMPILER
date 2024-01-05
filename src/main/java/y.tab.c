#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 1 ".\gramatica_final.y"
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

#line 24 "y.tab.c"
#define IF 257
#define THEN 258
#define ELSE 259
#define END_IF 260
#define OUT 261
#define FUN 262
#define RETURN 263
#define BREAK 264
#define WHEN 265
#define DEFER 266
#define UI8 267
#define F64 268
#define ID 269
#define CONST 270
#define CADENA 271
#define TOF64 272
#define CONTINUE 273
#define FOR 274
#define LLAVE_AB 275
#define LLAVE_CE 276
#define MENOR 277
#define MAYOR 278
#define IGUAL 279
#define MENOR_IGUAL 280
#define MAYOR_IGUAL 281
#define MENOS 282
#define SUM 283
#define MULT 284
#define DIV 285
#define PARE_AB 286
#define PARE_CIE 287
#define PUNTO_COMA 288
#define DOS_PUNTOS 289
#define COMA 290
#define ASIGNACION 291
#define DISTINTO 292
#define DOBLE 293
#define ENTERO 294
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    2,    0,    0,    3,    3,    3,    4,    4,    4,   10,
   10,   10,   10,   10,   10,    5,    5,    5,    5,    5,
    5,   17,   17,   17,   17,   17,   17,   19,   19,   19,
   20,   20,   20,   20,   20,   20,   24,   24,   24,   24,
   24,   24,   26,   26,   26,   26,   27,   27,   27,   27,
   27,   28,   28,   28,   28,   28,   29,   29,   29,   29,
   29,   30,   30,   30,   30,   30,   31,   31,   31,   31,
   31,    1,    1,    1,    1,    1,   32,   32,   32,   32,
   32,   33,   33,   33,   33,   33,   34,   34,   34,   34,
   34,   35,   35,   35,   35,   35,   36,   36,   36,   36,
   36,   37,   37,   37,    7,    7,   39,   40,   40,   41,
   41,   41,   42,   43,   43,   43,   44,    8,   45,   45,
   45,   47,   47,    9,   46,   46,   48,   48,   49,   49,
   49,   49,   13,   11,   11,   11,   16,   16,   51,   51,
   51,   51,   53,   53,   53,   53,   54,   54,   55,   55,
   25,   25,   25,   25,   25,   56,   56,   57,   57,   18,
   18,   18,   18,   18,   58,   58,   59,   59,   22,   22,
   22,   22,   22,   60,   60,   61,   61,   14,   14,   14,
   14,   14,   14,   62,   62,   12,   12,   63,   63,   63,
   64,   64,   64,   65,   65,   65,   65,   50,   50,   50,
   50,   38,   38,   52,   52,   52,   52,   52,   52,   66,
   66,   66,   66,   67,   67,   68,   68,   68,   68,   69,
   69,   69,   70,   70,   70,   71,   71,   72,   15,   73,
   15,   74,   74,   23,   75,   23,   76,   76,   76,   77,
   77,   77,   77,   77,    6,    6,   21,   21,
};
short yylen[] = {                                         2,
    0,    3,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    2,    2,    1,    1,    1,    2,    2,
    1,    1,    1,    1,    3,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    2,    2,    1,    1,    1,    3,
    1,    1,    2,    2,    3,    3,    2,    2,    3,    3,
    1,    2,    2,    3,    3,    1,    2,    2,    3,    3,
    1,    2,    2,    3,    2,    1,    2,    2,    3,    3,
    2,    3,    2,    2,    3,    2,    3,    3,    3,    3,
    2,    3,    3,    3,    3,    2,    3,    3,    3,    3,
    2,    3,    3,    3,    3,    2,    3,    3,    3,    3,
    2,    1,    1,    3,    2,    2,    2,    1,    3,    3,
    2,    3,    2,    2,    2,    1,    0,    5,    3,    3,
    3,    1,    3,    2,    1,    1,    1,    1,    5,    3,
    2,    3,    2,    3,    3,    3,    2,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    1,    2,    1,    2,
    7,    5,    7,    7,    7,    1,    2,    1,    2,    7,
    5,    7,    7,    7,    1,    2,    1,    2,    7,    5,
    7,    7,    7,    1,    2,    1,    2,    7,    5,    7,
    7,    7,    5,    1,    1,    4,    3,    1,    1,    1,
    1,    3,    3,    1,    3,    3,    2,    4,    1,    4,
    4,    1,    1,    1,    1,    1,    1,    1,    1,    3,
    3,    3,    3,    1,    1,    2,    1,    2,    2,    3,
    3,    3,    7,    7,    7,    1,    1,    2,    3,    0,
    5,    1,    1,    3,    0,    5,    1,    1,    1,    5,
    5,    5,    5,    3,    4,    4,    4,    4,
};
short yydefred[] = {                                      0,
    3,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  202,  203,    0,    0,    0,    0,    2,    0,    4,
    5,    6,    7,    8,    9,   16,   10,   11,   12,   17,
   18,   21,    0,    0,    0,  230,    0,    0,    0,    0,
    0,  113,  138,    0,    0,    0,  125,  126,  189,  190,
  137,  191,    0,    0,    0,    0,    0,    0,   13,   14,
   15,   19,   20,  103,  102,    0,    0,  228,    0,    0,
  133,    0,  122,    0,    0,    0,   76,    0,   44,   43,
   73,    0,    0,    0,    0,    0,   75,    0,    0,    0,
    0,    0,    0,    0,  187,  185,  184,    0,    0,    0,
    0,    0,    0,    0,    0,  239,  237,  238,    0,    0,
    0,    0,    0,  135,    0,  127,  131,  128,    0,  136,
  134,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   27,    0,   22,   24,  227,   23,  226,  229,   72,
   46,   45,    0,  111,    0,    0,    0,  116,    0,  117,
    0,  205,  204,  206,  208,  207,  209,    0,    0,    0,
  144,  146,  145,  143,    0,    0,    0,  174,    0,    0,
  186,    0,    0,  192,  193,    0,    0,    0,  244,    0,
    0,  246,  245,  132,  130,    0,  104,  120,  121,  119,
  123,    0,    0,    0,    0,   56,    0,    0,    0,    0,
    0,   86,    0,  112,  107,    0,  110,  115,  114,    0,
    0,  140,  141,  142,  139,   51,    0,    0,   81,    0,
  175,    0,  183,    0,    0,  179,  200,  201,  198,    0,
    0,    0,    0,  221,  222,  220,    0,    0,    0,    0,
   53,   52,   85,   83,    0,    0,    0,   25,   84,   82,
  109,    0,    0,  118,  231,   48,   47,   80,   78,    0,
   79,   77,    0,  176,    0,    0,    0,  241,  242,  243,
  240,  129,    0,    0,    0,    0,    0,    0,   55,   54,
    0,  156,    0,    0,   61,    0,    0,    0,    0,   28,
   31,   34,    0,   29,   30,   32,   33,    0,  235,   91,
    0,   50,   49,  177,  181,  180,  182,  178,  211,  212,
  213,  210,    0,  215,  214,  217,    0,    0,    0,    0,
  157,    0,    0,    0,  161,    0,    0,   35,   36,    0,
   58,   57,   90,   88,    0,    0,   89,   87,  218,  219,
  216,  224,  225,  223,    0,  158,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   42,    0,   39,
   37,  233,   38,  232,  234,   60,   59,    0,  159,  163,
  162,  164,  160,    0,    0,    0,  165,    0,    0,  248,
  247,    0,    0,    0,    0,    0,  101,    0,    0,   66,
    0,    0,   96,    0,  166,    0,    0,    0,  170,   71,
   68,   67,  100,   98,    0,    0,    0,   40,   99,   97,
  236,   63,   62,   65,   95,   93,    0,   94,   92,    0,
  167,    0,    0,    0,   70,   69,    0,  147,    0,    0,
   64,  168,  172,  171,  173,  169,  148,    0,    0,    0,
  152,    0,  149,    0,    0,    0,  150,  154,  153,  155,
  151,
};
short yydgoto[] = {                                       3,
   18,    4,   19,   20,   21,   22,   23,   24,   25,   26,
   27,   28,   29,   30,   31,   32,  345,  137,  293,  294,
  295,  296,  297,  442,  363,   33,  218,  198,  298,  392,
  384,  264,  346,  254,  421,  443,   70,   34,  146,  147,
   85,   35,  150,  210,   73,   50,   74,  119,   71,   89,
   90,  158,   40,  429,  444,  283,  347,  378,  422,  169,
  265,   98,   52,   53,   54,  239,  317,  318,  127,   76,
  139,   36,   86,  365,  336,  109,   57,
};
short yysindex[] = {                                   -221,
    0,    0,    0,  668,  860, -215, -260, -227, -153, -111,
  247,    0,    0,   91, -208, -239,  683,    0, -237,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  699, -161, -200,    0,  784,  -77,  -59,   61,
   32,    0,    0, -182,  -83,   15,    0,    0,    0,    0,
    0,    0,  -43,   42, -156,  -37,  113,  182,    0,    0,
    0,    0,    0,    0,    0,  190, -238,    0,   72, -160,
    0, -113,    0, -143,  -85,  731,    0,  715,    0,    0,
    0, -234, -160, -222, -231,  -25,    0,  863,  374,  -27,
  858, -203,  746,  746,    0,    0,    0,  -24,   58,   58,
  -43,   15,   15,   15,   15,    0,    0,    0,  863,   55,
  384,  668,  668,    0,   70,    0,    0,    0, -176,    0,
    0,  -49,   92, -206, -208,  -15, -229,  814, -215,   56,
 -154,    0,  516,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   85,    0,   20,   71,   94,    0,  136,    0,
 -239,    0,    0,    0,    0,    0,    0,  190,  190,   74,
    0,    0,    0,    0,  828,  557,   60,    0,  170,   46,
    0,  -28,    9,    0,    0,  -43,  -43, -156,    0, -156,
 -156,    0,    0,    0,    0,   17,    0,    0,    0,    0,
    0,  190,  164,  -89,  -89,    0, -186,  522,  194,   95,
  -46,    0,  543,    0,    0,  167,    0,    0,    0, -232,
  731,    0,    0,    0,    0,    0, -144,  571,    0,  585,
    0,  746,    0,  746,  746,    0,    0,    0,    0,  102,
  107, -155,  111,    0,    0,    0,  863,  391,   90, -139,
    0,    0,    0,    0, -128,  731,  731,    0,    0,    0,
    0,  799,  468,    0,    0,    0,    0,    0,    0, -114,
    0,    0,  120,    0,  152,  155,  -54,    0,    0,    0,
    0,    0,  190,  190,  184, -173, -173, -173,    0,    0,
  153,    0,  159,  103,    0, -215, -111,  275, -239,    0,
    0,    0, -105,    0,    0,    0,    0,  484,    0,    0,
  500,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   92,    0,    0,    0, -188,  157,  173,  180,
    0,  731,  731,  731,    0,  230,  255,    0,    0,  754,
    0,    0,    0,    0,  -88,  201,    0,    0,    0,    0,
    0,    0,    0,    0,  163,    0,  225,  239,  -45,  769,
  769, -232, -232,  836, -215,  217,  221,    0,  593,    0,
    0,    0,    0,    0,    0,    0,    0, -239,    0,    0,
    0,    0,    0,  850,  607,  226,    0,  259,  151,    0,
    0,  283,  -84,  615,  261,  278,    0,  629,  754,    0,
  444,  638,    0,  652,    0,  769,  769,  769,    0,    0,
    0,    0,    0,    0,  -67,  754,  754,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  272,    0,    0,  276,
    0,  307,  317,   75,    0,    0,  300,    0,  333,  168,
    0,    0,    0,    0,    0,    0,    0,  754,  754,  754,
    0,  328,    0,  335,  345,   77,    0,    0,    0,    0,
    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  213,    0,    0,    0,    0,    0,    0,
    0,    0,  246,  358,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -62,
    0,    0,    0,  -60,    0,    0,    0,    2,    0,    0,
    0,    0,  -38,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  274,    0,    0,    0,    0,    0,    0,    0,    0,  400,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   -3,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  336,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  302,  330,    0,    0,    0,
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
    0,    0,    0,    0,    0,  428,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
short yygindex[] = {                                      0,
  342,    0,  -16, -223,  114,    0,    0,    0,    0,  -76,
   -7,   -5,   -6,  609,  -10,   25,  -48,    0,    6,   51,
    0,  340, -145, -315,    0,  207,  463,  508,  378,  258,
  284,  387,  -65,  189,  195, -317,  608,  -70,  438,    0,
    0,    0,    0,    0,  523,  -47,    0,  471,    0,   -2,
  610,  -73, -117,  251,  130,  412,  249,  309,  177,  573,
  373,    0,  498,  -23,  503,  478,    0,  348,    0, -141,
  464,   -1,    0,  285,    0,  -19,  395,
};
#define YYTABLESIZE 1155
short yytable[] = {                                     134,
   63,   74,   49,   59,   61,   60,   51,  108,  108,  211,
  138,  199,  364,  145,  362,  160,   82,  115,   79,  118,
   82,  141,  101,  252,  148,   41,  194,  136,  290,  290,
  116,   49,   49,  143,    1,  178,  111,  181,  383,   49,
   38,   42,  253,  383,   12,   13,   75,    2,  117,  189,
   80,  134,  163,  142,   47,   48,  134,  149,  195,   49,
   72,   82,   49,  114,  144,  135,  121,  340,  405,  241,
   39,  364,  405,  362,  290,  188,  190,  290,  209,  197,
  176,  177,  313,  164,  197,   84,   47,   48,  428,  428,
  427,  427,   49,   49,   64,   49,   49,   49,   49,  106,
  270,  242,   43,   67,   47,   48,    7,   65,  314,  315,
  185,  256,  107,  186,  201,   44,  277,  135,   45,   47,
   48,  134,  135,   59,   61,   60,  134,  279,   46,  122,
  108,  271,  108,  108,  134,  145,   47,   48,  118,   47,
   48,  302,  329,  257,   55,  138,  125,  330,  278,  245,
  331,   49,   49,   49,  245,  212,  213,  215,  230,  280,
  231,  232,  136,  273,  275,  123,  237,  366,  326,  134,
  134,  401,   99,  303,   56,  291,  291,  124,   88,  238,
  282,  282,  332,  126,  361,   49,   49,  135,  425,  234,
  236,   44,  135,  106,   45,  124,   91,  281,  281,  367,
  135,  307,  100,  402,   46,  308,  167,  167,  361,   44,
  372,   37,   45,  361,  373,   47,   48,  105,  110,  187,
  426,  291,   46,   78,  291,  106,  389,  124,  316,  316,
  316,  107,   66,   47,   48,  135,  135,  385,  361,   67,
  102,  103,  361,  361,   69,  134,  134,  134,  151,  105,
  299,  299,   26,  104,  105,   47,   48,   74,  227,  161,
  361,  361,  171,  192,  228,  339,   49,   49,   49,  341,
  309,  310,  312,  291,  291,  193,  292,  292,  217,  217,
   59,   61,   60,   44,   26,  116,  299,   95,  205,   74,
  104,  105,  361,  361,  361,  229,  299,  291,  291,  299,
   96,  224,   97,  335,  225,  226,  335,   47,   48,   47,
   48,  135,  135,  135,  291,  291,   93,  291,   94,  291,
  291,  291,  292,  104,  105,  292,   44,  120,  299,  214,
  435,  260,  450,  260,  436,  263,  451,  263,  263,   46,
   44,  179,   44,   45,  200,   45,   64,  221,  299,  299,
   47,   48,  299,   46,  360,   46,  184,  299,  323,   65,
  206,  324,  325,  248,   47,   48,   47,   48,  112,   66,
  113,  204,  299,  299,  292,  292,   67,  276,  360,   68,
  207,   69,  299,  360,   47,   48,  299,  299,  268,  299,
  299,  208,  299,  269,  299,  299,  299,  272,  292,  292,
  376,  376,   12,   13,  299,  299,  397,  304,  360,  398,
  399,  305,  360,  360,  306,  292,  292,  322,  292,  235,
  292,  292,  292,  439,  391,  391,  440,  441,  222,  223,
  360,  360,   44,   12,   13,   45,  299,  299,  299,  311,
  321,  414,  417,  342,  417,   46,  420,  420,  420,  246,
  369,  247,   44,  182,  183,   45,   47,   48,   44,  343,
   66,   45,  360,  360,  360,   46,  344,   67,  188,  188,
   68,   46,   69,  188,  368,  188,   47,   48,  188,  168,
  168,  188,   47,   48,  370,  350,  188,  351,  188,  188,
  188,  188,  188,  188,  188,  188,  188,  188,  371,  188,
  188,  194,  194,    6,  188,  386,  194,    7,  194,   68,
  352,  194,  353,  395,  194,   58,  406,  396,  407,  194,
   16,  194,  194,  194,  194,  194,  194,  194,  194,  197,
  197,  286,  194,  194,  197,    7,  197,  194,  400,  197,
  380,  381,  197,   58,  377,  377,  408,  197,  289,  197,
  197,  197,  197,  197,  197,  197,  197,  196,  196,  431,
  197,  197,  196,  432,  196,  197,  433,  196,  445,  446,
  196,  348,  349,  423,  424,  196,  434,  196,  196,  196,
  196,  196,  196,  196,  196,  195,  195,  437,  196,  196,
  195,  438,  195,  196,  448,  195,  266,  267,  195,  174,
  175,  172,  173,  195,  449,  195,  195,  195,  195,  195,
  195,  195,  195,  199,  199,  447,  195,  195,  199,   62,
  199,  195,  108,  199,  319,  320,  199,  328,  220,  159,
  301,  199,  394,  199,  199,  199,  199,  199,  199,  180,
  203,   83,  388,  251,  199,  199,  274,  191,   92,  199,
  152,  153,  154,  155,  156,  239,  233,  430,  284,  379,
  152,  153,  154,  155,  156,  157,  170,  152,  153,  154,
  155,  156,  240,  411,  255,  157,  239,  239,  239,  239,
  239,  327,  157,   41,   41,    0,    0,    0,   41,    0,
   41,  239,    0,   41,    0,    0,   41,    0,    0,  412,
  286,   41,    0,   41,    7,    0,    9,    0,    0,  288,
    0,    0,   58,    0,    0,   41,    0,  289,    0,    0,
    0,    0,    0,  285,  286,    0,    0,    0,    7,    8,
    9,  413,  287,  288,   12,   13,   14,   15,    0,  333,
  286,  289,    0,  300,    7,    8,    9,    0,  287,  288,
   12,   13,   14,   15,    0,  337,  286,  289,    0,  334,
    7,    8,    9,    0,  287,  288,   12,   13,   14,   15,
    0,  196,  129,  289,    0,  338,    7,  243,  129,  130,
    0,  131,    7,    0,   58,  130,    0,  131,  132,   16,
   58,  202,    0,    0,  132,   16,    0,  244,  249,  129,
    0,    0,    0,    7,    0,    0,  130,    0,  131,    0,
    0,   58,  216,    6,    0,  132,   16,    7,  250,    9,
    0,    0,   11,    0,    0,   58,  258,    6,    0,    0,
   16,    7,  219,    9,    0,    0,   11,    0,    0,   58,
  261,    6,    0,    0,   16,    7,  259,    9,  382,  355,
   11,    0,    0,   58,    0,    9,  356,    0,   16,    0,
  262,  357,  390,  286,    0,  358,  289,    7,  387,    9,
  403,  355,  288,    0,    0,   58,    0,    9,  356,    0,
  289,    0,  393,  357,  409,  355,    0,  358,  289,    0,
  404,    9,  356,  415,  286,    0,    0,  357,    7,    0,
    9,  358,  289,  288,  410,    0,   58,  418,  286,    0,
    0,  289,    7,  416,    9,    0,    0,  288,    0,    0,
   58,    0,    0,    5,    6,  289,    0,  419,    7,    8,
    9,    0,   10,   11,   12,   13,   14,   15,    0,    6,
    0,   16,   17,    7,    8,    9,    0,   10,   11,   12,
   13,   14,   15,    0,    0,    6,   16,    0,   77,    7,
    8,    9,    0,   10,   11,   12,   13,   14,   15,    0,
    0,    6,   16,    0,   81,    7,    8,    9,    0,   10,
   11,   12,   13,   14,   15,    0,  128,  129,   16,    0,
  140,    7,    0,    0,  130,    0,  131,    0,    0,   58,
    0,  165,    6,  132,   16,  133,    7,    0,    9,  354,
  355,   11,    0,    0,   58,    0,    9,  356,    0,   16,
  166,    0,  357,    0,  374,  286,  358,  289,  359,    7,
    0,    9,    0,    0,  288,    0,    0,   58,    0,   87,
    6,    0,  289,  375,    7,    8,    9,    0,   10,   11,
   12,   13,   14,   15,  285,  286,    0,   16,    0,    7,
    8,    9,    0,  287,  288,   12,   13,   14,   15,  196,
  129,    0,  289,    0,    7,    0,    0,  130,    0,  131,
    0,    0,   58,  216,    6,    0,  132,   16,    7,    0,
    9,  382,  355,   11,    0,    0,   58,    0,    9,  356,
    0,   16,    0,    0,  357,  390,  286,    0,  358,  289,
    7,    0,    9,    0,    0,  288,    6,    0,   58,    0,
    7,    8,    9,  289,   10,   11,   12,   13,   14,   15,
    0,    0,    0,   16,  152,  153,  154,  155,  156,  152,
  153,  154,  155,  156,  162,    0,    0,    0,    0,  157,
    0,    0,    0,    0,  157,
};
short yycheck[] = {                                      76,
   11,    0,    9,   11,   11,   11,    9,   55,   56,  151,
   76,  129,  330,   84,  330,   89,   33,  256,  256,   67,
   37,  256,   46,  256,  256,  286,  256,   76,  252,  253,
  269,   38,   39,  256,  256,  109,   56,  111,  354,   46,
  256,  269,  275,  359,  267,  268,  286,  269,  287,  256,
  288,  128,  256,  288,  293,  294,  133,  289,  288,   66,
  269,   78,   69,   66,  287,   76,   69,  256,  384,  256,
  286,  389,  388,  389,  298,  123,  124,  301,  149,  128,
  104,  105,  256,  287,  133,  286,  293,  294,  406,  407,
  406,  407,   99,  100,  256,  102,  103,  104,  105,  256,
  256,  288,  256,  286,  293,  294,  261,  269,  282,  283,
  287,  256,  269,  290,  269,  269,  256,  128,  272,  293,
  294,  198,  133,  131,  131,  131,  203,  256,  282,  290,
  178,  287,  180,  181,  211,  206,  293,  294,  186,  293,
  294,  256,  288,  288,  256,  211,  290,  289,  288,  198,
  256,  158,  159,  160,  203,  158,  159,  160,  178,  288,
  180,  181,  211,  237,  238,  279,  256,  256,  286,  246,
  247,  256,  256,  288,  286,  252,  253,  291,  256,  269,
  246,  247,  288,  269,  330,  192,  193,  198,  256,  192,
  193,  269,  203,  256,  272,  256,  256,  246,  247,  288,
  211,  256,  286,  288,  282,  260,   93,   94,  354,  269,
  256,    5,  272,  359,  260,  293,  294,  256,  256,  269,
  288,  298,  282,   17,  301,  288,  368,  288,  276,  277,
  278,  269,  279,  293,  294,  246,  247,  355,  384,  286,
  284,  285,  388,  389,  291,  322,  323,  324,  274,  288,
  252,  253,  256,  282,  283,  293,  294,  256,  287,  287,
  406,  407,  287,  279,  256,  313,  273,  274,  275,  317,
  273,  274,  275,  350,  351,  291,  252,  253,  165,  166,
  288,  288,  288,  269,  288,  269,  288,  256,  269,  288,
  282,  283,  438,  439,  440,  287,  298,  374,  375,  301,
  269,  256,  271,  298,  259,  260,  301,  293,  294,  293,
  294,  322,  323,  324,  391,  392,  256,  394,  258,  396,
  397,  398,  298,  282,  283,  301,  269,  256,  330,  256,
  256,  218,  256,  220,  260,  222,  260,  224,  225,  282,
  269,  287,  269,  272,  289,  272,  256,  288,  350,  351,
  293,  294,  354,  282,  330,  282,  287,  359,  256,  269,
  290,  259,  260,  269,  293,  294,  293,  294,  256,  279,
  258,  287,  374,  375,  350,  351,  286,  288,  354,  289,
  287,  291,  384,  359,  293,  294,  388,  389,  287,  391,
  392,  256,  394,  287,  396,  397,  398,  287,  374,  375,
  350,  351,  267,  268,  406,  407,  256,  288,  384,  259,
  260,  260,  388,  389,  260,  391,  392,  259,  394,  256,
  396,  397,  398,  256,  374,  375,  259,  260,  259,  260,
  406,  407,  269,  267,  268,  272,  438,  439,  440,  256,
  288,  391,  392,  287,  394,  282,  396,  397,  398,  256,
  288,  258,  269,  112,  113,  272,  293,  294,  269,  287,
  279,  272,  438,  439,  440,  282,  287,  286,  256,  257,
  289,  282,  291,  261,  274,  263,  293,  294,  266,   93,
   94,  269,  293,  294,  260,  256,  274,  258,  276,  277,
  278,  279,  280,  281,  282,  283,  284,  285,  260,  287,
  288,  256,  257,  257,  292,  289,  261,  261,  263,  289,
  256,  266,  258,  288,  269,  269,  256,  259,  258,  274,
  274,  276,  277,  278,  279,  280,  281,  282,  283,  256,
  257,  257,  287,  288,  261,  261,  263,  292,  256,  266,
  352,  353,  269,  269,  350,  351,  269,  274,  274,  276,
  277,  278,  279,  280,  281,  282,  283,  256,  257,  288,
  287,  288,  261,  288,  263,  292,  260,  266,  439,  440,
  269,  323,  324,  397,  398,  274,  260,  276,  277,  278,
  279,  280,  281,  282,  283,  256,  257,  288,  287,  288,
  261,  259,  263,  292,  260,  266,  224,  225,  269,  102,
  103,   99,  100,  274,  260,  276,  277,  278,  279,  280,
  281,  282,  283,  256,  257,  288,  287,  288,  261,   11,
  263,  292,  287,  266,  277,  278,  269,  288,  166,  256,
  253,  274,  375,  276,  277,  278,  279,  280,  281,  256,
  133,   34,  359,  206,  287,  288,  256,  125,   39,  292,
  277,  278,  279,  280,  281,  256,  186,  407,  247,  351,
  277,  278,  279,  280,  281,  292,   94,  277,  278,  279,
  280,  281,  195,  389,  211,  292,  277,  278,  279,  280,
  281,  287,  292,  256,  257,   -1,   -1,   -1,  261,   -1,
  263,  292,   -1,  266,   -1,   -1,  269,   -1,   -1,  256,
  257,  274,   -1,  276,  261,   -1,  263,   -1,   -1,  266,
   -1,   -1,  269,   -1,   -1,  288,   -1,  274,   -1,   -1,
   -1,   -1,   -1,  256,  257,   -1,   -1,   -1,  261,  262,
  263,  288,  265,  266,  267,  268,  269,  270,   -1,  256,
  257,  274,   -1,  276,  261,  262,  263,   -1,  265,  266,
  267,  268,  269,  270,   -1,  256,  257,  274,   -1,  276,
  261,  262,  263,   -1,  265,  266,  267,  268,  269,  270,
   -1,  256,  257,  274,   -1,  276,  261,  256,  257,  264,
   -1,  266,  261,   -1,  269,  264,   -1,  266,  273,  274,
  269,  276,   -1,   -1,  273,  274,   -1,  276,  256,  257,
   -1,   -1,   -1,  261,   -1,   -1,  264,   -1,  266,   -1,
   -1,  269,  256,  257,   -1,  273,  274,  261,  276,  263,
   -1,   -1,  266,   -1,   -1,  269,  256,  257,   -1,   -1,
  274,  261,  276,  263,   -1,   -1,  266,   -1,   -1,  269,
  256,  257,   -1,   -1,  274,  261,  276,  263,  256,  257,
  266,   -1,   -1,  269,   -1,  263,  264,   -1,  274,   -1,
  276,  269,  256,  257,   -1,  273,  274,  261,  276,  263,
  256,  257,  266,   -1,   -1,  269,   -1,  263,  264,   -1,
  274,   -1,  276,  269,  256,  257,   -1,  273,  274,   -1,
  276,  263,  264,  256,  257,   -1,   -1,  269,  261,   -1,
  263,  273,  274,  266,  276,   -1,  269,  256,  257,   -1,
   -1,  274,  261,  276,  263,   -1,   -1,  266,   -1,   -1,
  269,   -1,   -1,  256,  257,  274,   -1,  276,  261,  262,
  263,   -1,  265,  266,  267,  268,  269,  270,   -1,  257,
   -1,  274,  275,  261,  262,  263,   -1,  265,  266,  267,
  268,  269,  270,   -1,   -1,  257,  274,   -1,  276,  261,
  262,  263,   -1,  265,  266,  267,  268,  269,  270,   -1,
   -1,  257,  274,   -1,  276,  261,  262,  263,   -1,  265,
  266,  267,  268,  269,  270,   -1,  256,  257,  274,   -1,
  276,  261,   -1,   -1,  264,   -1,  266,   -1,   -1,  269,
   -1,  256,  257,  273,  274,  275,  261,   -1,  263,  256,
  257,  266,   -1,   -1,  269,   -1,  263,  264,   -1,  274,
  275,   -1,  269,   -1,  256,  257,  273,  274,  275,  261,
   -1,  263,   -1,   -1,  266,   -1,   -1,  269,   -1,  256,
  257,   -1,  274,  275,  261,  262,  263,   -1,  265,  266,
  267,  268,  269,  270,  256,  257,   -1,  274,   -1,  261,
  262,  263,   -1,  265,  266,  267,  268,  269,  270,  256,
  257,   -1,  274,   -1,  261,   -1,   -1,  264,   -1,  266,
   -1,   -1,  269,  256,  257,   -1,  273,  274,  261,   -1,
  263,  256,  257,  266,   -1,   -1,  269,   -1,  263,  264,
   -1,  274,   -1,   -1,  269,  256,  257,   -1,  273,  274,
  261,   -1,  263,   -1,   -1,  266,  257,   -1,  269,   -1,
  261,  262,  263,  274,  265,  266,  267,  268,  269,  270,
   -1,   -1,   -1,  274,  277,  278,  279,  280,  281,  277,
  278,  279,  280,  281,  287,   -1,   -1,   -1,   -1,  292,
   -1,   -1,   -1,   -1,  292,
};
#define YYFINAL 3
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 294
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"IF","THEN","ELSE","END_IF","OUT",
"FUN","RETURN","BREAK","WHEN","DEFER","UI8","F64","ID","CONST","CADENA","TOF64",
"CONTINUE","FOR","LLAVE_AB","LLAVE_CE","MENOR","MAYOR","IGUAL","MENOR_IGUAL",
"MAYOR_IGUAL","MENOS","SUM","MULT","DIV","PARE_AB","PARE_CIE","PUNTO_COMA",
"DOS_PUNTOS","COMA","ASIGNACION","DISTINTO","DOBLE","ENTERO",
};
char *yyrule[] = {
"$accept : program",
"$$1 :",
"program : ID $$1 bloq_general",
"program : error",
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
"bloq_general : bloq_sentencias LLAVE_CE",
"bloq_general : LLAVE_AB bloq_sentencias",
"bloq_general : error bloq_sentencias error",
"bloq_general : LLAVE_AB LLAVE_CE",
"bloq_ejecutable : LLAVE_AB bloq_sentencias_e LLAVE_CE",
"bloq_ejecutable : error bloq_sentencias_e LLAVE_CE",
"bloq_ejecutable : LLAVE_AB bloq_sentencias_e error",
"bloq_ejecutable : error bloq_sentencias_e error",
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 1123 ".\gramatica_final.y"
 



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
          entry.getValue().getCuerpoFuncion().recorrerArbol("");
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
#line 1187 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 25 ".\gramatica_final.y"
{setPrograma(((Token)yyvsp[0].obj)); }
break;
case 2:
#line 26 ".\gramatica_final.y"
{
		funciones.get("main").setCuerpoFuncion((ArbolSintactico) yyvsp[0].obj);
		agregarResultado("Programa terminado . Linea " + lineaUltimoToken);
		}
break;
case 3:
#line 30 ".\gramatica_final.y"
{
		yyerror("Estructura de program no reconocida.");
		}
break;
case 4:
#line 38 ".\gramatica_final.y"
{yyval = yyvsp[0];}
break;
case 5:
#line 39 ".\gramatica_final.y"
{yyval = yyvsp[0];}
break;
case 13:
#line 52 ".\gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia asignación diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) yyvsp[0].obj);
		}
break;
case 14:
#line 56 ".\gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia out diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) yyvsp[0].obj);
		}
break;
case 15:
#line 60 ".\gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia invocación diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) yyvsp[0].obj);
		}
break;
case 19:
#line 69 ".\gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia if diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) yyvsp[0].obj);
		}
break;
case 20:
#line 73 ".\gramatica_final.y"
{
			yyval = new ParserVal (new NodoHoja("Sentencia for diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) yyvsp[0].obj);
		}
break;
case 21:
#line 77 ".\gramatica_final.y"
{
			yyerror("Sentencia de retorno fuera de función. Linea: " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
break;
case 25:
#line 87 ".\gramatica_final.y"
{
				agregarResultado("Sentencia Break con TAG dentro de for. Linea " + lineaUltimoToken);
				Token id =(Token) yyvsp[0].obj ;
				if(!existeIdEnAmbito(id,"Tag"))
					AgregarErrorSemantico("Tag no declarado. Linea " + lineaUltimoToken);
				else 
					if(!etiquetasAnidadas.contains(id.getLexema()))
						AgregarErrorSemantico("Tag declarado pero no se encuentra anidado en las sentencias de control. Linea " + lineaUltimoToken);

				
				yyval = new ParserVal(new NodoControl("Break con TAG", new NodoHoja(id.getLexema(), "", id.getLexema())));}
break;
case 26:
#line 99 ".\gramatica_final.y"
{
				agregarResultado("Sentencia Break. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Break",""));
				}
break;
case 27:
#line 103 ".\gramatica_final.y"
{
				agregarResultado("Sentencia CONTINUE dentro de for. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Continue",""));
				}
break;
case 35:
#line 120 ".\gramatica_final.y"
{
							insertarSentenciaDiferida((ArbolSintactico) yyvsp[0].obj);
			   }
break;
case 36:
#line 123 ".\gramatica_final.y"
{
							insertarSentenciaDiferida((ArbolSintactico) yyvsp[0].obj);
			   }
break;
case 40:
#line 132 ".\gramatica_final.y"
{
					agregarResultado("Sentencia Break con . Linea " + lineaUltimoToken);
					Token id =(Token) yyvsp[0].obj ;
					if(!existeIdEnAmbito(id,"Tag"))
						AgregarErrorSemantico("Tag no declarado. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Sentencia Break con TAG",""));}
break;
case 41:
#line 138 ".\gramatica_final.y"
{
					agregarResultado("Sentencia Break. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Break",""));}
break;
case 42:
#line 141 ".\gramatica_final.y"
{
					agregarResultado("Sentencia CONTINUE dentro de for. Linea " + lineaUltimoToken);}
break;
case 43:
#line 149 ".\gramatica_final.y"
{
				if(yyvsp[-1].obj != null)
					yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,null ));
			}
break;
case 44:
#line 153 ".\gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 45:
#line 157 ".\gramatica_final.y"
{
				if(yyvsp[-1].obj != null)
					yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,(ArbolSintactico) yyvsp[-2].obj ));
			}
break;
case 46:
#line 161 ".\gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 47:
#line 169 ".\gramatica_final.y"
{
				yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,null ));}
break;
case 48:
#line 171 ".\gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 49:
#line 175 ".\gramatica_final.y"
{
				yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,(ArbolSintactico) yyvsp[-2].obj ));}
break;
case 50:
#line 177 ".\gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 51:
#line 181 ".\gramatica_final.y"
{
				yyerror("Sentencia ejecutable esperada : Linea  " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 52:
#line 188 ".\gramatica_final.y"
{
				yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,null ));
}
break;
case 53:
#line 191 ".\gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 54:
#line 195 ".\gramatica_final.y"
{
			yyval = new ParserVal(new NodoComun("Sentencia declarativa",(ArbolSintactico) yyvsp[-1].obj ,(ArbolSintactico) yyvsp[-2].obj ));	
			}
break;
case 55:
#line 198 ".\gramatica_final.y"
{
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 56:
#line 202 ".\gramatica_final.y"
{
			yyerror("Sentencia ejecutable o de iteración esperada . Linea " + lineaUltimoToken);
			yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 57:
#line 208 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,null ));}
break;
case 58:
#line 209 ".\gramatica_final.y"
{
					yyerror("';' esperado en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
				   }
break;
case 59:
#line 214 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,(ArbolSintactico) yyvsp[-2].obj ));}
break;
case 60:
#line 215 ".\gramatica_final.y"
{
					yyerror("';' esperado en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
				   }
break;
case 61:
#line 219 ".\gramatica_final.y"
{
					yyerror("Sentencia ejecutable o declarativa de función esperada . Linea " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 62:
#line 225 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,null ));}
break;
case 63:
#line 227 ".\gramatica_final.y"
{
					yyerror("';' esperado en linea: " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 64:
#line 233 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,(ArbolSintactico) yyvsp[-2].obj ));}
break;
case 65:
#line 235 ".\gramatica_final.y"
{
					yyerror("';' esperado en linea: " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 66:
#line 238 ".\gramatica_final.y"
{
					yyerror("Sentencia ejecutable o de función esperada . Linea " + lineaUltimoToken);
					yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 67:
#line 244 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,null ));}
break;
case 68:
#line 245 ".\gramatica_final.y"
{
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
						 }
break;
case 69:
#line 249 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) yyvsp[-1].obj ,(ArbolSintactico) yyvsp[-2].obj ));}
break;
case 70:
#line 250 ".\gramatica_final.y"
{
							yyerror("';' esperado en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 71:
#line 254 ".\gramatica_final.y"
{
							yyerror("Sentencia ejecutable, de iteración o de función esperada . Linea " + lineaUltimoToken);
							yyval =new ParserVal(new NodoHoja("Error en sintaxis",""));}
break;
case 72:
#line 260 ".\gramatica_final.y"
{
				yyval = yyvsp[-1];
				}
break;
case 73:
#line 263 ".\gramatica_final.y"
{
				yyerror("'{' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 74:
#line 266 ".\gramatica_final.y"
{
				yyerror("'}' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 75:
#line 269 ".\gramatica_final.y"
{
				yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 76:
#line 272 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 77:
#line 275 ".\gramatica_final.y"
{yyval= yyvsp[-1];}
break;
case 78:
#line 276 ".\gramatica_final.y"
{
				yyerror("'{' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 79:
#line 279 ".\gramatica_final.y"
{
				yyerror("'}' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 80:
#line 282 ".\gramatica_final.y"
{
				yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 81:
#line 285 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 82:
#line 291 ".\gramatica_final.y"
{yyval=yyvsp[-1];}
break;
case 83:
#line 292 ".\gramatica_final.y"
{
					yyerror("'{' esperado en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 84:
#line 295 ".\gramatica_final.y"
{
					yyerror("'}' esperado en linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 85:
#line 298 ".\gramatica_final.y"
{
					yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 86:
#line 301 ".\gramatica_final.y"
{
					yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 87:
#line 305 ".\gramatica_final.y"
{ yyval = yyvsp[-1];}
break;
case 88:
#line 306 ".\gramatica_final.y"
{yyerror("'{' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 89:
#line 308 ".\gramatica_final.y"
{yyerror("'}' esperado en linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 90:
#line 310 ".\gramatica_final.y"
{yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 91:
#line 312 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 92:
#line 315 ".\gramatica_final.y"
{yyval = yyvsp[-1];}
break;
case 93:
#line 316 ".\gramatica_final.y"
{
							yyerror("'{' esperado en linea : " + lineaUltimoToken);}
break;
case 94:
#line 318 ".\gramatica_final.y"
{
							yyerror("'}' esperado en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 95:
#line 321 ".\gramatica_final.y"
{
							yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 96:
#line 324 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 97:
#line 328 ".\gramatica_final.y"
{
	yyval = yyvsp[-1];
}
break;
case 98:
#line 331 ".\gramatica_final.y"
{
							yyerror("'{' esperado en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 99:
#line 334 ".\gramatica_final.y"
{
							yyerror("'}' esperado en linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 100:
#line 337 ".\gramatica_final.y"
{
							yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
							yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 101:
#line 340 ".\gramatica_final.y"
{
							yyval = new ParserVal(new NodoHoja("Sentencia" , ""));}
break;
case 102:
#line 348 ".\gramatica_final.y"
{declaracionVarActual.add((Token)yyvsp[0].obj);}
break;
case 103:
#line 349 ".\gramatica_final.y"
{
		yyerror("Error en declaración de variables ',' esperada. Linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 104:
#line 352 ".\gramatica_final.y"
{ declaracionVarActual.add((Token)yyvsp[0].obj);}
break;
case 105:
#line 355 ".\gramatica_final.y"
{verificarDeclaracionVariables();
						String tipo = ((Token)yyvsp[-1].obj).getLexema();
                        declararTipos(tipo);
						yyval = new ParserVal(null);
						declaracionVarActual.clear();
						}
break;
case 106:
#line 361 ".\gramatica_final.y"
{
		yyerror("Error en declaración de variables Tipo desconocido. Linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
	  }
break;
case 107:
#line 368 ".\gramatica_final.y"
{
	/*Alta de parametro, le paso referencia, uso "parametro" y tipo $1.getLexema()*/
	Token parametro = (Token)yyvsp[0].obj;
	altaDeParametro(parametro,((Token)yyvsp[-1].obj).getLexema());
	yyval = new ParserVal(new Parametro(parametro.getLexema(),((Token)yyvsp[-1].obj).getLexema(), parametro.getRef()));

}
break;
case 108:
#line 377 ".\gramatica_final.y"
{
				Parametro[] params = new Parametro[1]; /* Se devuelve arreglo de 1 es mas facil preguntar por longitud*/
				params[0] =(Parametro) yyvsp[0].obj;
				yyval = new ParserVal(params);
				}
break;
case 109:
#line 382 ".\gramatica_final.y"
{
				Parametro[] params = new Parametro[2];
				params[0] = (Parametro)yyvsp[-2].obj;
				params[1] =(Parametro) yyvsp[0].obj;
				yyval = new ParserVal(params);
				}
break;
case 110:
#line 390 ".\gramatica_final.y"
{
				yyval = yyvsp[-1];}
break;
case 111:
#line 392 ".\gramatica_final.y"
{
				yyval = new ParserVal(new Parametro[0]);}
break;
case 112:
#line 394 ".\gramatica_final.y"
{
				 yyerror("Error en parametros de función. Linea " + lineaUltimoToken);
			    yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 113:
#line 401 ".\gramatica_final.y"
{
	Token ID = (Token) yyvsp[0].obj;
	altaDeFuncion(ID);
	if(erroresSemanticos.isEmpty()){
	funciones.put(pila.getAmbitoActual(),new ElemFuncion());
	funciones.get(pila.getAmbitoActual()).setRetorno(false);
	}
	yyval = yyvsp[0];
	
}
break;
case 114:
#line 412 ".\gramatica_final.y"
{
			String tipo =((Token) yyvsp[0].obj).getLexema();
			yyval = new ParserVal(tipo);
}
break;
case 115:
#line 416 ".\gramatica_final.y"
{
			yyerror("Tipo esperado en declaración de funcion. Linea " + lineaUltimoToken);}
break;
case 116:
#line 418 ".\gramatica_final.y"
{
			yyerror("Tipo esperado en declaración de funcion. Linea " + lineaUltimoToken);}
break;
case 117:
#line 422 ".\gramatica_final.y"
{
			try{Token ID = (Token) yyvsp[-2].obj;
			Parametro[] a = (Parametro[]) yyvsp[-1].obj;
			if(a.length>1){
				ts.setParametro1(ID.getRef(), a[0]);
				ts.setParametro2(ID.getRef(), a[1]);
			}
			else if (a.length == 1) 
				ts.setParametro1(ID.getRef(), a[0]);

			if (ts.getUso(((Token) yyvsp[-2].obj).getRef()).equals("Funcion")) /* Se asigna tipo de función*/
				ts.setTipo(((Token) yyvsp[-2].obj).getRef(), yyvsp[0].sval);
			}catch(Exception e){}
			
			}
break;
case 118:
#line 436 ".\gramatica_final.y"
{
					agregarResultado("Declaracion FUNCIÓN. Linea " + lineaUltimoToken);
					Token ID = (Token) yyvsp[-4].obj;
					
					yyval = new ParserVal(null); /* Lo que se muestra en caso de estar en el arbol principal */
					if(erroresSemanticos.isEmpty()){
					if(!funciones.get(pila.getAmbitoActual()).isRetorno())
						AgregarErrorSemantico("Función sin sentencia de retorno asociada. Linea " + lineaUltimoToken);
					funciones.get(pila.getAmbitoActual()).setCuerpoFuncion((ArbolSintactico) yyvsp[0].obj);
					funciones.get(pila.getAmbitoActual()).setId(ID.getRef());
					pila.eliminarUltimoAmbito();}}
break;
case 119:
#line 449 ".\gramatica_final.y"
{
							agregarResultado("Declaracion asignacion constante en linea " + lineaUltimoToken);				
                            Token id = ((Token)yyvsp[-2].obj);
				
							altaDeConstante(id);
                			if(ts.existeEnTS(id.getRef()) && ts.getUso(id.getRef()).equals("Constante")){
                				ts.setTipo(id.getRef(),((ArbolSintactico)yyvsp[0].obj).getTipo());
								ts.setValor(id.getRef(),((ArbolSintactico)yyvsp[0].obj).getLexema() );
                			}
							yyval = new ParserVal(new NodoHoja("Declaración de constantes", ts.getTipo(id.getRef()), id.getRef() ));}
break;
case 120:
#line 459 ".\gramatica_final.y"
{
					yyerror("':' esperado en asignacion "  + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 121:
#line 462 ".\gramatica_final.y"
{
					yyerror("error en valor de asignación, valor constante esperado. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 122:
#line 469 ".\gramatica_final.y"
{yyval = yyvsp[0];}
break;
case 123:
#line 470 ".\gramatica_final.y"
{yyval = yyvsp[0];}
break;
case 124:
#line 473 ".\gramatica_final.y"
{
		agregarResultado("Declaracion CONST. Linea " + lineaUltimoToken);
		yyval = new ParserVal (null);
		}
break;
case 125:
#line 480 ".\gramatica_final.y"
{
				String valor  = ((Token)yyvsp[0].obj).getLexema();
				yyval = new ParserVal(new NodoHoja(valor, ts.getTipo(valor), valor));
				ts.setValor(valor, valor);
				yyval.sval = valor;}
break;
case 126:
#line 485 ".\gramatica_final.y"
{
				String valor  = ((Token)yyvsp[0].obj).getLexema();
				yyval = new ParserVal(new NodoHoja(valor, ts.getTipo(valor), valor));
				ts.setValor(valor, valor);
				yyval.sval = valor;
				}
break;
case 127:
#line 494 ".\gramatica_final.y"
{
				Token ID = (Token) yyvsp[0].obj; 
				if (existeIdEnAmbito(ID,"Variable") || existeIdEnAmbito(ID,"Constante")) {  
                    listaParametrosReales.add(new Parametro(ID.getLexema(), ts.getTipo(ID.getRef()), ID.getRef()));
                }
				}
break;
case 128:
#line 500 ".\gramatica_final.y"
{
		   		listaParametrosReales.add(new Parametro(((ArbolSintactico)yyvsp[0].obj).getLexema(),((ArbolSintactico)yyvsp[0].obj).getTipo(), ((ArbolSintactico)yyvsp[0].obj).getLexema()));
		   }
break;
case 131:
#line 507 ".\gramatica_final.y"
{yyval = null;}
break;
case 132:
#line 508 ".\gramatica_final.y"
{yyerror("Error en parametros invocación a funcion. Linea " + lineaUltimoToken);
					yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 133:
#line 512 ".\gramatica_final.y"
{
	Token funcionInvocada = (Token)yyvsp[-1].obj;
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
case 134:
#line 563 ".\gramatica_final.y"
{agregarResultado("Asignacion en linea " + lineaUltimoToken);
							  Token id = (Token)yyvsp[-2].obj;
                              if (!existeIdEnAmbito(id, "Variable")) 
            					AgregarErrorSemantico("Variable no definida . Linea " + lineaUltimoToken);

							  yyval = new ParserVal(new NodoComun("=:", new NodoHoja(id.getLexema(), ts.getTipo(id.getRef()), id.getRef()),(ArbolSintactico)yyvsp[0].obj));
							  
							  if(((ArbolSintactico) yyval.obj).getTipo().equals("error")) 
							  	AgregarErrorSemantico("Tipos no compatibles en asignación . Linea " + lineaUltimoToken);
							  }
break;
case 135:
#line 573 ".\gramatica_final.y"
{
			yyerror("':' esperado en asignacion "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 136:
#line 576 ".\gramatica_final.y"
{
			yyerror("error en valor de asignación. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 137:
#line 583 ".\gramatica_final.y"
{
				agregarResultado("Sentencia RETURN. Linea " + lineaUltimoToken);
				if(!revisarTipoRetorno(((ArbolSintactico)yyvsp[0].obj).getTipo())){
					AgregarErrorSemantico("Tipo erroneo en sentencia Return. Linea: " + lineaUltimoToken);
				}
				funciones.get(pila.getAmbitoActual()).setRetorno(true);
				ArbolSintactico sentenciasDiferidas = funciones.get(pila.getAmbitoActual()).getSentenciasDiferidas();
				if (sentenciasDiferidas!=null)
					yyval = new ParserVal(new NodoComun(("Sentencias Diferidas"), new NodoControl("Retorno",(ArbolSintactico) yyvsp[0].obj) , sentenciasDiferidas));
				else
					yyval = new ParserVal(new NodoControl("Retorno",(ArbolSintactico) yyvsp[0].obj));
				}
break;
case 138:
#line 595 ".\gramatica_final.y"
{
			yyerror("Error en valor de retorno. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
break;
case 139:
#line 604 ".\gramatica_final.y"
{
		yyval = new ParserVal(new NodoComun( ((Token)yyvsp[-1].obj).getLexema(), (ArbolSintactico)yyvsp[-2].obj ,(ArbolSintactico)yyvsp[0].obj));  
		if(((ArbolSintactico) yyval.obj).getTipo().equals("error")) 
							  	AgregarErrorSemantico("Tipos no compatibles en comparación . Linea " + lineaUltimoToken);
							
		}
break;
case 140:
#line 610 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 141:
#line 611 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 142:
#line 612 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 143:
#line 615 ".\gramatica_final.y"
{
		yyval = new ParserVal(new NodoControl("CondicionIF",(ArbolSintactico) yyvsp[-1].obj));
	}
break;
case 144:
#line 618 ".\gramatica_final.y"
{
		yyerror("Esperado '(' en linea: " + lineaUltimoToken);
	}
break;
case 145:
#line 621 ".\gramatica_final.y"
{
		yyerror("Esperado ')' en linea: " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 146:
#line 624 ".\gramatica_final.y"
{
		yyerror("Error en condición, linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 151:
#line 642 ".\gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) yyvsp[-3].obj, (ArbolSintactico) yyvsp[-1].obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-5].obj,cuerpo));
			
			}
break;
case 152:
#line 649 ".\gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) yyvsp[-1].obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-3].obj,cuerpo));
	}
break;
case 153:
#line 655 ".\gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 154:
#line 658 ".\gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 155:
#line 661 ".\gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 156:
#line 667 ".\gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) yyvsp[0].obj));}
break;
case 157:
#line 669 ".\gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) yyvsp[-1].obj));
		   }
break;
case 158:
#line 674 ".\gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) yyvsp[0].obj));}
break;
case 159:
#line 676 ".\gramatica_final.y"
{
		   	yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) yyvsp[-1].obj));}
break;
case 160:
#line 681 ".\gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) yyvsp[-3].obj, (ArbolSintactico) yyvsp[-1].obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-5].obj,cuerpo));
			}
break;
case 161:
#line 687 ".\gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) yyvsp[-1].obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-3].obj,cuerpo));
	
		}
break;
case 162:
#line 695 ".\gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 163:
#line 698 ".\gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 164:
#line 701 ".\gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 169:
#line 718 ".\gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) yyvsp[-3].obj, (ArbolSintactico) yyvsp[-1].obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-5].obj,cuerpo));
			
			}
break;
case 170:
#line 725 ".\gramatica_final.y"
{
		agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
		NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) yyvsp[-1].obj);
		yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-3].obj,cuerpo));
	}
break;
case 171:
#line 731 ".\gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 172:
#line 734 ".\gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 173:
#line 737 ".\gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 174:
#line 743 ".\gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) yyvsp[0].obj));}
break;
case 175:
#line 745 ".\gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Then",(ArbolSintactico) yyvsp[-1].obj));
		   }
break;
case 176:
#line 750 ".\gramatica_final.y"
{
			yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) yyvsp[0].obj));}
break;
case 177:
#line 752 ".\gramatica_final.y"
{
		   	yyval= new ParserVal(new NodoControl("Else",(ArbolSintactico) yyvsp[-1].obj));}
break;
case 178:
#line 757 ".\gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) yyvsp[-3].obj, (ArbolSintactico) yyvsp[-1].obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-5].obj,cuerpo));
			}
break;
case 179:
#line 763 ".\gramatica_final.y"
{
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) yyvsp[-1].obj);
			yyval = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) yyvsp[-3].obj,cuerpo));
	
		}
break;
case 180:
#line 771 ".\gramatica_final.y"
{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 181:
#line 774 ".\gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 182:
#line 777 ".\gramatica_final.y"
{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 183:
#line 780 ".\gramatica_final.y"
{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 184:
#line 788 ".\gramatica_final.y"
{
				String lexema = ((Token)yyvsp[0].obj).getLexema();
				yyval = new ParserVal(new NodoHoja(lexema, "str"));
}
break;
case 185:
#line 792 ".\gramatica_final.y"
{      
				Token id = (Token)yyvsp[0].obj;
                 if (!(existeIdEnAmbito(id, "Variable") || existeIdEnAmbito(id, "Constante") || existeIdEnAmbito(id, "Parametro") )) {
                  AgregarErrorSemantico("Identificador inexistente para sentencia de out. Linea " + lineaUltimoToken); 
                 }
				 yyval = new ParserVal(new NodoHoja(id.getRef(), ts.getTipo(id.getRef()), id.getRef()));
				}
break;
case 186:
#line 802 ".\gramatica_final.y"
{
	agregarResultado("Sentencia OUT. Linea " + lineaUltimoToken);
	yyval = new ParserVal(new NodoControl("Sentencia out",(ArbolSintactico) yyvsp[-1].obj ));	
	}
break;
case 187:
#line 807 ".\gramatica_final.y"
{
		yyerror("Error en contenido del OUT");
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 188:
#line 814 ".\gramatica_final.y"
{
		Token id = (Token) yyvsp[0].obj;
		if(!existeIdEnAmbito(id,"Variable") &&  !existeIdEnAmbito(id,"Parametro") && !existeIdEnAmbito(id,"Constante")){
			AgregarErrorSemantico("Variable no declarada en la expresión. Linea " + lineaUltimoToken);	
		}
		yyval = new ParserVal(new NodoHoja(id.getLexema(),ts.getTipo(id.getRef()), id.getRef()));}
break;
case 190:
#line 821 ".\gramatica_final.y"
{ yyval = yyvsp[0];}
break;
case 191:
#line 824 ".\gramatica_final.y"
{yyval = yyvsp[0];}
break;
case 192:
#line 825 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("*", (ArbolSintactico)yyvsp[-2].obj, (ArbolSintactico)yyvsp[0].obj));}
break;
case 193:
#line 826 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("/", (ArbolSintactico)yyvsp[-2].obj, (ArbolSintactico)yyvsp[0].obj));}
break;
case 194:
#line 829 ".\gramatica_final.y"
{yyval = yyvsp[0];}
break;
case 195:
#line 830 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("+", (ArbolSintactico)yyvsp[-2].obj, (ArbolSintactico)yyvsp[0].obj));}
break;
case 196:
#line 831 ".\gramatica_final.y"
{yyval = new ParserVal(new NodoComun("-", (ArbolSintactico)yyvsp[-2].obj, (ArbolSintactico)yyvsp[0].obj));}
break;
case 197:
#line 832 ".\gramatica_final.y"
{yyval = new ParserVal( new NodoHoja("-"+((ArbolSintactico)yyvsp[0].obj).getLexema(), ((ArbolSintactico)yyvsp[0].obj).getTipo()));}
break;
case 198:
#line 835 ".\gramatica_final.y"
{
					agregarResultado("Casteo explicito. Linea " + lineaUltimoToken);
					ArbolSintactico exp = (ArbolSintactico)yyvsp[-1].obj;
					ArbolSintactico cast = exp;
					if(exp.getTipo().equals("ui8")){
						exp.setTipo("f64");
						cast = new NodoControl("Casteo TOF64", exp);
					}else
						AgregarErrorSemantico("No se puede convertir a f64 desde un tipo que no sea ui8. Linea " + lineaUltimoToken);
					yyval = new ParserVal(cast);
}
break;
case 199:
#line 846 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 200:
#line 847 ".\gramatica_final.y"
{
			yyerror("'(' esperado en casteo explicito, linea :" + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 201:
#line 850 ".\gramatica_final.y"
{
			yyerror("')' esperado en casteo explicito, linea :" + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 204:
#line 859 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 205:
#line 860 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 206:
#line 861 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 207:
#line 862 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 208:
#line 863 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 209:
#line 864 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 210:
#line 868 ".\gramatica_final.y"
{
		Token ID = ((Token)yyvsp[-2].obj);
		if(!existeIdEnAmbito(ID,"Variable")){
			AgregarErrorSemantico("Variable inexistente en cond. del for");	
		}
		
		yyval = new ParserVal(new NodoComun( ((Token)yyvsp[-1].obj).getLexema(), new NodoHoja(ID.getLexema(),ts.getTipo(ID.getRef()), ID.getRef()) ,(ArbolSintactico)yyvsp[0].obj)); 
		if(((ArbolSintactico) yyval.obj).getTipo().equals("error")) 
			AgregarErrorSemantico("Tipos no compatibles en comparación . Linea " + lineaUltimoToken);
		yyval.sval = ID.getRef();
}
break;
case 211:
#line 879 ".\gramatica_final.y"
{
			yyerror("Error en condición del For , identificador  esperado. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 212:
#line 882 ".\gramatica_final.y"
{
			yyerror("Error en condición del For , condición  esperada. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 213:
#line 885 ".\gramatica_final.y"
{
			yyerror("Error en condición del For , expresión aritmetica esperada. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 214:
#line 890 ".\gramatica_final.y"
{yyval = new ParserVal(((Token)yyvsp[0].obj).getLexema());}
break;
case 215:
#line 891 ".\gramatica_final.y"
{yyval = new ParserVal(((Token)yyvsp[0].obj).getLexema());}
break;
case 216:
#line 893 ".\gramatica_final.y"
{
			if(!((ArbolSintactico) yyvsp[0].obj).getTipo().equals("ui8")) 
				AgregarErrorSemantico("Esperado tipo entero en sentencia de incremento / decremento . Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoControl("incr_decr", new NodoHoja(yyvsp[-1].sval+((ArbolSintactico)yyvsp[0].obj).getLexema(),"")));
			}
break;
case 217:
#line 898 ".\gramatica_final.y"
{
			if(!((ArbolSintactico) yyvsp[0].obj).getTipo().equals("ui8")) 
				AgregarErrorSemantico("Esperado tipo entero en sentencia de incremento / decremento . Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoControl("incr_decr", new NodoHoja("+"+ ((ArbolSintactico)yyvsp[0].obj).getLexema(),"")));
		  }
break;
case 218:
#line 903 ".\gramatica_final.y"
{
			yyerror("Error en condición del for , '+' o '-' en clausula de crecimiento esperado. Linea "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 219:
#line 907 ".\gramatica_final.y"
{
			yyerror("Error en condición del for , constante numerica esperada en crecimiento. Linea "  + lineaUltimoToken);
		  	yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
break;
case 220:
#line 913 ".\gramatica_final.y"
{
			agregarResultado("Asignacion dentro del for en linea " + lineaUltimoToken);		
			Token id = ((Token)yyvsp[-2].obj);
			altaDeVariable(id);
                			if(ts.existeEnTS(id.getRef()) && ts.getUso(id.getRef()).equals("Variable")){
                				ts.setTipo(id.getRef(),((ArbolSintactico)yyvsp[0].obj).getTipo());
                			}else{
								AgregarErrorSemantico("Variable en el for, ya existente. Linea " + lineaUltimoToken);
							}
							yyval = new ParserVal(new NodoComun("=:", new NodoHoja(id.getLexema(), ts.getTipo(id.getRef()), id.getRef()),(ArbolSintactico)yyvsp[0].obj));
							yyval.sval = id.getRef();}
break;
case 221:
#line 925 ".\gramatica_final.y"
{
			yyerror("':' esperado en asignacion "  + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 222:
#line 928 ".\gramatica_final.y"
{
			yyerror("error en valor de agnación. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 223:
#line 933 ".\gramatica_final.y"
{
			if(!((ArbolSintactico)yyvsp[-5].obj).getTipo().equals("ui8"))
				AgregarErrorSemantico("Asignación dentro del for solo permite tipos enteros. Linea "+lineaUltimoToken);
			
			if(!((ArbolSintactico)yyvsp[-3].obj).getTipo().equals("ui8"))
				AgregarErrorSemantico("Condición dentro del for solo permite tipos enteros. Linea "+lineaUltimoToken);
				
			if(!yyvsp[-3].sval.equals(yyvsp[-5].sval))
				AgregarErrorSemantico("Se debe utilizar la misma variable de asignacion para la condición en for. Linea "+lineaUltimoToken);



			ArbolSintactico condicion= new NodoControl("Condicion", (ArbolSintactico) yyvsp[-3].obj);
			ArbolSintactico incr_decr = new NodoComun("Incr decr", (ArbolSintactico) yyvsp[-1].obj , condicion);
			ArbolSintactico cuerpo= new NodoComun("Cuerpo For", null, incr_decr);
			ArbolSintactico arbolFor = new NodoComun("For",(ArbolSintactico) yyvsp[-5].obj , cuerpo );

			yyval = new ParserVal(arbolFor);
}
break;
case 224:
#line 952 ".\gramatica_final.y"
{
				yyerror("Esperado ';' en clausula del for. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 225:
#line 955 ".\gramatica_final.y"
{
				yyerror("Esperado ';' en clausula del for. Linea " + lineaUltimoToken);
				yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 226:
#line 960 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 227:
#line 961 ".\gramatica_final.y"
{yyval=yyvsp[0];}
break;
case 228:
#line 964 ".\gramatica_final.y"
{
	yyval = yyvsp[-1];
}
break;
case 229:
#line 970 ".\gramatica_final.y"
{
		agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)yyvsp[0].obj;
		try{
		((ArbolSintactico) yyvsp[-1].obj).getDer().setIzq(cuerpo);
		}catch(Exception e){}
		yyval = yyvsp[-1];
		}
break;
case 230:
#line 979 ".\gramatica_final.y"
{
		altaDeTag((Token)yyvsp[0].obj);
		etiquetasAnidadas.push(((Token)yyvsp[0].obj).getLexema());
	}
break;
case 231:
#line 982 ".\gramatica_final.y"
{agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)yyvsp[0].obj ;
		ArbolSintactico forCompleto = (ArbolSintactico) yyvsp[-1].obj;
		
		forCompleto.getDer().setIzq(cuerpo);
		forCompleto.setDer(new NodoComun("For con Tag" , new NodoHoja(((Token)yyvsp[-4].obj).getLexema(), ""), forCompleto.getDer()));
		etiquetasAnidadas.pop();
		yyval = yyvsp[-1];
		}
break;
case 234:
#line 997 ".\gramatica_final.y"
{
		agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)yyvsp[0].obj;
		((ArbolSintactico) yyvsp[-1].obj).getDer().setIzq(cuerpo);
		yyval = yyvsp[-1];
		}
break;
case 235:
#line 1003 ".\gramatica_final.y"
{
			altaDeTag((Token)yyvsp[0].obj);
		etiquetasAnidadas.push(((Token)yyvsp[0].obj).getLexema());
		}
break;
case 236:
#line 1006 ".\gramatica_final.y"
{
			ArbolSintactico cuerpo = (ArbolSintactico)yyvsp[0].obj ;
			ArbolSintactico forCompleto = (ArbolSintactico) yyvsp[-1].obj;
		
			forCompleto.getDer().setIzq(cuerpo);
			forCompleto.setDer(new NodoComun("For con Tag" , new NodoHoja(((Token)yyvsp[-4].obj).getLexema(), ""), forCompleto.getDer()));
			etiquetasAnidadas.pop();
		yyval = yyvsp[-1];
		}
break;
case 237:
#line 1018 ".\gramatica_final.y"
{
		Token id =(Token)yyvsp[0].obj;
		if(!existeIdEnAmbito(id,"Constante")){
			AgregarErrorSemantico("Esperada constante para comparación del when . Linea " + lineaUltimoToken);
		}
		yyval = new ParserVal(new NodoHoja(id.getLexema(), ts.getTipo(id.getRef())));
		yyval.sval = id.getRef();
	}
break;
case 238:
#line 1026 ".\gramatica_final.y"
{
		yyval = yyvsp[0];
	}
break;
case 239:
#line 1029 ".\gramatica_final.y"
{
		yyerror("Error esperado constante en condición del when, linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
break;
case 240:
#line 1036 ".\gramatica_final.y"
{ /* Solo se comparan dos constantes para poder */
			
		ArbolSintactico const1 = (ArbolSintactico) yyvsp[-3].obj;
		ArbolSintactico const2 = (ArbolSintactico) yyvsp[-1].obj;

		
		String ref1 = yyvsp[-3].sval;
		String ref2 = yyvsp[-1].sval;
		if(!ts.getTipo(ref1).equals(ts.getTipo(ref2)))
			AgregarErrorSemantico("Las dos constantes del when deben ser del mismo tipo");
		if (comparacion(convertirValor(ref1),convertirValor(ref2),((Token)yyvsp[-2].obj).getLexema()))
		{
			yyval = new ParserVal(new NodoControl("Cond",new NodoComun(((Token)yyvsp[-2].obj).getLexema(), const1 , const2)));
		}
		else
			yyval = null;
		dentroWhen = true;
		identificadoresWhen.push(new ArrayList<String>());

	}
break;
case 241:
#line 1056 ".\gramatica_final.y"
{
		yyerror("Esperado '(' en linea: " + lineaUltimoToken);
	}
break;
case 242:
#line 1058 ".\gramatica_final.y"
{
		yyerror("Esperado comparador en linea: " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 243:
#line 1061 ".\gramatica_final.y"
{
		yyerror("Esperado ')' en linea: " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 244:
#line 1064 ".\gramatica_final.y"
{
		yyerror("Error en condición del when, linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 245:
#line 1072 ".\gramatica_final.y"
{
	agregarResultado("Sentencia WHEN. Linea " + lineaUltimoToken);
	if(yyvsp[-2] != null) /* Si el resultado del calculo de condición vino nulo significa que no se genera */
	{
		yyval = new ParserVal(new NodoComun("When",(ArbolSintactico)yyvsp[-2].obj , new NodoControl("CuerpoWhen",(ArbolSintactico)yyvsp[0].obj  ))); /* De todas maneras el código intermedio de la condición solo se guarda*/
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
case 246:
#line 1091 ".\gramatica_final.y"
{
		yyerror("Then esperado en when. Linea " + lineaUltimoToken);
		yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
case 247:
#line 1096 ".\gramatica_final.y"
{
	agregarResultado("Sentencia WHEN. Linea " + lineaUltimoToken);
	if(yyvsp[-2] != null) /* Si el resultado del calculo de condición vino nulo significa que no se genera */
	{
		yyval = new ParserVal(new NodoComun("When",(ArbolSintactico)yyvsp[-2].obj , new NodoControl("CuerpoWhen",(ArbolSintactico)yyvsp[0].obj  ))); /* De todas maneras el código intermedio de la condición solo se guarda*/
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
case 248:
#line 1115 ".\gramatica_final.y"
{
			yyerror("Falta 'then' en WHEN. Linea " + lineaUltimoToken);
			yyval = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
break;
#line 2785 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
