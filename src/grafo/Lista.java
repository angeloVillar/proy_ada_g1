package grafo;

import java.io.Serializable;

public class Lista implements Cloneable, Serializable {
    private NodoDJ base;
    private int cont;

    public Lista(){
        base = null;
        cont = 0;
    }

    public int getCont(){
        return cont;
    }

    public void addFinal(int v, int d){
        NodoDJ nuevo = new NodoDJ(v, d);
        nuevo.setNext(null);
        if(base == null){
            base = nuevo;
        }
        else{
            NodoDJ ulti = base;
            while(ulti.getNext() != null){
                ulti = ulti.getNext();
            }
            ulti.setNext(nuevo);
        }
        cont++;
    }

    public void addInicio(int v, int d){
        NodoDJ nuevo = new NodoDJ(v, d);
        if(base == null){
            base = nuevo;
        } else {
            nuevo.setNext(base);
            base = nuevo;
        }
    }

    public void eliminarInicio(){
        if(base != null){
            base = base.getNext();
        }
    }

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

    public NodoDJ getPos(int pos){
        if(base == null){
            return null;
        } else {
            NodoDJ temp = base;
            int i = 0; boolean flag = false;
            while (i != pos){
                temp = temp.getNext();
                i++; flag = true;
            }
            if(flag || i==pos){
                return temp;
            }
        }
        return null;
    }

    public Lista clone() throws CloneNotSupportedException {
        return (Lista) super.clone();
    }

}
