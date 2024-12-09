package sistemabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import modelo.LearningPath;
import modelo.actividades.*;
import modelo.usuarios.*;
import modelo.preguntas.*;
import persistencia.*;

public class EstadoGlobal {
    private static Map<String, Usuario> usuarios;
    private static Map<String, LearningPath> learningPaths;
    private static Scanner escaner = new Scanner(System.in);

    // Getters para usuarios y learningPaths
    public static Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public static Map<String, LearningPath> getLearningPaths() {
        return learningPaths;
    }

    // Validar existencia de usuario por login
    public static boolean validarUsuario(String login) {
        return usuarios.containsKey(login);
    }

    // Validar login y contraseña
    public static boolean validarLogin(String login, String password) {
        if (validarUsuario(login)) {
            Usuario usuarioActual = usuarios.get(login);
            return usuarioActual.getPassword().equals(password); // Comparación de contenido
        }
        return false;
    }

	public static Usuario getUsuario( String login )
	{
		return usuarios.get( login );
	}

	public static void aniadirUsuario( Usuario usuario )
	{
		usuarios.put(usuario.getLogin(), usuario);
	}

	public static void aniadirLearningPath( LearningPath learningPath )
	{
		learningPaths.put( learningPath.getTitulo(), learningPath ); 	
	}

    // Función principal para cargar datos del sistema
    public static void cargarSistemaGlobal() {
		System.out.println("Cargando sistema...");
		usuarios = PersistenciaSistema.cargarUsuarios();
		PersistenciaSistema.cargarActividades();
		learningPaths = PersistenciaSistema.cargarLearningPaths();
		System.out.println("Sistema cargado correctamente.");
	}
	

    // Menú de inicio de sesión
    public static String menuLogin() {
        String login;
        String password;

        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Crear Usuario");
        System.out.print("Opción: ");

        int opcion = leerEntero("");
        switch (opcion) {
            case 1:
                System.out.print("Ingrese su login: ");
                login = escaner.nextLine();
                System.out.print("Ingrese su contraseña: ");
                password = escaner.nextLine();
                break;

            case 2:
                String[] usuarioCreado = crearUsuarioConsola();
                login = usuarioCreado[0];
                password = usuarioCreado[1];
                break;

            default:
                System.out.println("Opción no válida.");
                return "error";
        }

        if (validarLogin(login, password)) {
            return login;
        } else {
            System.out.println("Credenciales incorrectas.");
            return "error";
        }
    }

    // Crear usuario desde la consola
    public static String[] crearUsuarioConsola() {
		Usuario usuarioCreado;
	
		System.out.print("Ingrese su Nombre: ");
		String nombre = escaner.nextLine();
	
		System.out.print("Ingrese su email: ");
		String email = escaner.nextLine();
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			System.out.println("Email no válido. Intente de nuevo.");
			return new String[]{"error", ""};
		}
	
		System.out.print("Ingrese su login: ");
		String login = escaner.nextLine();
		if (usuarios.containsKey(login)) {
			System.out.println("El login ya existe. Intente con otro.");
			return new String[]{"error", ""};
		}
	
		System.out.print("Ingrese su contraseña(minimo 6 caracteres): ");
		String contrasena = escaner.nextLine();
		if (contrasena.length() < 6) {
			System.out.println("La contraseña debe tener al menos 6 caracteres.");
			return new String[]{"error", ""};
		}
	
		System.out.println("Seleccione una opción:");
		System.out.println("1. Usuario Profesor");
		System.out.println("2. Usuario Estudiante");
		System.out.print("Opción: ");
		int opcion = leerEntero("");
	
		switch (opcion) {
			case 1:
				usuarioCreado = new Profesor(nombre, email, login, contrasena);
				usuarios.put(login, usuarioCreado);
				break;
	
			case 2:
				usuarioCreado = new Estudiante(nombre, email, login, contrasena);
				usuarios.put(login, usuarioCreado);
				break;
	
			default:
				System.out.println("Opción no válida. Intente de nuevo.");
				return new String[]{"error", ""};
		}
	
