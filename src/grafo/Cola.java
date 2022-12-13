package grafo;

public class Cola {
    private NodoDJ cabeza;

    public Cola(){
        cabeza = null;
    }

    public NodoDJ getCabeza(){
        return cabeza;
    }
    public void push(int x, int p){
        NodoDJ nuevo = new NodoDJ(x, p);
        if(cabeza == null){
            cabeza = nuevo;
            return;
        }
        NodoDJ temp = cabeza;
        NodoDJ ante = null;
        while(temp != null && temp.getDistancia() < p){
            ante = temp;
            temp = temp.getNext();
        }
        if(temp == null){
            ante.setNext(nuevo);
        } else {
            if(ante == null){
                nuevo.setNext(cabeza);
                cabeza = nuevo;
            } else {
                nuevo.setNext(temp);
                ante.setNext(nuevo);
            }
        }
    }

/*    public NodoDJ pop(){
        if(cabeza != null){
            NodoDJ temp = cabeza;
            cabeza = cabeza.getNext();
            return temp;
        }
        return null;
    }*/

    public NodoDJ pop(){
        if(isEmpty()){
            return null;
        }
        NodoDJ temp = cabeza;
        cabeza = cabeza.getNext();
        if(isEmpty()){
            cabeza=null;
        }
        return temp;
    }

    public boolean isEmpty(){
        return (cabeza) == null;
    }

    public void print(){
        NodoDJ temp = cabeza;
        while(temp != null){
            System.out.println(temp.getVertice()+" , "+temp.getDistancia());
            temp = temp.getNext();
        }
    }

}
