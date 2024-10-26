package sistemabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.preguntas.Opcion;
import modelo.preguntas.Pregunta;
import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;


public class GeneradorPreguntas
{
    private static Map<String, Pregunta> preguntas = new HashMap<String, Pregunta>( );	

    public static String generarCodigoPregunta()
    {
        int numero = ( int ) ( Math.random( ) * 10e10 );
        String codigo = "" + numero;
        while( preguntas.containsKey( codigo ) )
        {
            numero = ( int ) ( Math.random( ) * 10e10 );
            codigo = "" + numero;
        }

        while( codigo.length( ) < 10 )
            codigo = "0" + codigo;

        return codigo;

    }

    public static PreguntaAbierta generarPreguntaAbierta(String enunciado, String explicacion)
    {
        String codigo = generarCodigoPregunta();
        PreguntaAbierta preguntaAbierta =  new PreguntaAbierta(enunciado, explicacion, codigo);
        preguntas.put(codigo, preguntaAbierta);
        return preguntaAbierta;
    }

    public static PreguntaCerrada generarPreguntaCerrada(String enunciado, String explicacion, List<Opcion> opciones)
    {
        String codigo = generarCodigoPregunta();
        PreguntaCerrada preguntaCerrada = new PreguntaCerrada(enunciado, explicacion, codigo, opciones);
        preguntas.put(codigo, preguntaCerrada);
        return preguntaCerrada;
    }

    public static Pregunta getPreguntaID ( String id)
    {
        return preguntas.get(id);
    } 
	
}