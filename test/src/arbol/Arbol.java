package arbol;

import java.io.*;
import javax.swing.*;
import java.util.Date;

public class Arbol implements Serializable {
    private Nodo raiz; //La estructura se guardara a partir de este nodo
    private int cant; //Contador
    private boolean flag = false;

    //Constructor
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

    //Metodo recursivo para agregar un contacto
    //Usa el metodo comparar para determinar hacia cual de los nodos hijos moverse
    //Las llamadas recursivas se acumulan hasta que encuentra un nodo nulo
    private Nodo addR(Nodo now, String tel, String nombre, int inicio, int fin){
        if(now == null){
            flag=true;
            return new Nodo(tel, nombre, inicio, fin); //crea un nuevo nodo con los datos en esta posicion
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

    //Metodo para acceder al metodo recursivo desde main
    public void add(String tel, String nombre, int inicio, int fin){
        raiz = addR(raiz, tel, nombre, inicio, fin);
        if(flag){
            cant++;
            flag=false;
        }
        System.out.println(cant);
    }

    //Metodo recursivo para buscar un contacto
    //Usa el metodo comparar para determinar hacia que nodo hijo moverse
    //Retorna el nodo actual cuando encuentra una coincidencia
    //Si no encuentra una coincidencia, las llamadas recursivas se acumulan hasta encontrar un nodo nulo
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

    //Metodo para acceder al metodo recursivo desde main
    public Nodo buscar(String nombre){
        return buscarR(raiz, nombre);
    }

    //Metodo recursivo para borrar un contacto
    //Sigue la misma logica de los metodos anteriores
    //Cuando encuentra una coincidencia reemplaza el nodo actual con el mayor menor
    //Luego borra dicho nodo
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

    //Metodo para acceder al metodo recursivo desde main
    public void borrar(String nombre){
        raiz = borrarR(raiz, nombre);
        cant--;
    }

    //Metodo auxiliar para el metodo borrar
    //Encuentra el mayor menor en una de las ramas
    private String findMin(Nodo raiz){
        if(raiz.left == null){
            return raiz.nombre;
        } else {
            return findMin(raiz.left);
        }
    }

    //Metodo recursivo para mostrar la lista de contactos
    //Se desplaza por el arbol imprimiendo en un JTextPane cada nodo no nulo
    //Incorpora un contador que crece con cada llamada recursiva
    private int mostrarR(Nodo raiz, int cont, JTextPane instruccion){
        if(raiz.left != null){
            cont = mostrarR(raiz.left, cont, instruccion);
        }
        if(raiz.right != null){
            cont = mostrarR(raiz.right, cont, instruccion);
        }
        instruccion.setText( instruccion.getText() + cont + ") " + raiz.nombre + "\t   " + raiz.tel + "\t\t"
                + Conversion.timeToString(raiz.inicio) + "  -  " + Conversion.timeToString(raiz.fin) + "\n" );
        return cont+1;
    }

    //Metodo para acceder a mostrar desde main
    public void mostrar(JTextPane instruccion){
        if(cant==0){return;}
        Nodo rar = this.raiz;
        //System.out.println("#\tnombre\t\ttelefono\t\tinicio\t\tfin\n");
        instruccion.setText("");
        mostrarR(rar, 1, instruccion);
    }

    //Metodo recursivo para editar un contacto
    //Misma logica de los metodos anteriores
    //Incorpora un switch para determinar que informacion cambiar
    private Nodo editarR(int opc, Nodo now, String nombre, String nuevo, String tel, int inicio, int fin){
        if(now == null){
            return null;
        }
        if(comparar(nombre, now.nombre) == 0){
            switch (opc) {
                case 2 -> now.tel = tel;
                case 3 -> now.inicio = inicio;
                case 4 -> now.fin = fin;
            }
            return now;
        }
        if(comparar(nombre, now.nombre) < 0){
            now.left = editarR(opc, now.left, nombre, nuevo, tel, inicio, fin);
        }
        now.right = editarR(opc, now.right, nombre, nuevo, tel, inicio, fin);
        return now;
    }

    //Metodo para acceder a editarR desde main
    //No accede al metodo recursivo si el nombre a reemplazar es el mismo del contacto
    public void editar(int opc, String nombre, String nuevo, String tel, int inicio, int fin){
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

    //Metodo recursivo para convertir el arbol a un arreglo
    //Sigue la misma logica que el metodo mostrar
    private static int toArrayR(Nodo raiz, int cont, Nodo[] arr){
        if(raiz.left != null){
            cont = toArrayR(raiz.left, cont, arr);
        }
        if(raiz.right != null){
            cont = toArrayR(raiz.right, cont, arr);
        }
        arr[cont] = raiz;
        return cont+1;
    }

    //Metodo para acceder a toArrayR desde main
    public Nodo[] toArray(){
        Nodo rar = this.raiz;
        Nodo[] arr = new Nodo[cant];
        System.out.println(cant);
        toArrayR(rar, 0, arr);
        return arr;
    }

    //Contador que recorre el arbol para medir su tamano
    private int setCant(Nodo raiz, int cont){
        if(raiz != null){
            cont = setCant(raiz.left, cont);
            cont++;
            cont = setCant(raiz.right, cont);
        }
        return cont;
    }

    //Serializa el arbol en un bloc de notas
    public long guardar(String path,int op) throws IOException, ClassNotFoundException {
        boolean flag = false;
        File f = new File(path);
        Nodo temp = raiz;
        if(f.exists() && !f.isDirectory()) {
            cargar(1,path);
            flag = true;
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(raiz);
        out.close();
        if(flag){raiz = temp;}
        if(op==1) return System.currentTimeMillis();
        java.awt.Toolkit.getDefaultToolkit().beep();
        JOptionPane.showConfirmDialog(null, "Los contactos fueron exportados",
                "Contactos", JOptionPane.DEFAULT_OPTION);
        return 0;
    }

    //Deserializa el arbol si encuentra un archivo con los datos del objeto
    public void cargar(int op,String path) throws ClassNotFoundException, IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        Nodo temp = raiz;
        raiz = (Nodo)in.readObject();
        if(temp != null){
            cargarR(temp);
        }
        in.close();
        Nodo r = raiz;
        cant = setCant(r, 0);
        if(op == 0){
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showConfirmDialog(null, "Los contactos fueron importados",
                    "Contactos", JOptionPane.DEFAULT_OPTION);
        }
    }

    //Metodo auxuliar para cargar
    //Sirve para evitar que el arbol deserializado se sobreescriba por el arbol en memoria
    private void cargarR(Nodo temp){
        if(temp.left != null){
            cargarR(temp.left);
        }
        if(temp.right != null){
            cargarR(temp.right);
        }
        add(temp.tel, temp.nombre, temp.inicio, temp.fin);
    }

}
