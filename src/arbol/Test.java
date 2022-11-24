package arbol;

import java.io.IOException;

import javax.swing.JOptionPane;
import java.util.Scanner;

import ordenamiento.QuickSort;
import horarios.MaxContactos;

public class Test {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Arbol arbol = new Arbol();
        //arbol.cargar("test");
        /*
        arbol.add(98, "ali", 10, 14);
        arbol.add(928, "cdv12li", 12, 15);
        arbol.add(91238, "fel355i", 11, 16);
        arbol.add(9548, "ouili", 17, 20);

        arbol.mostrar();
        System.out.println("arbol.buscar(\"ouili\").tel = " + arbol.buscar("ouili").tel);
        arbol.editar(2, "ali", "", 999, 0, 0);
        arbol.editar(1, "ouili", "fred", 9969, 0, 0);

        arbol.mostrar();
        System.out.println(" = ");
        Nodo[] arr = arbol.toArray();
        for(int i=0; i<arbol.getCant(); i++){
            System.out.println("arr[i].nombre = " + arr[i].nombre+" - "+arr[i].fin);
        }
        QuickSort.quickSort(arr);
        //Pila pila = new Pila();
        for(int i=0; i<arbol.getCant(); i++){
            System.out.println(" arr[i].nombre = " + arr[i].nombre+" - "+arr[i].fin);
            //pila.push(arr[i]);
        }
        System.out.println(" ===== ");
        MaxContactos.maxContactos(arr);
        //pila.print();
        //arbol.guardar();
         */
        int opc;
        Scanner sc = new Scanner(System.in);
        do{
            menu();
            opc=sc.nextInt();
            switch(opc){
                case 1:{
                    System.out.print("Nombre: ");
                    sc.nextLine();
                    String nom=sc.nextLine();
                    System.out.print("Telefono: ");
                    int tlf=sc.nextInt();
                    System.out.println("Tiempo disponible: ");
                    System.out.print("\tHora de inicio: ");
                    int ini=sc.nextInt();
                    System.out.print("\tHora de fin: ");
                    int fn=sc.nextInt();
                    arbol.add(tlf, nom, ini, fn);
                    arbol.mostrar();
                    System.out.println("Presione alguna tecla para continuar..");
                    sc.next();
                    break;
                }
                case 2:{
                    editarContacto(sc,arbol);
                    arbol.mostrar();
                    System.out.println("Presione alguna tecla para continuar..");
                    sc.next();
                    break;
                }
                case 3:{
                    System.out.print("Nombre: ");
                    sc.nextLine();
                    String nom=sc.nextLine();
                    System.out.println("arbol.buscar(\""+nom+"\").tel = " + arbol.buscar(nom).tel);
                    System.out.println("Presione alguna tecla para continuar..");
                    sc.next();
                    break;
                }
                case 4:{
                    arbol.mostrar();
                    System.out.println("Presione alguna tecla para continuar..");
                    sc.next();
                    break;
                }
                case 5:{
                    Nodo[] arr = arbol.toArray();
                    MaxContactos.maxContactos(arr);
                    System.out.println("Presione alguna tecla para continuar..");
                    sc.next();
                    break;
                }
            }
        }while(opc!=0);
    }

    public static void menu(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("\n\t MENU PRINCIPAL\n\n");
        System.out.println("1.- Agregar contacto");
        System.out.println("2.- Editar contacto");
        System.out.println("3.- Buscar telefono por nombre");
        System.out.println("4.- Ordenar contactos alfabeticamente");
        System.out.println("5.- Organizar visitas individuales segun tiempo disponible");
        System.out.println("0.- Salir");
        
        System.out.print("Ingrese una opcion: [  ]\b\b\b ");
    }

    public static void editarContacto(Scanner sc, Arbol arbol){
        String nom="";
        int tlf=0, ini=0, fn=0,i=0, op=0;
        Nodo[] arr = arbol.toArray();
        System.out.println("\n\t EDITAR CONTACTO\n\n");
        do{
            System.out.println("Escoja el contacto a editar:");
            arbol.mostrar();
            System.out.print("Ingrese una opcion: [  ]\b\b\b ");
            i=sc.nextInt();
        }while(i<0||i>arbol.getCant());
        
        do{
            System.out.println("A continuacion elija que informacion del contacto quiere editar:");
            System.out.println("1.- Nombre");
            System.out.println("2.- Telefono");
            System.out.println("3.- Hora de inicio de tiempo libre");
            System.out.println("4.- Hora de fin de tiempo libre");
            System.out.print("Ingrese una opcion: [  ]\b\b\b ");
            op=sc.nextInt();    
        }while(op<0||op>4);
        
        switch(op){
            case 1:{
                System.out.print("Nombre: ");
                sc.nextLine();
                nom=sc.nextLine();
                break;
            }
            
            case 2:{
                System.out.print("Telefono: ");
                tlf=sc.nextInt();
                break;
            }
            
            case 3:{
                System.out.print("\tHora de inicio: ");
                ini=sc.nextInt();
                break;
            }
            
            case 4:{
                System.out.print("\tHora de fin: ");
                fn=sc.nextInt();
                break;  
            }
            
        }
        arbol.editar(op, arr[i-1].nombre, nom, tlf, ini, fn);
    }
}

