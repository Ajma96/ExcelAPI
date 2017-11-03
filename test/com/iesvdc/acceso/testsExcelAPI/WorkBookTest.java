package com.iesvdc.acceso.testsExcelAPI;

import com.iesvdc.acceso.excelAPI.Hoja;
import com.iesvdc.acceso.excelAPI.Libro;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
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
public class WorkBookTest {
    
    public WorkBookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getFilename method, of class WorkBook.
     */
    @Test
    public void testGetFilename() {
        
      System.out.println("getNombreArchivo");
      
      String nombreArchivo = "test.xlsx";  
      Libro libro = new Libro( nombreArchivo );
        
      String expResult = nombreArchivo;
      String result = libro.getNombreArchivo();
      assertEquals(expResult, result);
    }

    /**
     * Test of setFilename method, of class WorkBook.
     */
    @Test
    public void testSetFilename() {
        
        System.out.println( "setFilename" );
        
        String nombreArchivo   = "TestFilename.xlsx"; 
        Libro libro            = new Libro();
        
        libro.setNombreArchivo( nombreArchivo );
        assertEquals( "testSetFilename", nombreArchivo, libro.getNombreArchivo());

    }

    /**
     * Test of addDataSheet method, of class WorkBook.
     */
    @Test
    public void testAddDataSheet_DataSheet() {
        
        System.out.println( "addHoja" );
        Hoja hoja   = new Hoja( "Mi hoja", 5, 5 );
        Libro libro = new Libro();
        
        boolean expResult = true;
        boolean result    = libro.addHoja( hoja );
        assertEquals( expResult, result );

    }

    /**
     * Test of addDataSheet method, of class WorkBook.
     */
    @Test
    public void testAddDataSheet_int_DataSheet() {
        
        System.out.println( "addDataSheet" );
        Hoja hoja1 = new Hoja( "Mi hoja1", 5, 5 );
        Hoja hoja2 = new Hoja( "Mi hoja2", 5, 5 );
        // int index = 1;
        // DataSheet ds = null;
        Libro libro = new Libro();
        libro.addHoja( hoja1 );
        libro.addHoja( 0, hoja2 );
        
        Hoja hoja3 = new Hoja( "Mi hoja3", 5, 5 );
        libro.addHoja( 1, hoja3 );
    }

    /**
     * Test of removeDataSheet method, of class WorkBook.
     */
    @Test
    public void testRemoveDataSheet() throws Exception {
        
        System.out.println( "removeDataSheet" );
        DataSheet ds1 = new DataSheet( "Mi hoja1", 5, 5 );
        DataSheet ds2 = new DataSheet( "Mi hoja2", 5, 5 );
        
        int index = 0;
        WorkBook instance = new WorkBook();
        instance.addDataSheet( ds1 );
        instance.addDataSheet( ds2 );
        
        DataSheet expResult = ds1;
        DataSheet result    = instance.removeDataSheet( 0 );
        assertEquals( expResult, result );
    }


    /**
     * Test of load and save methods, of class WorkBook.
     */
    @Test
    public void testSave() throws Exception {
        
        System.out.println( "loadSave" );
        try {
          DataSheet ds1 = new DataSheet( "Mi hoja1", 5, 6 );
          DataSheet ds2 = new DataSheet( "Mi hoja2", 7, 3 );
          
          for (int i=0;i<5;i++) {
            for ( int j = 0; j < 6; j++ ) {
              ds1.setData( i, j, "Hoja 1:" + i + "," + j );
            } 
          }
          
          for (int i = 0; i < 7; i++) { 
              for (int j = 0; j < 3; j++) {
                  ds2.setData( i, j, "Hoja 2:" + i + "," + j );
              }
          }
          
          String filename = "testLoadSave.xlsx";
          WorkBook wb     = new WorkBook( filename );
          System.out.println( "Escribiendo..." );
          wb.addDataSheet(ds1);
          wb.addDataSheet(ds2);
          wb.save();

          System.out.println( "Leyendo entrada..." );
          WorkBook wb2 = new WorkBook( filename );
          wb2.load();
          assertEquals( "Save and load file compare.", true, wb.compare( wb2 ) );

      }
      catch ( ExcelApiException ex ) {
        System.out.println( ex.getLocalizedMessage() );
    }
        
  }
    
}
