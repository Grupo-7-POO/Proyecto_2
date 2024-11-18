package modelo.actividades;

import java.util.List;
import java.util.Date;


public class Tarea extends Actividad {
    private String motivoEntrega;
    private String estadoEnvio; // No entregado, entregado, exitoso, no exitoso

    // Constructor
    public Tarea(String nombre, String descripcion, String objetivo, 
                String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
                Actividad seguimiento, Date fechaLimite,String id,
                 String motivoEntrega, String estadoEnvio) 

    {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, id);        
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
