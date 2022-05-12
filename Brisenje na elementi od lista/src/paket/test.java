package paket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class SLLNode <E>{	
	E element;
	SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}
}
class SLL <E>{
	SLLNode <E> first;
	public SLL() {
		first=null;
	}
	public void insertFirst(E element) {
		SLLNode <E> nov=new SLLNode<E>(element,first);
			first=nov;	
			//System.out.println("InsertFirst Complite!");
	}
	public void insertLast(E element) {
		if(this.first==null) {
			insertFirst(element);
		}else {
			SLLNode<E> dvizi=this.first;
			SLLNode<E> nov=new SLLNode<E> (element,null);
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			dvizi.succ=nov;
		}
		//System.out.println("InsertLast Complite!");
	}
	@Override
	public String toString() {
		String string=new String();
		SLLNode <E> dvizi=this.first;
		while(dvizi!=null) {
			if(dvizi.succ!=null) {
			string=string+dvizi.element+"->";
			}else {
				string=string+dvizi.element;
			}
			dvizi=dvizi.succ;
		}
		//System.out.println("toStringComplite");
		return string;
	}
	public void deleteSLL() {
			this.first=null;
	}
	public SLLNode<E> getFirst(){
		return this.first;
	}
	public void deleteFirst() {
		this.first=first.succ;
	}
	public void deleteLast() {
		SLLNode<E> dvizi=this.first;
		while(dvizi.succ.succ!=null) {
			dvizi=dvizi.succ;
		}
			dvizi.succ=null;
	}
	public void deleteAllMatch(E element) {
		SLLNode <E> dvizi=this.first;
		SLLNode <E> pred=null;
		SLLNode <E> posle=null;
		if(dvizi.succ!=null) {
			posle=dvizi;
			dvizi=dvizi.succ;
		}
		if(dvizi.succ!=null) {
			pred=dvizi.succ;
		}else {
			if(posle.element==element) {
				this.first=dvizi;	//samo dokolku nizata e sostavena od eden ili dva elementa
			
			}
		}
		while(dvizi!=null) {
				if(dvizi.element==element) {//find Matching
					posle.succ=pred;
					dvizi=pred;
					if(pred!=null) {
						pred=pred.succ;
					}
				}else {
				posle=dvizi;
				dvizi=pred;
					if(pred!=null) {
						pred=pred.succ;
					}
			
			}
		}
		if(first.element==element) {	//Ako ostane element na pocetakot!
			first=first.succ;
		}
	}
	public void deleteFirstMatch(E element) {
		SLLNode <E> dvizi=first;
		SLLNode	<E> pred=null;
		SLLNode <E> posle=null;
		if(dvizi.succ!=null) {
			posle=dvizi;
			dvizi=dvizi.succ;
			if(dvizi.succ!=null) {
				pred=dvizi.succ;
			}
			while(dvizi!=null) {
				if(posle.element==element) {//samo dokolku e prv elemento
					first=first.succ;
					break;
				}
				if(dvizi.element==element) {
						posle.succ=pred;
						return;
				}else {
					posle=dvizi;
					dvizi=pred;
					if(pred!=null) {
						pred=pred.succ;
					}
					
				}
				
			}
		}
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
	public void spojuvanje(SLL <E> vtora) {
		SLLNode <E> dvizi=this.first;
		if(this.first!=null) {
		while(dvizi.succ!=null) {
			dvizi=dvizi.succ;
		}
			dvizi.succ=vtora.getFirst();
		
		}else {
			first= vtora.getFirst();
		}
	}
}
public class test {

	public static void main(String[] args) throws IOException {
			BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Vnesete gi elementite na nizata odaleceni so prazno mesto!");
			String string=input.readLine();
			String []niza=string.split(" ");
			SLL <Integer> lista=new SLL<Integer>();
			SLL<Integer> vtora=new SLL<Integer>();
			for(int i=0;i<niza.length;i++) {
				int intager=Integer.parseInt(niza[i]);
				System.out.println(intager);
				lista.insertLast(intager);
				vtora.insertLast(intager+2);
				//System.out.println(Integer.parseInt(niza[i]));
			}
		
			System.out.println(lista.toString());
				//lista.deleteSLL();
				//System.out.println("After delete "+lista.toString());
				//lista.deleteFirst();
				//System.out.println(lista.toString());
				//lista.deleteLast();
				//System.out.println(lista.toString());
				//lista.deleteAllMatch(5);
				//System.out.println("After delete "+lista.toString());
				//lista.deleteFirstMatch(5);
				//System.out.println("After delete: "+lista);
				//System.out.println("Lenght on list is "+lista.lenght());
					//lista.spojuvanje(vtora);
					//System.out.println(lista);
	}

}
