import java.util.NoSuchElementException;



interface Stack<E> {
	 public boolean isEmpty ();
	 public E peek ();
	 public void clear ();
	 public void push (E x);
	 public E pop ();
	}
class ArrayStack <E> implements Stack<E>{
	private E  [] elements;
	private int depth;
	public ArrayStack (int maxlen){
		depth=0;
		elements=(E[]) new Object[maxlen];
	}
	@Override
	public boolean isEmpty() {
		return depth==0;
		
	}

	@Override
	public E peek() {
		if(depth==0) {
			throw new NoSuchElementException();
		}else {
			return elements[depth-1];
		}
	}

	
	@Override
	public void clear() {
		for (int i=0;i<depth;i++)
			elements[i]=null;
		depth=0;
		
	}

	@Override
	public void push(E x) {
		if(depth<elements.length) {
			elements[depth++]=x;
		}
	}

	@Override
	public E pop() {
		if(depth!=0) {
			E element=elements[--depth];
			elements[depth]=null;
			return element;
		}else {
			throw new NoSuchElementException();
		}
	}
	
}
public class create {

	public static void main(String[] args) {
			ArrayStack<Integer> stek=new ArrayStack<Integer>(10);
			stek.push(10);
			stek.push(5);
			System.out.println(stek.peek());
			stek.pop();
			System.out.println(stek.peek()); 
			stek.pop();
			System.out.println(stek.peek()); //Stekot e prazen

	}

}
