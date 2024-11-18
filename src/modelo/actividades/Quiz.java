package modelo.actividades;

import java.util.List;
import java.util.Date;

import modelo.preguntas.PreguntaCerrada;

public class Quiz extends Actividad {
    private String estadoCalificacion;
    private List<PreguntaCerrada> preguntas;

    // Constructor
    public Quiz(String nombre, String descripcion, String objetivo, 
    String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
    Actividad seguimiento, Date fechaLimite,String id, List<PreguntaCerrada> preguntas) 
    {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, id);
        this.preguntas = preguntas;
        this.estadoCalificacion = "";
    }

    // Getters and Setters
    public List<PreguntaCerrada> getPreguntas()
    {
        return this.preguntas;
    }
    public String getEstadoCalificacion() {
        return this.estadoCalificacion;
    }

    public void setEstadoCalificacion(String estadoCalificacion) 
    {
        this.estadoCalificacion = estadoCalificacion;
    }

    public void setPreguntas( List<PreguntaCerrada> nuevasPreguntas )
    {
        this.preguntas = nuevasPreguntas;
    }
}
