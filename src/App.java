import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import arbol.*;
import ex.WrongInputException;
import horarios.*;
import grafo.*;

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

    //Constructor para la interfaz de usuario
    public App(){
        sema = new Semaphore(0);
        box = "";
        setContentPane(panel1);
        setTitle("Analisis y Diseno de Algoritmos");
        //setTitle("Estructuras de Datos - G3");
        setSize(400, 450);
        setLocation(650, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        instruccion.setCharacterAttributes(attributeSet, true);
        instruccion.setText(menu());
        ejecutarButton.addActionListener(this);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingreso.setText("");
                box = "/back";
                sema.release();
            }
        });

        int condition = JComponent.WHEN_FOCUSED;
        InputMap iMap = ingreso.getInputMap(condition);
        ActionMap aMap = ingreso.getActionMap();

        String enter = "enter";
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        aMap.put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                box = ingreso.getText();
                ingreso.setText("");
                sema.release();
            }
        });

    }

    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}

        App myApp = new App();
        Arbol arbol = new Arbol();
        Grafo grafo = null;

        //Menu switch para controlar los algoritmos desde la interfaz
        int opc;
        volver:
        do{
            myApp.listaDeContactosTextField.setText("Lista de contactos:");
            arbol.mostrar(myApp.mostrado);
            myApp.instruccion.setText(menu());
            sema.acquire();
            try {
                opc=Integer.parseInt(myApp.box);
            } catch (NumberFormatException e) {
                //System.exit(0);
                opc=-1;
                //throw new RuntimeException("Ninguna opcion fue ingresada");
            }
            switch(opc){
                case 1:{
                    myApp.instruccion.setText("Nombre: ");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    String nom=myApp.box;
                    myApp.instruccion.setText("Telefono: ");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    String tlf=myApp.box;
                    myApp.instruccion.setText("Tiempo disponible: ");
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nHora de inicio (HH:MM): ");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    String ini=myApp.box;
                    myApp.instruccion.setText("Tiempo disponible: ");
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nHora de fin (HH:MM): ");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    String fn=myApp.box;
                    try {
                        arbol.add(tlf, nom, Conversion.stringToTime(ini), Conversion.stringToTime(fn));
                    } catch (WrongInputException e) {
                        myApp.instruccion.setText(e.getMessage());
                        sema.acquire();
                        if(myApp.box.equals("/back")){break;}
                        break;
                    }
                    arbol.mostrar(myApp.mostrado);
                    myApp.instruccion.setText("Presione 'Ejecutar' para continuar..");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
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
                        if(myApp.box.equals("/back")){continue volver;}
                        //i = Integer.parseInt(myApp.box);
                        nombre = myApp.box;
                        if(arbol.buscar(nombre)==null){
                            myApp.instruccion.setText("No se ingreso un contacto valido");
                            sema.acquire();
                            if(myApp.box.equals("/back")){break;}
                            continue volver;
                        }
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
                        if(myApp.box.equals("/back")){continue volver;}
                        try {
                            op=Integer.parseInt(myApp.box);
                        } catch (NumberFormatException e) {
                            continue volver;
                        }
                    }while(op<0||op>4);

                    switch(op){
                        case 1:{
                            myApp.instruccion.setText("Nombre: ");
                            sema.acquire();
                            if(myApp.box.equals("/back")){continue volver;}
                            nom=myApp.box;
                            break;
                        }

                        case 2:{
                            myApp.instruccion.setText("Telefono: ");
                            sema.acquire();
                            if(myApp.box.equals("/back")){continue volver;}
                            tlf=myApp.box;
                            break;
                        }

                        case 3:{
                            myApp.instruccion.setText("Hora de inicio (HH:MM): ");
                            sema.acquire();
                            if(myApp.box.equals("/back")){continue volver;}
                            String aux=myApp.box;
                            try {
                                ini = Conversion.stringToTime(aux);
                            } catch (WrongInputException e) {
                                myApp.instruccion.setText(e.getMessage());
                                sema.acquire();
                                continue volver;
                            }
                            break;
                        }

                        case 4:{
                            myApp.instruccion.setText("Hora de fin (HH:MM): ");
                            sema.acquire();
                            if(myApp.box.equals("/back")){continue volver;}
                            String aux=myApp.box;
                            try {
                                fn = Conversion.stringToTime(aux);
                            } catch (WrongInputException e) {
                                myApp.instruccion.setText(e.getMessage());
                                sema.acquire();
                                continue volver;
                            }
                            break;
                        }

                    }
                    //arbol.editar(op, arr[i - 1].getNombre(), nom, tlf, ini, fn);
                    arbol.editar(op, nombre, nom, tlf, ini, fn);



                    arbol.mostrar(myApp.mostrado);
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nPresione 'Ejecutar' para continuar..");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    break;
                }
                case 3:{
                    myApp.instruccion.setText("Nombre: ");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    String nom=myApp.box;
                    if(arbol.buscar(nom)==null){break;}
                    myApp.instruccion.setText("arbol.buscar(\""+nom+"\").tel = " + arbol.buscar(nom).getTel());
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nPresione 'Ejecutar' para continuar..");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    break;
                }
                case 4:{
                    myApp.instruccion.setText("Nombre: ");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    String nom=myApp.box;
                    arbol.borrar(nom);
                    if(arbol.getCant()>0){
                        arbol.mostrar(myApp.mostrado);
                    }else{
                        myApp.mostrado.setText("");
                    }
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nPresione 'Ejecutar' para continuar..");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    break;
                }
                case 5:{
                    arbol.mostrar(myApp.mostrado);
                    myApp.instruccion.setText("Presione 'Ejecutar' para continuar..");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    break;
                }
                case 6:{
                    arbol = new Arbol();
                    myApp.mostrado.setText("");
                    myApp.instruccion.setText("Presione 'Ejecutar' para continuar..");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    break;
                }
                case 7:{
                    myApp.instruccion.setText("");
                    Nodo[] arr = arbol.toArray();
                    MaxContactos.maxContactos(arr, myApp.instruccion);
                    myApp.instruccion.setText(myApp.instruccion.getText()+"\nPresione 'Ejecutar' para continuar..");
                    sema.acquire();
                    if(myApp.box.equals("/back")){break;}
                    break;
                }
                case 8:{
                    arbol.guardar();
                    break;
                }
                case 9:{
                    arbol.cargar(0);
                    break;
                }
                case 10:{


                    do {
                        int op = 0;
                        do{
                            myApp.mostrado.setText("");
                            myApp.listaDeContactosTextField.setText("Grafo:");
                            myApp.instruccion.setText("""
                                Menu de mapeo de citas:
                                1.- Crear nuevo grafo
                                2.- Ingresar contactos involucrados
                                3.- Insertar nuevo camino entre dos elementos (nombres)
                                4.- Insertar nuevo camino entre dos elementos (indices)
                                5.- Hallar ruta mas corta desde el origen (nombres)
                                6.- Hallar ruta mas corta desde el origen (indices)
                                7.- Guardar grafo
                                8.- Cargar grafo
                                Ingrese una opcion:
                                """);
                            sema.acquire();
                            if(myApp.box.equals("/back")){continue volver;}
                            op=Integer.parseInt(myApp.box);
                        }while(op<0||op>8);

                        switch(op){
                            case 1:{
                                int ca, or;
                                myApp.instruccion.setText("Ingrese cantidad de elementos del grafo:");
                                sema.acquire();
                                if(myApp.box.equals("/back")){continue;}
                                ca = Integer.parseInt(myApp.box);
                                myApp.instruccion.setText("Ingrese elemento de origen\nNumero del 0 al "+Integer.toString(ca-1));
                                sema.acquire();
                                if(myApp.box.equals("/back")){continue;}
                                or = Integer.parseInt(myApp.box);
                                grafo = new Grafo(ca, or);
                                break;
                            }
                            case 2:{
                                int cont = 0;
                                if (grafo != null) {
                                    for(int i=0; i<grafo.getCant(); i++){
                                        myApp.instruccion.setText("Ingrese un contacto");
                                        myApp.instruccion.setText(myApp.instruccion.getText()+"\nActualmente: "+cont);
                                        sema.acquire();
                                        if(myApp.box.equals("/back")){break;}
                                        String t = myApp.box;
                                        if(arbol.buscar(t)==null){
                                            myApp.instruccion.setText("No se encontro ese contacto");
                                            sema.acquire();
                                            if(myApp.box.equals("/back")){break;}
                                            cont--;
                                        } else {
                                            grafo.nombrar(t);
                                        }
                                        cont++;
                                    }
                                } else {
                                    myApp.instruccion.setText("El grafo debe ser creado antes de usar esta opcion");
                                    break;
                                }
                                break;
                            }
                            case 3:{
                                int cont = 0;
                                do {
                                    if(grafo != null){
                                        myApp.instruccion.setText("Ingrese elemento en el siguiente formato:\n" +
                                                "'contacto origen','contacto destino','distancia en km'\n" +
                                                "Ejemplo: alfred,beto,30");
                                        myApp.instruccion.setText(myApp.instruccion.getText()+"\nActualmente: "+cont);
                                        sema.acquire();
                                        if(myApp.box.equals("/back")){break;}
                                        String[] t = myApp.box.split(",");
                                        if(arbol.buscar(t[0])!=null && arbol.buscar(t[1])!=null){
                                            int c1 = grafo.traducirIN(t[0]);
                                            int c2 = grafo.traducirIN(t[1]);
                                            try {
                                                grafo.insert(c1, c2, Integer.parseInt(t[2]));
                                            } catch (Exception e) {
                                                myApp.instruccion.setText(e.getMessage());
                                                sema.acquire();
                                                break;
                                            }
                                            cont++;
                                        } else {
                                            myApp.instruccion.setText("uno de los contactos no fue encontrado");
                                        }
                                    } else {
                                        myApp.instruccion.setText("El grafo debe ser creado antes de usar esta opcion");
                                        break;
                                    }
                                } while (!myApp.box.equals("/back"));
                                myApp.box="";
                                break;
                            }
                            case 4:{
                                int cont = 0;
                                do {
                                    if(grafo != null){
                                        myApp.instruccion.setText("Ingrese elemento en el siguiente formato:\n" +
                                                "'vertice origen','vertice destino','distancia'\n" +
                                                "Ejemplo: 0,1,4");
                                        myApp.instruccion.setText(myApp.instruccion.getText()+"\nActualmente: "+cont);
                                        sema.acquire();
                                        if(myApp.box.equals("/back")){break;}
                                        String[] t = myApp.box.split(",");
                                        try {
                                            grafo.insert(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]));
                                        } catch (Exception e) {
                                            myApp.instruccion.setText(e.getMessage());
                                            sema.acquire();
                                            break;
                                        }
                                        cont++;
                                    } else {
                                        myApp.instruccion.setText("El grafo debe ser creado antes de usar esta opcion");
                                        break;
                                    }
                                } while (!myApp.box.equals("/back"));
                                myApp.box="";
                                break;
                            }
                            case 5:{
                                if(grafo != null){
                                    grafo.caminoMasCortoNombres(myApp.mostrado);
                                } else {
                                    myApp.instruccion.setText("El grafo debe ser creado antes de usar esta opcion");
                                }
                                break;
                            }
                            case 6:{
                                if(grafo != null){
                                    grafo.caminoMasCorto(myApp.mostrado);
                                } else {
                                    myApp.instruccion.setText("El grafo debe ser creado antes de usar esta opcion");
                                }
                                break;
                            }
                            case 7:{
                                Grafo.guardar(grafo);
                                break;
                            }
                            case 8:{
                                grafo = Grafo.cargar();
                                break;
                            }

                        }
                    } while (!myApp.box.equals("/back"));


                    break;
                }
                default:{
                    break;
                }
            }
        }while(opc!=0);
        System.exit(0);

    }



    public static String menu(){
        return """
                INGRESAR OPCION

                1.- Agregar contacto
                2.- Editar contacto
                3.- Buscar telefono por nombre
                4.- Borrar contacto
                5.- Ordenar contactos alfabeticamente
                6.- VACIAR lista de contactos
                7.- Organizar visitas individuales segun tiempo disponible
                8.- Exportar contactos
                9.- Importar contactos
                10.- Menu de citas
                """;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        box = ingreso.getText();
        ingreso.setText("");
        sema.release();
    }

}