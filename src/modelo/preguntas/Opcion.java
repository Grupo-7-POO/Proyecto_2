package modelo.preguntas;

public class Opcion
{
	private String texto;
	
	private boolean esCorrecta;
	
	
	public Opcion( String texto)
	{
		this.texto = null;
		this.esCorrecta = false;
	}

	// GETTERS

	public String getTexto()
	{
		return this.texto;
	}

	public boolean getEsCorrecta()
	{
		return this.esCorrecta;
	}

	// SETTERS
	
	public void setEsCorrecta()
	{
		this.esCorrecta = true;
	}
	
}