package AnalizadorLexico.ArbolSintactico;

public class SentenciaFor {
    private String variable;
    private String etiquetaIncremento;
    private String etiquetaSiguiente;
    private String etiquetaCondicion;
    private String tag;

    SentenciaFor(int i) {
        this.etiquetaIncremento = "increment" + i;
        this.etiquetaSiguiente = "next" + i;
        this.etiquetaCondicion = "condition" + i;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getLabelCondition() {
        return etiquetaCondicion;
    }

    public String getLabelIncrement() {
        return etiquetaIncremento;
    }

    public String getLabelNext() {
        return etiquetaSiguiente;
    }

    public boolean contieneTag(String a) {
        return tag != null && tag.equals(a);
    }

    public void setTag(String lexema) {
        this.tag = lexema;
    }

}