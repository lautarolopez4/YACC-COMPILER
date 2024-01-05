package AnalizadorLexico.ArbolSintactico;

import AnalizadorLexico.TablaSimbolos;

public class NodoHoja extends ArbolSintactico {

	private boolean auxiliar;
	private String refParametroFormal;

	public NodoHoja(String lexema, String tipo, String referencia, boolean auxiliar) { // auxiliar
		super(lexema, tipo, referencia); // no tiene hijos
		this.auxiliar = auxiliar;
	}

	public NodoHoja(String lexema, String tipo, String referencia) { // Pertenece a variable, constante, etc
		super(lexema, tipo, referencia); // no tiene hijos
		auxiliar = false;
	}

	public NodoHoja(String lexema, String tipo) {
		super(lexema, tipo); // no tiene hijos
		auxiliar = false;
	}

	@Override
	public void recorrerArbol(String blancoAcumulado) {

		System.out.print(blancoAcumulado + this.getLexema());
		String tipo = this.getTipo();
		if (tipo!=null && !tipo.equals(""))
			System.out.print(". Tipo : " + tipo);
		System.out.print('\n');

	}

	@Override
	public String getRefString() {
		if (auxiliar)
			return super.getRefString().replace(".", "_");
		else
			return "_" + super.getRefString().replace(".", "_");
	}

	@Override
	public String getAssembler() {
		String salida = "";
		System.out.println("Nodo hoja : " + this.getLexema());
		switch (getLexema()) {
			case "Continue": {
				salida += "JMP " + sentenciasFor.peek().getLabelIncrement() + " \n ";
				break;
			}
			case "Break": {
				salida += "JMP " + sentenciasFor.peek().getLabelNext() + " \n ";
				break;
			}
			default:
				break;
		}
		return salida;
	}

	@Override
	public ArbolSintactico getHoja() {
		return this;
	}

	public String getRefParametroFormal() {
		return refParametroFormal;
	}

	public void setRefParametroFormal(String refParametroFormal) {
		this.refParametroFormal = refParametroFormal;
	}

}