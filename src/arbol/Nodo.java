package arbol;

import java.io.Serializable;

public class Nodo implements Serializable {
    String tel;
    String nombre;
    int inicio, fin;
    Nodo left, right; //Para el arbol se usan ambos apuntadores, para la pila se usa uno solo

    //Constructor
    Nodo(String tel, String nombre, int inicio, int fin){
        this.tel = tel;
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
        this.left = null;
        this.right = null;
    }

    public String getTel() {
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

    //tiempo es el resultado entre el tiempo de fin y tiempo de inicio de cada nodo
    public int getTiempo(){
        return fin-inicio;
    }

}
