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
		case 1: crearLearningPathProfesor( profesor );
		case 2: editarLearningPathProfesor( profesor );
		case 3: crearActividadProfesor( profesor );
		case 4: editarActividadProfesor( profesor );
		case 5: clonarActividadProfesor( profesor );
		case 6:
				escaner.close();
				System.exit(0);
				break;
		default:
				System.out.println("Opción no válida");
				break;
		}
	}

	public static void crearLearningPathProfesor( Profesor profesor )
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
			System.out.println("Escoja el ID de las actividades, 8 numeros.");
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
		LearningPath learningPathCreado = profesor.crearLearningPath(titulo, descripcion, nivelDificultad, duracion, actividades); // FALTA AÑADIR LEARNING PATH A BASE DE DATOS GENERAL
		learningPaths.put( learningPathCreado.getTitulo()+"_"+learningPathCreado.getVersion() , learningPathCreado);
	}
	public static LearningPath escogerLearningPath( Profesor profesor)
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Ingrese el titulo del Learning Path deseado:");
		String titulo = escaner.nextLine();
		escaner.close();
		System.exit(0);

		return profesor.getLearningPathbyNombre( titulo );
	}

	public static void editarLearningPathProfesor( Profesor profesor )
	{
		LearningPath learningPath = escogerLearningPath( profesor );
		Scanner escaner = new Scanner(System.in);
		System.out.println("Selecciona una opción:");
		System.out.println("1. Editar Titulo:");
		System.out.println("2. Editar Descripcion");
		System.out.println("3. Editar Nivel de Dificultad:");
		System.out.println("4. Editar Duracion:");
		System.out.println("5. Editar Actividades:");
		System.out.println("6. Salir:");

		System.out.print("Opción: ");
		int opcion = escaner.nextInt();

		switch(opcion)
		{
		case 1: editarTituloProfesor(learningPath);
		case 2: editarDescripcionProfesor( learningPath );
		case 3: editarNivelDificultadProfesor( learningPath );
		case 4: editarDuracionProfesor( learningPath );
		case 5: editarActividadesProfesor( learningPath );
		case 6:
				escaner.close();
				System.exit(0);
				break;
		default:
				System.out.println("Opción no válida");
				break;
		}
	}

	public static void editarTituloProfesor( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Escriba un nuevo titulo:");
		String titulo = escaner.nextLine();
		learningPath.setTitulo( titulo );
		learningPath.actualizacionRealizada();
		escaner.close();
		System.exit(0);
	}

	public static void editarDescripcionProfesor( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Escriba una nueva descripcion:");
		String descripcion = escaner.nextLine();
		learningPath.setDescripcion( descripcion );
		learningPath.actualizacionRealizada();
		escaner.close();
		System.exit(0);
	}

	public static void editarNivelDificultadProfesor( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Escoja un nivel de Dificultad:");
		System.out.println("1. Principiante");
		System.out.println("2. Intermedio");
		System.out.println("3. Avanzado");
		int nivel = escaner.nextInt();
		String dificultad = null;

		switch (nivel) {
			case 1: dificultad = "Principiante";
			case 2: dificultad = "Intermedio";
			case 3: dificultad = "Avanzado";
			default: System.out.println("Opcion no valida");
		}

		if ( dificultad != null)
		{
			learningPath.setNivelDificultad( dificultad );
			learningPath.actualizacionRealizada();
		}
		escaner.close();
		System.exit(0);
	}

	public static void editarDuracionProfesor( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Escoja una nueva duracion en minutos:");
		int duracion = escaner.nextInt();
		learningPath.setDuracion( duracion );
		learningPath.actualizacionRealizada();
		escaner.close();
		System.exit(0);
	}

	public static void editarActividadesProfesor( LearningPath learningPath )
	{
		List<Actividad> actividades = learningPath.getActividades();
		
		learningPath.actualizacionRealizada();
	}

	public static void crearActividadProfesor( Profesor profesor )
	{
		profesor.crearActividad();
	}

	public static void editarActividadProfesor( Profesor profesor )
	{
		profesor.editarActividad();
	}

	public static void clonarActividadProfesor( Profesor profesor )
	{
		profesor.clonarActividad();
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