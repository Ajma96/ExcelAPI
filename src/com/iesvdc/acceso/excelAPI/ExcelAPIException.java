package com.iesvdc.acceso.excelAPI;

/**
 * @author amacias
 * @version 0.1
 */
public class ExcelAPIException extends Exception {

    public ExcelAPIException() {
    }

    /**
     * Expulsa una excepción de la API personalizada
     * @param msg 
     */
    public ExcelAPIException( String msg ) {
        super("ExcelApiExcepcion:: " + msg);
    }
    
}