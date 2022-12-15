package arbol;
import javax.swing.*;

public class Pila implements Cloneable{
    private Nodo top;
    private int cant, tiempo;

    //Constructor
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

    //tiempo es el valor que se usara en MaxContactos para determinar la prioridad de cada Pila
    public void setTiempo(int t) {
        this.tiempo = t;
    }

    //Agrega un nodo en la cima con los datos del nodo en los parametros
    //Crea un nodo temporal, lo enlaza con una copia del nodo de la cima
    //Actualiza la cima para que sea igual a temp
    public void push(Nodo pass){
        Nodo temp = new Nodo(pass.tel, pass.nombre, pass.inicio, pass.fin);
        temp.right = top;
        top = temp;
        cant++;
    }

    //Metodo para eliminar el nodo de la cima, retorna ese nodo
    //Crea un nodo temporal y lo iguala al nodo de la cima
    //Actualiza el nodo de la cima para ser igual a su nodo siguiente (null)
    //Retorna el nodo temporal
    public Nodo pop(){
        if (top == null) {
            return null;
        }
        else
        {
            Nodo temp = top;
            top = top.right;
            return temp;
        }
    }

    //Imprime la lista en un JTextPane empezando desde la cima
    public void print(JTextPane console){
        Nodo temp = top;
        while(temp != null){
            //System.out.println(temp.nombre);
            console.setText("\n" + temp.nombre + "     "
                    + Conversion.timeToString(temp.inicio) + " - " +
                    Conversion.timeToString(temp.fin) + console.getText());
            temp = temp.right;
        }
    }

    //Recorre la pila una determinada cantidad de nodos
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

    //Metodo que retorna una copia de una instancia de Pila
    //Sirve para solucionar un problema que surgio debido al funcionamiento interno de java en MaxContactos
    public Pila clone() throws CloneNotSupportedException {
        return (Pila) super.clone();
        /*Pila clone = (Pila) super.clone();
        return clone;*/
    }

}
