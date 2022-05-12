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
class SLLNode<E>{
	E element;
	SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
				this.element=element;
				this.succ=succ;
	}
}
class LinkedQueue<E> implements Queue<E>{
	SLLNode<E> front;
	SLLNode<E>rear;
	int lenght;
	public  LinkedQueue() {
		front=rear=null;
		lenght=0;
	}
	

	@Override
	public boolean isEmpty() {
	
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
		if(rear!=null) {
			rear.succ=nov;
			rear=nov;
		}else {
			front=rear=nov;
		}
		lenght++;
		
	}

	@Override
	public E dequeue() {
		if(front==null) {
			throw new NoSuchElementException();
		}
			E pom=front.element;
			lenght--;
			front=front.succ;
			if (front == null) rear = null;
			
		return pom;
	}
	
}
public class Revrst {
	public static void revrst(LinkedQueue<String> redica,int n,int k) {
		String [] pom=new String [n];
		for (int i=0;i<k;i++) {
			String el=redica.dequeue();
			pom[i]=el;
		}
		for (int i=k;i<n;i++) {
			String el=redica.dequeue();
			pom[i]=el;
		}
		for (int i=k-1;i>=0;i--) {
			redica.enqueue(pom[i]);
		}
		for (int i=k;i<n;i++) {
			redica.enqueue(pom[i]);
		}
		for (int i=0;i<n;i++) {
			String el=redica.dequeue();
			System.out.println(el);
		}
		
		
		
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());	
		int K=Integer.parseInt(input.readLine());
		String srt=input.readLine();
		String [] niza=srt.split(" ");
		LinkedQueue<String> redica=new LinkedQueue<String>();
		for (int i=0;i<N;i++) {
			redica.enqueue(niza[i]);
		}
		revrst(redica,N,K);
		

	}

}
