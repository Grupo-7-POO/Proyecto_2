package modelo.actividades;

import java.util.List;
import java.util.LinkedList;
import java.util.Date;
import modelo.Reseña;

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

	private List<Reseña> reseñas;

	private double rating;
	
	
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
		this.reseñas = new LinkedList<Reseña>();
		this.rating = 0;
	}
	// GETTERS
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

	public List<Reseña> getReseñas()
	{
		return this.reseñas;
	}

	public double getRating()
	{
		return this.rating;
	}
	// SETTERS

}