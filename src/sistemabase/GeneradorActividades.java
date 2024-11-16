package sistemabase;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Quiz;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;

import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;


public class GeneradorActividades
{
    private static Map<String, Actividad> actividades = new HashMap<String, Actividad>( );	

    public static Map<String, Actividad> getActividades()
    {
        return actividades;
    }

    public static Actividad getActividad( String codigo)
    {
        return actividades.get(codigo);
    }

    public static boolean validarActividad( String codigo )
    {
        return actividades.containsKey(codigo);
    }

    // GENERADORES DE ACTIVIDADES
    public static String generarCodigoActividades()
    {
        int numero = ( int ) ( Math.random( ) * 10e8 );
        String codigo = "" + numero;
        while( actividades.containsKey( codigo ) )
        {
            numero = ( int ) ( Math.random( ) * 10e8 );
            codigo = "" + numero;
        }

        while( codigo.length( ) < 8 )
            codigo = "0" + codigo;
        
        return codigo;
    }

    public static Encuesta generarEncuesta( String nombre, String descripcion, String objetivo, 
                                            String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos, 
                                            Actividad seguimiento, Date fechaLimite, List<PreguntaAbierta> preguntas )
    {
        String codigo = generarCodigoActividades();
        Encuesta nuevaEncuesta = new Encuesta( nombre,  descripcion,  objetivo, 
                                                 nivelDificultad,  duracionEstimada, preRequisitos,  
                                                 seguimiento,  fechaLimite, codigo, preguntas);
        actividades.put(codigo, nuevaEncuesta);
        return nuevaEncuesta;
    }

    public static Examen generarExamen(String nombre, String descripcion, String objetivo, 
                                        String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
                                        Actividad seguimiento, Date fechaLimite, List<PreguntaAbierta> preguntas)
    {
        String codigo = generarCodigoActividades();
        Examen nuevoExamen = new Examen( nombre,  descripcion,  objetivo, 
                                         nivelDificultad,  duracionEstimada, preRequisitos,  
                                         seguimiento,  fechaLimite, codigo, preguntas);
        actividades.put(codigo, nuevoExamen);
        return nuevoExamen;
    }

    public static Quiz generarQuiz(String nombre, String descripcion, String objetivo, 
                                    String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
                                    Actividad seguimiento, Date fechaLimite, List<PreguntaCerrada> preguntas)
    {
        String codigo = generarCodigoActividades();
        Quiz nuevoQuiz = new Quiz(  nombre,  descripcion,  objetivo, 
                                 nivelDificultad,  duracionEstimada,  preRequisitos,  
                                 seguimiento,  fechaLimite, codigo, preguntas);
        actividades.put(codigo, nuevoQuiz);
        return nuevoQuiz;
    }

    public static RecursoEducativo generarRecursoEducativo(String nombre, String descripcion, String objetivo, 
                                                            String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
                                                            Actividad seguimiento, Date fechaLimite, String tipoRecurso, String urlRecurso, String titulo,
                                                            String descripcionRecurso)
    {
        String codigo = generarCodigoActividades();
        RecursoEducativo nuevoRecursoEducativo = new RecursoEducativo(nombre,  descripcion,  objetivo, 
                                                                    nivelDificultad,  duracionEstimada,  preRequisitos,  
                                                                    seguimiento,  fechaLimite, codigo,  tipoRecurso,  urlRecurso,  titulo, descripcionRecurso);
        actividades.put(codigo, nuevoRecursoEducativo);
        return nuevoRecursoEducativo;
    }

    public static Tarea generarTarea(String nombre, String descripcion, String objetivo, 
                                    String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
                                    Actividad seguimiento, Date fechaLimite, String motivoEntrega, String estadoEnvio)
    {
        String codigo = generarCodigoActividades();
        Tarea nuevaTarea = new Tarea( nombre,  descripcion,  objetivo, 
                                                                     nivelDificultad,  duracionEstimada, preRequisitos,  
                                                                     seguimiento,  fechaLimite, codigo,
                                                                     motivoEntrega, estadoEnvio);
        actividades.put(codigo, nuevaTarea);
        return nuevaTarea;
    }
}
