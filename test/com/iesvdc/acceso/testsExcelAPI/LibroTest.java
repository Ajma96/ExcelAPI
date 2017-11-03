package com.iesvdc.acceso.testsExcelAPI;

import com.iesvdc.acceso.excelAPI.ExcelAPIException;
import com.iesvdc.acceso.excelAPI.Hoja;
import com.iesvdc.acceso.excelAPI.Libro;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author amacias
 * @version 0.1
 */
public class LibroTest {
    
    public LibroTest() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}

    /**
     * Test del método getNombreArchivo, de la clase Libro.
     */
    @Test
    public void testGetNombreArchivo() {
      
      String nombreMetodo = "getNombreArchivo";  
      
      System.out.println( nombreMetodo );
      
      String nombreArchivo = nombreMetodo + ".xlsx";  
      Libro libro = new Libro( nombreArchivo );
        
      String resultadoEsperado = nombreArchivo;
      String resultadoObtenido = libro.getNombreArchivo();
      
      assertEquals(resultadoEsperado, resultadoObtenido);
    }

    /**
     * Test del método setNombreArchivo, de la clase Libro.
     */
    @Test
    public void testSetNombreArchivo() {
        
        String nombreMetodo = "testSetNombreArchivo";
        
        System.out.println( nombreMetodo );
        
        String nombreArchivo   = nombreMetodo + ".xlsx"; 
        Libro libro            = new Libro();
        
        libro.setNombreArchivo( nombreArchivo );
        
        assertEquals( nombreMetodo, nombreArchivo, libro.getNombreArchivo());
    }

    /**
     * Test del método addHoja, de la clase Libro.
     */
    @Test
    public void testAddHoja() {
        
        System.out.println( "addHoja" );
        Hoja hoja   = new Hoja( "Mi hoja", 5, 5 );
        Libro libro = new Libro();
        
        boolean resultadoEsperado = true;
        boolean resultadoObtenido = libro.addHoja( hoja );
        
        assertEquals(resultadoEsperado, resultadoObtenido );

    }

    /**
     * Test del método removeHoja, de la clase Libro.
     * @exception Puede arrojar excepciones en el borrado por acceso a zona
     * restringida de memoria.
     */
    @Test
    public void testRemoveHoja() throws Exception {
        
        System.out.println( "removeDataSheet" );
        
        int index   = 0;
        Hoja hoja1  = new Hoja( "Mi hoja1", 5, 5 );
        Hoja hoja2  = new Hoja( "Mi hoja2", 5, 5 );
        Libro libro = new Libro();
        
        libro.addHoja( hoja1 );
        libro.addHoja( hoja2 );
        
        Hoja resultadoEsperado = hoja1;
        Hoja resultadoObtenido = libro.removeHoja( index );
        assertEquals( resultadoEsperado, resultadoObtenido );
    }

    /**
     * Test del método load, de la clase Libro.
     * @exception Puede arrojar excepción por no encontrar el archivo.
     */
    @Test
    public void testLoad() throws Exception {
        
        System.out.println("testLoad");
        
        String nombreArchivo = "testLoad.xlsx";
        Libro libro1         = new Libro( nombreArchivo );
        
        Libro libro2 = new Libro( nombreArchivo );
        libro2.load();
        
        assertEquals( libro1, libro2 );
    }
    
    /**
     * Test del método save, de la clase Libro.
     * Es un poco redundante teniendo ya un testLoad, pero creo que la mejor
     * forma de testear esto era repitiendo ese código, porque no quería eliminar
     * testLoad(), quiero conservarlo como un método independiente.
     * @exception Puede arrojar excepción al cargar el archivo para comprobar si
     * se ha exportado bien (no lo encuentra).
     */
    @Test
    public void testSave() throws ExcelAPIException {
        
        System.out.println( "testSave" );
        
        try {
          Hoja hoja1 = new Hoja( "Mi hoja1", 5, 6 );
          Hoja hoja2 = new Hoja( "Mi hoja2", 7, 3 );
          
          for ( int i = 0; i < 5; i++ ) {
            for ( int j = 0; j < 6; j++ ) {
              hoja1.setDatos( "Hoja 1:" + i + "," + j, i, j );
            } 
          }
          
          for ( int i = 0; i < 7; i++ ) { 
              for (int j = 0; j < 3; j++) {
                  hoja2.setDatos( "Hoja 2:" + i + "," + j, i, j );
              }
          }
          
          String nombreArchivo  = "testSave.xlsx";
          Libro  libroEnMemoria = new Libro( nombreArchivo );
          
          libroEnMemoria.addHoja(hoja1);
          libroEnMemoria.addHoja(hoja2);
          libroEnMemoria.save();
          
          Libro libroCargado = new Libro( nombreArchivo );
          libroCargado.load();
          
          assertEquals( libroEnMemoria, libroCargado );

      }
      catch ( ExcelAPIException ex ) {
        System.out.println( ex.getLocalizedMessage() );
      }
        
  }
    
  /**
   * Método que testea la extensión del archivo.
   */
  public void testExtension() {
    
    System.out.println("testExtension");
      
    String nombreArchivo = "pruebaExtension.xlsx";
    String nombreLibro   = "pruebaExtension";
    
    Libro libroExtension = new Libro( nombreLibro );
        try {
            libroExtension.save();
        }
        catch (ExcelAPIException ex) {
            System.out.println( ex.getLocalizedMessage() );
        }
    
    int finExtensionActual    = nombreArchivo.length() - 1;
    int inicioExtensionActual = finExtensionActual - 5;
    String extensionActual    = nombreArchivo.substring(inicioExtensionActual, 
                                                        finExtensionActual);
    String miExtensionDeseada = ".xlsx";
    
    if ( !extensionActual.equals( miExtensionDeseada ) ) {
      nombreArchivo += miExtensionDeseada;
    }
    
  }
    
}
