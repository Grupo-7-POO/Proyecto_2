package modelo.preguntas;

import java.util.List;

public class PreguntaCerrada extends Pregunta
{
	private List<Opcion> opciones;
	private boolean esCorrecta;
	
	public PreguntaCerrada(String enunciado, String explicacion, String id, List<Opcion> opciones)
	{
		super(enunciado, explicacion, id);
	}

	public List<Opcion> getOpciones()
	{
		return this.opciones;
	}

	public boolean seleccionarOpcion( int i)
	{
		Opcion respuesta = this.opciones.get(i);
		return respuesta.getEsCorrecta();
	}
	public void setEsCorrecta(boolean esCorrecta) {

        this.esCorrecta = esCorrecta;

    }x
}