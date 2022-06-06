package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * Esta clase se encarga de pedir los datos correspodientes a una sala, teniendo en cuenta que para registrar una sala tiene que haber al menos un complejo registrado, en donde al suministrar los datos correspodiente detallamos la forma en como será el mapa de respectiva sala, esta clase también se encarga de calcular el porcentaje de ocupación.
 *
 * @author Jose Manuel Quintero Rodríguez.
 * @author Juan Ángel Riaño Quintero.
 *
 */
public class Sala {

	// Atributos de la clase.
	int numeroSala, cantidadFilas, sillasPorFila;
	double valorBoleta;

	// Declaramos el arreglo de programaciones que serán como máximo 5.
	Programacion programaciones[] = new Programacion[5];

	/**
	 * Este es el método constructor de la clase que se encarga de inicializar los valoes de los atributos, Cabe destacar que la inicializacion tambien sirve para que al momento de crear un objeto de tipo Sala y guardarlo en un arreglo de objetos, este no se almacene con datos nulos.
	 *
	 * @author José Manuel Quintero Rodriguez.
	 *
	 */
	public Sala() {

		numeroSala = 0;
		cantidadFilas = 5;
		sillasPorFila = 5;
		valorBoleta = 0;
	}

	/**
	 * Este método se encarga de solicitar los datos de la sala además de definir el diseño del mapa de la sala.
	 *
	 * @author Jose Manuel Quintero Rodriguez.
	 *
	 */
	public void pedirInfoSala() {

		// El identificador de la sala se volverá autoincrementable.
		numeroSala = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el identificador de la sala:"));

		do {
			valorBoleta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de la boleta: "));

			if (valorBoleta < 0) {
				JOptionPane.showMessageDialog(null, "No se permiten valores negativos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (valorBoleta < 0);
	}	
	
	/**
	 * Este método, permite al usuario ingresar la cantidad de programaciones que desee.
	 * @author Juan Ángel Riaño Quintero.
	 * 
	 */
	public void ingresarProgramacion() {
	
		int nProgram, contIngresos = 0;  // nProgram, es el número de programaciones que el usuario quiera registrar en el momento.		
		do {
			nProgram = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos complejos desea ingresar?"));
			
			if (nProgram > programaciones.length) {	
				JOptionPane.showMessageDialog(null, "No se pueden ingresar más complejos de los que tiene la empresa", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (nProgram > programaciones.length);
		
		for (int i = 0; i < programaciones.length; i++) {
			
			if (contIngresos == nProgram) {
				break;
			}
			
			if (programaciones[i] == null) {
				programaciones[i] = new Programacion(numeroSala);
				programaciones[i].pedirInfoProgramacion();
				contIngresos += 1;
				JOptionPane.showMessageDialog(null, "Complejo registrado exitosamente");
			}
			
			if (programaciones[i] != null && i >= programaciones.length && contIngresos == 0) {			
				JOptionPane.showMessageDialog(null, "Ya hay " + programaciones.length + " complejos registrados", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			}
		}
	}


	/**
	 * Este metodo se encarga de mostrar la programación que tiene la sala para un día.
	 *
	 * @author Jose Manuel Quintero Rodriguez.
	 *
	 */
	public void mostrarProgramacion() {

		// Creamos un arreglo que almacenará un listado de películas en cartelera.
		String listadoPeliculas = "";
		boolean existencia = false;

		// Recorremos el arreglo de programaciones.
		for (int i = 0; i < programaciones.length; i++) {

			// Verificamos que el objeto Película en la posición indicada, exista en el arreglo.
			if (programaciones[i] != null) {

				if (programaciones[i].peli.actividad == true) {

					// Indicamos si la pelicula está activa para agregarla a la programación.
					existencia = true;
					listadoPeliculas += (i + 1) + ". " + programaciones[i].peli.nomEspanol
					+ "\nHorario: " + programaciones[i].horario + "\nNumero de la sala: " + numeroSala;
				}
			} else {

				JOptionPane.showMessageDialog(null, "No se puede mostrar la programación\n"
				+ "si no hay peliculas registradas", "ERROR", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		// Mostramos el listado con la programación.
		if (existencia == true) {
			JOptionPane.showMessageDialog(null, listadoPeliculas);
		}
	}

	/* Este método se encarga de hacer la reserva en de una silla en una sala
		@author Jose Manuel Quintero Rodriguez*/
	public void reservarAsiento(int id) {

		boolean existencia = false;
		int controladorAlertas = 0;
		int confirmarReserva; // variable que indica si la confimacion de la reserva
		int confirmador, espFila, espColumna;
		for (int i = 0; i < programaciones.length; i++) {
			if (programaciones[i].numeroSala == id) {
				do {
					espFila = Integer.parseInt(JOptionPane.showInputDialog("Dime el numero de fila"));
					if (espFila < 0 || espFila > programaciones[i].sillas.length - 1) {
						JOptionPane.showMessageDialog(null, "No se permite el número", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} while (espFila < 0 || espFila > programaciones[i].sillas.length - 1);
				do {
					espColumna = Integer.parseInt(JOptionPane.showInputDialog("Dime el numero de columna"));
					if (espColumna < 0 || espColumna > programaciones[i].sillas[0].length - 1) {
						JOptionPane.showMessageDialog(null, "No se permite el número", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} while (espColumna < 0 || espColumna > programaciones[i].sillas[0].length - 1);
				programaciones[i].sillas[espFila][espColumna] = false;
				break;
			}
		}

//        for (int i = 0; i < sillas.length; i++) { // Recorremos el arreglo de la sala
//                if (numeroSala == id) { // Verificamos si existe la sala
//                    existencia = true;
//                    for (int j = 0; j < salas[i].cantidadFilas; j++) { // Recorremos las filas 
//                        for (int k = 0; k < salas[i].sillasPorFila; k++) {
//                            sl.programaciones[i].sillas[j][k] = true; // Por defecto decimos que la silla
//                            if (sl.programaciones[i].sillas[j][k] == true) {
//                                confirmarReserva = JOptionPane.showConfirmDialog(null, ""
//                                        + "¿Quiere reservar el asiento [" + j + "][" + k + "] ?", "", JOptionPane.YES_NO_OPTION);
//                                if (confirmarReserva == JOptionPane.YES_OPTION) {
//                                    sl.programaciones[i].sillas[j][k] = false; // Ocupamos la silla
//                                } else {
//                                    if (confirmarReserva == JOptionPane.NO_OPTION) {
//                                        break; // Rompe el ciclo interno
//                                    }
//                                }
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Esta silla está ocupada", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//                            }
//                        }
//                    }
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "No hay salas registradas en este complejo", "ERROR", JOptionPane.ERROR_MESSAGE);
//                controladorAlertas = 1;
//                break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
//            }
//        } // En caso de que la existencia de esa sala especifica sea falsa
//        if (existencia == false && controladorAlertas == 0) {
//            JOptionPane.showMessageDialog(null, "No se encontró la sala", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
	}

	public void mostrarMapaSala(int id) {
		boolean existencia = false;
		String muestreMapa = "";
		for (int i = 0; i < programaciones.length; i++) { // Recorremos el arreglo de la sala
			if (programaciones[i].numeroSala == id) { // Verificamos si la sala existe
				for (int j = 0; j < programaciones[i].sillas.length; j++) {
					for (int k = 0; k < programaciones[i].sillas[0].length; k++) {
						muestreMapa += programaciones[i].sillas[j][k] + " ";
					}
					muestreMapa += "\n";
				}
				JOptionPane.showMessageDialog(null, muestreMapa);
			} else {
				JOptionPane.showMessageDialog(null, "No existe una sala con ese número", "ERROR", JOptionPane.ERROR_MESSAGE);
				break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
			}
		} // En caso de que la existencia de esa sala especifica sea falsa
		if (existencia == true) {
			JOptionPane.showMessageDialog(null, muestreMapa);
		}
	}

	/**
	 * Este método se encarga de calcular el porcetaje de ocupación en una sala especifical, tomando como parametro de entrada el numero de personas que visitaron dicha sala.
	 *
	 * @author Jose Manuel Quintero Rodriguez.
	 *
	 * @param nPersonas es el parametro que nos indica cuatas reservas se hicieron para una película.
	 *
	 * @return El metodo debe retornar un valor Real, el cual será el porcentaje de ocupación.
	 *
	 */
	public double calcularPorcOcupacion(int nPersonas) {

		double porcentaje = 0;

		// Verfificamos que la cantidad de personas no sea mayor al numero de sillas de la sala
		if (nPersonas > (cantidadFilas * sillasPorFila)) {
			JOptionPane.showMessageDialog(null, "No pueden haber mas personas que sillas en la sala", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			porcentaje = nPersonas * 100 / (cantidadFilas * sillasPorFila);
		}
		return porcentaje;
	}
}
