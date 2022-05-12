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
	 
	 
}
public class Listi  {
	public static SLL<String> spoj(SLL <String> prva,SLL<String> vtora){
			SLLNode<String> dviziprva=prva.getFirst();
			SLLNode<String> dvizivtora=vtora.getFirst();
			SLLNode<String> momentalno=null;
			if(dviziprva==null) {
				return vtora;
			}
			if(dvizivtora==null) {
				return prva;
			}
			SLL<String> rezultat=null;
			if(dviziprva.element.compareTo(dvizivtora.element)>0) {
				rezultat=prva;
				momentalno=prva.getFirst();
				dviziprva=dviziprva.succ;
				
			}else {
				rezultat=vtora;
				momentalno=vtora.getFirst();
				dvizivtora=dvizivtora.succ;
			}
			
			SLLNode<String> predprva=null;
			SLLNode<String> predvtora=null;
			
			
			while(dviziprva!=null && dvizivtora !=null) {
				//predprva=dviziprva.succ;
				//predvtora=dvizivtora.succ;
				
				if(dviziprva.element.compareTo(dvizivtora.element)>0) {
						momentalno.succ=dviziprva;
						dviziprva=dviziprva.succ;
						momentalno=momentalno.succ;
				}else {
					momentalno.succ=dvizivtora;
					dvizivtora=dvizivtora.succ;
					momentalno=momentalno.succ;
					
				}
			}
			if(dviziprva!=null) {
				momentalno.succ=dviziprva;
				dviziprva=dviziprva.succ;
			}
			if(dvizivtora!=null) {
				momentalno.succ=dvizivtora;
				dvizivtora=dvizivtora.succ;
			}
		
		return rezultat;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesete gi elementite na prvata lista");
		String s=input.readLine();
		String []niza=s.split(" ");
		SLL<String> prva=new SLL<String>();
		for (int i=0;i<niza.length;i++) {
					prva.insertLast(niza[i]);
		}
		System.out.println("Vnesete gi elementite na vtorata lista");
		String a=input.readLine();
		String []nizaa=a.split(" ");
		SLL<String> vtora=new SLL<String>();
		for (int i=0;i<nizaa.length;i++) {
					vtora.insertLast(nizaa[i]);
		}
			System.out.println(prva);
			System.out.println(vtora);
			System.err.println(spoj(prva,vtora));

	}

}
