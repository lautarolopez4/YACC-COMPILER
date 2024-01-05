package AnalizadorLexico.AccionSemantica;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.Constantes;
import AnalizadorLexico.TablaSimbolos;

public class AccionSemanticaDobles implements AccionSemantica {

    @Override
    public void ejecutar(char entrada) {
        String parteDecimal;
        String parteExponente;
        if (AnalizadorLexico.tokenActual.getLexema().contains("D")) {
            String[] partes = AnalizadorLexico.tokenActual.getLexema().split("D");// separo el lexema que voy a comparar
                                                                                  // usando la d de separador entre la
                                                                                  // parte
                                                                                  // entera y la decimal
            parteDecimal = partes[0];
            parteExponente = partes[1];

            if (parteExponente.contains("+")) {
                parteExponente.replace("+", "");
            }
        } else {
            parteDecimal = AnalizadorLexico.tokenActual.getLexema();
            parteExponente = "0";
        }
        try {
            double dec = Double.parseDouble(parteDecimal); // LEER ANOTACIONES, se optó por la solución de acortar el
                                                           // error perdiendo precisión
            int exp = Integer.parseInt(parteExponente);
            if (dec != 0.0
                    && ((dec >= 2.225073858507202 && exp <= -308) || (dec <= 1.797693134862316 && exp >= 308))) {
                AnalizadorLexico.AgregarError("Error en la conversión del doble.");
            } else {
                AnalizadorLexico.tokenActual.setId(Constantes.DOBLE);
                TablaSimbolos.getInstancia().altaEnTS(AnalizadorLexico.tokenActual.getLexema(), "f64", "Constante");
            }
            if (entrada == '\n')
                AnalizadorLexico.numeroLinea--;
            AnalizadorLexico.posEnCodigo--;
        } catch (Exception e) {
            AnalizadorLexico.AgregarError("Error en la conversión del doble.");
        }

    }
}