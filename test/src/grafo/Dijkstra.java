package grafo;

import java.util.ArrayList;

public class Dijkstra {

    //El algoritmo de dijkstra encuentra el camino mas corto entre el nodo de origen y el nodo final
    //Recibe como parametro principalmente una matriz de adjacencia formada por una lista de listas
    //Ver semana 12 de Estructuras de Datos para mas informacion
    public static int[] dijkstra(int tam, ArrayList<Lista> grafo, int org) throws CloneNotSupportedException {
        int[] dist = new int[tam];          //Arreglo para administrar los nodos visitados y no visitados
        for(int i=0; i<tam; i++){
            dist[i] = Integer.MAX_VALUE;    //Inicializar todos los elementos del arreglo como distancia infinita
        }
        dist[org] = 0;                      //El origen tiene una distancia de 0 desde el origen

        Cola prioridad = new Cola();        //Cola de prioridad que almacenara aristas; la primera sera la proxima a visitar
        prioridad.push(org, 0);          //Inicializar el primer elemento de la cola

        //Hasta que la cola de prioridad se quede sin elementos que revisar
        while(!prioridad.isEmpty()){
            NodoDJ temp = prioridad.pop();  //Se visita el primer elemento de la cola de prioridad
            //Se lee: Para cada elemento de la lista de aristas en la posicion temp.getVertice del ArrayList...
            for(int i=0; i<grafo.get(temp.getVertice()).getCont(); i++){
                NodoDJ n = grafo.get(temp.getVertice()).clone().getPos(i);                  //Obtener arista[i]
                //Si es la mejor distancia hasta ahora...
                if(dist[temp.getVertice()] + n.getDistancia() < dist[n.getVertice()]){
                    dist[n.getVertice()] = n.getDistancia() + dist[temp.getVertice()];      //Actualizar dist[n.vertice]
                    prioridad.push(n.getVertice(), dist[n.getVertice()]);                   //Encolar elemento por visitar
                }
            }
        }
        //Ver documentacion para una explicacion visual de ese bucle
        return dist;

    }
}
