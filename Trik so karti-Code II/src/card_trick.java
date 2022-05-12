import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
interface Queue<E>{
	public void clear();
	public boolean isEmpty();
	public int size();
	public E peek();
	public void enqueue(E x);
	public E dequeue();
}
class SLLNode<E>{
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E el,SLLNode<E> succ) {
		this.element=el;
		this.succ=succ;
	}
}
class LinkedQueue<E> implements Queue<E>{
	private SLLNode<E> front;
	private SLLNode<E> rear;
	private int lenght;
	public LinkedQueue() {
		clear();
	}
	@Override
	public void clear() {
		front=rear=null;
		lenght=0;
	}

	@Override
	public boolean isEmpty() {
		return lenght==0;
	}

	@Override
	public int size() {
		return lenght;
	}

	@Override
	public E peek() {
		if(front!=null) {
			return front.element;
		}else {
			throw new NoSuchElementException();
		}
		
	}

	@Override
	public void enqueue(E x) {
		SLLNode<E> nov=new SLLNode<E>(x,null);
		if(rear!=null) {
			rear.succ=nov;
			rear=nov;
			lenght++;
		}else {
			rear=front=nov;
		}
		lenght++;
	}

	@Override
	public E dequeue() {
		if (front!=null) {
			E el=front.element;
			front=front.succ;
			lenght--;
			return el;
		}else {
			throw new NoSuchElementException();
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

public class card_trick {
	public static int count(int N){
		// Vasiot kod tuka
		int brojac=0;
		LinkedStack<Integer> stek=new LinkedStack<Integer>();
		LinkedQueue<Integer> redica=new LinkedQueue<Integer>();
		for(int i=1;i<=51;i++) {
			redica.enqueue(i);
		}
		while(redica.peek()!=N) {
				for (int i=1;i<=7;i++) {
					stek.push(redica.dequeue());
				}
				while(!stek.isEmpty()) {
					redica.enqueue(stek.pop());
					redica.enqueue(redica.dequeue());
				}
				brojac++;
		}
		return brojac;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		System.out.println(count(Integer.parseInt(br.readLine())));
	}

}
