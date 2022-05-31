
package proyectofinal;

import java.time.LocalTime;
import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 9 de mayo
 * @author Jose Manuel Quintero Rodriguez, Juan Angel Riaño
 * 
 * Esta clase, representa la programación que posee cualquier sala de un complejo,
 * o la programación de todo el complejo en general.
 * 
 */
public class Programacion {

    // Atributos de la clase
    int sillasReservadas;
    // Establecemos el formato de la hora para el horario
    LocalTime horario = LocalTime.now();
    String nombrePelicula, numeroSala;
    Pelicula[] peliculas = new Pelicula[2];
    boolean sillas[][] = new boolean[5][5];
    
    Pelicula pl = new Pelicula();
    Complejo cp = new Complejo();
    Sala sl = new Sala();
    
    /*Este método constructor se encarga de realizar asignación de valores a los atributos
    @author Jose Manuel Quintero Rodriguez*/
    public Programacion() {
        numeroSala = "";
    }
    
    public void crearProgramacion(){
        String listarSalas = "";
        // Con este ciclo se irán listando las salas a seleccionar
        for (int i = 0; i < cp.salas.length; i++) {
            if (cp.salas[i] != null) {
                listarSalas += cp.salas[i].numeroSala + ", ";
                if (i == cp.salas.length-1){
                    listarSalas += cp.salas[i].numeroSala;
                }
            }
        }
        numeroSala = (JOptionPane.showInputDialog(null, "Elija el numero de la sala", "Salas",
                JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Seleccionar", listarSalas}, "")).toString();
        nombrePelicula = JOptionPane.showInputDialog("Ingrese nombre de la pelicula");
        // Generar el mapa de la sala sin asientos reservados
        for (int i = 0; i < sl.cantidadFilas; i++) {
            for (int j = 0; j < sl.sillasPorFila; j++) {
                sillas[i][j] = true;
            }
        }
        String espacios = "";
        for (int i = 0; i < sl.cantidadFilas; i++) {
            for (int j = 0; j < sl.sillasPorFila; j++) {
                espacios += sillas[i][j] + " ";
            }
            espacios += "\n";
        }
        JOptionPane.showMessageDialog(null, espacios);
    }
    /*Método encargado de mostrar el mapa de una sala en un estado temporal durante la programacion de un filme*/
    public void mostrarMapaProgramado(int idSala){
        boolean existencia = false;
        String muestreMapa = "";
        for (int i = 0; i < cp.salas.length; i++) { // Recorremos el arreglo de la sala
            if (cp.salas[i] != null) { // Verificamos si la sala existe
                if (cp.salas[i].numeroSala == idSala) { // Verificamos si existe la sala
                    existencia = true;
                    for (int j = 0; j < cp.salas[i].cantidadFilas; j++) {
                        for (int k = 0; k < cp.salas[i].sillasPorFila; k++) {
                            try {
                                muestreMapa += sl.programaciones[i].sillas[j][k] + " ";
                            } catch(NullPointerException ex){
                                JOptionPane.showMessageDialog(null, "No existen programaciones registradas");
                            }
                        }
                        muestreMapa += "\n";
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay salas registradas en este complejo", "ERROR", JOptionPane.ERROR_MESSAGE);
                break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
            }
        } // En caso de que la existencia de esa sala especifica sea falsa
        if (existencia == true) {
            JOptionPane.showMessageDialog(null, muestreMapa);
        }
    }
}