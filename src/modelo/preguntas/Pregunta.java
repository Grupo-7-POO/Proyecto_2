package modelo.preguntas;

public abstract class Pregunta
{
	private String enunciado;
	
	private String explicacion;
	
	private String id;
	
	public Pregunta(String enunciado, String explicacion, String id)
	{
		this.enunciado = enunciado;
		this.explicacion = explicacion;
		this.id = id;
	}

	public String getEnunciado()
	{
		return this.enunciado;
	}

	public String getExplicacion()
	{
		return this.explicacion;
	}

	public String getID()
	{
		return this.id;
	}
	
}