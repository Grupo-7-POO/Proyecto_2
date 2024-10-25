package modelo.actividades;

import java.util.List;
import java.util.Date;
import modelo.preguntas.PreguntaCerrada;

public class RecursoEducativo extends Actividad {
    private String tipoRecurso;
    private String urlRecurso;
    private String titulo;
    private String descripcion;
    private boolean completado;

    // Constructor
    public RecursoEducativo(String nombre, String descripcion, String objetivo, String nivelDificultad, 
                            String duracionEstimada, Date fechaLimite, String resultado, String id, 
                            String tipoRecurso, String urlRecurso, String titulo, boolean completado) 
                            {
        super(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, fechaLimite, resultado, id);
        this.tipoRecurso = tipoRecurso;
        this.urlRecurso = urlRecurso;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completado = completado;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}

