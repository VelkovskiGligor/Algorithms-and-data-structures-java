package Kolokvium;

import java.util.NoSuchElementException;
import java.io.*;
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
 
interface Queue<E> {
    public boolean isEmpty();
 
    public int size();
 
    public E peek();
 
    public void clear();
 
    public void enqueue(E x);
 
    public E dequeue();
}
 
 
class ArrayQueue<E> implements Queue<E> {
 
    E[] elems;
    int length, front, rear;
 
 
    @SuppressWarnings("unchecked")
    public ArrayQueue (int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }
 
    public boolean isEmpty () {
        return (length == 0);
    }
 
    public int size () {
        return elems.length;
    }
 
    public E peek () {
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }
 
    public void clear () {
        length = 0;
        front = rear = 0;  // arbitrary
    }
 
    public void enqueue (E x) {
        elems[rear++] = x;
        if (rear == elems.length)  rear = 0;
        length++;
    }
 
    public E dequeue () {
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length)  front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}
public class Kolokvium {
	public static boolean proveri(String ime,String[] niza) {
		for (int i=0;i<niza.length;i++) {
			if(ime.equals(niza[i])) {
				niza[i]="XXXX";
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        int brTermini=Integer.parseInt(input.readLine());//max vo termin
        ArrayQueue <String> kePolagaMat=new ArrayQueue<String>(100);
        ArrayQueue <String> TERMIN=new ArrayQueue<String>(100);
     
        int prijaveniMat=Integer.parseInt(input.readLine());
        for(int i=0;i<prijaveniMat;i++) {
        	kePolagaMat.enqueue(input.readLine());
        }
        ArrayQueue <String> ostanati=new ArrayQueue<String>(100);
        int brostanati=Integer.parseInt(input.readLine());
        for(int i=0;i<brostanati;i++) {
        	ostanati.enqueue(input.readLine());
        }
        
        int brstv=Integer.parseInt(input.readLine());
        String []  stvPolagamat=new String [brstv];
        for(int i=0;i<brstv;i++) {
        	stvPolagamat[i]=input.readLine();
        }
       ///////Proveruvameee!
        System.out.println("TERMINIII:");
        int pom=prijaveniMat;
        for (int i=0;i<pom;i++) {
        	String ime=kePolagaMat.dequeue();
        if(proveri(ime,stvPolagamat)) {
        	TERMIN.enqueue(ime);
        }else {
        	kePolagaMat.enqueue(ime);
        }
        }
        for (int i=0;i<brostanati;i++) {
        	String ime=ostanati.dequeue();
        		TERMIN.enqueue(ime);
        }
       while(!kePolagaMat.isEmpty()) {
    	   String ime=kePolagaMat.dequeue();
    	   TERMIN.enqueue(ime);
       }
       int brojac=0;
       int glaven=1;
       boolean flag=true;
       while(!TERMIN.isEmpty()) {
    	   if(flag) {
    		   flag=false;
    		   System.out.println(glaven);
    	   }
    	   String ime=TERMIN.dequeue();
    	   System.out.println(ime);
    	   brojac++;
    	   if(brojac==brTermini) {
    		   flag=true;
    		   brojac=0;
    		   glaven++;
    	   }
       }
	}

}
