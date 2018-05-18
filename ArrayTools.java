public class ArrayTools {

	//Add a given object to the specified object array
	public static Object[] addToArray(Object[] oarr, Object o) {
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

	//Separate operands from operators, and put them in an array form
	public static Object[] eqnToArr(String eqn) {
		Object[] parsedEqn = null;
		char[] eq = eqn.replaceAll("\\s+","").toCharArray();
		String temp = "";
		for (int ix = 0; ix < eq.length; ix++) {
			if (FixTools.isOperand("" + eq[ix])) {
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

	public static int indexOf(String[] arr, String s) {
		if (arr == null) {
			return -1;
		}
		for (int ix = 0; ix < arr.length; ix++) {
			if (arr[ix].equals(s)) {
				return ix;
			}
		}
		return -1;
	}
}