import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
class Gragjanin {
	protected String ime;
	protected int licna;
	protected int pasos;
	protected int vozacka;
	public Gragjanin(String ime, int licna, int pasos, int vozacka) {
		super();
		this.ime = ime;
		this.licna = licna;
		this.pasos = pasos;
		this.vozacka = vozacka;
	}
	public String toString() {
		return ime;
	}

}
public class MVR {
	
	public static void main(String[] args) {
		
		Scanner br = new Scanner(System.in);
		LinkedQueue<Gragjanin> licni=new LinkedQueue<Gragjanin>();
		LinkedQueue<Gragjanin> pasosi=new LinkedQueue<Gragjanin>();
		LinkedQueue<Gragjanin> vozacki=new LinkedQueue<Gragjanin>();
        int N = Integer.parseInt(br.nextLine());
        for(int i=1;i<=N;i++){
        	String imePrezime = br.nextLine();
        	int lKarta = Integer.parseInt(br.nextLine());
        	int pasos = Integer.parseInt(br.nextLine());
        	int vozacka = Integer.parseInt(br.nextLine());
        	Gragjanin covek = new Gragjanin(imePrezime,lKarta,pasos,vozacka);
        	if(lKarta==1) {
        		licni.enqueue(covek);
        	}else if(pasos==1) {
        		pasosi.enqueue(covek);
        	}else if(vozacka==1){
        		vozacki.enqueue(covek);
        	}
       	}
        procesing(licni,pasosi,vozacki);
	}
	public static void procesing(LinkedQueue<Gragjanin> licni, LinkedQueue<Gragjanin> pasosi,
			LinkedQueue<Gragjanin> vozacki) {
				while(!licni.isEmpty()) {
					Gragjanin pom= licni.dequeue();
					if( pom.pasos==1) {
						pasosi.enqueue(pom);
					}else if( pom.vozacka==1) {
						vozacki.enqueue(pom);
					}else {
						System.out.println(pom.ime);
					}
				}
				while(!pasosi.isEmpty()) {
					Gragjanin pom= pasosi.dequeue();
					if( pom.vozacka==1) {
						vozacki.enqueue(pom);
					}else {
						System.out.println(pom.ime);
					}
				}
				while(!vozacki.isEmpty()) {
					Gragjanin pom= vozacki.dequeue();
					System.out.println(pom.ime);
					}
		
	}
	
}
