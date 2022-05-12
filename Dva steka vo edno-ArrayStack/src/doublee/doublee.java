package doublee;

import java.util.NoSuchElementException;

interface Stack<E> {
	 public boolean isEmptyFirst ();
	 public boolean isEmptySecound ();
	 public boolean isFull();
	 public E peekFirst ();
	 public E peekSecound ();
	 public void clearFirst();
	 public void clearSecound ();
	 public void pushFirst (E x);
	 public void pushSecound (E x);
	 public E popFirst ();
	 public E popSecound ();
	}
class DoubleArrayStack<E> implements Stack<E>{
	E [] niza;
	int depth1;
	int depth2;
	public DoubleArrayStack(int maxDepth) {
		niza=(E[]) new Object[maxDepth];
		depth1=depth2=0;
	}
	@Override
	public boolean isEmptyFirst() {
		
		return (depth1 ==0 );
	}
	public boolean isEmptySecound() {
		
		return (depth2==0);
	}
	@Override
	public boolean isFull() {
		if(depth1+depth2== niza.length)
			return true;
		return false;
	}
	@Override
	public E peekFirst() {
			if(depth1==0) {
				throw new NoSuchElementException();

			}
		return niza[depth1-1];
	}
	@Override
	public E peekSecound() {
		if(depth2==0) {
			throw new NoSuchElementException();

		}
		return niza[niza.length-depth2];
	}
	@Override
	public void clearFirst() {
		if(!isEmptyFirst()) {
			for(int i=0;i<depth1;i++) {
				niza[i]=null;
			}
			depth1=0;
		}else{
			throw new NoSuchElementException();
		}
		
	}
	@Override
	public void clearSecound () {
		for(int i=niza.length-1;i> niza.length-depth2;i--) {
			niza[i]=null;
		}
		depth2=0;
	}
	@Override
	public void pushFirst(E x) {
		if(!isFull()) {
			niza[depth1]=x;
			depth1++;
		}else {
			throw new NoSuchElementException();
		}
		
	}
	@Override
	public void pushSecound(E x) {
		if(!isFull()) {
			niza[niza.length-depth2]=x;
			depth2++;
		}else {
			throw new NoSuchElementException();
		}
		
	}
	@Override
	public E popFirst() {
		if(!isEmptyFirst()) {
			E pom=niza[depth1-1];
			niza[depth1-1]=null;
			depth1--;
			return pom;
		}else {
			throw new NoSuchElementException();
		}
		
	}
	@Override
	public E popSecound() {
		if(!isEmptySecound() ) {
			E pom=niza[niza.length-depth2];
			niza[niza.length-depth2]=null;
			depth2--;
			return pom;
		}else {
			throw new NoSuchElementException();
		}

	}

	

	



	
}
public class doublee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
