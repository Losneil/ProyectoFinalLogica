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
	int numeroSala, sillasPorFila, cantidadFilas;
	double valorBoleta;
	boolean sillas[][] = new boolean[5][5];
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
		valorBoleta = 0;
		sillasPorFila = sillas[0].length;
		cantidadFilas = sillas.length;
		// Llenamos toda la sala de cine con la cantidad de sillas predefinida
	}

	/**
	 * Este método se encarga de solicitar los datos de la sala además de definir el diseño del mapa de la sala.
	 *
	 * @author Jose Manuel Quintero Rodriguez.
	 *
	 */
	public void pedirInfoSala() {
		int controlExcep;

		do {// El identificador de la sala se volverá autoincrementable.
			try {
				controlExcep = 0;
				numeroSala = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el identificador de la sala:"));

				do {
					valorBoleta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de la boleta: "));

					if (valorBoleta < 0) {
						JOptionPane.showMessageDialog(null, "No se permiten valores negativos", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} while (valorBoleta < 0);

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Debe ingresar un numero",
				"ERROR", JOptionPane.ERROR_MESSAGE);
				controlExcep = 1;
			}
		} while (controlExcep == 1);
		// Se irá llenando la matriz de sillas de una sala
		for (int i = 0; i < sillas.length; i++) {
			for (int j = 0; j < sillas[0].length; j++) {
				sillas[i][j] = true;
			}
		}
	}

	/**
	 * Este método, permite al usuario ingresar la cantidad de programaciones que desee.
	 *
	 * @author Juan Ángel Riaño Quintero.
	 *
	 * @param nP es el número de programaciones que el usuario quiere registrar en el momento.
	 *
	 */
	public void ingresarProgramacion(int nP) {

		int nProgram = nP, contIngresos = 0;

		for (int i = 0; i < programaciones.length; i++) {

			if (contIngresos == nProgram) {
				break;
			}

			if (programaciones[i] == null) {
				programaciones[i] = new Programacion(numeroSala);
				programaciones[i].pedirInfoProgramacion();
				contIngresos += 1;

				if (programaciones[i].peli.actividad == true) {
					JOptionPane.showMessageDialog(null, "Programación registrada exitosamente");
				}
			}

			if (programaciones[i] != null && i >= programaciones.length && contIngresos == 0) {
				JOptionPane.showMessageDialog(null, "Ya hay " + programaciones.length + " programaciones registradas", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
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
