package grafo;

import java.util.ArrayList;

public class Dijkstra {
    public static int[] dijkstra(int tam, ArrayList<Lista> grafo, int org) throws CloneNotSupportedException {
        int[] dist = new int[tam];
        for(int i=0; i<tam; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[org] = 0;

        Cola prioridad = new Cola();
        prioridad.push(org, 0);

        while(!prioridad.isEmpty()){
            NodoDJ temp = prioridad.pop();
            for(int i=0; i<grafo.get(temp.getVertice()).getCont(); i++){
                NodoDJ n = grafo.get(temp.getVertice()).clone().getPos(i);
                if(dist[temp.getVertice()] + n.getDistancia() < dist[n.getVertice()]){
                    dist[n.getVertice()] = n.getDistancia() + dist[temp.getVertice()];
                    prioridad.push(n.getVertice(), dist[n.getVertice()]);
                }
            }
        }

        return dist;

    }
}
