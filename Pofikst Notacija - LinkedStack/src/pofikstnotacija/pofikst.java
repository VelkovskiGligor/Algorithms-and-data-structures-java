package pofikstnotacija;

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
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}
}
class LinkedStack <E> implements Stack<E>{
	SLLNode<E> top;
	public LinkedStack() {
		top=null;
	}
	@Override
	public boolean isEmpty() {
		if(top==null) {
		return true;
		}
		return false;
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
		E pom=top.element;
		top=top.succ;
		return pom;
	}
	
}
public class pofikst {
	public static int sum(String s) {
		LinkedStack<Integer> stek=new LinkedStack<Integer> ();
		for (int i=0;i<s.length();i++) {
			char znak=s.charAt(i);
			if( znak == '0' || znak == '1' ||znak == '2'||znak == '3' ||znak == '4' ||znak == '5' ||znak == '6' ||znak == '7' ||znak == '8' ||znak == '9' ) {
				String str=znak+"";
				int pom=Integer.parseInt(str);
				System.out.println("Stava: "+pom);
				stek.push(pom);
			}else if(znak =='+' || znak=='-' || znak=='*' || znak=='/') {
				int vtor=stek.pop();
				int prv=stek.pop();
				if(znak == '+') {
					stek.push(prv+vtor);
				}
				else if(znak == '-') {
					stek.push(prv-vtor);
				}else if(znak == '*') {
					stek.push(prv*vtor);
				}else if(znak == '/') {
					stek.push(prv/vtor);
				}
			}
				
		}
		
		return stek.pop();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Vnesete go izrazot");
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String izraz=input.readLine();
		int presmetaj=sum(izraz);
		System.out.println("Zbirot e "+presmetaj);
	}

}
