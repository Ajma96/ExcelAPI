package com.iesvdc.acceso.excelAPI;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author amacias
 * @version 0.1
 */
public class HolaMundoExcel {

  /**
   * El típico "Hola Mundo" llevado a un .xlsx de la mano del tratamiento que nos
   * permite hacer Apache POI de los XML detrás del mismo.
   * @param args the command line arguments
   */
  public static void main( String[] args ) {

    SXSSFWorkbook wb = new SXSSFWorkbook();

    Sheet sh = wb.createSheet( "HOLA MUNDO" );

    for ( int i = 0; i < 10; i++ ) {
      Row row = sh.createRow( i );
      
      for ( int j = 0; j < 10; j++ ) {
        Cell cell = row.createCell( j );
        cell.setCellValue((char) ( 'A' + j ) + " " + ( i + 1 ) );
      }
      
    }

    try {
      FileOutputStream out = new FileOutputStream( "holaMundoExcel.xlsx" );
      wb.write( out );
      out.close();
    }
    catch ( IOException ex ) {
      System.out.println("ERROR al crear el archivo: " + ex.getLocalizedMessage());
    }
    finally {
      wb.dispose();
    }

  }

}