package sistemabase;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Quiz;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;


public class GeneradorActividades
{
    private static Set<String> codigos = new HashSet<String>( );	

    public static Actividad generarActividad(String nombre, String descripcion, String obejtivo, 
					String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
					Actividad seguimiento, Date fechaLimite, int tipoActividad)
    {
        int numero = ( int ) ( Math.random( ) * 10e7 );
        String codigo = "" + numero;
        while( codigos.contains( codigo ) )
        {
            numero = ( int ) ( Math.random( ) * 10e7 );
            codigo = "" + numero;
        }

        while( codigo.length( ) < 7 )
            codigo = "0" + codigo;

        if (tipoActividad == 1) // ENCUESTA
        {
            return new Encuesta(nombre, descripcion, obejtivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, codigo, null);
        }
        else if (tipoActividad == 2) // EXAMEN
        {
            return new Examen(nombre, descripcion, obejtivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, codigo, null);
        }
        else if (tipoActividad == 3)
        {
            return new Quiz(nombre, descripcion, obejtivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, codigo, null);
        }
        else if (tipoActividad == 4)
        {
            return new RecursoEducativo(nombre, descripcion, codigo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, nombre, obejtivo, nivelDificultad, codigo, descripcion);
        }
        else if (tipoActividad == 5)
        {
            return new Tarea(nombre, descripcion, descripcion, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, obejtivo, nivelDificultad, codigo);
        }

        
    }
}
