package arbol;

public class Test {
    public static void main(String args[]){
        Arbol arbol = new Arbol();
        arbol.add(98, "ali", 13, 14);
        arbol.add(928, "cdv12li", 12, 15);
        arbol.add(91238, "fel355i", 11, 16);
        arbol.add(9548, "ouili", 18, 20);
        arbol.mostrar();
        System.out.println("arbol.buscar(\"ouili\").tel = " + arbol.buscar("ouili").tel);
        arbol.borrar("fel355i");
        arbol.mostrar();
    }
}

