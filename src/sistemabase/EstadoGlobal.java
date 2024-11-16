package sistemabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Quiz;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;
import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import modelo.usuarios.Usuario;
import modelo.preguntas.Opcion;
import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;


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
		String login;
		String password;

        Scanner escaner = new Scanner(System.in);
        System.out.println("1. Iniciar Sesion");
		System.out.println("2. Crear Usuario");
		System.out.println("Opción: ");
			
		int opcion = escaner.nextInt();

		switch (opcion)
		{
		case 1:
				System.out.println("Ingrese su login: ");
				login = escaner.nextLine();
				System.out.print(login); 
				System.out.println("Ingrese su contraseña:");
				password = escaner.nextLine();
				break;

		case 2: 
				String[] usuarioCreado = crearUsuarioConsola();
				login = usuarioCreado[0];
				password = usuarioCreado[1];
				break;
		default:
				System.out.println("Opción no válida");
				escaner.close();
				System.exit(0);
				return "error";
		}
		escaner.close();


        if ( validarLogin(login, password) ) 
		{
			return login;
		}
		else { return "error"; }
		
    }

	public static String[] crearUsuarioConsola()
	{
		Usuario usuarioCreado;
		Scanner escaner = new Scanner(System.in);

		System.out.println("Ingrese su Nombre:");
		String nombre = escaner.nextLine();
		System.out.println("Ingrese su email: ");
		String email = escaner.nextLine();
		System.out.println("Ingrese su login: ");
		String login = escaner.nextLine();
		if ( usuarios.containsKey(login) == true )
		{
			System.out.print("Login ya existe");
			escaner.close();
			System.exit(0);
		}
		System.out.println("Ingrese su contraseña: ");
		String contrasena = escaner.nextLine();

		System.out.println("Selecciona una opción:");
		System.out.println("1. Usuario Profesor");
		System.out.println("2. Usuario Estudiante");

		System.out.print("Opción: ");
			
		int opcion = escaner.nextInt();

		switch (opcion)
		{
			case 1: 
					usuarioCreado = new Profesor(nombre, email, login, contrasena);
					usuarios.put( login, usuarioCreado );
					break;
			case 2: 
					usuarioCreado = new Estudiante(nombre, email, login, contrasena);
					usuarios.put( login, usuarioCreado );
					break;
			default:
					System.out.println("Opción no válida");
					escaner.close();
					System.exit(0);
					break; // POSIBLE AÑADIR EXDCEPTION DE TIPO DE USUARIO NO VALIDO
		}
		escaner.close();
		return new String[] {login, contrasena};

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
		case 1: 
				crearLearningPathProfesor( profesor );
				break;
		case 2: 
				editarLearningPathProfesor( profesor );
				break;
		case 3: 
				crearActividadProfesor( profesor );
				break;	
		case 4: 
				editarActividadProfesor( profesor );
				break;
		case 5: 
				clonarActividadProfesor( profesor );
				break;
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
		case 5: editarActividadesLPProfesor( learningPath );
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
	}

	public static void editarDescripcionProfesor( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Escriba una nueva descripcion:");
		String descripcion = escaner.nextLine();
		learningPath.setDescripcion( descripcion );
		learningPath.actualizacionRealizada();
		escaner.close();
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
	}

	public static void editarDuracionProfesor( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Escoja una nueva duracion en minutos:");
		int duracion = escaner.nextInt();
		learningPath.setDuracion( duracion );
		learningPath.actualizacionRealizada();
		escaner.close();
	}

	public static void editarActividadesLPProfesor( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Selecciona una opción:");
		System.out.println("1. Añadir Actividad:");
		System.out.println("2. Eliminar Actividad");
		System.out.print("Opción: ");
		int opcion = escaner.nextInt();

		switch(opcion)
		{
		case 1: añadirActividadLPProfesor(learningPath);
		case 2: eliminarActividadLPProfesor( learningPath );
		default:
				System.out.println("Opción no válida");
				break;
		}
		escaner.close();
	}

	public static void añadirActividadLPProfesor ( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Inserte el codigo de la actividad que desea añadir:");
		String idBuscado = escaner.nextLine();
		
		if (idBuscado.length() == 8)
		{
			Actividad actividadBuscada = GeneradorActividades.getActividad(idBuscado);
			List<Actividad> actividades = learningPath.getActividades();
	
			if ( actividades.contains(actividadBuscada) == true) { System.out.println("La actividad ya se encuentra dentro del LearningPath"); }
			else
			{
				actividades.add(actividadBuscada);
				learningPath.actualizacionRealizada();
			}
		}
		else 
		{ System.out.println("Recuerde que los ID de las actividades son de 8 digitos"); }
		escaner.close();
	}

	public static void eliminarActividadLPProfesor ( LearningPath learningPath )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Inserte el codigo de la actividad que desea eliminar:");
		String idBuscado = escaner.nextLine();
		
		if (idBuscado.length() == 8)
		{
			Actividad actividadBuscada = GeneradorActividades.getActividad(idBuscado);
			List<Actividad> actividades = learningPath.getActividades();
	
			if ( actividades.contains(actividadBuscada) == true) 
			{
				actividades.remove(actividadBuscada);
				learningPath.actualizacionRealizada();
			}
			else
			{
				System.out.println("La actividad no se encuentra dentro del LearningPath");
			}
		}
		else 
		{ System.out.println("Recuerde que los ID de las actividades son de 8 digitos"); }
		escaner.close();
	}

	public static void crearActividadProfesor( Profesor profesor )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Selecciona el tipo de  Actividad");
		System.out.println("1. Encuesta");
		System.out.println("2. Examen");
		System.out.println("3. Quiz");
		System.out.println("4. Recurso Educativo");
		System.out.println("5. Tarea");
		System.out.println("6. Salir:");

		System.out.print("Opción: ");
		int opcion = escaner.nextInt();
		// Campos en comun de todas las actividades
		System.out.println("Escriba el nombre de la actividad");
		String nombre = escaner.nextLine();
		System.out.println("Escriba la descripcion de la actividad");
		String descripcion = escaner.nextLine();
		System.out.println("Escriba el objetivo nombre de la actividad");
		String objetivo = escaner.nextLine();

		System.out.println("Escriba la duracion estimada de la actividad");
		String value = escaner.nextLine(); // convertir a double
		double duracionEstimada = Double.parseDouble(value);

		System.out.println("Escriba el nivel de dificultad de la actividad");
		String nivelDificultad = escaner.nextLine();

		System.out.println("Escriba la fecha limite recomendada para la actividad en formato dd-MMM-yyyy");
		String date = escaner.nextLine(); // convertir a date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		Date fechaLimite = null;
		try {
			fechaLimite = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Escoja las actividades que son Pre Requisitos");

		boolean check1 = true;
		List<Actividad> actividadesPre = new LinkedList<Actividad>();
		while ( check1 )
		{
			System.out.println("Escoja el ID de las actividades, escoja 0 para detenerse.");
			System.out.println("Escoja el ID de las actividades, 8 numeros.");
			String codigo = escaner.nextLine();
			if (codigo.equals("0")) 
			{ 
				check1 = false;
				escaner.close();
			}
			else 
			{
				if ( GeneradorActividades.validarActividad(codigo) ) { actividadesPre.add(GeneradorActividades.getActividad(codigo)); }
				else { System.out.println("Actividad no encontrada"); }
			}
		}
		boolean check2 = true;
		Actividad actividadSeguimiento = null;
		while ( check2 )
		{
			System.out.println("Escoja la actividad de seguimiento");
			System.out.println("Escoja el ID de las actividad, 8 numeros.");
			String codigo = escaner.nextLine();
			if ( GeneradorActividades.validarActividad(codigo) ) 
			{ 
				actividadSeguimiento = GeneradorActividades.getActividad(codigo); 
				check2 = false;
			}
			else { System.out.println("Actividad no encontrada"); }
		}
		// Campos diferentes dependiendo de actividad
		switch(opcion)
		{
		case 1:
				List<PreguntaAbierta> preguntasEncuesta = generadorPreguntasAbiertas();
				Encuesta encuestaNueva = GeneradorActividades.generarEncuesta(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, preguntasEncuesta);
				profesor.añadirActividadCreada(encuestaNueva);
				break;
		case 2: 
				List<PreguntaAbierta> preguntasExamen = generadorPreguntasAbiertas();
				Examen examenNuevo = GeneradorActividades.generarExamen(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, preguntasExamen);
				profesor.añadirActividadCreada(examenNuevo);
				break;

		case 3: 
				List<PreguntaCerrada> preguntasQuiz = generadorPreguntasCerradas();
				Quiz quizNuevo = GeneradorActividades.generarQuiz(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, preguntasQuiz);
				profesor.añadirActividadCreada(quizNuevo);
				break;
		case 4: 
				System.out.println("Escriba el tipo de Recurso");
				String tipoRecurso = escaner.nextLine();
				System.out.println("Escriba el URL del Recurso");
				String urlRecurso = escaner.nextLine();
				System.out.println("Escriba el titulo del Recurso");
				String titulo = escaner.nextLine();
				System.out.println("Escriba la descripcion del Recurso");
				String descripcionRecurso = escaner.nextLine();
				RecursoEducativo recursoEducativoNuevo = GeneradorActividades.generarRecursoEducativo(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, tipoRecurso, urlRecurso, titulo, descripcionRecurso);
				profesor.añadirActividadCreada(recursoEducativoNuevo);
				break;
		case 5: 
				System.out.println("Escriba el motivo de entrega");
				String motivoEntrega = escaner.nextLine();
				Tarea tareaNueva = GeneradorActividades.generarTarea(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, actividadesPre, actividadSeguimiento, fechaLimite, motivoEntrega, "No Entregado" );
				profesor.añadirActividadCreada(tareaNueva);
				break;
		case 6:
				escaner.close();
				System.exit(0);
				break;
		default:
				System.out.println("Opción no válida");
				break;
		}
	}

	public static List<PreguntaAbierta> generadorPreguntasAbiertas() // El ususario escribe las preguntas que desea hasta 
	{
		List<PreguntaAbierta> preguntas = new LinkedList<>();
		boolean check = true;
		Scanner escaner = new Scanner(System.in);
		while ( check )
		{
			System.out.println("Escriba el enunciado de la pregunta: ");
			String enunciado = escaner.nextLine();
			if ( enunciado.equals("00") == false ) 
			{ 
				System.out.println("Escriba la explicacion de la pregunta: ");
				String explicacion = escaner.nextLine();
				PreguntaAbierta preguntaNueva = GeneradorPreguntas.generarPreguntaAbierta(enunciado, explicacion);
				preguntas.add(preguntaNueva);
				System.out.println("Escriba 00 para terminar de generar preguntas.");
			}
			else 
			{ 
				System.out.println("Se han generado"+preguntas.size()+"preguntas abiertas"); 
				check = false;
			}
		}
		escaner.close();
		return preguntas;
	}

	public static List<PreguntaCerrada> generadorPreguntasCerradas() // El ususario escribe las preguntas que desea hasta 
	{
		List<PreguntaCerrada> preguntas = new LinkedList<>();
		boolean check = true;
		
		Scanner escaner = new Scanner(System.in);
		while ( check )
		{
			boolean checkCorrecta = false;
			System.out.println("Escriba el enunciado de la pregunta: ");
			String enunciado = escaner.nextLine();
			if ( enunciado.equals("00") == false ) 
			{ 
				System.out.println("Escriba la explicacion de la pregunta: ");
				String explicacion = escaner.nextLine();

				List<Opcion> opciones = new LinkedList<>();

				for (int i = 0; i < 4; i++)
				{
					System.out.println("Escriba el enunciado de la opcion" + (i+1));
					String texto = escaner.nextLine();
					Opcion opcionActual = new Opcion(texto);
					System.out.println("Es la respuesta correcta? S/N");
					String esCorrecta = escaner.nextLine();
					if ( (esCorrecta.equals("S")) && (checkCorrecta = false) )
					{
						opcionActual.setEsCorrecta();
						checkCorrecta = true;
						System.out.println("Se ha escogido como la opcion correcta");
					}
					else{System.out.println("No se ha escogido como la opcion correcta");}
					opciones.add(opcionActual);
				}

				PreguntaCerrada preguntaNueva = GeneradorPreguntas.generarPreguntaCerrada(enunciado, explicacion, opciones);
				preguntas.add(preguntaNueva);
				System.out.println("Escriba 00 para terminar de generar preguntas.");
			}
			else 
			{ 
				System.out.println("Se han generado"+preguntas.size()+"preguntas abiertas"); 
				check = false;
			}
		}
		escaner.close();
		return preguntas;
	}

	public static void clonarActividadProfesor( Profesor profesor )
	{
		Scanner escaner = new Scanner(System.in);
		System.out.println("Ingresa el codigo de la Actividad a clonar");
		String idActividad = escaner.nextLine();
		Actividad actividadClonada = null;;
		if ( GeneradorActividades.validarActividad(idActividad) == true)
		{
			Actividad actividadClonar = GeneradorActividades.getActividad(idActividad);

			if ( actividadClonar instanceof Encuesta ){ actividadClonada = GeneradorActividades.clonarEncuesta(idActividad); }
			else if ( actividadClonar instanceof Examen ){ actividadClonada = GeneradorActividades.clonarExamen(idActividad); }
			else if ( actividadClonar instanceof Quiz ){ actividadClonada = GeneradorActividades.clonarQuiz(idActividad); }
			else if ( actividadClonar instanceof RecursoEducativo ){ actividadClonada = GeneradorActividades.clonarRecursoEducativo(idActividad); }
			else if ( actividadClonar instanceof Tarea ){ actividadClonada = GeneradorActividades.clonarTarea(idActividad); }

			String nuevoCodigo = actividadClonada.getID();
			System.out.println("El codigo ID de la actividad clonada es"+nuevoCodigo);
			profesor.añadirActividadCreada(actividadClonada);
		} 
		else 
		{ 
			System.out.println("Ingresa el codigo de la Actividad a clonar");
		}
		escaner.close();
	}

	public static void editarActividadProfesor( Profesor profesor )
	{
		//profesor.editarActividad();
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
					menuProfesor( profesor );
				}
				else if ( usuario instanceof Estudiante)
				{
					Estudiante estudiante = (Estudiante) usuario;
					menuEstudiante( estudiante );
				}
			}
        }
    }
}