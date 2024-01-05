%{package AnalizadorLexico.AnalizadorSintactico;
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

%}

%token IF THEN ELSE END_IF OUT FUN RETURN BREAK WHEN DEFER UI8 F64 ID CONST CADENA TOF64 CONTINUE  FOR  LLAVE_AB LLAVE_CE MENOR MAYOR IGUAL MENOR_IGUAL MAYOR_IGUAL MENOS SUM MULT DIV PARE_AB PARE_CIE PUNTO_COMA DOS_PUNTOS COMA ASIGNACION DISTINTO DOBLE ENTERO
%start program

%%
program : 
		ID {setPrograma(((Token)$1.obj)); } 
		bloq_general  {
		funciones.get("main").setCuerpoFuncion((ArbolSintactico) $3.obj);
		agregarResultado("Programa terminado . Linea " + lineaUltimoToken);
		}
;

// SENTENCIAS SIMPLES

// GENERAL
sentencia : sentencia_d {$$ = $1;}
		  | sentencia_e_gen {$$ = $1;}
		  | when
;


sentencia_d : d_var 
	| d_fun
	| const 	
;

sentencia_e : asignacion  
		| out
		| invocacion_fun
		| DEFER asignacion {
			$$ = new ParserVal (new NodoHoja("Sentencia asignación diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) $2.obj);
		}
		| DEFER out{
			$$ = new ParserVal (new NodoHoja("Sentencia out diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) $2.obj);
		}
		| DEFER invocacion_fun {
			$$ = new ParserVal (new NodoHoja("Sentencia invocación diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) $2.obj);
		}
;

sentencia_e_gen: sentencia_e
		| if
		| for
		| DEFER if {
			$$ = new ParserVal (new NodoHoja("Sentencia if diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) $2.obj);
		}
		| DEFER for {
			$$ = new ParserVal (new NodoHoja("Sentencia for diferida", ""));
			insertarSentenciaDiferida((ArbolSintactico) $2.obj);
		}
		| return {
			yyerror("Sentencia de retorno fuera de función. Linea: " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
	
;

sentencia_e_for: sentencia_e
			   | if_for
			   | for 
			   |BREAK DOS_PUNTOS ID {
				agregarResultado("Sentencia Break con TAG dentro de for. Linea " + lineaUltimoToken);
				Token id =(Token) $3.obj ;
				if(!existeIdEnAmbito(id,"Tag"))
					AgregarErrorSemantico("Tag no declarado. Linea " + lineaUltimoToken);
				else 
					if(!etiquetasAnidadas.contains(id.getLexema()))
						AgregarErrorSemantico("Tag declarado pero no se encuentra anidado en las sentencias de control. Linea " + lineaUltimoToken);

				
				$$ = new ParserVal(new NodoControl("Break con TAG", new NodoHoja(id.getLexema(), "", id.getLexema())));}
				|BREAK ID {
				yyerror("':' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
			   |BREAK {
				agregarResultado("Sentencia Break. Linea " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Break",""));
				}
			   |CONTINUE  {
				agregarResultado("Sentencia CONTINUE dentro de for. Linea " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Continue",""));
				}
;

// FUNCION

sentencia_fun: sentencia_d
             | sentencia_e_fun
			 | when_fun
;

sentencia_e_fun: sentencia_e
               | if_fun
			   | for_fun
			   | return
			   | DEFER if_fun {
							insertarSentenciaDiferida((ArbolSintactico) $2.obj);
			   }
			   | DEFER for_fun {
							insertarSentenciaDiferida((ArbolSintactico) $2.obj);
			   }
;


sentencia_e_for_fun:for_fun
                   |if_for_fun
				   |return 
			   	   |BREAK DOS_PUNTOS ID {
					agregarResultado("Sentencia Break con . Linea " + lineaUltimoToken);
					Token id =(Token) $3.obj ;
					if(!existeIdEnAmbito(id,"Tag"))
						AgregarErrorSemantico("Tag no declarado. Linea " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Sentencia Break con TAG",""));}
				   |BREAK {
					agregarResultado("Sentencia Break. Linea " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Break",""));}
			   	   |CONTINUE {
					agregarResultado("Sentencia CONTINUE dentro de for. Linea " + lineaUltimoToken);}
;

// BLOQUES DE SENTENCIAS
// GENERAL

bloq_sentencias : sentencia  PUNTO_COMA 
			{
				if($1.obj != null)
					$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $1.obj ,null ));
			}
			| sentencia error  {
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
			| bloq_sentencias sentencia PUNTO_COMA {
				if($2.obj != null)
					$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $2.obj ,(ArbolSintactico) $1.obj ));
			}   			
 			| bloq_sentencias sentencia  error  {
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
; 



bloq_sentencias_e : sentencia_e_gen  PUNTO_COMA {
				$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $1.obj ,null ));}
			| sentencia_e_gen error {
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
			| bloq_sentencias_e sentencia_e_gen PUNTO_COMA {
				$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $2.obj ,(ArbolSintactico) $1.obj ));}	
 			| bloq_sentencias_e sentencia_e_gen error {
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
			| error {
				yyerror("Sentencia ejecutable esperada : Linea  " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
			
; 

bloq_sentencias_e_for : sentencia_e_for PUNTO_COMA{
				$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $1.obj ,null ));
}
			| sentencia_e_for error {
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
			| bloq_sentencias_e_for sentencia_e_for PUNTO_COMA   {
			$$ = new ParserVal(new NodoComun("Sentencia declarativa",(ArbolSintactico) $2.obj ,(ArbolSintactico) $1.obj ));	
			}
 			| bloq_sentencias_e_for sentencia_e_for error {
				yyerror("';' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
			| error {
			yyerror("Sentencia ejecutable o de iteración esperada . Linea " + lineaUltimoToken);
			$$ =new ParserVal(new NodoHoja("Error en sintaxis",""));}
; 

bloq_sentencias_fun:  sentencia_fun PUNTO_COMA
					{$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $1.obj ,null ));}	
				   | sentencia_fun error  {
					yyerror("';' esperado en linea : " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
				   }
				   |  bloq_sentencias_fun  sentencia_fun PUNTO_COMA
				   {$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $2.obj ,(ArbolSintactico) $1.obj ));}	
				   |  bloq_sentencias_fun  sentencia_fun error  {
					yyerror("';' esperado en linea : " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
				   }
				   | error {
					yyerror("Sentencia ejecutable o declarativa de función esperada . Linea " + lineaUltimoToken);
					$$ =new ParserVal(new NodoHoja("Error en sintaxis",""));}
;

bloq_sentencias_e_fun: sentencia_e_fun PUNTO_COMA
					{$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $1.obj ,null ));}

				   | sentencia_e_fun error	{
					yyerror("';' esperado en linea: " + lineaUltimoToken);
					$$ =new ParserVal(new NodoHoja("Error en sintaxis",""));}
				   
				   | bloq_sentencias_e_fun sentencia_e_fun PUNTO_COMA  
				   
					{$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $2.obj ,(ArbolSintactico) $1.obj ));}
				   
				   | sentencia_e_fun sentencia_e_fun {
					yyerror("';' esperado en linea: " + lineaUltimoToken);
					$$ =new ParserVal(new NodoHoja("Error en sintaxis",""));}
				   | error {
					yyerror("Sentencia ejecutable o de función esperada . Linea " + lineaUltimoToken);
					$$ =new ParserVal(new NodoHoja("Error en sintaxis",""));}
;

bloq_sentencias_e_for_fun: sentencia_e_for_fun PUNTO_COMA
						 {$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $1.obj ,null ));}
						 | sentencia_e_for_fun error {
							$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
						 }
		                 | bloq_sentencias_e_for_fun sentencia_e_for_fun PUNTO_COMA
						 {$$ = new ParserVal(new NodoComun("Sentencia",(ArbolSintactico) $2.obj ,(ArbolSintactico) $1.obj ));}
						 | bloq_sentencias_e_for_fun sentencia_e_for_fun error {
							yyerror("';' esperado en linea : " + lineaUltimoToken);
							$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}

						| error error{
							yyerror("Sentencia ejecutable, de iteración o de función esperada . Linea " + lineaUltimoToken);
							$$ =new ParserVal(new NodoHoja("Error en sintaxis",""));};

// DELIMITADORES DE SENTENCIAS CON LLAVES

bloq_general:  	LLAVE_AB bloq_sentencias LLAVE_CE{
				$$ = $2;
				}	 
				| error bloq_sentencias LLAVE_CE  {
				yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| LLAVE_AB bloq_sentencias error{
				yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| bloq_sentencias{
				yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| LLAVE_AB LLAVE_CE{$$ = new ParserVal(new NodoHoja("Sentencia" , ""));}
;

bloq_ejecutable : LLAVE_AB bloq_sentencias_e LLAVE_CE {$$= $2;}
				| error bloq_sentencias_e LLAVE_CE {
				yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| LLAVE_AB bloq_sentencias_e error{
				yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				//| bloq_sentencias_e  {
				//yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				//$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| LLAVE_AB LLAVE_CE{$$ = new ParserVal(new NodoHoja("Sentencia" , ""));}
				
;



bloq_ejecutable_for:  LLAVE_AB bloq_sentencias_e_for LLAVE_CE {$$=$2;}
					| error bloq_sentencias_e_for LLAVE_CE {
					yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
					| LLAVE_AB bloq_sentencias_e_for error {
					yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
					| error bloq_sentencias_e_for error {
					yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
					| LLAVE_AB LLAVE_CE{
					$$ = new ParserVal(new NodoHoja("Sentencia" , ""));}
;

bloq_general_fun: LLAVE_AB bloq_sentencias_fun LLAVE_CE{ $$ = $2;}
				| error bloq_sentencias_fun LLAVE_CE  {yyerror("'{' esperado en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| LLAVE_AB bloq_sentencias_fun error{yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| error bloq_sentencias_fun error {yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
				| LLAVE_AB LLAVE_CE{$$ = new ParserVal(new NodoHoja("Sentencia" , ""));}
;

bloq_ejecutable_fun: 	  LLAVE_AB bloq_sentencias_e_fun LLAVE_CE  {$$ = $2;}
						  | error bloq_sentencias_e_fun LLAVE_CE {
							yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);}
						  | LLAVE_AB bloq_sentencias_e_fun error{
							yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
							$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
						  | error bloq_sentencias_e_fun  error  {
							yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
							$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
						  | LLAVE_AB LLAVE_CE{$$ = new ParserVal(new NodoHoja("Sentencia" , ""));}
;


bloq_ejecutable_for_fun:  LLAVE_AB bloq_sentencias_e_for_fun LLAVE_CE {
	$$ = $2;
}
						  | error bloq_sentencias_e_for_fun LLAVE_CE {
							yyerror("'{' esperado o error al principio del bloque en linea : " + lineaUltimoToken);
							$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
						  | LLAVE_AB bloq_sentencias_e_for_fun error {
							yyerror("'}' esperado o error al final del bloque en linea : " + lineaUltimoToken);
							$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
						  | error bloq_sentencias_e_for_fun error {
							yyerror("'{' y '}' esperados en bloque, linea : " + lineaUltimoToken);
							$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
						  | LLAVE_AB LLAVE_CE{
							$$ = new ParserVal(new NodoHoja("Sentencia" , ""));}
;


 // SENTENCIAS DECLARATIVASS

 // DECLARACION VAR
l_variables : ID {declaracionVarActual.add((Token)$1.obj);}
	| error  {
		yyerror("Error en declaración de variables ',' esperada. Linea " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	|  l_variables COMA ID { declaracionVarActual.add((Token)$3.obj);}	
; 

d_var : tipo l_variables {verificarDeclaracionVariables();
						String tipo = ((Token)$1.obj).getLexema();
                        declararTipos(tipo);
						$$ = new ParserVal(null);
						declaracionVarActual.clear();
						}
	  | ID l_variables{
		yyerror("Error en declaración de variables Tipo desconocido. Linea " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
	  } 
;

// DECLARACION FUNCION
parametro_form : tipo ID{
	//Alta de parametro, le paso referencia, uso "parametro" y tipo $1.getLexema()
	Token parametro = (Token)$2.obj;
	altaDeParametro(parametro,((Token)$1.obj).getLexema());
	$$ = new ParserVal(new Parametro(parametro.getLexema(),((Token)$1.obj).getLexema(), parametro.getRef()));

}
; 

l_parametros_form : parametro_form{
				Parametro[] params = new Parametro[1]; // Se devuelve arreglo de 1 es mas facil preguntar por longitud
				params[0] =(Parametro) $1.obj;
				$$ = new ParserVal(params);
				}
            	| parametro_form COMA parametro_form  {
				Parametro[] params = new Parametro[2];
				params[0] = (Parametro)$1.obj;
				params[1] =(Parametro) $3.obj;
				$$ = new ParserVal(params);
				}
; 

parametros_fun: PARE_AB l_parametros_form PARE_CIE {
				$$ = $2;}
			  | PARE_AB PARE_CIE {
				$$ = new ParserVal(new Parametro[0]);} 
			  | PARE_AB error PARE_CIE {
				 yyerror("Error en parametros de función. Linea " + lineaUltimoToken);
			    $$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
			  
;


cabeceraFun: FUN ID{
	Token ID = (Token) $2.obj;
	altaDeFuncion(ID);
	if(erroresSemanticos.isEmpty()){
	funciones.put(pila.getAmbitoActual(),new ElemFuncion());
	funciones.get(pila.getAmbitoActual()).setRetorno(false);
	}
	$$ = $2;
	
}

retornoFun: DOS_PUNTOS tipo {
			String tipo =((Token) $2.obj).getLexema();
			$$ = new ParserVal(tipo);
}
		  | DOS_PUNTOS error {
			yyerror("Tipo esperado en declaración de funcion. Linea " + lineaUltimoToken);}
		  | error {
			yyerror("Tipo esperado en declaración de funcion. Linea " + lineaUltimoToken);}
;

d_fun : 	cabeceraFun parametros_fun retornoFun{
			try{Token ID = (Token) $1.obj;
			Parametro[] a = (Parametro[]) $2.obj;
			if(a.length>1){
				ts.setParametro1(ID.getRef(), a[0]);
				ts.setParametro2(ID.getRef(), a[1]);
			}
			else if (a.length == 1) 
				ts.setParametro1(ID.getRef(), a[0]);

			if (ts.getUso(((Token) $1.obj).getRef()).equals("Funcion")) // Se asigna tipo de función
				ts.setTipo(((Token) $1.obj).getRef(), $3.sval);
			}catch(Exception e){}
			
			} bloq_general_fun {
					agregarResultado("Declaracion FUNCIÓN. Linea " + lineaUltimoToken);
					Token ID = (Token) $1.obj;
					
					$$ = new ParserVal(null); // Lo que se muestra en caso de estar en el arbol principal 
					if(erroresSemanticos.isEmpty()){
					if(!funciones.get(pila.getAmbitoActual()).isRetorno())
						AgregarErrorSemantico("Función sin sentencia de retorno asociada. Linea " + lineaUltimoToken);
					if(!revisarRamasRetorno(((ArbolSintactico) $5.obj).getIzq()))
						AgregarErrorSemantico("No existe una sentencia de retorno asociada por cada rama de ejecución. Linea " + lineaUltimoToken);
					funciones.get(pila.getAmbitoActual()).setCuerpoFuncion((ArbolSintactico) $5.obj);
					funciones.get(pila.getAmbitoActual()).setId(ID.getRef());
					pila.eliminarUltimoAmbito();}}
;

asignacion_const: ID ASIGNACION constante_num {
							agregarResultado("Declaracion asignacion constante en linea " + lineaUltimoToken);				
                            Token id = ((Token)$1.obj);
				
							altaDeConstante(id);
                			if(ts.existeEnTS(id.getRef()) && ts.getUso(id.getRef()).equals("Constante")){
                				ts.setTipo(id.getRef(),((ArbolSintactico)$3.obj).getTipo());
								ts.setValor(id.getRef(),((ArbolSintactico)$3.obj).getLexema() );
                			}
							$$ = new ParserVal(new NodoHoja("Declaración de constantes", ts.getTipo(id.getRef()), id.getRef() ));}  
		   		| ID IGUAL constante_num {
					yyerror("':' esperado en asignacion "  + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
		   		| ID ASIGNACION error {
					yyerror("error en valor de asignación, valor constante esperado. Linea " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;


// DECLARACION CONST
l_const : asignacion_const {$$ = $1;}
		| l_const COMA asignacion_const  {$$ = $3;}
;

const : CONST l_const {
		agregarResultado("Declaracion CONST. Linea " + lineaUltimoToken);
		$$ = new ParserVal (null);
		}
;


constante_num : DOBLE {
				String valor  = ((Token)$1.obj).getLexema();
				$$ = new ParserVal(new NodoHoja(valor, ts.getTipo(valor), valor));
				ts.setValor(valor, valor);
				$$.sval = valor;}
			  | ENTERO {
				String valor  = ((Token)$1.obj).getLexema();
				$$ = new ParserVal(new NodoHoja(valor, ts.getTipo(valor), valor));
				ts.setValor(valor, valor);
				$$.sval = valor;
				}

;

parametro_real : ID {
				Token ID = (Token) $1.obj; 
				if (existeIdEnAmbito(ID,"Variable") || existeIdEnAmbito(ID,"Constante")) {  
                    listaParametrosReales.add(new Parametro(ID.getLexema(), ts.getTipo(ID.getRef()), ID.getRef()));
                }
				}
	       | constante_num {
		   		listaParametrosReales.add(new Parametro(((ArbolSintactico)$1.obj).getLexema(),((ArbolSintactico)$1.obj).getTipo(), ((ArbolSintactico)$1.obj).getLexema()));
		   }
;	       

l_parametros_reales: PARE_AB parametro_real COMA parametro_real PARE_CIE 
					| PARE_AB parametro_real  PARE_CIE 
					| PARE_AB PARE_CIE {$$ = null;}
					| PARE_AB error PARE_CIE {yyerror("Error en parametros invocación a funcion. Linea " + lineaUltimoToken);
					$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

invocacion_fun : ID l_parametros_reales {
	Token funcionInvocada = (Token)$1.obj;
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
		
		$$ = new ParserVal(new NodoComun("Invocación a función", new NodoHoja(funcionInvocada.getLexema(), tipoRetorno, funcionInvocada.getRef(), true),new NodoComun("Parametros", primerParametro, segundoParametro)));
	}
	else if(listaParametrosReales.size() == 1)
	{
		Parametro primero = listaParametrosReales.get(0);

            NodoHoja primerParametro = new NodoHoja(primero.getId(), primero.getTipo(), primero.getReferencia());
            Parametro primerFormal = ts.getParametro1(funcionInvocada.getRef());
            if (primerFormal != null)
              primerParametro.setRefParametroFormal(primerFormal.getReferencia());

		$$ = new ParserVal(new NodoComun("Invocación a función", new NodoHoja(funcionInvocada.getLexema(), tipoRetorno, funcionInvocada.getRef(),true), new NodoComun("Parametros",primerParametro, null)));
	}
	else {
		$$ = new ParserVal(new NodoComun("Invocación a función", new NodoHoja(funcionInvocada.getLexema(), tipoRetorno, funcionInvocada.getRef(), true), new NodoComun("Parametros",null, null)));
	}
	((ArbolSintactico)$$.obj).setTipo(tipoRetorno);
	
	listaParametrosReales.clear();

}

// ASIGNACION
asignacion : ID ASIGNACION op_aritemtica_cast {agregarResultado("Asignacion en linea " + lineaUltimoToken);
							  Token id = (Token)$1.obj;
                              if (!existeIdEnAmbito(id, "Variable")) 
            					AgregarErrorSemantico("Variable no definida . Linea " + lineaUltimoToken);

							  $$ = new ParserVal(new NodoComun("=:", new NodoHoja(id.getLexema(), ts.getTipo(id.getRef()), id.getRef()),(ArbolSintactico)$3.obj));
							  
							  if(((ArbolSintactico) $$.obj).getTipo().equals("error")) 
							  	AgregarErrorSemantico("Tipos no compatibles en asignación . Linea " + lineaUltimoToken);
							  }  
		   | ID IGUAL op_aritemtica_cast {
			yyerror("':' esperado en asignacion "  + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
		   | ID ASIGNACION error {
			yyerror("error en valor de asignación. Linea " + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

// Return

return:  RETURN op_aritemtica_cast {
				agregarResultado("Sentencia RETURN. Linea " + lineaUltimoToken);
				if(!revisarTipoRetorno(((ArbolSintactico)$2.obj).getTipo())){
					AgregarErrorSemantico("Tipo erroneo en sentencia Return. Linea: " + lineaUltimoToken);
				}
				try{
				funciones.get(pila.getAmbitoActual()).setRetorno(true);
				ArbolSintactico sentenciasDiferidas = funciones.get(pila.getAmbitoActual()).getSentenciasDiferidas();
				if (sentenciasDiferidas!=null)
					$$ = new ParserVal(new NodoComun(("Sentencias Diferidas"), new NodoControl("Retorno",(ArbolSintactico) $2.obj) , sentenciasDiferidas));
				else
					$$ = new ParserVal(new NodoControl("Retorno",(ArbolSintactico) $2.obj));
				}
				catch(Exception e){
					$$ = new ParserVal(new NodoHoja("Error en retorno", ""));
				}
				}
		| RETURN error {
			yyerror("Error en valor de retorno. Linea " + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
;



// IF
cond_if: op_aritemtica_cast comp op_aritemtica_cast {
		$$ = new ParserVal(new NodoComun( ((Token)$2.obj).getLexema(), (ArbolSintactico)$1.obj ,(ArbolSintactico)$3.obj));  
		if(((ArbolSintactico) $$.obj).getTipo().equals("error")) 
							  	AgregarErrorSemantico("Tipos no compatibles en comparación . Linea " + lineaUltimoToken);
							
		}
	   | error comp op_aritemtica_cast {$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	   | op_aritemtica_cast error op_aritemtica_cast {$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	   | op_aritemtica_cast comp error {$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

cond : PARE_AB cond_if PARE_CIE {
		$$ = new ParserVal(new NodoControl("CondicionIF",(ArbolSintactico) $2.obj));
	}
	 | error cond_if PARE_CIE {
		yyerror("Esperado '(' en linea: " + lineaUltimoToken);
	} 
	 | PARE_AB cond_if error {
		yyerror("Esperado ')' en linea: " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	 | PARE_AB error PARE_CIE {
		yyerror("Error en condición, linea " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;



// IF dentro de for de funcion (Permite continue break y return)

bloque_then_for_fun: bloq_ejecutable_for_fun
		   | sentencia_e_for_fun PUNTO_COMA
;

bloque_else_for_fun: bloq_ejecutable_for_fun
		   | sentencia_e_for_fun PUNTO_COMA
;

if_for_fun :// Correctas 
	    IF cond THEN bloque_then_for_fun ELSE bloque_else_for_fun END_IF {
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) $4.obj, (ArbolSintactico) $6.obj);
			$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
			
			}
	// Simples
	|   IF cond THEN bloque_then_for_fun END_IF {
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) $4.obj);
			$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
	}	
	//Errores
	| 	IF cond THEN bloque_then_for_fun error bloque_else_for_fun END_IF{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond error bloque_then_for_fun ELSE bloque_else_for_fun END_IF {
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond THEN bloque_then_for_fun ELSE bloque_else_for_fun error{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

// IF dentro de for (Permite continue break)
bloque_then_for: bloq_ejecutable_for {
			$$= new ParserVal(new NodoControl("Then",(ArbolSintactico) $1.obj));}
		   | sentencia_e_for PUNTO_COMA {
			$$= new ParserVal(new NodoControl("Then",(ArbolSintactico) $1.obj));
		   }
;

bloque_else_for: bloq_ejecutable_for {
			$$= new ParserVal(new NodoControl("Else",(ArbolSintactico) $1.obj));}
		   | sentencia_e_for PUNTO_COMA {
		   	$$= new ParserVal(new NodoControl("Else",(ArbolSintactico) $1.obj));}
;

if_for :// Correctas 
	    IF cond THEN bloque_then_for ELSE bloque_else_for END_IF {
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) $4.obj, (ArbolSintactico) $6.obj);
			$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
			}
	// Simples
	|   IF cond THEN bloque_then_for END_IF {
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) $4.obj);
			$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
	
		}	
	//|   IF cond THEN bloq_ejecutable END_IF {agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );}	
	//Errores
	| 	IF cond THEN bloque_then_for error bloque_else_for END_IF{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond error bloque_then_for ELSE bloque_else_for END_IF {
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond THEN bloque_then_for ELSE bloque_else_for error{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;


// IF dentro de funcion (permite return)

bloque_then_fun: bloq_ejecutable_fun
		   | sentencia_e_fun PUNTO_COMA
;

bloque_else_fun: bloq_ejecutable_fun
		   | sentencia_e_fun PUNTO_COMA
;

if_fun :// Correctas 
	    IF cond THEN bloque_then_fun ELSE bloque_else_fun END_IF {
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) $4.obj, (ArbolSintactico) $6.obj);
			$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
			
			}
	// Simples
	|   IF cond THEN bloque_then_fun END_IF {
		agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
		NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) $4.obj);
		$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
	}	
	//Errores
	| 	IF cond THEN bloque_then_fun error bloque_else_fun END_IF{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond error bloque_then_fun ELSE bloque_else_fun END_IF {
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond THEN bloque_then_fun ELSE bloque_else_fun error{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;


bloque_then: bloq_ejecutable {
			$$= new ParserVal(new NodoControl("Then",(ArbolSintactico) $1.obj));}
		   | sentencia_e_gen PUNTO_COMA {
			$$= new ParserVal(new NodoControl("Then",(ArbolSintactico) $1.obj));
		   }
;

bloque_else: bloq_ejecutable {
			$$= new ParserVal(new NodoControl("Else",(ArbolSintactico) $1.obj));}
		   | sentencia_e_gen PUNTO_COMA {
		   	$$= new ParserVal(new NodoControl("Else",(ArbolSintactico) $1.obj));}
;

if :// Correctas 
	    IF cond THEN bloque_then ELSE bloque_else END_IF {
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoComun cuerpo =  new NodoComun("CuerpoIF" ,(ArbolSintactico) $4.obj, (ArbolSintactico) $6.obj);
			$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
			}
	// Simples
	|   IF cond THEN bloque_then END_IF {
			agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );
			NodoControl cuerpo =  new NodoControl("CuerpoIF" ,(ArbolSintactico) $4.obj);
			$$ = new ParserVal(new NodoComun("IF" ,(ArbolSintactico) $2.obj,cuerpo));
	
		}	
	//|   IF cond THEN bloq_ejecutable END_IF {agregarResultado("Sentencia IF. Linea " + lineaUltimoToken );}	
	//Errores
	| 	IF cond THEN bloque_then error bloque_else END_IF{
		yyerror("Falta ELSE en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond error bloque_then ELSE bloque_else END_IF {
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	| 	IF cond THEN bloque_then ELSE bloque_else error{
		yyerror("Falta END_IF en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	|   IF cond error bloque_then END_IF{
		yyerror("Falta THEN en if, linea" + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}

	 
;

// OUT
cont_cadena : CADENA{
				String lexema = ((Token)$1.obj).getLexema();
				$$ = new ParserVal(new NodoHoja(lexema, "str"));
}
			| ID {      
				Token id = (Token)$1.obj;
                 if (!(existeIdEnAmbito(id, "Variable") || existeIdEnAmbito(id, "Constante") || existeIdEnAmbito(id, "Parametro") )) {
                  AgregarErrorSemantico("Identificador inexistente para sentencia de out. Linea " + lineaUltimoToken); 
                 }
				 $$ = new ParserVal(new NodoHoja(id.getRef(), ts.getTipo(id.getRef()), id.getRef()));
				}
;


out : OUT PARE_AB cont_cadena PARE_CIE  {
	agregarResultado("Sentencia OUT. Linea " + lineaUltimoToken);
	$$ = new ParserVal(new NodoControl("Sentencia out",(ArbolSintactico) $3.obj ));	
	}
 	
	| OUT PARE_AB error {
		yyerror("Error en contenido del OUT");
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
 ;

// EXP ARITMETICAS

factor : ID {
		Token id = (Token) $1.obj;
		if(!existeIdEnAmbito(id,"Variable") &&  !existeIdEnAmbito(id,"Parametro") && !existeIdEnAmbito(id,"Constante")){
			AgregarErrorSemantico("Variable no declarada en la expresión. Linea " + lineaUltimoToken);	
		}
		$$ = new ParserVal(new NodoHoja(id.getLexema(),ts.getTipo(id.getRef()), id.getRef()));}
    | invocacion_fun 
	| constante_num { $$ = $1;}
;

termino : factor {$$ = $1;}
	| termino MULT factor {$$ = new ParserVal(new NodoComun("*", (ArbolSintactico)$1.obj, (ArbolSintactico)$3.obj));}
	| termino DIV factor {$$ = new ParserVal(new NodoComun("/", (ArbolSintactico)$1.obj, (ArbolSintactico)$3.obj));} 
;

e_aritmetica : termino {$$ = $1;}
		 | e_aritmetica SUM termino {$$ = new ParserVal(new NodoComun("+", (ArbolSintactico)$1.obj, (ArbolSintactico)$3.obj));}
	     | e_aritmetica MENOS termino {$$ = new ParserVal(new NodoComun("-", (ArbolSintactico)$1.obj, (ArbolSintactico)$3.obj));} 
	     | MENOS termino {$$ = new ParserVal( new NodoHoja("-"+((ArbolSintactico)$2.obj).getLexema(), ((ArbolSintactico)$2.obj).getTipo()));} 
;

op_aritemtica_cast : TOF64 PARE_AB e_aritmetica PARE_CIE {
					agregarResultado("Casteo explicito. Linea " + lineaUltimoToken);
					ArbolSintactico exp = (ArbolSintactico)$3.obj;
					ArbolSintactico cast = exp;
					if(exp.getTipo().equals("ui8")){
						exp.setTipo("f64");
						cast = new NodoControl("Casteo TOF64", exp);
					}else
						AgregarErrorSemantico("No se puede convertir a f64 desde un tipo que no sea ui8. Linea " + lineaUltimoToken);
					$$ = new ParserVal(cast);
}
		   | e_aritmetica {$$=$1;}
		   |  TOF64  error e_aritmetica PARE_CIE {
			yyerror("'(' esperado en casteo explicito, linea :" + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
		   |  TOF64  PARE_AB e_aritmetica  error{
			yyerror("')' esperado en casteo explicito, linea :" + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

tipo : UI8
	| F64
;

comp: MAYOR {$$=$1;}
	| MENOR {$$=$1;}
	| IGUAL {$$=$1;}
	| MAYOR_IGUAL {$$=$1;}
	| MENOR_IGUAL {$$=$1;}
	| DISTINTO {$$=$1;}
;
 
// FOR
cond_for : ID comp op_aritemtica_cast {
		Token ID = ((Token)$1.obj);
		if(!existeIdEnAmbito(ID,"Variable")){
			AgregarErrorSemantico("Variable inexistente en cond. del for");	
		}
		
		$$ = new ParserVal(new NodoComun( ((Token)$2.obj).getLexema(), new NodoHoja(ID.getLexema(),ts.getTipo(ID.getRef()), ID.getRef()) ,(ArbolSintactico)$3.obj)); 
		if(((ArbolSintactico) $$.obj).getTipo().equals("error")) 
			AgregarErrorSemantico("Tipos no compatibles en comparación . Linea " + lineaUltimoToken);
		$$.sval = ID.getRef();
}
		 | error comp op_aritemtica_cast {
			yyerror("Error en condición del For , identificador  esperado. Linea "  + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
		 | ID error op_aritemtica_cast   {
			yyerror("Error en condición del For , condición  esperada. Linea "  + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
		 | ID comp error  {
			yyerror("Error en condición del For , expresión aritmetica esperada. Linea "  + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

signo : SUM {$$ = new ParserVal(((Token)$1.obj).getLexema());}
	  | MENOS {$$ = new ParserVal(((Token)$1.obj).getLexema());}
;
crecim_for: signo constante_num {
			if(!((ArbolSintactico) $2.obj).getTipo().equals("ui8")) 
				AgregarErrorSemantico("Esperado tipo entero en sentencia de incremento / decremento . Linea " + lineaUltimoToken);
			$$ = new ParserVal(new NodoControl("incr_decr", new NodoHoja($1.sval+((ArbolSintactico)$2.obj).getLexema(),"")));
			}
		  | constante_num {
			if(!((ArbolSintactico) $1.obj).getTipo().equals("ui8")) 
				AgregarErrorSemantico("Esperado tipo entero en sentencia de incremento / decremento . Linea " + lineaUltimoToken);
			$$ = new ParserVal(new NodoControl("incr_decr", new NodoHoja("+"+ ((ArbolSintactico)$1.obj).getLexema(),"")));
		  }
		  | error constante_num {
			yyerror("Error en condición del for , '+' o '-' en clausula de crecimiento esperado. Linea "  + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			}
		  | signo error {
			yyerror("Error en condición del for , constante numerica esperada en crecimiento. Linea "  + lineaUltimoToken);
		  	$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
			} 
;

asignacion_for:  ID ASIGNACION op_aritemtica_cast {
			agregarResultado("Asignacion dentro del for en linea " + lineaUltimoToken);		
			Token id = ((Token)$1.obj);
			altaDeVariable(id);
                			if(ts.existeEnTS(id.getRef()) && ts.getUso(id.getRef()).equals("Variable")){
                				ts.setTipo(id.getRef(),((ArbolSintactico)$3.obj).getTipo());
                			}else{
								AgregarErrorSemantico("Variable en el for, ya existente. Linea " + lineaUltimoToken);
							}
							$$ = new ParserVal(new NodoComun("=:", new NodoHoja(id.getLexema(), ts.getTipo(id.getRef()), id.getRef()),(ArbolSintactico)$3.obj));
							$$.sval = id.getRef();}

		   | ID IGUAL op_aritemtica_cast {
			yyerror("':' esperado en asignacion "  + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
		   | ID ASIGNACION error {
			yyerror("error en valor de agnación. Linea " + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

clausula_for: PARE_AB asignacion_for PUNTO_COMA cond_for PUNTO_COMA crecim_for PARE_CIE {
			if(!((ArbolSintactico)$2.obj).getTipo().equals("ui8"))
				AgregarErrorSemantico("Asignación dentro del for solo permite tipos enteros. Linea "+lineaUltimoToken);
			
			if(!((ArbolSintactico)$4.obj).getTipo().equals("ui8"))
				AgregarErrorSemantico("Condición dentro del for solo permite tipos enteros. Linea "+lineaUltimoToken);
				
			if(!$4.sval.equals($2.sval))
				AgregarErrorSemantico("Se debe utilizar la misma variable de asignacion para la condición en for. Linea "+lineaUltimoToken);



			ArbolSintactico condicion= new NodoControl("Condicion", (ArbolSintactico) $4.obj);
			ArbolSintactico incr_decr = new NodoComun("Incr decr", (ArbolSintactico) $6.obj , condicion);
			ArbolSintactico cuerpo= new NodoComun("Cuerpo For", null, incr_decr);
			ArbolSintactico arbolFor = new NodoComun("For",(ArbolSintactico) $2.obj , cuerpo );

			$$ = new ParserVal(arbolFor);
}
			| PARE_AB asignacion_for error cond_for PUNTO_COMA crecim_for PARE_CIE  {
				yyerror("Esperado ';' en clausula del for. Linea " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));} 
			| PARE_AB asignacion_for PUNTO_COMA cond_for error crecim_for PARE_CIE  {
				yyerror("Esperado ';' en clausula del for. Linea " + lineaUltimoToken);
				$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));} 
;

cuerpoFor: bloq_ejecutable_for {$$=$1;}
         | sentencia_e_for {$$=$1;}
;
	 //Correctas 	
tag: ID DOS_PUNTOS{
	$$ = $1;
}
;
 

for : FOR clausula_for cuerpoFor  {
		agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)$3.obj;
		try{
		((ArbolSintactico) $2.obj).getDer().setIzq(cuerpo);
		}catch(Exception e){}
		$$ = $2;
		}
	
    | tag {
		altaDeTag((Token)$1.obj);
		etiquetasAnidadas.push(((Token)$1.obj).getLexema());
	} FOR clausula_for  cuerpoFor {agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)$5.obj ;
		ArbolSintactico forCompleto = (ArbolSintactico) $4.obj;
		
		forCompleto.getDer().setIzq(cuerpo);
		forCompleto.setDer(new NodoComun("For con Tag" , new NodoHoja(((Token)$1.obj).getLexema(), ""), forCompleto.getDer()));
		etiquetasAnidadas.pop();
		$$ = $4;
		}
;    

cuerpoForFun: bloq_ejecutable_for_fun
         | sentencia_e_for_fun

	 
for_fun : FOR clausula_for cuerpoForFun {
		agregarResultado("Sentencia FOR. Linea " + lineaUltimoToken);
		ArbolSintactico cuerpo = (ArbolSintactico)$3.obj;
		((ArbolSintactico) $2.obj).getDer().setIzq(cuerpo);
		$$ = $2;
		}
    	| tag {
			altaDeTag((Token)$1.obj);
		etiquetasAnidadas.push(((Token)$1.obj).getLexema());
		}FOR clausula_for cuerpoForFun {
			ArbolSintactico cuerpo = (ArbolSintactico)$5.obj ;
			ArbolSintactico forCompleto = (ArbolSintactico) $4.obj;
		
			forCompleto.getDer().setIzq(cuerpo);
			forCompleto.setDer(new NodoComun("For con Tag" , new NodoHoja(((Token)$1.obj).getLexema(), ""), forCompleto.getDer()));
			etiquetasAnidadas.pop();
		$$ = $4;
		}
;    

const_when: 
	ID {
		Token id =(Token)$1.obj;
		if(!existeIdEnAmbito(id,"Constante")){
			AgregarErrorSemantico("Esperada constante para comparación del when . Linea " + lineaUltimoToken);
		}
		$$ = new ParserVal(new NodoHoja(id.getLexema(), ts.getTipo(id.getRef())));
		$$.sval = id.getRef();
	}
	| constante_num {
		$$ = $1;
	}
	| error {
		yyerror("Error esperado constante en condición del when, linea " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));
		}
;


cond_when :PARE_AB const_when comp const_when PARE_CIE { // Solo se comparan dos constantes para poder 
			
		ArbolSintactico const1 = (ArbolSintactico) $2.obj;
		ArbolSintactico const2 = (ArbolSintactico) $4.obj;

		
		String ref1 = $2.sval;
		String ref2 = $4.sval;
		if(!ts.getTipo(ref1).equals(ts.getTipo(ref2)))
			AgregarErrorSemantico("Las dos constantes del when deben ser del mismo tipo");
		if (comparacion(convertirValor(ref1),convertirValor(ref2),((Token)$3.obj).getLexema()))
		{
			$$ = new ParserVal(new NodoControl("Cond",new NodoComun(((Token)$3.obj).getLexema(), const1 , const2)));
		}
		else
			$$ = null;
		dentroWhen = true;
		identificadoresWhen.push(new ArrayList<String>());

	}
	 | error const_when comp const_when PARE_CIE {
		yyerror("Esperado '(' en linea: " + lineaUltimoToken);
	}| PARE_AB const_when error const_when PARE_CIE {
		yyerror("Esperado comparador en linea: " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	 | PARE_AB const_when comp const_when error {
		yyerror("Esperado ')' en linea: " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
	 | PARE_AB error PARE_CIE {
		yyerror("Error en condición del when, linea " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}
;

;

// WHEN
when : WHEN cond_when THEN bloq_general   {
	agregarResultado("Sentencia WHEN. Linea " + lineaUltimoToken);
	if($2 != null) // Si el resultado del calculo de condición vino nulo significa que no se genera 
	{
		$$ = new ParserVal(new NodoComun("When",(ArbolSintactico)$2.obj , new NodoControl("CuerpoWhen",(ArbolSintactico)$4.obj  ))); // De todas maneras el código intermedio de la condición solo se guarda
		List<String> aux= identificadoresWhen.pop();
    	if(!identificadoresWhen.empty()) 
      		identificadoresWhen.peek().addAll(aux);
	}																												// para mostrar debido a que su condición en este punto fue calculada
	else
	{
		$$ = new ParserVal(new NodoHoja("When - condición erronea",""));
		for(String s : identificadoresWhen.pop()){
			ts.disminuir(s);
		}
	}
	if(identificadoresWhen.empty())
		dentroWhen = false;
	}
	 |WHEN cond_when error bloq_general   {
		yyerror("Then esperado en when. Linea " + lineaUltimoToken);
		$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}  
;

when_fun : WHEN cond_when THEN bloq_general_fun  {
	agregarResultado("Sentencia WHEN. Linea " + lineaUltimoToken);
	if($2 != null) // Si el resultado del calculo de condición vino nulo significa que no se genera 
	{
		$$ = new ParserVal(new NodoComun("When",(ArbolSintactico)$2.obj , new NodoControl("CuerpoWhen",(ArbolSintactico)$4.obj  ))); // De todas maneras el código intermedio de la condición solo se guarda
		List<String> aux = identificadoresWhen.pop();
    	if(!identificadoresWhen.empty()) 
      		identificadoresWhen.peek().addAll(aux);
	}																												// para mostrar debido a que su condición en este punto fue calculada
	else
	{
		$$ = new ParserVal(new NodoHoja("When - condición erronea",""));
		for(String s : identificadoresWhen.pop()){
      		ts.disminuir(s);
    	}
	}
	if(identificadoresWhen.empty())
	  dentroWhen = false;
	}
 		 | WHEN cond_when error bloq_general_fun   {
			yyerror("Falta 'then' en WHEN. Linea " + lineaUltimoToken);
			$$ = new ParserVal(new NodoHoja("Error de sintaxis" , ""));}

;



%% 



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