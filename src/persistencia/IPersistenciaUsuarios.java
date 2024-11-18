package persistencia;

import java.io.IOException;
import java.util.List;

import exceptions.InformacionInconsistenteException;
import modelo.LearningPath;
import modelo.usuarios.Usuario;

/**
 * Esta interfaz define las operaciones relacionadas con la persistencia de los tiquetes de la aerolíena (salvar y cargar)
 */
public interface IPersistenciaUsuarios
{
    /**
     * Carga la información de los clientes y tiquetes vendidos por la aerolínea, y actualiza la estructura de objetos que se encuentra dentro de la aerolínea
     * @param archivo La ruta al archivo que contiene la información que se va a cargar
     * @param aerolinea La aerolínea dentro de la cual debe almacenarse la información
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo
     * @throws InformacionInconsistenteException Se lanza esta excepción si hay información inconsistente dentro del archivo, o entre el archivo y el estado de la aerolínea
     */
    public void cargarUsuarios( String archivo, LearningPath learningPath ) throws IOException, InformacionInconsistenteException;

    /**
     * Salva en un archivo toda la información sobre los clientes y los tiquetes vendidos por la aerolínea 
     * @param archivo La ruta al archivo donde debe quedar almacenada la información
     * @param aerolinea La aerolínea que tiene la información que se quiere almacenar
     * @throws IOException Se lanza esta excepción si hay problemas escribiendo el archivo
     */
    public void salvarUsuarios( String archivo,  LearningPath learningPath ) throws IOException;

}
