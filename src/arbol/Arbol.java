package arbol;

public class Arbol {
    private Nodo raiz;

    public int comparar(String s1, String s2){
        int comparisonResult = s1.compareTo(s2);
        return comparisonResult;
    }

    private Nodo addR(Nodo now, int tel, String nombre, int inicio, int fin){
        if(now == null){
            return new Nodo(tel, nombre, inicio, fin);
        }
        if(comparar(nombre, now.nombre) < 0){
            now.left = addR(now.left, tel, nombre, inicio, fin);
        } else if(comparar(nombre, now.nombre) > 0){
            now.right = addR(now.right, tel, nombre, inicio, fin);
        } else {
            return now;
        }
        return now;
    }

    public void add(int tel, String nombre, int inicio, int fin){
        raiz = addR(raiz, tel, nombre, inicio, fin);
    }

    private Nodo buscarR(Nodo now, String nombre){
        if(now == null){
            return null;
        }
        if(comparar(nombre, now.nombre) == 0){
            return now;
        }
        if(comparar(nombre, now.nombre)<0){
            return buscarR(now.left, nombre);
        } else {
            return buscarR(now.right, nombre);
        }
     }

    public Nodo buscar(String nombre){
        return buscarR(raiz, nombre);
    }

    private Nodo borrarR(Nodo now, String nombre){
        if(now == null){
            return null;
        }
        if(comparar(nombre, now.nombre) == 0){

            if(now.left == null && now.right == null){
                return null;
            }
            if(now.right == null){
                return now.left;
            }
            if(now.left == null){
                return now.right;
            }
            String menor = findMin(now.right);
            now.nombre = menor;
            now.right = borrarR(now.right, menor);
            return now;

        }
        if(comparar(nombre, now.nombre) < 0){
            now.left = borrarR(now.left, nombre);
        }
        now.right = borrarR(now.right, nombre);
        return now;
    }

    public void borrar(String nombre){
        raiz = borrarR(raiz, nombre);
    }

    private String findMin(Nodo raiz){
        if(raiz.left == null){
            return raiz.nombre;
        } else {
            return findMin(raiz.left);
        }
    }

    public void mostrarR(Nodo raiz, int cont){
        if(raiz != null){
            mostrarR(raiz.left, cont);
            cont++;
            System.out.println(cont + ") " + raiz.nombre);
            mostrarR(raiz.right, cont);
            cont++;
        }
    }

    public void mostrar(){
        Nodo rar = this.raiz;
        mostrarR(rar, 0);
    }

}
