package AnalizadorLexico;

import java.io.File;
import java.io.FileWriter;

import AnalizadorLexico.AnalizadorSintactico.Parser;
import AnalizadorLexico.ArbolSintactico.ArbolSintactico;
import AnalizadorLexico.ArbolSintactico.NodoComun;
import AnalizadorLexico.ArbolSintactico.NodoControl;

public class GeneradorAssembler {
    private StringBuilder data;
    private StringBuilder code;
    private StringBuilder librerias = new StringBuilder();
    private StringBuilder codigo = new StringBuilder();
    private Parser parser;
    private ArbolSintactico arb;
    public static String codFuncion = "";

    /**
     * @param p
     */
    public GeneradorAssembler(Parser p) {
        data = new StringBuilder();
        code = new StringBuilder();
        this.librerias.append(".386 \n" // set de instrucciones
                + ".model flat, stdcall \n" // modelo de memoria para windows
                + ".stack 200h \n" // tamanio de la pila, inicializa stack en direccion indicada
                + "option casemap :none  \n"
                + "include \\masm32\\include\\windows.inc \n"
                + "include \\masm32\\include\\kernel32.inc \n"
                + "include \\masm32\\include\\user32.inc \n"
                + "include\\masm32\\include\\masm32rt.inc \n"
                + "dll_dllcrt0 PROTO C \n"
                + "printf PROTO C : VARARG \n"
                + "includelib \\masm32\\lib\\kernel32.lib \n" // includes de librerias de windows
                + "includelib \\masm32\\lib\\masm32.lib \n"
                + "includelib \\masm32\\lib\\user32.lib \n"); // las doble barras \\ son xq no deja utilizar 1 sola
        this.parser = p;
        if (p.getArbProg() != null)
            codigo.append(p.getArbProg().getAssembler()); // obtengo el codigo asociado (main)
    }

    public String genData() { // carga variables y datos provenientes de la tabla de simbolos
        StringBuilder salida = new StringBuilder();
        salida.append("\n.data \n"); // inicia .data
        TablaSimbolos ts = TablaSimbolos.getInstancia();
        for (String s : ts.getClaves()) {
            ElemTabla e = ts.getElem(s);
            String uso = e.getUso();

            if (uso.equals("Constante")) { // primero se declaran las constantes, prefijo '_'
                String aux = s.replace('.', '_');
                if (e.getTipo().equals("f64"))
                    data.append("_" + aux + " dq " + e.getValor() + "\n"); // si es doble utilizo dq que es de 64bits
                else
                    data.append("_" + aux + " db " + e.getValor() + "\n"); // si es entero corto utilizo db que es de
                                                                           // 8bits
            } else if (uso.equals("Variable") || uso.equals("Parametro") || uso.equals("Auxiliar")) { // declaracion de
                                                                                                      // variables, si
                                                                                                      // es var
                String pre = "";
                s = s.replace(".", "_");
                if (e.getUso().equals("Variable") || e.getUso().equals("Parametro"))
                    pre = "_";

                if (e.getTipo().equals("ui8"))
                    data.append(pre + s + " db ?  \n");
                else if (e.getTipo().equals("f64"))
                    data.append(pre + s + " dq ? \n");
            }
        }
        salida.append(NodoControl.outData + this.data);
        salida.append("msj_error_overflow_suma_flot db \"Error: Overflow.\", 0 \n" // definicion de cadenas, mensajes de
                                                                                   // error
                + "msj_error_resta_neg db \"Error: La resta dio como resultado un numero negativo.\", 0 \n"
                + "msj_error_recursion_mutua db \"Error: Recursion mutua en invocacion de funciones .\", 0 \n"
                + "_ultimoLlamado dd ? \n"
                + "@aux_conversion dw ? \n"
                + "_MAX dq 2,8339517666293232453490869445087r+78 \n"
                + "_AUXFLOAT dq ? \n");

        return salida.toString();
    }

    public String genCode() {
        code.append("\n.code\n" // inicia zona de codigo
                + "errorOverflow: \n"
                + "invoke MessageBox, NULL, addr msj_error_overflow_suma_flot, addr msj_error_overflow_suma_flot, MB_OK"
                + "\n"
                + "invoke ExitProcess, 1 \n"
                + "errorRecursion: \n"
                + "invoke MessageBox, NULL, addr msj_error_recursion_mutua, addr msj_error_recursion_mutua, MB_OK"
                + "\n"
                + "invoke ExitProcess, 1 \n"
                + "errorResta: \n"
                + "invoke MessageBox, NULL, addr msj_error_resta_neg, addr msj_error_resta_neg, MB_OK" + "\n"
                + "invoke ExitProcess, 1 \n"
                + parser.getFunciones()); // si el nodo es una funcion, el cual viene con el ret
        code.append("start:\n"); // indica el inicio del main
        code.append(codigo);
        code.append("invoke ExitProcess, 0" + "\n"
                + "end start \n"); // fin del main
        return code.toString();
    }

    public String getSalida() {
        String code = genCode();
        return this.librerias.toString() + this.genData() + code;
    }

    public void generarSalida(String nombreArchivo) {
        File archivo = new File(nombreArchivo + ".asm");
        FileWriter f;
        try {
            f = new FileWriter(archivo);
            f.append(getSalida());
            f.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}