package modelo.preguntas;

public class PreguntaAbierta extends Pregunta
{
	private boolean esCorrecta;
	
	
	public PreguntaAbierta(String enunciado, String explicacion, String id)
	{
		super(enunciado, explicacion, id);
		this.esCorrecta = false;
	}

	public boolean getEsCorrecta()
	{
		return this.esCorrecta;
	}

	public void setEsCorrecta( boolean estado)
	{
		this.esCorrecta = estado;
	}
	
}