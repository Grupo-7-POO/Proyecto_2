package modelo.actividades;

import java.util.List;
import java.util.Date;
import modelo.preguntas.PreguntaAbierta;




public class Examen extends Actividad {
    private String estadoCalificacion;
    private List<PreguntaAbierta> preguntas;

    // Constructor
    public Examen(String nombre, String descripcion, String objetivo, 
    String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
    Actividad seguimiento, Date fechaLimite,String id, List<PreguntaAbierta> preguntas)
    {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, id);
        this.preguntas = preguntas;
        this.estadoCalificacion = "";
    }

    // Getters and Setters
    public List<PreguntaAbierta> getPreguntas()
    {
        return this.preguntas;
    }
    public String getEstadoCalificacion() {
        return estadoCalificacion;
    }

    public void setEstadoCalificacion(String estadoCalificacion) {
        this.estadoCalificacion = estadoCalificacion;
    }
}
