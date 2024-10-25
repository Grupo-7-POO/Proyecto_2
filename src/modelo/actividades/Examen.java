package modelo.actividades;

import java.util.List;
import modelo.preguntas.PreguntaAbierta;


import java.util.List;

public class Examen extends Actividad {
    private String estadoCalificacion;

    // Constructor
    public Examen(String nombre, String descripcion, String objetivo, String nivelDificultad, 
                  String duracionEstimada, Date fechaLimite, String resultado, String id, 
                  List<Pregunta> preguntas) 
    {
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
