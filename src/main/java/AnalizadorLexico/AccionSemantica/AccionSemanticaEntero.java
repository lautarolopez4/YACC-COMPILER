package AnalizadorLexico.AccionSemantica;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Constantes;
import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.Token;

public class AccionSemanticaEntero implements AccionSemantica {

    @Override
    public void ejecutar(char entrada) {

        Token act = AnalizadorLexico.tokenActual;
        String e = act.getLexema();
        int a;
        try {
            a = Integer.parseInt(e);
        } catch (RuntimeException ex) {
            a = 256;
        }
        act.setId(Constantes.ENTERO);
        if (a > 255) {
            AnalizadorLexico.AgregarError("El entero corto superó el tamaño maximo, se restringe a 255");
            AnalizadorLexico.tokenActual.setLexema("255");
        

        }
        TablaSimbolos.getInstancia().altaEnTS(AnalizadorLexico.tokenActual.getLexema(), "ui8", "Constante");
        AnalizadorLexico.posEnCodigo--;
    
        if (entrada == '\n')
            AnalizadorLexico.numeroLinea--;
    }

}