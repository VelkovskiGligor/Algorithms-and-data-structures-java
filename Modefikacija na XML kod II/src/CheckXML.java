import java.io.*;
import java.util.NoSuchElementException;
interface Stack<E> {
	 public boolean isEmpty ();
	 public E peek ();
	 public void clear ();
	 public void push (E x);
	 public E pop ();
	}
class SLLNode<E>{
	E element;
	SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}
}
class LinkedStack <E> implements Stack<E>{
	SLLNode<E> top;
	public LinkedStack() {
		top=null;
	}
	@Override
	public boolean isEmpty() {
		if(top==null) {
		return true;
		}
		return false;
	}

	@Override
	public E peek() {
		if(top==null) {
			throw new NoSuchElementException();
		}
		return top.element;
	}

	@Override
	public void clear() {
		top=null;
		
	}

	@Override
	public void push(E x) {
		SLLNode<E> nov=new SLLNode<E>(x,top);
		top=nov;
		
	}

	@Override
	public E pop() {
		if(top==null) {
			throw new NoSuchElementException();
		}
		E pom=top.element;
		top=top.succ;
		return pom;
	}
	
}
public class CheckXML {	
	public static int proveri(String[] niza) {
		LinkedStack<String> stek=new LinkedStack<String>();
		for (int i=0;i<niza.length;i++) {
			if(niza[i].charAt(0)=='[' && niza[i].charAt(1)!='/') {
				stek.push(niza[i]);
			}else if(niza[i].charAt(0)=='[' && niza[i].charAt(1)=='/') {
				String otvoren=stek.pop();
				otvoren=otvoren.substring(1,otvoren.length()-1);
				String zatvoren=niza[i].substring(2,niza[i].length()-1);
				if(!zatvoren.equals(otvoren)) {
					return 0;
				}
			}
		}
		if(!stek.isEmpty()) {
			return 0;
		}
		return 1;
	}
	public static void main(String[] args) throws Exception{
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String s = br.readLine();
		int n = Integer.parseInt(s);
		String [] redovi = new String[n];
	
		for(int i=0;i<n;i++)
			redovi[i] = br.readLine();
       
		int valid;
    	
        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
		System.out.println(proveri(redovi));
        
        br.close();
	}

	
}