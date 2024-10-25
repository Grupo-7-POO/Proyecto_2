package modelo.usuarios;

import java.util.List;
import java.util.Map;

import modelo.LearningPath;
import modelo.Reseña;
import modelo.actividades.Actividad;

public  class Estudiante extends Usuario
{
	private LearningPath lPInscrito;
	
	private List<LearningPath> lPCompletados;
	
	private List<Actividad> actividadesCompletadas;
	
	private Map<LearningPath, Double> progreso;
	
	private List<Reseña> reseñasCreadas;
	
	public Estudiante(String nombre, String email, String login, String contrasena)
	{
		super( nombre, email, login, contrasena);
	}
	
}