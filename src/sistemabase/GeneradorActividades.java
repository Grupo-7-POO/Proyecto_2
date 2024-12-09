package sistemabase;

import java.util.*;
import modelo.actividades.*;
import modelo.preguntas.*;

public class GeneradorActividades {

    private static final int LONGITUD_CODIGO = 8;
    private static Map<String, Actividad> actividades = new HashMap<>();

    // Obtener todas las actividades
    public static Map<String, Actividad> getActividades() {
        return actividades;
    }

    // Obtener una actividad específica
    public static Actividad getActividad(String codigo) {
        if (!validarActividad(codigo)) {
            throw new IllegalArgumentException("La actividad con código " + codigo + " no existe.");
        }
        return actividades.get(codigo);
    }

    // Validar existencia de una actividad
    public static boolean validarActividad(String codigo) {
        return actividades.containsKey(codigo);
    }

    // Generar un código único para actividades
    public static String generarCodigoActividades() {
        String codigo;
        do {
            codigo = UUID.randomUUID().toString().replaceAll("-", "").substring(0, LONGITUD_CODIGO);
        } while (actividades.containsKey(codigo));
        return codigo;
    }

    // Generar actividad genérica (utilizada por métodos específicos)
    private static <T extends Actividad> T guardarActividad(String codigo, T actividad) {
        actividades.put(codigo, actividad);
        return actividad;
    }

    // **Generadores**
    public static Encuesta generarEncuesta(String nombre, String descripcion, String objetivo,
                                           String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,
                                           Actividad seguimiento, Date fechaLimite, List<PreguntaAbierta> preguntas) {
        String codigo = generarCodigoActividades();
        Encuesta encuesta = new Encuesta(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos,
                                         seguimiento, fechaLimite, codigo, preguntas);
        return guardarActividad(codigo, encuesta);
    }

    public static Examen generarExamen(String nombre, String descripcion, String objetivo,
                                       String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,
                                       Actividad seguimiento, Date fechaLimite, List<PreguntaAbierta> preguntas) {
        String codigo = generarCodigoActividades();
        Examen examen = new Examen(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos,
                                   seguimiento, fechaLimite, codigo, preguntas);
        return guardarActividad(codigo, examen);
    }

    public static Quiz generarQuiz(String nombre, String descripcion, String objetivo,
                                   String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,
                                   Actividad seguimiento, Date fechaLimite, List<PreguntaCerrada> preguntas) {
        String codigo = generarCodigoActividades();
        Quiz quiz = new Quiz(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos,
                             seguimiento, fechaLimite, codigo, preguntas);
        return guardarActividad(codigo, quiz);
    }

    public static RecursoEducativo generarRecursoEducativo(String nombre, String descripcion, String objetivo,
                                                           String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,
                                                           Actividad seguimiento, Date fechaLimite, String tipoRecurso, String urlRecurso,
                                                           String titulo, String descripcionRecurso) {
        String codigo = generarCodigoActividades();
        RecursoEducativo recurso = new RecursoEducativo(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos,
                                                        seguimiento, fechaLimite, codigo, tipoRecurso, urlRecurso, titulo, descripcionRecurso);
        return guardarActividad(codigo, recurso);
    }

    public static Tarea generarTarea(String nombre, String descripcion, String objetivo,
                                     String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,
                                     Actividad seguimiento, Date fechaLimite, String motivoEntrega, String estadoEnvio) {
        String codigo = generarCodigoActividades();
        Tarea tarea = new Tarea(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos,
                                seguimiento, fechaLimite, codigo, motivoEntrega, estadoEnvio);
        return guardarActividad(codigo, tarea);
    }

    // **Clonadores**
    public static Encuesta clonarEncuesta(String codigo) {
        Encuesta original = (Encuesta) getActividad(codigo);
        String nuevoCodigo = generarCodigoActividades();
        Encuesta clon = new Encuesta(original.getNombre(), original.getDescripcion(), original.getObjetivo(),
                                     original.getNivelDificultad(), original.getDuracionEstimada(), original.getPreRequisitos(),
                                     original.getSeguimiento(), original.getFechaLimite(), nuevoCodigo, original.getPreguntas());
        return guardarActividad(nuevoCodigo, clon);
    }

    public static Examen clonarExamen(String codigo) {
        Examen original = (Examen) getActividad(codigo);
        String nuevoCodigo = generarCodigoActividades();
        Examen clon = new Examen(original.getNombre(), original.getDescripcion(), original.getObjetivo(),
                                 original.getNivelDificultad(), original.getDuracionEstimada(), original.getPreRequisitos(),
                                 original.getSeguimiento(), original.getFechaLimite(), nuevoCodigo, original.getPreguntas());
        return guardarActividad(nuevoCodigo, clon);
    }

    public static Quiz clonarQuiz(String codigo) {
        Quiz original = (Quiz) getActividad(codigo);
        String nuevoCodigo = generarCodigoActividades();
        Quiz clon = new Quiz(original.getNombre(), original.getDescripcion(), original.getObjetivo(),
                             original.getNivelDificultad(), original.getDuracionEstimada(), original.getPreRequisitos(),
                             original.getSeguimiento(), original.getFechaLimite(), nuevoCodigo, original.getPreguntas());
        return guardarActividad(nuevoCodigo, clon);
    }

    public static RecursoEducativo clonarRecursoEducativo(String codigo) {
        RecursoEducativo original = (RecursoEducativo) getActividad(codigo);
        String nuevoCodigo = generarCodigoActividades();
        RecursoEducativo clon = new RecursoEducativo(original.getNombre(), original.getDescripcion(), original.getObjetivo(),
                                                     original.getNivelDificultad(), original.getDuracionEstimada(), original.getPreRequisitos(),
                                                     original.getSeguimiento(), original.getFechaLimite(), nuevoCodigo,
                                                     original.getTipoRecurso(), original.getUrlRecurso(), original.getTitulo(), original.getDescripcionRecurso());
        return guardarActividad(nuevoCodigo, clon);
    }

    public static Tarea clonarTarea(String codigo) {
        Tarea original = (Tarea) getActividad(codigo);
        String nuevoCodigo = generarCodigoActividades();
        Tarea clon = new Tarea(original.getNombre(), original.getDescripcion(), original.getObjetivo(),
                               original.getNivelDificultad(), original.getDuracionEstimada(), original.getPreRequisitos(),
                               original.getSeguimiento(), original.getFechaLimite(), nuevoCodigo, original.getMotivoEntrega(),
                               original.getEstadoEnvio());
        return guardarActividad(nuevoCodigo, clon);
    }
}
