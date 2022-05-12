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
public class topcinja {
	public static void ponisti(LinkedQueue<String> redica) {
		// TODO Auto-generated method stub
		int br=redica.lenght;
		boolean ednakvi=false;
		for (int i=0;i<br;i++) {
			String topce=redica.dequeue();
			 ednakvi=false;
			char tip=topce.charAt(0);
			char znak=topce.charAt(1);
			br=redica.lenght;
			for (int j=0;j<br;j++) {
				String pom=redica.dequeue();
				char pomtip=pom.charAt(0);
				char pomznak=pom.charAt(1);
				if(tip == pomtip && znak!= pomznak) {
					ednakvi=true;
					break;
				}else {
					redica.enqueue(pom);
				}
			}
			if(!ednakvi) {
				redica.enqueue(topce);
			}
		}
		System.err.println(redica.size());
		while(!redica.isEmpty()) {
			System.err.println(redica.dequeue());
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		String [] niza=s.split(" ");
		LinkedQueue<String> redica=new LinkedQueue<String>();
		for (int i=0;i<niza.length;i++) {
			redica.enqueue(niza[i]);
		}
		ponisti(redica);
		
	}

	

}
