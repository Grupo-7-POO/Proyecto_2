package modelo.actividades;

import java.util.List;
import java.util.Date;

public abstract class Actividad
{
	private String nombre;
	
	private String descripcion;
	
	private String objetivo;
	
	private String nivelDificultad;
	
	private double duracionEstimada;
	
	private List<Actividad> preRequisitos;
	
	private Actividad seguimiento;
	
	private Date fechaLimite;
	
	private String resultado;
	
	private String id;
	
	
	public Actividad(String nombre, String descripcion, String obejtivo, 
					String nivelDificultad, double duracionEstimada, List<Actividad> preRequisitos,  
					Actividad seguimiento, Date fechaLimite,String id)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetivo = obejtivo;
		this.nivelDificultad = nivelDificultad;
		this.duracionEstimada = duracionEstimada;
		this.preRequisitos = preRequisitos;
		this.seguimiento = seguimiento;
		this.fechaLimite = fechaLimite;
		this.resultado = null;
		this.id = id;
	}
	// SETTERS
	public String getNombre()
	{
		return this.nombre;
	}

	public String getDescripcion()
	{
		return this.descripcion;
	}

	public String getObjetivo()
	{
		return this.objetivo;
	}

	public String getNivelDificultad()
	{
		return this.nivelDificultad;
	}

	public double getDuracionEstimada()
	{
		return this.duracionEstimada;
	}

	public List<Actividad> getPreRequisitos()
	{
		return this.preRequisitos;
	}

	public Actividad getSeguimiento()
	{
		return this.seguimiento;
	}

	public Date getFechaLimite()
	{
		return this.fechaLimite;
	}

	public String getResultado()
	{
		return this.resultado;
	}

	public String getID()
	{
		return this.id;
	}
}