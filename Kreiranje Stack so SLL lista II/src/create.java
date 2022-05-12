import java.util.NoSuchElementException;

interface Stack<E>{
	public boolean isEmpty();
	public void clear();
	public  E peek();
	public void push(E x);
	public E pop();
}
class SLLNode<E>{
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}
	@Override
	public String toString() {
		return this.element.toString();
		
	}
	
}
class LinkedStack<E> implements Stack<E>{
		private  SLLNode top;
		public LinkedStack() {
			top=null;
		}
	
	@Override
	public boolean isEmpty() {
		
		return top==null;
	}

	@Override
	public void clear() {
		top=null;
		
	}

	@Override
	public E peek() {
		if(top==null) {
			throw new NoSuchElementException();
		}
		return (E) top.element;
	}

	@Override
	public void push(E x) {
		SLLNode<E> nov=new SLLNode<E>(x,top);
		top=nov;
	}

	@Override
	public E pop() {
		E pom= (E) top.element;
		top=top.succ;
		return pom;
	}
	
}
public class create {

	public static void main(String[] args) {
		LinkedStack<Integer> stek=new LinkedStack<Integer>();
		stek.push(10);
		System.out.println(stek.peek());
		stek.push(5);
		System.out.println(stek.pop());
		System.out.println(stek.pop());
		

	}

}
