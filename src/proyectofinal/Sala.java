package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * Esta clase se encarga de pedir los datos correspodientes a una sala, teniendo
 * en cuenta que para registrar una sala tiene que haber al menos un complejo
 * registrado, en donde al suministrar los datos correspodiente detallamos la
 * forma en como será el mapa de respectiva sala, esta clase también se encarga
 * de calcular el porcentaje de ocupación.
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
    Programacion pr;

    /**
     * Este es el método constructor de la clase que se encarga de inicializar
     * los valoes de los atributos, Cabe destacar que la inicializacion tambien
     * sirve para que al momento de crear un objeto de tipo Sala y guardarlo en
     * un arreglo de objetos, este no se almacene con datos nulos.
     *
     * @author José Manuel Quintero Rodriguez.
     *
     */
    public Sala() {

        numeroSala = 0;
        cantidadFilas = 5;
        sillasPorFila = 5;
        valorBoleta = 0;
        pr = new Programacion(numeroSala);
    }

    /**
     * Este método se encarga de solicitar los datos de la sala además de
     * definir el diseño del mapa de la sala.
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

        for (int i = 0; i < programaciones.length; i++) {
            if (programaciones[i] == null) {
                programaciones[i] = new Programacion(numeroSala);
                programaciones[i].pedirDatosProgramacion();
            }
        }
    }

    /**
     * Este metodo se encarga de mostrar la programación que tiene la sala para
     * un día.
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
     * Este método se encarga de calcular el porcetaje de ocupación en una sala
     * especifical, tomando como parametro de entrada el numero de personas que
     * visitaron dicha sala.
     *
     * @author Jose Manuel Quintero Rodriguez.
     *
     * @param nPersonas es el parametro que nos indica cuatas reservas se
     * hicieron para una película.
     *
     * @return El metodo debe retornar un valor Real, el cual será el porcentaje
     * de ocupación.
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

      /* Esté metodo permite mostrar el mapa de la sala especificando el número de esta
		@author José Manuel Quintero Rodríguez*/
    public void mostrarMapaProgramacion(int id) {
        boolean existencia = false;
        String muestreMapa = "";
        int unaProgramacion = 0;
        for (int i = 0; i < programaciones.length; i++) {
            for (int j = 0; j < programaciones[i].sillas.length; j++) {
                for (int k = 0; k < programaciones[i].sillas[0].length; k++) {
                    if(programaciones[i] != null){
                        if(programaciones[i].numeroSala == id){
                            muestreMapa += programaciones[i].sillas[j][k] + " ";
                            unaProgramacion = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontro la sala", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                muestreMapa += "\n";
            }
            if (unaProgramacion == 1) {
                break;
            }
        }
        JOptionPane.showMessageDialog(null, muestreMapa);
//        for (int i = 0; i < programaciones.length; i++) { // Recorremos el arreglo de la sala
//            if (programaciones[i] != null) { // Verificamos si la sala existe
//                if (programaciones[i].numeroSala == id) { // Verificamos si existe la sala
//                    existencia = true;
//                    for (int j = 0; j < programaciones[i].sillas.length; j++) {
//                        for (int k = 0; k < programaciones[i].sillas[0].length; k++) {
//                            try {
//                                muestreMapa += programaciones[i].sillas[j][k] + " ";
//                            } catch (NullPointerException ex) {
//                                JOptionPane.showMessageDialog(null, "No existen programaciones registradas");
//                            }
//                        }
//                        muestreMapa += "\n";
//                    }
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "No hay salas registradas en este complejo", "ERROR", JOptionPane.ERROR_MESSAGE);
//                break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
//            }
//        } // En caso de que la existencia de esa sala especifica sea falsa
//        if (existencia == true) {
//            JOptionPane.showMessageDialog(null, muestreMapa);
//        }
    }
    
    /* Este método se encarga de hacer la reserva en de una silla en una sala
		@author Jose Manuel Quintero Rodriguez*/
    public void reservarSilla(int id) {
        boolean existencia = false;
        int controladorAlertas = 0;
        int confirmarReserva; // variable que indica si la confimacion de la reserva
        int espFila, espColumna = 0;
        String cadena = "";
        for (int i = 0; i < programaciones.length; i++) {
            for (int j = 0; j < programaciones[i].sillas.length; j++) {
                for (int k = 0; k < programaciones[i].sillas[0].length; k++) {
                    programaciones[i].sillas[i][j] = true;
                    cadena += programaciones[i].sillas[i][j] + " ";
                }
                cadena += "\n";
            }
        }
        int filas = pr.sillas.length;
        int columnas = pr.sillas[0].length;
        JOptionPane.showMessageDialog(null, cadena);
        do {
            espFila = Integer.parseInt(JOptionPane.showInputDialog("Dime el numero de fila"));
            if (espFila < 0 || espFila > filas - 1) {
                JOptionPane.showMessageDialog(null, "No se permite el número", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (espFila < 0 || espFila > filas - 1);
        do {
            espColumna = Integer.parseInt(JOptionPane.showInputDialog("Dime el numero de columna"));
            if (espColumna < 0 || espColumna > columnas - 1) {
                JOptionPane.showMessageDialog(null, "No se permite el número", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (espColumna < 0 || espColumna > columnas - 1);
        for (int i = 0; i < programaciones.length; i++) {
            if (programaciones[i].numeroSala == id) {
                programaciones[i].sillas[espFila][espColumna] = false;
            }
        }
//        for (int i = 0; i < sl.programaciones.length; i++) { // Recorremos el arreglo de la sala
//            try {
//                if (salas[i] != null) { // Comprobamos que si existan salas en el complejo
//                    if (salas[i].numeroSala == id) { // Verificamos si existe la sala
//                        existencia = true;
//                        for (int j = 0; j < salas[i].cantidadFilas; j++) { // Recorremos las filas 
//                            for (int k = 0; k < salas[i].sillasPorFila; k++) {
//                                sl.programaciones[i].sillas[j][k] = true; // Por defecto decimos que la silla
//                                if (sl.programaciones[i].sillas[j][k] == true) {
//                                    confirmarReserva = JOptionPane.showConfirmDialog(null, ""
//                                            + "¿Quiere reservar el asiento [" + j + "][" + k + "] ?", "", JOptionPane.YES_NO_OPTION);
//                                    if (confirmarReserva == JOptionPane.YES_OPTION) {
//                                        sl.programaciones[i].sillas[j][k] = false; // Ocupamos la silla
//                                    } else {
//                                        if (confirmarReserva == JOptionPane.NO_OPTION) {
//                                            break; // Rompe el ciclo interno
//                                        }
//                                    }
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "Esta silla está ocupada", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//                                }
//                            }
//                        }
//                    }
//                }
//            } catch (NullPointerException e) {
//                JOptionPane.showMessageDialog(null, "No se puede reservar sobre una sala inexistente",
//                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//            }
//        } // En caso de que la existencia de esa sala especifica sea falsa
//        if (existencia == false && controladorAlertas == 0) {
//            JOptionPane.showMessageDialog(null, "No se encontró la sala", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
    }
}
