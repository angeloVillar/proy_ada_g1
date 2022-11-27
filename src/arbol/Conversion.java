package arbol;

public class Conversion {

    public static int stringToTime(String sTime){
        int timeInMins  =   0;
        String[] strArr =   sTime.split(":");
        int hour        =   Integer.parseInt(strArr[0]);
        int min         =   Integer.parseInt(strArr[1]);

        timeInMins = hour*60 + min;

        return timeInMins;
    }

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
