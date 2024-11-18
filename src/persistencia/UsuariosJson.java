package persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import exceptions.InformacionInconsistenteException;
import exceptions.LearningPathDuplicadoException;
import exceptions.InformacionInconsistenteUserException;
import exceptions.TipoInvalidoException;
import exceptions.UserRepetidoException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modelo.LearningPath;
import modelo.Reseña;
import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import modelo.usuarios.Usuario;
import modelo.preguntas.Opcion;
import modelo.preguntas.Pregunta;
import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;
import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Quiz;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;

import sistemabase.EstadoGlobal;
import sistemabase.GeneradorActividades;
import sistemabase.GeneradorPreguntas;

import java.util.ArrayList;

public class UsuariosJson implements IPersistenciaUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void salvarUsuarios(String archivo, LearningPath learningPath) throws IOException {
        JSONObject jUsuarios = new JSONObject();
        JSONArray jArrayUsuarios = new JSONArray();

        for (Usuario usuario : usuarios) {
            JSONObject jUsuario = new JSONObject();
            jUsuario.put("login", usuario.getLogin());
            jUsuario.put("nombre", usuario.getNombre());
            jUsuario.put("password", usuario.getPassword());
            jUsuario.put("tipo", usuario.getClass().getSimpleName());

            if (usuario instanceof Estudiante) {
                Estudiante estudiante = (Estudiante) usuario;
                jUsuario.put("learningPaths", salvarLearningPaths(estudiante.getLearningPaths()));
            } else if (usuario instanceof Profesor) {
                Profesor profesor = (Profesor) usuario;
                jUsuario.put("learningPaths", salvarLearningPaths(profesor.getLearningPaths()));
            }

                jArrayUsuarios.put(jUsuario);
            }
    }

    private JSONArray salvarLearningPaths(List<LearningPath> learningPaths) {
        JSONArray jLearningPaths = new JSONArray();
        for (LearningPath learningPath : learningPaths) {
            JSONObject jLearningPath = new JSONObject();
            jLearningPath.put("id", learningPath.getTitulo());
            jLearningPath.put("titulo", learningPath.getTitulo());
            jLearningPath.put("descripcion", learningPath.getDescripcion());
            jLearningPath.put("nivelDificultad", learningPath.getNivelDificultad());
            jLearningPath.put("duracion", learningPath.getDuracion());
            jLearningPaths.put(jLearningPath);
        }
        return jLearningPaths;
    }

    @Override
    public void cargarUsuarios(String archivo,LearningPath learningPath) throws IOException, JSONException, InformacionInconsistenteException {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);
        JSONArray jArrayUsuarios = raiz.getJSONArray("usuarios");

        for (int i = 0; i < jArrayUsuarios.length(); i++) {
            JSONObject jUsuario = jArrayUsuarios.getJSONObject(i);
            String login = jUsuario.getString("login");
            String nombre = jUsuario.getString("nombre");
            String tipo = jUsuario.getString("tipo");
            String email = jUsuario.getString("email");
            String contrasena = jUsuario.getString("contrasena");
            Usuario usuario;
            if (tipo.equals("Estudiante")) {
                usuario = new Estudiante(nombre, email, login, contrasena);
                ((Estudiante) usuario).setLearningPaths(cargarLearningPaths(jUsuario.getJSONArray("learningPaths")));
            } else if (tipo.equals("Profesor")) {
                usuario = new Profesor(nombre, email, login, contrasena);
                ((Profesor) usuario).setLearningPaths(cargarLearningPaths(jUsuario.getJSONArray("learningPaths")));
            } else {
                throw new InformacionInconsistenteException("Tipo de usuario inconsistente: " + tipo);
            }

            usuarios.add(usuario);
        }
    }

    private List<LearningPath> cargarLearningPaths(JSONArray jLearningPaths) throws JSONException {
        List<LearningPath> learningPaths = new LinkedList<>();
        for (int i = 0; i < jLearningPaths.length(); i++) {
            JSONObject jLearningPath = jLearningPaths.getJSONObject(i);
            String id = jLearningPath.getString("id");
            String titulo = jLearningPath.getString("titulo");
            String descripcion = jLearningPath.getString("descripcion");
            String nivelDificultad = jLearningPath.getString("nivelDificultad");
            int duracion = jLearningPath.getInt("duracion");
            List<Actividad> actividades = new LinkedList<>();
            String loginProfesor = jLearningPath.getString("loginProfesor");
            

            LearningPath learningPath = new LearningPath(titulo, descripcion, nivelDificultad, duracion, 
            actividades,loginProfesor);

            learningPaths.add(learningPath);
        }
        return learningPaths;
    }
}