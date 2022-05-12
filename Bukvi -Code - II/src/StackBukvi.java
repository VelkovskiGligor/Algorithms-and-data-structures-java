import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    	// Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    	// Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    	// Go prazni stekot.

    public void push (E x);
    	// Go dodava x na vrvot na stekot.

    public E pop ();
    	// Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class StackBukvi {
    
	static int proveri_t_posle_s(char [] St)
	{
		ArrayStack<Character> stek=new ArrayStack<Character> (100);
		int prv=0;
		int brojac=0;
	    for(int i=0;i<St.length;i++) {
	    	if(St[i]=='T') {
	    		stek.push(St[i]);
	    	}
	    	else if(St[i]=='S') {
	    		stek.push(St[i]);
	    	}
	    }
	    char x='z';
	    while(!stek.isEmpty() ) {
	    	 x=stek.pop();
	    	 if(x=='T'){
		    		prv++;
		    	}
	    	else if(x=='S') {
	    	break;
	    	} 
	    }
	    
	    while(!stek.isEmpty() ) {
	    	x=stek.pop();
	    	if(x=='S') {
	    		if(prv!=brojac) {
	    			return 0;
	    		}else {
	    			brojac=0;
	    		}
	    		
	    	}else if(x=='T'){
	    		brojac++;
	    	}
	    }
			return 1;
		
	    
	}

	
	public static void main(String[] args) throws IOException {
		char [] niza=new char[100];
	
		Scanner f=new Scanner(System.in);
		String st=f.next();
		niza=st.toCharArray();
		
	    int rez= proveri_t_posle_s(niza);
		System.out.println(rez);
	}
	
	
}