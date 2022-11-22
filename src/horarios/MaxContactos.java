package horarios;

import arbol.Nodo;
import arbol.Pila;
import ordenamiento.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

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

    }

    public static void maxContactos(Nodo[] arr){
        QuickSort.quickSort(arr);
        int n = arr.length;

        Aux[] dp = new Aux[n];
        Arrays.fill(dp, new Aux());
        dp[0].tiempo = arr[0].getTiempo();
        dp[0].aux.push(arr[0]);

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
                    dp[i].aux.push(arr[i]);
                } else {
                    dp[i] = dp[i-1];
                }

            }
            //tabla[i] = Math.max(tiempo, tabla[i-1]);

        }
        System.out.println("Maximo tiempo = "+dp[n-1].tiempo);
        dp[n-1].aux.print();

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
