package consola;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import modelo.actividades.Actividad;
import modelo.usuarios.Estudiante;
import modelo.LearningPath;
import modelo.usuarios.Profesor;


public class StudentConsole {
    private static Estudiante estudiante;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        estudiante = new Estudiante("Nombre del Estudiante", "ID del Estudiante", "Correo del Estudiante", "Teléfono del Estudiante");
        boolean exit = false;

        while (!exit) {
            showMenu();
            int option = getUserInput();

            switch (option) {
                case 1:
                    estudiante.viewInformation();
                    break;
                case 2:
                    estudiante.viewCourses();
                    break;
                case 3:
                    enrollInLearningPath();
                    break;
                case 4:
                    System.out.println("Saliendo de la consola...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Consola de Estudiante ---");
        System.out.println("1. Ver Información del Estudiante");
        System.out.println("2. Ver Cursos Asignados");
        System.out.println("3. Inscribirse en un Camino de Aprendizaje");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static int getUserInput() {
        int option = 0;
        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
        return option;
    }
}