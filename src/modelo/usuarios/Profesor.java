package modelo.usuarios;

import modelo.LearningPath;
import modelo.Reseña;
import modelo.actividades.Actividad;
import java.util.List;



public  class Profesor extends Usuario
{
	private List<LearningPath> learningPathsCreados;
	
	private List<Reseña> reseñasRecibidas;
	
	private double rating;
	
	public Profesor(String nombre, String email, String login, String contrasena)
	{
		super( nombre, email, login, contrasena);
	}
	
	public LearningPath crearLearningPath()
	{
		return LearningPath;
	}
	
	
	
}