package AnalizadorLexico.AccionSemantica;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Constantes;
import AnalizadorLexico.TablaSimbolos;

public class AccionSemanticaCadena implements AccionSemantica{

    @Override
    public void ejecutar(char entrada) {
        AnalizadorLexico.tokenActual.setId(Constantes.STRING);
        TablaSimbolos ts = TablaSimbolos.getInstancia();
        String lex = AnalizadorLexico.tokenActual.getLexema();
        ts.altaEnTS("str_"+lex,"String","Cadena");        
    } 
    
}
