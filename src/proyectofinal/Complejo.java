package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * Esta clase se encarga de pedir los datos de un complejo, ingresar un numero
 * no mayor a 10 de salas mostrar el mapa de la sala mediante especificación de
 * identificador, reservar una silla en una sala, calcular el valor recaudado
 * por venta de boletas en un día por cada sala del complejo, y permite también
 * registrar a los usuarios vendedor y administrador que pertenecerá al
 * complejo.
 *
 * @author Jose Manuel Quintero Rodríguez.
 * @author Juan Ángel Riaño Quintero.
 *
 */
public class Complejo {

    // Atributos de la clase.
    String nombre, direccion, nombreAdmin;

    /* Crear arreglo de tipo sala que almacenará como máximo 10 salas por complejo
    En este caso se dejó indicado el indice del numero de salas*/
    Sala[] salas = new Sala[10];
    Sala sl = new Sala();

    /**
     * Método constructor que inicializa los valores de los atributos.
     *
     * @author José Manuel Quintero Rodriguez.
     *
     */
    public Complejo() {

        nombre = "";
        direccion = "";
        nombreAdmin = "";
    }

    /**
     * Este método se encarga de pedir los datos del Complejo en general.
     *
     * @author José Manuel Quintero Rodríguez.
     *
     */
    public void pedirInfoComplejo() {
        nombre = JOptionPane.showInputDialog("Ingrese el nombre del complejo: ");
        direccion = JOptionPane.showInputDialog("Ingrese la direccion del complejo: ");
        nombreAdmin = JOptionPane.showInputDialog("Ingrese el nombre del administrador: ");
    }

    /**
     * Este método se encarga de crear un objeto de tipo Sala, pedir los datos
     * del mismo y validar que el objeto de la sala no esté repetido mediante su
     * ID
     *
     * @author José Manuel Quintero Rodríguez
     */
    public void ingresarSala() {

        int controlExcep, nSalas = 0, contIngresos = 0;  // nComplejos, es el número de programaciones que el usuario quiera registrar en el momento.		
        do {
            do {
                try {
                    controlExcep = 0;
                    nSalas = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas salas desea ingresar?"));

                    if (nSalas > salas.length) {
                        JOptionPane.showMessageDialog(null, "No se pueden ingresar más de 10 salas",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número", "ERROR", JOptionPane.ERROR_MESSAGE);
                    controlExcep = 1;
                }
            } while (nSalas > salas.length);

        } while (controlExcep == 1);

        for (int i = 0; i < salas.length; i++) {

            if (contIngresos == nSalas) {
                break;
            }

            if (salas[i] == null) {

                salas[i] = new Sala();
                salas[i].pedirInfoSala();

                if (i > 0 && i < salas.length) {
                    do {
                        if (salas[i].numeroSala == salas[i - 1].numeroSala) {
                            JOptionPane.showMessageDialog(null, "La sala con este identificador ya existe",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                            salas[i].pedirInfoSala();
                        }
                    } while (salas[i].numeroSala == salas[i - 1].numeroSala);
                }

                contIngresos += 1;
                JOptionPane.showMessageDialog(null, "Sala registrada exitosamente");
            }

            if (salas[i] != null && (i >= salas.length - 1) && contIngresos == 0) {
                JOptionPane.showMessageDialog(null, "Ya hay " + salas.length + " salas registradas",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * @author José Manuel Quintero Rodríguez
     *
     * @param id el cual es el parametro que se necesita para especificar la
     * sala de la cual se deseea conocer su estado
     */
    public void mostrarMapaSala(int id) {
        boolean existencia = false;
        String muestreMapa = "";
        for (int i = 0; i < salas.length; i++) { // Recorremos el arreglo de la sala
            if (salas[i] != null) {
                if (salas[i].numeroSala == id) { // Verificamos si la sala existe
                    for (int j = 0; j < salas[i].sillas.length; j++) {
                        for (int k = 0; k < salas[i].sillas[0].length; k++) {
                            muestreMapa += salas[i].sillas[j][k] + " ";
                        }
                        muestreMapa += "\n";
                    }
                    JOptionPane.showMessageDialog(null, muestreMapa);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe una sala con ese número", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
                }
            }
        } // En caso de que la existencia de esa sala especifica sea falsa
        if (existencia == true) {
            JOptionPane.showMessageDialog(null, muestreMapa);
        }
    }

    /**
     * Este método se encarga de hacer la reserva en de una silla en una sala
     *
     * @author Jose Manuel Quintero Rodriguez
     *
     * @param id el cual es es parametro que se necesita para especificar una
     * sala en donde se hará la reserva
     */
    public void reservarAsiento(int id) {
        int espFila, espColumna;
        for (int i = 0; i < salas.length; i++) {
            if (salas[i] != null) {
                if (salas[i].numeroSala == id) {
                    do {
                        espFila = Integer.parseInt(JOptionPane.showInputDialog("Indique el numero de fila"));
                        if (espFila < 0 || espFila > salas[i].sillas.length - 1) {
                            JOptionPane.showMessageDialog(null, "No se permite el número", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (espFila < 0 || espFila > salas[i].sillas.length - 1);
                    do {
                        espColumna = Integer.parseInt(JOptionPane.showInputDialog("Indique el numero de columna"));
                        if (espColumna < 0 || espColumna > salas[i].sillas[0].length - 1) {
                            JOptionPane.showMessageDialog(null, "No se permite el número", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (espColumna < 0 || espColumna > salas[i].sillas[0].length - 1);
                    salas[i].sillas[espFila][espColumna] = false;
                    JOptionPane.showMessageDialog(null, "Asiento Reservado Exitosamente");
                    break;
                }
            }
        }
    }

    /**
     * Este método se encarga de calcular el valor de recaudo por ganancias en
     * un dia
     *
     * @author Jose Manuel Quintero Rodriguez
     * @return recaudo donde se retorna el valor obtenido por venta de boletas
     * dentro del complejo
     */
    public double calcularValorRecaudo() {
        double recaudo = 0;
        for (int i = 0; i < salas.length; i++) {
            if (salas[i] != null) {
                recaudo += salas[i].valorBoleta;
                break;
            }
        }
        return recaudo;
    }
}
