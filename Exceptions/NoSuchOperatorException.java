package Exceptions;
public class NoSuchOperatorException extends RuntimeException {
	
	static final long serialVersionUID = -7731729232087509747L;

	public NoSuchOperatorException() {
		super();
	}

	public NoSuchOperatorException(String message) {
		super(message);
	}
}