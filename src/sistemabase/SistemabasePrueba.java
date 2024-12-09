
package sistemabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import modelo.LearningPath;
import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Quiz;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;
import modelo.usuarios.Estudiante;
import modelo.usuarios.Profesor;
import modelo.usuarios.Usuario;
import modelo.preguntas.Opcion;
import modelo.preguntas.PreguntaAbierta;
import modelo.preguntas.PreguntaCerrada;


public class SistemabasePrueba 
{
    private static Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
    private static Map<String, LearningPath> learningPaths = new HashMap<String, LearningPath>();
    private static Scanner escaner = new Scanner(System.in);

    public static Map<String, Usuario> getUsuarios()
    {
        return usuarios;
    }

    public static Map<String, LearningPath> getLearningPaths()
    {
        return learningPaths;
    }

    public static boolean validarUsuario( String login)
    {
        if ( usuarios.containsKey(login) ) { return true; }
        else{ return false;}
    }

    public static boolean validarLogin( String login, String password)
    {

        if ( validarUsuario(login) )
        {
            Usuario usuarioactual = usuarios.get(login);
            if (usuarioactual.getPassword() == password){ return true; }
            else { return false;}
        }
        else { return false;}
    }

    // MAIN DE LA APLICACION

    public static void cargarSistemaGlobal()
    {
        // Cargar learningPaths, Usuarios, actividades y preguntas guardadas en archivos externos

    }

    public static String menuLogin()
    {
        String login;
        String password;

        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Crear Usuario");
        System.out.println("Opción: ");
            
        String opcionIn = escaner.nextLine();
        int opcion = Integer.parseInt(opcionIn);

        switch (opcion)
        {
        case 1:
                System.out.println("Ingrese su login: ");
                login = escaner.nextLine();
                System.out.print(login); 
                System.out.println("Ingrese su contrasenia:");
                password = escaner.nextLine();
                break;

        case 2: 
                String[] usuarioCreado = crearUsuarioConsola();
                login = usuarioCreado[0];
                password = usuarioCreado[1];
                break;
        default:
                System.out.println("Opción no válida");
                System.exit(0);
                return "error";
        }

        if ( validarLogin(login, password) ) 
        {
            return login;
        }
        else { return "error"; }
        
    }

    public static String[] crearUsuarioConsola()
    {
        Usuario usuarioCreado;

        System.out.println("Ingrese su Nombre:");
        String nombre = escaner.nextLine();
        System.out.println("Ingrese su email: ");
        String email = escaner.nextLine();
        System.out.println("Ingrese su login: ");
        String login = escaner.nextLine();
        if ( usuarios.containsKey(login) == true )
        {
            System.out.print("Login ya existe");
            System.exit(0);
        }
        System.out.println("Ingrese su contrasenia: ");
        String contrasena = escaner.nextLine();

        System.out.println("Selecciona una opción:");
        System.out.println("1. Usuario Profesor");
        System.out.println("2. Usuario Estudiante");

        System.out.print("Opción: ");
            
        String opcionIn = escaner.nextLine();
        int opcion = Integer.parseInt(opcionIn);

        switch (opcion)
        {
            case 1: 
                    usuarioCreado = new Profesor(nombre, email, login, contrasena);
                    usuarios.put( login, usuarioCreado );
                    break;
            case 2: 
                    usuarioCreado = new Estudiante(nombre, email, login, contrasena);
                    usuarios.put( login, usuarioCreado );
                    break;
            default:
                    System.out.println("Opción no válida");
                    System.exit(0);
                    break; // POSIBLE AniADIR EXDCEPTION DE TIPO DE USUARIO NO VALIDO
        }
        return new String[] {login, contrasena};
    }

