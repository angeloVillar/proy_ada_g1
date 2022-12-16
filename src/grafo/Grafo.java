package grafo;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import arbol.Arbol;
import arbol.Nodo;
import ex.WrongInputException;

public class Grafo implements Serializable{
    int cant;

    //La matriz de adjacencia sera una lista de listas
    ArrayList<Lista> grafo;
    int origen;

    String[] nombres;

    //Constructor
    public Grafo(int cantidad, int org){
        cant = cantidad;
        grafo  = new ArrayList<>();
        for(int i=0; i<cant; i++) {
            grafo.add(new Lista());
        }
        origen = org;
        nombres = new String[cantidad];
        Arrays.fill(nombres, "");
    }

    public Grafo(){}

    //Insertar una nueva relacion entre nodos
    public void insert(int org, int v, int d) throws WrongInputException {
        if(org > cant-1 || org < 0 || v > cant-1 || v < 0){
            throw new WrongInputException("Formato incorrecto!");
        }
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

    public void caminoMasCortoNombres(JTextPane console) throws CloneNotSupportedException {
        int[] dist = Dijkstra.dijkstra(cant, grafo, origen);
        System.out.println("Vertice\tDistancia desde origen");
        console.setText("Vertice\tDistancia desde origen");
        for(int i=0; i<cant; i++){
            System.out.println(traducirOUT(i) + "\t" + dist[i]);
            console.setText(console.getText() + "\n" + traducirOUT(i) + "\t\t" + dist[i]);
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

    //Sirve para asignar un contacto a cada indice de un arreglo
    public void nombrar(String nm){
        if(!nombres[nombres.length-1].equals("")){
            System.out.println("Arreglo lleno");
            return;
        }
        for(int i=0; i<nombres.length; i++){
            if(nombres[i].equals("")){
                nombres[i] = nm;
                return;
            }
        }
    }

    //Devuelve el indice que le corresponde a un contacto en el arreglo traductor
    public int traducirIN(String nm){
        int i=0;
        for(String n : nombres){
            if(nm.equals(n)){
                return i;
            }
            i++;
        }
        return -1;
    }

    //Devuelve el nombre de un contacto en determinado indice del arreglo traductor
    public String traducirOUT(int nm){
        return nombres[nm];
    }

    //Imprimir el grafo
    public void mostrar(JTextPane console){
        if(cant==0 || grafo==null){return;}
        console.setText("");
        console.setText(console.getText() + 0 + " -> | " + grafo.get(0).print(true, this));
        for(int i=1; i<cant; i++){
            console.setText(console.getText() + "\n" + i + " -> | " + grafo.get(i).print(true, this));
        }
    }

    public void mostrarN(JTextPane console){
        if(cant==0 || grafo==null){return;}
        console.setText("");
        console.setText(console.getText() + traducirOUT(0) + " -> | " + grafo.get(0).print(false, this));
        for(int i=1; i<cant; i++){
            console.setText(console.getText() + "\n" + traducirOUT(i) + " -> | " + grafo.get(i).print(false, this));
        }
    }

}
