// Esta es la primera clase en ser creada y modificada, ya que de esta no sale ninguna flecha a la hora de implementarla.
package proyectofinal;

import javax.swing.JOptionPane;

/**
 * Fecha de creacion: 3 de mayo
 *
 * Esta clase representa una película que se muestra en una sala de cine.
 *
 * @author Jose Manuel Quintero Rodríguez
 * @author Juan Ángel Riaño Quintero
 *
 */
public class Pelicula {

    // Atributos de la clase.
    String nomEspanol, nomOriginal, paisOrigen;
    // El genero se declara como un entero, debido a que este se selecciona mediante un menú de opciones.
    int genero, anioEstreno, duracionMinutos;
    boolean actividad;

    /**
     * Este es el método constructor que inicializa los valores de los
     * atributos.
     *
     * @author Jose Manuel Quintero Rodriguez
     *
     */
    public Pelicula() {

        nomEspanol = "";
        nomOriginal = "";
        paisOrigen = "";
        genero = 0;
        anioEstreno = 0;
        duracionMinutos = 0;
        actividad = false;
    }

    /**
     * Este Método se encarga de solicitar la información de la película.
     *
     * @author Jose Manuel Quintero Rodriguez.
     * @author Juan Angel Riano Quintero.
     *
     */
    public void pedirInfoPelicula() {

        int indicadorActividad, controladorExcepciones;
        nomEspanol = JOptionPane.showInputDialog("Ingrese el nombre de la película en español: ");

        do {
            do {
                try {
                    controladorExcepciones = 0;
                    anioEstreno = Integer.parseInt(JOptionPane.showInputDialog("¿En que año se estrenó?"));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "No se permiten datos de tipo cadena o texto",
                            "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    controladorExcepciones = 1;
                }
            } while (controladorExcepciones == 1);

            // Comprobamos que los años a ingresar sean los legalmente establecidos para una fecha de estreno de un filme.
            if (anioEstreno < 1900) {
                JOptionPane.showMessageDialog(null, "No existen peliculas filmadas antes de 1900",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
            if (anioEstreno > 2022) {
                JOptionPane.showMessageDialog(null, "Estas especificando un año que aún no ha pasado",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } while (anioEstreno < 1900 || anioEstreno > 2022);

        // Este ciclo sirve para comprobar que la duración corresponde a la de un largometraje.
        do {
            duracionMinutos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese duracion en minutos"));
            if (duracionMinutos < 60) {
                JOptionPane.showMessageDialog(null, "Para que sea una pelicula debe durar mas de 60 minutos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } while (duracionMinutos < 60);

        nomOriginal = JOptionPane.showInputDialog("Ingrese nombre original");
        paisOrigen = JOptionPane.showInputDialog("Ingrese pais de origen");

        /**
         * Ciclo que indica que el usuario no debe ingresar un número distinto a
         * los establecidos.
         *
         * @author Jose Manuel Quintero Rodriguez.
         *
         */
        do {
            genero = Integer.parseInt(JOptionPane.showInputDialog("¿A qué genero pertenece la película?"
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
        } else {  // Si la pelicula está inactiva no se agregará a la programación.

            if (indicadorActividad == JOptionPane.NO_OPTION) {
                actividad = false;
            }
        }
    }

    /**
     * Este método, permite obtener el nombre del genero de la película de
     * acuerdo al caso que se halla escogido en el menú de generos.
     *
     * @author Jose Manuel Quintero Rodriguez
     * @author Juan Ángel Riaño Quintero
     *
     * @return Debe retornar el nombre del genero de la película.
     *
     */
    public String obtenerNombreGenero() {
        // Según sea el número de genero especificado
        String nomGenero;
        switch (genero) {
            case 1 -> {
                nomGenero = "Drama";
                return nomGenero;
            }
            case 2 -> {
                nomGenero = "Suspenso";
                return nomGenero;
            }
            case 3 -> {
                nomGenero = "Terror";
                return nomGenero;
            }
            case 4 -> {
                nomGenero = "Acción";
                return nomGenero;
            }
            case 5 -> {
                nomGenero = "Comedia";
                return nomGenero;
            }
            case 6 -> {
                nomGenero = "Infantíl";
                return nomGenero;
            }
            default -> {
                return "";
            }
        }
    }
}
