package prevrtuvanje;

import java.io.*;


class SLLNode <E>{
	E element;
	SLLNode <E> succ;
	public SLLNode(E element,SLLNode <E> succ){
		this.element=element;
		this.succ=succ;
	}
}
class SLL<E>{
	SLLNode <E> first;
	public SLL() {
		first=null;
	}
	public void insertFirst(E element) {
		SLLNode <E> nov=new SLLNode<E>(element,first);
		first=nov;
	}
	public void insertLast(E element) {
		if(first==null) {
			insertFirst(element);
		}else {
			SLLNode<E> nov=new SLLNode<E>(element,null);
			SLLNode<E> dvizi=first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			dvizi.succ=nov;
		}
	}
	public String toString() {
		SLLNode <E> dvizi=this.first;
		String string=new String();
		while(dvizi!=null) {
			if(dvizi.succ!=null) {
			string=string+dvizi.element+"->";
			}else {
				string=string+dvizi.element;
			}
			dvizi=dvizi.succ;
		}
			return string;
	}
	public void prevrtiLista() {
		SLLNode<E> dvizi=this.first;
		SLLNode<E> predhodnik=null;
		SLLNode<E> sledbenik=null;
		if(dvizi.succ!=null) {
				predhodnik=dvizi;
				dvizi=dvizi.succ;
				sledbenik=dvizi.succ;
				predhodnik.succ=null;
			}
		while(dvizi!=null) {
			if(dvizi.succ==null) {
				first=dvizi;
			}
			dvizi.succ=predhodnik;
			predhodnik=dvizi;
			dvizi=sledbenik;
			
			if(sledbenik!=null) {
				sledbenik=sledbenik.succ;
			}
				
			
			
		}
		System.out.println("Finish!");
	
	}
}

public class prevrtuvanje {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesete gi elementite na listata");
		String string=input.readLine();
		String [] niza=string.split(" ");
		SLL<Integer> lista=new SLL<Integer>();
		for(int i=0;i<niza.length;i++) {
			int integer=Integer.parseInt(niza[i]);
			lista.insertLast(integer);
		}
		System.out.println(lista);
		System.err.println("Afret prevrtuvanje");
		lista.prevrtiLista();
		System.out.println(lista);

	}

}
