package sistemabase;

import java.util.HashMap;
import modelo.LearningPath;
import modelo.usuarios.Usuario;
import java.util.Map;


public class EstadoGlobal
{
	private static Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
	private static Map<String, LearningPath> learningPaths = new HashMap<String, LearningPath>();


	public Map<String, Usuario> getUsuarios()
	{
		return usuarios;
	}

	public Map<String, LearningPath> getLearningPaths()
	{
		return learningPaths;
	}

	public boolean valirdarUsuario( String login, String password)
	{

		if ( usuarios.containsKey(login) )
		{
			Usuario usuarioactual = usuarios.get(login);
			if (usuarioactual.getPassword() == password){ return true; }
			else { return false;}
		}
		else { return false;}
	}
}