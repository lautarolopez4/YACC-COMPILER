package AnalizadorLexico.AccionSemantica;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.AnalizadorSintactico.Parser;

public class AccionSemanticaError implements AccionSemantica {

    @Override
    public void ejecutar(char entrada) {
        if (entrada != '\n')
            AnalizadorLexico.AgregarError("Caracter '" + entrada + "' no esperado en la linea "
                    + AnalizadorLexico.numeroLinea);
        else
            AnalizadorLexico.AgregarError("Salto de linea no esperado en la linea "
                    + AnalizadorLexico.numeroLinea);
        AnalizadorLexico.tokenActual.setId(Parser.YYERRCODE);
    }
}
