package persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exceptions.InformacionInconsistenteException;
import exceptions.LearningPathDuplicadoException;
import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Quiz;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;
import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;
import modelo.Reseña;
import modelo.preguntas.Opcion;
import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import modelo.preguntas.Pregunta;
import sistemabase.EstadoGlobal;

public class PersistenciaLearningPathsJson implements IPersistenciaLearningPaths {

    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String DESCRIPCION = "descripcion";
    private static final String NIVEL_DIFICULTAD = "nivelDificultad";
    private static final String DURACION = "duracion";
    private static final String ACTIVIDADES = "actividades";
    private static final String TIPO_ACTIVIDAD = "tipoActividad";
    private static final String PREGUNTAS = "preguntas";
    private static final String ESTUDIANTES_CURSANDO = "estudiantesCursando";
    private static final String ESTUDIANTES_COMPLETADO = "estudiantesCompletado";
    private static final String LOGIN_PROFESOR = "loginProfesor";
	private String objetivo;
	private String nivelDificultad;
	private Actividad seguimiento;
	private Date fechaLimite;
    private String nombre;
	private String descripcion;
	private double duracionEstimada;
	private List<Actividad> preRequisitos;
	private String resultado;
	private String id;
	private List<Reseña> reseñas;
	private double rating;
	


    @Override
    public void cargarLearningPath(String archivo, LearningPath learningPath) throws IOException, InformacionInconsistenteException, JSONException, LearningPathDuplicadoException {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        learningPath.setTitulo(raiz.getString(TITULO));
        learningPath.setDescripcion(raiz.getString(DESCRIPCION));
        learningPath.setNivelDificultad(raiz.getString(NIVEL_DIFICULTAD));
        learningPath.setDuracion(raiz.getInt(DURACION));

        JSONArray actividades = raiz.getJSONArray(ACTIVIDADES);
        for (int i = 0; i < actividades.length(); i++) {
            JSONObject jActividad = actividades.getJSONObject(i);
            Actividad actividad = cargarActividad(jActividad);
            learningPath.getActividades().add(actividad);
            } 

//xd

        

        JSONArray estudiantesCursando = raiz.getJSONArray(ESTUDIANTES_CURSANDO);
        for (int i = 0; i < estudiantesCursando.length(); i++) {
            String login = estudiantesCursando.getString(i);
            Estudiante estudiante = (Estudiante) EstadoGlobal.getUsuarios().get(login);
            learningPath.getEstudiantesCursando().add(estudiante);
        }

        JSONArray estudiantesCompletado = raiz.getJSONArray(ESTUDIANTES_COMPLETADO);
        for (int i = 0; i < estudiantesCompletado.length(); i++) {
            String login = estudiantesCompletado.getString(i);
            Estudiante estudiante = (Estudiante) EstadoGlobal.getUsuarios().get(login);
            learningPath.getEstudiantesGraduados().add(estudiante);
        }

        String loginProfesor = raiz.getString(LOGIN_PROFESOR);
        Profesor profesor = (Profesor) EstadoGlobal.getUsuarios().get(loginProfesor);
        learningPath.setProfesor(profesor.getLogin());
    }

    private Actividad cargarActividad(JSONObject jActividad) throws JSONException {
        String tipo = jActividad.getString(TIPO_ACTIVIDAD);
        switch (tipo) {
            case "Encuesta":
                return cargarEncuesta(jActividad);
            case "Examen":
                return cargarExamen(jActividad);
            case "Quiz":
                return cargarQuiz(jActividad);
            case "RecursoEducativo":
                return cargarRecursoEducativo(jActividad);
            case "Tarea":
                return cargarTarea(jActividad);
            default:
                throw new JSONException("Tipo de actividad desconocido: " + tipo);
        }
    }

    private Encuesta cargarEncuesta(JSONObject jActividad) throws JSONException {
        String id = jActividad.getString(ID);
        String titulo = jActividad.getString(TITULO);
        String descripcion = jActividad.getString(DESCRIPCION);
        int duracion = jActividad.getInt(DURACION);
    
        Encuesta encuesta = new Encuesta(titulo, descripcion, objetivo,nivelDificultad, duracion, new ArrayList<>(), seguimiento, fechaLimite, id, new ArrayList<>());
    
        JSONArray jPreguntas = jActividad.getJSONArray(PREGUNTAS);
        for (int i = 0; i < jPreguntas.length(); i++) {
            JSONObject jPregunta = jPreguntas.getJSONObject(i);
            PreguntaAbierta pregunta = cargarPreguntaAbierta(jPregunta);
            encuesta.getPreguntas().add(pregunta);
        }
    
        return encuesta;
        }
    
