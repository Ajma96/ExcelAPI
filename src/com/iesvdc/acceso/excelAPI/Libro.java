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

  /**
   * Contructor por defecto de la clase Libro.
   */
  public Libro() {
      
    this.hojas         = new ArrayList<>();
    this.nombreArchivo = "nuevo.xlsx";
  }
  
  /**
   * Constructor parametrizado de la clase Libro.
   * @param nombreArchivo 
   */
  public Libro( String nombreArchivo ) {
    
    this.hojas         = new ArrayList<>();
    this.nombreArchivo = nombreArchivo;
  }
  
  /**
   * Constructor parametrizado de la clase Libro.
   * @param hojas
   * @param nombreArchivo 
   */
  public Libro( List<Hoja> hojas, String nombreArchivo ) {
      
    this.hojas         = hojas;
    this.nombreArchivo = nombreArchivo;
  }

  /**
   * Método que devuelve la lista de hojas que componen un Libro.
   * @return Lista de hojas del libro.
   */
  public List<Hoja> getHojas() {
      
    return hojas;
  }

  /**
   * Método que asigna la lista de hojas que recibe a un libro.
   * @param hojas 
   */
  public void setHojas( List<Hoja> hojas ) {
      
    this.hojas = hojas;
  }

  /**
   * Método que devuelve el nombre del libro.
   * @return nombre del libro.
   */
  public String getNombreArchivo() {
      
    return nombreArchivo;
  }

  /**
   * Método que asigna el nombre al libro que se le pasa como parámetro.
   * @param nombreArchivo 
   */
  public void setNombreArchivo( String nombreArchivo ) {
      
    this.nombreArchivo = nombreArchivo;
  }

  /**
   * Método que añade la hoja que se le pasa como parámetro a las de dicho libro.
   * @param hoja
   * @return un flag que indica si se ha podido hacer con éxito o no.
   */
  public boolean addHoja( Hoja hoja ) {
      
    return this.hojas.add( hoja );
  }

  /**
   * Método que elimina la hoja con la posición que se le pasa en ese Libro.
   * @param index
   * @return 
   * @throws ExcelAPIException 
   */
  public Hoja removeHoja( int index ) throws ExcelAPIException {
      
    if ( index < 0 || index > this.hojas.size() ) {
      throw new ExcelAPIException( "Libro::removeHoja(): Posición no válida" );
    }
    
    return this.hojas.remove( index );
  }

  /**
   * Método que devuelve la posición de una hoja en el libro.
   * @param index
   * @return posición de la hoja.
   * @throws ExcelAPIException 
   */
  public Hoja indexHoja( int index ) throws ExcelAPIException {
      
    if ( index < 0 || index > this.hojas.size() ) {
      throw new ExcelAPIException( "Libro::indexHoja(): Posición no válida" );
    }
    
    return this.hojas.get( index );
  }

  /**
   * Método que carga a memoria un archivo .xlsx (unmarshalling).
   */
  public void load() {
    
  }

  /**
   * Método que carga en memoria un archivo con el nombre indicado.
   * @param nombreArchivo 
   */
  public void load( String nombreArchivo ) {
      
    this.nombreArchivo = nombreArchivo;
    this.load();
  }

  /**
   * Método que pasae un libro en memoria a un archivo .xlsx .
   * @throws ExcelAPIException 
   */
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

  /**
   * Método que hace pasa a un archivo .xlsx un libro en memoria.
   * @param filename
   * @throws ExcelAPIException 
   */
  public void save( String filename ) throws ExcelAPIException {
      
    this.nombreArchivo = filename;
    this.save();
  }

}