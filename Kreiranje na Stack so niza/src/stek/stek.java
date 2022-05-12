package stek;

import java.util.NoSuchElementException;
interface Stack<E> {
	 public boolean isEmpty ();
	 public E peek ();
	 public void clear ();
	 public void push (E x);
	 public E pop ();
	}
class ArrayStack<E> implements Stack<E> {
	private E [] elements;
	private int depth;
	public ArrayStack(int maxDepth) {
		elements=(E[]) new Object[maxDepth];
		depth=0;
	}
	public boolean isEmpty() {
		if(depth==0) {
			return true;
		}else {
			return false;
		}
	}
	public E peek() {
		if(depth==0) {
			throw new NoSuchElementException();
		}
		return elements[depth-1];
	}
	public void clear() {
		for (int i=0;i<depth;i++) {
			elements[i]=null;
		}
		depth=0;
	}
	public void push(E element) {
		elements[depth]=element;
		depth++;
	}
	public E pop() {
		if(depth==0) {
			throw new NoSuchElementException();
		}else {
			E pom=elements[depth-1];
			elements[depth-1]=null;
			depth--;
			return pom;
		}
	}
}
public class stek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
