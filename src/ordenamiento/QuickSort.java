package ordenamiento;

import arbol.Nodo;

public class QuickSort {
    private static void swap(Nodo[] arr, int i, int j){
        Nodo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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

    public static void quickSort(Nodo[] arr, int low, int high){
        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void quickSort(Nodo[] arr){
        int n = arr.length;
        quickSort(arr, 0, n-1);
    }

}
