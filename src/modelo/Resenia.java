package modelo;

import java.util.Date;

public class Resenia
{
	private String comentario;
	
	private double rating;
	
	private Date fechaResenia;
	
	private String creador;
	
	public Resenia(String comentario, double rating, String creador)
	{
		this.comentario = comentario;
		this.rating = rating;
		this.creador = creador;
		this.fechaResenia = new Date();
	}

	
	public String getComentario()
	{
		return comentario;
	}

	public Date getFechaResenia()
	{
		return fechaResenia;
	}

	public double getRating()
	{
		return rating;
	}

	public String getCreador()
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