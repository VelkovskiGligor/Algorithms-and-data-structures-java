package XML;

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
class LinkedStack<E> implements Stack<E>{
	SLLNode<E>  top;
	public  LinkedStack() {
		top=null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (top==null);
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(top==null)
			throw new NoSuchElementException();
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
		if(top==null)
			throw new NoSuchElementException();
		E pom=top.element;
		top=top.succ;
		return pom;
	}
	
}
public class XML {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		LinkedStack<String> stek=new LinkedStack<String>();
		BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		for (int i=0;i<N;i++) {
			String tag=input.readLine();
		
			if( tag.charAt(0)=='[' && tag.charAt(1) != '/' && tag.charAt(tag.length()-1)==']') {
				stek.push(tag);
			} else if(tag.charAt(0)=='[' && tag.charAt(1)=='/' && tag.charAt(tag.length()-1)==']') {
					if(stek.isEmpty()) {
						System.out.println("0");
						return;
					}
					String imezatvoren=tag.substring(2, tag.length()-1);
					//System.out.println("imezatvoren: "+imezatvoren);
					String imeotvoren=stek.pop().substring(1, tag.length()-2);
					//System.out.println("Ime otvoren: "+imeotvoren);
					if(!imezatvoren.equals(imeotvoren)) {
						System.out.println("0");
						return;
					}
			}
			
		}	
		if(stek.isEmpty()) {
			System.err.println("1");
		}else {
			System.err.println("0");
		}

	}

}
