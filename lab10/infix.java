/* Miguel Tirado 
   CSC 20 lab 10
   sec: 04 
   10/3/2017 */ 
import java.io.*;
import java.util.*;

public class infix {
	static LinkedList <Token> infixToPostfix(String s) throws Exception {
         Stack <Operator> theStack = new Stack <Operator>();
      LinkedList <Token> theQueue = new LinkedList <Token>();
      theStack.push(new Operator('#'));
      Tokenizer T = new Tokenizer(s);
      while (T.moreTokens()) {
         Token Tkn = T.nextToken();
         if (Tkn instanceof Operand) {
            theQueue.addLast(Tkn);
         } else { +
            Operator Opr = (Operator)Tkn;
            if (Opr.operator=='(') {
               theStack.push(Opr);
            }
            else if (Opr.operator == ')') {
            try{
               while ((theStack.peek()).operator!= '(') {               
                  theQueue.addLast(theStack.pop());
               } theStack.pop();
            } catch(Exception e) {
                 throw new infixException(errorType.ExcessRightParenthesis);
			   }
             
            }else {
               while ((theStack.peek()).precedence()>= Opr.precedence()) {                 
                  theQueue.addLast(theStack.pop());
               } theStack.push(Opr);   
            }
          }
    }
    while ((theStack.peek()).operator!= '#') {
      if((theStack.peek()).operator== '(') {
         throw new infixException(errorType.ExcessLeftParenthesis);
      }            
      theQueue.addLast(theStack.pop());             
    }     
    return theQueue;     
	}

	static int evaluePostfix(LinkedList <Token> Post) throws Exception {
      Stack<Operand> theStack = new Stack <Operand>();
      int result = 0;
      int opnd1,opnd2;
      while (!Post.isEmpty()) {
         
            Token Tkn = Post.removeFirst();
            if (Tkn instanceof Operand) {
               theStack.push((Operand)Tkn);
            }else {
               Operator Opr = (Operator)Tkn;
               try {
                  Operand opind2 = theStack.pop();
                  Operand opind1 = theStack.pop();
                  opnd2 = opind2.operand;
                  opnd1 = opind1.operand;
               } catch (Exception e) {
                  //operators
                  throw new infixException(errorType.ExcessOperator);
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
         //operands
         throw new infixException(errorType.ExcessOperand);
      }
   
   return result;
	}

	public static void main(String[] args) throws IOException {
	LinkedList <Token> Post;
	while(true) {
      try {
   		System.out.print("Enter infix: ");
   		System.out.flush();
   		InputStreamReader isr = new InputStreamReader(System.in);
   		BufferedReader br = new BufferedReader(isr);
   		String s = br.readLine();
   		if ( s.equals("") ) break;
   		Post = infixToPostfix(s);
   		//System.out.println("Postfix is " + Post.toString() + '\n');
         System.out.println("Postfix is ");
         for(Token A: Post){
            System.out.print(A + " ");
         }
   		int result = evaluePostfix(Post);
   		System.out.println("Result is " + result + '\n');
      }catch(Exception e) {
         System.out.println("\n*****"+e.toString()+"*****\n");
      }
	}
  }
}
enum errorType { ExcessLeftParenthesis, ExcessRightParenthesis, ExcessOperator, ExcessOperand};
class infixException extends Exception {
private errorType etype;
public infixException(errorType et) { // constructor
etype = et;
}
public String toString() {
return etype.name();
}
}