    private Examen cargarExamen(JSONObject jActividad) throws JSONException {
            String id = jActividad.getString(ID);
            String titulo = jActividad.getString(TITULO);
            String descripcion = jActividad.getString(DESCRIPCION);
            int duracion = jActividad.getInt(DURACION);
            List<Pregunta> preguntas = new ArrayList<>();
     
            Examen examen = new Examen(titulo, descripcion, objetivo,nivelDificultad, duracion, new ArrayList<>(), seguimiento, fechaLimite, id, new ArrayList<>());
        
            JSONArray jPreguntas = jActividad.getJSONArray(PREGUNTAS);
            for (int i = 0; i < jPreguntas.length(); i++) {
                JSONObject jPregunta = jPreguntas.getJSONObject(i);
                PreguntaCerrada pregunta = cargarPreguntaCerrada(jPregunta);
                preguntas.add(pregunta);
            }
        
            return examen;
        }

    private PreguntaAbierta cargarPreguntaAbierta(JSONObject jPregunta) throws JSONException {
    String enunciado = jPregunta.getString("enunciado");
    String explicacion = jPregunta.getString("explicacion");
    String id = jPregunta.getString("id");
    boolean esCorrecta = jPregunta.getBoolean("esCorrecta");

    PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, explicacion, id);
    pregunta.setEsCorrecta(esCorrecta);

        return pregunta;
    }
	
	private Quiz cargarQuiz(JSONObject jActividad) throws JSONException {
		String id = jActividad.getString(ID);
		String titulo = jActividad.getString(TITULO);
		String descripcion = jActividad.getString(DESCRIPCION);
		int duracion = jActividad.getInt(DURACION);
	
		Quiz quiz = new Quiz(titulo, descripcion, objetivo,nivelDificultad, duracion, new ArrayList<>(), seguimiento, fechaLimite, id, new ArrayList<>());
	
		JSONArray jPreguntas = jActividad.getJSONArray(PREGUNTAS);
		for (int i = 0; i < jPreguntas.length(); i++) {
			JSONObject jPregunta = jPreguntas.getJSONObject(i);
			PreguntaCerrada pregunta = cargarPreguntaCerrada(jPregunta);
			quiz.getPreguntas().add(pregunta);
		}
	
		return quiz;
	}
	
	private RecursoEducativo cargarRecursoEducativo(JSONObject jActividad) throws JSONException {
		String id = jActividad.getString(ID);
		String titulo = jActividad.getString(TITULO);
		String descripcion = jActividad.getString(DESCRIPCION);
        String tipoRecurso = jActividad.getString("tipoRecurso");
        String urlRecurso = jActividad.getString("urlRecurso");
        String descripcionRecurso = jActividad.getString("descripcionRecurso");
	
		RecursoEducativo recurso = new RecursoEducativo(nombre, descripcion, objetivo, 
        nivelDificultad, duracionEstimada, preRequisitos,  
        seguimiento, fechaLimite, id, tipoRecurso, urlRecurso, titulo,
        descripcionRecurso);
	
		return recurso;
	}
	
	private Tarea cargarTarea(JSONObject jActividad) throws JSONException {
		String id = jActividad.getString(ID);
		String descripcion = jActividad.getString(DESCRIPCION);
        String motivoEntrega = jActividad.getString("motivoEntrega");
        String estadoEnvio = jActividad.getString("estadoEnvio");
	
		Tarea tarea = new Tarea(nombre, descripcion, objetivo, 
        nivelDificultad, duracionEstimada, preRequisitos,  
        seguimiento, fechaLimite,id,
        motivoEntrega, estadoEnvio);
	
		return tarea;
	}
	
	private PreguntaCerrada cargarPreguntaCerrada(JSONObject jPregunta) throws JSONException {
		String enunciado = jPregunta.getString("enunciado");
		String explicacion = jPregunta.getString("explicacion");
		String id = jPregunta.getString("id");
		boolean esCorrecta = jPregunta.getBoolean("esCorrecta");
	
        JSONArray jOpciones = jPregunta.getJSONArray("opciones");

        List<Opcion> opciones = new ArrayList<>();
        for (int i = 0; i < jOpciones.length(); i++) {
            JSONObject jOpcion = jOpciones.getJSONObject(i);
            Opcion opcion = new Opcion(jOpcion.getString("texto"));
            opciones.add(opcion);
        }
        PreguntaCerrada pregunta = new PreguntaCerrada(enunciado, explicacion, id, opciones);
		pregunta.setEsCorrecta(esCorrecta);
	
		return pregunta;
	}
    

    @Override

    public void salvarLearningPath(String archivo, LearningPath learningPath) throws IOException {
    JSONObject jLearningPath = new JSONObject();
    jLearningPath.put(TITULO, learningPath.getTitulo());
    jLearningPath.put(DESCRIPCION, learningPath.getDescripcion());
    jLearningPath.put(NIVEL_DIFICULTAD, learningPath.getNivelDificultad());
    jLearningPath.put(DURACION, learningPath.getDuracion());

    JSONArray jActividades = new JSONArray();
    for (Actividad actividad : learningPath.getActividades()) {
        jActividades.put(salvarActividad(actividad));
    }
    jLearningPath.put(ACTIVIDADES, jActividades);

    JSONArray jEstudiantesCursando = new JSONArray();
    for (Estudiante estudiante : learningPath.getEstudiantesCursando()) {
        jEstudiantesCursando.put(estudiante.getLogin());
    }
    jLearningPath.put(ESTUDIANTES_CURSANDO, jEstudiantesCursando);

    JSONArray jEstudiantesCompletado = new JSONArray();
    for (Estudiante estudiante : learningPath.getEstudiantesGraduados()) {
        jEstudiantesCompletado.put(estudiante.getLogin());
    }
    jLearningPath.put(ESTUDIANTES_COMPLETADO, jEstudiantesCompletado);

    jLearningPath.put(LOGIN_PROFESOR, learningPath.getProfesor());

    Files.write(new File(archivo).toPath(), jLearningPath.toString().getBytes());
}

