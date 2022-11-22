package arbol;

import java.io.IOException;

public class Test {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Arbol arbol = new Arbol();
        //arbol.cargar("test");

        arbol.add(98, "ali", 13, 14);
        arbol.add(928, "cdv12li", 12, 15);
        arbol.add(91238, "fel355i", 11, 16);
        arbol.add(9548, "ouili", 18, 20);

        arbol.mostrar();
        System.out.println("arbol.buscar(\"ouili\").tel = " + arbol.buscar("ouili").tel);
        arbol.editar(2, "ali", "", 999, 0, 0);
        arbol.mostrar();
        System.out.println(" = ");
        Nodo[] arr = arbol.toArray();
        for(int i=0; i<arbol.getCant(); i++){
            System.out.println("arr[i].nombre = " + arr[i].nombre);
        }
        //arbol.guardar("test");
    }
}

