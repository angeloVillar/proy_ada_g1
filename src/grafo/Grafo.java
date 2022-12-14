package grafo;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Grafo implements Serializable{
    int cant;

    //La matriz de adjacencia sera una lista de listas
    ArrayList<Lista> grafo;
    int origen;

    //Constructor
    public Grafo(int cantidad, int org){
        cant = cantidad;
        grafo  = new ArrayList<>();
        for(int i=0; i<cant; i++) {
            grafo.add(new Lista());
        }
        origen = org;
    }

    //Insertar una nueva relacion entre nodos
    public void insert(int org, int v, int d){
        grafo.get(org).addFinal(v, d);
    }

    //Metodo que accede al algoritmo de dijkstra e imprime el resultado
    public void caminoMasCorto(JTextPane console) throws CloneNotSupportedException {
        int[] dist = Dijkstra.dijkstra(cant, grafo, origen);
        System.out.println("Vertice\tDistancia desde origen");
        console.setText("Vertice\tDistancia desde origen");
        for(int i=0; i<cant; i++){
            System.out.println(i + "\t" + dist[i]);
            console.setText(console.getText() + "\n" + i + "\t\t" + dist[i]);
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

    private static final long SerialVersionUID = 10L;

    //Metodo que serializa el objeto en un archivo de texto
    public static void guardar(Grafo g) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("grafo.txt"));
        out.writeObject(g);
        out.close();
        java.awt.Toolkit.getDefaultToolkit().beep();
        JOptionPane.showConfirmDialog(null, "El grafo fue exportado",
                "Contactos", JOptionPane.DEFAULT_OPTION);
    }

    //Metodo que deserializa el objeto encontrado en el archivo de texto
    public static Grafo cargar() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("grafo.txt"));
        Grafo g = (Grafo)in.readObject();
        in.close();
        java.awt.Toolkit.getDefaultToolkit().beep();
        JOptionPane.showConfirmDialog(null, "El grafo fue importado",
                "Contactos", JOptionPane.DEFAULT_OPTION);
        return g;
    }

}
