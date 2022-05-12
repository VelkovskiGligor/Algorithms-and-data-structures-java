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
public class klasa {
	public static boolean proveri(LinkedStack<Integer> stek) {
		LinkedQueue<Integer> redica=new LinkedQueue<Integer>();
		while(!stek.isEmpty()) {
				int broj=stek.pop();
				if(!stek.isEmpty()) {
					if( broj == (stek.peek()+1) ) {
						redica.enqueue(broj);
					}else {
						return false;
					}
				}else {
					redica.enqueue(broj);
				}
		}
		while(!redica.isEmpty()) {
			stek.push(redica.dequeue());
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			String s=input.readLine();
			String []pom=s.split(" ");
			LinkedStack<Integer> stek=new LinkedStack<Integer>();
			for (int i=0;i<pom.length;i++) {
				stek.push(Integer.parseInt(pom[i]));
			}
			if(proveri(stek)) {
				System.err.println("Posledovatelen");
				while(!stek.isEmpty()) {
					System.err.println(stek.pop());
				}
			}else {
				System.err.println("Ne e posledovatelen");
			}	
	}

	

}
