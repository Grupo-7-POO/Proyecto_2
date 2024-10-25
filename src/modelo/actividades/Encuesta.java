package modelo.actividades;

import java.util.List;
import modelo.preguntas.PreguntaAbierta;


public class Encuesta extends Actividad
{
	private List<PreguntaAbierta> preguntas;
		
	private boolean Completado;
	
	public Encuesta()
	{
		super();
	}
	
}