package arbol;

public class Conversion {

    //Metodo para convertir la entrada del usuario como String (formato HH:MM) a int (minutos)
    //Las horas es el valor que se encuentra antes del :
    //Los minutos es el valor que se encuentra despues del :
    public static int stringToTime(String sTime){
        int timeInMins  =   0;
        String[] strArr =   sTime.split(":");
        int hour        =   Integer.parseInt(strArr[0]);
        int min         =   Integer.parseInt(strArr[1]);

        timeInMins = hour*60 + min;

        return timeInMins;
    }

    //Realiza una operacion matematica para convertir tiempo en minutos (int) a formato HH:MM (String)
    public static String timeToString(int sTime){
        String time;
        int hour = sTime/60, min = sTime%60;
        String min2;
        if(min == 0){
            time = hour + ":00";
        } else if(min < 10){
            time = hour + ":0" + min;
        } else {
            time = hour + ":" + min;
        }
        if(hour < 10){
            time = "0" + time;
        }
        return time;
    }

}
