package ex;

import javax.sql.rowset.spi.TransactionalWriter;

//Se creo esta excepcion para mostrar mensajes de error en el menu y evitar que el programa se cierre

public class WrongInputException extends Exception{
    public WrongInputException(String mensaje){
        super(mensaje);
    }
    public WrongInputException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
    public WrongInputException(Throwable causa){
        super(causa);
    }
    public WrongInputException(){}
}
