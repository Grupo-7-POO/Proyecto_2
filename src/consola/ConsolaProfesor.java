package consola;

import java.util.Scanner;

public class ConsolaProfesor {
    private static Profesor profesor;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        profesor = new Profesor("Nombre del Profesor", "ID del Profesor"); // Se puede inicializar más tarde
        boolean exit = false;

        while (!exit) {
            showMenu();
            int option = getUserInput();

            switch (option) {
                case 1:
                    profesor.viewInformation();
                    break;
                case 2:
                    viewCourses();
                    break;
                case 3:
                    manageStudents();
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
        System.out.println("\n--- Consola de Profesor ---");
        System.out.println("1. Ver Información del Profesor");
        System.out.println("2. Ver Cursos");
        System.out.println("3. Gestionar Estudiantes");
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

    private static void viewCourses() {
    }

    private static void manageStudents() {
    }
}

