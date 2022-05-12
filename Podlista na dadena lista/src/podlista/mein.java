package podlista;

import java.io.*;
class SLLNode <E>{
	E element;
	SLLNode <E> succ;
	public SLLNode (E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
		
	}
}
class SLL <E>{
	SLLNode<E> first;
	public SLL() {
		this.first=null;
	}
	public void insertFirst(E element) {
		SLLNode <E> nov=new SLLNode<E> (element,first);
		this.first=nov;
	}
	public void insertLast(E element) {
		if(first==null) {
			insertFirst(element);
		}else {
			SLLNode<E>dvizi=this.first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			SLLNode<E> nov=new SLLNode<E>(element,null);
			dvizi.succ=nov;
		}
	}
	public String toString() {
		String string=new String();
		SLLNode<E> dvizi=this.first;
		while(dvizi!=null) {
			if(dvizi.succ!=null) {
				string+=dvizi.element+"->";
			}else {
				string+=dvizi.element;
			}
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
public class mein {
	public static void ifPodlista(SLL<Integer> prva,SLL<Integer> vtora) {
		if(prva.lenght()<vtora.lenght()) {
			System.out.println("False");
			return;
		}
		SLLNode<Integer> dviziprva=prva.first;
		SLLNode<Integer> dvizivtora=vtora.first;
		boolean flag=true;
		while(dviziprva!=null) {
			 flag=false;
			if(dviziprva.element==dvizivtora.element) {
				while(dviziprva!=null && dvizivtora!=null) {
					if(dviziprva.element!=dvizivtora.element) {
						flag=false;
						dvizivtora=vtora.first;
						break;
					}else {
						flag=true;
					dviziprva=dviziprva.succ;
					dvizivtora=dvizivtora.succ;
					}
				}
				if(flag && dvizivtora==null) {
					System.out.println("True");
					return;
				}
			}	
			if(dviziprva!=null) {
				dviziprva=dviziprva.succ;
			}
			
		}
		if(flag && dvizivtora==null) {
			System.out.println("True");
			return;
		}else {
			System.err.println("False");
		}
		
	}
	public static void main(String[] args) throws IOException {
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
		
		ifPodlista(prva, vtora);
	}

	}


