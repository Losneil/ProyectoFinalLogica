package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * @author Jose Manuel Quintero Rodríguez
 * 
 * Esta clase se encarga de pedir los datos correspodientes a una sala, teniendo en cuenta
 * que para registrar una sala tiene que haber al menos un complejo registrado,
 * en donde al suministrar los datos correspodiente detallamos la forma en como será el mapa de respectiva sala,
 * esta clase también se encarga de calcular el porcentaje de ocupación.
 */
public class Sala{

    // Se tienen los atributos respectivos de la clase
    int numeroSala, cantidadFilas, sillasPorFila;
    double valorBoleta;
    boolean sillas[][];
    String mapa[][];

    /*Este es el método constructor de la clase que se encarga de inicializar los valoes
    de los atributos. Cabe destacar que la inicializacion tambien sirve para que al momento de
    crear un objeto de tipo Sala y guardarlo en un arreglo de objetos, este no se almacene con datos nulos
    @author José Manuel Quintero Rodriguez*/
    public Sala() {
        numeroSala = 0;
        cantidadFilas = 0;
        sillasPorFila = 0;
        valorBoleta = 0;
    }

    /* Este método se encarga de solicitar los datos de la sala además de definir el diseño del mapa de la sala
    @author Jose Manuel Quintero Rodriguez */
    public void pedirInfoSala() {
        // El identificador de la sala se volverá autoincrementable
        numeroSala = Integer.parseInt(JOptionPane.showInputDialog("Ingrese identificador de la sala"));
        cantidadFilas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de filas"));
        sillasPorFila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de sillas por fila"));
        valorBoleta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese valor de la boleta"));
        sillas = new boolean[cantidadFilas][sillasPorFila];
        mapa = new String[cantidadFilas][sillasPorFila];
        // Generar el mapa de la sala sin asientos reservados
        for (int i = 0; i < cantidadFilas; i++) {
            for (int j = 0; j < sillasPorFila; j++) {
                mapa[i][j] = "[ ]";
            }
        }
    }

    /* Este método se encarga de calcular el porcetaje de ocupación en una sala especifica
    tomando como parametro de entrada el numero de personas que visitaron dicha sala
    @author Jose Manuel Quintero Rodriguez */
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
