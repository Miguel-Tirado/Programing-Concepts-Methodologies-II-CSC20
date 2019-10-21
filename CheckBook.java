/* Miguel Tirado 
   Professor Wang
   CSC 20
   Project 
   12/06/2017  */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class Transaction implements Serializable { String Date;int CheckNo; String TransactionDes; double Amount;int Type;} 
public class CheckBook  implements ActionListener {
static String[] buttonL = {"Create a New Account","Load Trans from a file", "Add New Transactions","Search Transactions","Sort Transactions","View/Delete Transaction","Save trans to a file","Exit"};
/////////////////////////////////////////////////////////// Box 1 
static JButton topButtons[] = new JButton[buttonL.length];
static JScrollPane scrollPane;
static JScrollPane scrollPane2; 
static JTextField tf = new JTextField("",10);
static JTextField tf2 = new JTextField("",10);
////////////////////////////////////////////////////////// Box 2
static JTextField tf3 = new JTextField("",10);                     
static JTextField tf4 = new JTextField("",10); 
static JButton box2A = new JButton();  
static JButton box2B = new JButton(); 
////////////////////////////////////////////////////////// Box 3
static  JTextField tf5 = new JTextField("",10);
static JButton box3A = new JButton(); 
static JButton box3B = new JButton(); 
/////////////////////////////////////////////////////////Box 4
static String[] tNames = {"Date","Trans. Type","Check No","Trans.Descroption","Payment/Debit(-)","Depsoit/Credit(+)","Balance"};
static String[] types = {"Deposit","Automatic Deposit", "ATM Withdrawl","Check","Debit"};
static JComboBox <String> bankTypes = new JComboBox <String>(types); 
static JTextField tf6 = new JTextField("",20); 
static JTextField tf7 = new JTextField("",20);
static JTextField tf8 = new JTextField("",20);
static JTextField tfA = new JTextField("",20);
static JTextField tfB = new JTextField("",20);
static JButton box4A = new JButton();
static JButton box4B = new JButton();  
/////////////////////////////////////////////////////////Box 5  
static  JTextField tf9 = new JTextField("",20);
static JButton box5A = new JButton();
static JButton box5B = new JButton(); 
/////////////////////////////////////////////////////////Box 6
static JButton box6A = new JButton();
static JButton box6B = new JButton(); 
////////////////////////////////////////////////////////Box 7  
static JButton box7A = new JButton();
static JButton box7B = new JButton();
/////////////////////////////////////////////////////////// 
static Transaction[] TA = new Transaction[100];
static int Tnum = 0;
static double Balance = 0;
static JTable abtable;
///////////////////////////////////////////////////////
static CardLayout contentPaneLayout;
static Container contentPane;
 	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
      for (int i = 0; i <8;i++) {
         if (source == topButtons[i]) {
            switch(i) {
               case 0: contentPaneLayout.show(contentPane, "Card 2"); 
               return;
               case 1:
                contentPaneLayout.show(contentPane, "Card 3");
               return;
               case 2: contentPaneLayout.show(contentPane, "Card 4");
               return;
               case 3: contentPaneLayout.show(contentPane, "Card 5");
               return;
               case 4: contentPaneLayout.show(contentPane, "Card 6");
               return;
               case 5:
                  Balance = 0; 
                  String data[][] = new String[Tnum][7];
                  for(int j = 0; j< Tnum;++j) {
                     data[j][0] = TA[j].Date;
                     data[j][1] = types[TA[j].Type];
                     data[j][2] = "" + TA[j].CheckNo;
                     if (TA[j].Type == 3) {
                        data[j][3] = TA[j].TransactionDes;
   
                     }else { data[j][3] = "";}
                     if(TA[j].Type <= 1) {
                         data[j][4] = "";
                         data[j][5] = "" +TA[j].Amount;
                         Balance += TA[j].Amount;
                     } else {                 
                        data[j][4] = "" + TA[j].Amount;
                        data[j][5] = "";
                        Balance -= TA[j].Amount;
   
                     } 
                     data[j][6] = "" +Balance;
                  }
                  abtable = new JTable(data,tNames); 
                  JScrollPane tmp = new JScrollPane(abtable); 
                  scrollPane2.setViewport(tmp.getViewport());
                   contentPaneLayout.show(contentPane, "Card 7");
               return;
               case 6: 	
               try {	FileOutputStream fos = new FileOutputStream (tf.getText(), false);
               			ObjectOutputStream oos = new ObjectOutputStream(fos);
               		 for(int j = 0; j< Tnum;++j) {
                        oos.writeObject(TA[j]); 
                      }
  
                        oos.close();
               	  } catch(FileNotFoundException ee){ System.out.println(ee.toString());
               		  } catch(IOException ee){ ee.printStackTrace();}
                     return;
                     case 7:
                        System.exit(0);
               	}
            }
         }
         if (source == box3A) {
            try {
				FileInputStream fis = new FileInputStream (tf5.getText()); //ghere
				ObjectInputStream ois = new ObjectInputStream(fis);
            Tnum = 0;
      				while (true) {
      					Transaction t = (Transaction) ois.readObject();
      					TA[Tnum++] = t;
      				}
   			   } catch(EOFException ee){                            // i changed it e to ee 
   			     } catch(Exception ee){ ee.printStackTrace();
   		          } 
         }     
      if (source== box2B ||source == box3B || source == box4B || source == box5B || source == box6B || source == box7B  ) {
         contentPaneLayout.show(contentPane, "Card 1");
         return;       
      }
      if(source == box2A) {
         tf.setText(tf3.getText());
         tf2.setText(tf4.getText());
         Transaction t = new Transaction();
         t.TransactionDes = "Initial Balance";
         t.Amount = (double)Double.parseDouble(tf4.getText());
         TA[0] = t;
         Tnum = 1; 
         contentPaneLayout.show(contentPane, "Card 1");
         return;
      }
      if(source == box4A){
         Transaction t = new Transaction();
         t.Date = tf6.getText();
         t.Type = bankTypes.getSelectedIndex();
         if(t.Type == 3) {
            t.CheckNo = (int)Double.parseDouble(tfA.getText());
         }
         t.TransactionDes = tf7.getText();
         if( t.Type <= 1) {
            t.Amount = (double)Double.parseDouble(tf8.getText());
         }
         else {
            t.Amount = (double)Double.parseDouble(tfB.getText());
         }
         TA[Tnum++] = t;
         tf6.setText("");
         tf7.setText("");
         tfA.setText("");
         tf8.setText("");
         tfB.setText("");
         return;
      }
      if(source == box7A) {
         int d = abtable.getSelectedRow();
         for (int i = d+1; i< Tnum; ++i) {
            TA[i-1] = TA[i];
         }
         Tnum--;
           Balance = 0; 
                  String data[][] = new String[Tnum][7];
                  for(int j = 0; j< Tnum;++j) {
                     data[j][0] = TA[j].Date;
                     data[j][1] = types[TA[j].Type];
                     data[j][2] = "" + TA[j].CheckNo;
                     if (TA[j].Type == 3) {
                        data[j][3] = TA[j].TransactionDes;
   
                     }else { data[j][3] = "";}
                     if(TA[j].Type <= 1) {
                         data[j][4] = "";
                         data[j][5] = "" +TA[j].Amount;
                         Balance += TA[j].Amount;
                     } else {                 
                        data[j][4] = "" + TA[j].Amount;
                        data[j][5] = "";
                        Balance -= TA[j].Amount;   
                     } 
                     data[j][6] = "" +Balance;
                  }
                  abtable = new JTable(data,tNames); 
                  JScrollPane tmp = new JScrollPane(abtable); 
                  scrollPane2.setViewport(tmp.getViewport());
          return; // test
      }
      if(source == box3A) {
         try {
				FileInputStream fis = new FileInputStream (tf5.getText());
				ObjectInputStream ois = new ObjectInputStream(fis);
            Tnum = 0;
            Balance = 0;
				while (true) {
					Transaction T = (Transaction) ois.readObject();
               TA[Tnum++] = T;
               if (T.Type <= 1){
                  Balance+= T.Amount;
               }else {
                  Balance -= T.Amount;
               }					
				}
			} catch(EOFException ee){
			} catch(Exception ee){ ee.printStackTrace();
		   } 
         tf.setText(tf5.getText());
         tf2.setText("" + Balance);
         contentPaneLayout.show(contentPane, "Card 1");   
         return; //test            
      }
      
     }            
	public static void main(String[] args) {
      ActionListener AL = new CheckBook() ;
		JFrame frm = new JFrame("Checkbook");
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      JLabel title1 = new JLabel("Use The Buttons below to Manage Transactios",JLabel.CENTER);           //Box 1 Label
      JLabel title2 = new JLabel("Create a new Account",JLabel.CENTER);                                  // BOX 2 Label
      JLabel title3 = new JLabel("Load Trasaction From a File",JLabel.CENTER);                           //BOX 3 Label 
      JLabel title5 = new JLabel("Search Transactions by TransactionDate/Type/Check No./Description");   //box 5
      JLabel title6 = new JLabel("Sort Transactions",JLabel.CENTER);                                     // box 6
      JLabel title7 = new JLabel("Transactions Currently in The Checkbook",JLabel.CENTER);               //box 7
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	   contentPane = frm.getContentPane();
      contentPane.setLayout(contentPaneLayout=new CardLayout());
      JPanel box1 = new JPanel(new BorderLayout(0,1));
      box1.add(title1,BorderLayout.NORTH);
      JPanel panel2 = new JPanel (new GridLayout(2,4)); 
      JPanel panel3 = new JPanel (new FlowLayout());                 // Test center Panel Box 1
      JLabel label2 = new JLabel("Account Name:",JLabel.CENTER);     // account name Box 1
      JLabel label3 = new JLabel("Balance:",JLabel.CENTER);          // Balance Box 1
      //JTextField tf = new JTextField("",10);                       // account text Box 1
      //JTextField tf2 = new JTextField("",10);                      // Balance text Box 1
      panel3.add(label2);                                            //Box 1
      panel3.add(tf);                                                //Box 1
      panel3.add(label3);                                            //Box 1
      panel3.add(tf2);                                               //Box 1
      for (int i=0; i<buttonL.length; ++i) {    
			panel2.add( topButtons[i] = new JButton(""+buttonL[i]));
         topButtons[i].addActionListener(AL); 
      }
      box1.add(panel3,BorderLayout.CENTER);                         
      box1.add(panel2,BorderLayout.SOUTH);
      contentPane.add("Card 1", box1);
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      JPanel box2 = new JPanel(new GridLayout(0,1));                                      
      JLabel label5 = new JLabel("Account Name:",JLabel.CENTER);   
      JLabel label6 = new JLabel("Initial Balance:",JLabel.CENTER); 
      JPanel panel5 = new JPanel(new FlowLayout());                 
      JPanel panel6 = new JPanel(new GridLayout(0,2));               
      JPanel panelA = new JPanel();
      JPanel panelB = new JPanel(new FlowLayout());
      JPanel panelC = new JPanel();
      box2.add(title2,panel5);  
      panel5.add(label5);                                           
      panel5.add(tf3);                                               
      panelB.add(label6);
      panelB.add(tf4);
      box2.add(panel5);
      box2.add(panelA);
      box2.add(panelB);
      box2.add(panelC);
      panel6.add(box2A = new JButton("Create"));
      box2A.addActionListener(AL);                //CHnage here 
      panel6.add(box2B = new JButton("Cancel"));
      box2B.addActionListener(AL);                //change here 
      //box2.add(panel6,BorderLayout.SOUTH);  
      box2.add(panel6);
      contentPane.add("Card 2", box2);                                   
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      JPanel box3 = new JPanel(new BorderLayout(0,1));            
      box3.add(title3,BorderLayout.NORTH);                                            
      JLabel label8 = new JLabel("Account Name:",JLabel.CENTER);     
      JPanel panel8 = new JPanel(new FlowLayout());
      JPanel panel9 = new JPanel(new GridLayout(0,2));
      panel8.add(label8);
      panel8.add(tf5);
      box3.add(panel8,BorderLayout.CENTER);
      panel9.add(box3A = new JButton("Load"));
      box3A.addActionListener(AL);  
      panel9.add(box3B = new JButton("Cancel"));
      box3B.addActionListener(AL); 
      box3.add(panel9,BorderLayout.SOUTH);
      contentPane.add("Card 3", box3); 
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
      JPanel box4 = new JPanel(new BorderLayout());            //Box 4 
      JPanel panel10 = new JPanel(new GridLayout(0,2));              //Box 4 
      JPanel panel11 = new JPanel(new GridLayout(0,2));              //box 4
      JLabel label9 = new JLabel("Date:",JLabel.RIGHT);
      JLabel label10 = new JLabel("Trans. Type",JLabel.RIGHT);
      JLabel label11 = new JLabel("Check No.",JLabel.RIGHT);
      JLabel label12 = new JLabel("Trans. Description",JLabel.RIGHT);
      JLabel label13 = new JLabel("PaymentDebit(-)",JLabel.RIGHT);
      JLabel label14 = new JLabel("DepositCredit(+)",JLabel.RIGHT);
      JPanel panelD = new JPanel();
      JPanel panelE = new JPanel();
      //JComboBox <String> bankTypes = new JComboBox <String>(types);    // if u run this on athena it needs the <String> out, old compiler wont work on this   
      panel10.add(label9);
      panel10.add(tf6);
      panel10.add(label10);
      panel10.add(bankTypes);
      panel10.add(label11);
      //panel10.add(panelD);
      panel10.add(tfA);
      panel10.add(label12);
      panel10.add(tf7);
      panel10.add(label13);
      //panel10.add(panelE);
      panel10.add(tfB);
      panel10.add(label14);
      panel10.add(tf8);
      panel11.add(box4A = new JButton("Save new Trasaction"));
      box4A.addActionListener(AL); 
      panel11.add(box4B = new JButton("Top Menu"));
      box4B.addActionListener(AL); 
      box4.add(panel10,BorderLayout.CENTER);
      box4.add(panel11,BorderLayout.SOUTH);
      contentPane.add("Card 4", box4); 
      //////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////
      JPanel box5 = new JPanel (new BorderLayout(0,1));                                       
      JPanel panel20 = new JPanel( new GridLayout(0,1));
      JPanel panel21 = new JPanel( new FlowLayout());
      JPanel panel22 = new JPanel(new FlowLayout());
      JLabel label21 = new JLabel("Search String:",JLabel.CENTER);     
      panel22.add(label21);
      panel22.add(tf9);
      panel20.add(panel22);
      panel21.add( box5A = new JButton("Search"));
      box5A.addActionListener(AL); 
      panel21.add( box5B = new JButton("Top Menu"));
      box5B.addActionListener(AL); 
      panel20.add(panel21);
      scrollPane = new JScrollPane();             
      box5.add(title5,BorderLayout.NORTH);
      box5.add(scrollPane,BorderLayout.CENTER);   
      box5.add(panel20,BorderLayout.SOUTH);
      contentPane.add("Card 5", box5); 
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      JPanel box6 = new JPanel (new BorderLayout(0,1));                     
      JPanel panel23 = new JPanel(new FlowLayout());
      JPanel panel24 = new JPanel(new GridLayout(0,2));
      JRadioButton sort = new JRadioButton("By Type");
      JRadioButton sort2 = new JRadioButton("By Date");
      panel23.add(sort);
      panel23.add(sort2);
      panel24.add(box6A = new JButton("Sort"));
      box6A.addActionListener(AL); 
      panel24.add(box6B = new JButton("Top Menu"));
      box6B.addActionListener(AL); 
      box6.add(title6,BorderLayout.NORTH);
      box6.add(panel23,BorderLayout.CENTER);
      box6.add(panel24,BorderLayout.SOUTH);
      contentPane.add("Card 6", box6);
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      String[] tNames2 = {"Date","Trans. Type","Check No","Trans.Descroption","Payment/Debit(-)","Depsoit/Credit(+)","Balance"};
      JPanel box7 = new JPanel (new BorderLayout(0,1));
      JPanel panel25 = new JPanel(new GridLayout(0,1));
      JPanel panel26 = new JPanel(new FlowLayout());
      JPanel panel27 = new JPanel(new FlowLayout());
      box7.add(title7,BorderLayout.NORTH);
      scrollPane2 = new JScrollPane();
      box7.add(scrollPane2,BorderLayout.CENTER); 
      panel26.add(box7A =new JButton("Delete Selected"));
      box7A.addActionListener(AL); 
      panel26.add(box7B =new JButton("Top Menu"));
      box7B.addActionListener(AL); 
      box7.add(panel26,BorderLayout.SOUTH);
      contentPane.add("Card 7", box7);
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //This Section controls the Display of each window or box 
      //contentPane.add(box1);
      contentPaneLayout. show(contentPane, "Card 1");                         
      //contentPane.add(box2);
      //contentPaneLayout. show(contentPane, "Card 2");                         
      //contentPane.add(box3);
      //contentPaneLayout. show(contentPane, "Card 3");                          
      //contentPane.add(box4);
      //contentPaneLayout. show(contentPane, "Card 4");                         
      //contentPane.add(box5);
      //contentPaneLayout. show(contentPane, "Card 5"); 
      //contentPane.add(box6);
      //contentPaneLayout. show(contentPane, "Card 6"); 
      //contentPane.add(box7);
      //contentPaneLayout. show(contentPane, "Card 7"); 
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		frm.pack();
		frm.setSize(318,220);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}
}