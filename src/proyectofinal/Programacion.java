package proyectofinal;

import javax.swing.JOptionPane;
// Importar el formato que genera la hora actual en la que se ejecuta el programa
import java.time.LocalTime;

/**
 * Fecha de creacion: 9 de mayo
 *
 * @authores Jose Manuel Quintero Rodriguez
 * 
 * Para esta clase necesitamos tomar el atributo id de sala por lo cual se hace la herencia de la clase Sala
 * Esta clase también se encarga de mostrar la programación del día, mostrar la información completa de una 
 * pelicula y actualizar los datos de una pelicula especifica.
 */
public class Programacion{

    // Atributos de la clase
    int sillasReservadas, idSala;
    // Establecemos el formato de la hora para el horario
    LocalTime horario = LocalTime.now();
    // Declaramos el arreglo de objetos de tipo Pelicula que almacenará 5 peliculas por sala
    Pelicula[] peliculas = new Pelicula[5];

    /*Este método constructor se encarga de realizar asignación de valores a los atributos
    @author Jose Manuel Quintero Rodriguez*/
    public Programacion() {
        sillasReservadas = 0;
        
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

    /* Este metodo se encarga de mostrar la programación que tiene la sala para un día
    @author Jose Manuel Quintero Rodriguez*/
    public void mostrarProgramacion() {
        // Creamos un arreglo que almacenara un listado de peliculas en cartelera
        String listadoPeliculas = "";
        boolean existencia = false;
        for (int i = 0; i < peliculas.length; i++) { // Recorremos el arreglo de peliculas
            if (peliculas[i] != null) { // Verificamos que el objeto Pelicula en la posición indicada, exista en el arreglo
                if (peliculas[i].actividad == true) { // Indicamos si la pelicula está activa para agregarla a la programación
                    existencia = true;
                    listadoPeliculas += (i + 1) + ". " + peliculas[i].nomEspanol
                            + "\nHorario: " + horario + "\nNumero de la sala: " + idSala;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede mostrar la programación\n"
                        + "si no hay peliculas registradas", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
            }
        } // Mostramos el listado con la programación
        if (existencia == true) {
            JOptionPane.showMessageDialog(null, listadoPeliculas);
        }
    }

    /*Este metodo se encarga de mostrar la informacion una pelicula
    especificando el nombre de la misma dentro del arreglo de objetos Pelicula
    @author Jose Manuel Quintero Rodriguez*/
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

    /*Este método se encarga de actualizar cualquier dato de una pelicula especificando el nombre de la misma
    @author Jose Manuel Quintero Rodriguez*/
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
