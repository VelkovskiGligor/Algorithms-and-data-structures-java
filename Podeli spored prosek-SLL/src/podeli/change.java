package podeli;

import java.io.*;

class SLLNode<E>{
	E element;
	SLLNode<E> succ;
	public SLLNode(E element,SLLNode <E> succ) {
		this.element=element;
		this.succ=succ;
	}
	
}
class SLL <E>{
	SLLNode <E> first;
	public SLL() {
		this.first=null;
	}
	public void insertFirst(E element) {
		SLLNode <E> nov=new SLLNode<E>(element,first);
		first=nov;
	}
	public void insertLast(E element) {
		SLLNode <E> dvizi=first;
		if(first==null) {
			insertFirst(element);
		}else {
		while(dvizi.succ!=null) {
			dvizi=dvizi.succ;
		}
		SLLNode <E> nov=new SLLNode <E>(element,null);
		dvizi.succ=nov;
		}
	}
	 public String toString() {
		 String string=new String();
		 if(first==null) {
			 return "Listata e prazna";
		 }else {
			 SLLNode <E> dvizi=first;
			 while(dvizi!=null) {
				 if(dvizi.succ!=null) {
					 string=string+dvizi.element+"->";
				 }else {
					 string=string+dvizi.element;
				 }
				 dvizi=dvizi.succ;
			 }
		 }
		 return string;
	 }
	 public SLLNode<E> getFirst(){
		 return this.first;
	 }
	 public void setFirst(SLLNode<E> here){
		 this.first=here;
		 
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
public class change {
	public static int getProsek(SLL<Integer> lista) {
		 int broj=0;
		 SLLNode<Integer> dvizi=lista.first;
		 while(dvizi!=null) {
			 
			 broj+=dvizi.element;
			 dvizi=dvizi.succ;
			 
		 }
		 return broj/lista.lenght();
	}
	public static void change(SLL<Integer> lista) {
		int prosek=getProsek(lista);
		System.out.println(prosek);
		SLL<Integer> pomali=null;
		SLL<Integer> pogolemi=null;
		SLLNode<Integer> dvizi=lista.getFirst();
		SLLNode<Integer> dvizipomali=null;
		SLLNode<Integer> dvizipogolemi=null;
		if(dvizi.element>prosek) {
			pogolemi=lista;
			dvizipogolemi=pogolemi.getFirst();
		}else {
			pomali=lista;
			dvizipomali=pomali.getFirst();
		}
		dvizi=dvizi.succ;
		while(dvizi!=null) {
			if(dvizi.element>prosek) {
				if(pogolemi==null) {
				pogolemi=new SLL <Integer>();
				pogolemi.setFirst(dvizi);
				dvizipogolemi=pogolemi.getFirst();
				}else {
					dvizipogolemi.succ=dvizi;
					dvizipogolemi=dvizi;
				}
			}else {
				if(pomali==null) {
				pomali=new SLL<Integer>();
				pomali.setFirst(dvizi);
				dvizipomali=pomali.getFirst();
				}else {
					dvizipomali.succ=dvizi;
					dvizipomali=dvizi;
				}
			}
			
			dvizi=dvizi.succ;
		}
		if(dvizipogolemi!=null) {
			dvizipogolemi.succ=null;
		}
		if(dvizipomali!=null) {
			dvizipomali.succ=null;
		}
		System.err.println("Pomali "+pomali);
		System.out.println("Pogolemi "+pogolemi);
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		SLL<Integer> lista=new SLL<Integer>();
		String recenica=input.readLine();
		String [] niza=recenica.split(" ");
		for (int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			lista.insertLast(broj);
		}
		System.err.println(lista);
		change(lista);
		
	}

}
