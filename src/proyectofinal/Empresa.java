package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 27 de mayo
 *
 * @author José Manuel Quintero Rodríguez
 * @author Juan Angel Riaño Quintero
 *
 */
public class Empresa {

	/**
	 * Creamos los arreglos necesarios que va a tener esta clase empresa
	 *
	 */
	Usuario usuarios[] = new Usuario[2];
	Complejo complejos[];
	Pelicula peliculas[];
	Reserva rv = new Reserva();

	/**
	 * Método constructor de la clase
	 *
	 * @param nc
	 */
	public Empresa(int nc) {
		complejos = new Complejo[nc];
	}

	/**
	 * @author José Manuel Quintero Rodríguez
	 *
	 * @param id el cual es el parametro que se necesita para especificar la sala de la cual se deseea conocer su estado
	 */
	public void mostrarMapaSala(int id) {
		boolean existencia = false;
		String muestreMapa = "";
		for (int i = 0; i < complejos.length; i++) { // Recorremos el arreglo de la sala
			if (complejos[i].salas[i].programaciones[i] != null) {
				if (complejos[i].salas[i].programaciones[i].numeroSala == id) { // Verificamos si la sala existe
					for (int j = 0; j < complejos[i].salas[i].sillas.length; j++) {
						for (int k = 0; k < complejos[i].salas[i].sillas[0].length; k++) {
							muestreMapa += complejos[i].salas[i].sillas[j][k] + " ";
						}
						muestreMapa += "\n";
					}
					JOptionPane.showMessageDialog(null, muestreMapa);
				} else {
					JOptionPane.showMessageDialog(null, "No existe una sala con ese número", "ERROR", JOptionPane.ERROR_MESSAGE);
					break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
				}
			} else {
				JOptionPane.showMessageDialog(null, "Programacion Inexistente", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				break;
			}

		} // En caso de que la existencia de esa sala especifica sea falsa
		if (existencia == true) {
			JOptionPane.showMessageDialog(null, muestreMapa);
		}
	}

	/**
	 * Este método se encarga de registrar a los dos usuarios que usarán el sistema.
	 *
	 * @author José Manuel Quintero Rodriguez.
	 */
	public void registrarUsuarios() {
		String nick, password, tipoUser;
		for (int i = 0; i < usuarios.length; i++) {
			// Creamos el objeto con datos inicializados
			usuarios[i] = new Usuario();
			if (i == 0) {
				tipoUser = "Administrador";
			} else {
				tipoUser = "Cajero";
			}
			nick = JOptionPane.showInputDialog("Registre nombre para el " + tipoUser);
			password = JOptionPane.showInputDialog("Registre contrasena para el " + tipoUser);
			usuarios[i].setNombre(nick);
			usuarios[i].setContrasena(password);
			// Esta es la forma mas simple de hacerlo
//            users[i].nombre = JOptionPane.showInputDialog("Registre nombre para cajero " + (i + 1));
//            users[i].contrasena = JOptionPane.showInputDialog("Registre contraseña para cajero " + (i + 1));
		}
	}

	/*Este método se encarga de insertar las peliculas en la programación, 
		siempre y cuando estas se encuentren activas para agregarlas a la programacion
		@author José Manuel Quintero Rodríguez*/
	public void ingresarPelicula() {
		for (int i = 0; i < peliculas.length; i++) {
			if (peliculas[i] == null) { // Primero verificamos que no halla un objeto Pelicula en esta posicion
				// En la posicion i agregamos un nuevo objeto de pelicula con datos inicializados
				peliculas[i] = new Pelicula();
				// Solicitamos informacion de la pelicula
				peliculas[i].pedirInfoPelicula();
				/* Ciclo que ayuda a comprobar que no hallan peliculas a proyectar en el mismo horario
                    en la sala que se está gestionando*/
				for (int j = 0; j < i; j++) {
					if (peliculas[j].nomEspanol.equals(peliculas[i].nomEspanol)) {
						JOptionPane.showMessageDialog(null, "Ya existe esta pelicula en la programacion", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						i--; // Pasamos a la posicion anterior y volvemos a la misma para sobreescribir el nombre
						break; // Aqui rompemos el ciclo interno que maneja el iterador j
					}
				}
			} else { // Condicional que evalua si el arreglo de objetos Pelicula está lleno
				JOptionPane.showMessageDialog(null, "No se pueden registrar mas peliculas", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
	}

	/**
	 * Método para ingresar un complejo teniendo en cuenta la cantidad de los mismos que se desea ingresar
	 *
	 * @author Juan Angel Riaño Quintero
	 *
	 */
	public void ingresarComplejo() {
		
		int controlExcep ,nComplejos = 0, contIngresos = 0;  // nComplejos, es el número de programaciones que el usuario quiera registrar en el momento.		
		
		do {
			do {
				try {
					controlExcep = 0;
					nComplejos = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos complejos desea ingresar?"));				
				
					if (nComplejos > complejos.length) {	
						JOptionPane.showMessageDialog(null, "No se pueden ingresar más complejos de los que tiene la empresa",
						"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un número", "ERROR", JOptionPane.ERROR_MESSAGE);
					controlExcep = 1;
				}					
			} while (nComplejos > complejos.length);	
		} while (controlExcep == 1);
		
		for (int i = 0; i < complejos.length; i++) {
			
			if (contIngresos == nComplejos) {
				break;
			}
			
			if (complejos[i] == null) {
				complejos[i] = new Complejo();
				complejos[i].pedirInfoComplejo();
				contIngresos += 1;
				JOptionPane.showMessageDialog(null, "Complejo registrado exitosamente");
			}
			
			if (complejos[i] != null && i >= complejos.length && contIngresos == 0) {			
				JOptionPane.showMessageDialog(null, "Ya hay " + complejos.length + " complejos registrados", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

//		for (int i = 0; i < complejos.length; i++) {
//			if (complejos[i] == null) { // Primero verificamos que no halla un objeto complejo en esta posicion
//				// En la posicion i agregamos un nuevo objeto de complejo con datos inicializados
//				complejos[i] = new Complejo();
//				// Solicitamos informacion de la pelicula
//				complejos[i].pedirInfoComplejo();
//				/* Ciclo que ayuda a comprobar que no hallan complejos a proyectar en el mismo horario
//								en la sala que se está gestionando*/
//				for (int j = 0; j < i; j++) {
//					if (complejos[j].nombre.equals(complejos[i].nombre)) {
//						JOptionPane.showMessageDialog(null, "Ya existe esta pelicula en la programacion", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//						i--; // Pasamos a la posicion anterior y volvemos a la misma para sobreescribir el nombre
//						break; // Aqui rompemos el ciclo interno que maneja el iterador j
//					}
//				}
//			} else { // Condicional que evalua si el arreglo de objetos Pelicula está lleno
//				JOptionPane.showMessageDialog(null, "No se pueden registrar mas peliculas", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//				break;
//			}
//		}
//	}
	/**
	 * Este metodo se encarga de mostrar la informacion una pelicula especificando el nombre de la misma dentro del arreglo de objetos Pelicula
	 *
	 * @author Jose Manuel Quintero Rodriguez
	 * @param nombre que el parametro que se necesita para comprobar que el nombre ingresado concuerde con alguno del arreglo
	 */
	public void mostrarInfoPelicula(String nombre) {
		// El indicador por defecto es falso, pero si se encuentra una pelicula, su valor cambia a verdadero
		boolean indicador = false;
		int controladorAlertas = 0; // Variable que ayuda a controlar las alertas por accion realizada
		for (int i = 0; i < peliculas.length; i++) { // Recorremos el arreglo de objetos Pelicula
			if (peliculas[i] != null) { // Verificamos que si existe el objeto de Pelicula en la posición indicada
				if (peliculas[i].nomEspanol.equalsIgnoreCase(nombre)) {
					indicador = true;
					JOptionPane.showMessageDialog(null, "Nombre en español: " + peliculas[i].nomEspanol
					+ "\nAño de estreno: " + peliculas[i].anioEstreno
					+ "\nDuracion en minutos: " + peliculas[i].duracionMinutos
					+ "\nNombre original: " + peliculas[i].nomOriginal
					+ "\nGenero: " + peliculas[i].obtenerNombreGenero()
					+ "\nPais de origen: " + peliculas[i].paisOrigen);
				}
			} else {
				JOptionPane.showMessageDialog(null, "No hay peliculas registradas", "ERROR", JOptionPane.ERROR_MESSAGE);
				controladorAlertas = 1;
				break;
			}
		} // Si al recorrer el ciclo no se encontro ninguna pelicula con el nombre esepcificado
		if (indicador == false && controladorAlertas == 0) {
			JOptionPane.showMessageDialog(null, "No se encontraron peliculas con el nombre especificado", "ADVERTENCIA",
			JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Este método se encarga de actualizar cualquier dato de una pelicula especificando el nombre de la misma
	 *
	 * @author Jose Manuel Quintero Rodriguez
	 * @param nombre
	 */
	public void actualizarDatosPelicula(String nombre) {
		int opcionCambio, controladorAlertas = 0;
		boolean existencia = false;
		for (int i = 0; i < peliculas.length; i++) { // Recorremos el arreglo de objetos Pelicula
			if (peliculas[i] != null) { // Comprobamos que si exista la pelicula en la posicion indicada
				if (peliculas[i].nomEspanol.equalsIgnoreCase(nombre)) { // Comprobamos el nombre con el parametro ingresado
					existencia = true;
					do {
						opcionCambio = Integer.parseInt(JOptionPane.showInputDialog("¿Que dato desea cambiar?"
						+ "\n1. Nombre en español"
						+ "\n2. Año de estreno"
						+ "\n3. Duracion en minutos"
						+ "\n4. Nombre original"
						+ "\n5. Pais de origen"
						+ "\n6. Genero"));
						if (opcionCambio < 1 || opcionCambio > 6) {
							JOptionPane.showMessageDialog(null, "No existe ese dato de la pelicula", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} while (opcionCambio < 1 || opcionCambio > 6);
					switch (opcionCambio) {
						case 1: // Pedimos nuevos valores para los atributos de la pelicula en la posicion especificada
							peliculas[i].nomEspanol = JOptionPane.showInputDialog("Actualize el nombre en español");
							break;
						case 2:
							do {
								peliculas[i].anioEstreno = Integer.parseInt(JOptionPane.showInputDialog("Actualize el año de estreno"));
								/* Condicional que evalua si se está en el año actual
																@author Juan Angel Riano Quintero*/
								if (peliculas[i].anioEstreno < 1900) {
									JOptionPane.showMessageDialog(null, "No existen peliculas filmadas antes de 1900",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
								}
								if (peliculas[i].anioEstreno > 2022) {
									JOptionPane.showMessageDialog(null, "Estas especificando un año que aún no ha pasado",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
								}
							} while (peliculas[i].anioEstreno < 1900 || peliculas[i].anioEstreno > 2022);
							peliculas[i].anioEstreno = Integer.parseInt(JOptionPane.showInputDialog("Actualize el año de estreno"));
							break;
						case 3:
							do { // Comprobar que la duración corresponde a la de un largometraje
								peliculas[i].duracionMinutos = Integer.parseInt(JOptionPane.showInputDialog("Actualize la duración en minutos"));
								if (peliculas[i].duracionMinutos < 60) {
									JOptionPane.showMessageDialog(null, "Para que sea una pelicula debe durar mas de 60 minutos",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
								}
							} while (peliculas[i].duracionMinutos < 60);
							break;
						case 4:
							peliculas[i].nomOriginal = JOptionPane.showInputDialog("Actualize el nombre original");
							break;
						case 5:
							peliculas[i].paisOrigen = JOptionPane.showInputDialog("Actualize el pais de origen");
							break;
						case 6:
							do {
								peliculas[i].genero = Integer.parseInt(JOptionPane.showInputDialog("¿A qué genero desea cambiarlo?"
								+ "\n1. Drama"
								+ "\n2. Suspenso"
								+ "\n3. Terror"
								+ "\n4. Acción"
								+ "\n5. Comedia"
								+ "\n6. Infantil"));
								if (peliculas[i].genero < 1 || peliculas[i].genero > 6) {
									JOptionPane.showMessageDialog(null, "Genero no identificado", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
							} while (peliculas[i].genero < 1 || peliculas[i].genero > 6);
						default:
							break;
					}
				}
			} else { // Condicional que evalúa si el arreglo de objetos Pelicula está vacío
				JOptionPane.showMessageDialog(null, "No hay peliculas registradas", "ERROR", JOptionPane.ERROR_MESSAGE);
				controladorAlertas = 1; // Con esta asignacion aseguro que se muestre este mensaje y no el posterior
				break;
			}
		} // Si no se encontraron peliculas con el nombre indicado
		if (existencia == false && controladorAlertas == 0) {
			JOptionPane.showMessageDialog(null, "No existe la pelicula con el nombre especificado", "ADVERTENCIA",
			JOptionPane.WARNING_MESSAGE);
		}
	}
}
