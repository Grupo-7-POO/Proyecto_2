package modelo.usuarios;

import modelo.LearningPath;
import modelo.actividades.Actividad;

import java.util.LinkedList;
import java.util.List;

public class Profesor extends Usuario {
    private List<LearningPath> learningPathsCreados;
    private List<Actividad> actividadesCreadas;

    public Profesor(String nombre, String email, String login, String contrasena) {
        super(nombre, email, login, contrasena);
        this.learningPathsCreados = new LinkedList<>();
        this.actividadesCreadas = new LinkedList<>();
    }

    public List<LearningPath> getLearningPathsCreados() {
        return this.learningPathsCreados;
    }

    public LearningPath getLearningPathbyNombre(String titulo) {
        for (LearningPath learningPathActual : this.learningPathsCreados) {
            if (learningPathActual.getTitulo().equals(titulo)) { // Compara contenido de cadenas
                return learningPathActual;
            }
        }
        return null; // Devolver null si no se encuentra un LearningPath con el título dado
    }

    public List<Actividad> getActividadesCreadas() {
        return this.actividadesCreadas;
    }

    public void aniadirActividadCreada(Actividad actividadNueva) {
        if (actividadNueva != null) {
            this.actividadesCreadas.add(actividadNueva);
        }
    }

    public LearningPath crearLearningPath(String titulo, String descripcion, String nivelDificultad, int duracion, List<Actividad> actividades) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título del Learning Path no puede ser nulo o vacío.");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción del Learning Path no puede ser nula o vacía.");
        }
        if (actividades == null) {
            actividades = new LinkedList<>(); // Inicializar la lista si es null
        }
        // Crear el Learning Path
        LearningPath learningPath = new LearningPath(titulo, descripcion, nivelDificultad, duracion, actividades, this);
        this.learningPathsCreados.add(learningPath);
        return learningPath;
    }
    
}