		// Guardar cambios
		PersistenciaSistema.guardarUsuarios(usuarios);
		System.out.println("Usuario creado exitosamente y cambios guardados.");
	
		return new String[]{login, contrasena};
	}
	

    // Menú principal del profesor
    public static void menuProfesor(Profesor profesor) {
		System.out.println("Selecciona una opción:");
		System.out.println("1. Crear Learning Path");
		System.out.println("2. Editar Learning Path");
		System.out.println("3. Crear Actividad");
		System.out.println("4. Editar Actividades del Learning Path");
		System.out.println("5. Clonar Actividad");
		System.out.println("6. Salir");

		int opcion = leerEntero("Opción: ");
		switch (opcion) {
			case 1:
				crearLearningPathProfesor(profesor);
				break;

			case 2:
				editarLearningPathProfesor(profesor);
				break;

			case 3:
				crearActividadProfesor(profesor);
				break;

			case 4:
				LearningPath learningPath = escogerLearningPath(profesor);
				if (learningPath != null) {
					editarActividadesLPProfesor(learningPath); // Método corregido para edición de actividades
				} else {
					System.out.println("No se encontró el Learning Path.");
				}
				break;

			case 5:
				clonarActividadProfesor(profesor);
				break;

			case 6:
				System.out.println("Saliendo del menú...");
				System.exit(0);
				break;

			default:
				System.out.println("Opción no válida.");
		}
	}


    // Crear Learning Path para el profesor
    public static void crearLearningPathProfesor(Profesor profesor) {
		System.out.print("Escriba un título para el Learning Path: ");
		String titulo = escaner.nextLine();
		System.out.print("Escriba una descripción para el Learning Path: ");
		String descripcion = escaner.nextLine();
		System.out.print("Escriba el nivel de dificultad: ");
		String nivelDificultad = escaner.nextLine();
		int duracion = leerEntero("Escriba la duración estimada en minutos: ");
	
		// Crear el Learning Path inicialmente vacío de actividades
		LearningPath learningPath = profesor.crearLearningPath(titulo, descripcion, nivelDificultad, duracion, new LinkedList<>());
		learningPaths.put(titulo, learningPath);
		System.out.println("Learning Path creado exitosamente.");
	
		boolean seguir = true;
		while (seguir) {
			System.out.println("¿Desea agregar actividades al Learning Path ahora?");
			System.out.println("1. Sí");
			System.out.println("2. No");
			int opcion = leerEntero("Seleccione una opción: ");
	
			if (opcion == 1) {
				agregarActividadesALearningPath(learningPath);
			} else if (opcion == 2) {
				seguir = false; // Salir del bucle si no desea agregar actividades
			} else {
				System.out.println("Opción no válida.");
			}
		}
	
		PersistenciaSistema.guardarLearningPaths(learningPaths);
		System.out.println("Learning Path actualizado y cambios guardados.");
	}
	
	// Método para agregar actividades a un Learning Path existente
	public static void agregarActividadesALearningPath(LearningPath learningPath) {
		boolean seguir = true;
		while (seguir) {
			System.out.print("Escriba el ID de la actividad para agregar al Learning Path (o 0 para terminar): ");
			String codigo = escaner.nextLine().trim();
	
			if (codigo.equals("0")) {
				seguir = false; // Salir del bucle si se ingresa 0
			} else if (GeneradorActividades.validarActividad(codigo)) {
				Actividad actividad = GeneradorActividades.getActividad(codigo);
				if (!learningPath.getActividades().contains(actividad)) {
					learningPath.getActividades().add(actividad);
					System.out.println("Actividad añadida al Learning Path.");
				} else {
					System.out.println("La actividad ya está en el Learning Path.");
				}
			} else {
				System.out.println("Actividad no encontrada. Verifique el código.");
			}
		}
	}
	

    // Editar Learning Path del profesor
    public static void editarLearningPathProfesor(Profesor profesor) {
        LearningPath learningPath = escogerLearningPath(profesor);
        if (learningPath == null) {
            return;
        }

        System.out.println("1. Editar Título");
        System.out.println("2. Editar Descripción");
        System.out.println("3. Editar Nivel de Dificultad");
        System.out.println("4. Editar Duración");
        System.out.println("5. Editar Actividades");
        System.out.print("Opción: ");
        int opcion = leerEntero("");

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo título: ");
                String nuevoTitulo = escaner.nextLine();
                learningPath.setTitulo(nuevoTitulo);
                System.out.println("Título actualizado.");
                break;

            case 2:
                System.out.print("Ingrese la nueva descripción: ");
                String nuevaDescripcion = escaner.nextLine();
                learningPath.setDescripcion(nuevaDescripcion);
                System.out.println("Descripción actualizada.");
                break;

            case 3:
                System.out.print("Ingrese el nuevo nivel de dificultad: ");
                String nuevoNivel = escaner.nextLine();
                learningPath.setNivelDificultad(nuevoNivel);
                System.out.println("Nivel de dificultad actualizado.");
                break;

            case 4:
                int nuevaDuracion = leerEntero("Ingrese la nueva duración en minutos: ");
                learningPath.setDuracion(nuevaDuracion);
                System.out.println("Duración actualizada.");
                break;

            case 5:
                editarActividadesLPProfesor(learningPath);
                break;

            default:
                System.out.println("Opción no válida.");
        }
		PersistenciaSistema.guardarLearningPaths(learningPaths);
    	System.out.println("Learning Path actualizado y cambios guardados.");
    }

    public static LearningPath escogerLearningPath(Profesor profesor) {
        System.out.print("Ingrese el título del Learning Path deseado: ");
        String titulo = escaner.nextLine();
        LearningPath learningPath = profesor.getLearningPathbyNombre(titulo);

        if (learningPath == null) {
            System.out.println("Learning Path no encontrado.");
        }
        return learningPath;
    }
	
	public static void crearActividadProfesor(Profesor profesor) {
		System.out.println("Seleccione el tipo de actividad:");
		System.out.println("1. Encuesta");
		System.out.println("2. Examen");
		System.out.println("3. Quiz");
		System.out.println("4. Recurso Educativo");
		System.out.println("5. Tarea");
		System.out.print("Opción: ");
		int opcion = leerEntero("");
	
		// Datos comunes de todas las actividades
		System.out.print("Ingrese el nombre de la actividad: ");
		String nombre = escaner.nextLine();
		System.out.print("Ingrese la descripción de la actividad: ");
		String descripcion = escaner.nextLine();
		System.out.print("Ingrese el objetivo de la actividad: ");
		String objetivo = escaner.nextLine();
		System.out.print("Ingrese el nivel de dificultad: ");
		String nivelDificultad = escaner.nextLine();
		double duracionEstimada = leerEntero("Ingrese la duración estimada (en minutos): ");
		Date fechaLimite = leerFecha("Ingrese la fecha límite (dd-MMM-yyyy): ");
	
		// Preguntar si la actividad tendrá prerequisito
		Actividad prerequisito = null; // Por defecto, no hay prerequisito
		System.out.print("¿Esta actividad tendrá un prerequisito? (S/N): ");
		String respuesta = escaner.nextLine().trim().toUpperCase();
		if (respuesta.equals("S")) {
			System.out.print("Ingrese el código de la actividad que será el prerequisito: ");
			String codigo = escaner.nextLine().trim();
			if (GeneradorActividades.validarActividad(codigo)) {
				prerequisito = GeneradorActividades.getActividad(codigo);
				System.out.println("Prerrequisito asignado correctamente.");
			} else {
				System.out.println("Actividad no encontrada. Continuando sin prerequisito.");
			}
		}
	
		// Crear actividad según el tipo seleccionado
		Actividad nuevaActividad = null;
		switch (opcion) {
			case 1:
				List<PreguntaAbierta> preguntasEncuesta = new LinkedList<>();
				nuevaActividad = GeneradorActividades.generarEncuesta(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada,
						prerequisito != null ? Collections.singletonList(prerequisito) : new LinkedList<>(), null, fechaLimite, preguntasEncuesta);
				break;
	
			case 2:
				List<PreguntaAbierta> preguntasExamen = new LinkedList<>();
				nuevaActividad = GeneradorActividades.generarExamen(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada,
						prerequisito != null ? Collections.singletonList(prerequisito) : new LinkedList<>(), null, fechaLimite, preguntasExamen);
				break;
	
			case 3:
				List<PreguntaCerrada> preguntasQuiz = new LinkedList<>();
				nuevaActividad = GeneradorActividades.generarQuiz(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada,
						prerequisito != null ? Collections.singletonList(prerequisito) : new LinkedList<>(), null, fechaLimite, preguntasQuiz);
				break;
	
			case 4:
				System.out.print("Ingrese el tipo de recurso: ");
				String tipoRecurso = escaner.nextLine();
				System.out.print("Ingrese la URL del recurso: ");
				String urlRecurso = escaner.nextLine();
				System.out.print("Ingrese el título del recurso: ");
				String tituloRecurso = escaner.nextLine();
				System.out.print("Ingrese la descripción del recurso: ");
				String descripcionRecurso = escaner.nextLine();
				nuevaActividad = GeneradorActividades.generarRecursoEducativo(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada,
						prerequisito != null ? Collections.singletonList(prerequisito) : new LinkedList<>(), null, fechaLimite,
						tipoRecurso, urlRecurso, tituloRecurso, descripcionRecurso);
				break;
	
			case 5:
				System.out.print("Ingrese el motivo de entrega: ");
				String motivoEntrega = escaner.nextLine();
				nuevaActividad = GeneradorActividades.generarTarea(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada,
						prerequisito != null ? Collections.singletonList(prerequisito) : new LinkedList<>(), null, fechaLimite,
						motivoEntrega, "No entregado");
				break;
	
			default:
				System.out.println("Opción no válida.");
				return;
		}
	
		if (nuevaActividad != null) {
			profesor.aniadirActividadCreada(nuevaActividad);
			System.out.println("Actividad creada exitosamente.");
	
			// Guardar actividad en persistencia
			PersistenciaSistema.guardarActividades();
			System.out.println("Actividad guardada correctamente en el archivo.");
		}
	}
	
	

    public static void clonarActividadProfesor(Profesor profesor) {
        System.out.print("Ingrese el código de la actividad a clonar: ");
        String codigo = escaner.nextLine();
        if (GeneradorActividades.validarActividad(codigo)) {
            Actividad actividadClonada = null;

            if (GeneradorActividades.getActividad(codigo) instanceof Encuesta) {
                actividadClonada = GeneradorActividades.clonarEncuesta(codigo);
            } else if (GeneradorActividades.getActividad(codigo) instanceof Examen) {
                actividadClonada = GeneradorActividades.clonarExamen(codigo);
            } else if (GeneradorActividades.getActividad(codigo) instanceof Quiz) {
                actividadClonada = GeneradorActividades.clonarQuiz(codigo);
            } else if (GeneradorActividades.getActividad(codigo) instanceof RecursoEducativo) {
                actividadClonada = GeneradorActividades.clonarRecursoEducativo(codigo);
            } else if (GeneradorActividades.getActividad(codigo) instanceof Tarea) {
                actividadClonada = GeneradorActividades.clonarTarea(codigo);
            }

            if (actividadClonada != null) {
                profesor.aniadirActividadCreada(actividadClonada);
                System.out.println("Actividad clonada exitosamente. Nuevo código: " + actividadClonada.getID());
            }
        } else {
            System.out.println("Actividad no encontrada.");
        }
		PersistenciaSistema.guardarActividades();
		System.out.println("Actividad guardada correctamente en el archivo.");
    }

    public static void editarActividadesLPProfesor(LearningPath learningPath) {
        System.out.println("1. Añadir Actividad");
        System.out.println("2. Eliminar Actividad");
        System.out.print("Opción: ");
        int opcion = leerEntero("");

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el ID de la actividad que desea añadir: ");
                String idAñadir = escaner.nextLine();
                if (GeneradorActividades.validarActividad(idAñadir)) {
                    Actividad actividadAñadir = GeneradorActividades.getActividad(idAñadir);
                    if (!learningPath.getActividades().contains(actividadAñadir)) {
                        learningPath.getActividades().add(actividadAñadir);
                        System.out.println("Actividad añadida.");
                    } else {
                        System.out.println("La actividad ya está en el Learning Path.");
                    }
                } else {
                    System.out.println("Actividad no encontrada.");
                }
                break;

            case 2:
                System.out.print("Ingrese el ID de la actividad que desea eliminar: ");
                String idEliminar = escaner.nextLine();
                Actividad actividadEliminar = GeneradorActividades.getActividad(idEliminar);
                if (learningPath.getActividades().remove(actividadEliminar)) {
                    System.out.println("Actividad eliminada.");
                } else {
                    System.out.println("La actividad no estaba en el Learning Path.");
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }
		PersistenciaSistema.guardarActividades();
		System.out.println("Actividad guardada correctamente en el archivo.");
    }

	public static List<PreguntaAbierta> generadorPreguntasAbiertas() {
		List<PreguntaAbierta> preguntas = new LinkedList<>();
		boolean continuar = true;
	
		while (continuar) {
			System.out.println("\nEscriba el enunciado de la pregunta (o '00' para terminar): ");
			String enunciado = escaner.nextLine().trim();
			
			if (enunciado.equals("00")) {
				System.out.println("Finalizando creación de preguntas abiertas. Total generadas: " + preguntas.size());
				continuar = false;
			} else if (enunciado.isEmpty()) {
				System.out.println("El enunciado no puede estar vacío. Intente de nuevo.");
			} else {
				System.out.println("Escriba la explicación de la pregunta: ");
				String explicacion = escaner.nextLine().trim();
				
				if (explicacion.isEmpty()) {
					System.out.println("La explicación no puede estar vacía. Intente de nuevo.");
				} else {
					PreguntaAbierta preguntaNueva = GeneradorPreguntas.generarPreguntaAbierta(enunciado, explicacion);
					preguntas.add(preguntaNueva);
					System.out.println("Pregunta abierta creada con éxito.");
				}
			}
		}
	
		return preguntas;
	}
	
	public static List<PreguntaCerrada> generadorPreguntasCerradas() {
		List<PreguntaCerrada> preguntas = new LinkedList<>();
		boolean continuar = true;
	
		while (continuar) {
			System.out.println("\nEscriba el enunciado de la pregunta cerrada (o '00' para terminar): ");
			String enunciado = escaner.nextLine().trim();
	
			if (enunciado.equals("00")) {
				System.out.println("Finalizando creación de preguntas cerradas. Total generadas: " + preguntas.size());
				continuar = false;
			} else if (enunciado.isEmpty()) {
				System.out.println("El enunciado no puede estar vacío. Intente de nuevo.");
			} else {
				System.out.println("Escriba la explicación de la pregunta: ");
				String explicacion = escaner.nextLine().trim();
	
				if (explicacion.isEmpty()) {
					System.out.println("La explicación no puede estar vacía. Intente de nuevo.");
				} else {
					List<Opcion> opciones = new LinkedList<>();
					boolean tieneCorrecta = false;
	
					for (int i = 0; i < 4; i++) {
						System.out.println("Escriba el texto de la opción " + (i + 1) + ": ");
						String textoOpcion = escaner.nextLine().trim();
	
						if (textoOpcion.isEmpty()) {
							System.out.println("El texto de la opción no puede estar vacío. Intente de nuevo.");
							i--; // Reintenta esta opción
							continue;
						}
	
						System.out.println("¿Es esta opción la correcta? (S/N): ");
						String esCorrecta = escaner.nextLine().trim().toUpperCase();
	
						boolean opcionCorrecta = esCorrecta.equals("S");
						if (opcionCorrecta) {
							tieneCorrecta = true;
						}
	
						opciones.add(new Opcion(textoOpcion, opcionCorrecta));
					}
	
					if (!tieneCorrecta) {
						System.out.println("Debe haber al menos una opción correcta. Intente de nuevo.");
					} else {
						PreguntaCerrada preguntaNueva = GeneradorPreguntas.generarPreguntaCerrada(enunciado, explicacion, opciones);
						preguntas.add(preguntaNueva);
						System.out.println("Pregunta cerrada creada con éxito.");
					}
				}
			}
		}
	
		return preguntas;
	}
	
	public static void menuEstudiante(Estudiante estudiante) {
    while (true) {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Registrarse a un Learning Path");
        System.out.println("2. Iniciar Actividad");
        System.out.println("3. Completar Learning Path");
        System.out.println("4. Crear Reseña");
        System.out.println("5. Salir");
        int opcion = leerEntero("Opción: ");

        switch (opcion) {
            case 1:
                registrarLearningPathEstudiante(estudiante);
                break;

            case 2:
                iniciarActividadEstudiante(estudiante);
                break;

            case 3:
                completarLearningPathEstudiante(estudiante);
                break;

            case 4:
                crearReseniaEstudiante(estudiante);
                break;

            case 5:
                System.out.println("Saliendo del menú estudiante...");
                return;

            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}

	public static void registrarLearningPathEstudiante(Estudiante estudiante) {
		if (estudiante.getLearningPathInscrito() == null) {
			System.out.print("Escriba el nombre del Learning Path para inscribirse: ");
			String titulo = escaner.nextLine();
			LearningPath learningPath = learningPaths.get(titulo);

			if (learningPath != null) {
				estudiante.inscribirLearningPath(learningPath);
				System.out.println("Learning Path inscrito exitosamente.");
			} else {
				System.out.println("No se ha encontrado un Learning Path con ese nombre.");
			}
		} else {
			System.out.println("Ya tiene un Learning Path inscrito.");
		}
	}

	public static void iniciarActividadEstudiante(Estudiante estudiante) {
		if (estudiante.getLearningPathInscrito() == null) {
			System.out.println("No tiene un Learning Path inscrito.");
			return;
		}
		LearningPath learningPath = estudiante.getLearningPathInscrito();
		List<Actividad> actividades = learningPath.getActividades();
		if (actividades.isEmpty()) {
			System.out.println("El Learning Path no tiene actividades disponibles.");
			return;
		}
		System.out.println("Actividades disponibles:");
		for (int i = 0; i < actividades.size(); i++) {
			System.out.println((i + 1) + ". " + actividades.get(i).getNombre());
		}
		int opcion = leerEntero("Seleccione el número de la actividad que desea realizar: ");
		if (opcion < 1 || opcion > actividades.size()) {
			System.out.println("Opción inválida. Por favor, seleccione un número entre 1 y " + actividades.size() + ".");
			return;
		}
		Actividad actividad = actividades.get(opcion - 1);
	
		// Validar prerequisitos
		if (!actividad.getPreRequisitos().isEmpty() && !estudiante.getActividadesCompletadas().containsAll(actividad.getPreRequisitos())) {
			System.out.println("No ha completado todos los prerequisitos requeridos para esta actividad.");
			System.out.println("Prerequisitos pendientes:");
			for (Actividad prerequisito : actividad.getPreRequisitos()) {
				if (!estudiante.getActividadesCompletadas().contains(prerequisito)) {
					System.out.println("- " + prerequisito.getNombre());
				}
			}
			return;
		}
	
		// Validar que la actividad existe en el sistema
		if (!GeneradorActividades.validarActividad(actividad.getID())) {
			System.out.println("La actividad seleccionada ya no está disponible en el sistema.");
			return;
		}
	
		// Iniciar la actividad según el tipo
		if (actividad instanceof Encuesta) {
			iniciarEncuestaEstudiante((Encuesta) actividad);
		} else if (actividad instanceof Examen) {
			iniciarExamenEstudiante((Examen) actividad);
		} else if (actividad instanceof Quiz) {
			iniciarQuiz((Quiz) actividad);
		} else if (actividad instanceof RecursoEducativo) {
			iniciarRecursoEducativo((RecursoEducativo) actividad);
		} else if (actividad instanceof Tarea) {
			iniciarTarea((Tarea) actividad);
		} else {
			System.out.println("Tipo de actividad no reconocido.");
			return;
		}
	
		// Registrar como completada
		estudiante.getActividadesCompletadas().add(actividad);
		System.out.println("Progreso actualizado: " + estudiante.getProgreso() + "%.");
	}	

	public static void iniciarEncuestaEstudiante(Encuesta encuesta) {
		List<String> respuestas = new LinkedList<>();
		for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
			System.out.println(pregunta.getEnunciado());
			System.out.print("Respuesta: ");
			String respuesta = escaner.nextLine();
			respuestas.add(respuesta);
		}

		encuesta.setEstadoCompletado(true);
		System.out.println("Encuesta completada.");
		if (encuesta.getSeguimiento() != null) {
			System.out.println("La siguiente actividad recomendada es: " + encuesta.getSeguimiento().getNombre());
		} else {
			System.out.println("No hay actividades de seguimiento.");
		}
	}

	public static void iniciarExamenEstudiante(Examen examen) {
		List<String> respuestas = new LinkedList<>();
		for (PreguntaAbierta pregunta : examen.getPreguntas()) {
			System.out.println(pregunta.getEnunciado());
			System.out.print("Respuesta: ");
			String respuesta = escaner.nextLine();
			respuestas.add(respuesta);
		}

		examen.setEstadoCalificacion("Enviado");
		System.out.println("Examen completado. Enviado para revisión.");
		if (examen.getSeguimiento() != null) {
			System.out.println("La siguiente actividad recomendada es: " + examen.getSeguimiento().getNombre());
		} else {
			System.out.println("No hay actividades de seguimiento.");
		}
	}

	public static void iniciarQuiz(Quiz quiz) {
		double contador = 0;
		for (PreguntaCerrada pregunta : quiz.getPreguntas()) {
			System.out.println(pregunta.getEnunciado());
			int opcionNumero = 1;
			for (Opcion opcion : pregunta.getOpciones()) {
				System.out.println(opcionNumero + ". " + opcion.getTexto());
				opcionNumero++;
			}
			System.out.print("Seleccione una opción (1-4): ");
			int respuestaNum;
			try {
				respuestaNum = Integer.parseInt(escaner.nextLine()) - 1;
				if (respuestaNum >= 0 && respuestaNum < pregunta.getOpciones().size()) {
					if (pregunta.getOpciones().get(respuestaNum).getEsCorrecta()) {
						contador++;
					}
				} else {
					System.out.println("Opción fuera de rango.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada no válida. Debe ser un número.");
			}
		}

		double calificacion = (contador / quiz.getPreguntas().size()) * 5;
		if (calificacion >= 3.0) {
			quiz.setEstadoCalificacion("Completado");
			System.out.println("Quiz completado exitosamente. Calificación: " + calificacion);
		} else {
			System.out.println("Quiz no aprobado. Calificación: " + calificacion);
		}
	}

	public static void iniciarRecursoEducativo(RecursoEducativo recursoEducativo) {
		System.out.println("Visualizando recurso educativo:");
		System.out.println("Tipo: " + recursoEducativo.getTipoRecurso());
		System.out.println("URL: " + recursoEducativo.getUrlRecurso());
		System.out.println("Título: " + recursoEducativo.getTitulo());
		System.out.println("Descripción: " + recursoEducativo.getDescripcionRecurso());

		recursoEducativo.setCompletado(true);
		System.out.println("Recurso educativo completado.");
		if (recursoEducativo.getSeguimiento() != null) {
			System.out.println("La siguiente actividad recomendada es: " + recursoEducativo.getSeguimiento().getNombre());
		} else {
			System.out.println("No hay actividades de seguimiento.");
		}
	}

	public static void iniciarTarea(Tarea tarea) {
		System.out.println("Iniciando tarea:");
		System.out.println("Nombre: " + tarea.getNombre());
		System.out.println("Descripción: " + tarea.getDescripcion());
		System.out.println("Objetivo: " + tarea.getObjetivo());
		System.out.println("Motivo de entrega: " + tarea.getMotivoEntrega());

		tarea.setEstadoEnvio("Enviada");
		System.out.println("Tarea enviada exitosamente.");
	}

	public static void completarLearningPathEstudiante(Estudiante estudiante) {
		LearningPath learningPath = estudiante.getLearningPathInscrito();
		if (learningPath == null) {
			System.out.println("No tiene un Learning Path inscrito.");
			return;
		}

		double progreso = estudiante.getProgreso();
		if (progreso == 100.0) {
			estudiante.terminarLearningPath();
			System.out.println("¡Felicidades! Learning Path completado exitosamente.");
		} else {
			System.out.println("Progreso insuficiente para completar el Learning Path. Progreso actual: " + progreso + "%.");
		}
	}

	public static void crearReseniaEstudiante(Estudiante estudiante) {
		System.out.print("Escriba el nombre del Learning Path para reseñar: ");
		String titulo = escaner.nextLine();
		LearningPath learningPath = learningPaths.get(titulo);

		if (learningPath == null) {
			System.out.println("Learning Path no encontrado.");
			return;
		}

		System.out.print("Escriba su comentario: ");
		String comentario = escaner.nextLine();

		double rating = leerDouble("Escriba la puntuación de 1 a 5: ");
		if (rating < 1 || rating > 5) {
			System.out.println("Puntuación inválida. Debe estar entre 1 y 5.");
			return;
		}

		System.out.println(estudiante.crearResenia(comentario, rating, learningPath));
	}

	public static double leerDouble(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			try {
				return Double.parseDouble(escaner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada no válida. Debe ser un número decimal.");
			}
		}
	}

	public static int leerEntero(String mensaje) {
		System.out.print(mensaje);
		while (true) {
			try {
				return Integer.parseInt(escaner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada no válida. Intente de nuevo.");
			}
		}
	}

	public static Date leerFecha(String mensaje) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		while (true) {
			System.out.print(mensaje);
			try {
				return formatter.parse(escaner.nextLine());
			} catch (ParseException e) {
				System.out.println("Formato de fecha incorrecto. Intente de nuevo (dd-MMM-yyyy).");
			}
		}
	}

	public static void main(String[] args) {
		try {
			cargarSistemaGlobal();
			while (true) {
				String login = menuLogin();
				if (login.equals("error")) {
					System.out.println("Cerrando sesión...");
					break;
				}

				Usuario usuario = usuarios.get(login);
				if (usuario instanceof Profesor) {
					menuProfesor((Profesor) usuario);
				} else if (usuario instanceof Estudiante) {
					menuEstudiante((Estudiante) usuario);
				}
			}
		} finally {
			// Guardar todos los cambios al salir
			PersistenciaSistema.guardarUsuarios(usuarios);
			PersistenciaSistema.guardarLearningPaths(learningPaths);
			PersistenciaSistema.guardarActividades();
			System.out.println("Todos los datos han sido guardados correctamente.");
		}
	}
}
