package modelo.actividades;

import java.util.List;


public abstract class Actividad
{
	private String nombre;
	
	private String descripcion;
	
	private String objetivo;
	
	private String nivelDificultad;
	
	private String duracionEstimada;
	
	private List<Actividad> preRequisitos;
	
	private Actividad seguimiento;
	
	private String fechaLimite;
	
	private String resultado;
	
	private String id;
	
	
	public Actividad()
	{

	}
	
}