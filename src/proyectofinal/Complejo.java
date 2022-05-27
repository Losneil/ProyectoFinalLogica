package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * @authores Jose Manuel Quintero Rodríguez
 * 
 * Esta clase se encarga de pedir los datos de un complejo, ingresar un numero no mayor a 10 de salas
 * mostrar el mapa de la sala mediante especificación de identificador, reservar una silla en una sala,
 * calcular el valor recaudado por venta de boletas en un día por cada sala del complejo,
 * y permite también registrar a los usuarios vendedor y administrador que pertenecerá al complejo.
 */
public class Complejo {
    
    // Atributos de la clase
    String nombre, direccion, nombreAdmin;
    int numSalas;
    /* Crear arreglo de tipo sala que almacenará como máximo 10 salas por complejo
    En este caso se dejó indicado el indice del numero de salas*/
    Sala[] salas;
    /* Creamos el arreglo de usuarios que como máximo almacenará 2 usuarios
    que son el administrador y el cajero*/
    Usuario[] users = new Usuario[2];

    /* Método constructor que inicializa los valores de los atributos
    @author José Manuel Quintero Rodriguez*/
    public Complejo() {
        numSalas = 0;
        nombre = "";
        direccion = "";
        nombreAdmin = "";
    }

    /* Este método se encarga de pedir los datos del Complejo en general
    @author José Manuel Quintero Rodríguez*/
    public void pedirInfoComplejo() {
        nombre = JOptionPane.showInputDialog("Ingrese nombre del complejo");
        direccion = JOptionPane.showInputDialog("Ingrese direccion");
        nombreAdmin = JOptionPane.showInputDialog("Ingrese nombre del administrador");
        do{
            numSalas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese numero de salas a registrar"));
            if(numSalas > 10)
                JOptionPane.showMessageDialog(null, "No pueden haber mas de 10 salas");
        } while(numSalas > 10);
        salas = new Sala[numSalas];
    }

    /* Este método se encarga de crear un objeto de tipo Sala, pedir los datos del mismo
    y validar que el objeto de la sala no esté repetido mediante su ID
    @author José Manuel Quintero Rodríguez*/
    int iterador = 0;
    public void ingresarSala() {
        Sala sl = new Sala();
        sl.pedirInfoSala();
        if(this.buscarSala(sl.numeroSala) == null){
            salas[iterador] = sl;
            iterador++;
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe");
        }
        
//        for (int i = 0; i < salas.length; i++) {
//            if (salas[i] == null) {
//                salas[i] = new Sala(); // Creamos un objeto de tipo sala con datos inicializados y lo guardamos en la posicion i
//                salas[i].pedirInfoSala(); // En el objeto que está en la posición i pedimos los datos y los guardamos para ese objeto
//                for (int j = 0; j < i; j++) {
//                    if (salas[j].numeroSala == salas[i].numeroSala) {
//                        JOptionPane.showMessageDialog(null, "Ya existe la sala con este numero", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//                        i--; // Pasamos a la posicion anterior y volvemos a la misma para sobreescribir el nombre
//                        break; // Aqui rompemos el ciclo interno que maneja el iterador j
//                    }
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Ya hay salas registradas" , "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//                break;
//            }
//        }
    }

    public Sala buscarSala(int idBuscar){
        int i = 0;
        while(salas[i] != null){
            if(salas[i].numeroSala == idBuscar){
                return salas[i];
            }
            i++;
        }
        return null;
    }
    
    /* Esté metodo permite mostrar el mapa de la sala especificando el número de esta
    @author José Manuel Quintero Rodríguez*/
    public void mostrarMapaSala(int id) {
        boolean existencia = false;
        String muestreMapa = "";
        for (int i = 0; i < salas.length; i++) { // Recorremos el arreglo de la sala
            if (salas[i] != null) { // Verificamos si la sala existe
                if (salas[i].numeroSala == id) { // Verificamos si existe la sala
                    existencia = true;
                    for (int j = 0; j < salas[i].cantidadFilas; j++) {
                        for (int k = 0; k < salas[i].sillasPorFila; k++) {
                            muestreMapa += salas[i].sillas[j][k] + " ";
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

    /* Este método se encarga de hacer la reserva en de una silla en una sala
    @author Jose Manuel Quintero Rodriguez*/
    public void reservarSilla(int id) {
        boolean existencia = false;
        int controladorAlertas = 0;
        int confirmarReserva; // variable que indica si la confimacion de la reserva
        for (int i = 0; i < salas.length; i++) { // Recorremos el arreglo de la sala
            if (salas[i] != null) { // Comprobamos que si existan salas en el complejo
                if (salas[i].numeroSala == id) { // Verificamos si existe la sala
                    existencia = true;
                    for (int j = 0; j < salas[i].cantidadFilas; j++) { // Recorremos las filas 
                        for (int k = 0; k < salas[i].sillasPorFila; k++) {
                            salas[i].sillas[j][k] = true; // Por defecto decimos que la silla
                            if (salas[i].sillas[j][k] == true) {
                                confirmarReserva = JOptionPane.showConfirmDialog(null, ""
                                        + "¿Quiere reservar el asiento [" + j + "][" + k + "] ?", "", JOptionPane.YES_NO_OPTION);
                                if (confirmarReserva == JOptionPane.YES_OPTION) {
                                    salas[i].sillas[j][k] = false; // Ocupamos la silla
                                } else {
                                    if (confirmarReserva == JOptionPane.NO_OPTION) {
                                        break; // Rompe el ciclo interno
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Esta silla está ocupada", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay salas registradas en este complejo", "ERROR", JOptionPane.ERROR_MESSAGE);
                controladorAlertas = 1;
                break; // Rompemos el ciclo inmediatamente sacandonos del ciclo
            }
        } // En caso de que la existencia de esa sala especifica sea falsa
        if (existencia == false && controladorAlertas == 0) {
            JOptionPane.showMessageDialog(null, "No se encontró la sala", "ERROR", JOptionPane.ERROR_MESSAGE);
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
