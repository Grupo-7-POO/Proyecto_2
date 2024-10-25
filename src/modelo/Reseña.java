package modelo;

import java.util.Date;

import modelo.usuarios.Estudiante;

public class Reseña
{
	private String comentario;
	
	private double rating;
	
	private Date fechaReseña;
	
	private Estudiante creador;
	
	public Reseña(String comentario, double rating, Estudiante creador)
	{
		this.comentario = comentario;
		this.rating = rating;
		this.creador = creador;
		this.fechaReseña = new Date();
	}

	public String getComentario()
	{
		return comentario;
	}

	public Date getFechaReseña()
	{
		return fechaReseña;
	}

	public double getRating()
	{
		return rating;
	}

	public Estudiante getCreador()
	{
		return creador;
	}

	public void editarComentario( String nuevoComentario)
	{
		this.comentario = nuevoComentario;
	}

	public void editarRating( double nuevoRating)
	{
		this.rating = nuevoRating;
	}
	
}