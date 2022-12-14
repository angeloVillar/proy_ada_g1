package grafo;

//Esta es una cola de prioridad, asi que los nodos se insertan por posicion segun un criterio
//La cola de priridad es una estructura de datos basada en una lista enlazada
//Sirve como estructura de datos auxiliar en el algoritmo de dijkstra
public class Cola {
    private NodoDJ cabeza; //Desde aqui se agregaran nuevos nodos, la cabeza apunta al resto de la estructura

    //Constructor
    public Cola(){
        cabeza = null;
    }

    public NodoDJ getCabeza(){
        return cabeza;
    }

    //Metodo para agregar un nodo segun la distancia p (menores distancias tienen mayor prioridad)
    public void push(int x, int p){
        NodoDJ nuevo = new NodoDJ(x, p);
        //Si la cola de prioridad esta vacia, se agrega el nuevo nodo como cabeza
        if(cabeza == null){
            cabeza = nuevo;
            return;
        }
        NodoDJ temp = cabeza;
        NodoDJ ante = null;

        //Busca el ultimo nodo con menor prioridad que el nuevo
        while(temp != null && temp.getDistancia() < p){
            ante = temp;
            temp = temp.getNext();
        }

        //Si ningun nodo tuvo menos prioridad que el nuevo -> agregar al final
        if(temp == null){
            ante.setNext(nuevo);
        } else {
            //Si el nuevo nodo es el de mayor prioridad -> agregar al inicio
            if(ante == null){
                nuevo.setNext(cabeza);
                cabeza = nuevo;
            } else {
                //Se agrega el nuevo nodo en la posicion respectiva
                nuevo.setNext(temp);
                ante.setNext(nuevo);
            }
        }
    }

    //Metodo para eliminar el nodo con mayor prioridad / Retornar dicho nodo
    public NodoDJ pop(){
        //Si la cola de prioridad esta vacia, no retorna ni elimina nada
        if(isEmpty()){
            return null;
        }
        //Nodo temporal para usarse en el return
        NodoDJ temp = cabeza;
        //Eliminar el nodo de la cabeza
        cabeza = cabeza.getNext();
        if(isEmpty()){
            cabeza=null;
        }
        return temp;
    }

    //Verifica si la cola esta vacia o no
    public boolean isEmpty(){
        return (cabeza) == null;
    }

    //Metodo para mostrar la Cola. Se uso para probar el algoritmo
    public void print(){
        NodoDJ temp = cabeza;
        while(temp != null){
            System.out.println(temp.getVertice()+" , "+temp.getDistancia());
            temp = temp.getNext();
        }
    }

}
