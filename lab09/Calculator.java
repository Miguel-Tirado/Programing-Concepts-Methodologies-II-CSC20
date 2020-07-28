/* Miguel Tirado 
   CSC 20 Lab 09
   Sec: 
   Date: 11/07/2017  */ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calculator implements ActionListener  {  
static String[] calcB = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","+","=","C"};     
static boolean newNumber = true; 
static double opnd1 = 0;
static double opnd2 = 0; 
static double res = 0;
static char operator = ' ';
static JTextField tf = new JTextField("0",JTextField.CENTER);
                           
	public static void main(String[] args) {
      JButton[] Button = new JButton[calcB.length];
		JFrame frm = new JFrame("Miguels Calculator");
      JLabel label = new JLabel("CSC 20 lab08",JLabel.CENTER);
      //JTextField tf = new JTextField("0",JTextField.CENTER);
      JPanel panelA = new JPanel(new GridLayout(0,1));
            panelA.add(label);
		Container contentPane = frm.getContentPane();
		contentPane.setLayout(new GridLayout(0, 1));
      contentPane.add(panelA);
      tf.setHorizontalAlignment(JTextField.RIGHT);
      contentPane.add(tf);
		JPanel panelB = new JPanel(new GridLayout(0,4,2,2));
      JPanel panelC = new JPanel(new GridLayout(0,4,2,2));
      JPanel panelD = new JPanel(new GridLayout(0,4,2,2));
      JPanel panelE = new JPanel(new GridLayout(1,2,2,2));
      JPanel panelF = new JPanel(new GridLayout(1,2,2,2));
      JPanel panelG = new JPanel(new GridLayout(0,2,2,2));
      ActionListener AL = new Calculator();                 // this is the action listener 
      for (int i=0; i<=3; ++i) {    
			panelB.add(Button[i] = new JButton(""+calcB[i]));
         Button[i].addActionListener(AL);
      }
      contentPane.add(panelB);
      for (int i=4; i<=7; ++i) {
			panelC.add(Button[i] = new JButton(""+calcB[i]));
         Button[i].addActionListener(AL);
      }
      contentPane.add(panelC);
      for (int i=8; i<=11; ++i) {
			panelD.add(Button[i] = new JButton(""+calcB[i]));
         Button[i].addActionListener(AL);
      }
      contentPane.add(panelD);
		panelE.add(Button[12] = new JButton(""+calcB[12]));
      Button[12].addActionListener(AL);
  
      for (int i=13; i<=14; ++i) {
			panelF.add(Button[i] = new JButton(""+calcB[i]));
         Button[i].addActionListener(AL);
      }
      panelE.add(panelF);
      contentPane.add(panelE);
      for (int i=15; i<=16; ++i) {
			panelG.add(Button[i] = new JButton(""+calcB[i]));
         Button[i].addActionListener(AL);
      }
      contentPane.add(panelG);
		frm.pack();
		frm.setSize(318,220);
		//frm.setResizable(false);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}  
   public void actionPerformed(ActionEvent e) {
      char c = e.getActionCommand().charAt(0);
      switch(c) {
         case '.':
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
         if (newNumber) {
            tf.setText(""+c); newNumber = false;
            }else  tf.setText(tf.getText()+c);
         
         return;
         case '-':
         case '*':
         case '/':                                                 
         case '+': opnd1 = Double.parseDouble(tf.getText());
         newNumber = true;
         operator = c;
         return;
         case 'C': tf.setText("" + 0);
         return;
         case '=': opnd2 = Double.parseDouble(tf.getText());
         switch (operator) {
         case '/': res = opnd1 / opnd2; break;
         case '*': res = opnd1 * opnd2; break;
         case '-': res = opnd1 - opnd2; break;
         case '+': res = opnd1 + opnd2; break;
         }
         //Display res in the textfield;
         tf.setText("" + res);
         newNumber = true;
         return;
     }
 }
   
}
