package modelo.actividades;

import java.util.List;
import java.util.Date;
import modelo.preguntas.PreguntaAbierta;
import java.util.Date;

public class Encuesta extends Actividad {
    private boolean estadoCompletado;

    // Constructor
    public Encuesta(String nombre, String descripcion, String objetivo, String nivelDificultad, 
                    String duracionEstimada, Date fechaLimite, String resultado, String id, 
                    List<PreguntaAbierta> preguntas) {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, fechaLimite, resultado, id);
        this.preguntas = preguntas;
        this.estadoCompletado = false;
    }

    // Getters and Setters
    public boolean isEstadoCompletado() {
        return estadoCompletado;
    }

    public void setEstadoCompletado(boolean estadoCompletado) {
        this.estadoCompletado = estadoCompletado;
    }
}
