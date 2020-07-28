
/* Miguel Tirado 
   CSC 20 Lab 08
   Sec: 
   Date: 10/31/2017  */ 
import javax.swing.*;
import java.awt.*;
public class Calculator {  
static String[] calcB = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","+","=","C"};                              
	public static void main(String[] args) {
		JFrame frm = new JFrame("Miguels Calculator");
      JLabel label = new JLabel("CSC 20 lab08",JLabel.CENTER);
      JTextField text = new JTextField("0",JTextField.CENTER);
      JPanel panelA = new JPanel(new GridLayout(0,1));
      panelA.add(label);
		Container contentPane = frm.getContentPane();
		contentPane.setLayout(new GridLayout(0, 1));
      contentPane.add(panelA);
      text.setHorizontalAlignment(JTextField.RIGHT);
      contentPane.add(text);
		JPanel panelB = new JPanel(new GridLayout(0,4,2,2));
      JPanel panelC = new JPanel(new GridLayout(0,4,2,2));
      JPanel panelD = new JPanel(new GridLayout(0,4,2,2));
      JPanel panelE = new JPanel(new GridLayout(1,2,2,2));
      JPanel panelF = new JPanel(new GridLayout(1,2,2,2));
      JPanel panelG = new JPanel(new GridLayout(0,2,2,2));
      for (int i=0; i<=3; ++i) {    
			panelB.add(new JButton(""+calcB[i]));
      }
      contentPane.add(panelB);
      for (int i=4; i<=7; ++i) {
			panelC.add(new JButton(""+calcB[i]));
      }
      contentPane.add(panelC);
      for (int i=8; i<=11; ++i) {
			panelD.add(new JButton(""+calcB[i]));
      }
      contentPane.add(panelD);
		panelE.add(new JButton(""+calcB[12]));
  
      for (int i=13; i<=14; ++i) {
			panelF.add(new JButton(""+calcB[i]));
      }
      panelE.add(panelF);
      contentPane.add(panelE);
      for (int i=15; i<=16; ++i) {
			panelG.add(new JButton(""+calcB[i]));
      }
      contentPane.add(panelG);
		frm.pack();
		frm.setSize(318,220);
		//frm.setResizable(false);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}
}
