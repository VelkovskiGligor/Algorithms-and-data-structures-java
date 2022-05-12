package changeList;

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

		public E deleteFirst() {
			if (first != null) {
				SLLNode<E> tmp = first;
				first = first.succ;
				return tmp.element;
			} else {
				System.out.println("Listata e prazna");
				return null;
			}
		}
		public E delete(SLLNode<E> node) {
			if (first != null) {
				SLLNode<E> tmp = first;
				if(first ==node){
					return this.deleteFirst();
				}
				while (tmp.succ != node && tmp.succ.succ != null)
					tmp = tmp.succ;
				if (tmp.succ == node) {
					tmp.succ = tmp.succ.succ;
					return node.element;
				} else {
					System.out.println("Elementot ne postoi vo listata");
					return null;
				}
			} else {
				System.out.println("Listata e prazna");
				return null;
			}

		}
	 public void insertBefore(E o, SLLNode<E> before) {
			
			if (first != null) {
				SLLNode<E> tmp = first;
				if(first==before){
					this.insertFirst(o);
					return;
				}
				//ako first!=before
				while (tmp.succ != before)
					tmp = tmp.succ;
				if (tmp.succ == before) {
					SLLNode<E> ins = new SLLNode<E>(o, before);
					tmp.succ = ins;
				} else {
					System.out.println("Elementot ne postoi vo listata");
				}
			} else {
				System.out.println("Listata e prazna");
			}
		}

	
}
public class changeList {
	public static void changeList(SLL<Integer> lista) {
	SLLNode<Integer> dvizi=lista.first;
	SLLNode<Integer> next=dvizi;
	SLLNode<Integer> prednext=null;
	SLLNode<Integer> posedvizi=null;
	if(lista.lenght()==2)return;
	while(dvizi.succ!=null){
		posedvizi=dvizi.succ;
		if(posedvizi.succ==null)break;
		next=dvizi.succ;
		prednext=dvizi;
		while(next.succ!=null) {
			next=next.succ;
			prednext=prednext.succ;
		}
		dvizi.succ=next;
		next.succ=posedvizi;
		prednext.succ=null;
		dvizi=posedvizi;
		
	}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Vnesi ja listata");
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String recenica=input.readLine();
		String []niza=recenica.split(" ");
		SLL <Integer> lista=new SLL<Integer>();
		for (int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			lista.insertLast(broj);
		}
		System.out.println(lista);
		changeList(lista);
		System.out.println("After chanfe");
		System.out.println(lista);
		
	}

}
