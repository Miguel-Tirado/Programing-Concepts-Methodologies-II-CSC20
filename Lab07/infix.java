/* Miguel Tirado 
   CSC 20 lab 7
   sec: 04 
   10/3/2017 */ 
import java.io.*;
import MyStackQueue.*;
class TooManyRight extends Exception {
	public String toString () { return "****** Too Many ) ******";}
}
class TooManyLeft extends Exception {
	public String toString () { return "******* Too Many ( *****";}
}

class TooManyOperands extends Exception {
	public String toString () { return "**** Too Many Operands ***";}
}
class TooManyOperators extends Exception {
   public String toString() {return "**** To Many Operators ****";}
}
public class infix {
	static Queue infixToPostfix(String s) throws Exception {
        Stack theStack = new Stack();
      Queue theQueue = new Queue();
      theStack.push(new Operator('#'));
      Tokenizer T = new Tokenizer(s);
      while (T.moreTokens()) {
         Token Tkn = T.nextToken();
         if (Tkn instanceof Operand) {
            theQueue.enqueue(Tkn);
         } else { 
            Operator Opr = (Operator)Tkn;
            if (Opr.operator=='(') {
               theStack.push(Opr);
            }
            else if (Opr.operator == ')') {
            try{
               while (((Operator)theStack.top()).operator!= '(') {               
                  theQueue.enqueue(theStack.pop());
               } theStack.pop();
            } catch(Exception e) {
                 throw new TooManyRight();
			   }
             
            }else {
               while (((Operator)theStack.top()).precedence()>= Opr.precedence()) {                 
                  theQueue.enqueue(theStack.pop());
               } theStack.push(Opr);   
            }
          }
    }
    while (((Operator)theStack.top()).operator!= '#') {
      if(((Operator)theStack.top()).operator== '(') {
         throw new TooManyLeft();
      }            
      theQueue.enqueue(theStack.pop());             
    }     
    return theQueue;     
	}

	static int evaluePostfix(Queue Post) throws Exception {
      Stack theStack = new Stack();
      int result = 0;
      int opnd1,opnd2;
      while (!Post.isEmpty()) {
         
            Token Tkn = (Token)Post.dequeue();
            if (Tkn instanceof Operand) {
               theStack.push(Tkn);
            }else {
               Operator Opr = (Operator)Tkn;
               try {
                  Operand opind2 = (Operand)theStack.pop();
                  Operand opind1 = (Operand)theStack.pop();
                  opnd2 = opind2.operand;
                  opnd1 = opind1.operand;
               } catch (Exception e) {
                  throw new TooManyOperators();
               }
               
               switch(Opr.operator) {
               case '+': result = opnd1 + opnd2; break;
               case '-': result = opnd1 - opnd2; break;
               case '*': result = opnd1 * opnd2; break;
               case '/': result = opnd1 / opnd2; break;
               }
               theStack.push(new Operand(result));
            }
        
      }
      theStack.pop();
      if (!theStack.isEmpty()) {
         throw new TooManyOperands();
      }
   
   return result;
	}

	public static void main(String[] args) throws IOException {
	Queue Post;
	while(true) {
      try {
   		System.out.print("Enter infix: ");
   		System.out.flush();
   		InputStreamReader isr = new InputStreamReader(System.in);
   		BufferedReader br = new BufferedReader(isr);
   		String s = br.readLine();
   		if ( s.equals("") ) break;
   		Post = infixToPostfix(s);
   		System.out.println("Postfix is " + Post.toString() + '\n');
   		int result = evaluePostfix(Post);
   		System.out.println("Result is " + result + '\n');
      }catch(Exception e) {
         System.out.println("\n*****"+e.toString()+"*****\n");
      }
	}
     }
}
