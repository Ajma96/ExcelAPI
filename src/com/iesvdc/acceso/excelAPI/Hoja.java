package com.iesvdc.acceso.excelAPI;

/**
 * @author amacias
 * @version 0.1
 */
public class Hoja {

  private String[][] datos;
  private String     nombre;
  private int nFilas;
  private int nColumnas;

  /**
   * Constructor por defecto de la clase Hoja.
   */
  public Hoja() {
    this.datos     = new String[5][5];
    this.nFilas    = 5;
    this.nColumnas = 5;
    this.nombre    = "";
  }

  /**
   * Constructor parametrizado de la clase Hoja.
   * @param nombre
   * @param nFilas
   * @param nColumnas 
   */
  public Hoja(String nombre, int nFilas, int nColumnas) {
    this.datos     = new String[nFilas][nColumnas];
    this.nombre    = nombre;
    this.nFilas    = nFilas;
    this.nColumnas = nColumnas;
  }

  /**
   * Método que devuelve el dato de la celda que se le indica mediante coordenadas.
   * @param fila
   * @param columna
   * @return dato de la celda convertido a cadena
   */
  public String getDatos(int fila, int columna) {
    // comprobar posiciones
    return datos[fila][columna];
  }

  /**
   * Método que inserta como dato la cadena que se le pasa en la celda que se le
   * indica mediante coordenadas.
   * @param dato
   * @param fila
   * @param columna 
   */
  public void setDatos(String dato, int fila, int columna) {
    this.datos[fila][columna] = dato;
  }

  /**
   * Método que devuelve el nombre de una hoja.
   * @return nombre de la hoja
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * Método que inserta como nombre la cadena que se le pasa.
   * @param nombre 
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Método que devuelve el número de filas de una hoja.
   * @return número de filas de una hoja
   */
  public int getNFilas() {
    return nFilas;
  }

  /**
   * Método que devuelve el número de columnas de una hoja.
   * @return Número de columnas.
   */
  public int getNColumnas() {
    return nColumnas;
  }

  /**
   * Método que indica si ambas hojas son iguales o no.
   * @param hoja
   * @return booleano que indica o no la igualdad de ambas hojas.
   */
  public boolean compare(Hoja hoja) {
    boolean iguales = true;

      if (this.nColumnas == hoja.getNColumnas()
          && this.nFilas == hoja.getNFilas()
          && this.nombre.equals(hoja.getNombre())) {

        for (int i = 0; i < this.nFilas; i++) {
          for (int j = 0; j < this.nColumnas; j++) {
            if (this.datos[i][j].equals((hoja.getDatos(i, j)))) {
              iguales = false;
              break;
            }
          }
          if (!iguales) {
            break;
          }
        }

      }
      else {
        iguales = false;
      }
      
      return iguales;
    }

}