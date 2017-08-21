package com.devlabs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author VakSF
 */
public class DateUtil {
    
    /**
     * Retorna una fecha simple en base a una fecha definida.
     * 
     * @param date es una fecha completa
     * @return la fecha esperada en String
     */
    public static String getDateString(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    
    /**
     * Retorna una fecha en base a una fecha en String.
     * 
     * @param dateSt es una fecha en String
     * @return la fecha esperada en Date
     */
    public static Date parseDate(String dateSt) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateSt);
    }
    
}
