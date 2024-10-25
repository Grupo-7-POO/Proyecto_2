package modelo.usuarios;

public abstract class Usuario
{
	private String nombre;
	
	private String email;
	
	private String login;
	
	private String contrasena;
		
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

}