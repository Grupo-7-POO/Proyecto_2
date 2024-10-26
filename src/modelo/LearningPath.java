package modelo;

import java.util.List;
import java.util.Date;
import java.util.LinkedList;

import modelo.usuarios.Estudiante;
import modelo.actividades.Actividad;

public class LearningPath{
	
	private String titulo;
	
	private String descripcion;
	
	private String nivelDificultad;
	
	private int duracion;
	
	private double rating;
	
	private Date fechaCreacion;
	
	private Date fechaModificacion;
	
	private String version;
	
	private List<Actividad> actividades;
	
	private List<Estudiante> estudiantesCursando;
	
	private List<Estudiante> estudiantesCompletado;
	
	private String loginProfesor;
	
	public LearningPath( String titulo, String descripcion, String nivelDificultad, int duracion, 
						List<Actividad> actividades, String profesor )
	{
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.duracion = duracion;
		this.rating = 0;
		this.fechaCreacion = new Date();
		this.fechaModificacion = new Date();
		this.version = "1.0";
		this.actividades = actividades;
		this.estudiantesCursando = new LinkedList<Estudiante>();
		this.estudiantesCompletado = new LinkedList<Estudiante>();
		this.loginProfesor = profesor;
	}
	
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
	
	public String getVersion()
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
	
	public String getProfesor()
	{
		return loginProfesor;
	}
	
}
