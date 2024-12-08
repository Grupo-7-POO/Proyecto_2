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

	public LearningPath getLearningPathbyNombre( String titulo )
	{
		LearningPath match = null;
		for ( int i = 0; i < this.learningPathsCreados.size(); i++ )
		{
			LearningPath learningPathActual = this.learningPathsCreados.get(i);
			if ( learningPathActual.getTitulo() == titulo ) { match =  learningPathActual; }
		}
		return match;
	}

	public List<Actividad> getActividadesCreadas()
	{
		return this.actividadesCreadas;
	}

	public void aniadirActividadCreada(Actividad actividadNueva)
	{
		this.actividadesCreadas.add(actividadNueva);
	}
	
	public LearningPath crearLearningPath( String titulo, String descripcion, String nivelDificultad, int duracion, List<Actividad> actividades)
	{
		LearningPath learningPath = new LearningPath( titulo,  descripcion,  nivelDificultad,  duracion, actividades, this.nombre);
		learningPathsCreados.add(learningPath);
		return learningPath;
	} 
}