/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase auxiliar con métodos útiles.
 * @author Adrián
 */
public class Helper {
    
    /**
     * Devuelve la fecha actual formateada para la generación de ficheros
     * @return La fecha actual para los ficheros
     */
    public static String getCurrentTime()
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
    
    /**
     * Devuelve un número aleatorio entre el mínimo y el máximo.
     * @param min El mínimo valor. Incluído.
     * @param max El máximo valor. No incluído.
     * @return Un valor aleatorio entre <code>min</code> y <code>max</code>
     */
    public static int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
