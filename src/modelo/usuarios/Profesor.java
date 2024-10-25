package modelo.usuarios;

import modelo.LearningPath;
import modelo.actividades.Actividad;

import java.util.LinkedList;
import java.util.List;



public  class Profesor extends Usuario
{
	private List<LearningPath> learningPathsCreados;	
	
	public Profesor(String nombre, String email, String login, String contrasena)
	{
		super( nombre, email, login, contrasena);
		learningPathsCreados = new LinkedList<LearningPath>();
	}

	public List<LearningPath> getLearningPathsCreados()
	{
		return this.learningPathsCreados;
	}
	
	public void crearLearningPath()
	{
		LearningPath learningPath = new LearningPath();
		learningPathsCreados.add(learningPath);
	}
	
}