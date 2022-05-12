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
public class StackShift {
	public static void Shift(LinkedStack<String> stek, int x) {
		// TODO Auto-generated method stub
		LinkedQueue<String>redica=new LinkedQueue<String>();
		while(!stek.isEmpty()) {
			redica.enqueue(stek.pop());
		}
		int size=redica.lenght;
		for(int i=0;i<size-x;i++) {
			stek.push(redica.dequeue());
		
		}
		while(!stek.isEmpty()) {
			redica.enqueue(stek.pop());
		}
		for(int i=0;i<x;i++) {
			redica.enqueue(redica.dequeue());
		}
		while(!redica.isEmpty()) {
			stek.push(redica.dequeue());
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		String [] niza=s.split(" ");
		LinkedStack<String> stek=new LinkedStack<String>();
		for (int i=0;i<niza.length;i++) {
			stek.push(niza[i]);
		}
		
		Shift(stek,Integer.parseInt(input.readLine()));
		while(!stek.isEmpty()) {
			System.err.println(stek.pop());
		}

	}

	

}
