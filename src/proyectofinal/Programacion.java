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
    
    Pelicula pl = new Pelicula();

    /*Este método constructor se encarga de realizar asignación de valores a los atributos
    @author Jose Manuel Quintero Rodriguez*/
    public Programacion() {
        numeroSala = 0;
    }
    
    public void crearProgramacion(){
        numeroSala = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la sala"));
        
    }
}
