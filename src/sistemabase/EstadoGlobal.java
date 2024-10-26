package sistemabase;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import modelo.usuarios.Usuario;
import sistemabase.GeneradorActividades;
import sistemabase.GeneradorPreguntas;


public class EstadoGlobal 
{
	private static Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
	private static Map<String, LearningPath> learningPaths = new HashMap<String, LearningPath>();

	public static Map<String, Usuario> getUsuarios()
	{
		return usuarios;
	}

	public static Map<String, LearningPath> getLearningPaths()
	{
		return learningPaths;
	}

	public static boolean validarUsuario( String login)
	{
		if ( usuarios.containsKey(login) ) { return true; }
		else{ return false;}
	}

	public static boolean validarLogin( String login, String password)
	{

		if ( validarUsuario(login) )
		{
			Usuario usuarioactual = usuarios.get(login);
			if (usuarioactual.getPassword() == password){ return true; }
			else { return false;}
		}
		else { return false;}
	}

	// MAIN DE LA APLICACION

	public static void cargarSistemaGlobal()
	{

	}

	public static String menuLogin()
    {
        Scanner escaner = new Scanner(System.in);
        System.out.print("Inicie Sesion");
        System.out.print("Ingrese su login:");
        String login = escaner.nextLine();
		System.out.print(login); 
        System.out.print("Ingrese su contraseña:");
        String password = escaner.nextLine();
		escaner.close();

        if ( validarLogin(login, password) ) 
		{
			return login;
		}
		else { return "error"; }
    }

	public static void menuProfesor( Profesor profesor)
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Selecciona una opción:");
		System.out.println("1. Crear Learning Path:");
		System.out.println("2. Editar Learning Path");
		System.out.println("3. Crear Actividad:");
		System.out.println("4. Editar Actividad:");
		System.out.println("5. Clonar Actividad:");
		System.out.println("6. Salir:");

		System.out.print("Opción: ");
			
		int opcion = escaner.nextInt();

		switch (opcion)
		{
		case 1: CrearLearningPathProfesor( profesor );
		case 2: EditarLearningPathProfesor( profesor );
		case 3: CrearActividadProfesor( profesor );
		case 4: EditarActividadProfesor( profesor );
		case 5: ClonarActividadProfesor( profesor );
		case 6:
				escaner.close();
				System.exit(0);
				break;
		default:
				System.out.println("Opción no válida");
				break;
		}
	}

	public static void CrearLearningPathProfesor( Profesor profesor )
	{
		List<Actividad> actividades = new LinkedList<Actividad>();

		Scanner escaner = new Scanner(System.in);
		System.out.println("Escriba un titulo para el Learning Path");
		String titulo = escaner.nextLine();
		System.out.println("Escriba una descripcion para el Learning Path");
		String descripcion = escaner.nextLine();
		System.out.println("Escriba el nivel de dificultad");
		String nivelDificultad = escaner.nextLine();
		System.out.println("Escriba la duracion estimada en minutos");
		int duracion = escaner.nextInt();
		System.out.println("Escoja las actividades que haran parte del Learning Path");
		
		boolean check = true;
		while ( check )
		{
			System.out.println("Escoja el ID de las actividades, escoja 0 para detenerse.");
			String codigo = escaner.nextLine();
			if (codigo == "0") 
			{ 
				check = false;
				escaner.close();
			}
			else 
			{
				if ( GeneradorActividades.validarActividad(codigo) ) { actividades.add(GeneradorActividades.getActividad(codigo)); }
				else { System.out.println("Actividad no encontrada"); }
			}
		}
		profesor.crearLearningPath(titulo, descripcion, nivelDificultad, duracion, actividades);
	}

	public static void EditarLearningPathProfesor( Profesor profesor )
	{
		profesor.crearLearningPath();
	}

	public static void CrearActividadProfesor( Profesor profesor )
	{
		profesor.crearLearningPath();
	}

	public static void EditarActividadProfesor( Profesor profesor )
	{
		profesor.crearLearningPath();
	}

	public static void ClonarActividadProfesor( Profesor profesor )
	{
		profesor.crearLearningPath();
	}

	public static void menuEstudiante( Estudiante estudiante )
	{
		
	}

	
    public static void main( String[] args )
    {
        
        while (true)
        {
            String login = menuLogin();
			if (login == "error")
			{  
				System.exit(0);
				break;
			}
			else
			{
				Usuario usuario = usuarios.get(login);
				if (  usuario instanceof Profesor ) 
				{
					Profesor profesor = (Profesor) usuario; 
					menuProfesor(profesor);
				}
				else if ( usuario instanceof Estudiante)
				{
					Estudiante estudiante = (Estudiante) usuario;
					menuEstudiante( estudiante);
				}
			}
        }
    }
}