package modelo.usuarios;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import modelo.LearningPath;
import modelo.Reseña;
import modelo.actividades.Actividad;

public class Estudiante extends Usuario {
    
    private LearningPath lPInscrito;
    private List<LearningPath> lPCompletados;
    private Map<LearningPath, Actividad> actividadesCompletadas;
    private double progreso;
    private List<Reseña> reseñasCreadas;

    public Estudiante(String nombre, String email, String login, String contrasena) {
        super(nombre, email, login, contrasena);
        this.lPInscrito = null;
        this.lPCompletados = new LinkedList<LearningPath>();
        this.actividadesCompletadas = new HashMap<LearningPath, Actividad>();
        this.progreso = 0;
        this.reseñasCreadas = new LinkedList<Reseña>();
    }

    public LearningPath getLearningPathInscrito() {
        return lPInscrito;
    }

    public List<LearningPath> getLearningPathsCompletados() {
        return lPCompletados;
    }

    public Map<LearningPath, Actividad> getActividadesCompletadas() {
        return actividadesCompletadas;
    }

    public double getProgreso() {
        return progreso;
    }

    public List<Reseña> getReseñasCreadas() {
        return reseñasCreadas;
    }

    public void inscribirLearningPath(LearningPath LP) {
        this.lPInscrito = LP;
        this.progreso = 0;
    }

    public String terminarLearningPath() {
        if (this.progreso == 100) {
            this.lPCompletados.add(lPInscrito);
            this.lPInscrito = null;
            this.progreso = 0;
            return "Learning Path completado";
        } else {
            return "No se ha completado el Learning Path actual";
        }
    }

    public String crearReseña(String comentario, double rating, Actividad actividad) {
        if (this.actividadesCompletadas.containsValue(actividad)) {
            String login = getLogin();
            Reseña nuevaReseña = new Reseña(comentario, rating, login);
            this.reseñasCreadas.add(nuevaReseña);
            return "Reseña creada con éxito";
        } else {
            return "No se ha completado la actividad";
        }
    }
}
