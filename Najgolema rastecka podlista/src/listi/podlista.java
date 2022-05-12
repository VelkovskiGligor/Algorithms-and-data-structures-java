package listi;
import java.io.*;

class SLLNode<E>{
	E element;
	SLLNode <E> succ;
	public SLLNode(E element,SLLNode <E> succ){
		this.element=element;
		this.succ=succ;
	}
}
class SLL <E>{
	SLLNode<E> first;
	public SLL() {
		first=null;
	}
	public void insertFirst(E element) {
		SLLNode <E> nov=new SLLNode<E>(element,first);
		first=nov;
	}
	public void insertLast(E element) {
		if(this.first==null) {
			insertFirst(element);
		}else {
			SLLNode<E> nov=new SLLNode<E>(element,null);
			SLLNode<E> dvizi=this.first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			dvizi.succ=nov;
		}
	}
	@Override
	public String toString() {
		SLLNode<E> dvizi=this.first;
		String string=new String();
		while(dvizi!=null) {
			string=string+dvizi.element +"->";
			dvizi=dvizi.succ;
		}
		return string;
	}
	 public int lenght() {
			SLLNode <E> dvizi=first;
			int lenght=0;
			if(dvizi!=null) {
				while(dvizi!=null) {
					lenght++;
					dvizi=dvizi.succ;
				}
			return lenght;
			}else 
				return 0;
		}
		
}
public class podlista {
	public static void promeni(SLL<Integer> prva){
		SLLNode<Integer> dvizi=prva.first;
		SLLNode<Integer> maxpocetak=null;
		SLLNode<Integer> maxkraj=null;
		SLLNode<Integer> pocetak=null;
		SLLNode<Integer> kraj=null;
		int brojac=0;
		int maxbrojac=0;
		while(dvizi.succ!=null) {
			
				if(dvizi.element<=dvizi.succ.element) {
					if(pocetak==null) {
						pocetak=dvizi;
					}
					brojac++;
					kraj=dvizi.succ;
				}else {
					if(maxbrojac<brojac) {
						maxbrojac=brojac;
						maxpocetak=pocetak;
						maxkraj=kraj;
					}
					pocetak=null;
					brojac=0;
				}	
				
			
			dvizi=dvizi.succ;
			
		}
		if(maxbrojac<brojac) {
			maxbrojac=brojac;
			maxpocetak=pocetak;
			maxkraj=kraj;
		}
		if(maxpocetak!=null) {
		prva.first=maxpocetak;
		maxkraj.succ=null;	
		}
		
	
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesete ja prvata lista");
		String recenica=input.readLine();
		String [] niza=recenica.split(" ");
		SLL <Integer> prva=new SLL<Integer>();
		for(int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			prva.insertLast(broj);
			
		}
		System.out.println(prva);
	
		promeni(prva);
		System.out.println(prva);
		
	}

}
