package modelo;

import java.util.List;

import java.util.Date;
import java.util.LinkedList;

import modelo.usuarios.Estudiante;
import modelo.actividades.Actividad;
import modelo.usuarios.Profesor;


public class LearningPath
{
	
	private String titulo;
	
	private String descripcion;
	
	private String nivelDificultad;
	
	private int duracion;
	
	private double rating;
	
	private Date fechaCreacion;
	
	private Date fechaModificacion;
	
	private Double version;
	
	private List<Actividad> actividades;
	
	private List<Estudiante> estudiantesCursando;
	
	private List<Estudiante> estudiantesCompletado;

	private Profesor profesor;
	
	private String loginProfesor;
	
	public LearningPath( String titulo, String descripcion, String nivelDificultad, int duracion, 
						List<Actividad> actividades, Profesor profesor )
	{
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.duracion = duracion;
		this.rating = 0;
		this.fechaCreacion = new Date();
		this.fechaModificacion = new Date();
		this.version = 1.0;
		this.actividades = actividades;
		this.estudiantesCursando = new LinkedList<Estudiante>();
		this.estudiantesCompletado = new LinkedList<Estudiante>();
		this.profesor = profesor;
	}
	// GETTERS
	public String getTitulo()
	{
		return titulo;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public String getNivelDificultad()
	{
		return nivelDificultad;
	}
	
	public double getDuracion()
	{
		return duracion;
	}
	
	public double getRating()
	{
		return rating;
	}
	
	public Date getFechaCreacion()
	{
		return fechaCreacion;
	}
	
	public Date getFechaModificacion()
	{
		return fechaModificacion;
	}
	
	public Double getVersion()
	{
		return version;
	}
	
	public List<Actividad> getActividades()
	{
		return actividades;
	}
	
	public List<Estudiante> getEstudiantesCursando()
	{
		return estudiantesCursando;
	}
	
	public List<Estudiante> getEstudiantesGraduados()
	{
		return estudiantesCompletado;
	}
	
	public Profesor getProfesor() { 
        return profesor;
    }

	// SETTERS

	public void setTitulo( String titulo)
	{
		this.titulo = titulo;
	}

	public void setProfesor( String profesor)
	{
		this.loginProfesor = profesor;
	}
	
	public void setDescripcion( String descripcion)
	{
		this.descripcion = descripcion;
	}

	public void setNivelDificultad( String nivelDificultad)
	{
		this.nivelDificultad = nivelDificultad;
	}

	public void setDuracion( int duracion)
	{
		this.duracion = duracion;
	}

	public void actualizarRating()
	{
		double suma = 0;
		for (int i = 0; i < this.actividades.size(); i++)
		{
			Actividad actividad = this.actividades.get(i);
			suma = suma + actividad.getRating();
		}
		this.rating = suma/this.actividades.size();
	}

	public void actualizacionRealizada()
	{
		Double nuevaVersion = this.version + 0.1;
		this.version = nuevaVersion;
	}

}
