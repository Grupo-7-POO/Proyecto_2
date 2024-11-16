package sistemabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sistemabase.EstadoGlobal;
import modelo.usuarios.Usuario;
import modelo.usuarios.Profesor;
import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.usuarios.Estudiante;

class EstadoGlogalTest {

	@BeforeEach
    public void setup() {
        EstadoGlobal.getUsuarios().clear();
        EstadoGlobal.getLearningPaths().clear();

        Usuario profesor = new Profesor("profesor1", "1234", null, null);
        Usuario estudiante = new Estudiante("estudiante1", "abcd", null, null);

        EstadoGlobal.getUsuarios().put("profesor1", profesor);
        EstadoGlobal.getUsuarios().put("estudiante1", estudiante);
    }

    @Test
    public void testValidarUsuario() {
        assertTrue(EstadoGlobal.validarUsuario("profesor1"));
        assertTrue(EstadoGlobal.validarUsuario("estudiante1"));
        assertFalse(EstadoGlobal.validarUsuario("nonexistentUser"));
    }

    @Test
    public void testValidarLogin() {
        assertTrue(EstadoGlobal.validarLogin("profesor1", "1234"));
        assertTrue(EstadoGlobal.validarLogin("estudiante1", "abcd"));
        assertFalse(EstadoGlobal.validarLogin("profesor1", "wrongPass"));
        assertFalse(EstadoGlobal.validarLogin("nonexistentUser", "1234"));
    }

    @Test
    public void testCrearLearningPath() {
        Profesor profesor = (Profesor) EstadoGlobal.getUsuarios().get("profesor1");
        List<Actividad> actividades = new ArrayList<>();

        LearningPath lp = profesor.crearLearningPath(
            "Título Test", "Descripción Test", "Intermedio", 120, actividades
        );

        EstadoGlobal.getLearningPaths().put(lp.getTitulo() + "_" + lp.getVersion(), lp);

        assertNotNull(EstadoGlobal.getLearningPaths().get("Título Test_1"));
        assertEquals("Título Test", lp.getTitulo());
        assertEquals("Intermedio", lp.getNivelDificultad());
    }

    @Test
    public void testEditarLearningPath() {
        Profesor profesor = (Profesor) EstadoGlobal.getUsuarios().get("profesor1");
        LearningPath lp = profesor.crearLearningPath(
            "Título Original", "Descripción Original", "Principiante", 100, new ArrayList<>()
        );
        EstadoGlobal.getLearningPaths().put(lp.getTitulo() + "_" + lp.getVersion(), lp);
        lp.setTitulo("Nuevo Título");
        lp.actualizacionRealizada();

        assertEquals("Nuevo Título", lp.getTitulo());
    }

    @Test
    public void testCargarSistemaGlobal() {
        EstadoGlobal.cargarSistemaGlobal();

        assertFalse(EstadoGlobal.getUsuarios().isEmpty());
        assertFalse(EstadoGlobal.getLearningPaths().isEmpty());
    }

    @AfterEach
    public void cleanup() {
        EstadoGlobal.getUsuarios().clear();
        EstadoGlobal.getLearningPaths().clear();
    }


}