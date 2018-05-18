public class FixTools {
	
	// Convert an infix expression to a postfix expression
	public static Object[] toPostfix(String infix) {
		Object[] postfixOut = null;
		Object[] infixArr = eqnToArr(infix.replaceAll("\\s+",""));
		if (emptyExpression(infixArr)) {
			throw new IllegalArgumentException("Empty Infix Expression");
		}
		if (!parenMatch(infixArr)) {
			throw new IllegalArgumentException("Paren Mismatch");
		}
		int ix = 0;
		Object cur;
		LinkedListStack stack = new LinkedListStack();
		while (ix < infixArr.length) {
			cur = infixArr[ix];
			if (isOperand(cur)) {
				postfixOut = addToArray(postfixOut, cur);
			} else if (cur.equals("(")) {
				stack.push(cur);
			} else if (cur.equals(")")) {
				while (!stack.peak().equals("(")) {
					postfixOut = addToArray(postfixOut, stack.pop());
				}
				stack.pop();

			} else {
				while (!stack.isEmpty() && rankOf(stack.peak(), true) > rankOf(cur, false)) {
					postfixOut = addToArray(postfixOut, stack.pop());
				}
				stack.push(cur);
			}
			ix++;
		}
		while (!stack.isEmpty()) {
			postfixOut = addToArray(postfixOut, stack.pop());
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
					throw new IllegalArgumentException("Infix Syntax Error");
				}
				stack.push(evalOperation(left, arr[ix], right));
			}
		}
		
		if (stack.size() == 1 && stack.peak() != null) {
			return (double)stack.peak();
		} else {
			throw new IllegalArgumentException("Infix Syntax Error");
		}
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
			throw new IllegalArgumentException("Operator Not Found");
		}
	}
	
	//Separate operands from operators, and put them in an array form
	private static Object[] eqnToArr(String eqn) {
		Object[] parsedEqn = null;
		char[] eq = eqn.replaceAll("\\s+","").toCharArray();
		String temp = "";
		for (int ix = 0; ix < eq.length; ix++) {
			if (isOperand("" + eq[ix])) {
				temp += eq[ix];
			} else {
				if (!temp.equals(""))
					parsedEqn = addToArray(parsedEqn, temp);
				temp = eq[ix] + "";
				parsedEqn = addToArray(parsedEqn, temp);
				temp = "";
			}
		}
		if (!temp.equals(""))
			parsedEqn = addToArray(parsedEqn, temp);
		return parsedEqn;
	}
	
	//Add a given object to the specified object array
	private static Object[] addToArray(Object[] oarr, Object o) {
		Object[] ret, temp = oarr;
		if (temp == null) {
			ret = new Object[1];
			ret[0] = o;
			return ret;
		} else {
			ret = new Object[temp.length + 1];
			for (int ix = 0; ix < temp.length; ix++) {
				ret[ix] = temp[ix];
			}
			ret[ret.length - 1] = o;
			return ret;
		}
		
	}

	private static boolean isOperand(Object c) {
		return (rankCur(c) == -1);
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
	
	public static String arrToString(Object[] arr) {
		if (arr == null) {
			return "Array is null";
		}
		String temp = "";
		for (int ix = 0; ix < arr.length; ix++) {
			temp += "[" + arr[ix] + "]";
		}
		return temp;
	}
	
	public static String arrToString(Object[] arr, String seperator) {
		if (arr == null) {
			return "Array is null";
		}
		String temp = "";
		for (int ix = 0; ix < arr.length; ix++) {
			temp += arr[ix] + seperator;
		}
		return temp;
	 }


}