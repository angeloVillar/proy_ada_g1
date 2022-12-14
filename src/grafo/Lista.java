package grafo;

import java.io.Serializable;

public class Lista implements Cloneable, Serializable {
    private NodoDJ base;
    private int cont;

    //Constructor
    public Lista(){
        base = null;
        cont = 0;
    }

    public int getCont(){
        return cont;
    }

    //Metodo que agrega un nodo al final de la lista
    public void addFinal(int v, int d){
        NodoDJ nuevo = new NodoDJ(v, d);
        nuevo.setNext(null);
        //Si la lista esta vacia
        if(base == null){
            base = nuevo;
        }
        else{
            NodoDJ ulti = base;
            //Busca el ultimo elemento
            while(ulti.getNext() != null){
                ulti = ulti.getNext();
            }
            ulti.setNext(nuevo);
        }
        cont++;
    }

    //Metodo para agregar un nodo al inicio de la lista (mas eficiente)
    public void addInicio(int v, int d){
        NodoDJ nuevo = new NodoDJ(v, d);
        //Si la lista esta vacia
        if(base == null){
            base = nuevo;
        } else {
            //Agregar al inicio (desplazar el primero)
            nuevo.setNext(base);
            base = nuevo;
        }
    }

    //Metodo para eliminar el primer nodo
    public void eliminarInicio(){
        if(base != null){
            base = base.getNext();
        }
    }

    //Metodo para mostrar los elementos de la lista (se uso para probar el algoritmo)
    public void print(){
        if(base == null){
            System.out.println("lista vacia");
        } else {
            System.out.println("Lista:");
            NodoDJ temp = base;
            while (temp != null){
                System.out.println(temp.getVertice() + " , " + temp.getDistancia());
                temp = temp.getNext();
            }
        }
    }

    //Metodo que retorna una copia del nodo en la posicion pos
    public NodoDJ getPos(int pos){
        //Si la lista esta vacia no retorna nada
        if(base == null){
            return null;
        } else {
            NodoDJ temp = base;
            //Recorre la lista durante pos nodos
            int i = 0; boolean flag = false;
            while (i != pos && temp!=null){
                temp = temp.getNext();
                i++;
            }
            //Si encuentra el nodo en esa posicion, se enciende el flag
            if(temp!=null){
                flag = true;
            }
            //Si se encontro el nodo exitosamente, se retorna dicho nodo
            if(flag || i==pos){
                return temp;
            }
        }
        return null;
    }

    //Metodo auxiliar que retorna una copia del objeto. Sirve para solucionar un error del lenguaje.
    public Lista clone() throws CloneNotSupportedException {
        return (Lista) super.clone();
    }

}
