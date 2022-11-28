package horarios;

import arbol.*;
import ordenamiento.QuickSort;

import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.*;

public class MaxContactos {

    private static int busquedaBin(Nodo[] arr, int i){
        int min = 0, max = i-1;
        while(min <= max){
            int mid = (min + max)/2;
            if(arr[mid].getFin() <= arr[i].getInicio()){
                if(arr[mid+1].getFin() <= arr[i].getInicio()){
                    min = mid+1;
                } else {
                    return mid;
                }
            } else {
                max = mid-1;
            }
        }
        return -1;
    }



    static class Aux{
        Pila aux;
        int tiempo;

        public Aux(){
            aux = new Pila();
            tiempo = 0;
        }

        public Pila getAux() {
            return aux;
        }

        public void setAux(Pila aux) {
            this.aux = aux;
        }

        public int getTiempo() {
            return tiempo;
        }

        public void setTiempo(int tiempo) {
            this.tiempo = tiempo;
        }
    }

    public static void maxContactos(Nodo[] arr, JTextPane console) throws CloneNotSupportedException {
        QuickSort.quickSort(arr);
        int n = arr.length;

        LinkedList<Aux> lis = new LinkedList<>();
        for(int i=0; i<n; i++){
            lis.add(new Aux());
        }

        lis.get(0).setTiempo(arr[0].getTiempo());
        lis.get(0).aux.push(arr[0]);

        for(int i=1; i<n; i++){
            int tiempo = arr[i].getTiempo();
            int l = busquedaBin(arr, i);
            if(l != -1){
                tiempo += lis.get(l).getTiempo();
            }
            if(tiempo > lis.get(i-1).tiempo){
                lis.get(i).setTiempo(tiempo);
                lis.get(i).setAux(lis.get(l).getAux().clone());
                lis.get(i).getAux().push(arr[i]);

            } else {
                lis.get(i).setTiempo(lis.get(i-1).getTiempo());
                lis.get(i).setAux(lis.get(i-1).getAux().clone());
            }
        }
        console.setText(console.getText() + "\nMaximo tiempo = "+ Conversion.timeToString(lis.get(n-1).getTiempo())+" horas.\n");
        lis.get(n-1).getAux().print(console);
        console.setText(console.getText() + "\n");

    }

}
