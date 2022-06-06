package proyectofinal;
// Para esta clase y para las demás clases, se trabajó con ventanas emergentes.

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * Esta clase principal se encargará ya de por si permitir el ingreso al sistema
 * al usuario gerente, el cual se encargará de registrar usuarios y
 * proporcionarles a cada tipo de usuario, unas determinadas funcionalidades
 * dentro del sistema.
 *
 * @author José Manuel Quintero Rodriguez.
 * @author Juan Ángel Riaño Quintero.
 *
 */
public class PrincipalEmpresa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Primeramente creamos el objeto de tipo Empresa
        Empresa ep;
        String nomPrincipal = "", contraPrincipal = "", buscarPelicula, peliEspecifica, listadoPorcentajes = "";
        int opcionesCajero = 0, opcionesAdministrador = 0, numeroPersonas, idSala, seguir, numUso, numUsuario;
        int registroUnico = 0; // Indicador de primero registro
        boolean esSalaEnComplejo; // Es quien nos indica si existe una sala especifica en un complejo especifico
        try {
            do { // Ingresando credenciales al iniciar la ejecución del programa
                nomPrincipal = JOptionPane.showInputDialog("Ingrese su nombre, señor gerente:");
                contraPrincipal = JOptionPane.showInputDialog("Ingrese su contraseña, señor gerente:");

                // Si el el nombre y la contraseña son distintos de estos que se evaluan en esta condicion
                if (!nomPrincipal.equals("1") || !contraPrincipal.equals("1")) {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } while (!nomPrincipal.equals("1") || !contraPrincipal.equals("1"));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No se permiten datos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        // Creamos las variables con respecto a las cantidades de complejos y peliculas que promoverá la empresa
        int numeroPeliculas, numeroComplejos, cAlertas = 0;
        String nombreComplejo;
        numeroComplejos = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos complejos en total posee la empresa?"));
//		numeroPeliculas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de peliculas a registrar"));
        ep = new Empresa(numeroComplejos);
        String nombre, clave; // variables que se pasaran como parametros al metodo de autenticaciopn
        String[] opcionUso = {"Realizar registros", "Ingresar con usuario especifico"}; //Arreglo para las opciones de botón de acción
        String[] nombreUsuario = {"Administrador", "Cajero"}; //Arreglo para las opciones de botón de tipo de usuario

        do { // Una vez validadas las credenciales damos funcionalidades propias para un gerente o administrador principal
            if (nomPrincipal.equals("1") && contraPrincipal.equals("1")) {
                numUso = JOptionPane.showOptionDialog(null, "¿Seleccione una opción?", "Elige...",
                        0, JOptionPane.QUESTION_MESSAGE, null, opcionUso, "");
                boolean autentiAdmin = false;
                boolean autentiCajero = false;
                if (numUso == 0 && registroUnico == 0) { // Si se seleccionó la opción de realizar registros
                    ep.registrarUsuarios(); // Invocar el metodo de registrar usuarios, cabe destacar que se invoca solo una vez
                    // Si se ha hecho el registro de los dos usuarios, no se permitirá realizar un segundo registro de usuarios
                    registroUnico++;
                    // Pedimos las cantidades necesarias para los controles de ingresos de datos
                } else {
                    if (numUso == 0 && registroUnico != 0) {
                        JOptionPane.showMessageDialog(null, "No se permite realizar más registros", "ADVERTENCIA",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    if (numUso == 1) { // Si se seleccionó la opción de ingresar con un usuarios específico
                        numUsuario = JOptionPane.showOptionDialog(null, "¿Con que perfil desea entrar?", "Elige...",
                                0, JOptionPane.QUESTION_MESSAGE, null, nombreUsuario, "");
                        if (numUsuario == 0) { // Si el usuario seleccionado para entrar es el administrador
                            if (ep.usuarios[numUsuario] != null) { // Verificamos si existe el administrador
                                do { // Válidar credenciales del usuarios administrador
                                    nombre = JOptionPane.showInputDialog("Digite su nombre, señor administrador");
                                    clave = JOptionPane.showInputDialog("Digite su contraseña, señor administrador");
                                    if (ep.usuarios[numUsuario].autenticar(nombre, clave)) {
                                        autentiAdmin = true;
                                        do { // Mostrar el menú de opciones para el administrador
                                            int controlExcep;
                                            do {
                                                try {
                                                    controlExcep = 0; // Controlador de excepciones que sirve para controlar las iteraciones con el ciclo DO
                                                    opcionesAdministrador = Integer.parseInt(JOptionPane.showInputDialog("Elija una opcion"
                                                            + "\n1. Registrar complejo"
                                                            + "\n2. Registrar sala a un complejo especifico"
                                                            // + "\n3. Almacenar datos de una pelicula"
                                                            + "\n3. Modificar datos de una pelicula"
                                                            + "\n4. Obtener porcentaje de ocupacion en cada sala"
                                                            + "\n5. Obtener el total de ganancias por boletas"
                                                            + "\n6. Salir"));
                                                } catch (NumberFormatException ex) {
                                                    JOptionPane.showMessageDialog(null, "Debe elegir una opción del menú",
                                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                                    controlExcep = 1;
                                                }
                                            } while (controlExcep == 1);
                                            switch (opcionesAdministrador) {
                                                case 1:
                                                    ep.ingresarComplejo();
                                                    break;
                                                case 2:
                                                    boolean existeComplejo = false;
                                                    for (int i = 0; i < ep.complejos.length; i++) {
                                                        try {
                                                            if (ep.complejos[i] != null) {
                                                                existeComplejo = true;
                                                                JOptionPane.showMessageDialog(null, (i + 1) + ". Ingresar sala para el complejo " + ep.complejos[i].nombre);
                                                                ep.complejos[i].ingresarSala();
                                                                break;
                                                            }
                                                        } catch (ArrayIndexOutOfBoundsException e) {
                                                            JOptionPane.showMessageDialog(null, "No existe el complejo " + (i + 1));
                                                        }
                                                    }
                                                    if (existeComplejo == false) {
                                                        JOptionPane.showMessageDialog(null, "Para registrar una sala "
                                                                + "\ntiene que haber al menos un complejo registrado", "ERROR",
                                                                JOptionPane.ERROR_MESSAGE);
                                                    }
                                                    break;
//                                                    case 3:
//                                                        ep.ingresarPelicula();
//                                                        break;
                                                case 3:
                                                    peliEspecifica = JOptionPane.showInputDialog("Especifique el nombre de pelicula");
                                                    ep.actualizarDatosPelicula(peliEspecifica);
                                                    break;
//                                                    case 5:
//                                                        boolean salaParaProgramacion = false;
//                                                        for (int i = 0; i < cp.salas.length; i++) {
//                                                            if (cp.salas[i] != null) {
//                                                                salaParaProgramacion = true;
//                                                                pr.crearProgramacion();
//                                                                break;
//                                                            }
//                                                        } // Si no se encontró ninguna sala
//                                                        if (salaParaProgramacion == false) {
//                                                            JOptionPane.showMessageDialog(null,
//                                                                    "No se puede crear una programacion si no hay una sals registrada",
//                                                                    "ERROR", JOptionPane.ERROR_MESSAGE);
//                                                        }
//                                                        break;
                                                case 4:
                                                    boolean salaParaPorcentaje = false;
                                                    for (int i = 0; i < ep.complejos[i].salas.length; i++) { // Recorremos el arreglo de objetos tipo Sala
                                                        if (ep.complejos[i].salas[i] != null) { // Verificamos que si exista el objeto de la sala en la posición indicada
                                                            salaParaPorcentaje = true;
                                                            numeroPersonas = Integer.parseInt(JOptionPane.showInputDialog(""
                                                                    + "Ingrese numero de personas que visitaron la sala: " + (i + 1)));
                                                            listadoPorcentajes += "El porcentaje de ocupacion para la sala " + (i + 1) + " es: "
                                                                    + ep.complejos[i].salas[i].calcularPorcOcupacion(numeroPersonas) + "%\n";
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "No hay salas registradas", "ERROR",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            break;
                                                        }
                                                    } // Si se ha encontrado una sala
                                                    if (salaParaPorcentaje == true) {
                                                        JOptionPane.showMessageDialog(null, listadoPorcentajes);
                                                    }
                                                    break;
                                                case 5:
                                                    nombreComplejo = JOptionPane.showInputDialog("Especifique el nombre del complejo");
                                                    for (int l = 0; l < ep.complejos.length; l++) {
                                                        if(ep.complejos[l].nombre.equals(nombreComplejo)){
                                                            JOptionPane.showMessageDialog(null, "El valor del recaudo por venta de boletas es: " + ep.complejos[l].calcularValorRecaudo());
                                                        }
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }

                                            // AQUI ESTABA EL CATCH
                                        } while (opcionesAdministrador != 6);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Autenticación fallida, intente nuevamente"
                                                + "", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                } while (autentiAdmin == false);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró al administrador", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            if (numUsuario == 1) { // Si el usuario deseado para entrar es el cajero
                                if (ep.usuarios[numUsuario] != null) { // verificamos que el cajero si exista
                                    do {
                                        nombre = JOptionPane.showInputDialog("Digite su nombre, señor cajero");
                                        clave = JOptionPane.showInputDialog("Digite su contraseña, señor cajero");
                                        if (ep.usuarios[numUsuario].autenticar(nombre, clave)) {
                                            autentiCajero = true;
                                            do { // Mostrar el menú de opciones para el cajero
                                                try {
                                                    opcionesCajero = Integer.parseInt(JOptionPane.showInputDialog("Elija una opcion para cajero"
                                                            + "\n1. Ver programacion de la sala"
                                                            + "\n2. Ver informacion de la pelicula"
                                                            + "\n3. Ver mapa de una sala"
                                                            + "\n4. Reservar Asiento en una sala"
                                                            + "\n5. Generar boleta"
                                                            + "\n6. Salir"));
                                                } catch (NumberFormatException ex) { // Capturamos la excepcion en caso de que haya ingresado una cadena
                                                    JOptionPane.showMessageDialog(null, "No se permiten caracteres vacios",
                                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                                }
                                                switch (opcionesCajero) {
                                                    case 1:
                                                        // PENDIENTE
                                                        int nSala;
                                                        nombreComplejo = JOptionPane.showInputDialog("Especifique el nombre del complejo");
                                                        nSala = Integer.parseInt(JOptionPane.showInputDialog("Especifique el nombre del complejo"));
                                                        for (int i = 0; i < ep.complejos.length; i++) {
                                                            if(ep.complejos[i].nombre.equals(nombreComplejo)){ // Comparamos nombre complejo
                                                                if(ep.complejos[i].salas[i].numeroSala == nSala){ // Comparamos numero de sala a ese complejo
                                                                    JOptionPane.showMessageDialog(null, ep.complejos[i].salas[i].programaciones[i].peli
                                                                            + "\n" + ep.complejos[i].salas[i].programaciones[i].horario
                                                                            + "\n" + ep.complejos[i].salas[i].programaciones[i].numeroSala);
                                                                    break;
                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, "No se encontró la sala", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                                                }
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "No se encontró la sala", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                                            }
                                                        }
                                                        ep.complejos[0].salas[0].mostrarProgramacion();
                                                        break;
                                                    case 2:
                                                    try {
                                                        buscarPelicula = JOptionPane.showInputDialog("Especifique el nombre de pelicula");
                                                        ep.mostrarInfoPelicula(buscarPelicula);
                                                    } catch (NullPointerException e) {
                                                        JOptionPane.showMessageDialog(null, "No se permiten caracteres vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                    break;
                                                    case 3:
                                                        esSalaEnComplejo = false;
                                                        idSala = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el id de la sala"));
                                                        nombreComplejo = JOptionPane.showInputDialog("Ingrese el nombre del complejo");
                                                        for (int i = 0; i < ep.complejos.length; i++) {
                                                            for (int j = 0; j < ep.complejos[i].salas.length; j++) {
                                                                if(ep.complejos[i].nombre.equals(nombreComplejo)){
                                                                    if(ep.complejos[i].salas[i].numeroSala == idSala){
                                                                        ep.complejos[i].salas[i].mostrarMapaSala(idSala);
                                                                        esSalaEnComplejo = true;
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            if(esSalaEnComplejo == true){
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    case 4:
                                                        esSalaEnComplejo = false;
                                                        idSala = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el id de la sala"));
                                                        nombreComplejo = JOptionPane.showInputDialog(null, "Especifique el nombre del complejo");
                                                        for (int i = 0; i < ep.complejos.length; i++) {
                                                            for (int j = 0; j < ep.complejos[i].salas.length; j++) {
                                                                if(ep.complejos[i].nombre.equals(nombreComplejo)){
                                                                    if(ep.complejos[i].salas[i].numeroSala == idSala){
                                                                        ep.complejos[i].salas[i].reservarAsiento(idSala);
                                                                        esSalaEnComplejo = true;
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            if(esSalaEnComplejo == true){
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    case 5:
                                                        ep.rv.pedirDatosBoleta();
                                                        ep.rv.generarBoleta();
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            } while (opcionesCajero != 6);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Autenticación fallida, intente nuevamente"
                                                    + "", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } while (autentiCajero == false);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se encontró al cajero", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }
            seguir = JOptionPane.showConfirmDialog(null, "¿Quiere terminar el programa?", "Confirmar", JOptionPane.YES_NO_OPTION);
        } while (seguir == JOptionPane.NO_OPTION);
    }
}
