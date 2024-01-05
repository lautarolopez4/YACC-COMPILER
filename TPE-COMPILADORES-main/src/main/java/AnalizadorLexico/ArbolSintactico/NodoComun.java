package AnalizadorLexico.ArbolSintactico;

import AnalizadorLexico.GeneradorAssembler;
import AnalizadorLexico.TablaSimbolos;

public class NodoComun extends ArbolSintactico {
	public static ArbolSintactico sub1, sub2;

	private static boolean negado;

	public NodoComun(String lexema, ArbolSintactico izq, ArbolSintactico der) {
		super(lexema, revisarTipos(izq, der));
		super.setIzq(izq);
		super.setDer(der);

	}

	private static String revisarTipos(ArbolSintactico a, ArbolSintactico b) {

		if (a != null && b != null && a.getTipo() != null && b.getTipo() != null) {
			if (a.getTipo().equals(b.getTipo()))
				return a.getTipo();
			else
				return "error";
		}
		return "";
	}

	@Override
	public void recorrerArbol(String blancoAcumulado) {
		String lexema = this.getLexema();
		System.out.print(blancoAcumulado + lexema);
		String tipo = this.getTipo();
		if (!tipo.equals(""))
			System.out.print(". Tipo : " + tipo);
		System.out.print('\n');
		if (getIzq() != null) {
			System.out.println(getBlanco() + blancoAcumulado + "Hijo Izquierdo");
			getIzq().recorrerArbol(getBlanco() + blancoAcumulado);
		}
		if (getDer() != null) {
			System.out.println(getBlanco() + blancoAcumulado + "Hijo Derecho");
			getDer().recorrerArbol(getBlanco() + blancoAcumulado);
		}

	}

	public String comparacion(String salto) {
		StringBuilder salida = new StringBuilder();
		salida.append(getIzq().getAssembler() + getDer().getAssembler());
		ArbolSintactico hojaI = getIzq().getHoja();
		ArbolSintactico hojaD = getDer().getHoja();

		if (hojaI.getTipo().equals("ui8")) {
			salida.append("MOV AL, " + hojaI.getRefString() + "\n"
					+ "CMP AL, " + hojaD.getRefString() + "\n"
					+ salto + etiquetas.peek() + "\n");
		} else {
			salida.append("FLD " + hojaI.getRefString() + "\n"
					+ "FCOMP " + hojaD.getRefString() + "\n"
					+ salto + etiquetas.peek() + "\n");
		}

		return salida.toString();
	}

