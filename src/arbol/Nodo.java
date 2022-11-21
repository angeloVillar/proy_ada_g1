package arbol;

public class Nodo {
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

}
