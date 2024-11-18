package modelo.actividades;

import java.util.List;
import java.util.Date;
import modelo.preguntas.PreguntaAbierta;

public class Encuesta extends Actividad {
    private boolean estadoCompletado;
    private List<PreguntaAbierta> preguntas;

    // Constructor
    public Encuesta(String nombre, String descripcion, String objetivo, 
        String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
        Actividad seguimiento, Date fechaLimite,String id, List<PreguntaAbierta> preguntas) 
    {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, id);

        this.preguntas = preguntas;
        this.estadoCompletado = false;
    }

    // Getters and Setters
    public List<PreguntaAbierta> getPreguntas()
    {
        return preguntas;
    }
    public boolean isEstadoCompletado() {
        return estadoCompletado;
    }

    public void setEstadoCompletado(boolean estadoCompletado) 
    {
        this.estadoCompletado = estadoCompletado;
    }

    public void setPreguntas( List<PreguntaAbierta> nuevasPreguntas )
    {
        this.preguntas = nuevasPreguntas;
    }

}
