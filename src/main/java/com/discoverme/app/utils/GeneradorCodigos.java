package com.discoverme.app.utils;


/**
 * Clase que nos genera codigos aleatorios para crear usuario, contraseñas y codigos de ofertas
 * @author Manuel Leyva
 */
public class GeneradorCodigos {
    
    public static String NUMEROS = "0123456789";
    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    /**
    * Funcion que un pin de cuatro cifras aleatorias
    * @return String 
    * @author Manuel Leyva    
    */
    public static String getPinNumber() {
        return getCodigo(NUMEROS, 4);
    }
    
    /**
    * Funcion que devuelve un codigo de 8 caracteres (numeros, mayusculas y minusculas)
    * @return String 
    * @author Manuel Leyva    
    */
    public static String getCodigo() {
        return getCodigo(8);
    }

    /**
    * Funcion que devuelve un codigo de x caracteres donde x es el numero pasado por parametro (numeros, mayusculas y minusculas)
    * @param length
    * @return String 
    * @author Manuel Leyva    
    */
    public static String getCodigo(int length) {
        return getCodigo(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    /**
    * Funcion que devuelve un codigo de x caracteres, pasados por parametro (key) donde x es el numero pasado por parametro (length)
    * @param key
    * @param length
    * @return String 
    * @author Manuel Leyva    
    */
    public static String getCodigo(String key, int length) {
        String codigo = "";
        for (int i = 0; i < length; i++) {
            codigo+=(key.charAt((int)(Math.random() * key.length())));
        }
        return codigo;
    }
}
