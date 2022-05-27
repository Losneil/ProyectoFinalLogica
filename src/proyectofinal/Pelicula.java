// Esta es la primera clase en ser creada y modificada ya que de esta no sale ninguna flecha a la hora de implementarla
package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * @author Jose Manuel Quintero Rodríguez
 * @author Juan Angel Riano Quintero
 * 
 * Esta clase almacena la información correspodiente a una pelicula 
 * además de tener un método que permite retornar el nombre del genero debido
 * a que se tienen generos prestablecidos para asignarles de estos, solo uno, a una pelicula
 */
public class Pelicula {

    // atributos de la clase
    String nomEspanol, nomOriginal, paisOrigen;
    // el genero se declara como entero debido a que se trata de escoger mendiante un menú de opciones
    int genero, anioEstreno, duracionMinutos;
    boolean actividad;

    /* Este es el método constructor que inicializa los valores de los atributos
    @author Jose Manuel Quintero Rodriguez*/
    public Pelicula() {
        nomEspanol = "";
        nomOriginal = "";
        paisOrigen = "";
        genero = 0;
        anioEstreno = 0;
        duracionMinutos = 0;
        actividad = false;
    }

    /* Este Método se encarga solicitar la información de la pelicula
    @author Jose Manuel Quintero Rodriguez
    @author Juan Angel Riano Quintero*/
    public void pedirInfoPelicula() {
        /*el nombre en español, el año de estreno, la duración en minutos, el nombre original, el
        género (drama, suspenso, terror, acción, comedia o infantil) */
        int indicadorActividad;
        nomEspanol = JOptionPane.showInputDialog("Ingrese nombre en español");
        do {
            anioEstreno = Integer.parseInt(JOptionPane.showInputDialog("¿En que año se estrenó?"));
            // Comprobamos que los años a ingresar sean los legalmente establecidos para una fecha de estreno de filme
            if (anioEstreno < 1900) {
                JOptionPane.showMessageDialog(null, "No existen peliculas filmadas antes de 1900", 
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
            if (anioEstreno > 2022) {
                JOptionPane.showMessageDialog(null, "Estas especificando un año que aún no ha pasado", 
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } while (anioEstreno < 1900 || anioEstreno > 2022);
        do { // Comprobar que la duración corresponde a la de un largometraje
            duracionMinutos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese duracion en minutos"));
            if (duracionMinutos < 60) {
                JOptionPane.showMessageDialog(null, "Para que sea una pelicula debe durar mas de 60 minutos", "ADVERTENCIA",
                        JOptionPane.WARNING_MESSAGE);
            }
        } while (duracionMinutos < 60);
        nomOriginal = JOptionPane.showInputDialog("Ingrese nombre original");
        paisOrigen = JOptionPane.showInputDialog("Ingrese pais de origen");
        /* Ciclo que indica que el usuario no debe ingresar un numero distinto a los establcidos
        @author Jose Manuel Quintero Rodriguez*/
        do {
            genero = Integer.parseInt(JOptionPane.showInputDialog("¿A qué genero pertenece?"
                    + "\n1. Drama"
                    + "\n2. Suspenso"
                    + "\n3. Terror"
                    + "\n4. Acción"
                    + "\n5. Comedia"
                    + "\n6. Infantil"));
            if (genero < 1 || genero > 6) {
                JOptionPane.showMessageDialog(null, "Genero no identificado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (genero < 1 || genero > 6);
        indicadorActividad = JOptionPane.showConfirmDialog(null, "¿La pelicula está activa?", "", JOptionPane.YES_NO_OPTION);
        // Si la pelicula está activa
        if (indicadorActividad == JOptionPane.YES_OPTION) {
            actividad = true;
        } else { // Si la pelicula está inactiva no se agregará a la programación
            if (indicadorActividad == JOptionPane.NO_OPTION) {
                actividad = false;
            }
        }
    }

    /* Este método permite obtener el nombre del genero de la pelicula
    de acuerdo al caso que se halla escogido en el menú de generos
    @author Jose Manuel Quintero Rodriguez */
    public String obtenerNombreGenero() {
        // Según sea el número de genero especificado
        switch (genero) {
            case 1:
                return "Drama";
            case 2:
                return "Suspenso";
            case 3:
                return "Terror";
            case 4:
                return "Acción";
            case 5:
                return "Comedia";
            case 6:
                return "Infantil";
            default:
                return "";
        }
    }
}
