package sistemabase;

import java.util.*;
import modelo.preguntas.Opcion;
import modelo.preguntas.Pregunta;
import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;

public class GeneradorPreguntas {
    private static final int LONGITUD_CODIGO = 10;
    private static Map<String, Pregunta> preguntas = new HashMap<>();	

    // Método para generar un código único
    public static String generarCodigoPregunta() {
        String codigo;
        do {
            codigo = UUID.randomUUID().toString().replaceAll("-", "").substring(0, LONGITUD_CODIGO);
        } while (preguntas.containsKey(codigo));
        return codigo;
    }

    // Generar pregunta abierta
    public static PreguntaAbierta generarPreguntaAbierta(String enunciado, String explicacion) {
        if (enunciado == null || enunciado.isEmpty()) {
            throw new IllegalArgumentException("El enunciado no puede ser nulo o vacío.");
        }
        if (explicacion == null || explicacion.isEmpty()) {
            throw new IllegalArgumentException("La explicación no puede ser nula o vacía.");
        }

        String codigo = generarCodigoPregunta();
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta(enunciado, explicacion, codigo);
        preguntas.put(codigo, preguntaAbierta);
        return preguntaAbierta;
    }

    // Generar pregunta cerrada
    public static PreguntaCerrada generarPreguntaCerrada(String enunciado, String explicacion, List<Opcion> opciones) {
        if (enunciado == null || enunciado.isEmpty()) {
            throw new IllegalArgumentException("El enunciado no puede ser nulo o vacío.");
        }
        if (explicacion == null || explicacion.isEmpty()) {
            throw new IllegalArgumentException("La explicación no puede ser nula o vacía.");
        }
        if (opciones == null || opciones.size() < 2) {
            throw new IllegalArgumentException("Debe haber al menos 2 opciones para una pregunta cerrada.");
        }

        String codigo = generarCodigoPregunta();
        PreguntaCerrada preguntaCerrada = new PreguntaCerrada(enunciado, explicacion, codigo, opciones);
        preguntas.put(codigo, preguntaCerrada);
        return preguntaCerrada;
    }

    // Obtener una pregunta por ID
    public static Pregunta getPreguntaID(String id) {
        if (!preguntas.containsKey(id)) {
            System.out.println("La pregunta con ID " + id + " no existe.");
            return null;
        }
        return preguntas.get(id);
    }
    
    // Obtener una lista de preguntas abiertas por una lista de códigos
    public static List<PreguntaAbierta> getPreguntasAbiertasPorCodigos(String codigos) {
        List<PreguntaAbierta> preguntasAbiertas = new LinkedList<>();

        if (codigos == null || codigos.isEmpty()) {
            return preguntasAbiertas; // Devuelve una lista vacía si no hay códigos
        }

        String[] ids = codigos.split(",");
        for (String id : ids) {
            Pregunta pregunta = preguntas.get(id.trim());
            if (pregunta instanceof PreguntaAbierta) {
                preguntasAbiertas.add((PreguntaAbierta) pregunta);
            } else if (pregunta != null) {
                System.out.println("El código " + id + " no corresponde a una pregunta abierta.");
            } else {
                System.out.println("La pregunta con ID " + id + " no existe.");
            }
        }

        return preguntasAbiertas;
    }

    // Obtener una lista de preguntas cerradas por una lista de códigos
    public static List<PreguntaCerrada> getPreguntasCerradasPorCodigos(String codigos) {
        List<PreguntaCerrada> preguntasCerradas = new LinkedList<>();

        if (codigos == null || codigos.isEmpty()) {
            return preguntasCerradas; // Devuelve una lista vacía si no hay códigos
        }

        String[] ids = codigos.split(",");
        for (String id : ids) {
            Pregunta pregunta = preguntas.get(id.trim());
            if (pregunta instanceof PreguntaCerrada) {
                preguntasCerradas.add((PreguntaCerrada) pregunta);
            } else if (pregunta != null) {
                System.out.println("El código " + id + " no corresponde a una pregunta cerrada.");
            } else {
                System.out.println("La pregunta con ID " + id + " no existe.");
            }
        }

        return preguntasCerradas;
    }


    // Método para obtener todas las preguntas (opcional)
    public static Collection<Pregunta> obtenerTodasLasPreguntas() {
        return preguntas.values();
    }
}