private JSONObject salvarActividad(Actividad actividad) {
		JSONObject jActividad = new JSONObject();
		jActividad.put(ID, actividad.getID());
		jActividad.put(TITULO, actividad.getTitulo());
		jActividad.put(DESCRIPCION, actividad.getDescripcion());
        jActividad.put(DURACION, actividad.getDuracionEstimada());

		if (actividad instanceof Encuesta) {
			jActividad.put(TIPO_ACTIVIDAD, "Encuesta");
			jActividad.put(PREGUNTAS, salvarPreguntas(((Encuesta) actividad).getPreguntas()));
		} else if (actividad instanceof Examen) {
			jActividad.put(TIPO_ACTIVIDAD, "Examen");
			jActividad.put(PREGUNTAS, salvarPreguntas(((Examen) actividad).getPreguntas()));
		} else if (actividad instanceof Quiz) {
			jActividad.put(TIPO_ACTIVIDAD, "Quiz");
			jActividad.put(PREGUNTAS, salvarPreguntas(((Quiz) actividad).getPreguntas()));
		} else if (actividad instanceof RecursoEducativo) {
			jActividad.put(TIPO_ACTIVIDAD, "RecursoEducativo");
		} else if (actividad instanceof Tarea) {
			jActividad.put(TIPO_ACTIVIDAD, "Tarea");
		}

		return jActividad;
	}

private JSONArray salvarPreguntas(List<? extends Pregunta> preguntas) {
		JSONArray jPreguntas = new JSONArray();
		for (Pregunta pregunta : preguntas) {
			JSONObject jPregunta = new JSONObject();
			jPregunta.put("enunciado", pregunta.getEnunciado());
			jPregunta.put("explicacion", pregunta.getExplicacion());
			jPregunta.put("id", pregunta.getID());
            if (pregunta instanceof PreguntaAbierta) {
                jPregunta.put("esCorrecta", ((PreguntaAbierta) pregunta).getEsCorrecta());
            } else if (pregunta instanceof PreguntaCerrada) {
                jPregunta.put("esCorrecta", ((PreguntaCerrada) pregunta).getEsCorrecta());
            }
			jPreguntas.put(jPregunta);
		}
		return jPreguntas;
}
}