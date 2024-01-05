package AnalizadorLexico;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.swing.text.TabableView;

public class TablaSimbolos {
    static TablaSimbolos instancia;
    private static Hashtable<String, ElemTabla> tablaSimb = new Hashtable<String, ElemTabla>();

    private TablaSimbolos() {
        tablaSimb = new Hashtable<>();
    }

    public static TablaSimbolos getInstancia() {
        if (instancia == null) {
            instancia = new TablaSimbolos();
        }
        return instancia;
    }

    public boolean existeEnTS(String s) {
        return tablaSimb.containsKey(s);
    }

    public void altaEnTS(String referencia, String tipo, String uso) {
        if (!existeEnTS(referencia))
            tablaSimb.put(referencia, new ElemTabla(tipo, uso));
        else
            tablaSimb.get(referencia).aumentarCant();
    }

    public void bajaEnTS(String s) {
        if (existeEnTS(s))
            tablaSimb.remove(s);
    }

    public ElemTabla getElem(String s) {
        return tablaSimb.get(s);
    }

    public String getUso(String ref) {
        ElemTabla aux = tablaSimb.get(ref);
        if (aux != null) {
            return aux.getUso();
        }
        return null;
    }

    public String getTipo(String s) {
        ElemTabla aux = tablaSimb.get(s);
        if (aux != null) {
            return aux.getTipo();
        }
        return null;
    }

    public void setTipo(String s, String tipo) {

        if (existeEnTS(s))
            tablaSimb.get(s).setTipo(tipo);
    }

    public void setUso(String s, String uso) {
        tablaSimb.get(s).setUso(uso);
    }

    public void disminuir(String s) {
        tablaSimb.get(s).disminuirCant();
        if (tablaSimb.get(s).getCant() == 0)
            tablaSimb.remove(s);

    }

    public void aumentar(String s) {
        tablaSimb.get(s).aumentarCant();
    }

    public void setParametro1(String ref, Parametro p) {
        if (existeEnTS(ref))
            tablaSimb.get(ref).setParametro1(p);
    }

    public void setParametro2(String ref, Parametro p) {
        if (existeEnTS(ref))
            tablaSimb.get(ref).setParametro2(p);
    }

    public Parametro getParametro1(String ref) {
        if (existeEnTS(ref))
            return tablaSimb.get(ref).getParametro1();
        return null;
    }

    public Parametro getParametro2(String ref) {
        if (existeEnTS(ref))
            return tablaSimb.get(ref).getParametro2();
        return null;
    }

    @Override
    public String toString() {
        Enumeration<String> palabras = tablaSimb.keys();
        while (palabras.hasMoreElements()) {
            String key = palabras.nextElement();
            System.out.println("Simbolo: " + key + ":      Tipo: " + tablaSimb.get(key).getTipo() + " ---- Uso: "
                    + tablaSimb.get(key).getUso());
        }
        return "";
    }

    public int cantidadParametros(String idFun) {
        ElemTabla aux = tablaSimb.get(idFun);
        if (aux.getParametro1() != null && aux.getParametro2() != null) {
            return 2;
        } else if (aux.getParametro1() != null) {
            return 1;
        }
        return 0;

    }

    public Set<String> getClaves() {
        return tablaSimb.keySet();
    }

    public String getValor(String ref) {
        ElemTabla aux = tablaSimb.get(ref);
        if (aux != null) {
            return aux.getValor();
        }
        return null;
    }

    public void setValor(String ref, String lexema) {
        ElemTabla aux = tablaSimb.get(ref);
        if (aux != null) {
            aux.setValor(lexema);
        }
    }

}