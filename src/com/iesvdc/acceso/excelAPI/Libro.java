package com.iesvdc.acceso.excelAPI;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;




/**
 * @author amacias
 * @version 0.1
 */
public class Libro {

  private List<Hoja> hojas;
  private String nombreArchivo;

  public Libro() {
      
    this.hojas         = new ArrayList<>();
    this.nombreArchivo = "nuevo.xlsx";
  }
  
  public Libro( String nombreArchivo ) {
    
    this.hojas         = new ArrayList<>();
    this.nombreArchivo = nombreArchivo;
  }
  
  public Libro( List<Hoja> hojas, String nombreArchivo ) {
      
    this.hojas         = hojas;
    this.nombreArchivo = nombreArchivo;
  }

  public List<Hoja> getHojas() {
      
    return hojas;
  }

  public void setHojas( List<Hoja> hojas ) {
      
    this.hojas = hojas;
  }

  public String getNombreArchivo() {
      
    return nombreArchivo;
  }

  public void setNombreArchivo( String nombreArchivo ) {
      
    this.nombreArchivo = nombreArchivo;
  }

  public boolean addHoja( Hoja hoja ) {
      
    return this.hojas.add( hoja );
  }

  public Hoja removeHoja( int index ) throws ExcelAPIException {
      
    if ( index < 0 || index > this.hojas.size() ) {
      throw new ExcelAPIException( "Libro::removeHoja(): Posición no válida" );
    }
    
    return this.hojas.remove( index );
  }

  public Hoja indexHoja( int index ) throws ExcelAPIException {
      
    if ( index < 0 || index > this.hojas.size() ) {
      throw new ExcelAPIException( "Libro::indexHoja(): Posición no válida" );
    }
    
    return this.hojas.get( index );
  }

  public void load() {
    
    
  }

  public void load( String filename ) {
      
    this.nombreArchivo = filename;
    this.load();
  }

  public void save() throws ExcelAPIException {
      
    SXSSFWorkbook wb = new SXSSFWorkbook();

      for( Hoja hoja : this.hojas ) {
        Sheet sh = wb.createSheet( hoja.getNombre() );
        
        for( int i = 0; i < hoja.getNFilas(); i++ ) {
          Row row = sh.createRow( i );
          
          for( int j = 0; j < hoja.getNColumnas(); j++ ) {
              Cell cell = row.createCell( j );
              cell.setCellValue( hoja.getDatos( i, j ) );
          }
          
        }
        
      }
      try {
        FileOutputStream out = new FileOutputStream( this.nombreArchivo );
        wb.write( out );
        out.close();                        
      }
      catch (IOException ex) {
        throw new ExcelAPIException( "Error al guardar el archivo" );
      }
      finally {
        wb.dispose();
      }
}

  public void save( String filename ) throws ExcelAPIException {
      
    this.nombreArchivo = filename;
    this.save();
  }
  
  public void testExtension() {
    
    int finExtensionActual    = nombreArchivo.length() - 1;
    int inicioExtensionActual = finExtensionActual - 5;
    String extensionActual    = nombreArchivo.substring(inicioExtensionActual, finExtensionActual);
    String miExtension        = ".xlsx";
    
    if ( !extensionActual.equals( miExtension ) ) {
      nombreArchivo += miExtension;
    }
    
  }

}