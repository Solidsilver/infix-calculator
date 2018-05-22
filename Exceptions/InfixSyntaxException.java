package Exceptions;
public class InfixSyntaxException extends RuntimeException {
	
	private static final long serialVersionUID = -3664803993368953659L;

	public InfixSyntaxException() {
		super();
	}

	public InfixSyntaxException(String message) {
		super(message);
	}
}