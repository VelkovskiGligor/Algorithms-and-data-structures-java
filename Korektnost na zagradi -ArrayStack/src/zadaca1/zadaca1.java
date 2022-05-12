package zadaca1;
import java.io.*;
import java.util.NoSuchElementException;



interface Stack<E> {
	 public boolean isEmpty ();
	 public E peek ();
	 public void clear ();
	 public void push (E x);
	 public E pop ();
	}
class ArrayStack<E> implements Stack<E>{
	private E [] elements;
	private int depth;
	public ArrayStack(int maxDepth) {
		elements=(E[]) new Object[maxDepth];//Kreiranje na prazan Stack;
		depth=0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(depth == 0) {
			return true;
		}
		return false;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(depth==0) {
			throw new NoSuchElementException();
		}
		return elements[depth-1];
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i=0;i<depth;i++) {
			elements[i]=null;
		}
		depth=0;
	}

	@Override
	public void push(E x) {
		// TODO Auto-generated method stub
		elements[depth]=x;
		depth++;
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if(depth==0) {
			throw new NoSuchElementException();
		}
		E pom=elements[depth-1];
		elements[depth-1]=null;
		depth--;
		return pom;
	}
	
}
public class zadaca1 {
	public static void proveri(String izraz) {
	
		ArrayStack <Character> stek=new ArrayStack(100); 
		for(int i=0;i<izraz.length();i++) {
			char  znak=izraz.charAt(i);
			if(isZnak(znak)) {
				if( znak == '('  || znak== '{'  || znak== '['  ) {
					System.out.println("push: "+znak);
					stek.push(znak);
				}else {
					if(stek.isEmpty()) {
						System.err.println("Pogresen izraz");
						return;
					}else {
						
							char pom=stek.pop();
							System.out.println("Od stekot se vadi: "+pom + " I se sporeduva so " + znak);
							
							if(!sporedi(pom,znak)) {
								System.out.println("Pogresen izraz!!");
								return;
							}else {
								//nisto
							}
					}
					
				}
			}
			
		}
		if(!stek.isEmpty()) {
			System.err.println("NADvor Pogresen izraz");
		}else {
			System.out.println("Izrazot e vo red!");
		}
	}
	public static boolean isZnak(char s) {
		if( s=='(' || s == ')' || s== '{' || s == '}' || s =='[' || s == ']' ) {
				return true;
		}
		return false;
	}
	public static boolean sporedi(char s,char z) {
		if(  ( s== '(' && z == ')' ) || ( s == '['  && z==  ']') || (s == '{' && z =='}' ) ) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Vnesete go izrazot");
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String izraz=input.readLine();
		proveri(izraz);
	}

}
