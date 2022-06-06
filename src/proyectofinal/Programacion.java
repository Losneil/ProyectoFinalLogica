package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 9 de mayo
 *
 * Esta clase, representa la programación que posee cualquier sala de un
 * complejo, o la programación de todo el complejo en general.
 *
 * @author Jose Manuel Quintero Rodriguez.
 * @author Juan Ángel Riaño Quintero.
 *
 */
public class Programacion {

    // Atributos de la clase.
    String horario;
    int numeroSala;
    Pelicula peli;

    // La matriz de sillas[], siempre será una matriz 5x5, osea que todas las salas tendran el mismo tamaño.
    boolean sillas[][] = new boolean[5][5];

//	 Pelicula pl = new Pelicula();
//	 Complejo cp = new Complejo();
//	 Sala sl = new Sala();
    /**
     * Este método constructor se encarga de realizar asignación de valores a
     * los atributos.
     *
     * @author Jose Manuel Quintero Rodriguez.
     *
     * @param numeroSala es el ID de la sala a la cual pertenece la
     * programación.
     *
     */
    public Programacion(int numeroSala) {
        this.numeroSala = numeroSala;
        horario = "";
        for (int i = 0; i < sillas.length; i++) {
            for (int j = 0; j < sillas[0].length; j++) {
                sillas[i][j] = true;
            }
        }
    }

    /**
     * Método encargado de pedir datos de una programación en el cual se piden
     * también los datos de una pelicula
     *
     * @author Juan Angel Riaño Quintero.
     */
    public void pedirInfoProgramacion() {
        peli = new Pelicula();
        peli.pedirInfoPelicula();
        if (peli.actividad == true) {
            horario = JOptionPane.showInputDialog("Ingrese la hora en la que se proyectará la película: ");
        } else {
            JOptionPane.showMessageDialog(null, "La película no se agregó a la programación");
        }

    }

    public void crearProgramacion() {
        
    }
}
