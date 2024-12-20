package modelo.actividades;

import java.util.List;
import java.util.LinkedList;
import java.util.Date;
import modelo.Resenia;

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

	private List<Resenia> resenias;

	private double rating;

	private String titulo;
	
	
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
		this.resenias = new LinkedList<Resenia>();
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

	public List<Resenia> getResenias()
	{
		return this.resenias;
	}

	public double getRating()
	{
		return this.rating;
	
	
	}

	public String getTitulo() {

        return titulo;

    }


	// SETTERS

	public void setNombre( String nuevoNombre)
	{
		this.nombre = nuevoNombre;
	}

	public void setDescripcion( String nuevaDescripcion )
	{
		this.descripcion = nuevaDescripcion;
	}

	public void setObjetivo( String nuevoObejtivo )
	{
		this.objetivo = nuevoObejtivo;
	}

	public void setNivelDificultad( String nuevoObejtivo )
	{
		this.objetivo = nuevoObejtivo;
	}

	public void setDuracionEstimada( double nuevaDuracion )
	{
		this.duracionEstimada = nuevaDuracion;
	}

	public void aniadirActividadesPrerequisitos ( Actividad nuevaActividad )
	{
		this.preRequisitos.add(nuevaActividad);
	}

	public void eliminarActividadPrerequisitos ( Actividad actividad )
	{
		this.preRequisitos.remove(actividad);
	}

	public void eliminarPrerequisitos ( )
	{
		this.preRequisitos = new LinkedList<Actividad>();
	}

	public void setActividadSeguimiento ( Actividad nuevaActividad )
	{
		this.seguimiento = nuevaActividad;
	}

	public void eliminarActividadSeguimiento ()
	{
		this.seguimiento = null;
	}

	public void setFechaLimite( Date fechaNueva)
	{
		this.fechaLimite = fechaNueva;
	}

	public void setResultado( String resultado)
	{
		this.resultado = resultado;
	}

}