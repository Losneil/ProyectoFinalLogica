
package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 9 de mayo
 * @author Jose Manuel Quintero Rodriguez
 * 
 * Esta clase, representa la programación que posee cualquier sala de un complejo,
 * o la programación de todo el complejo en general.
 * 
 */
public class Programacion {

	// Atributos de la clase.
	int sillasReservadas, numeroSala;
	String nombrePelicula, horario;
	boolean sillas[][] = new boolean[5][5];
	Pelicula pl;
	Complejo cp = new Complejo();
	Sala sl = new Sala();

	/**
	 * Este es el método constructor que inicializa los valores de los atributos.
	 * @author Jose Manuel Quintero Rodriguez.
	 * 
	 */
	public Programacion() {
		
		numeroSala = 0;
		sillasReservadas = 0;
		nombrePelicula = "";
		horario = "";
	}
	
	public void crearProgramacion() {
		
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

	/**
	 *  Este método es el encargado de mostrar el mapa de una sala en un estado temporal durante la programacion de un filme.
	 * @author Jose Manuel Quintero Rodriguez
	 * @param idSala es el numero identificador de la sala, el cual se requiere para poder mostrar su mapa.
	 * 
	 */
	public void mostrarMapaProgramado(int idSala) {
		
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
							} catch (NullPointerException ex) {
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
