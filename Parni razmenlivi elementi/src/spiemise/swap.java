package spiemise;

import java.io.*;

class SLLNode<E>{
	E element;
	SLLNode <E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}			
}
class SLL<E>{
	SLLNode<E> first;
	public SLL() {
		first=null;
	}
	public void insertFirst(E element) {
		SLLNode<E> nov=new SLLNode<E>(element,first);
		this.first=nov;
	}
	public void insertLast(E element) {
		if(first==null) {
			insertFirst(element);
		}else {
			SLLNode<E> dvizi= this.first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			SLLNode<E> nov=new SLLNode<E>(element,null);
			dvizi.succ=nov;
		}
	}
	public String toString() {
		String string=new String();
		SLLNode<E>dvizi=this.first;
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
}

public class swap {
	public static void swaped(SLL<Integer> lista) {
		SLLNode<Integer> prv=lista.first;
		SLLNode<Integer> vtor=prv.succ;
		if(prv.succ==null)return;
		if(vtor.succ==null || vtor==null) {
			if(vtor.element %2 == 0 ){
				vtor.succ=prv;
				lista.first=vtor;
				prv.succ=null;
				
			}else {
				return;
			}
			
		}
		SLLNode<Integer> tret=vtor.succ;
		SLLNode<Integer> cet=null;
		if(tret!=null) {
			cet=tret.succ;
		}

		if(vtor.element%2==0) {
			vtor.succ=prv;
			lista.first=vtor;
			prv.succ=tret;
			prv=vtor;
			vtor=vtor.succ;
			
			
		}

		while(tret!=null) {
			if(tret.element%2==0) {
				tret.succ=vtor;
				prv.succ=tret;
				vtor.succ=cet;
				vtor=tret;
				tret=tret.succ;
				
			}else {
				prv=prv.succ;
				vtor=vtor.succ;
				tret=tret.succ;
				if(cet!=null) {
					cet=cet.succ;
				}else {
					break;
				}
			}
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SLL<Integer> lista=new SLL<Integer>();
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String recenica=input.readLine();
		String[]niza=recenica.split(" ");
		for(int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			lista.insertLast(broj);
		}
		System.out.println(lista);
		System.out.println("After swap");
		swaped(lista);
		System.out.println(lista);
		
	}

}
