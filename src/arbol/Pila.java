package arbol;

public class Pila {
    private Nodo top;
    private int cant, tiempo;

    public Pila(){
        top = null;
        cant = 0;
    }

    public int getCant() {
        return cant;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int t) {
        this.tiempo = t;
    }

    public void push(Nodo pass)
    {
        Nodo n = new Nodo(pass.tel, pass.nombre, pass.inicio, pass.fin);
        if (top == null) {
            top = n;
            return;
        }
        Nodo temp = top;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = n;
        cant++;
    }

    public void print(){
        Nodo temp = top;
        while(temp != null){
            System.out.println(temp.nombre);
            temp = temp.right;
        }
    }

}
