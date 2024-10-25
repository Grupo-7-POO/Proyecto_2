package persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modelo.LearningPath;
import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;

public class PersistenciaLearningPathsJson implements IPersistenciaLearningPaths
{
	private static final String CAPACIDAD_AVION = "capacidad"; // Lista de aviones
	private static final String NOMBRE_AVION = "capacidad";
	
    private static final String CODIGO_RUTA = "codigoRuta"; //Mapa de rutas
    private static final String DESTINO = "destino";
    private static final String HORA_LLEGADA = "horaLlegada";
    private static final String HORA_SALIDA = "horaSalida";
    private static final String ORIGEN = "origen";
    
    private static final String AVION = "avion"; // lista de vuelos
    private static final String FECHA = "fecha";
    private static final String RUTA = "ruta";
    
    private static final String CODIGO_AEROPUERTO = "codigoAeropuerto"; // Aeropuerto
    private static final String LATITUD = "latitud";
    private static final String LONGITUD = "longitud";
    private static final String NOMBRE_AEROPUERTO = "nombreAeropuerto";
    private static final String NOMBRE_CIUDAD = "nombre_ciudad";


	@Override
	public void cargarLearningPath(String archivo, LearningPath LearningPath)
			throws IOException, InformacionInconsistenteException, JSONException, LearningPathDuplicadoException {
		// TODO Auto-generated method stub
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo).toPath()));
		JSONObject raiz = new JSONObject ( jsonCompleto );
		
		JSONArray aviones = raiz.getJSONArray("aviones");
		for( int i = 0; i < aviones.length(); i++ )
		{
			JSONObject avion = aviones.getJSONObject(i);
			cargarAvion( avion );
		}
		
		JSONArray rutas = raiz.getJSONArray("rutas");
		for( int i = 0; i < rutas.length(); i++ )
		{
			JSONObject ruta = rutas.getJSONObject(i);
			cargarVuelo( ruta );
		}
		
		JSONArray vuelos = raiz.getJSONArray("vuelos");
		for( int i = 0; i < vuelos.length(); i++ )
		{
			JSONObject vuelo = vuelos.getJSONObject(i);
			cargarVuelo( vuelo );
		}
		
	}
	
	private Vuelo cargarVuelo(JSONObject vuelo) throws JSONException, AeropuertoDuplicadoException
	{
		JSONObject avion = vuelo.getJSONObject(AVION);
		Avion nuevoAvion = cargarAvion(avion);
		JSONObject ruta = vuelo.getJSONObject(RUTA);
    	Ruta nuevaRuta = cargarRuta(ruta);
		String fecha = vuelo.getString(FECHA);
		
		Vuelo nuevoVuelo = new Vuelo(nuevaRuta, fecha, nuevoAvion);
		
		return nuevoVuelo;
	}
	
	private Ruta cargarRuta(JSONObject ruta) throws JSONException, AeropuertoDuplicadoException
	{
		String codigoRuta = ruta.getString(CODIGO_RUTA);
		String horaLlegada = ruta.getString(HORA_LLEGADA);
		String horaSalida = ruta.getString(HORA_SALIDA);
		
		JSONObject origen = ruta.getJSONObject(ORIGEN);
		Aeropuerto nuevoOrigen = new Aeropuerto(origen.getString(NOMBRE_AEROPUERTO), origen.getString(CODIGO_AEROPUERTO), origen.getString(NOMBRE_CIUDAD), origen.getDouble(LATITUD), origen.getDouble(LONGITUD));

		JSONObject destino = ruta.getJSONObject(DESTINO);
		Aeropuerto nuevoDestino = new Aeropuerto(destino.getString(NOMBRE_AEROPUERTO), destino.getString(CODIGO_AEROPUERTO), destino.getString(NOMBRE_CIUDAD), destino.getDouble(LATITUD), destino.getDouble(LONGITUD));

		Ruta nuevaRuta = new Ruta(nuevoOrigen, nuevoDestino, horaSalida, horaLlegada, codigoRuta);
		return nuevaRuta;
	}
	
	private Avion cargarAvion(JSONObject avion)
	{
		int capacidad = avion.getInt(CAPACIDAD_AVION);
		String nombre = avion.getString(NOMBRE_AVION);
		Avion nuevoAvion = new Avion(nombre, capacidad);
		
		return nuevoAvion;
	}

	@Override
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
		// TODO Auto-generated method stub
		JSONObject jobject = new JSONObject( );

		// Salvar vuelos
		JSONArray jVuelos = new JSONArray( );
		for( Vuelo vuelo : aerolinea.getVuelos() )
		{
			jVuelos.put(salvarVuelo(vuelo));
		}
		jobject.put("vuelos", jVuelos);
		
		// Salvar rutas
		JSONArray jRutas = new JSONArray( );
		for( Ruta ruta : aerolinea.getRutas() )
		{
			jRutas.put(salvarRuta(ruta));
		}
		jobject.put("rutas", jRutas);

		// Salvar aviones
		JSONArray jAviones = new JSONArray();
		for( Avion avion : aerolinea.getAviones() )
		{
			jAviones.put(salvarAvion(avion));
		}
		jobject.put("aviones", jAviones);

        // Escribir la estructura JSON en un archivo
        PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
	
	}
	
	private JSONObject salvarVuelo(Vuelo vuelo)
	{
		JSONObject jVuelo = new JSONObject( );
		JSONObject nuevoAvion = salvarAvion(vuelo.getAvion());
		jVuelo.put(AVION, nuevoAvion);
		jVuelo.put(FECHA, vuelo.getFecha());
		JSONObject nuevaRuta = salvarRuta(vuelo.getRuta());
		jVuelo.put(RUTA, nuevaRuta);
		
		return jVuelo;
	}
	
	
	private JSONObject salvarAeropuerto(Aeropuerto aeropuerto)
	{
		JSONObject jAeropuerto = new JSONObject( );
		jAeropuerto.put(CODIGO_AEROPUERTO, aeropuerto.getCodigo());
		jAeropuerto.put(LATITUD, aeropuerto.getLatitud());
		jAeropuerto.put(LONGITUD, aeropuerto.getLongitud());
		jAeropuerto.put(NOMBRE_AEROPUERTO, aeropuerto.getNombre());
		jAeropuerto.put(NOMBRE_CIUDAD, aeropuerto.getNombreCiudad());
		
		return jAeropuerto;
	}
	
	private JSONObject salvarAvion(Avion avion)
	{

		JSONObject jAvion = new JSONObject( );
		jAvion.put(CAPACIDAD_AVION, avion.getCapacidad());
		jAvion.put(NOMBRE_AVION, avion.getNombre());
		
		return jAvion;
	}
	
	private JSONObject salvarRuta(Ruta ruta)
	{
		JSONObject jRuta = new JSONObject( );
		jRuta.put(CODIGO_RUTA, ruta.getCodigoRuta());
		jRuta.put(HORA_LLEGADA, ruta.getHoraLlegada());
		jRuta.put(HORA_SALIDA, ruta.getHoraSalida());
		
		JSONObject origen = salvarAeropuerto(ruta.getOrigen());
		JSONObject destino = salvarAeropuerto(ruta.getDestino());
		jRuta.put(ORIGEN, origen);
		jRuta.put(DESTINO, destino);
		
		return jRuta;
	}
}