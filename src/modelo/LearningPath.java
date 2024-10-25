package modelo;

import java.io.BufferedReader;
import java.util.List;

import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;

public class LearningPath{
	
	private String titulo;
	
	private String descripcion;
	
	private String nivelDificultad;
	
	private int duracion;
	
	private double rating;
	
	private String fechaCreacion;
	
	private String fechaModificacion;
	
	private String version;
	
	private List<Actividad> actividades;
	
	private List<Estudiante> estudiantesCursando;
	
	private List<Estudiante> estudiantesCompletado;
	
	private Profesor profesor;
	
	public LearningPath( )
	{
		
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
	
	public int getDuracion()
	{
		return duracion;
	}
	
	public double getRating()
	{
		return rating;
	}
	
	public String getFechaCreacion()
	{
		return fechaCreacion;
	}
	
	public String getFechaModificacion()
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
	
	public Profesor getProfesor()
	{
		return profesor;
	}
	
		
}
