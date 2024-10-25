package modelo.actividades;

import java.util.List;
import java.util.Date;
import modelo.preguntas.PreguntaAbierta;




public class Examen extends Actividad {
    private String estadoCalificacion;

    // Constructor
    public Examen(String nombre, String descripcion, String objetivo, String nivelDificultad, 
                  String duracionEstimada, Date fechaLimite, String resultado, String id, 
<<<<<<< HEAD
                  List<Pregunta> preguntas) 
    {
=======
                  List<PreguntaAbierta> preguntas) {
>>>>>>> Estudiante-3-STG
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, fechaLimite, resultado, id);
        this.preguntas = preguntas;
        this.estadoCalificacion = "";
    }

    // Getters and Setters
    public String getEstadoCalificacion() {
        return estadoCalificacion;
    }

    public void setEstadoCalificacion(String estadoCalificacion) {
        this.estadoCalificacion = estadoCalificacion;
    }
}
