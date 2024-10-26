package exceptions;

/**
 * Esta excepción se usa para indicar que se encontraron dos clientes con el mismo identificador, lo cual nunca debería ocurrir.
 */
@SuppressWarnings("serial")
public class UserRepetidoException extends InformacionInconsistenteException
{
    /**
     * El tipo de cliente que se estaba creando cuando se detectó el problema
     */
    private String tipoCliente;
    
    /**
     * El identificador de cliente que ya se había encontrado antes
     */
    private String identificador;

    public UserRepetidoException( String tipoCliente, String identificador )
    {
        super( "" );
        this.tipoCliente = tipoCliente;
        this.identificador = identificador;
    }

    @Override
    public String getMessage( )
    {
        return "Cliente repetido: ya existe un cliente de tipo " + tipoCliente + "  con el identificador " + this.identificador;
    }

}
