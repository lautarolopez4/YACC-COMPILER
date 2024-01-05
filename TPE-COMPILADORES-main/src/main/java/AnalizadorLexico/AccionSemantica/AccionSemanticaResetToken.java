package AnalizadorLexico.AccionSemantica;

import AnalizadorLexico.Token;
import AnalizadorLexico.AnalizadorLexico;;


public class AccionSemanticaResetToken implements AccionSemantica {
    @Override
    public void ejecutar(char entrada) {
        AnalizadorLexico.tokenActual = new Token(AnalizadorLexico.numeroLinea);
    }
    // Para los estados que vuelven al 0 y deben resetear desde donde se lee el nuevo token

}
