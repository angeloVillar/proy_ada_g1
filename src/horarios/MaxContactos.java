package horarios;

import arbol.*;
import ordenamiento.QuickSort;

import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.*;

public class MaxContactos {

    //Funcion de busqueda binaria en un arreglo de nodos
    //Eficiente para encontrar el proximo nodo cuyo horario no choque con el nodo del indice
    //Retorna -1 si no encuentra un nodo que no choque
    //Solo funciona si el arreglo esta ordenado
    //Ver semana 5 de Analisis y Diseno de Algoritmos para mas informacion
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


    //Clase auxiliar que almacena una Pila y un entero que representa el tiempo acumulado actual
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

    //Esta funcion recibe un arreglo de nodos (contactos), los ordena usando quicksort y aplica programacion dinamica
    //para encontrar el optimo conjunto de contactos con los que se puede reunir sin choques de horarios
    //Ver semana 10 de Analisis y Diseno de Algoritmos para mas informacion
    public static void maxContactos(Nodo[] arr, JTextPane console) throws CloneNotSupportedException {
        //Ordena los nodos segun su tiempo de finalizacion
        QuickSort.quickSort(arr);
        int n = arr.length;

        //Inicializar lista de objetos tipo Aux que almacenaran los datos de la tabla
        //lis almacena los nodos seleccionados y el tiempo acumulado
        LinkedList<Aux> lis = new LinkedList<>();
        //Inicializar elementos de la lista
        for(int i=0; i<n; i++){
            lis.add(new Aux());
        }
        //Inicializar el primer elemento de la lista con los datos del primer elemento del arreglo
        lis.get(0).setTiempo(arr[0].getTiempo());
        lis.get(0).aux.push(arr[0]);

        //Bucle para llenar las listas
        for(int i=1; i<n; i++){
            int tiempo = arr[i].getTiempo();    //Variable de tiempo auxiliar
            int l = busquedaBin(arr, i);        //Encuentra el indice del sigueinte nodo cuyo horario no choque

            //Si lo encuentra, no retornara -1
            if(l != -1){
                tiempo += lis.get(l).getTiempo(); //Se le suma el tiempo acumulado de lis en la posicion l a la variable tiempo
            }

            //Si el tiempo acumulado actual es mayor que el de la posicion anterior de la lista...
            if(tiempo > lis.get(i-1).tiempo&&l>-1){
                lis.get(i).setTiempo(tiempo);                       //Se actualiza el tiempo de la lista en la posicion actual
                lis.get(i).setAux(lis.get(l).getAux().clone());     //Se reemplaza el Aux de la lista por la de la posicion l
                lis.get(i).getAux().push(arr[i]);                   //Se agrega el nodo[i] a la Pila en Aux en la lista en i

            } else {
                //Si no se cumple la condicion, se pasa el valor anterior a la posicon actual
                lis.get(i).setTiempo(lis.get(i-1).getTiempo());
                lis.get(i).setAux(lis.get(i-1).getAux().clone());
            }
        }
        //Se imprimen los resultados
        console.setText(console.getText() + "\nMaximo tiempo = "+ Conversion.timeToString(lis.get(n-1).getTiempo())+" horas.\n");
        lis.get(n-1).getAux().print(console);
        console.setText(console.getText() + "\n");

    }

}
