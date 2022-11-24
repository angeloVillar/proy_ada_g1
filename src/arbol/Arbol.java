package arbol;

import java.io.*;

public class Arbol implements Serializable {
    private Nodo raiz;
    private int cant;

    public Arbol(){
        raiz = null;
        cant = 0;
    }

    public int getCant() {
        return cant;
    }

    public int comparar(String s1, String s2){
        return s1.compareTo(s2);
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
        cant++;
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
        cant--;
    }

    private String findMin(Nodo raiz){
        if(raiz.left == null){
            return raiz.nombre;
        } else {
            return findMin(raiz.left);
        }
    }

    private void mostrarR(Nodo raiz, int cont){
        if(raiz != null){
            mostrarR(raiz.left, cont);
            cont++;
            System.out.println(cont + ") " + raiz.nombre + " - " + raiz.tel);
            mostrarR(raiz.right, cont);
            cont++;
        }
    }

    public void mostrar(){
        Nodo rar = this.raiz;
        mostrarR(rar, 0);
    }

    private Nodo editarR(int opc, Nodo now, String nombre, String nuevo, int tel, int inicio, int fin){
        if(now == null){
            return null;
        }
        if(comparar(nombre, now.nombre) == 0){
            switch (opc) {
                case 2: now.tel = tel;
                case 3: now.inicio = inicio;
                case 4: now.fin = fin;
            }
            return now;
        }
        if(comparar(nombre, now.nombre) < 0){
            now.left = editarR(opc, now.left, nombre, nuevo, tel, inicio, fin);
        }
        now.right = editarR(opc, now.right, nombre, nuevo, tel, inicio, fin);
        return now;
    }

    public void editar(int opc, String nombre, String nuevo, int tel, int inicio, int fin){
        if (opc==1 && !nombre.equals(nuevo)) {
            Nodo temp = buscar(nombre);
            add(temp.tel, nuevo, temp.inicio, temp.fin);
            borrar(nombre);
            return;
        } else if (opc == 1){
            return;
        }
        raiz = editarR(opc, raiz, nombre, nuevo, tel, inicio, fin);
    }

    private Nodo[] toArrayR(Nodo raiz, int cont, Nodo[] arr){
        if(raiz != null){
            toArrayR(raiz.left, cont, arr);
            cont++;
            arr[cont] = raiz;
            toArrayR(raiz.right, cont, arr);
            cont++;
        }
        return arr;
    }

    public Nodo[] toArray(){
        Nodo rar = this.raiz;
        Nodo[] arr = new Nodo[cant];
        return toArrayR(rar, -1, arr);
    }

    private int setCant(Nodo raiz, int cont){
        if(raiz != null){
            cont = setCant(raiz.left, cont);
            cont++;
            cont = setCant(raiz.right, cont);
        }
        return cont;
    }

    void guardar() throws IOException {
        File f = new File("arbol");
        if(f.exists()){
            f.delete();
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("arbol"));
        out.writeObject(raiz);
        out.close();
    }

    void cargar() throws ClassNotFoundException, IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("arbol"));
        raiz = (Nodo)in.readObject();
        in.close();
        Nodo r = raiz;
        cant = setCant(r, 0);
    }

}
