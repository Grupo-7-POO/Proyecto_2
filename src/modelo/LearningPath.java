package modelo;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import exceptions.InformacionInconsistenteException;
import persistencia.CentralPersistencia;
import persistencia.IPersistenciaLearningPaths;
import persistencia.IPersistenciaUsuarios;
import modelo.actividades.Actividad;
import exceptions.LearningPathDuplicadoException;
import exceptions.InformacionInconsistenteUserException;
import exceptions.TipoInvalidoException;
import exceptions.UserRepetidoException;

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
	

	 /**
     * Salva la información de la aerlínea en un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas escribiendo en el archivo
	 * @throws persistencia.TipoInvalidoException 
     */
	    public void cargarLearningPath( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException, JSONException, LearningPathDuplicadoException, persistencia.TipoInvalidoException
    {
    	IPersistenciaLearningPaths cargador = CentralPersistencia.getPersistenciaLearningPaths(tipoArchivo);
    	cargador.cargarLearningPath(archivo, this);
        // TODO implementar
    }

    /**
     * Salva la información de la aerlínea en un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas escribiendo en el archivo
     * @throws persistencia.TipoInvalidoException 
     */
    public void salvarLearningPath( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, persistencia.TipoInvalidoException
    {
    	IPersistenciaLearningPaths cargador = CentralPersistencia.getPersistenciaLearningPaths(tipoArchivo);
    	cargador.salvarLearningPath(archivo, this);
        // TODO implementar
    }

    /**
     * Carga toda la información de sobre los clientes y tiquetes de una aerolínea a partir de un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas leyendo el archivo
     * @throws InformacionInconsistenteException Lanza esta excepción si durante la carga del archivo se encuentra información que no es consistente con la información de la
     *         aerolínea
     * @throws persistencia.TipoInvalidoException 
     */
    public void cargarUsuarios( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException, persistencia.TipoInvalidoException
    {
        IPersistenciaUsuarios cargador = CentralPersistencia.getPersistenciaUsuarios( tipoArchivo );
        cargador.cargarUsuarios( archivo, this );
    }

    /**
     * Salva la información de la aerlínea en un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas escribiendo en el archivo
     * @throws persistencia.TipoInvalidoException 
     */
    public void salvarUsuarios( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, persistencia.TipoInvalidoException
    {
        IPersistenciaUsuarios cargador = CentralPersistencia.getPersistenciaUsuarios( tipoArchivo );
        cargador.salvarUsuarios( archivo, this );
    }


}
