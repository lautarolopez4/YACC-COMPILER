package AnalizadorLexico;

public class Parametro {

    private String id;
    private String tipo;
    private String referencia;

    public Parametro (String id, String tipo, String referencia) {
        this.id=id;
        this.tipo=tipo;
        this.referencia = referencia;
    }

    public String getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getReferencia() {
        return this.referencia;
    }
}