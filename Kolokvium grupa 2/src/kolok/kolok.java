package kolok;

import java.io.*;
import java.util.NoSuchElementException;

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
public class kolok {

	private static void Raspredeli(int brAsistenti, ArrayQueue<String> imeAsistenti, int brPredmeti,
			ArrayQueue<String> predmeti, int brOtsutni, String[] otsutni) {
			System.err.println("OUTPUT");
		for(int i=0;i<brPredmeti;i++) {
			String full=predmeti.dequeue();
			String [] niza=full.split(" ");
			String ime=niza[0];
			int broj=Integer.parseInt(niza[1]);
			System.out.println(ime);
			System.out.println(broj);
			for(int j=0;j<broj;j++) {
				String asistent=imeAsistenti.dequeue();
				if(proveri(asistent,otsutni,brOtsutni)) {
					System.out.println(asistent);
					imeAsistenti.enqueue(asistent);
				}else {
					imeAsistenti.enqueue(asistent);
					j--;
				}
			}
			
		}
		
	}
	public static boolean  proveri(String ime,String [] otsutni,int br) {
		for (int i=0;i<br;i++) {
			if(ime.equals(otsutni[i])) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int brAsistenti=Integer.parseInt(input.readLine());
		ArrayQueue<String> imeAsistenti=new ArrayQueue<String>(100);
		for (int i=0;i<brAsistenti;i++) {
			String ime=input.readLine();
			imeAsistenti.enqueue(ime);
		}
		int brPredmeti=Integer.parseInt(input.readLine());
		ArrayQueue<String> Predmeti=new ArrayQueue<String>(100);
		for (int i =0;i<brPredmeti;i++) {
			String ime=input.readLine();
			Predmeti.enqueue(ime);
		}
		int brOtsutni=Integer.parseInt(input.readLine());
		String [] otsutni=new String[brOtsutni];
		for (int i=0;i<brOtsutni;i++) {
			String ime=input.readLine();
			otsutni[i]=ime;
		}
		Raspredeli(brAsistenti,imeAsistenti,brPredmeti,Predmeti,brOtsutni,otsutni);
		
	}


}
