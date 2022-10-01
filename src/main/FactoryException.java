package main;

public class FactoryException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public FactoryException(Object message, Object...args)
	{
		super( String.format( message instanceof String ? ( String ) message : message.toString() ) );
	}
}
