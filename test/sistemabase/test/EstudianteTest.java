package sistemabase.test;
import modelo.usuarios.Estudiante;
import modelo.LearningPath;
import modelo.Reseña;
import modelo.actividades.Actividad;
import modelo.actividades.RecursoEducativo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;


class EstudianteTest {

	private Estudiante estudiante;
    private LearningPath learningPath;
    private Actividad actividad1;
    private Actividad actividad2;

    @BeforeEach
    public void setup() {
        estudiante = new Estudiante("Juan Pérez", "juan@example.com", "juan123", "password123");
        actividad1 = new RecursoEducativo(
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
        );

        actividad2 = new RecursoEducativo(
                "Recurso 2",
                "Descripción recurso 2",
                "Objetivo recurso 2",
                "Intermedio",
                60.0,
                new LinkedList<>(),
                null,
                new Date(),
                "REC2",
                "Video",
                "http://example.com/video2",
                "Título Video 2",
                "Descripción del Video 2"
        );

        List<Actividad> actividades = new ArrayList<>();
        actividades.add(actividad1);
        actividades.add(actividad2);
        learningPath = new LearningPath(
                "Learning Path Test",
                "Descripción del Learning Path",
                "Intermedio",
                120,
                actividades,
                "profesor1"
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Juan Pérez", estudiante.getNombre());
        assertEquals("juan@example.com", estudiante.getEmail());
        assertNull(estudiante.getLearningPathInscrito());
        assertEquals(0, estudiante.getLearningPathsCompletados().size());
        assertEquals(0, estudiante.getActividadesCompletadas().size());
        assertEquals(0, estudiante.getProgreso());
        assertEquals(0, estudiante.getReseñasCreadas().size());
    }

    @Test
    public void testInscribirLearningPath() {
        estudiante.inscribirLearningPath(learningPath);
        // Validar inscripción
        assertEquals(learningPath, estudiante.getLearningPathInscrito());
        assertEquals(0, estudiante.getProgreso());
    }

    @Test
    public void testTerminarLearningPathCompletado() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getLearningPathInscrito(); // Progreso ficticio
        estudiante.getLearningPathInscrito(); // Se insiste.

        // Completar el LearningPath
        String resultado = estudiante.terminarLearningPath();
        assertEquals("Learning Path completado", resultado);
        assertTrue(estudiante.getLearningPathsCompletados().contains(learningPath));
        assertNull(estudiante.getLearningPathInscrito());
    }

    @Test
    public void testTerminarLearningPathNoCompletado() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getLearningPathInscrito();
        String resultado = estudiante.terminarLearningPath();
        assertEquals("No se ha completado el Learning Path actual", resultado);
        assertNull(estudiante.getLearningPathInscrito());
    }

    @Test
    public void testCrearReseñaParaActividadCompletada() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getActividadesCompletadas().put(learningPath, actividad1);

        // Crear una reseña para la actividad completada
        String resultado = estudiante.crearReseña("Excelente actividad", 5.0, actividad1);
        assertEquals("Reseña creada con éxito", resultado);
        assertEquals(1, estudiante.getReseñasCreadas().size());

        Reseña reseña = estudiante.getReseñasCreadas().get(0);
        assertEquals("Excelente actividad", reseña.getComentario());
        assertEquals(5.0, reseña.getRating());
        assertEquals("juan123", reseña.getCreador());
    }

    @Test
    public void testCrearReseñaParaActividadNoCompletada() {
        estudiante.inscribirLearningPath(learningPath);
        // Intentar crear una reseña para una actividad no completada
        String resultado = estudiante.crearReseña("Actividad interesante", 4.0, actividad2);
        // Validar que la reseña no se creó
        assertEquals("No se ha completado la actividad", resultado);
        assertEquals(0, estudiante.getReseñasCreadas().size());
    }

    @Test
    public void testEditarReseña() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getActividadesCompletadas().put(learningPath, actividad1);

        // Crear una reseña para la actividad completada
        estudiante.crearReseña("Actividad excelente", 5.0, actividad1);

        // Editar la reseña creada
        Reseña reseña = estudiante.getReseñasCreadas().get(0);
        reseña.editarComentario("Comentario actualizado");
        reseña.editarRating(4.5);

        // Validar que los cambios se reflejan correctamente
        assertEquals("Comentario actualizado", reseña.getComentario());
        assertEquals(4.5, reseña.getRating());
    }

    @Test
    public void testReseñasMúltiples() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getActividadesCompletadas().put(learningPath, actividad1);
        estudiante.getActividadesCompletadas().put(learningPath, actividad2);

        // Crear reseñas para ambas actividades
        estudiante.crearReseña("Primera reseña", 4.0, actividad1);
        estudiante.crearReseña("Segunda reseña", 5.0, actividad2);
        assertEquals(2, estudiante.getReseñasCreadas().size());

        Reseña reseña1 = estudiante.getReseñasCreadas().get(0);
        Reseña reseña2 = estudiante.getReseñasCreadas().get(1);

        assertEquals("Primera reseña", reseña1.getComentario());
        assertEquals(4.0, reseña1.getRating());
        assertEquals("Segunda reseña", reseña2.getComentario());
        assertEquals(5.0, reseña2.getRating());
    }

}
