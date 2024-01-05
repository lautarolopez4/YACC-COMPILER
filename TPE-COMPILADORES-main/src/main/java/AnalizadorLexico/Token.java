package AnalizadorLexico;

public class Token {

    private int id;
    private String lexema = "";
    private int nroLinea;
    private String refTabla= "";

    public Token(int id, String lexema, int nroLinea) {
        this.id = id;
        this.lexema = lexema;
    }

    public Token() {
    }

    public Token(int numeroLinea) {
        this.nroLinea = numeroLinea;
    }

    public int getId() {
        return id;
    }
    public int getNroLinea(){
        return nroLinea;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLexema() {
        return lexema;
    }

    public void addCharLexema(char c) {
        this.lexema += c;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setRef(String refTabla) {
        this.refTabla = refTabla;
    }

    public String getRef() {
        return refTabla;
    }

    @Override
    public String toString() {
        if (lexema == "") {
            return "Token ID" + id + ", Nro linea : " + nroLinea;
        }

        return "Token ID" + id + ", Nro linea : " + nroLinea + " Lexema: " + lexema;
    }

    public void resetLexema() {
        lexema= "";
    }
}
