package Exceptions;
public class NoSuchOperatorException extends RuntimeException {
	
	public NoSuchOperatorException() {
		super();
	}

	public NoSuchOperatorException(String message) {
		super(message);
	}
}