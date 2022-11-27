package arbol;
import javax.swing.*;

public class Pila implements Cloneable{
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

    public void push(Nodo pass){
        Nodo temp = new Nodo(pass.tel, pass.nombre, pass.inicio, pass.fin);
        if (temp == null) {
            System.out.print("\nHeap Overflow");
            return;
        }
        temp.right = top;
        top = temp;
        cant++;
    }

    public void pop(){
        if (top == null) {
            return;
        }
        top = (top).right;
    }

    public void print(JTextPane console){
        Nodo temp = top;
        while(temp != null){
            //System.out.println(temp.nombre);
            console.setText(console.getText() + "\n" + temp.nombre + "     "
                    + Conversion.timeToString(temp.inicio) + " - " + Conversion.timeToString(temp.fin));
            temp = temp.right;
        }
    }

    public Nodo getIndex(int i){
        Nodo temp = top; int index = 0;
        while(temp != null){
            //System.out.println(temp.nombre);
            if(index == i){
                return temp;
            }
            index++;
            temp = temp.right;
        }
        return null;
    }

    public void print2(){
        Nodo temp = top;
        while(temp != null){
            System.out.println(temp.nombre);
            temp = temp.right;
        }
    }

    public Pila clone() throws CloneNotSupportedException {
        return (Pila) super.clone();
        /*Pila clone = (Pila) super.clone();
        return clone;*/
    }

}
