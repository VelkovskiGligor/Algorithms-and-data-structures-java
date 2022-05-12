import java.io.*;


class SLLNode<E>{
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ){
		this.element=element;
		this.succ=succ;
		}
	}
class SLL<E>{
	private SLLNode<E> first;
	public SLL() {
		first=null;
	}
	public void insertFirst(E element) {
		SLLNode<E> nov=new SLLNode<E>(element,first);
		first=nov;
	}
	public SLLNode<E> getFirst() {
		return first;
	}
	public void insertLast(E element) {
		if(first==null) {
			insertFirst(element);
			return;
		}
		SLLNode<E> dvizi=first;
		while(dvizi.succ!=null) {
			dvizi=dvizi.succ;
		}
		SLLNode<E> nov=new SLLNode<E> (element,null);
		dvizi.succ=nov;
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
	public String toString() {
		String s="";
		if(first==null) {
			return "Listata e prazna!";
		}else {
			SLLNode<E> dvizi=this.first;
			while(dvizi.succ!=null) {
				s=s+dvizi.element+"->";
				dvizi=dvizi.succ;
			}
			return s=s + dvizi.element;
			
		}
	}
}
class Monom{
	private int koficient;
	private int exponent;
	public Monom(int x,int ex) {
		this.koficient=x;
		this.exponent=ex;
	}
	public int Sporedi(Monom m) {
		if(this.exponent == m.exponent) {
			return 0;
		}else if(this.exponent>m.exponent) {
			return 1;
		}
		else return -1;
		
	}
	
	public int getKoficient() {
		return koficient;
	}
	public void setKoficient(int koficient) {
		this.koficient = koficient;
	}
	public int getExponent() {
		return exponent;
	}
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
	@Override
	public String toString() {
		return "" + koficient + "x^" + exponent + "";
	}
	
}
public class sum {
	public static SLL<Monom> soberi(SLL<Monom> prva,SLL<Monom> vtora){
		SLLNode<Monom> dviziprva=prva.getFirst();
		SLLNode<Monom> dvizivtora=vtora.getFirst();
		SLL <Monom> rezz=new SLL<Monom>();
		while(dviziprva!=null && dvizivtora!=null) {
			if(dviziprva.element.Sporedi(dvizivtora.element)==0) {
				Monom m=new Monom(dviziprva.element.getKoficient()+dvizivtora.element.getKoficient(),dviziprva.element.getExponent());
				rezz.insertLast(m);
				dviziprva=dviziprva.succ;
				dvizivtora=dvizivtora.succ;
			}
			else if(dviziprva.element.Sporedi(dvizivtora.element)==-1) {
				rezz.insertLast(dvizivtora.element);
				dvizivtora=dvizivtora.succ;
			}else{
				rezz.insertLast(dviziprva.element);
				dviziprva=dviziprva.succ;
			}
		}
		if(dvizivtora!=null) {
			
			while(dvizivtora!=null) {
				rezz.insertLast(dvizivtora.element);
				dvizivtora=dvizivtora.succ;
			}
		}
		if(dviziprva!=null) {
			while(dviziprva!=null) {
				rezz.insertLast(dviziprva.element);
				dviziprva=dviziprva.succ;
			}
		}
		
		return rezz;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		String [] niza=s.split(" ");
		SLL<Monom> lista1 =new SLL<Monom>();
		for (int i=0;i<niza.length;i=i+2) {
			Monom m=new Monom(Integer.parseInt(niza [i]),Integer.parseInt(niza[i+1]));
			lista1.insertLast(m);
		}
		String ss=input.readLine();
		String [] ar=ss.split(" ");
		SLL<Monom> lista2 =new SLL<Monom>();
		for (int i=0;i<ar.length;i=i+2) {
			Monom m=new Monom(Integer.parseInt(ar [i]),Integer.parseInt(ar[i+1]));
			lista2.insertLast(m);
		}
		System.out.println(lista1);
		System.out.println(lista2);
			System.err.println(soberi(lista1, lista2));
	}

}
