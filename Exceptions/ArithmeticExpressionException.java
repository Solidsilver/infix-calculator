package Exceptions;
public class ArithmeticExpressionException extends RuntimeException {
	
	public ArithmeticExpressionException() {
		super();
	}

	public ArithmeticExpressionException(String message) {
		super(message);
	}
}