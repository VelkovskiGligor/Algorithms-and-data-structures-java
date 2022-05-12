import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLLNode<E>{
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E element , SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}
}
class SLL<E>{
	private SLLNode<E> first;
	public SLL() {
		this.first=null;//Kreiranje prazna lista;
	}
	public void insertFirst(E element) {//Dodavanje na prv element
		SLLNode <E> pom=new SLLNode(element, first);
		this.first=pom;
	}
	public void insertLast(E element) {//Dodavanje na posleden element
		if(this.first==null) {
			insertFirst(element);
		}else {
			SLLNode<E> pom=new SLLNode<E>(element,null);
			SLLNode<E> dvizi=first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			dvizi.succ=pom;
		}
	}
	public void insertAfret(E element,SLLNode<E> node) {//Dodavanje pose daden jazol
		if(node!=null) {
			SLLNode<E> nov=new SLLNode<E>(element,node.succ);
			node.succ=nov;
		}else {
			System.out.println("Prateniot jazol pokazuva na null");
		}
	}
	public E deleteFirst() {//Go brise samo prviot element i go vrakja
		if(first!=null) {
			SLLNode <E> pom=first;
			first=first.succ;
			return  pom.element;
		}else {
			System.err.println("Listata e prazna");
			return null;
		}
	}
	public E delete(SLLNode<E> node) {
		if(this.first!=null) {
			SLLNode<E> dvizi=first;
			while(dvizi!=null  && dvizi.succ!=node ) {
				dvizi=dvizi.succ;
			}
			if(dvizi.succ==node) {
				SLLNode<E> pom =node;
				dvizi.succ=dvizi.succ.succ;
				return pom.element;
				
			}else {
				System.out.println("Ne postoi takov element vo listata");
				return null;
			}
			
		}else {
			System.err.println("Listata e prazna");
			return null;
		}
	}
	public int lenght() {
		if(this.first!=null) {
			int count=0;
			SLLNode<E> dvizi=this.first;
			while(dvizi!=null) {
				count++;
				dvizi=dvizi.succ;
			}
			return count;
		}else {
			return 0;
		}
	}
	public void insertBefore(E o,SLLNode<E> before) {//Postavuva element pred daden jazol
		if(first!=null) {
			SLLNode<E> dvizi=first;
			if(first==before) {
				this.insertFirst(o);
				return;
			}
			while(dvizi!=null && dvizi.succ!=before) {
				dvizi=dvizi.succ;
			}
			if(dvizi.succ==before) {
					SLLNode <E> nov=new SLLNode(o,before);
					dvizi.succ=before;
			}else {
				System.err.println("Ne postoi takoj element");
			}
		}else {
			System.err.println("Listata e prazna");
		}
	}
	public SLLNode find(E e) {
		if(first!=null) {
			SLLNode<E> dvizi=this.first;
			while(dvizi!=null) {
				if(dvizi.element.equals(e)) {
					return dvizi;
				}
				dvizi=dvizi.succ;
			}
		}else {
			System.err.println("Listata e prazna");
			return null;
		}
		return null;
	}
	public void clearList() {
		this.first=null;
	}
	public void merge(SLL<E> list) {//Spojuvanje na listi;
		if(this.first==null) {
			this.first=list.first;
		}else {
			SLLNode<E> dvizi=first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			dvizi.succ=list.first;
		}
	}
	public SLLNode<E> getFirst() {
		return first;
	}
	@Override
public String toString() {
		String s="";
		if(this.first!=null) {
			SLLNode<E> dvizi=this.first;
			while(dvizi!=null) {
				s=s+ dvizi.element +" ";
				dvizi=dvizi.succ;
			}
		
			return s;
		}else {
			return "Listata e prazna";
			
		}
	}

}

public class SpecialSLLJoin {
	public static SLL<Integer> specialJoin(SLL <Integer> lista1,SLL <Integer> lista2) {
		// TODO Auto-generated method stub
		SLLNode<Integer> dviziprva=lista1.getFirst();
		SLLNode<Integer> dvizivtora=lista2.getFirst();
		SLLNode<Integer> predprva=null;
		SLLNode<Integer> predvtora=null;
		SLLNode<Integer> tmp=dviziprva;
		if(dviziprva.succ!=null) {
			predprva=dviziprva.succ;
			tmp=predprva;
			if(predprva.succ!=null) {
				dviziprva=predprva.succ;
					predprva=dviziprva.succ;
			}else {
				dviziprva=null;
			}
		}else {
			dviziprva=null;
		}
		if(dvizivtora.succ!=null) {
			predvtora=dvizivtora.succ;
		}
		while(dviziprva!=null && dvizivtora!=null) {
			tmp.succ=dvizivtora;
			tmp=tmp.succ;
			if(predvtora!=null) {
				
				tmp.succ=predvtora;
				tmp=tmp.succ;
				if(predvtora.succ!=null) {
					dvizivtora=predvtora.succ;
					predvtora=dvizivtora.succ;
				}else {
					dvizivtora=null;
					break;
				}
			}else {
				dvizivtora=null;
				break;
			}
			tmp.succ=dviziprva;
			tmp=tmp.succ;
			if(predprva!=null) {
				tmp.succ=predprva;
				tmp=tmp.succ;
				if(predprva.succ!=null) {
					dviziprva=predprva.succ;
					predprva=dviziprva.succ;
				}else {
					dviziprva=null;
					break;
				}
				
			}else {
				dviziprva=null;
				break;
			}
		}
		if(dviziprva!=null) {
			tmp.succ=dviziprva;
		}
		if(dvizivtora!=null) {
			tmp.succ=dvizivtora;
		}
		return lista1;
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");
		SLL<Integer> lista1=new SLL<Integer>();
		for (int i = 0; i < N; i++) {
			lista1.insertLast(Integer.parseInt(pomniza[i]));
		}

		s = stdin.readLine();
		N = Integer.parseInt(s);
		s = stdin.readLine();
		pomniza = s.split(" ");
		SLL<Integer> lista2=new SLL<Integer> ();
		for (int i = 0; i < N; i++) {
			lista2.insertLast(Integer.parseInt(pomniza[i]));
		}
		
		SLL <Integer >spoeni = specialJoin(lista1,lista2);
		System.out.println(spoeni);
		
	}

	
}
