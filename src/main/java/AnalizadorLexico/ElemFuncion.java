package AnalizadorLexico;

import AnalizadorLexico.ArbolSintactico.ArbolSintactico;
import AnalizadorLexico.ArbolSintactico.NodoComun;

public class ElemFuncion {
    private ArbolSintactico sentenciasDiferidas;
    private ArbolSintactico cuerpoFuncion;
    private boolean retorno;
    private String id;
    
    public ArbolSintactico getSentenciasDiferidas() {
        return sentenciasDiferidas;
    }
    public void setSentenciasDiferidas(ArbolSintactico sentenciasDiferidas) {
        this.sentenciasDiferidas = sentenciasDiferidas;
    }
    public ArbolSintactico getCuerpoFuncion() {
        return cuerpoFuncion;
    }
    public void setCuerpoFuncion(ArbolSintactico cuerpoFuncion) {
            this.cuerpoFuncion = cuerpoFuncion;
    }
    public boolean isRetorno() {
        return retorno;
    }
    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
}