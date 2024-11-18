package sistemabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.actividades.RecursoEducativo;
import persistencia.CentralPersistencia;
import persistencia.IPersistenciaLearningPaths;
import modelo.preguntas.Opcion;
import modelo.preguntas.PreguntaCerrada;
import modelo.preguntas.PreguntaAbierta;
import modelo.actividades.Quiz;
import modelo.actividades.Tarea;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;

class LearningPathTest {

	private LearningPath learningPath;
    private String testFilePath;

    @BeforeEach
    public void setup() {
        // opciones para preguntas cerradas
        List<Opcion> opcionesPregunta1 = new ArrayList<>();
        opcionesPregunta1.add(new Opcion("París", true));
        opcionesPregunta1.add(new Opcion("Londres", false));

        // preguntas cerradas para Quiz
        List<PreguntaCerrada> preguntasQuiz = new ArrayList<>();
        preguntasQuiz.add(new PreguntaCerrada("¿Cuál es la capital de Francia?", "Explicación 1", "Q1", opcionesPregunta1));

        // preguntas abiertas para Encuesta y Examen
        List<PreguntaAbierta> preguntasAbiertas = new ArrayList<>();
        preguntasAbiertas.add(new PreguntaAbierta("¿Qué opinas del curso?", "Explicación 1", "PA1"));
        preguntasAbiertas.add(new PreguntaAbierta("¿Qué mejorarías en el contenido?", "Explicación 2", "PA2"));

        List<Actividad> actividades = new ArrayList<>();
        actividades.add(new RecursoEducativo(
                "Recurso 1", 
                "Descripción recurso 1", 
                "Objetivo recurso 1", 
                "Principiante", 
                45.0, 
                new LinkedList<>(), 
                null, 
                new Date(), 
                "REC1", 
                "Video", 
                "http://example.com/video1", 
                "Título Video 1", 
                "Descripción del Video 1"
        ));
        actividades.add(new Tarea(
                "Tarea 1", 
                "Descripción tarea 1", 
                "Objetivo tarea 1", 
                "Intermedio", 
                60.0, 
                new LinkedList<>(), 
                null, 
                new Date(), 
                "TAR1", 
                "Proyecto final", 
                "No entregado"
        ));
        actividades.add(new Quiz(
                "Quiz 1", 
                "Descripción quiz 1", 
                "Objetivo quiz 1", 
                "Avanzado", 
                30.0, 
                new LinkedList<>(), 
                null, 
                new Date(), 
                "QUIZ1", 
                preguntasQuiz
        ));
        actividades.add(new Encuesta(
                "Encuesta 1", 
                "Descripción encuesta 1", 
                "Objetivo encuesta 1", 
                "Intermedio", 
                30.0, 
                new LinkedList<>(), 
                null, 
                new Date(), 
                "ENC1", 
                preguntasAbiertas
        ));
		actividades.add(new Examen(
                "Examen 1", 
                "Descripción examen 1", 
                "Objetivo examen 1", 
                "Avanzado", 
                90.0, 
                new LinkedList<>(), 
                null, 
                new Date(), 
                "EX1", 
                preguntasAbiertas
        ));

        learningPath = new LearningPath(
                "Título Test", 
                "Descripción Test", 
                "Principiante", 
                165, 
                actividades, 
                "profesor1"
        );

        testFilePath = "learningPath_test.json";
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Título Test", learningPath.getTitulo());
        assertEquals(165, learningPath.getDuracion());
        assertEquals(4, learningPath.getActividades().size());

        Actividad actividad4 = learningPath.getActividades().get(3);
        assertTrue(actividad4 instanceof Encuesta);
        Encuesta encuesta = (Encuesta) actividad4;

        assertEquals("Encuesta 1", encuesta.getNombre());
        assertEquals(2, encuesta.getPreguntas().size());
        assertEquals("¿Qué opinas del curso?", encuesta.getPreguntas().get(0).getEnunciado());

		Actividad actividad5 = learningPath.getActividades().get(4);
        assertTrue(actividad5 instanceof Examen);
        Examen examen = (Examen) actividad5;

        assertEquals("Examen 1", examen.getNombre());
        assertEquals(2, examen.getPreguntas().size());
        assertEquals("¿Qué opinas del curso?", examen.getPreguntas().get(0).getEnunciado());
    }

