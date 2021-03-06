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
    Pelicula peliculas[] = new Pelicula[50];
    Reserva rv = new Reserva();

    /**
     * Método constructor de la clase en donde se asignarán los tamaños de los
     * arreglos Pelicula[] y Complejo[].
     *
     * @author José Manuel Quintero Rodriguez.
     *
     * @param nc es el valor que me indica el tamaño del arreglo de Complejos.
     *
     */
    public Empresa(int nc) {
        complejos = new Complejo[nc];
    }

    /**
     * Este método se encarga de registrar a los dos usuarios que usarán el
     * sistema.
     *
     * @author José Manuel Quintero Rodriguez.
     *
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

    /**
     * Este método se encarga de insertar las peliculas en la programación,
     * siempre y cuando estas se encuentren activas para agregarlas a la
     * programacion
     *
     * @author José Manuel Quintero Rodríguez
     */
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
     * Método para ingresar un complejo, teniendo en cuenta la cantidad de los
     * mismos que se desea ingresar.
     *
     * @author Juan Angel Riaño Quintero
     *
     */
    public void ingresarComplejo() {

        int controlExcep, nComplejos = 0, contIngresos = 0;  // nComplejos, es el número de programaciones que el usuario quiera registrar en el momento.		

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

    /**
     * Este metodo se encarga de mostrar la informacion una pelicula
     * especificando el nombre de la misma dentro del arreglo de objetos
     * Pelicula
     *
     * @author Jose Manuel Quintero Rodriguez
     * @param nombre que el parametro que se necesita para comprobar que el
     * nombre ingresado concuerde con alguno del arreglo
     */
    public void mostrarInfoPelicula(String nombre) {
        // El indicador por defecto es falso, pero si se encuentra una pelicula, su valor cambia a verdadero
        boolean indicador = false;
        int controladorAlertas = 0; // Variable que ayuda a controlar las alertas por accion realizada.

        for (int i = 0; i < complejos.length; i++) {
            if (complejos[i] != null) {
                for (int j = 0; j < complejos[i].salas.length; j++) {
                    if (complejos[i].salas[j] != null) {
                        for (int k = 0; k < complejos[i].salas[j].programaciones.length; k++) {
                            if (complejos[i].salas[j].programaciones != null) {
                                if (complejos[i].salas[j].programaciones[k].peli.nomEspanol.equals(nombre)) {
                                    JOptionPane.showMessageDialog(null, "Nombre en español: " + complejos[i].salas[j].programaciones[k].peli.nomEspanol
                                            + "\nAño de estreno: " + complejos[i].salas[j].programaciones[k].peli.anioEstreno
                                            + "\nDuracion en minutos: " + complejos[i].salas[j].programaciones[k].peli.duracionMinutos
                                            + "\nNombre original: " + complejos[i].salas[j].programaciones[k].peli.nomOriginal
                                            + "\nGenero: " + complejos[i].salas[j].programaciones[k].peli.obtenerNombreGenero()
                                            + "\nPais de origen: " + complejos[i].salas[j].programaciones[k].peli.paisOrigen);
                                }
                            }
                        }
                    }
                    if (indicador == false) {
                        break;
                    }
                }
            }
            if (indicador == false) {
                break;
            }
        }
    }

    /**
     * Este método se encarga de actualizar cualquier dato de una pelicula
     * especificando el nombre de la misma
     *
     * @author Jose Manuel Quintero Rodriguez.
     *
     * @param nombre es el nombre de la película que el usuario quiere editar.
     *
     */
    public void actualizarDatosPelicula(String nombre) {
        int opcionCambio;
        boolean existencia = false;
        for (int i = 0; i < complejos.length; i++) {
            if (complejos[i] != null) {
                for (int j = 0; j < complejos[i].salas.length; j++) {
                    if (complejos[i].salas[j] != null) {
                        for (int k = 0; k < complejos[i].salas[j].programaciones.length; k++) {
                            if (complejos[i].salas[j].programaciones != null) {
                                if (complejos[i].salas[j].programaciones[k].peli.nomEspanol.equals(nombre)) {
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
                                            complejos[i].salas[j].programaciones[k].peli.nomEspanol = JOptionPane.showInputDialog("Actualize el nombre en español");
                                            break;

                                        case 2:
                                            do {
                                                complejos[i].salas[j].programaciones[k].peli.anioEstreno = Integer.parseInt(JOptionPane.showInputDialog("Actualize el año de estreno"));
                                                /* Condicional que evalua si se está en el año actual
																@author Juan Angel Riano Quintero*/
                                                if (complejos[i].salas[j].programaciones[k].peli.anioEstreno < 1900) {
                                                    JOptionPane.showMessageDialog(null, "No existen peliculas filmadas antes de 1900",
                                                            "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                                }
                                                if (complejos[i].salas[j].programaciones[k].peli.anioEstreno > 2022) {
                                                    JOptionPane.showMessageDialog(null, "Estas especificando un año que aún no ha pasado",
                                                            "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                                }
                                            } while (complejos[i].salas[j].programaciones[k].peli.anioEstreno < 1900 || complejos[i].salas[j].programaciones[k].peli.anioEstreno > 2022);

                                            complejos[i].salas[j].programaciones[k].peli.anioEstreno = Integer.parseInt(JOptionPane.showInputDialog("Actualize el año de estreno"));
                                            break;

                                        case 3:
                                            do { // Comprobar que la duración corresponde a la de un largometraje
                                                complejos[i].salas[j].programaciones[k].peli.duracionMinutos = Integer.parseInt(JOptionPane.showInputDialog("Actualize la duración en minutos"));
                                                if (complejos[i].salas[j].programaciones[k].peli.duracionMinutos < 60) {
                                                    JOptionPane.showMessageDialog(null, "Para que sea una pelicula debe durar mas de 60 minutos",
                                                            "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                                }
                                            } while (complejos[i].salas[j].programaciones[k].peli.duracionMinutos < 60);
                                            break;

                                        case 4:
                                            complejos[i].salas[j].programaciones[k].peli.nomOriginal = JOptionPane.showInputDialog("Actualize el nombre original");
                                            break;

                                        case 5:
                                            complejos[i].salas[j].programaciones[k].peli.paisOrigen = JOptionPane.showInputDialog("Actualize el pais de origen");
                                            break;

                                        case 6:
                                            do {
                                                complejos[i].salas[j].programaciones[k].peli.genero = Integer.parseInt(JOptionPane.showInputDialog("¿A qué genero desea cambiarlo?"
                                                        + "\n1. Drama"
                                                        + "\n2. Suspenso"
                                                        + "\n3. Terror"
                                                        + "\n4. Acción"
                                                        + "\n5. Comedia"
                                                        + "\n6. Infantil"));
                                                if (complejos[i].salas[j].programaciones[k].peli.genero < 1 || complejos[i].salas[j].programaciones[k].peli.genero > 6) {
                                                    JOptionPane.showMessageDialog(null, "Genero no identificado", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                }
                                            } while (complejos[i].salas[j].programaciones[k].peli.genero < 1 || complejos[i].salas[j].programaciones[k].peli.genero > 6);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            if (existencia == true) {
                                break;
                            }
                        }
                    }
                    if (existencia == true) {
                        break;
                    }
                }
            }
        }
        if (existencia == false) {
            JOptionPane.showMessageDialog(null, "No existe la pelicula con el nombre especificado", "ADVERTENCIA",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
