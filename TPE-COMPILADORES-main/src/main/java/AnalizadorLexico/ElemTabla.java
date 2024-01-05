package AnalizadorLexico;

public class ElemTabla {
    private int cant;
    private String tipo;
    private String uso;
    private Parametro parametro1;
    private Parametro parametro2;
    private String valor;

    public ElemTabla(String tipo, String uso) {
        this.tipo = tipo;
        this.uso = uso;
        this.cant = 1;
        this.parametro1 = null;
        this.parametro2 = null;
    }


    public void setParametro1(Parametro p) {
        this.parametro1 = p;
    }

    public void setParametro2(Parametro p) {
        this.parametro2 = p;
    }

    public Parametro getParametro1() {
        return this.parametro1;
    }

    public Parametro getParametro2() {
        return this.parametro2;
    }

    public int getCant() {
        return this.cant;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getUso() {
        return this.uso;
    }

    public void setTipo(String t) {
        this.tipo = t;
    }

    public void setUso(String u) {
        this.uso = u;
    }

    public void aumentarCant() {
        cant++;
    }

    public void disminuirCant() {
        cant--;
    }

    public String toString() {
        return tipo + ", " + uso + ", " + cant;
    }

    public void setValor(String lexema) {
        this.valor = lexema;
    }
    public String getValor() {
        return this.valor;
    }

}