    @Test
    public void testModificarEstadoCompletadoEncuesta() {
        Encuesta encuesta = (Encuesta) learningPath.getActividades().get(3);
        assertFalse(encuesta.isEstadoCompletado());

        encuesta.setEstadoCompletado(true);
        assertTrue(encuesta.isEstadoCompletado());
    }

    @Test
    public void testEstadoCorrectoDePreguntaAbierta() {
        Encuesta encuesta = (Encuesta) learningPath.getActividades().get(3);
        PreguntaAbierta pregunta = encuesta.getPreguntas().get(0);

        assertFalse(pregunta.getEsCorrecta());

        pregunta.setEsCorrecta(true);
        assertTrue(pregunta.getEsCorrecta());
    }

    @Test
    public void testModificarEstadoDeTarea() {
        Tarea tarea = (Tarea) learningPath.getActividades().get(1);
        assertEquals("No entregado", tarea.getEstadoEnvio());

        tarea.setEstadoEnvio("Entregado");
        assertEquals("Entregado", tarea.getEstadoEnvio());

        tarea.setEstadoEnvio("Exitoso");
        assertEquals("Exitoso", tarea.getEstadoEnvio());
    }

    @Test
    public void testSeleccionarOpcionCorrectaEnQuiz() {
        Quiz quiz = (Quiz) learningPath.getActividades().get(2);
        PreguntaCerrada pregunta = quiz.getPreguntas().get(0);

        assertTrue(pregunta.seleccionarOpcion(0)); // Opción correcta
        assertFalse(pregunta.seleccionarOpcion(1)); // Opción incorrecta
    }
	
	@Test
    public void testModificarEstadoCalificacionExamen() {
        Examen examen = (Examen) learningPath.getActividades().get(4);
        assertEquals("", examen.getEstadoCalificacion());

        examen.setEstadoCalificacion("Aprobado");
        assertEquals("Aprobado", examen.getEstadoCalificacion());

        examen.setEstadoCalificacion("Reprobado");
        assertEquals("Reprobado", examen.getEstadoCalificacion());
    }

    @Test
    public void testAgregarYModificarPreguntasExamen() {
        Examen examen = (Examen) learningPath.getActividades().get(4);

        // Agregar una nueva pregunta abierta
        PreguntaAbierta nuevaPregunta = new PreguntaAbierta("¿Cómo aplicarías lo aprendido?", "Explicación 3", "PA3");
        examen.getPreguntas().add(nuevaPregunta);
        assertEquals(3, examen.getPreguntas().size());
        assertEquals("¿Cómo aplicarías lo aprendido?", examen.getPreguntas().get(2).getEnunciado());
    }


    @Test
    public void testPersistenciaSalvarYCargarLearningPath() throws Exception {
        IPersistenciaLearningPaths persistencia = CentralPersistencia.getPersistenciaLearningPaths(CentralPersistencia.JSON);

        persistencia.salvarLearningPath(testFilePath, learningPath);
        File file = new File(testFilePath);
        assertTrue(file.exists());

        LearningPath loadedLearningPath = new LearningPath(
                null, null, null, 0, new ArrayList<>(), null
        );
        persistencia.cargarLearningPath(testFilePath, loadedLearningPath);

        assertEquals(learningPath.getTitulo(), loadedLearningPath.getTitulo());
        assertEquals(learningPath.getDuracion(), loadedLearningPath.getDuracion());
        assertEquals(learningPath.getActividades().size(), loadedLearningPath.getActividades().size());

		Examen examenCargado = (Examen) loadedLearningPath.getActividades().get(4);
        assertEquals(2, examenCargado.getPreguntas().size());
        assertEquals("¿Qué opinas del curso?", examenCargado.getPreguntas().get(0).getEnunciado());
    }

    @AfterEach
    public void cleanup() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
