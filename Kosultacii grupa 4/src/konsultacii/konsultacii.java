package konsultacii;

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
class SLLNode<E> {
	E element;
	SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}
}
class LinkedQueue <E> implements Queue<E>{
	int lenght;
	SLLNode<E> front;
	SLLNode<E> rear;
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
		if(front==null) {
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
			rear=front=nov;
		}
		lenght++;
	}

	@Override
	public E dequeue() {
		if(front==null) {
			throw new NoSuchElementException();
		}else {
			E pom=front.element;
			front=front.succ;
			lenght--;
			if (front == null) rear = null;
			return pom;
		}
	}
	
}
public class konsultacii {
	private static void usluzi(LinkedQueue<String> APS, LinkedQueue<String> MMS) {
		String last = new String();
		System.err.println("Output!");
		if(!APS.isEmpty()) {
			String full=APS.dequeue();
			String [] niza=full.split(" ");
			String ime=niza[0];
			String tip=niza[1];
			last=tip;
			System.out.println(ime);
		}
		while(!APS.isEmpty()) {
			String full=APS.dequeue();
			String [] niza=full.split(" ");
			String ime=niza[0];
			String tip=niza[1];
			if(!last.equals(tip)) {
				System.out.println(ime);
				last=tip;
			}else {
				APS.enqueue(full);
				if(!MMS.isEmpty()) {
					String name=MMS.dequeue();
					System.out.println(name);
				}
				last="QQQQ";
			}
			
		}
		while(!MMS.isEmpty()) {
			String name=MMS.dequeue();
			System.out.println(name);
		}
	
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int brAPS=Integer.parseInt(input.readLine());
		LinkedQueue<String> APS=new LinkedQueue<String>();
		for (int i=0;i<brAPS;i++) {
			String ime=input.readLine();
			APS.enqueue(ime);
		}
		int brMMS=Integer.parseInt(input.readLine());
		LinkedQueue<String> MMS=new LinkedQueue<String>();
		for (int i=0;i<brMMS;i++) {
			String ime=input.readLine();
			MMS.enqueue(ime);
			
		}
		usluzi(APS,MMS);

	}

	

}
