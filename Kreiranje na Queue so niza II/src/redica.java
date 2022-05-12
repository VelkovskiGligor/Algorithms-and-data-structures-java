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
	 private E []elements;
	 private int lenght;
	 private int front,rear;
	 public ArrayQueue(int maxlen) {
		 elements=(E[]) new Object[maxlen];
		 lenght=0;rear=0;front=0;
		 clear();
	 }
	@Override
	public boolean isEmpty() {
		return lenght==0;
	}

	@Override
	public int size() {
		return lenght;
	}

	@Override
	public E peek() {
		if(lenght!=0) {
		return elements[front];
		}else {
			throw new NoSuchElementException();
		}
		
	}

	@Override
	public void clear() {
		for(int i=0;i<elements.length;i++) {
			elements[i]=null;
		}	
		rear=0;
		front=0;
		lenght=0;
	}

	@Override
	public void enqueue(E x) {
		if(lenght!=elements.length) {
			elements[rear]=x;
			rear++;
			lenght++;
			if(rear==elements.length) {
				rear=0;
			}
		}
		
	}

	@Override
	public E dequeue() {
		if(lenght!=0) {
			E pom=elements[front];
			elements[front]=null;
			if(front==elements.length-1) {
			front=0;
			}else {
				front++;
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
		// TODO Auto-generated method stub

	}

}
