package AnalizadorLexico.AccionSemantica;

import java.util.HashMap;
import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.Token;
import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Constantes;

public class AccionSemanticaIdent implements AccionSemantica {

    protected HashMap<String, Integer> reserved_words; // Mapa de <palabra,valor token> --> ej: ui8, 111

    public AccionSemanticaIdent() {
        reserved_words = new HashMap<String,Integer>();

        reserved_words.put("if", Constantes.IF);
        reserved_words.put("then", Constantes.THEN);
        reserved_words.put("else", Constantes.ELSE);
        reserved_words.put("end_if", Constantes.ENDIF);
        reserved_words.put("out", Constantes.OUT);
        reserved_words.put("fun", Constantes.FUN);
        reserved_words.put("return", Constantes.RETURN);
        reserved_words.put("break", Constantes.BREAK);
        reserved_words.put("when", Constantes.WHEN);
        reserved_words.put("for", Constantes.FOR);
        reserved_words.put("ui8", Constantes.UI8);
        reserved_words.put("f64", Constantes.F64);
        reserved_words.put("defer", Constantes.DEFER);
        reserved_words.put("tof64", Constantes.TOF64);
        reserved_words.put("const", Constantes.CONST);
        reserved_words.put("continue", Constantes.CONTINUE);
    }

    @Override
    public void ejecutar(char entrada) {
        Token act = AnalizadorLexico.tokenActual;
        if (reserved_words.containsKey(act.getLexema())) { // Existe la palabra reservada?
            act.setId(reserved_words.get(act.getLexema()));
        } else {
            act.setId(Constantes.ID);
            if (act.getLexema().length() > 25) {
                String ident = act.getLexema().substring(0, 24);
                AnalizadorLexico.AgregarError(
                        "WARNING: Se acortó el identificador por superar los 25 caracteres, resultando: " + ident);
                act.setLexema(ident);
            }

            //buscar uso y asignarlo en el alta
            
            TablaSimbolos.getInstancia().altaEnTS("id_"+act.getLexema(), "ID", "");
            act.setRef("id_"+act.getLexema());


        }
        AnalizadorLexico.posEnCodigo--;
        if (entrada == '\n')
            AnalizadorLexico.numeroLinea--;

    }

}
