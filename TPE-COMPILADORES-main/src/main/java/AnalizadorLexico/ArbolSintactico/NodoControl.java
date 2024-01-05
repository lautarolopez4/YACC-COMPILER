package AnalizadorLexico.ArbolSintactico;

import AnalizadorLexico.TablaSimbolos;

public class NodoControl extends ArbolSintactico {

	public static String outData = "";

	public NodoControl(String lexema, ArbolSintactico nodo) {
		super(lexema, "");
		super.setIzq(nodo); // como es un nodo de control solo tiene un hijo, y por default es el de la
							// izquierda
	}

	@Override
	public String getTipo() {
		return getIzq().getTipo();
	}

	@Override
	public void recorrerArbol(String blancoAcumulado) {
		String operacion = this.getLexema();
		System.out.println(blancoAcumulado + operacion);
		System.out.println(getBlanco() + blancoAcumulado + "Hijo:");
		if (this.getIzq() != null)
			getIzq().recorrerArbol(getBlanco() + blancoAcumulado);
	}

	@Override
	public String getAssembler() {
		System.out.println("Assembler Nodo Control, entrada : " + this.getLexema());
		StringBuilder salida = new StringBuilder();
		String entrada = this.getLexema();
		switch (entrada) {
			case "Retorno": {
				salida.append(getIzq().getAssembler());
				ArbolSintactico a = getIzq().getHoja();
				if (a.getTipo().equals("ui8"))
					salida.append("MOV AL, " + a.getRefString() + " \n");
				else
					salida.append("FLD " + a.getRefString() + "\n");

				salida.append("ret  \n");
				break;
			}
			case "Sentencia out": {
				if (getIzq().getTipo().equals("str")) {
					cantAux++;
					nuevoAux = "@aux" + cantAux;
					outData += nuevoAux + " db \"" + getIzq().getLexema() + "\", 0\n";
					TablaSimbolos.getInstancia().altaEnTS(nuevoAux, "str", "Auxiliar");
					salida.append( "invoke StdOut, addr " + nuevoAux + " \n");
				} else if (getIzq().getTipo().equals("f64")) {

					salida.append("invoke printf, cfm$(\"%.20Lf\\n\"), " + getIzq().getRefString() + "\n");
				} else if (getIzq().getTipo().equals("ui8"))
					salida.append("invoke printf, cfm$(\"%.hu\\n\"), " + getIzq().getRefString() + "\n");
				break;
			}
			case "CuerpoIF": {
				salida.append(this.getIzq().getAssembler());
				salida.append(etiquetas.pop() + ": \n");
				break;
			}
			case "Casteo TOF64": { // tengo un dato de 8 bits y tengo que llevarlo a 16bits para poder hacer la
									// conversion de entero a flotante (16-->32)
				salida.append(getIzq().getAssembler());
				cantAux++;
				nuevoAux = "@aux" + cantAux;
				hoja = new NodoHoja(nuevoAux, "f64", nuevoAux, true);
				// setIzq(hoja);
				TablaSimbolos.getInstancia().altaEnTS(nuevoAux, "f64", "Auxiliar");

				salida.append("MOV BL, " + getIzq().getHoja().getRefString() + "\n");
				setIzq(hoja);
				salida.append("MOV BH, 0" + "\n" // extiende el signo de AL en AX, 8 --> 16 bits
						+ "MOV @aux_conversion, BX" + "\n"
						+ "FILD " + "@aux_conversion" + "\n"// conversion de entero de 16bits a float
						+ "FSTP " + nuevoAux + " \n");
				break;
			}
			case "Break con TAG": {
				String tag = getIzq().getLexema();
				salida.append("JMP " + getLabelFor(tag) + " \n ");
				break;
			}
			case "Condicion": {
				salida.append(sentenciasFor.peek().getLabelCondition() + ": \n");
				salida.append(getIzq().getAssembler());
				break;
			}

			case "CondicionIF": {
				salida.append(getIzq().getAssembler());
				break;
			}
			default: {

				salida.append(this.getIzq().getAssembler());
				break;
			}
		}
		return salida.toString();
	}

	public String getLabelFor(String tag) {
		for (SentenciaFor item : sentenciasFor) {
			if (item.contieneTag(tag))
				return item.getLabelNext();
		}
		return null;
	}

	@Override
	public ArbolSintactico getHoja() {
		return getIzq().getHoja();
	}

}