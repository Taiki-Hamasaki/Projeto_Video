package Modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tulio
 */
public class ConverteDataWEB {
    
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();
    
    public static Date somarData(int dias, Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();
    }
    
    public static String dataAtual() {
        return sdf.format(new Date());
    }
    
    public Calendar converteCalendario(String data) {
        try {
            cal.setTime(sdf.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
    
    public Calendar converteCalendario(Date data) {
        Calendar dataCal = Calendar.getInstance();
        dataCal.setTime(data);
        return dataCal;
    }
    
    public String converteTela(Object value) {
        Calendar c = (Calendar) value;
        return sdf.format(c.getTime());
    }
    
    public java.sql.Date converteBanco(Object value) {
        Calendar c = (Calendar) value;
        return new java.sql.Date(c.getTimeInMillis());
    }
    
    public String soEmNums(String data) {
        List<Character> dataList = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) != '/') {
                dataList.add(data.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Character c : dataList) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(dataAtual());
    }
}
