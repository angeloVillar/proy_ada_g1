import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import arbol.*;
import horarios.*;

public class App extends JFrame implements ActionListener{
    private JPanel panel1;
    private JTextField ingreso;
    private JButton ejecutarButton;
    private JButton volverButton;
    private JPanel panel2;
    private JTextPane instruccion;
    private JTextPane mostrado;
    private JTextField listaDeContactosTextField;
    private String box;
    private static Semaphore sema;

    public App(){
        sema = new Semaphore(0);
        box = "";
        setContentPane(panel1);
        setTitle("Analisis y Diseno de Algoritmos");
        setSize(400, 450);
        setLocation(650, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        instruccion.setText(menu());
        ejecutarButton.addActionListener(this);
    }

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        App myApp = new App();
        Arbol arbol = new Arbol();
        //sema.acquire();








        int opc;
        do{
            myApp.instruccion.setText(menu());
            sema.acquire();
            opc=Integer.parseInt(myApp.box);
            switch(opc){
                case 1:{
                    myApp.instruccion.setText("Nombre: ");
                    sema.acquire();
                    String nom=myApp.box;
                    myApp.instruccion.setText("Telefono: ");
                    sema.acquire();
                    String tlf=myApp.box;
                    myApp.instruccion.setText("Tiempo disponible: ");
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nHora de inicio (HH:MM): ");
                    sema.acquire();
                    String ini=myApp.box;
                    myApp.instruccion.setText("Tiempo disponible: ");
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nHora de fin (HH:MM): ");
                    sema.acquire();
                    String fn=myApp.box;
                    arbol.add(tlf, nom, Conversion.stringToTime(ini), Conversion.stringToTime(fn));
                    arbol.mostrar(myApp.mostrado);
                    myApp.instruccion.setText("Presione 'Ejecutar' para continuar..");
                    sema.acquire();
                    break;
                }
                case 2:{






                    String nom="", tlf=""; String nombre;
                    int ini=0, fn=0, i=0, op=0;
                    //Nodo[] arr = arbol.toArray();
                    myApp.instruccion.setText("EDITAR CONTACTO\n");
                    do{
                        myApp.instruccion.setText(myApp.instruccion.getText()+"Ingrese nombre del contacto:");
                        arbol.mostrar(myApp.mostrado);
                        //console.setText(console.getText()+"Ingrese una opcion (indice):");
                        sema.acquire();
                        //i = Integer.parseInt(myApp.box);
                        nombre = myApp.box;
                    }while(i<0||i>arbol.getCant());

                    do{
                        myApp.instruccion.setText("""
                            A continuacion elija que informacion del contacto quiere editar:
                            1.- Nombre
                            2.- Telefono
                            3.- Hora de inicio de disponibilidad
                            4.- Hora de fin de disponibilidad
                            Ingrese una opcion:
                            """);
                        sema.acquire();
                        op=Integer.parseInt(myApp.box);
                    }while(op<0||op>4);

                    switch(op){
                        case 1:{
                            myApp.instruccion.setText("Nombre: ");
                            sema.acquire();
                            nom=myApp.box;
                            break;
                        }

                        case 2:{
                            myApp.instruccion.setText("Telefono: ");
                            sema.acquire();
                            tlf=myApp.box;
                            break;
                        }

                        case 3:{
                            myApp.instruccion.setText("Hora de inicio (HH:MM): ");
                            sema.acquire();
                            String aux=myApp.box;
                            ini = Conversion.stringToTime(aux);
                            break;
                        }

                        case 4:{
                            myApp.instruccion.setText("Hora de fin (HH:MM): ");
                            sema.acquire();
                            String aux=myApp.box;
                            fn = Conversion.stringToTime(aux);
                            break;
                        }

                    }
                    //arbol.editar(op, arr[i - 1].getNombre(), nom, tlf, ini, fn);
                    arbol.editar(op, nombre, nom, tlf, ini, fn);









                    arbol.mostrar(myApp.mostrado);
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nPresione 'Ejecutar' para continuar..");
                    sema.acquire();
                    break;
                }
                case 3:{
                    myApp.instruccion.setText("Nombre: ");
                    sema.acquire();
                    String nom=myApp.box;
                    myApp.instruccion.setText("arbol.buscar(\""+nom+"\").tel = " + arbol.buscar(nom).getTel());
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nPresione 'Ejecutar' para continuar..");
                    sema.acquire();
                    break;
                }
                case 4:{
                    arbol.mostrar(myApp.mostrado);
                    myApp.instruccion.setText("Presione 'Ejecutar' para continuar..");
                    sema.acquire();
                    break;
                }
                case 5:{
                    myApp.instruccion.setText("");
                    Nodo[] arr = arbol.toArray();
                    MaxContactos.maxContactos(arr, myApp.instruccion);
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nPresione 'Ejecutar' para continuar..");
                    sema.acquire();
                    break;
                }
                case 6:{
                    arbol.guardar();
                    break;
                }
                case 7:{
                    arbol.cargar();
                    break;
                }
            }
        }while(opc!=0);






    }






    public static String menu(){
        return """
                INGRESAR OPCION

                1.- Agregar contacto
                2.- Editar contacto
                3.- Buscar telefono por nombre
                4.- Ordenar contactos alfabeticamente
                5.- Organizar visitas individuales segun tiempo disponible
                6.- Exportar contactos
                7.- Importar contactos
                """;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        box = ingreso.getText();
        ingreso.setText("");
        sema.release();
    }

}