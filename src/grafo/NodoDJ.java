package grafo;

import java.io.Serializable;

public class NodoDJ implements Serializable {
    private int vertice, distancia;
    private NodoDJ next;
    public NodoDJ(int v, int d){
        vertice = v;
        distancia = d;
        next = null;
    }
    public int getVertice(){
        return vertice;
    }
    public int getDistancia(){
        return distancia;
    }
    public NodoDJ getNext(){
        return next;
    }
    public void setNext(NodoDJ nx){
        next = nx;
    }
}
