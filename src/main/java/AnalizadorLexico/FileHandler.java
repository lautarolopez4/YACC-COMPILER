package AnalizadorLexico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import AnalizadorLexico.AccionSemantica.*;
import AnalizadorLexico.AnalizadorSintactico.Parser;

public class FileHandler {

    /**
     * 
     */
    public static String codigoALista(String path) {

        try {
            StringBuilder theString = new StringBuilder();

            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                theString.append(scanner.nextLine());
                theString.append('\n');
            }
            scanner.close();
            return theString.toString();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado");
        }

        return null;

    }

    public static AccionSemantica[][] leerMatrizAccion(String string, int cantidadFilas, int cantidadColumnas) {
        AccionSemantica[][] aux = new AccionSemantica[cantidadFilas][cantidadColumnas];
        aux[0][0] = new AccionSemanticaAddChar();
        aux[0][1] = new AccionSemanticaAddChar();
        aux[0][2] = new AccionSemanticaVacia();
        aux[0][3] = new AccionSemanticaVacia();
        aux[0][4] = new AccionSemanticaVacia();
        aux[0][5] = new AccionSemanticaAddChar();
        aux[0][6] = new AccionSemanticaError();
        aux[0][7] = new AccionSemanticaLiterales();
        aux[0][8] = new AccionSemanticaResetToken();

        aux[0][9] = new AccionSemanticaVacia();
        aux[0][10] = new AccionSemanticaError();
        aux[0][11] = new AccionSemanticaLiterales();
        aux[0][12] = new AccionSemanticaLiterales();
        aux[0][13] = new AccionSemanticaLiterales();
        aux[0][14] = new AccionSemanticaLiterales();
        aux[0][15] = new AccionSemanticaLiterales();
        aux[0][16] = new AccionSemanticaLiterales();
        aux[0][17] = new AccionSemanticaResetToken();

        aux[0][18] = new AccionSemanticaLiterales();
        aux[0][19] = new AccionSemanticaError();

        aux[1][0] = new AccionSemanticaAddChar();
        aux[1][1] = new AccionSemanticaAddChar();
        for (int i = 2; i < cantidadColumnas - 2; i++)
            aux[1][i] = new AccionSemanticaIdent();
        aux[1][cantidadColumnas - 2] = new AccionSemanticaIdent();
        aux[1][cantidadColumnas - 1] = new AccionSemanticaAddChar();

        aux[2][0] = new AccionSemanticaOperadores();
        aux[2][1] = new AccionSemanticaOperadores();
        aux[2][2] = new AccionSemanticaVacia();
        for (int i = 3; i < cantidadColumnas; i++)
            aux[2][i] = new AccionSemanticaOperadores();

        for (int i = 0; i < cantidadColumnas; i++)
            aux[3][i] = new AccionSemanticaVacia();

        for (int i = 0; i < cantidadColumnas; i++)
            aux[4][i] = new AccionSemanticaVacia();

        aux[4][3] = new AccionSemanticaResetToken();

        for (int i = 0; i < 4; i++)
            aux[5][i] = new AccionSemanticaAddChar();
        aux[5][4] = new AccionSemanticaCadena();
        for (int i = 5; i < cantidadColumnas; i++)
            aux[5][i] = new AccionSemanticaAddChar();
        aux[5][17] = new AccionSemanticaError();

        aux[6][0] = new AccionSemanticaEntero();
        aux[6][1] = new AccionSemanticaAddChar();
        for (int i = 2; i < cantidadColumnas; i++)
            aux[6][i] = new AccionSemanticaEntero();
        aux[6][5] = new AccionSemanticaAddChar();

        aux[7][0] = new AccionSemanticaError();
        aux[7][1] = new AccionSemanticaAddChar();
        for (int i = 2; i < cantidadColumnas; i++)
            aux[7][i] = new AccionSemanticaError();

        aux[8][0] = new AccionSemanticaDobles();
        aux[8][1] = new AccionSemanticaAddChar();
        for (int i = 2; i < cantidadColumnas; i++)
            aux[8][i] = new AccionSemanticaDobles();
        aux[8][6] = new AccionSemanticaAddChar();

        aux[9][0] = new AccionSemanticaError();
        aux[9][1] = new AccionSemanticaAddChar();
        for (int i = 2; i < 7; i++)
            aux[9][i] = new AccionSemanticaError();
        aux[9][7] = new AccionSemanticaAddChar();
        for (int i = 8; i < cantidadColumnas - 4; i++)
            aux[9][i] = new AccionSemanticaError();
        aux[9][cantidadColumnas - 4] = new AccionSemanticaAddChar();
        aux[9][cantidadColumnas - 3] = new AccionSemanticaError();
        aux[9][cantidadColumnas - 2] = new AccionSemanticaError();
        aux[9][cantidadColumnas - 1] = new AccionSemanticaError();

        aux[10][0] = new AccionSemanticaDobles();
        aux[10][1] = new AccionSemanticaAddChar();
        for (int i = 2; i < cantidadColumnas; i++)
            aux[10][i] = new AccionSemanticaDobles();
        aux[10][5] = new AccionSemanticaError();

        for (int i = 0; i < cantidadColumnas; i++)
            aux[11][i] = new AccionSemanticaOperadores();

        for (int i = 0; i < cantidadColumnas; i++)
            aux[12][i] = new AccionSemanticaOperadores();

        return aux;
    }

    public static int[][] leerMatriz(String path, int CANTIDAD_FILAS_ACC, int CANTIDAD_COLUMNAS_ACC) {

        int[][] aux = new int[CANTIDAD_FILAS_ACC][CANTIDAD_COLUMNAS_ACC];
        aux[0][0] = 1;
        aux[0][1] = 6;
        aux[0][2] = 2;
        aux[0][3] = 11;
        aux[0][4] = 5;
        aux[0][5] = 7;
        aux[0][6] = -1;
        aux[0][7] = 13;
        aux[0][8] = 0;
        aux[0][9] = 12;
        aux[0][10] = -1;
        aux[0][11] = 13;
        aux[0][12] = 13;
        aux[0][13] = 13;
        aux[0][14] = 13;
        aux[0][15] = 13;
        aux[0][16] = 13;
        aux[0][17] = 0;
        aux[0][18] = 13;
        aux[0][19] = -1;

        aux[1][0] = 1;
        aux[1][1] = 1;
        for (int i = 2; i < CANTIDAD_COLUMNAS_ACC - 1; i++)
            aux[1][i] = 13;
        aux[1][19] = 1;

        aux[2][0] = 13;
        aux[2][1] = 13;
        aux[2][2] = 3;
        for (int i = 3; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[2][i] = 13;

        for (int i = 0; i < 3; i++)
            aux[3][i] = 3;
        aux[3][3] = 4;
        for (int i = 4; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[3][i] = 3;

        for (int i = 0; i < 3; i++)
            aux[4][i] = 3;

        aux[4][3] = 0;

        for (int i = 4; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[4][i] = 3;

        for (int i = 0; i < 4; i++)
            aux[5][i] = 5;
        aux[5][4] = 13;
        for (int i = 5; i < CANTIDAD_COLUMNAS_ACC - 3; i++)
            aux[5][i] = 5;
        aux[5][CANTIDAD_COLUMNAS_ACC - 3] = -1;
        aux[5][CANTIDAD_COLUMNAS_ACC - 2] = 5;
        aux[5][CANTIDAD_COLUMNAS_ACC - 1] = 5;

        aux[6][0] = 13;
        aux[6][1] = 6;
        aux[6][2] = 13;
        aux[6][3] = 13;
        aux[6][4] = 13;
        aux[6][5] = 8;
        for (int i = 6; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[6][i] = 13;

        aux[7][0] = -1;
        aux[7][1] = 8;
        for (int i = 2; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[7][i] = -1;

        aux[8][0] = 13;
        aux[8][1] = 8;
        aux[8][2] = 13;
        aux[8][3] = 13;
        aux[8][4] = 13;
        aux[8][5] = 13;
        aux[8][6] = 9;
        for (int i = 7; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[8][i] = 13;

        aux[9][0] = -1;
        aux[9][1] = 10;
        for (int i = 2; i < 7; i++)
            aux[9][i] = -1;
        aux[9][7] = 10;
        for (int i = 8; i < CANTIDAD_COLUMNAS_ACC - 4; i++)
            aux[9][i] = -1;
        aux[9][CANTIDAD_COLUMNAS_ACC - 4] = 10;
        aux[9][CANTIDAD_COLUMNAS_ACC - 3] = -1;
        aux[9][CANTIDAD_COLUMNAS_ACC - 2] = -1;
        aux[9][CANTIDAD_COLUMNAS_ACC - 1] = 13;

        aux[10][0] = 13;
        aux[10][1] = 10;
        for (int i = 2; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[10][i] = 13;

        aux[10][5] = -1;

        for (int i = 0; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[11][i] = 13;

        for (int i = 0; i < CANTIDAD_COLUMNAS_ACC; i++)
            aux[12][i] = 13;

        return aux;
    }

    public static void resultadoLexico(List<Token> lista, String path) {
        try {

            File myObj = new File(path.replace(".txt", "-lexico.txt"));
            myObj.createNewFile();
            FileWriter aWriter = new FileWriter(myObj);

            aWriter.append("TOKENS DETECTADOS ----------------------------------");
            aWriter.append('\n');
            for (Token ro : lista) {
                aWriter.append(ro.toString());
                aWriter.append('\n');
            }

            aWriter.append("ERRORES LEXICOS -----------------------------------");
            aWriter.append(AnalizadorLexico.erroresLexicos);

            aWriter.append('\n');
            aWriter.close();
            System.out.println("Creado archivo " + myObj.getName() + " exitosamente.");

        } catch (Exception e) {

        }

    }

    public static void resultadoSintactico(List<String> estructurasReconocidas, String path) {
        try {

            File myObj = new File(path.replace(".txt", "-sintactico.txt"));
            myObj.createNewFile();

            FileWriter aWriter = new FileWriter(myObj, false);

            aWriter.append("ESTRUCTURAS SINTACTICAS DETECTADAS ----------------------------------");
            aWriter.append('\n');
            for (String s : estructurasReconocidas) {
                aWriter.append(s);
                aWriter.append('\n');
            }
            aWriter.append("ERRORES SINTACTICOS -----------------------------------");
            aWriter.append('\n');
            aWriter.append(Parser.erroresSintacticos);

            aWriter.close();
            System.out.println("Creado archivo " + myObj.getName() + " exitosamente.");

        } catch (Exception e) {

        }

    }

    public static boolean existeArchivo(String path) {
        try {

            Scanner scanner = new Scanner(new File(path));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
