package AnalizadorLexico.AccionSemantica;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Constantes;

public class AccionSemanticaOperadores implements AccionSemantica {

    // Según el caracter que haya ingresado y el estado donde estemos podremos
    // contempplar en que caso estamos.
    private void reducir(char entrada) {
        if (entrada == '\n')
            AnalizadorLexico.numeroLinea--;
        AnalizadorLexico.posEnCodigo--;

    }

    @Override
    public void ejecutar(char entrada) {

        int act = AnalizadorLexico.EstadoActual;

        if (act == 2) {
            if (entrada == '=') {
                AnalizadorLexico.tokenActual.setId(Constantes.MENOR_IGUAL);
                AnalizadorLexico.tokenActual.setLexema("<=");
            } else {
                AnalizadorLexico.tokenActual.setId(Constantes.MENOR);
                AnalizadorLexico.tokenActual.setLexema("<");
                reducir(entrada);
            }
        } else if (act == 11) {
            if (entrada == '=') {
                AnalizadorLexico.tokenActual.setId(Constantes.MAYOR_IGUAL);
                AnalizadorLexico.tokenActual.setLexema(">=");
            } else {
                reducir(entrada);
                AnalizadorLexico.tokenActual.setId(Constantes.MAYOR);
                AnalizadorLexico.tokenActual.setLexema(">");
            }
        } else if (act == 12) {
            if (entrada == '!') {
                AnalizadorLexico.tokenActual.setId(Constantes.DISTINTO);
                AnalizadorLexico.tokenActual.setLexema("=!");

            } else if (entrada == ':') {
                AnalizadorLexico.tokenActual.setId(Constantes.ASIGNACION);
                AnalizadorLexico.tokenActual.setLexema("=:");
            } else {
                AnalizadorLexico.tokenActual.setId(Constantes.IGUAL);
                AnalizadorLexico.tokenActual.setLexema("=");
                reducir(entrada);
            }
        }
    }

}
