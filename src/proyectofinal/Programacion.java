
package proyectofinal;

import javax.swing.JOptionPane;

/**
   * Fecha de creacion: 9 de mayo
   *
   * Esta clase, representa la programación que posee cualquier sala de un complejo, o la programación de todo el complejo en general.
   * @author Jose Manuel Quintero Rodriguez.
   * @author Juan Ángel Riaño Quintero.
   *
   */
public class Programacion {

	 // Atributos de la clase.
	 int sillasReservadas;
	 String horario;
	 int numeroSala;
	 Pelicula peli;

	 // La matriz de sillas[], siempre será una matriz 5x5, osea que todas las salas tendran el mismo tamaño.
	 boolean sillas[][] = new boolean[5][5];

//	 Pelicula pl = new Pelicula();
//	 Complejo cp = new Complejo();
//	 Sala sl = new Sala();

	 /**
		 * Este método constructor se encarga de realizar asignación de valores a los atributos.
		 * @author Jose Manuel Quintero Rodriguez. 
		 * 
		 * @param numeroSala es el ID de la sala a la cual pertenece la programación. 
		 * 
		 */
	 public Programacion(int numeroSala) {

			this.numeroSala = numeroSala;
			horario = "";		
			sillasReservadas = 0;
	  }

	 public void pedirDatosProgramacion() {
			
			peli = new Pelicula();
			peli.pedirInfoPelicula();
			horario = JOptionPane.showInputDialog("Ingrese la hora en la que se proyectará la película: ");
			
	 }


//	 public void crearProgramacion() {

//			String listadoSalas = "";
//			for (int i = 0; i < cp.salas.length; i++) {
//				 if (this.numeroSala == cp.salas[i].numeroSala) {
//						listadoSalas += cp.salas[i].numeroSala;
//				 } else {
//						JOptionPane.showMessageDialog(null, "Esa sala no existe");
//				 }
//			}
//			nombrePelicula = JOptionPane.showInputDialog("Ingrese nombre de la pelicula");
//			// Generar el mapa de la sala sin asientos ocupados
//			for (int i = 0; i < sl.cantidadFilas; i++) {
//				 for (int j = 0; j < sl.sillasPorFila; j++) {
//						sillas[i][j] = true;
//				 }
//			}
//			String espacios = "";
//			for (int i = 0; i < sl.cantidadFilas; i++) {
//				 for (int j = 0; j < sl.sillasPorFila; j++) {
//						espacios += sillas[i][j] + " ";
//				 }
//				 espacios += "\n";
//			}
//			JOptionPane.showMessageDialog(null, espacios);
//	 }
//
//	 /*Método encargado de mostrar el mapa de una sala en un estado temporal durante la programacion de un filme*/
//	 public void mostrarMapaProgramado(int idSala) {
//			boolean existencia = false;
//			String muestreMapa = "";
//			for (int i = 0; i < cp.salas.length; i++) { // Recorremos el arreglo de la sala
//				 if (cp.salas[i] != null) { // Verificamos si la sala existe
//						if (cp.salas[i].numeroSala == idSala) { // Verificamos si existe la sala
//							 existencia = true;
//							 for (int j = 0; j < cp.salas[i].cantidadFilas; j++) {
//									for (int k = 0; k < cp.salas[i].sillasPorFila; k++) {
//										 try {
//												muestreMapa += sl.programaciones[i].sillas[j][k] + " ";
//										 } catch (NullPointerException ex) {
//												JOptionPane.showMessageDialog(null, "No existen programaciones registradas");
//										 }
//									}
//									muestreMapa += "\n";
//							 }
//						}
//				 } else {
//						JOptionPane.showMessageDialog(null, "No hay salas registradas en este complejo", "ERROR", JOptionPane.ERROR_MESSAGE);
//						break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
//				 }
//			} // En caso de que la existencia de esa sala especifica sea falsa
//			if (existencia == true) {
//				 JOptionPane.showMessageDialog(null, muestreMapa);
//			}
//	 }
}
