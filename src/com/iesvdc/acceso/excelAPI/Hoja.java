package com.iesvdc.acceso.excelAPI;

/**
 * @author amacias
 * @version 0.1
 */
public class Hoja {

  private String[][] datos;
  private String nombre;
  private int nFilas;
  private int nColumnas;

  public Hoja() {
    this.datos = new String[5][5];
    this.nFilas = 5;
    this.nColumnas = 5;
    this.nombre = "";
  }

  public Hoja(String nombre, int nFilas, int nColumnas) {
    this.datos = new String[nFilas][nColumnas];
    this.nombre = nombre;
    this.nFilas = nFilas;
    this.nColumnas = nColumnas;
  }

  public String getDatos(int fila, int columna) {
    // comprobar posiciones
    return datos[fila][columna];
  }

  public void setDatos(String dato, int fila, int columna) {
    // comprobar posiciones
    this.datos[fila][columna] = dato;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getNFilas() {
    return nFilas;
  }

  public int getNColumnas() {
    return nColumnas;
  }

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