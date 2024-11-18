package modelo.usuarios;

import java.util.LinkedList;
import java.util.List;
import modelo.LearningPath;
import modelo.Reseña;
import modelo.actividades.Actividad;

public class Estudiante extends Usuario {
    
    private LearningPath lPInscrito;
    private Actividad actividadActual;
    private List<LearningPath> lPCompletados;
    private List<Actividad> actividadesCompletadas;
    private double progreso;
    private List<Reseña> reseñasCreadas;

    public Estudiante(String nombre, String email, String login, String contrasena) {
        super(nombre, email, login, contrasena);
        this.lPInscrito = null;
        this.actividadActual = null;
        this.lPCompletados = new LinkedList<LearningPath>();
        this.actividadesCompletadas = new LinkedList<Actividad>();
        this.progreso = 0;
        this.reseñasCreadas = new LinkedList<Reseña>();
    }

    public LearningPath getLearningPathInscrito() {
        return lPInscrito;
    }

    public Actividad getActividadActual() {
        return actividadActual;
    }

    public void setActividadActual( Actividad actividad ){
        this.actividadActual = actividad;
    }

    public List<LearningPath> getLearningPathsCompletados() {
        return lPCompletados;
    }

    public List<Actividad> getActividadesCompletadas() {
        return actividadesCompletadas;
    }

    public double getProgreso() {

        LearningPath lPActual = getLearningPathInscrito();
        List<Actividad> actividadesRealizadas = getActividadesCompletadas();
        int conteo = 0;
        double progresoCalculado;

        if ( lPActual != null )
        {
            List<Actividad> actividadesLP = lPActual.getActividades();
            for ( Actividad actividad : actividadesLP )
            {
                if ( actividadesRealizadas.contains(actividad) == true ) { conteo ++;}
            }

            progresoCalculado = conteo/actividadesLP.size();
        }
        else { progresoCalculado = 0; }
        this.progreso = progresoCalculado;
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

    public String crearReseña(String comentario, double rating, LearningPath learningPath )  
    {
        if ( this.lPCompletados.contains(learningPath) ) 
        {
            String login = getLogin();
            Reseña nuevaReseña = new Reseña(comentario, rating, login);
            this.reseñasCreadas.add(nuevaReseña);
            return "Reseña creada con éxito";
        } else {
            return "No se ha completado la actividad";
        }
    }
}
