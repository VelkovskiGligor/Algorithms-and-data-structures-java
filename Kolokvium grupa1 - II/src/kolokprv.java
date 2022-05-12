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
class Student{
	protected String ime;
	protected int izbor; // 1-bilo koga  2-Mat
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getIzbor() {
		return izbor;
	}
	public void setIzbor(int izbor) {
		this.izbor = izbor;
	}
	public Student(String ime, int izbor) {
		super();
		this.ime = ime;
		this.izbor = izbor;
	}
	@Override
	public String toString() {
		return ime;
	}
	
}
public class kolokprv {

	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int kapacitet=Integer.parseInt(input.readLine());
		int M=Integer.parseInt(input.readLine());
		LinkedQueue <Student> dvaizbor=new LinkedQueue<Student>();
		for (int i=0;i<M;i++) {
			String ime=input.readLine();
			Student student=new Student(ime,2);
			dvaizbor.enqueue(student);
		}
		int N=Integer.parseInt(input.readLine());
		LinkedQueue <Student> edenizbor=new LinkedQueue<Student>();
		for (int i=0;i<N;i++) {
			String ime=input.readLine();
			Student student=new Student(ime,1);
			edenizbor.enqueue(student);
		}
		
		int Z=Integer.parseInt(input.readLine());
		LinkedQueue <Student> stvmat=new LinkedQueue<Student>();
		for (int i=0;i<Z;i++) {
			String ime=input.readLine();
			Student student=new Student(ime,2);
			stvmat.enqueue(student);
		}
		
		LinkedQueue <Student> rezz=new LinkedQueue<Student>();
		raspored(dvaizbor,edenizbor,stvmat,rezz);
		int j=0;
		System.err.println("Output:");
		while(!rezz.isEmpty()) {
			//System.out.println("com");
			j++;
			System.out.println(j);
			for(int i=0;i<kapacitet;i++) {
				System.out.println(rezz.dequeue());
				if(rezz.isEmpty()) {
					break;
				}
			}
		}
		
	}

	public static void raspored(LinkedQueue<Student> dvaizbor, LinkedQueue<Student> edenizbor,
			LinkedQueue<Student> stvmat, LinkedQueue<Student> rezz) {
			boolean vistina=false;
			int Z=dvaizbor.size();
			for(int i=0;i<Z;i++) {
				Student s=dvaizbor.dequeue();
				vistina=false;
					int siz=stvmat.size();
					for(int j=0;j<siz;j++) {
						Student pom= stvmat.dequeue();
						if(s.ime.equals(pom.ime)) {
							vistina=true;
							break;
						}else {
							stvmat.enqueue(pom);
						}
				}
				if(vistina) {
					rezz.enqueue(s);
				}else {
					dvaizbor.enqueue(s);
				}
				
			}
			
			while(!edenizbor.isEmpty()) {
				rezz.enqueue(edenizbor.dequeue());
			}
			while(!dvaizbor.isEmpty()) {
				rezz.enqueue(dvaizbor.dequeue());
			}
		
	}

}
