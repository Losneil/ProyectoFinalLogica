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
    Sala[] salas;
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
//        do{
//            numSalas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese numero de salas a registrar"));
//            if(numSalas > 10)
//                JOptionPane.showMessageDialog(null, "No pueden haber mas de 10 salas");
//        } while(numSalas > 10);
//        salas = new Sala[numSalas];
    }

    /* Este método se encarga de crear un objeto de tipo Sala, pedir los datos del mismo
		y validar que el objeto de la sala no esté repetido mediante su ID
		@author José Manuel Quintero Rodríguez*/
    public void ingresarSala() {
        int n;

        do {
            n = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas salas desea ingresar?"));
            salas = new Sala[n];

            if (n > 10) {
                JOptionPane.showMessageDialog(null, "No pueden registrarse mas de 10 salas", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (n > 10);

        for (int i = 0; i < salas.length; i++) {

            if (salas[i] == null) {

                salas[i] = new Sala(); // Creamos un objeto de tipo sala con datos inicializados y lo guardamos en la posicion i
                salas[i].pedirInfoSala(); // En el objeto que está en la posición i pedimos los datos y los guardamos para ese objeto

                for (int j = 0; j < i; j++) {

                    if (salas[j].numeroSala == salas[i].numeroSala) {

                        JOptionPane.showMessageDialog(null, "Ya existe la sala con este numero", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        i--; // Pasamos a la posicion anterior y volvemos a la misma para sobreescribir el nombre
                        break; // Aqui rompemos el ciclo interno que maneja el iterador j
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ya hay salas registradas", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
    }
    
    /* Este método se encarga de calcular el valor de recaudo por ganancias en un dia
		@author Jose Manuel Quintero Rodriguez*/
    public double calcularValorRecaudo() {
        double recaudo = 0;
        for (int i = 0; i < salas.length; i++) {
            if (salas[i] != null) {
                recaudo += salas[i].valorBoleta;
            } else {
                JOptionPane.showMessageDialog(null, "No hay salas registradas", "ERROR", JOptionPane.ERROR_MESSAGE);
                break; // Este break sirve para que no me muestre el mensaje mas de una vez debido a que estoy dentro de un ciclo
            }
        }
        return recaudo;
    }
}
