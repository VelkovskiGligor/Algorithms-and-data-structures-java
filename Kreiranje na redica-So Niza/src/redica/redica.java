package redica;

import java.util.NoSuchElementException;

interface Queue<E> {
	 public boolean isEmpty ();
	 public int size ();
	 public E peek ();
	 public void clear ();
	 public void enqueue (E x);
	 public E dequeue ();
	}
 class ArrayQueue<E> implements Queue<E>{
	 E [] elems;
	 int lenght,front,rear;// front-glava rear-opaska
	 public ArrayQueue(int maxdepth) {
		 elems=(E[]) new Object[maxdepth];
		 lenght=0;
		 front=0;
		 rear=0;
	 }
	 
	@Override
	public boolean isEmpty() {
		if(lenght==0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
	
		return lenght;
	}

	@Override
	public E peek() {
		if(lenght>0) {
		return elems[front];	
		}else {
			throw new NoSuchElementException();
		}
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		lenght=0;
		front=0;
		rear=0;
		
	}

	@Override
	public void enqueue(E x) {
	
		elems[rear]=x;
		rear++;
		if(rear==elems.length) {
			rear=0;//ciklicna niza
		}
		lenght++;
	}

	@Override
	public E dequeue() {
		if(lenght>0) {
			E pom=elems[front];
			elems[front]=null;
			front++;
			if(front== elems.length ) {
				front=0;
			}
				lenght--;
			return pom;
		}else {
			throw new NoSuchElementException();
		}
		
	}
	 
 }
public class redica {

	public static void main(String[] args) {
		String s="Gligorije Velkovski Nikola Velkovski Martin Martinovski";
		String [] niza=s.split(" ");
		ArrayQueue <String> redica=new ArrayQueue<String>(100);
		for (int i=0;i<niza.length;i++) {
				System.out.println("se dodava:  "+niza[i]);
				redica.enqueue(niza[i]);
				if(i == 3 || i== 4) {
					String pom=redica.dequeue();
					System.out.println("Se vadi: " +pom);
					
					
				}
		}
	}

}
