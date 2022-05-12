package paket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
 interface Stack<E> {
	 public boolean isEmpty ();
	 public E peek ();
	 public void clear ();
	 public void push (E x);
	 public E pop ();
	}
class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

class LinkedStack<E> implements Stack<E>{
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
public class convert {
	public static void converts(int broj) {
		LinkedStack<Integer> stek=new LinkedStack<Integer>();
		while (broj!=0) {
			if(broj%2==0) {
				stek.push(0);
			}else {
				stek.push(1);
			}
			broj=broj/2;
		}
		while(!stek.isEmpty()) {
			int i=stek.pop();
			System.out.println(i);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
		Integer broj=Integer.parseInt(input.readLine());
		converts(broj);
	}

}
