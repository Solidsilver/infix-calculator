package Exceptions;
public class ArithmeticExpressionException extends RuntimeException {
	
	private static final long serialVersionUID = -781885720320742799L;

	public ArithmeticExpressionException() {
		super();
	}

	public ArithmeticExpressionException(String message) {
		super(message);
	}
}