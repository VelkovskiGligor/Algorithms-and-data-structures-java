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
public class konsultavii4 {
	public static void konsultacii(LinkedQueue<String> aps, LinkedQueue<String> mms) {
		String predtip="Z";
		while(!aps.isEmpty()) {
			String all=aps.dequeue();
			String [] pom=all.split(" ");
			String ime=pom[0];
			String tip=pom[1];
			if(predtip.equals(tip)) {
				aps.enqueue(all);
				if(!mms.isEmpty()) {
					System.err.println(mms.dequeue());
				}else {
					break;
				}
			}else {
				System.err.println(ime);
				predtip=tip;
			}
			
		}
		while(!aps.isEmpty()) {
			String all=aps.dequeue();
			String []ime=all.split(" ");
			System.err.println(ime[0]);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int braps=Integer.parseInt(input.readLine());
		LinkedQueue<String> aps=new LinkedQueue<String>();
				for(int i=0;i<braps;i++) {
					String ime=input.readLine();
					aps.enqueue(ime);
				}
				int brmms=Integer.parseInt(input.readLine());
				LinkedQueue<String> mms=new LinkedQueue<String>();
						for(int i=0;i<brmms;i++) {
							String ime=input.readLine();
							mms.enqueue(ime);
						}
						konsultacii(aps,mms);

	}

	

}
