package com.imd030.sgr.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by thiago on 28/05/16.
 */
public class DateUtils {

    private static String[] meses = {"janeiro", "fevereiro", "março", "abril", "maio", "junho",
            "julho", "agosto", "stembro", "outubro", "novembro", "dezembro"};

    public static String obterDataPorExtenso(Date data){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.DAY_OF_MONTH) + " de " +
                meses[calendar.get(Calendar.MONTH)] + " de " +
                calendar.get(Calendar.YEAR);
    }
}
