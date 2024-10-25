package modelo.actividades;

import java.util.List;
import modelo.preguntas.PreguntaAbierta;


public class Tarea extends Actividad {
    private String motivoEntrega;
    private String estadoEnvio;

    // Constructor
    public Tarea(String nombre, String descripcion, String objetivo, String nivelDificultad, 
                 String duracionEstimada, Date fechaLimite, String resultado, String id, 
                 String motivoEntrega, String estadoEnvio) {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, fechaLimite, resultado, id);
        this.motivoEntrega = motivoEntrega;
        this.estadoEnvio = estadoEnvio;
    }

    // Getters and Setters
    public String getMotivoEntrega() {
        return motivoEntrega;
    }

    public void setMotivoEntrega(String motivoEntrega) {
        this.motivoEntrega = motivoEntrega;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }
}
