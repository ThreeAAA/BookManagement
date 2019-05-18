package dataBase.operation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    public static void main(String[] args) {
        System.out.println(DateUtil.getFutureDate(1));
    }
    public static String getNowDay(){
        java.util.Date data = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(data);
    }

    public static String getNowTime(){
        java.util.Date data = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(data);
    }

    public static String getFutureDate(Date day, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.MONTH, 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    public static String getFutureDate(int month){
        Date day = new Date();
        return DateUtil.getFutureDate(day, month);
    }
}
