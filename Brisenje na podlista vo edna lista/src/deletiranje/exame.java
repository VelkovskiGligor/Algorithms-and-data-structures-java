package deletiranje;

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
public class exame {
	public static void promeni(SLL<Integer> prva,SLL<Integer> vtora) {
		SLLNode<Integer>dviziprva=prva.first;
		SLLNode<Integer>dvizivtora=vtora.first;
		SLLNode<Integer>pocetak=null;
		if(prva.lenght()<vtora.lenght())return;
		while(dviziprva!=null) {
			if(dviziprva.element==vtora.first.element) {
				dvizivtora=vtora.first;
				int brojac=0;
				while(dvizivtora!=null && dviziprva!=null) {
					if(dviziprva.element==dvizivtora.element) {
						brojac++;
					}else {
						break;
					}
					dviziprva=dviziprva.succ;
					dvizivtora=dvizivtora.succ;
				}
				if(brojac==vtora.lenght()) {
					if(pocetak==null) {
						prva.first=dviziprva;
					}else {
						pocetak.succ=dviziprva;
					}
					
				}else {
					if(pocetak==null) {
					pocetak=prva.first;
					dviziprva=pocetak.succ;
					}else {
						pocetak=pocetak.succ;
						dviziprva=pocetak.succ;
					}
					
				}
				
			}else {
				
				if(pocetak==null) {
				pocetak=prva.first;
				dviziprva=pocetak.succ;
				}else {
					dviziprva=dviziprva.succ;
					pocetak=pocetak.succ;
					
				}
			}
			
			
				
			
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
		System.out.println("Vnesete ja vtorata lista");
		String linia=input.readLine();
		String [] nizaa=linia.split(" ");
		SLL <Integer> vtora=new SLL<Integer>();
		for(int i=0;i<nizaa.length;i++) {
			int broj=Integer.parseInt(nizaa[i]);
			vtora.insertLast(broj);
			
		}
		System.out.println(prva);
		System.out.println(vtora);
		promeni(prva, vtora);
		System.out.println(prva);
	}

}
