package sistemabase;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Quiz;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;

import modelo.preguntas.Pregunta;
import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;


public class GeneradorActividades
{
    private static Map<String, Actividad> actividades = new HashMap<String, Actividad>( );	

    public static String generarCodigo()
    {
        int numero = ( int ) ( Math.random( ) * 10e7 );
        String codigo = "" + numero;
        while( actividades.containsKey( codigo ) )
        {
            numero = ( int ) ( Math.random( ) * 10e7 );
            codigo = "" + numero;
        }

        while( codigo.length( ) < 7 )
            codigo = "0" + codigo;
        
        return codigo;
    }

    public static Encuesta generarEncuesta( String nombre, String descripcion, String obejtivo, 
                                            String nivelDificultad, String duracionEstimada, String preRequisitos, String seguimiento, String fechaLimite, PreguntaAbierta preguntaAbierta)
    {
        String codigo = generarCodigo();
        return new Encuesta(nombre, descripcion, obejtivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, codigo, null);

    }
}
