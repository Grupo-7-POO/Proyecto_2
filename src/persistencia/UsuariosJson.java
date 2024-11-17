package persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

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

public class UsuariosJson implements IPersistenciaUsuarios {

    @Override
    public void cargarUsuarios(String archivo, LearningPath learningPath) throws IOException, JSONException, InformacionInconsistenteException {
        // Implementation for cargarUsuarios with LearningPath parameter
    }

    @Override
    public void salvarUsuarios(String archivo, LearningPath learningPath) throws IOException {
        // Implementation for salvarUsuarios with LearningPath parameter
    }

    @Override
    public void salvarUsuarios(String archivo, List<Usuario> usuarios) throws IOException {
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

        jUsuarios.put("usuarios", jArrayUsuarios);
        Files.write(new File(archivo).toPath(), jUsuarios.toString().getBytes());
    }

    private JSONArray salvarLearningPaths(List<LearningPath> learningPaths) {
        JSONArray jLearningPaths = new JSONArray();
        for (LearningPath learningPath : learningPaths) {
            JSONObject jLearningPath = new JSONObject();
            jLearningPath.put("id", learningPath.getId());
            jLearningPath.put("titulo", learningPath.getTitulo());
            jLearningPath.put("descripcion", learningPath.getDescripcion());
            jLearningPath.put("nivelDificultad", learningPath.getNivelDificultad());
            jLearningPath.put("duracion", learningPath.getDuracion());
            jLearningPaths.put(jLearningPath);
        }
        return jLearningPaths;
    }

    @Override
    public void cargarUsuarios(String archivo, List<Usuario> usuarios) throws IOException, JSONException, InformacionInconsistenteException {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);
        JSONArray jArrayUsuarios = raiz.getJSONArray("usuarios");

        for (int i = 0; i < jArrayUsuarios.length(); i++) {
            JSONObject jUsuario = jArrayUsuarios.getJSONObject(i);
            String login = jUsuario.getString("login");
            String nombre = jUsuario.getString("nombre");
            String password = jUsuario.getString("password");
            String tipo = jUsuario.getString("tipo");

            Usuario usuario;
            if (tipo.equals("Estudiante")) {
                usuario = new Estudiante(login, nombre, password);
                ((Estudiante) usuario).setLearningPaths(cargarLearningPaths(jUsuario.getJSONArray("learningPaths")));
            } else if (tipo.equals("Profesor")) {
                usuario = new Profesor(login, nombre, password);
                ((Profesor) usuario).setLearningPaths(cargarLearningPaths(jUsuario.getJSONArray("learningPaths")));
            } else {
                throw new InformacionInconsistenteException("tipo", tipo);
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

            LearningPath learningPath = new LearningPath(id, titulo, descripcion, nivelDificultad, duracion);
            learningPaths.add(learningPath);
        }
        return learningPaths;
    }
}