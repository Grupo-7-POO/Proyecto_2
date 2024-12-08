package modelo.usuarios;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import modelo.LearningPath;
import modelo.Resenia;
import modelo.actividades.Actividad;

public class Estudiante extends Usuario {

    private LearningPath learningPathInscrito;
    private Actividad actividadActual;
    private List<LearningPath> learningPathsCompletados;
    private List<Actividad> actividadesCompletadas;
    private double progreso;
    private List<Resenia> reseniasCreadas;

    public Estudiante(String nombre, String email, String login, String contrasena) {
        super(nombre, email, login, contrasena);
        this.learningPathInscrito = null;
        this.actividadActual = null;
        this.learningPathsCompletados = new LinkedList<>();
        this.actividadesCompletadas = new LinkedList<>();
        this.progreso = 0;
        this.reseniasCreadas = new LinkedList<>();
    }

    public LearningPath getLearningPathInscrito() {
        return learningPathInscrito;
    }

    public Actividad getActividadActual() {
        return actividadActual;
    }

    public void setActividadActual(Actividad actividad) {
        this.actividadActual = actividad;
    }

    public List<LearningPath> getLearningPathsCompletados() {
        return learningPathsCompletados;
    }

    public List<Actividad> getActividadesCompletadas() {
        return actividadesCompletadas;
    }

    public double getProgreso() {
        if (learningPathInscrito != null) {
            List<Actividad> actividadesLP = learningPathInscrito.getActividades();
            long conteo = actividadesLP.stream()
                                       .filter(actividadesCompletadas::contains)
                                       .count();
            this.progreso = (double) conteo / actividadesLP.size();
        } else {
            this.progreso = 0;
        }
        return progreso;
    }

    public List<Resenia> getReseniasCreadas() {
        return reseniasCreadas;
    }

    public void inscribirLearningPath(LearningPath LP) {
        if (this.learningPathInscrito != null) {
            throw new IllegalStateException("Ya estás inscrito en un Learning Path.");
        }
        this.learningPathInscrito = LP;
        this.progreso = 0;
    }

    public String terminarLearningPath() {
        if (this.progreso == 1.0) {
            this.learningPathsCompletados.add(learningPathInscrito);
            this.learningPathInscrito = null;
            this.progreso = 0;
            return "Learning Path completado";
        } else {
            return "No se ha completado el Learning Path actual";
        }
    }

    public String crearResenia(String comentario, double rating, LearningPath learningPath) {
        if (this.learningPathsCompletados.contains(learningPath)) {
            String login = getLogin();
            Resenia nuevaResenia = new Resenia(comentario, rating, login);
            this.reseniasCreadas.add(nuevaResenia);
            return "Reseña creada con éxito";
        } else {
            return "No se ha completado el Learning Path";
        }
    }
}
