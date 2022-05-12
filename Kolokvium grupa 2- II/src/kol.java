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
public class kol {

	private static void raspredeli(LinkedQueue<String> asistenti, LinkedQueue<String> predmeti,
			LinkedQueue<String> otsutni) {
			int bras=asistenti.size();
			for(int i=0;i<bras;i++) {	
				boolean isOtsuten=false;
				String asistent=asistenti.dequeue();
				int brots=otsutni.size();
				for (int j=0;j<brots;j++) {
					String otsuten=otsutni.dequeue();
					if(otsuten.equals(asistent)) {
						isOtsuten=true;
						break;
					}else {
						otsutni.enqueue(otsuten);
					}
					
				}
				
				if(!isOtsuten) {
					asistenti.enqueue(asistent);
				}
			}
			while(!predmeti.isEmpty()) {
				String all=predmeti.dequeue();
				String [] pom=all.split(" ");
				String predmet=pom[0];
				System.err.println(predmet);
				int brojass=Integer.parseInt(pom[1]);
				System.err.println(brojass);
				for (int i=0;i<brojass;i++) {
					String as=asistenti.dequeue();
					System.err.println(as);
					asistenti.enqueue(as);
				}
			}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int brasis=Integer.parseInt(input.readLine());
		LinkedQueue<String> asistenti=new LinkedQueue<String>();
		for(int i=0;i<brasis;i++) {
				String ime=input.readLine();
				asistenti.enqueue(ime);
		}
		int brpredmeti=Integer.parseInt(input.readLine());
		LinkedQueue<String> predmeti=new LinkedQueue<String>();
		for(int i=0;i<brpredmeti;i++) {
				String ime=input.readLine();
				predmeti.enqueue(ime);
		}
		int brotsutni=Integer.parseInt(input.readLine());
		LinkedQueue<String> otsutni=new LinkedQueue<String>();
		for(int i=0;i<brotsutni;i++) {
				String ime=input.readLine();
				otsutni.enqueue(ime);
		}
		System.err.println("Output:");
		raspredeli(asistenti,predmeti,otsutni);
	}


}
