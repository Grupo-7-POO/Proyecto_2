package sistemabase.test;

import modelo.usuarios.Estudiante;
import modelo.LearningPath;
import modelo.Resenia;
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
    public void testCrearReseniaParaLearningPathCompletado() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getActividadesCompletadas().addAll(learningPath.getActividades());
        estudiante.terminarLearningPath();

        String resultado = estudiante.crearResenia("Excelente aprendizaje", 5.0, learningPath);
        assertEquals("Resenia creada con éxito", resultado);
        assertEquals(1, estudiante.getReseniasCreadas().size());

        Resenia resenia = estudiante.getReseniasCreadas().get(0);
        assertEquals("Excelente aprendizaje", resenia.getComentario());
        assertEquals(5.0, resenia.getRating());
        assertEquals("juan123", resenia.getCreador());
        assertNotNull(resenia.getFechaResenia()); // Verifica que la fecha no sea nula
    }

    @Test
    public void testCrearReseniaParaLearningPathNoCompletado() {
        estudiante.inscribirLearningPath(learningPath);

        String resultado = estudiante.crearResenia("Buen contenido", 4.0, learningPath);
        assertEquals("No se ha completado el Learning Path", resultado);
        assertEquals(0, estudiante.getReseniasCreadas().size());
    }

    @Test
    public void testEditarResenia() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getActividadesCompletadas().addAll(learningPath.getActividades());
        estudiante.terminarLearningPath();

        // Crear una resenia
        String resultado = estudiante.crearResenia("Excelente contenido", 5.0, learningPath);
        assertEquals("Resenia creada con éxito", resultado);
        assertEquals(1, estudiante.getReseniasCreadas().size());

        Resenia resenia = estudiante.getReseniasCreadas().get(0);
        assertNotNull(resenia, "La resenia no debe ser nula");

        // Editar la resenia
        resenia.editarComentario("Comentario actualizado");
        resenia.editarRating(4.5);

        // Verificar los cambios
        assertEquals("Comentario actualizado", resenia.getComentario());
        assertEquals(4.5, resenia.getRating());
        assertNotNull(resenia.getFechaResenia()); // La fecha debe ser válida
    }


    @Test
    public void testReseniasMultiples() {
        estudiante.inscribirLearningPath(learningPath);
        estudiante.getActividadesCompletadas().addAll(learningPath.getActividades());
        estudiante.terminarLearningPath();

        estudiante.crearResenia("Primera resenia", 4.0, learningPath);
        estudiante.crearResenia("Segunda resenia", 5.0, learningPath);

        assertEquals(2, estudiante.getReseniasCreadas().size());

        Resenia resenia1 = estudiante.getReseniasCreadas().get(0);
        Resenia resenia2 = estudiante.getReseniasCreadas().get(1);

        assertEquals("Primera resenia", resenia1.getComentario());
        assertEquals(4.0, resenia1.getRating());
        assertEquals("Segunda resenia", resenia2.getComentario());
        assertEquals(5.0, resenia2.getRating());

        assertNotNull(resenia1.getFechaResenia());
        assertNotNull(resenia2.getFechaResenia());
    }
}
