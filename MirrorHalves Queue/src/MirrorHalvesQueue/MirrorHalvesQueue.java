package MirrorHalvesQueue;

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

class LinkedQueue<E> implements Queue<E>{
	SLLNode<E> front;
	SLLNode<E> rear;
	int lenght;
	public LinkedQueue() {
		front=null;
		rear=null;
		lenght=0;
	}
	@Override
	public boolean isEmpty() {
		
		return (lenght == 0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return lenght;
	}

	@Override
	public E peek() {
		if(front==null) {
			throw new NoSuchElementException();
		}
		return front.element;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		front=null;
		rear=null;
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
		if(front==null) {
			throw new NoSuchElementException();

		}else {
			E pom=front.element;
			lenght--;
			front=front.succ;
			if (front == null) rear = null;

			return pom;
		}
		
	}
	
}
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
public class MirrorHalvesQueue {
	public static void MirrorHaves(LinkedQueue<String> redica) {
		int brojac=redica.lenght;
		LinkedStack<String> stek=new LinkedStack<String>();
		for (int i=0;i<brojac/2;i++) {
			String izvadi=redica.dequeue();
			stek.push(izvadi);
			redica.enqueue(izvadi);
		}
		while(!stek.isEmpty()) {
			String izvadi=stek.pop();
			redica.enqueue(izvadi);
		}
		for (int i=brojac/2;i<brojac;i++) {
			String izvadi=redica.dequeue();
			stek.push(izvadi);
			redica.enqueue(izvadi);
		}
		while(!stek.isEmpty()) {
			String izvadi=stek.pop();
			redica.enqueue(izvadi);
		}
		
	}
	public static void main(String[] args) throws IOException {
	BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
	String izraz=input.readLine();
	LinkedQueue<String> redica=new LinkedQueue<String>();
	String [] niza=izraz.split(" ");
	for (int i=0 ; i<niza.length;i++) {
	
		redica.enqueue(niza[i]);
	}
		MirrorHaves(redica);
		while(!redica.isEmpty()) {
			
			System.out.println(redica.dequeue());
		}
	}



}
