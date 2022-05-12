import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {
	 public boolean isEmpty ();
	 public E peek ();
	 public void clear ();
	 public void push (E x);
	 public E pop ();
	}
class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

class LinkedStack<E> implements Stack<E>{
	private SLLNode<E> top;
	public LinkedStack() {
		top=null;
	}
	public boolean isEmpty() {
		if(top==null) {
			return true;
		}else {
			return false;
		}
	}
	public E peek() {
		if(top==null) {
			throw new NoSuchElementException();

		}else {
			return top.element;
		}
	}
	public void clear() {
		top=null;
	}
	public void push(E element) {
		SLLNode<E> nov=new SLLNode<E> (element,top);
		top=nov;
	}
	public E pop() {
		if(top==null) {
			throw new NoSuchElementException();
		}
		E pom=top.element;
		top=top.succ;
		return pom;
	}
}

public class PostFixEvaluation {
	public static int presmetaj(String s) {
		int sum;
		LinkedStack <String> stek=new LinkedStack<String>();
		String niza[] =s.split(" ");
		for(int i=0;i<niza.length;i++) {
			if(niza[i].equals("+") || niza[i].equals("-") || niza[i].equals("*") || niza[i].equals("/")){
				//System.err.println("HI");
				int vtor= Integer.parseInt(stek.pop());
				int prv=Integer.parseInt(stek.pop());
				//System.out.println("Prv: "+prv);
				//System.out.println("Vtor: "+vtor);
				if(niza[i].equals("+")) {
					sum=prv+vtor;
					stek.push(sum+"");
				}else if(niza[i].equals("-")) {
					sum=(prv-vtor);
					stek.push(sum+"");
				}else if(niza[i].equals("*")) {
					sum=(prv*vtor);
					stek.push(sum+"");
				}else if(niza[i].equals("/")) {
					sum=(prv/vtor);
					stek.push(sum+"");
				}
			}else {
					stek.push(niza[i]);
			}
					
		}
		return Integer.parseInt(stek.pop());
	}
	public static void main(String[] args) throws Exception{
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();
        System.out.println(presmetaj(s));
   
        
   
        
        br.close();

	}

	

}
