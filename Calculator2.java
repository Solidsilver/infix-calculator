import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class Calculator2 extends JFrame implements ActionListener { 

	private static final long serialVersionUID = 1L;
	JButton btn1 = new JButton("1");
	JButton btn2 = new JButton("2");
	JButton btn3 = new JButton("3");
	JButton btn_add = new JButton("+");
	JButton btn4 = new JButton("4");
	JButton btn5 = new JButton("5");
	JButton btn6 = new JButton("6");
	JButton btn_sub = new JButton("-");
	JButton btn7 = new JButton("7");
	JButton btn8 = new JButton("8");
	JButton btn9 = new JButton("9");
	JButton btn_mult = new JButton("*");
	JButton btn0 = new JButton("0");
	JButton btn_dot = new JButton(".");
	JButton btn_del = new JButton("DEL");
	JButton btn_div = new JButton("/");
	
	JButton btn_lpr = new JButton("(");
	JButton btn_rpr = new JButton(")");
	JButton btn_pow = new JButton("^");
	JButton btn_equ = new JButton("=");

	JButton btn_mema = new JButton("M+");
	JButton btn_memc = new JButton("MC");
	JButton btn_memr = new JButton("MR");
	JButton btn_clr = new JButton("CLR");
	
	JTextArea txt = new JTextArea();
	String str_number = "";
	String lastPressed = "";
	CalculatorCli cli = new CalculatorCli();
	
	public Calculator2() {
		JFrame frame = new JFrame("Simple Java Caculator");
		frame.setSize(320,420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		//frame.setVisible(true);
		frame.setLayout(new GridLayout(2,1));
		
		JPanel HeadPanel = new JPanel();
		JPanel NumberPanel = new JPanel();
		JPanel LabelPanel = new JPanel();
		
		LabelPanel.setBackground(Color.LIGHT_GRAY);
		HeadPanel.setBackground(Color.LIGHT_GRAY);
		NumberPanel.setLayout(new GridLayout(6,4));
		//LabelPanel.setLayout(new BorderLayout());
		LabelPanel.setLayout(new GridLayout(1,1));
		
		NumberPanel.add(btn1);
		btn1.addActionListener(this);
		NumberPanel.add(btn2);
		btn2.addActionListener(this);
		NumberPanel.add(btn3);
		btn3.addActionListener(this);
		NumberPanel.add(btn_add);
		btn_add.addActionListener(this);
		
		NumberPanel.add(btn4);
		btn4.addActionListener(this);
		NumberPanel.add(btn5);

		btn5.addActionListener(this);
		NumberPanel.add(btn6);
		btn6.addActionListener(this);
		NumberPanel.add(btn_sub);
		btn_sub.addActionListener(this);

		NumberPanel.add(btn7);
		btn7.addActionListener(this);
		NumberPanel.add(btn8);
		btn8.addActionListener(this);
		NumberPanel.add(btn9);
		btn9.addActionListener(this);
		NumberPanel.add(btn_mult);
		btn_mult.addActionListener(this);
		
		NumberPanel.add(btn0);
		btn0.addActionListener(this);
		NumberPanel.add(btn_dot);
		btn_dot.addActionListener(this);
		NumberPanel.add(btn_del);
		btn_del.addActionListener(this);
		NumberPanel.add(btn_div);
		btn_div.addActionListener(this);
		LabelPanel.add(txt);

		//LabelPanel.add(btn_equ);
		NumberPanel.add(btn_lpr);
		btn_lpr.addActionListener(this);
		NumberPanel.add(btn_rpr);
		btn_rpr.addActionListener(this);
		NumberPanel.add(btn_clr);
		btn_clr.addActionListener(this);
		NumberPanel.add(btn_pow);
		btn_pow.addActionListener(this);

		NumberPanel.add(btn_mema);
		btn_mema.addActionListener(this);
		NumberPanel.add(btn_memc);
		btn_memc.addActionListener(this);
		NumberPanel.add(btn_memr);
		btn_memr.addActionListener(this);
		NumberPanel.add(btn_equ);
		btn_equ.addActionListener(this);

		txt.setEditable(true);
		//btn_del.setEnabled(true);
		HeadPanel.add(new JLabel("A Java Calculator"));
		frame.add(LabelPanel);
		frame.add(NumberPanel);
		frame.setVisible(true);
}
	
public void actionPerformed(ActionEvent e) {
	if (lastPressed.equals("")) {
		str_number = txt.getText();
	} else {
		lastPressed = "";
	}
	if(e.getSource()==btn1) {
		str_number+="1";
		txt.setText(str_number); }
	else if(e.getSource()==btn2) {
		str_number+="2";
		txt.setText(str_number); }
	else if(e.getSource()==btn3) {
		str_number+="3";
		txt.setText(str_number); }
	else if(e.getSource()==btn4) {
		str_number+="4";
		txt.setText(str_number); }
	else if(e.getSource()==btn5) {
		str_number+="5";
		txt.setText(str_number); }
	else if(e.getSource()==btn6) {
		str_number+="6";
		txt.setText(str_number); }
	else if(e.getSource()==btn7) {
		str_number+="7";
		txt.setText(str_number); }
	else if(e.getSource()==btn8) {
		str_number+="8";
		txt.setText(str_number); }
	else if(e.getSource()==btn9) {
		str_number+="9";
		txt.setText(str_number); }
	else if(e.getSource()==btn0) {
		str_number+="0";
		txt.setText(str_number); }
	else if(e.getSource()==btn_lpr) {
		str_number+="(";
		txt.setText(str_number); }
	else if(e.getSource()==btn_rpr) {
		str_number+=")";
		txt.setText(str_number); }
	else if(e.getSource()==btn_pow) {
		str_number+="^";
		txt.setText(str_number); }
	
	else if(e.getSource()==btn_add) {
			str_number+="+";
			txt.setText(str_number);}
	else if(e.getSource()==btn_sub) {
			 str_number+="-";
			 txt.setText(str_number);}
	else if(e.getSource()==btn_mult) {
			 str_number+="*";
			 txt.setText(str_number);}
	else if(e.getSource()==btn_div) {
			 str_number+="/";
			 txt.setText(str_number);}
	else if(e.getSource()==btn_equ) {
			String ans;
			String onScreen = txt.getText();
			lastPressed = "eq";
			if (onScreen.indexOf("=") < 0) {
				try {
					ans = this.cli.eval(str_number);
					str_number+="=" + ans;
				} catch (Exception exc) {
					str_number += " ---> " + exc.getMessage();
				}	
				txt.setText(str_number);
				str_number = "";
			}
	}	
	else if(e.getSource()==btn_dot) {
			 str_number+=".";	
			 txt.setText(str_number);
	}
	else if(e.getSource()==btn_del) {
		if (str_number.length() > 0) {
			str_number = str_number.substring(0, str_number.length() - 1);
		}
		txt.setText(str_number);
	} else if(e.getSource()==btn_mema) {
		String onScreen = txt.getText();
		int eqPos = onScreen.indexOf("=");
		if(eqPos > -1) {
			try {
				cli.setVar("M", Double.parseDouble(onScreen.substring(eqPos + 1)));
				lastPressed = "memA";
			} catch (Exception p) {
				System.out.println(p.getMessage());
			}
		}

	} else if(e.getSource()==btn_memc) {
		cli.setVar("M", 0.0);
	} else if(e.getSource()==btn_memr) {
		double mem = cli.getVarValue("M");
		if (mem != 0.0) {
			if (mem < 0) {
				str_number+= "(0" + mem + ")";
			} else {
				str_number+= mem;	
			}
			txt.setText(str_number);
		}
		
	} else if(e.getSource()==btn_clr) {
		txt.setText("");
	}
}
	
	public static void main(String[] args) {
		new Calculator2();
	
	}
}

