package persistencia;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import modelo.actividades.*;
import modelo.usuarios.*;
import modelo.preguntas.*;
import sistemabase.*;
import modelo.LearningPath;
import modelo.usuarios.Profesor;


public class PersistenciaSistema {
    
    private static final String USUARIOS_FILE = "data/usuarios.txt";
    private static final String LEARNING_PATHS_FILE = "data/learning_paths.txt";
    private static final String ACTIVIDADES_FILE = "data/actividades.txt";

    // Cargar usuarios desde el archivo
    public static Map<String, Usuario> cargarUsuarios() {
        Map<String, Usuario> usuarios = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String login = partes[0];
                    String nombre = partes[1];
                    String email = partes[2];
                    String password = partes[3];
                    String tipo = partes[4];

                    Usuario usuario = tipo.equalsIgnoreCase("Profesor")
                            ? new Profesor(nombre, email, login, password)
                            : new Estudiante(nombre, email, login, password);

                    usuarios.put(login, usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public static void guardarUsuarios(Map<String, Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USUARIOS_FILE))) {
            for (Usuario usuario : usuarios.values()) {
                String tipo = (usuario instanceof Profesor) ? "Profesor" : "Estudiante";
                writer.write(String.join(";", usuario.getLogin(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), tipo));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    

    // Cargar actividades desde el archivo
    public static void cargarActividades() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACTIVIDADES_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    String[] partes = linea.split(";");
                    if (partes.length >= 10) {
                        String codigo = partes[0];
                        String tipo = partes[1];
                        String nombre = partes[2];
                        String descripcion = partes[3];
                        String objetivo = partes[4];
                        String nivelDificultad = partes[5];
                        double duracion = Double.parseDouble(partes[6]);
    
                        // Procesar prerequisitos
                        List<Actividad> preRequisitos = new LinkedList<>();
                        if (!partes[7].isEmpty()) {
                            String[] codigos = partes[7].split(",");
                            for (String cod : codigos) {
                                Actividad prerequisito = GeneradorActividades.getActividad(cod);
                                if (prerequisito != null) {
                                    preRequisitos.add(prerequisito);
                                }
                            }
                        }
    
                        // Procesar seguimiento
                        Actividad seguimiento = GeneradorActividades.getActividad(partes[8]);
    
                        // Procesar fecha límite
                        Date fechaLimite = new SimpleDateFormat("yyyy-MM-dd").parse(partes[9]);
    
                        // Crear actividad según tipo
                        switch (tipo) {
                            case "Encuesta":
                                procesarEncuesta(partes, nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite);
                                break;
    
                            case "RecursoEducativo":
                                procesarRecursoEducativo(partes, nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite);
                                break;
    
                            case "Tarea":
                                procesarTarea(partes, nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite);
                                break;
    
                            case "Examen":
                                procesarExamen(partes, nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite);
                                break;
    
                            case "Quiz":
                                procesarQuiz(partes, nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite);
                                break;
    
                            default:
                                System.out.println("Tipo de actividad desconocido: " + tipo);
                                break;
                        }
                    } else {
                        System.out.println("Datos insuficientes en la línea: " + linea);
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar línea: " + linea + ". Detalle: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar actividades: " + e.getMessage());
        }
    }
    
    // Métodos específicos para procesar cada tipo de actividad
    private static void procesarEncuesta(String[] partes, String nombre, String descripcion, String objetivo, String nivelDificultad, 
                                         double duracion, List<Actividad> preRequisitos, Actividad seguimiento, Date fechaLimite) {
        List<PreguntaAbierta> preguntasEncuesta = GeneradorPreguntas.getPreguntasAbiertasPorCodigos(partes[10]);
        GeneradorActividades.generarEncuesta(nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite, preguntasEncuesta);

    }
    
    private static void procesarRecursoEducativo(String[] partes, String nombre, String descripcion, String objetivo, String nivelDificultad, 
                                                 double duracion, List<Actividad> preRequisitos, Actividad seguimiento, Date fechaLimite) {
        String tipoRecurso = partes[10];
        String urlRecurso = partes[11];
        String tituloRecurso = partes[12];
        String descripcionRecurso = partes[13];
        GeneradorActividades.generarRecursoEducativo(nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite, tipoRecurso, urlRecurso, tituloRecurso, descripcionRecurso);
    }
    
    private static void procesarTarea(String[] partes, String nombre, String descripcion, String objetivo, String nivelDificultad, 
                                      double duracion, List<Actividad> preRequisitos, Actividad seguimiento, Date fechaLimite) {
        String motivoEntrega = partes[10];
        GeneradorActividades.generarTarea(nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite, motivoEntrega, "Pendiente");
    }
    
    private static void procesarExamen(String[] partes, String nombre, String descripcion, String objetivo, String nivelDificultad, 
                                       double duracion, List<Actividad> preRequisitos, Actividad seguimiento, Date fechaLimite) {
        List<PreguntaAbierta> preguntasEncuesta = GeneradorPreguntas.getPreguntasAbiertasPorCodigos(partes[10]);
        GeneradorActividades.generarExamen(nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite, preguntasEncuesta);
    }
    
    private static void procesarQuiz(String[] partes, String nombre, String descripcion, String objetivo, String nivelDificultad, 
                                     double duracion, List<Actividad> preRequisitos, Actividad seguimiento, Date fechaLimite) {
        List<PreguntaCerrada> preguntasQuiz = GeneradorPreguntas.getPreguntasCerradasPorCodigos(partes[10]);
        GeneradorActividades.generarQuiz(nombre, descripcion, objetivo, nivelDificultad, duracion, preRequisitos, seguimiento, fechaLimite, preguntasQuiz);

    }
    

    public static void guardarActividades() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACTIVIDADES_FILE))) {
            for (Actividad actividad : GeneradorActividades.getActividades().values()) {
                String baseDatos = String.join(";", actividad.getID(), actividad.getClass().getSimpleName(),
                        actividad.getNombre(), actividad.getDescripcion(), actividad.getObjetivo(),
                        actividad.getNivelDificultad(), String.valueOf(actividad.getDuracionEstimada()));
    
                // Prerequisitos
                List<String> codigosPrerequisitos = new LinkedList<>();
                for (Actividad prerequisito : actividad.getPreRequisitos()) {
                    codigosPrerequisitos.add(prerequisito.getID());
                }
                baseDatos += ";" + String.join(",", codigosPrerequisitos);
    
                // Seguimiento
                baseDatos += ";" + (actividad.getSeguimiento() != null ? actividad.getSeguimiento().getID() : "NULL");
    
                // Fecha Límite
                baseDatos += ";" + new SimpleDateFormat("yyyy-MM-dd").format(actividad.getFechaLimite());
    
                // Datos específicos según el tipo de actividad
                if (actividad instanceof Encuesta) {
                    Encuesta encuesta = (Encuesta) actividad;
                    baseDatos += ";" + String.join(",", encuesta.getPreguntas().stream()
                            .map(PreguntaAbierta::getID).toArray(String[]::new));
                } else if (actividad instanceof RecursoEducativo) {
                    RecursoEducativo recurso = (RecursoEducativo) actividad;
                    baseDatos += ";" + recurso.getTipoRecurso() + ";" + recurso.getUrlRecurso() + ";"
                            + recurso.getTitulo() + ";" + recurso.getDescripcionRecurso();
                } else if (actividad instanceof Tarea) {
                    Tarea tarea = (Tarea) actividad;
                    baseDatos += ";" + tarea.getMotivoEntrega() + ";" + tarea.getEstadoEnvio();
                }
    
                writer.write(baseDatos);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar actividades: " + e.getMessage());
        }
    }
    

    // Cargar learning paths desde el archivo
    public static Map<String, LearningPath> cargarLearningPaths() {
        Map<String, LearningPath> learningPaths = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LEARNING_PATHS_FILE))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                try {
                    String[] partes = linea.split(";");
                    if (partes.length >= 6) {
                        String titulo = partes[0];
                        String descripcion = partes[1];
                        String nivelDificultad = partes[2];
                        int duracion = Integer.parseInt(partes[3]);
    
                        // Obtener actividades
                        List<Actividad> actividades = new LinkedList<>();
                        if (!partes[4].isEmpty()) {
                            String[] codigos = partes[4].split(",");
                            for (String codigo : codigos) {
                                Actividad actividad = GeneradorActividades.getActividad(codigo);
                                if (actividad != null) {
                                    actividades.add(actividad);
                                } else {
                                    System.out.println("Actividad con código " + codigo + " no encontrada.");
                                }
                            }
                        }
    
                        // Obtener profesor
                        String profesorLogin = partes[5];
                        Usuario profesorUsuario = EstadoGlobal.getUsuarios().get(profesorLogin);
                        if (!(profesorUsuario instanceof Profesor)) {
                            System.out.println("El login " + profesorLogin + " no corresponde a un profesor.");
                            continue;
                        }
                        Profesor profesor = (Profesor) profesorUsuario;
    
                        // Crear y añadir el Learning Path al mapa
                        LearningPath lp = new LearningPath(titulo, descripcion, nivelDificultad, duracion, actividades, profesor);
                        learningPaths.put(titulo, lp);
                    } else {
                        System.out.println("Datos insuficientes para cargar Learning Path: " + linea);
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar línea: " + linea + ". Detalle: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar Learning Paths: " + e.getMessage());
        }
        return learningPaths;
    }
    
    
    public static void guardarLearningPaths(Map<String, LearningPath> learningPaths) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEARNING_PATHS_FILE))) {
            for (LearningPath lp : learningPaths.values()) {
                String actividadesCodigos = lp.getActividades().stream()
                        .map(Actividad::getID)
                        .reduce((a, b) -> a + "," + b).orElse("");
    
                String linea = String.join(";",
                        lp.getTitulo(),
                        lp.getDescripcion(),
                        lp.getNivelDificultad(),
                        String.valueOf(lp.getDuracion()),
                        actividadesCodigos,
                        lp.getProfesor().getLogin() // Profesor asociado al Learning Path
                );
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar Learning Paths: " + e.getMessage());
        }
    }
    
    
    
}
