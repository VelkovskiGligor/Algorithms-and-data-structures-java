package stek;

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
public class stek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
