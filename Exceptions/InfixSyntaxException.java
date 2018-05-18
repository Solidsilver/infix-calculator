package Exceptions;
public class InfixSyntaxException extends RuntimeException {
	
	public InfixSyntaxException() {
		super();
	}

	public InfixSyntaxException(String message) {
		super(message);
	}
}