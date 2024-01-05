package AnalizadorLexico;

import java.util.ArrayList;
import java.util.List;

import AnalizadorLexico.AccionSemantica.AccionSemantica;

public class AnalizadorLexico {

    // Info necesaria, matrices
    private final static int CANTIDAD_FILAS = 14;
    private final static int CANTIDAD_COLUMNAS = 20;

    private static int[][] matrizEstados = FileHandler.leerMatriz("MatrizEstados.txt", CANTIDAD_FILAS,
            CANTIDAD_COLUMNAS);
    private static AccionSemantica[][] matrizAcciones = FileHandler.leerMatrizAccion("matrizAccionesSemanticas.txt",
            CANTIDAD_FILAS, CANTIDAD_COLUMNAS);
    private static String codigo;

    // cambios de estado y recorrido de codigo
    public static int posEnCodigo = 0;
    public static int numeroLinea = 1;
    public static Token tokenActual = new Token();

    public static String erroresLexicos = "";

    public static int EstadoActual;
    public static int EstadoAnterior;
    private static final int estadoFinal = 13;

    private static List<Token> tokenReconocidos;
    
    public static List<Token> getTokenReconocidos() {
        return tokenReconocidos;
    }

    public AnalizadorLexico(String path) {
        
        codigo = FileHandler.codigoALista(path);
        obtenerTokens();
       
    }

    public static Token obtenerToken() {
        tokenActual = new Token(numeroLinea);
       // System.out.println("Nuevo token comenzado nro linea: " + numeroLinea);
        EstadoActual = 0;

        while (EstadoActual != estadoFinal && EstadoActual != -1 && posEnCodigo != codigo.length()) {

            char actual = codigo.charAt(posEnCodigo);
            nuevaLinea(actual);
            // System.out.println("Estado " + EstadoActual + ". Caracter actual: " + actual);

            
             matrizAcciones[EstadoActual][getColumna(actual)].ejecutar(actual);

            EstadoActual = siguienteEstado(EstadoActual, actual);

             //System.out.println("Nuevo estado: " + EstadoActual);

            posEnCodigo++;

            
        }

        if (posEnCodigo == codigo.length() || tokenActual.getId() == 0) {
            return null;
        }
        return tokenActual;

    }

    private static void nuevaLinea(char c) {
        if (c == '\n') {
            numeroLinea++;
        }
    }


    private static int siguienteEstado(int estadoActual, char charAt) {
        return matrizEstados[estadoActual][getColumna(charAt)];
    }

    private static int getColumna(char c) { // devuelve que columna dependiendo del caracter.
        ;
        switch (c) {
            case '<':
                return 2;
            case '>':
                return 3;
            case '\'':
                return 4;
            case '.':
                return 5;
            case 'D':
                return 6;
            case '+':
                return 7;
            case ' ':// Caracter vacio?
                return 8;
            case '=':
                return 9;
            case '!':
                return 10;
            case '*':
                return 11;
            case '/':
                return 12;
            case '(':
                return 13;
            case ')':
                return 13;
            case '{':
                return 13;
            case '}':
                return 13;
            case ',':
                return 14;
            case ';':
                return 15;
            case '-':
                return 16;
            case (char) 10: // \n
                return 17;
            case '\t': // Tabulacion
                return 17;
            case ':':
                return 18;
            case '_':
                return 19;
            default:
                int ascii = (int) c;
                if ((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                    return 0;
                } else if (ascii >= 48 && ascii <= 57) {
                    return 1;
                }
                System.out.println(c);
                return -1;
        }

    }

    public static void AgregarError(String string) {
        erroresLexicos = erroresLexicos + '\n' + string;

    }

    public static void obtenerTokens() {
        List<Token> aux = new ArrayList<Token>();

        while (codigo.length() != posEnCodigo) {
            Token actual = obtenerToken();
            if (actual != null) {
                aux.add(actual);
            }
        }
        
        tokenReconocidos=aux;
    }

    

}
