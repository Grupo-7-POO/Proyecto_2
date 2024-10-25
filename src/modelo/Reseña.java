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
	
	
}