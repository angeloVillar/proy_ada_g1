package horarios;

import arbol.Conversion;
import arbol.Nodo;
import arbol.Pila;
import ordenamiento.QuickSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Vector;
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

        //Aux[] dp = new Aux[n];
        //Arrays.fill(dp, new Aux());

        //dp[0].tiempo = arr[0].getTiempo();
        lis.get(0).setTiempo(arr[0].getTiempo());
        //dp[0].aux.push(arr[0]);
        lis.get(0).aux.push(arr[0]);
        //Arrays.fill(dp, 1, n, new Aux());

        //int[] tabla = new int[n];
        //tabla[0] = arr[0].getTiempo();

        for(int i=1; i<n; i++){
            int tiempo = arr[i].getTiempo();
            int l = busquedaBin(arr, i);
            if(l != -1){
                //tiempo += dp[l].tiempo;
                tiempo += lis.get(l).getTiempo();
            }
            if(tiempo > lis.get(i-1).tiempo /*tiempo > dp[i-1].tiempo && l!=-1*/ ){
                //dp[i].tiempo = tiempo;
                lis.get(i).setTiempo(tiempo);
                //Arrays.fill(dp, i+1, n, new Aux());
                //dp[i].aux = dp[l].aux;
                lis.get(i).setAux(lis.get(l).getAux().clone());
                //dp[i].aux.push(arr[i]);
                lis.get(i).getAux().push(arr[i]);

                //System.out.println("========");
                //dp[i].aux.print2();

            } else {
                //dp[i] = dp[i-1];
                lis.get(i).setTiempo(lis.get(i-1).getTiempo());
                lis.get(i).setAux(lis.get(i-1).getAux().clone());
            }
            //tabla[i] = Math.max(tiempo, tabla[i-1]);
        }

        //System.out.println("Maximo tiempo = "+dp[n-1].tiempo);
        console.setText(console.getText() + "\nMaximo tiempo = "+ Conversion.timeToString(lis.get(n-1).getTiempo())+" horas.");
        lis.get(n-1).getAux().print(console);

    }


    /*
    static class Aux{
        Vector<Nodo> aux;
        int tiempo;

        public Aux(){
            aux = new Vector<Nodo>();
            tiempo = 0;
        }

    }

    public static void maxContactos(Nodo[] arr){
        QuickSort.quickSort(arr);
        int n = arr.length;

        Aux[] dp = new Aux[n];
        Arrays.fill(dp, new Aux());
        dp[0].tiempo = arr[0].getTiempo();
        dp[0].aux.addElement(arr[0]);

        //int[] tabla = new int[n];
        //tabla[0] = arr[0].getTiempo();

        for(int i=1; i<n; i++){
            int tiempo = arr[i].getTiempo();
            int l = busquedaBin(arr, i);
            if(l != -1){
                //tiempo += tabla[l];
                tiempo += dp[l].tiempo;

                if(tiempo > dp[i-1].tiempo){
                    dp[i].tiempo = tiempo;
                    dp[i].aux = dp[l].aux;
                    dp[i].aux.addElement(arr[i]);
                } else {
                    dp[i] = dp[i-1];
                }

            }
            //tabla[i] = Math.max(tiempo, tabla[i-1]);

        }
        System.out.println("Maximo tiempo = "+dp[n-1].tiempo);
        for(int i=0; i<dp[n-1].aux.size(); i++){
            System.out.println(dp[n-1].aux.get(i).getNombre());
        }

    } */


}
