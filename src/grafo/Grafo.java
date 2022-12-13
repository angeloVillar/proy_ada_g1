package grafo;

import java.util.ArrayList;

public class Grafo {
    int cant;
    ArrayList<Lista> grafo;
    int origen;

    public Grafo(int cantidad, int org){
        cant = cantidad;
        grafo  = new ArrayList<>();
        for(int i=0; i<cant; i++) {
            grafo.add(new Lista());
        }
        origen = org;
    }

    public void insert(int org, int v, int d){
        grafo.get(org).addFinal(v, d);
    }

    public void caminoMasCorto() throws CloneNotSupportedException {
        int[] dist = Dijkstra.dijkstra(cant, grafo, origen);
        System.out.println("Vertice\tDistancia desde origen");
        for(int i=0; i<cant; i++){
            System.out.println(i + "\t" + dist[i]);
        }
    }

    public int getCant() {
        return cant;
    }

    public int getOrigen() {
        return origen;
    }

    public ArrayList<Lista> getGrafo() {
        return grafo;
    }
}
