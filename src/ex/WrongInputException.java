package ex;

import javax.sql.rowset.spi.TransactionalWriter;

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
