package arbol;

import java.io.Serializable;

public class Nodo implements Serializable {
    int tel;
    String nombre;
    int inicio, fin;
    Nodo left, right;

    Nodo(int tel, String nombre, int inicio, int fin){
        this.tel = tel;
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
        this.left = null;
        this.right = null;
    }

    public int getTel() {
        return tel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getInicio() {
        return inicio;
    }

    public int getFin() {
        return fin;
    }
}
