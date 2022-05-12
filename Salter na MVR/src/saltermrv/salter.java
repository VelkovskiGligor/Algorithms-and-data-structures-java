package saltermrv;
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
	 public SLLNode(E element,SLLNode<E> succ) {
		 this.element=element;
		 this.succ=succ;
	 }
 }
 class LinkedQueue<E> implements Queue<E>{
	 int lenght;
	 SLLNode<E> front,rear;
	 public LinkedQueue() {
		 lenght=0;
		 front=null;
		 rear=null;
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
		if(front==null) {
			throw new NoSuchElementException();

		}
		return front.element;
	}

	@Override
	public void clear() {
		front=null;
		lenght=0;
		rear=null;
		
	}

	@Override
	public void enqueue(E x) {
		SLLNode<E> nov=new SLLNode<E>(x,null);
		if(rear!=null) {
			rear.succ=nov;
			rear=rear.succ;
		}else {
			front=nov;
			rear=nov;
		}
		lenght++;
		
	}

	@Override
	public E dequeue() {
		if(lenght==0) {
			throw new NoSuchElementException();
		}else {
			E pom=front.element;
			front=front.succ;
			lenght--;
			if(front==null) {
				rear=null;
			}
		return pom;	
		}
		
	}
	 
 }
class Klient{
	private String ime;
	private int licnaKarta;
	private int pasos;
	private int dozvola;
	public Klient(String ime,int licna,int pasos,int dozvola) {
		this.ime=ime;
		this.licnaKarta=licna;
		this.pasos=pasos;
		this.dozvola=dozvola;
	}
	public String getIme() {
		return ime;
	}
	
	public int getLicnaKarta() {
		return licnaKarta;
	}
	
	public int getPasos() {
		return pasos;
	}
	
	public int getDozvola() {
		return dozvola;
	}
	
	
}
public class salter {
	public static void Usluzi(Klient [] klienti) {
		LinkedQueue <Klient> licna=new LinkedQueue<Klient>();
		LinkedQueue <Klient> pasos=new LinkedQueue<Klient>();
		LinkedQueue <Klient> dozvola=new LinkedQueue<Klient>();
		for (int i =0 ; i< klienti.length;i++) {
			if(klienti[i].getLicnaKarta()==1) {
				licna.enqueue(klienti[i]);
			}else if( klienti[i].getPasos()==1) {
				pasos.enqueue(klienti[i]);
			}else if(klienti[i].getDozvola()==1) {
				dozvola.enqueue(klienti[i]);
			}
			
		}
		//idemo samo licna karta
		while (!licna.isEmpty()) {
			Klient klient=licna.dequeue();
			if(klient.getPasos()==1) {
				pasos.enqueue(klient);
			}else if(klient.getDozvola()==1) {
				dozvola.enqueue(klient);
			}else {
				System.out.println(klient.getIme());
			}
		}
		while (!pasos.isEmpty()) {
			Klient klient=pasos.dequeue();
			 if(klient.getDozvola()==1) {
				dozvola.enqueue(klient);
			}else {
				System.out.println(klient.getIme());
			}
		}
		while (!dozvola.isEmpty()) {
			Klient klient=dozvola.dequeue();
				System.out.println(klient.getIme());
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		 int n=Integer.parseInt(input.readLine());
		 Klient [] klienti=new Klient[n];
		 for (int i=0;i<n;i++) {
			 String ime=input.readLine();
			 int x=Integer.parseInt(input.readLine());
			 int y=Integer.parseInt(input.readLine());
			 int z=Integer.parseInt(input.readLine());
			 klienti[i]=new Klient(ime,x,y,z);
		 }
		 Usluzi(klienti);
		 
	}

}