	public String getAssembler() {
		StringBuilder salida = new StringBuilder();
		String entrada = this.getLexema();
		System.out.println("Assembler Nodo Comun, entrada: " + entrada);
		switch (entrada) {

			case "*": {
				salida.append(this.getIzq().getAssembler() + this.getDer().getAssembler());
				cantAux++;
				nuevoAux = "@aux" + cantAux;
				sub1 = getIzq().getHoja();
				sub2 = getDer().getHoja();
				hoja = new NodoHoja(nuevoAux, sub1.getTipo(), nuevoAux, true);
				TablaSimbolos.getInstancia().altaEnTS(nuevoAux, sub1.getTipo(), "Auxiliar");

				if (sub1.getTipo().equals("ui8")) {
					salida.append("MOV AL, " + sub1.getRefString() + "\n"
							+ "MUL " + sub2.getRefString() + "\n"
							+ "MOV " + nuevoAux + ", AL" + "\n");
				} else {
					salida.append("FLD " + sub1.getRefString() + "\n"
							+ "FMUL " + sub2.getRefString() + "\n"
							+ "FSTP " + nuevoAux + "\n");
				}
				break;
			}

			case "/": {

				salida.append(this.getIzq().getAssembler() + this.getDer().getAssembler());
				cantAux++;
				nuevoAux = "@aux" + cantAux;
				sub1 = getIzq().getHoja(); // Dividendo
				sub2 = getDer().getHoja(); // Divisor
				hoja = new NodoHoja(nuevoAux, sub1.getTipo(), nuevoAux, true);
				TablaSimbolos.getInstancia().altaEnTS(nuevoAux, sub1.getTipo(), "Auxiliar");
				if (sub1.getTipo().equals("ui8")) {
					salida.append("MOV AH, 0 \n" // se limpian los 8 bits de ax de mayor peso. (Pueden venir cargados
													// por
													// multiplicación.)
							+ "MOV AL, " + sub1.getRefString() + "\n"
							+ "DIV " + sub2.getRefString() + "\n"
							+ "MOV " + nuevoAux + ", AL" + "\n"); // AL -> AX / r8
				} else {
					salida.append("FLD " + sub1.getRefString() + "\n"
							+ "FDIV " + sub2.getRefString() + "\n"
							+ "FSTP " + nuevoAux + "\n");
				}
				break;
			}

			case "-": {
				salida.append(this.getIzq().getAssembler() + this.getDer().getAssembler());
				cantAux++;
				nuevoAux = "@aux" + cantAux;
				sub1 = getIzq().getHoja();
				sub2 = getDer().getHoja();
				hoja = new NodoHoja(nuevoAux, sub1.getTipo(), nuevoAux, true);
				TablaSimbolos.getInstancia().altaEnTS(nuevoAux, sub1.getTipo(), "Auxiliar");
				if (sub1.getTipo().equals("ui8")) {
					salida.append("MOV AL, " + sub1.getRefString() + "\n"
							+ "SUB AL, " + sub2.getRefString() + "\n"
							+ "JS errorResta" + "\n"
							+ "MOV " + nuevoAux + ", AL" + "\n");
				} else {
					salida.append("FLD " + sub1.getRefString() + "\n"
							+ "FSUB " + sub2.getRefString() + "\n"
							+ "FSTP " + nuevoAux + "\n");
				}
				break;
			}
			case "+": {
				salida.append(this.getIzq().getAssembler() + this.getDer().getAssembler());
				cantAux++;
				nuevoAux = "@aux" + cantAux;
				sub1 = getIzq().getHoja();
				sub2 = getDer().getHoja();
				hoja = new NodoHoja(nuevoAux, sub1.getTipo(), nuevoAux, true);
				TablaSimbolos.getInstancia().altaEnTS(nuevoAux, sub1.getTipo(), "Auxiliar");
				if (sub1.getTipo().equals("ui8")) {
					salida.append("MOV AL," + sub1.getRefString() + "\n"
							+ "ADD AL, " + sub2.getRefString() + "\n"
							+ "MOV " + nuevoAux + ", AL" + "\n");
				} else {
					salida.append("FLD " + sub1.getRefString() + "\n" // cargo en el tope de la pila el valor de izq
							+ "FADD " + sub2.getRefString() + "\n" // realizo la suma del tope de la pila con el valor
																	// de der
							+ "FST " + nuevoAux + "\n"
							+ "FSTP _AUXFLOAT" + "\n"
							+ "FLD _MAX" + "\n"
							+ "FCOM" + "\n"
							+ "FSTSW AX" + "\n"
							+ "SAHF" + "\n"
							+ "JA errorOverflow" + "\n"); // cargo el valor de la suma del tope de la pila hacia memoria
															// (el nuevo aux)
				}
				break;
			}

			case "=:": {

				salida.append(this.getIzq().getAssembler() + this.getDer().getAssembler());
				sub1 = getIzq().getHoja();
				sub2 = getDer().getHoja();

				if (sub1.getTipo().equals("ui8")) {
					salida.append("MOV AL, " + sub2.getRefString() + "\n"
							+ "MOV " + sub1.getRefString() + ", AL" + "\n");
				} else {
					salida.append("FLD " + sub2.getRefString() + "\n"
							+ "FSTP " + sub1.getRefString() + "\n");
				}
				break;
			}

			case "Invocación a función": { // falta recursion mutua chequear
				cantAux++;
				nuevoAux = "@aux" + cantAux;
				sub1 = getIzq().getHoja();
				salida.append(getDer().getAssembler());
				hoja = new NodoHoja(nuevoAux, sub1.getTipo(), nuevoAux, true);
				TablaSimbolos.getInstancia().altaEnTS(nuevoAux, sub1.getTipo(), "Auxiliar");
				if (ultimoLLamado != null) {
					salida.append("MOV EAX, _ultimoLlamado \n"
							+ "CMP EAX, " + sub1.getRefString() + "\n"
							+ "JE errorRecursion \n"
							+ "MOV EAX, " + ultimoLLamado + "\n"
							+ "MOV _ultimoLlamado, EAX \n");

				}
				ultimoLLamado = sub1.getRefString();
				salida.append("call " + sub1.getRefString() + "\n");

				if (sub1.getTipo().equals("ui8"))
					salida.append("MOV " + nuevoAux + ", AL" + "\n");
				else
					salida.append("FSTP " + nuevoAux + "\n");
				break;
			}

			case "Parametros": {
				NodoHoja primerParam = (NodoHoja) getIzq();
				NodoHoja segundoParam = (NodoHoja) getDer();
				if (primerParam != null)
					if (primerParam.getTipo().equals("ui8")) {
						salida.append("MOV " + primerParam.getRefParametroFormal().replace(".", "_") + ", "
								+ primerParam.getRefString()
								+ "\n");
					} else {
						salida.append("FLD " + primerParam.getRefString() + "\n"); // f64\
						salida.append("FSTP _" + primerParam.getRefParametroFormal().replace(".", "_") + "\n");
					}

				if (getDer() != null)
					if (getDer().getTipo().equals("ui8")) {
						salida.append("MOV " + segundoParam.getRefParametroFormal().replace(".", "_") + ", "
								+ segundoParam.getRefString()
								+ "\n");
					} else {
						salida.append("FLD " + segundoParam.getRefString() + "\n"); // f64
						salida.append("FSTP _" + segundoParam.getRefParametroFormal().replace(".", "_") + "\n"); // f64
					}
				break;
			}

			case "When": {
				salida.append(this.getDer().getAssembler()); // en el caso del when tenemos 2 hijos, a la izquierda la
																// cond
				// que ya esta contemplada en el arbol, y a la derecha el cuerpo
				break;
			}

			case "CuerpoIF": { // Si es nodo comun entonces es then / else
				salida.append(getIzq().getAssembler());

				String etiquetaElse = etiquetas.pop(); // aca se guarda la que se salta desde la condicion
				nuevaEtiqueta(); // jmp al final del bloque

				salida.append("JMP " + etiquetas.peek() + "\n" // JMP AL FINAL DEL BLOQUE DEL IF
						+ etiquetaElse + ":" + "\n");
				salida.append(getDer().getAssembler());
				salida.append(etiquetas.pop() + ":" + "\n");
				break;
			}
			case ">": {
				if (negado)
					salida.append(comparacion("JLE "));
				else
					salida.append(comparacion("JA "));
				break;
			}
			case "<": {
				if (negado)
					salida.append(comparacion("JGE "));
				else
					salida.append(comparacion("JB "));
				break;
			}

			case "=": {
				if (negado)
					salida.append(comparacion("JNE "));
				else
					salida.append(comparacion("JE "));
				break;
			}

			case "=!": {
				if (negado)
					salida.append(comparacion("JE "));
				else
					salida.append(comparacion("JNE "));
				break;
			}

			case ">=": {
				if (negado)
					salida.append(comparacion("JL "));
				else
					salida.append(comparacion("JAE "));
				break;
			}

			case "<=": {
				if (negado)
					salida.append(comparacion("JG "));
				else
					salida.append(comparacion("JBE "));
				break;
			}

			case "Sentencia": {
				if (getDer() != null)
					salida.append(getDer().getAssembler());
				if (getIzq() != null)
					salida.append(getIzq().getAssembler());
				break;
			}

			case "Cond": {
				salida.append(getIzq().getAssembler());
				break;
			}

			case "IF": {
				String etiqueta = nuevaEtiqueta();
				negado=true;
				salida.append(getIzq().getAssembler());
				negado=false;
				salida.append( getDer().getAssembler());
				break;
			}

			case "For": {
				// Por la estructura de la asignación del for siempre el id de la variable a
				// iterar estará ahi
				salida.append(getIzq().getAssembler()); // Se genera primero la asignacion
				String nuevaEString = nuevaEtiqueta();
				sentenciasFor.push(new SentenciaFor(nroEtq)); // Pila para toda la info del for, variable, etiquetas
																// next e incremento
				salida.append("JMP " + sentenciasFor.peek().getLabelCondition() + " \n"); // Se revisa la condicion
																							// luego de
																							// la
				// asignacion como requisito para
				// recorrer el cuerpo.
				salida.append(nuevaEString);
				sentenciasFor.peek().setVariable(getIzq().getIzq().getRefString());
				salida.append(getDer().getAssembler());
				salida.append(sentenciasFor.peek().getLabelNext() + ": \n"); // Label para breaks

				etiquetas.pop();
				sentenciasFor.pop();

				break;

			}

			case "Cuerpo For": {
				salida.append(getIzq().getAssembler() + getDer().getAssembler());
				break;
			}

			case "Incr decr": {
				salida.append(sentenciasFor.peek().getLabelIncrement() + ": \n"); // Label para continues
				sub1 = getIzq().getHoja();
				if (sub1.getLexema().charAt(0) == '+') {
					salida.append("MOV AL, " + "_" + sub1.getLexema().substring(1) + "\n"
							+ "ADD AL, " + sentenciasFor.peek().getVariable() + "\n"
							+ "MOV " + sentenciasFor.peek().getVariable() + ", AL" + "\n");
				} else {
					salida.append("MOV AL, " + sentenciasFor.peek().getVariable() + "\n"
							+ "SUB AL, " + "_" + sub1.getLexema().substring(1) + "\n"
							+ "MOV " + sentenciasFor.peek().getVariable() + ", AL" + "\n");
				}

				salida.append(getDer().getAssembler());
				break;
			}

			case "For con Tag": {
				sentenciasFor.peek().setTag(getIzq().getLexema());
				salida.append(getDer().getAssembler());

				break;
			}

			default: {
				salida.append(getDer().getAssembler() + getIzq().getAssembler());
				break;
			}
		}
		return salida.toString();

	}

	@Override
	public ArbolSintactico getHoja() {
		return hoja;
	}
}