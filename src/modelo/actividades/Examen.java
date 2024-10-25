package modelo.actividades;

import java.util.List;
import modelo.preguntas.PreguntaAbierta;


public class Examen extends Actividad
{
	private List<PreguntaAbierta> preguntas;
		
	private String estadoCalificacion;
	
	public Examen()
	{
		super();
	}
	
}