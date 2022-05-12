package bukvi;

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
	public SLLNode(E el,SLLNode<E> succ){
		this.element=el;
		this.succ=succ;
	}
}
class LinkedStack<E> implements Stack<E>{
	SLLNode<E> top;
	public LinkedStack() {
		top=null;
	}
	@Override
	public boolean isEmpty() {
		
		return (top==null);
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
		SLLNode<E> nov=new SLLNode<E>(x,top);
		top=nov;
		
	}

	@Override
	public E pop() {
		if(top==null) {
			throw new NoSuchElementException();
		}
		E pom =top.element;
		top=top.succ;
		return pom;
	}
	
}

public class bukvi {
	public static boolean Proveri(String recenica) {
		LinkedStack<Character> stek=new LinkedStack<Character>();
		boolean flag=false;
		for (int i =0; i <recenica.length(); i++) {
			char znak=recenica.charAt(i);
			znak=Character.toUpperCase(znak);
			if(znak== 'S') {
				flag=true;
				stek.push(znak);
			}
			if(znak=='T' && flag) {
				
				stek.push(znak);
				
			}
		}
		if(chack(stek)) {
			return true;
		}else {
			return false;
		}
		
		
		
		
	}
	public static boolean chack(LinkedStack<Character> stek) {
		int brojac=0;
		int glaven=0;
		boolean first=true;
		while(!stek.isEmpty()) {
			char znak=stek.pop();
			if(znak=='T') {	
				brojac++;
			}else if(znak== 'S' ) {
				if(first) {
					first=false;
					glaven=brojac;
					brojac=0;
				}else {
					if(glaven!=brojac) {
						return false;
					}
					brojac=0;
				}
					
				
			}
			
			
		}
		return true;
		
	}
	public static void main(String[] args) throws IOException {
		System.out.println("Vnesete gi bukvite");
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String recenica=input.readLine();
		if(Proveri(recenica)) {
			System.out.println("DA");
		}else {
			System.err.println("NE");
		}
	}

}
