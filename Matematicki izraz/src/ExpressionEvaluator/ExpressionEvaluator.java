package ExpressionEvaluator;
import java.io.*;
import java.util.NoSuchElementException;

interface Stack<E> {
	 public boolean isEmpty ();
	 public E peek ();
	 public void clear ();
	 public void push (E x);
	 public E pop ();
	}
class SLLNode<E>{
	E element;
	SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
}
}
class LinkedStack <E> implements Stack<E>{
	private SLLNode<E> top;
	public LinkedStack() {
		top=null;
	}
	public boolean isEmpty() {
		if(top==null) {
			return true;
		}else {
			return false;
		}
	}
	public E peek() {
		if(top==null) {
			throw new NoSuchElementException();

		}else {
			return top.element;
		}
	}
	public void clear() {
		top=null;
	}
	public void push(E element) {
		SLLNode<E> nov=new SLLNode<E> (element,top);
		top=nov;
	}
	public E pop() {
		if(top==null) {
			throw new NoSuchElementException();
		}
		E pom=top.element;
		top=top.succ;
		return pom;
	}
}



public class ExpressionEvaluator {

	public static int evaluateExpression(String izraz){
		LinkedStack <Character> stek =new LinkedStack<Character>();
		for (int i=0;i<izraz.length();i++) {
			char znak=izraz.charAt(i);
			if(znak=='*' || znak=='+') {
				if(znak=='+') {
					stek.push(znak);
				}else {
					char predhoden=stek.pop();
					String predhodenn=predhoden+"";
					if(i+1==izraz.length()) {
						break;
					}
					 char nareden=izraz.charAt(i+1);
					
					 String naredenn=nareden+"";
						i++;
						int pom1=Integer.parseInt(predhodenn);
						int pom2=Integer.parseInt(naredenn);
						int sum=pom1*pom2;
						stek.push((char)sum);				
				}
				
			}else{
				stek.push(znak);
			}
			
		}
		int suma=0;
		while(!stek.isEmpty()) {
			char prvv=stek.pop();
			
			char znak =stek.pop();//se vadin znako
			
			char vtorr=stek.pop();
			int prv=Integer.parseInt(prvv+"");
			int vtor=Integer.parseInt(vtorr+"");
			suma=suma+prv+vtor;
		}
		
	    return suma;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		
		String izraz=input.readLine();
		
		
		System.out.println(evaluateExpression(izraz));
	}

}