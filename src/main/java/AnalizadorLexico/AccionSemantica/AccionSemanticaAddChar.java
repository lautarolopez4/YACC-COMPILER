package AnalizadorLexico.AccionSemantica;

import AnalizadorLexico.AnalizadorLexico;


public class AccionSemanticaAddChar implements AccionSemantica {

    @Override
    public void ejecutar(char entrada) {
        AnalizadorLexico.tokenActual.addCharLexema(entrada);
        
    }

    
}
