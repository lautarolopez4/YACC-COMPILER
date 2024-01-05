package AnalizadorLexico.ArbolSintactico;

import java.util.Stack;

public abstract class ArbolSintactico {

	private ArbolSintactico hijoIzq, hijoDer;
	private String lexema;
	private String tipo;
	private String referencia;
	private String blanco = "  "; //tabulacion entre nodos
	protected static int nroEtq = 0;
	protected static int cantAux = 0;
	protected static String ultimoLLamado ;

	protected NodoHoja hoja;
	protected static Stack<String> etiquetas = new Stack<String>();
	protected static Stack<SentenciaFor> sentenciasFor = new Stack<SentenciaFor>();
	
	protected String nuevoAux;

	
	
	public ArbolSintactico(String lexema, String tipo) {
		this.lexema = lexema;
		this.tipo = tipo;
		hijoIzq = null;
		hijoDer = null;
	}

	public ArbolSintactico(String lexema, String tipo, String referencia) {
		this.lexema = lexema;
		this.tipo = tipo;
		this.referencia = referencia;
		hijoIzq = null;
		hijoDer = null;
	}

	public ArbolSintactico getIzq() {
		return this.hijoIzq;
	}

	public ArbolSintactico getDer() {
		return this.hijoDer;
	}

	public void setIzq(ArbolSintactico izq) {
		this.hijoIzq = izq;
	}

	public void setDer(ArbolSintactico der) {
		this.hijoDer = der;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String string) {
		this.tipo = string;
	}

	public String getRefString() {
		return referencia;
	}
	public void setRefString (String ref) {
		this.referencia = ref;
	}

	public String getLexema() {
		return lexema;
	}
	public void setLexema (String ref) {
		this.lexema = ref;
	}

	public String getBlanco() {
		return this.blanco;
	}

	protected String nuevaEtiqueta() {
		nroEtq++;
		String etiqueta = "etiqueta" + nroEtq;
		etiquetas.push(etiqueta);
		return etiqueta + ": \n";
	}

	public abstract String getAssembler();
	
	public abstract ArbolSintactico getHoja();

	public abstract void recorrerArbol (String blancoAcumulado);

}