    public static void menuProfesor( Profesor profesor)
    {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Crear Learning Path:");
        System.out.println("2. Editar Learning Path");
        System.out.println("3. Crear Actividad:");
        System.out.println("4. Editar Actividad:");
        System.out.println("5. Clonar Actividad:");
        System.out.println("6. Salir:");

        System.out.print("Opción: ");
        String opcionIn = escaner.nextLine();
        int opcion = Integer.parseInt(opcionIn);
        
        switch (opcion)
        {
        case 1: 
                crearLearningPathProfesor( profesor );
                break;
        case 2: 
                editarLearningPathProfesor( profesor );
                break;
        case 3: 
                crearActividadProfesor( profesor );
                break;	
        case 4: 
                editarActividadProfesor( profesor );
                break;
        case 5: 
                clonarActividadProfesor( profesor );
                break;
        case 6:
                System.exit(0);
                break;
        default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public static void crearLearningPathProfesor( Profesor profesor )
    {
        List<Actividad> actividades = new LinkedList<Actividad>();

        System.out.println("Escriba un titulo para el Learning Path");
        String titulo = escaner.nextLine();
        System.out.println("Escriba una descripcion para el Learning Path");
        String descripcion = escaner.nextLine();
        System.out.println("Escriba el nivel de dificultad");
        String nivelDificultad = escaner.nextLine();
        System.out.println("Escriba la duracion estimada en minutos");
        String duracionLine = escaner.nextLine();
        int duracion = Integer.parseInt(duracionLine);
        System.out.println("Escoja las actividades que haran parte del Learning Path");
        
        boolean check = true;
        while ( check )
        {
            System.out.println("Escoja el ID de las actividades, escoja 0 para detenerse.");
            System.out.println("Escoja el ID de las actividades, 8 numeros.");
            String codigo = escaner.nextLine();
            if (codigo == "0") 
            { 
                check = false;
            }
            else 
            {
                if ( GeneradorActividades.validarActividad(codigo) ) { actividades.add(GeneradorActividades.getActividad(codigo)); }
                else { System.out.println("Actividad no encontrada"); }
            }
        }
        LearningPath learningPathCreado = profesor.crearLearningPath(titulo, descripcion, nivelDificultad, duracion, actividades); // FALTA AniADIR LEARNING PATH A BASE DE DATOS GENERAL
        learningPaths.put( learningPathCreado.getTitulo() , learningPathCreado);
    }
    
    public static LearningPath escogerLearningPath( Profesor profesor)
    {
        System.out.println("Ingrese el titulo del Learning Path deseado:");
        String titulo = escaner.nextLine();

        return profesor.getLearningPathbyNombre( titulo );
    }

    public static void editarLearningPathProfesor( Profesor profesor )
    {
        LearningPath learningPath = escogerLearningPath( profesor );

        System.out.println("Selecciona una opción:");
        System.out.println("1. Editar Titulo:");
        System.out.println("2. Editar Descripcion");
        System.out.println("3. Editar Nivel de Dificultad:");
        System.out.println("4. Editar Duracion:");
        System.out.println("5. Editar Actividades:");
        System.out.println("6. Salir:");

        System.out.print("Opción: ");
        String opcionIn = escaner.nextLine();
        int opcion = Integer.parseInt(opcionIn);

        switch(opcion)
        {
        case 1: editarTituloProfesor(learningPath);
        case 2: editarDescripcionProfesor( learningPath );
        case 3: editarNivelDificultadProfesor( learningPath );
        case 4: editarDuracionProfesor( learningPath );
        case 5: editarActividadesLPProfesor( learningPath );
        case 6:
                System.exit(0);
                break;
        default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public static void editarTituloProfesor( LearningPath learningPath )
    {
        System.out.println("Escriba un nuevo titulo:");
        String titulo = escaner.nextLine();
        learningPath.setTitulo( titulo );
        learningPath.actualizacionRealizada();
    }

    public static void editarDescripcionProfesor( LearningPath learningPath )
    {
        System.out.println("Escriba una nueva descripcion:");
        String descripcion = escaner.nextLine();
        learningPath.setDescripcion( descripcion );
        learningPath.actualizacionRealizada();
    }

    public static void editarNivelDificultadProfesor( LearningPath learningPath )
    {
        System.out.println("Escoja un nivel de Dificultad:");
        System.out.println("1. Principiante");
        System.out.println("2. Intermedio");
        System.out.println("3. Avanzado");

        String nivelLine = escaner.nextLine();
        int nivel = Integer.parseInt(nivelLine);
        
        String dificultad = null;

        switch (nivel) {
            case 1: dificultad = "Principiante";
            case 2: dificultad = "Intermedio";
            case 3: dificultad = "Avanzado";
            default: System.out.println("Opcion no valida");
        }

        if ( dificultad != null)
        {
            learningPath.setNivelDificultad( dificultad );
            learningPath.actualizacionRealizada();
        }
    }

    public static void editarDuracionProfesor( LearningPath learningPath )
    {
        System.out.println("Escoja una nueva duracion en minutos:");
        String duracionLine = escaner.nextLine();
        int duracion = Integer.parseInt(duracionLine);
        learningPath.setDuracion( duracion );
        learningPath.actualizacionRealizada();
    }

    public static void editarActividadesLPProfesor( LearningPath learningPath )
    {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Aniadir Actividad:");
        System.out.println("2. Eliminar Actividad");
        System.out.print("Opción: ");
        String opcionIn = escaner.nextLine();
        int opcion = Integer.parseInt(opcionIn);

        switch(opcion)
        {
        case 1: aniadirActividadLPProfesor(learningPath);
        case 2: eliminarActividadLPProfesor( learningPath );
        default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public static void aniadirActividadLPProfesor ( LearningPath learningPath )
    {
        System.out.println("Inserte el codigo de la actividad que desea aniadir:");
        String idBuscado = escaner.nextLine();
        
        if (idBuscado.length() == 8)
        {
            Actividad actividadBuscada = GeneradorActividades.getActividad(idBuscado);
            List<Actividad> actividades = learningPath.getActividades();
    
            if ( actividades.contains(actividadBuscada) == true) { System.out.println("La actividad ya se encuentra dentro del LearningPath"); }
            else
            {
                actividades.add(actividadBuscada);
                learningPath.actualizacionRealizada();
            }
        }
        else 
        { System.out.println("Recuerde que los ID de las actividades son de 8 digitos"); }
    }

    public static void eliminarActividadLPProfesor ( LearningPath learningPath )
    {
        System.out.println("Inserte el codigo de la actividad que desea eliminar:");
        String idBuscado = escaner.nextLine();
        
        if (idBuscado.length() == 8)
        {
            Actividad actividadBuscada = GeneradorActividades.getActividad(idBuscado);
            List<Actividad> actividades = learningPath.getActividades();
    
            if ( actividades.contains(actividadBuscada) == true) 
            {
                actividades.remove(actividadBuscada);
                learningPath.actualizacionRealizada();
            }
            else
            {
                System.out.println("La actividad no se encuentra dentro del LearningPath");
            }
        }
        else 
        { System.out.println("Recuerde que los ID de las actividades son de 8 digitos"); }
    }

    public static void crearActividadProfesor( Profesor profesor )
    {
        System.out.println("Selecciona el tipo de  Actividad");
        System.out.println("1. Encuesta");
        System.out.println("2. Examen");
        System.out.println("3. Quiz");
        System.out.println("4. Recurso Educativo");
        System.out.println("5. Tarea");
        System.out.println("6. Salir:");

        System.out.print("Opción: ");
        String opcionIn = escaner.nextLine();
        int opcion = Integer.parseInt(opcionIn);
        // Campos en comun de todas las actividades
        System.out.println("Escriba el nombre de la actividad");
        String nombre = escaner.nextLine();
        System.out.println("Escriba la descripcion de la actividad");
        String descripcion = escaner.nextLine();
        System.out.println("Escriba el objetivo nombre de la actividad");
        String objetivo = escaner.nextLine();

        System.out.println("Escriba la duracion estimada de la actividad");
        String value = escaner.nextLine(); // convertir a double
        double duracionEstimada = Double.parseDouble(value);

        System.out.println("Escriba el nivel de dificultad de la actividad");
        String nivelDificultad = escaner.nextLine();

        System.out.println("Escriba la fecha limite recomendada para la actividad en formato dd-MMM-yyyy");
        String date = escaner.nextLine(); // convertir a date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Date fechaLimite = null;
        try {
            fechaLimite = formatter.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Escoja las actividades que son Pre Requisitos");

        boolean check1 = true;
        List<Actividad> actividadesPre = new LinkedList<Actividad>();
        while ( check1 )
        {
            System.out.println("Escoja el ID de las actividades, escoja 0 para detenerse.");
            System.out.println("Escoja el ID de las actividades, 8 numeros.");
            String codigo = escaner.nextLine();
            if (codigo.equals("0")) 
            { 
                check1 = false;
            }
            else 
            {
                if ( GeneradorActividades.validarActividad(codigo) ) { actividadesPre.add(GeneradorActividades.getActividad(codigo)); }
                else { System.out.println("Actividad no encontrada"); }
            }
        }
        boolean check2 = true;
        Actividad actividadSeguimiento = null;
        while ( check2 )
        {
            System.out.println("Escoja la actividad de seguimiento");
            System.out.println("Escoja el ID de las actividad, 8 numeros.");
            String codigo = escaner.nextLine();
            if ( GeneradorActividades.validarActividad(codigo) ) 
            { 
                actividadSeguimiento = GeneradorActividades.getActividad(codigo); 
                check2 = false;
            }
            else { System.out.println("Actividad no encontrada"); }
        }
        // Campos diferentes dependiendo de actividad
        switch(opcion)
        {
        case 1:
                List<PreguntaAbierta> preguntasEncuesta = generadorPreguntasAbiertas();
                Encuesta encuestaNueva = GeneradorActividades.generarEncuesta(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, preguntasEncuesta);
                profesor.aniadirActividadCreada(encuestaNueva);
                break;
        case 2: 
                List<PreguntaAbierta> preguntasExamen = generadorPreguntasAbiertas();
                Examen examenNuevo = GeneradorActividades.generarExamen(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, preguntasExamen);
                profesor.aniadirActividadCreada(examenNuevo);
                break;

        case 3: 
                List<PreguntaCerrada> preguntasQuiz = generadorPreguntasCerradas();
                Quiz quizNuevo = GeneradorActividades.generarQuiz(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, preguntasQuiz);
                profesor.aniadirActividadCreada(quizNuevo);
                break;
        case 4: 
                System.out.println("Escriba el tipo de Recurso");
                String tipoRecurso = escaner.nextLine();
                System.out.println("Escriba el URL del Recurso");
                String urlRecurso = escaner.nextLine();
                System.out.println("Escriba el titulo del Recurso");
                String titulo = escaner.nextLine();
                System.out.println("Escriba la descripcion del Recurso");
                String descripcionRecurso = escaner.nextLine();
                RecursoEducativo recursoEducativoNuevo = GeneradorActividades.generarRecursoEducativo(nombre, descripcion, objetivo, nivelDificultad, opcion, actividadesPre, actividadSeguimiento, fechaLimite, tipoRecurso, urlRecurso, titulo, descripcionRecurso);
                profesor.aniadirActividadCreada(recursoEducativoNuevo);
                break;
        case 5: 
                System.out.println("Escriba el motivo de entrega");
                String motivoEntrega = escaner.nextLine();
                Tarea tareaNueva = GeneradorActividades.generarTarea(nombre, descripcion, objetivo, nivelDificultad, duracionEstimada, actividadesPre, actividadSeguimiento, fechaLimite, motivoEntrega, "No Entregado" );
                profesor.aniadirActividadCreada(tareaNueva);
                break;
        case 6:
                System.exit(0);
                break;
        default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public static List<PreguntaAbierta> generadorPreguntasAbiertas() // El ususario escribe las preguntas que desea hasta 
    {
        List<PreguntaAbierta> preguntas = new LinkedList<>();
        boolean check = true;
        while ( check )
        {
            System.out.println("Escriba el enunciado de la pregunta: ");
            String enunciado = escaner.nextLine();
            if ( enunciado.equals("00") == false ) 
            { 
                System.out.println("Escriba la explicacion de la pregunta: ");
                String explicacion = escaner.nextLine();
                PreguntaAbierta preguntaNueva = GeneradorPreguntas.generarPreguntaAbierta(enunciado, explicacion);
                preguntas.add(preguntaNueva);
                System.out.println("Escriba 00 para terminar de generar preguntas.");
            }
            else 
            { 
                System.out.println("Se han generado"+preguntas.size()+"preguntas abiertas"); 
                check = false;
            }
        }
        return preguntas;
    }

    public static List<PreguntaCerrada> generadorPreguntasCerradas() // El ususario escribe las preguntas que desea hasta 
    {
        List<PreguntaCerrada> preguntas = new LinkedList<>();
        boolean check = true;
        
        while ( check )
        {
            boolean checkCorrecta = false;
            System.out.println("Escriba el enunciado de la pregunta: ");
            String enunciado = escaner.nextLine();
            if ( enunciado.equals("00") == false ) 
            { 
                System.out.println("Escriba la explicacion de la pregunta: ");
                String explicacion = escaner.nextLine();

                List<Opcion> opciones = new LinkedList<>();

                for (int i = 0; i < 4; i++)
                {
                    Opcion opcionActual;
                    System.out.println("Escriba el enunciado de la opcion" + (i+1));
                    String texto = escaner.nextLine();
                    System.out.println("Es la respuesta correcta? S/N");
                    String esCorrecta = escaner.nextLine();
                    if ( (esCorrecta.equals("S")) && (checkCorrecta = false) )
                    {
                        opcionActual = new Opcion(texto, true);
                        checkCorrecta = true;
                        System.out.println("Se ha escogido como la opcion correcta");
                    }
                    else
                    {
                        System.out.println("No se ha escogido como la opcion correcta");
                        opcionActual = new Opcion(texto, false);
                    }
                    opciones.add(opcionActual);
                }

                PreguntaCerrada preguntaNueva = GeneradorPreguntas.generarPreguntaCerrada(enunciado, explicacion, opciones);
                preguntas.add(preguntaNueva);
                System.out.println("Escriba 00 para terminar de generar preguntas.");
            }
            else 
            { 
                System.out.println("Se han generado"+preguntas.size()+"preguntas abiertas"); 
                check = false;
            }
        }
        return preguntas;
    }

    public static void clonarActividadProfesor( Profesor profesor )
    {
        System.out.println("Ingresa el codigo de la Actividad a clonar");
        String idActividad = escaner.nextLine();
        Actividad actividadClonada = null;
        if ( GeneradorActividades.validarActividad(idActividad) == true)
        {
            Actividad actividadClonar = GeneradorActividades.getActividad(idActividad);

            if ( actividadClonar instanceof Encuesta ){ actividadClonada = GeneradorActividades.clonarEncuesta(idActividad); }
            else if ( actividadClonar instanceof Examen ){ actividadClonada = GeneradorActividades.clonarExamen(idActividad); }
            else if ( actividadClonar instanceof Quiz ){ actividadClonada = GeneradorActividades.clonarQuiz(idActividad); }
            else if ( actividadClonar instanceof RecursoEducativo ){ actividadClonada = GeneradorActividades.clonarRecursoEducativo(idActividad); }
            else if ( actividadClonar instanceof Tarea ){ actividadClonada = GeneradorActividades.clonarTarea(idActividad); }

            String nuevoCodigo = actividadClonada.getID();
            System.out.println("El codigo ID de la actividad clonada es"+nuevoCodigo);
            profesor.aniadirActividadCreada(actividadClonada);
        } 
        else 
        { 
            System.out.println("Ingresa el codigo de la Actividad a clonar");
        }
    }

    public static void editarActividadProfesor( Profesor profesor )
    {
        System.out.println("Ingresa el codigo de la Actividad a clonar");
        String idActividad = escaner.nextLine();
        if ( GeneradorActividades.validarActividad(idActividad) == true)
        {
            Actividad actividadEditar = GeneradorActividades.getActividad(idActividad);
            if ( profesor.getActividadesCreadas().contains(actividadEditar) == false)
            { 
                System.out.println("Esa actividad no le pertenece, se ha creado una copia para editar"); 
                Actividad actividadClonada = null;
                if ( actividadEditar instanceof Encuesta ){ actividadClonada = GeneradorActividades.clonarEncuesta(idActividad); }
                else if ( actividadEditar instanceof Examen ){ actividadClonada = GeneradorActividades.clonarExamen(idActividad); }
                else if ( actividadEditar instanceof Quiz ){ actividadClonada = GeneradorActividades.clonarQuiz(idActividad); }
                else if ( actividadEditar instanceof RecursoEducativo ){ actividadClonada = GeneradorActividades.clonarRecursoEducativo(idActividad); }
                else if ( actividadEditar instanceof Tarea ){ actividadClonada = GeneradorActividades.clonarTarea(idActividad); }

                actividadEditar = actividadClonada;
            } 	

            System.out.println("Selecciona una opción:");
            System.out.println("0. Salir");
            System.out.println("1. Editar Nombre");
            System.out.println("2. Editar Descripcion");
            System.out.println("3. Editar Objetivo");
            System.out.println("4. Editar Nivel de Dificultad");
            System.out.println("5. Editar duracion Estimada");
            System.out.println("6. Editar Actividades de preRequisitos");
            System.out.println("7. Editar Actividad de Seguimiento");
            System.out.println("8. Editar Fecha Limite de Actividad");
            System.out.println("9. Editar Resultado");

            if ( actividadEditar instanceof Encuesta )
            { 
                System.out.println("10. Editar Preguntas");
            }
            else if ( actividadEditar instanceof Examen )
            {
                System.out.println("10. Editar Preguntas");
            }
            else if ( actividadEditar instanceof Quiz )
            {
                System.out.println("10. Editar Preguntas");
            }
            else if ( actividadEditar instanceof RecursoEducativo )
            { 
                System.out.println("10. Editar tipo de Recurso");
                System.out.println("11. Editar URL del Recurso");
                System.out.println("12. Editar titulo del Recurso");
                System.out.println("13. Editar descripcion del Recurso");
            }
            else if ( actividadEditar instanceof Tarea )
            { 
                System.out.println("10. Editar motivo de Entrega");
                System.out.println("11. Editar Estado Envio");
            }

            System.out.print("Opción: ");
            String opcionIn = escaner.nextLine();
            int opcion = Integer.parseInt(opcionIn);

            switch(opcion)
            {
            case 0:
                    System.exit(0);
                    break;
            case 1:
                    System.out.println("Ingrese el nuevo Nombre de la Actividad: ");
                    String nuevoNombre = escaner.nextLine();
                    actividadEditar.setNombre(nuevoNombre);
                    break;
            case 2: 
                    System.out.println("Ingrese la nueva Descripcion de la Actividad: ");
                    String nuevaDescripcion = escaner.nextLine();
                    actividadEditar.setDescripcion(nuevaDescripcion);
                    break;

            case 3: 
                    System.out.println("Ingrese el nuevo Objetivo de la Actividad: ");
                    String nuevoObejtivo = escaner.nextLine();
                    actividadEditar.setObjetivo(nuevoObejtivo);
                    break;
            case 4: 
                    System.out.println("Escoja un nivel de Dificultad:");
                    System.out.println("1. Principiante");
                    System.out.println("2. Intermedio");
                    System.out.println("3. Avanzado");

                    String nivelLine = escaner.nextLine();
                    int nivel = Integer.parseInt(nivelLine);

                    String dificultad = null;
                    switch (nivel) 
                    {
                        case 1: dificultad = "Principiante";
                        case 2: dificultad = "Intermedio";
                        case 3: dificultad = "Avanzado";
                        default: System.out.println("Opcion no valida");
                    }
                    if ( dificultad != null) { actividadEditar.setNivelDificultad( dificultad ); }
                    break;
            case 5: 
                    System.out.println("Ingrese la nueva duracion Estimada");
                    String value = escaner.nextLine();
                    double nuevaDuracion = Double.parseDouble(value);
                    actividadEditar.setDuracionEstimada(nuevaDuracion);
                    break;
            case 6:
                    System.out.println("Escoja una accion:");
                    System.out.println("1. Aniadir PreRequisito");
                    System.out.println("2. Eliminar PreRequisito");
                    System.out.println("3. Eliminar todos los PreRequisitos");

                    String accionLine = escaner.nextLine();
                    int accionPR = Integer.parseInt(accionLine);
                    String codigoPR = null;
                    switch (accionPR) 
                    {
                        case 1:
                                System.out.println("Ingrese el codigo de la Actividad que desea aniadir");
                                codigoPR = escaner.nextLine();
                                if ( GeneradorActividades.validarActividad(codigoPR) ) { actividadEditar.aniadirActividadesPrerequisitos( GeneradorActividades.getActividad(codigoPR) ); }
                                else { System.out.println("No se encontro la actividad"); }
                                break;
                        case 2:
                                System.out.println("Ingrese el codigo de la Actividad que desea eliminar");
                                codigoPR = escaner.nextLine();
                                if ( GeneradorActividades.validarActividad(codigoPR) ) 
                                { 
                                    actividadEditar.eliminarActividadPrerequisitos( GeneradorActividades.getActividad(codigoPR) ); 
                                }
                                else { System.out.println("No se encontro la actividad"); }
                                break;
                        case 3:
                                actividadEditar.eliminarPrerequisitos();
                                break;
                        default: 
                                System.out.println("Opcion no valida");
                    }
                    break;
            case 7:
                    System.out.println("Escoja una accion:");
                    System.out.println("1. Cambiar Actividad de Seguimiento");
                    System.out.println("2. Eliminar Actividad de Seguimiento");

                    String accionCaso7 = escaner.nextLine();
                    int accionSeguimiento = Integer.parseInt(accionCaso7);

                    switch (accionSeguimiento) 
                    {
                        case 1:
                                System.out.println("Ingrese el codigo de la Actividad por la que desea reemplazar el Seguimiento");
                                String codigoSeguimiento = escaner.nextLine();
                                if ( GeneradorActividades.validarActividad(codigoSeguimiento) ) { actividadEditar.aniadirActividadesPrerequisitos( GeneradorActividades.getActividad(codigoSeguimiento) ); }
                                else { System.out.println("No se encontro la actividad"); }
                                break;
                        case 2:
                                actividadEditar.eliminarActividadSeguimiento();	
                                System.out.println("Se ha eliminado la actividad de seguimiento");
                                break;
                        default: 
                                System.out.println("Opcion no valida");
                    }
                    break;
            case 8:
                    System.out.println("Ingrese la nueva Fecha Limite de la Actividad en formato dd-MMM-yyyy");
                    String date = escaner.nextLine(); // convertir a date
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        Date fechaLimite = formatter.parse(date);
                        actividadEditar.setFechaLimite(fechaLimite);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            case 9:
                    System.out.println("Ingrese el resultado deseado");
                    String resultado = escaner.nextLine();
                    actividadEditar.setResultado(resultado);
                    break;
            case 10:
                    if ( actividadEditar instanceof Encuesta )
                    { 
                        System.out.println("Ingrese las nuevas preguntas de la Encuesta");
                        List<PreguntaAbierta> nuevasPreguntas = generadorPreguntasAbiertas();
                        ((Encuesta)actividadEditar).setPreguntas(nuevasPreguntas);
                    }
                    else if ( actividadEditar instanceof Examen )
                    { 
                        System.out.println("Ingrese las nuevas preguntas del Examen");
                        List<PreguntaAbierta> nuevasPreguntas = generadorPreguntasAbiertas();
                        ((Examen)actividadEditar).setPreguntas(nuevasPreguntas); 
                    }
                    else if ( actividadEditar instanceof Quiz )
                    { 
                        System.out.println("Ingrese las nuevas preguntas del Quiz");
                        List<PreguntaCerrada> nuevasPreguntas = generadorPreguntasCerradas();
                        ((Quiz)actividadEditar).setPreguntas(nuevasPreguntas); 
                    }
                    else if ( actividadEditar instanceof RecursoEducativo )
                    {
                        System.out.println("Ingrese el nuevo tipo de Recurso");
                        String tipoRecursoNuevo = escaner.nextLine();
                        ((RecursoEducativo)actividadEditar).setTipoRecurso(tipoRecursoNuevo); 
                    }
                    else if ( actividadEditar instanceof Tarea )
                    { 
                        System.out.println("Ingrese el nuevo motivo de Entrega");
                        String motivoEntregaNuevo = escaner.nextLine();
                        ((Tarea)actividadEditar).setMotivoEntrega(motivoEntregaNuevo); 
                    }
                    break;
            case 11:
                    if ( actividadEditar instanceof RecursoEducativo )
                    {
                        System.out.println("Ingrese el nuevo URL del Recurso");
                        String urlRecursoNuevo = escaner.nextLine();
                        ((RecursoEducativo)actividadEditar).setUrlRecurso(urlRecursoNuevo); 
                    }
                    else if ( actividadEditar instanceof Tarea )
                    {
                        System.out.println("Ingrese el nuevo Estado Envio");
                        String estadoEnvioNuevo = escaner.nextLine();
                        ((Tarea)actividadEditar).setEstadoEnvio(estadoEnvioNuevo);
                    }
                    else
                    {
                        System.exit(0);
                        break;
                    }
            case 12:
                    if ( actividadEditar instanceof RecursoEducativo )
                    {
                        System.out.println("Ingrese el nuevo titulo del Recurso");
                        String tituloNuevo = escaner.nextLine();
                        ((RecursoEducativo)actividadEditar).setTitulo(tituloNuevo); 
                    }
                    else
                    {
                        System.exit(0);
                        break;
                    }
            case 13:
                    if ( actividadEditar instanceof RecursoEducativo )
                    {
                        System.out.println("Ingrese la nueva descripcion del Recurso");
                        String descripcionRecursoNuevo = escaner.nextLine();
                        ((RecursoEducativo)actividadEditar).setTitulo(descripcionRecursoNuevo); 
                    }
                    else
                    {
                        System.exit(0);
                        break;
                    }
            default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    public static void menuEstudiante( Estudiante estudiante )
    {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Registrarse a un Learning Path");
        System.out.println("2. Iniciar Actividad");
        System.out.println("3. Completar Learning Path");
        System.out.println("4. Crear Resenia");
        System.out.println("5. Salir:");

        System.out.print("Opción: ");
        String opcionIn = escaner.nextLine();
        int opcion = Integer.parseInt(opcionIn);
        
        switch (opcion)
        {
            case 1: 
                registrarLearningPathEstudiante( estudiante );
                break;
        case 2: 
                iniciarActividadEstudiante( estudiante );
                break;
        case 3: 
                completarLearningPathEstudiante( estudiante );
                break;
        case 4: 
                crearReseniaEstudiante( estudiante );
                break;
        case 5:
                System.exit(0);
                break;
        default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public static void registrarLearningPathEstudiante( Estudiante estudiante )
    {
        if ( estudiante.getLearningPathInscrito() == null)
        {
            System.out.println("Escriba el nombre del Learning Path para inscribirse: ");
            String titulo = escaner.nextLine();
            estudiante.inscribirLearningPath(learningPaths.get(titulo));
            if ( estudiante.getLearningPathInscrito() == null ) { System.out.println("No se ha encontrado un Learning Path con ese nombre"); }
            else { System.out.println("Learning Path Inscrito Exitosamente");}
        }
        else { System.out.println("Ya tiene un Learning Path Inscrito"); }
    }

    public static void iniciarActividadEstudiante( Estudiante estudiante )
    {
        if ( estudiante.getLearningPathInscrito() != null)
        {
            System.out.println("Escriba el numero de Actividad que desea realizar");
            System.out.println(estudiante.getLearningPathInscrito().getActividades().size()+"opciones");
            String value = escaner.nextLine(); // convertir a double
            int i = Integer.parseInt(value);
            Actividad actividad = estudiante.getLearningPathInscrito().getActividades().get(i+1);
            boolean checkPR = false;
            for ( Actividad actividadpr : actividad.getPreRequisitos())
            {
                if ( estudiante.getActividadesCompletadas().contains(actividadpr) == true ) { checkPR = true; }
                else { checkPR = false; }
            }
            if ( checkPR == false) { System.out.println("No ha completado todos los PreRequisitos de la habilidad"); } 
        
            if ( actividad instanceof Encuesta ){ iniciarEncuestaEstudiante( (Encuesta) actividad ); }
            else if ( actividad instanceof Examen ){ iniciarExamenEstudiante( (Examen) actividad ); }
            else if ( actividad instanceof Quiz ){ iniciarQuiz( (Quiz) actividad ); }
            else if ( actividad instanceof RecursoEducativo ){ iniciarRecursoEducativo( (RecursoEducativo) actividad ); }
            else if ( actividad instanceof Tarea ){ iniciarTarea( (Tarea) actividad ); }

        }
        else { System.out.println("No tiene un Learning Path Inscrito"); }
    }

    public static void iniciarEncuestaEstudiante( Encuesta encuesta )
    {
        List<String> respuestas = new LinkedList<>();
        for ( PreguntaAbierta pregunta: encuesta.getPreguntas() )
        {
            System.out.println( pregunta.getEnunciado() );
            System.out.print("Respuesta: ");
            String respuesta = escaner.nextLine();
            respuestas.add(respuesta);
        }

        encuesta.setEstadoCompletado(true);
        System.out.println("La siguiente actividad recomendada es: ");
        System.out.print(encuesta.getSeguimiento().getNombre());
    }

    public static void iniciarExamenEstudiante( Examen examen )
    {
        List<String> respuestas = new LinkedList<>();
        for ( PreguntaAbierta pregunta: examen.getPreguntas() )
        {
            System.out.println( pregunta.getEnunciado() );
            System.out.print("Respuesta: ");
            String respuesta = escaner.nextLine();
            respuestas.add(respuesta);
        }
        examen.setEstadoCalificacion("Enviado");
        System.out.println("La siguiente actividad recomendada es: ");
        System.out.print(examen.getSeguimiento().getNombre());
    }

    public static void iniciarQuiz( Quiz quiz )
    {
        double contador = 0;
        for ( PreguntaCerrada pregunta: quiz.getPreguntas() )
        {
            System.out.println( pregunta.getEnunciado() );
            for ( Opcion opcion: pregunta.getOpciones() )
            {
                System.out.print(opcion.getTexto());
            }
            System.out.print("Opcion 1-4: ");
            String respuesta = escaner.nextLine();
            int respuestaNum = Integer.parseInt(respuesta);
            if ( pregunta.getOpciones().get(respuestaNum).getEsCorrecta() ){ contador ++; }
        }
        double calificacion = contador/quiz.getPreguntas().size()*5;
        if ( calificacion >= 3.0 ){ quiz.setEstadoCalificacion("Completado"); }
        else { System.out.println("Quiz no completado"); }
    }

    public static void iniciarRecursoEducativo( RecursoEducativo recursoEducativo )
    {
        System.out.println(recursoEducativo.getTipoRecurso());
        System.out.println(recursoEducativo.getUrlRecurso());
        System.out.println(recursoEducativo.getTitulo());
        System.out.println(recursoEducativo.getDescripcionRecurso());

        recursoEducativo.setCompletado(true);
        System.out.println("La siguiente actividad recomendada es: ");
        System.out.print(recursoEducativo.getSeguimiento().getNombre());
    }

    public static void iniciarTarea( Tarea tarea )
    {
        System.out.println(tarea.getNombre());
        System.out.println(tarea.getDescripcion());
        System.out.println(tarea.getObjetivo());
        System.out.println(tarea.getMotivoEntrega());
        tarea.setEstadoEnvio("Enviada");
    }

    public static void completarLearningPathEstudiante( Estudiante estudiante )
    {
        LearningPath learningPath = estudiante.getLearningPathInscrito();
        if ( learningPath != null)
        {
            double progreso = estudiante.getProgreso();
            if ( ( int) progreso == 100)
            {
                System.out.println("Learning Path completado exitosamente");
                estudiante.terminarLearningPath();
            }
            else { System.out.println("No tiene el progreso suficiente para terminar el Learning Path"); }
        }
        else { System.out.println("No tiene un Learning Path Inscrito"); }

    }

    public static void crearReseniaEstudiante( Estudiante estudiante )
    {
        System.out.println("Escriba el nombre del Learning Path para reseniar");
        String titulo = escaner.nextLine();
        LearningPath learningPath = learningPaths.get(titulo);
        if (  learningPath != null )
        {
            System.out.println("Escriba el comentario:");
            String comentario = escaner.nextLine();
            System.out.println("Escriba la puntacion de 1-5: ");
            String value = escaner.nextLine(); // convertir a double
            double rating = Double.parseDouble(value);
            System.out.println( estudiante.crearResenia(comentario, rating, learningPath) );
        }
        else { System.out.println("Learning Path no encontrado"); }
    }

    
    public static void main( String[] args )
    {
        while (true)
        {
            String login = menuLogin();
            if (login == "error")
            {  
                System.exit(0);
                break;
            }
            else
            {
                Usuario usuario = usuarios.get(login);
                if (  usuario instanceof Profesor ) 
                {
                    Profesor profesor = (Profesor) usuario; 
                    menuProfesor( profesor );
                }
                else if ( usuario instanceof Estudiante)
                {
                    Estudiante estudiante = (Estudiante) usuario;
                    menuEstudiante( estudiante );
                    }
            }
        }
    }
}

