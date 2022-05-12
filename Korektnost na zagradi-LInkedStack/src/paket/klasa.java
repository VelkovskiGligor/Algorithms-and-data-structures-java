package paket;

import java.util.NoSuchElementException;
import java.io.*;


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
	public SLLNode(E element,SLLNode <E>succ) {
		this.element=element;
		this.succ=succ;
	}
}
class LinkedStack<E> implements Stack<E>{
	private SLLNode<E> top;
	public LinkedStack() {
		top=null;
	}

	@Override
	public boolean isEmpty() {
		
	
		return top==null ? true:false;
	}

	@Override
	public E peek() {
		
		if(top==null) {
			throw new NoSuchElementException();
		}
		return top.element;
	}

	@Override
	public void clear() {
		
		top=null;
		
	}

	@Override
	public void push(E x) {
		
		SLLNode<E> nov =new SLLNode<E>(x,top);
		top=nov;
		
	}

	@Override
	public E pop() {
		if(top==null) {
			throw new NoSuchElementException();
		}
			E pom=top.element;
			top=top.succ;
			return pom;
	}
	
}
public class klasa {
	public static boolean proveri(String s) {
		LinkedStack<Character> stek=new LinkedStack<Character>();
		for (int i =0; i < s.length(); i++){
			char znak=s.charAt(i);
			if( znak == '(' || znak == '[' || znak == '{') {
				stek.push(znak);
			}else if( znak == ')' || znak == ']' || znak == '}') {
				if(!stek.isEmpty()) {
					return false;
				}
				char pom=stek.pop();
					if(!ednakvi(pom,znak)) {
						return false;
					}
			}	
		}
		if(!stek.isEmpty()) {
			return false;
		}
		return true;
	}
	public static boolean ednakvi(char prv,char vtor) {
		if(	(prv=='(' && vtor == ')') || (prv=='{' && vtor == '}') || (prv=='[' && vtor == ']')) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Vnesete go izrazot");
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String izraz=input.readLine();
		if(proveri(izraz)) {
			System.out.println("Izrazot e tocen."); 
		}else {
			System.err.println("Greska!");
		}
	}

}
