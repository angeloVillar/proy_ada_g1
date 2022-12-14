package ordenamiento;

import arbol.Nodo;

//Esta clase contiene la funcion quicksort que sera usada para ordenar un arreglo de nodos en MaxContactos
public class QuickSort {

    //Funcion para cambiar de lugar dos posiciones en el arreglo de nodos
    private static void swap(Nodo[] arr, int i, int j){
        Nodo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Funcion que define un pivote desde el cual se realizaran las comparaciones
    //Retorna un indice que sera usado por la funcion quicksort para partir el arreglo de nodos de forma logica
    private static int partition(Nodo[] arr, int low, int high){

        Nodo pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (arr[j].getFin() < pivot.getFin()) {

                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    //quicksort es un algoritmo de ordenamiento eficiente
    //Empieza definiendo dos posiciones (low y high) al inicio y final del arreglo
    //Luego llama al metodo partition y posteriormente, a si mismo (recursividad)
    //Hay un llamado recursivo para ambas "mitades" del arreglo
    //Ver semana 7 de Estructuras de Datos para mas informacion
    public static void quickSort(Nodo[] arr, int low, int high){
        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    //Funcion para acceder a quicksort desde main
    public static void quickSort(Nodo[] arr){
        int n = arr.length;
        quickSort(arr, 0, n-1);
    }

}
