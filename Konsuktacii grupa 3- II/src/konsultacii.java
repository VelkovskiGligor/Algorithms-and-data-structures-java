import java.io.*;
import java.util.NoSuchElementException;



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
	public SLLNode(E elem,SLLNode <E> succ) {
		this.element=elem;
		this.succ=succ;
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
public class konsultacii {
	public static void raspredeli(LinkedQueue<String> kratkiprasanja, LinkedQueue<String> samozadaci,
			LinkedQueue<String> dvete) {
				while(!kratkiprasanja.isEmpty() || !samozadaci.isEmpty() || ! dvete.isEmpty()) {
					if(!kratkiprasanja.isEmpty()) {
						String ime=kratkiprasanja.dequeue();
						System.err.println(ime);
					}else if(!dvete.isEmpty()) {
						String ime=dvete.dequeue();
						System.err.println(ime);
						samozadaci.enqueue(ime);
					}
					if(!samozadaci.isEmpty()) {
						System.err.println(samozadaci.dequeue());
					}else if(!dvete.isEmpty()) {
						String ime=dvete.dequeue();
						System.err.println(ime);
						kratkiprasanja.enqueue(ime);
					}
					
					
				}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int brkratki=Integer.parseInt(input.readLine());
		LinkedQueue <String> kratkiprasanja=new LinkedQueue<String>();
		for (int i=0;i<brkratki;i++) {
			String ime=input.readLine();
			kratkiprasanja.enqueue(ime);
		}
		int brsamozad=Integer.parseInt(input.readLine());
		LinkedQueue <String> samozadaci=new LinkedQueue<String>();
		for (int i=0;i<brsamozad;i++) {
			String ime=input.readLine();
			samozadaci.enqueue(ime);
		}
		int brdvete=Integer.parseInt(input.readLine());
		LinkedQueue <String> dvete=new LinkedQueue<String>();
		for (int i=0;i<brdvete;i++) {
			String ime=input.readLine();
			dvete.enqueue(ime);
		}
		System.err.println("Output:");
		raspredeli(kratkiprasanja,samozadaci,dvete);
	}

	

	

}
