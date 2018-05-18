import Exceptions.*;
public class FixTools {
	
	// Convert an infix expression to a postfix expression
	public static Object[] toPostfix(String infix) {
		Object[] postfixOut = null;
		Object[] infixArr = ArrayTools.eqnToArr(infix.replaceAll("\\s+",""));
		if (emptyExpression(infixArr)) {
			throw new ArithmeticExpressionException("Empty Infix Expression");
		}
		if (!parenMatch(infixArr)) {
			throw new ArithmeticExpressionException("Paren Mismatch");
		}
		int ix = 0;
		Object cur;
		LinkedListStack stack = new LinkedListStack();
		while (ix < infixArr.length) {
			cur = infixArr[ix];
			if (isOperand(cur)) {
				postfixOut = ArrayTools.addToArray(postfixOut, cur);
			} else if (cur.equals("(")) {
				stack.push(cur);
			} else if (cur.equals(")")) {
				while (!stack.peak().equals("(")) {
					postfixOut = ArrayTools.addToArray(postfixOut, stack.pop());
				}
				stack.pop();

			} else {
				while (!stack.isEmpty() && rankOf(stack.peak(), true) > rankOf(cur, false)) {
					postfixOut = ArrayTools.addToArray(postfixOut, stack.pop());
				}
				stack.push(cur);
			}
			ix++;
		}
		while (!stack.isEmpty()) {
			postfixOut = ArrayTools.addToArray(postfixOut, stack.pop());
		}
		return postfixOut;
	}
	
	//Evaluate a given postfix expression
	public static double evalPostfix(Object[] arr) {
		if (arr == null) {
			throw new NullPointerException("Cannot evaluate null array");
		}
		Object left, right;
		LinkedListStack stack = new LinkedListStack();
		for (int ix = 0; ix < arr.length; ix++) {
			if (isOperand(arr[ix])) {
				stack.push(arr[ix]);
			} else {
				try {
					right = stack.pop();
					left = stack.pop();
				} catch (Exception E) {
					throw new InfixSyntaxException("Infix Syntax Error");
				}
				stack.push(evalOperation(left, arr[ix], right));
			}
		}
		
		if (stack.size() == 1 && stack.peak() != null) {
			return (double)stack.peak();
		} else {
			throw new InfixSyntaxException("Infix Syntax Error");
		}
	}
	
	//Evaluate a simple operation given two operands and one operator
	private static double evalOperation(Object left, Object operator, Object right) {
		double lft = Double.parseDouble("" + left);
		double rgt = Double.parseDouble("" + right);
		if (operator.equals("^")) {
			return Math.pow(lft, rgt);
		} else if (operator.equals("*")) {
			return lft*rgt;
		} else if (operator.equals("/")) {
			return lft/rgt;
		} else if (operator.equals("%")) {
			return lft%rgt;
		} else if (operator.equals("+")) {
			return lft+rgt;
		} else if (operator.equals("-")) {
			return lft-rgt;
		} else {
			throw new NoSuchOperatorException("Operator " + operator + " Not Found");
		}
	}

	public static boolean isOperand(Object c) {
		return (rankCur(c) == -1);
	}

	private static boolean parenMatch(Object[] o) {
		LinkedListStack s = new LinkedListStack();
		for (int ix = 0; ix < o.length; ix++) {
			if (o[ix].equals("(")) {
				s.push(o[ix]);
			} else if (o[ix].equals(")")) {
				if (s.isEmpty()) {
					return false;
				}
				if (s.pop().equals(")")) {
					return false;
				}
			}
		}
		if (s.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean emptyExpression(Object[] o) {
		for (int ix = 0; ix < o.length; ix++) {
			if (rankCur(o[ix]) < 0) {
				return false;
			}
		}
		return true;
	}

	private static int rankOf(Object op, boolean onStack) {
		if (onStack)
			return rankOnStack(op);
		return rankCur(op);
	}

	private static int rankOnStack(Object op) {
		if (op.equals("(")) {
			return 0;
		} else if (op.equals("^")) {
			return 5;
		} else if (op.equals("*")) {
			return 4;
		} else if (op.equals("/")) {
			return 4;
		} else if (op.equals("%")) {
			return 4;
		} else if (op.equals("+")) {
			return 2;
		} else if (op.equals("-")) {
			return 2;
		} else {
			return -1;
		}
	}

	private static int rankCur(Object op) {
		if (op.equals("(")) {
			return 100;
		} else if (op.equals(")")) {
			return 0;
		} else if (op.equals("^")) {
			return 6;
		} else if (op.equals("*")) {
			return 3;
		} else if (op.equals("/")) {
			return 3;
		} else if (op.equals("%")) {
			return 3;
		} else if (op.equals("+")) {
			return 1;
		} else if (op.equals("-")) {
			return 1;
		} else {
			return -1;
		}
	}
}