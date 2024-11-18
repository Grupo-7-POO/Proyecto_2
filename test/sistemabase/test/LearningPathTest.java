package sistemabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

class LearningPathTest {

	private LearningPath learningPath;
    private String testFilePath;

    @BeforeEach
    public void setup() {
        // Crear actividad (RecursoEducativo)
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
        actividades.add(new RecursoEducativo(
                "Recurso 2", 
                "Descripción recurso 2", 
                "Objetivo recurso 2", 
                "Intermedio", 
                60.0, 
                new LinkedList<>(), 
                null, 
                new Date(), 
                "REC2", 
                "PDF", 
                "http://example.com/pdf1", 
                "Título PDF 1", 
                "Descripción del PDF 1"
        ));

        learningPath = new LearningPath(
                "Título Test", 
                "Descripción Test", 
                "Principiante", 
                105, 
                actividades, 
                "profesor1"
        );

        testFilePath = "learningPath_test.json";
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Título Test", learningPath.getTitulo());
        assertEquals("Descripción Test", learningPath.getDescripcion());
        assertEquals("Principiante", learningPath.getNivelDificultad());
        assertEquals(105, learningPath.getDuracion());
        assertEquals(2, learningPath.getActividades().size());
        assertEquals("profesor1", learningPath.getProfesor());

        Actividad actividad1 = learningPath.getActividades().get(0);
        assertTrue(actividad1 instanceof RecursoEducativo);
        RecursoEducativo recurso1 = (RecursoEducativo) actividad1;

        assertEquals("Recurso 1", recurso1.getNombre());
        assertEquals("Descripción recurso 1", recurso1.getDescripcion());
        assertEquals("Objetivo recurso 1", recurso1.getObjetivo());
        assertEquals("Principiante", recurso1.getNivelDificultad());
        assertEquals("Video", recurso1.getTipoRecurso());
        assertEquals("http://example.com/video1", recurso1.getUrlRecurso());
    }

    @Test
    public void testActualizarRating() {
        
        RecursoEducativo recurso1 = (RecursoEducativo) learningPath.getActividades().get(0);
        RecursoEducativo recurso2 = (RecursoEducativo) learningPath.getActividades().get(1);

        recurso1.setCompletado(true);
        recurso2.setCompletado(true);

        // Establecer ratings en las actividades
        recurso1.setDuracionEstimada(4.5);
        recurso2.setDuracionEstimada(3.8);

        learningPath.actualizarRating();
        assertEquals(4.15, learningPath.getRating(), 0.01);
    }

    @Test
    public void testActualizacionRealizada() {
        assertEquals(1.0, learningPath.getVersion());
        learningPath.actualizacionRealizada();
        assertEquals(1.1, learningPath.getVersion());
    }

    @Test
    public void testSetters() {
        learningPath.setTitulo("Nuevo Título");
        learningPath.setDescripcion("Nueva Descripción");
        learningPath.setNivelDificultad("Avanzado");
        learningPath.setDuracion(120);

        assertEquals("Nuevo Título", learningPath.getTitulo());
        assertEquals("Nueva Descripción", learningPath.getDescripcion());
        assertEquals("Avanzado", learningPath.getNivelDificultad());
        assertEquals(120, learningPath.getDuracion());
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
        assertEquals(learningPath.getDescripcion(), loadedLearningPath.getDescripcion());
        assertEquals(learningPath.getNivelDificultad(), loadedLearningPath.getNivelDificultad());
        assertEquals(learningPath.getDuracion(), loadedLearningPath.getDuracion());
        assertEquals(learningPath.getProfesor(), loadedLearningPath.getProfesor());
        assertEquals(learningPath.getActividades().size(), loadedLearningPath.getActividades().size());
    }

    @AfterEach
    public void cleanup() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
