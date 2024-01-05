package AnalizadorLexico.AccionSemantica;

import java.util.HashMap;
import java.util.Map;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Constantes;

public class AccionSemanticaLiterales implements AccionSemantica {

    /*
     * (non-Javadoc)
     * 
     * @see AnalizadorLexico.AccionSemantica.AccionSemantica#ejecutar(char)
     */
    private static Map<Character, Integer> literales = new HashMap<Character, Integer>();

    /**
     * 
     */
    public AccionSemanticaLiterales() {
        literales.put('+', Constantes.SUM);
        literales.put('-', Constantes.MENOS);
        literales.put('*', Constantes.MULT);
        literales.put('/', Constantes.DIV);
        literales.put('(', Constantes.PARE_AB);
        literales.put(')', Constantes.PARE_CE);
        literales.put('{', Constantes.LLAVE_AB);
        literales.put('}', Constantes.LLAVE_CE);
        literales.put(',', Constantes.COMA);
        literales.put(';', Constantes.PUNTO_COMA);
        literales.put(':', Constantes.DOS_PUNTOS);
    }

    @Override
    public void ejecutar(char entrada) {
        AnalizadorLexico.tokenActual.setId(literales.get(entrada));
        AnalizadorLexico.tokenActual.setLexema(String.valueOf(entrada));
        

    }
}