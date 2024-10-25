package persistencia;

import java.io.IOException;

import org.json.JSONException;

import exceptions.LearningPathDuplicadoException;
import exceptions.InformacionInconsistenteException;
import modelo.LearningPath;


public interface IPersistenciaLearningPaths
{
	/**
	 * Carga la información de todos los elementos de una aerolínea, excepto los clientes y tiquetes , y actualiza la estructura de objetos que se encuentra dentro de la aerolínea
		Parameters: 
		archivo - La ruta al archivo que contiene la información que se va a cargar
		aerolinea - La aerolínea dentro de la cual debe almacenarse la información
		Throws:
		java.io.IOException - Se lanza esta excepción si hay problemas leyendo el archivo
		InformacionInconsistenteException - Se lanza esta excepción si hay información inconsistente dentro del archivo, o entre el archivo y el estado de la aerolínea
	 * @throws AeropuertoDuplicadoException 
	 * @throws JSONException 
	 */
	public void cargarLearningPath(String archivo, LearningPath LearningPath) throws IOException, InformacionInconsistenteException, JSONException, LearningPathDuplicadoException;
	/**
	 * Salva en un archivo la información de todos los elementos de una aerolínea, excepto los clientes y tiquetes
		Parameters:
		archivo - La ruta al archivo donde debe quedar almacenada la información
		aerolinea - La aerolínea que tiene la información que se quiere almacenar
		Throws:
		java.io.IOException - Se lanza esta excepción si hay problemas escribiendo el archivo
	 */
	public void salvarLEarningPath(String archivo, LearningPath LearningPath) throws IOException;
}