package AnalizadorLexico.ArbolSintactico;

import java.util.ArrayList;
import java.util.List;

public class PilaAmbitos {
    private final List<String> ambitos = new ArrayList<>();
    private String ambitoGlobal;


    public PilaAmbitos (String AmbitoGlobal) {
        ambitos.add(AmbitoGlobal);
        this.ambitoGlobal = AmbitoGlobal;
    }

    public boolean estaEnAmbitoGlobal() {
        return getAmbitoActual().equals(ambitoGlobal);
    }

    public void agregarAmbito(String nuevoAmbito) {
        ambitos.add(nuevoAmbito);
    }

    public void eliminarUltimoAmbito() {
        ambitos.remove(ambitos.size() - 1); 
    }

    public String ultimoAmbito(){
       
        return ambitos.get(ambitos.size() - 1); 
    
    }

    public String getAmbitoActual() {
        String aux = "";
        boolean first = true;
        for (String ambito : ambitos) {
           if(first){
            aux = aux.concat(ambito);
            first = false;

        }
            else
            aux =aux.concat("."+ambito);
        }

        return aux; 
    }
}
