/* Miguel Tirado 
   Professor Wang 
   CSC 35 lab 6
   sec 04  */ 
package MyStackQueue;
public class Stack {
	private int topIndx = -1, capacity = 100;
	private Object S[];
	public Stack() { S = new Object[capacity]; }
	public boolean isEmpty() { return topIndx<0; }
	public boolean isFull() { return topIndx==capacity-1; }
	public void push(Object Element) { S[++topIndx] = Element; }
	public Object pop() {
		Object Element;
		Element =  S[topIndx];
		S[topIndx--] = null;
		return Element;
	}
	public Object top() { return S[topIndx]; }

}