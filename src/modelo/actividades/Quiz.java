package modelo.actividades;

import java.util.List;
import modelo.preguntas.PreguntaCerrada;


import java.util.List;

public class Quiz extends Actividad {
    private String estadoCalificacion;

    // Constructor
    public Quiz(String nombre, String descripcion, String objetivo, String nivelDificultad, 
                String duracionEstimada, Date fechaLimite, String resultado, String id, 
                List<Pregunta> preguntas) {
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
