package Revrst;

import java.util.NoSuchElementException;
import java.io.*;


interface Queue<E> {
	 public boolean isEmpty ();
	 public int size ();
	 public E peek ();
	 public void clear ();
	 public void enqueue (E x);
	 public E dequeue ();
	} 
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
class LinkedQueue<E> implements Queue<E>{
	SLLNode<E> front,rear;
	int lenght;
	public LinkedQueue() {
		front=rear=null;
		lenght=0;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (lenght==0);
	}

	@Override
	public int size() {
		
		return lenght;
	}

	@Override
	public E peek() {
		if(lenght==0) {
			throw new NoSuchElementException();
		}
		return front.element;
	}

	@Override
	public void clear() {
		front=rear=null;
		lenght=0;
		
	}

	@Override
	public void enqueue(E x) {
		SLLNode<E> nov=new SLLNode<E>(x,null);
		if(rear!=null) {//Ako ima elementi
		rear.succ=nov;
		rear=nov;	
		}else {//Ako listata e prazna
			front=rear=nov;
		}
		lenght++;
		
		
	}

	@Override
	public E dequeue() {
		if(lenght==0) {
			throw new NoSuchElementException();
		}
		E pom=front.element;
		front=front.succ;
		lenght--;
		if(front==null) {
			rear=null;
		}
		return pom;
	}
	
}
class LinkedStack<E>implements Stack<E>{
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
		E pom=top.element;
		top=top.succ;
		return pom;
	}
	
}
public class revrst {
	public static void revrst(LinkedQueue<String> redica, int n, int k) {
		LinkedStack<String> stek=new LinkedStack<String>();
		for (int i=0;i<k;i++) {
			String el=redica.dequeue();
			stek.push(el);
		}
		while(!stek.isEmpty()) {
			String el=stek.pop();
			redica.enqueue(el);
		}
		for (int i=k;i<n;i++) {
			String el=redica.dequeue();
			redica.enqueue(el);
		}
		for (int i=0;i<n;i++) {
			String el=redica.dequeue();
			System.err.println(el);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		int K=Integer.parseInt(input.readLine());
		LinkedQueue<String> redica=new LinkedQueue<String>();
		String rec=input.readLine();
		String [] niza=rec.split(" ");
		for (int i=0;i<N;i++) {
			redica.enqueue(niza[i]);
		}
		revrst(redica,N,K);
	}

	

}
