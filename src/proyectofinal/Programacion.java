package proyectofinal;

import javax.swing.JOptionPane;
// Importar el formato que genera la hora actual en la que se ejecuta el programa
import java.time.LocalTime;

/**
 * Fecha de creacion: 9 de mayo
 *
 * @authores Jose Manuel Quintero Rodriguez
 * 
 * Para esta clase necesitamos tomar el atributo id de sala por lo cual se hace la herencia de la clase Sala
 * Esta clase también se encarga de mostrar la programación del día, mostrar la información completa de una 
 * pelicula y actualizar los datos de una pelicula especifica.
 */
public class Programacion{

    // Atributos de la clase
    int sillasReservadas, numeroSala;
    // Establecemos el formato de la hora para el horario
    LocalTime horario = LocalTime.now();
    String nombrePelicula;
    Pelicula[] peliculas = new Pelicula[2];
    boolean sillas[][] = new boolean[5][5];
    
    Pelicula pl = new Pelicula();
    Complejo cp = new Complejo();
    Sala sl = new Sala();
    
    /*Este método constructor se encarga de realizar asignación de valores a los atributos
    @author Jose Manuel Quintero Rodriguez*/
    public Programacion() {
        numeroSala = 0;
    }
    
    public void crearProgramacion(){
        numeroSala = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la sala"));
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
