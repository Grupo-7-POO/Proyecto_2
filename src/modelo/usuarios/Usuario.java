package modelo.usuarios;

import java.util.List;

import modelo.LearningPath;

public abstract class Usuario
{
	protected String nombre;
	
	protected String email;
	
	protected String login;
	
	protected String contrasena;

	private List<LearningPath> learningPaths;
		
	public Usuario(String nombre, String email, String login, String contrasena)
	{
		this.nombre = nombre;
		this.email = email;
		this.login = login;
		this.contrasena = contrasena;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}

	public String getEmail()
	{
		return this.email;
	}

	public String getLogin()
	{
		return this.login;
	}

	public void changePassword( String password)
	{
		this.contrasena = password;
	}

	public String getPassword()
	{
		return contrasena;
	}

    public List<LearningPath> getLearningPaths() {

        return learningPaths;

    }



    public void setLearningPaths(List<LearningPath> learningPaths) {

        this.learningPaths = learningPaths;

    }

}