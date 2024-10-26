package modelo.usuarios;

import modelo.LearningPath;
import modelo.actividades.Actividad;

import java.util.LinkedList;
import java.util.List;

public  class Profesor extends Usuario
{
	private List<LearningPath> learningPathsCreados;	
	private List<Actividad> actividadesCreadas;
	
	public Profesor(String nombre, String email, String login, String contrasena)
	{
		super( nombre, email, login, contrasena);
		learningPathsCreados = new LinkedList<LearningPath>();
		actividadesCreadas = new LinkedList<Actividad>();
	}

	public List<LearningPath> getLearningPathsCreados()
	{
		return this.learningPathsCreados;
	}

	public List<Actividad> getActividadesCreadas()
	{
		return this.actividadesCreadas;
	}
	
	public void crearLearningPath( String titulo, String descripcion, String nivelDificultad, int duracion, List<Actividad> actividades)
	{
		LearningPath learningPath = new LearningPath( titulo,  descripcion,  nivelDificultad,  duracion, actividades, this.nombre);
		learningPathsCreados.add(learningPath);
	}
	
}