package modelo.actividades;

import java.util.List;
import java.util.Date;

public class RecursoEducativo extends Actividad {
    private String tipoRecurso;
    private String urlRecurso;
    private String titulo;
    private String descripcionRecurso;
    private boolean completado;

    // Constructor
    public RecursoEducativo(String nombre, String descripcion, String objetivo, 
                            String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
                            Actividad seguimiento, Date fechaLimite,String id, String tipoRecurso, String urlRecurso, String titulo,
                            String descripcionRecurso) 
    {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, preRequisitos, seguimiento, fechaLimite, id);
        this.tipoRecurso = tipoRecurso;
        this.urlRecurso = urlRecurso;
        this.titulo = titulo;
        this.descripcionRecurso = descripcionRecurso;
        this.completado = false;
    }

    // Getters and Setters
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getUrlRecurso() {
        return urlRecurso;
    }

    public void setUrlRecurso(String urlRecurso) {
        this.urlRecurso = urlRecurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionRecurso() {
        return descripcionRecurso;
    }

    public void setDescripcionRecurso(String descripcion) {
        this.descripcionRecurso = descripcion;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}

