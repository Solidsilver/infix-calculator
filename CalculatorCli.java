import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class CalculatorCli {
	
	private String[] vars;
	private double[] varValues;
	
	public static void main(String[] args) throws FileNotFoundException {
		CalculatorCli c = new CalculatorCli();
		if (args.length < 1) {
			c.readVars("hw5_input2.txt");
			PrintStream fout = new PrintStream(new File("hw5_output.txt"));
			c.eqnsFromFile("hw5_input3.txt", fout);
			
		} else if (args[0].equals("-p")) {
			String in;
			Scanner kb = new Scanner(System.in);
			do {
				out("clc: ");
				in = kb.nextLine();
				if (!in.equals("exit")) {
					try {
						out("= " + c.eval(in) + "\n\n");
					} catch (Exception e) {
						out(in + " --> " + e.getMessage() + "\n\n");
					}
				}
			} while (!in.equals("exit"));
		} else {
			out(c.eval(args[0]) + "\n");
		}
	}
	
	public String eval(String eqn) {
		Object[] arr = FixTools.toPostfix(eqn);
		this.replaceVars(arr);
		return "" + FixTools.evalPostfix(arr);
		
	}
	
	public String evalVerbos(String eqn) {
		String ret = "";
		try {
			ret += eqn + " --> ";
			Object[] arr = FixTools.toPostfix(eqn);
			this.replaceVars(arr);
			ret += ArrayTools.arrToString(arr, "") + " --> ";
			ret += FixTools.evalPostfix(arr) + "\n";
		} catch (Exception e) {
			ret += e.getMessage() + "\n";
		}
		return ret;
	}
	
	//Assign Variables
	public void setVar(String varName, double varValue) {
		if (this.vars == null || varValues == null) {
			this.vars = new String[1];
			this.varValues = new double[1];
			this.vars[0] = varName;
			this.varValues[0] = varValue;
		} else {
			if (ArrayTools.indexOf(this.vars, varName) < 0) {	//Check if variable is already in use
				String[] tempN = this.vars;
				double[] tempD = this.varValues;
				this.vars = new String[tempN.length + 1];
				this.varValues = new double[tempD.length + 1];
				for (int ix = 0; ix < tempN.length; ix++) {
					this.vars[ix] = tempN[ix];
					this.varValues[ix] = tempD[ix];
				}
				this.vars[tempN.length] = varName;
				this.varValues[tempD.length] = varValue;
			} else {
				this.varValues[ArrayTools.indexOf(this.vars, varName)] = varValue;
			}
		}
	}

	public double getVarValue(String varName) {
		int idx = ArrayTools.indexOf(vars, varName);
		if (idx >= 0) {
			return this.varValues[idx];
		} else {
			return 0.0;
		}
	}

	//Replace Variables with their values
	private void replaceVars(Object[] eqn) {
		for (int ix = 0; ix < eqn.length; ix++) {
			if (ArrayTools.indexOf(this.vars, "" + eqn[ix]) > -1) {
				eqn[ix] = this.varValues[ArrayTools.indexOf(this.vars, "" + eqn[ix])];
			}
		}
	}
	
	private void readVars(String fname) throws FileNotFoundException {
		File fn = new File(fname);
		if (!fn.exists())
			throw new FileNotFoundException("File does not exist");
		Scanner fin = new Scanner(fn);
		String line;
		while (fin.hasNextLine()) {
			line = fin.nextLine();
			line = line.replaceAll("\\s+","");
			setVar(line.substring(0, line.indexOf("=")), Double.parseDouble(line.substring(line.indexOf("=") + 1)));
		}
	}
	
	private void eqnsFromFile(String fname, PrintStream fout) throws FileNotFoundException {
		File fn = new File(fname);
		if (!fn.exists())
			throw new FileNotFoundException("File does not exist");
		Scanner fin = new Scanner(fn);
		while (fin.hasNextLine()) {
			out(evalVerbos(fin.nextLine()), fout);
		}
	}

	private static void out(Object o) {
		out(o, System.out);
	}
	
	private static void out(Object o, PrintStream p) {
		p.print(o);
	}
}