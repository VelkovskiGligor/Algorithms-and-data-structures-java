package numbers;
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
		public void insertAfter(E o, SLLNode<E> node) {
			if (node != null) {
				SLLNode<E> ins = new SLLNode<E>(o, node.succ);
				node.succ = ins;
			} else {
				System.out.println("Dadenot jazol e null");
			}
		}

	 
	 
}
public class numbers {
	public static void change(SLL<Integer> lista) {
		SLLNode<Integer>dvizi=lista.getFirst();
		SLLNode<Integer> go=dvizi;
		SLLNode<Integer> hit=null;
		int sum=0;
		while(dvizi!=null) {
			//System.out.println(dvizi.element);
			if(dvizi.element!=-1) {
				go=dvizi;
				//System.err.println(go.element);
				while(go!=null && go.element!=-1) {
					sum=sum*10+go.element;
					go=go.succ;
				}
				System.out.println("sumata: "+sum);
				lista.insertAfter(sum, dvizi);
				SLLNode<Integer> pom=dvizi.succ;
				lista.delete(dvizi);
				dvizi=pom;
				System.err.println("DVIZI "+dvizi.element);
				if(go!=null) {
				dvizi.succ=go.succ;
				}else {
					dvizi.succ=null;
				}
				sum=0;
				dvizi=dvizi.succ;
				System.out.println(lista);
			}else {
				SLLNode<Integer> pom=dvizi.succ;
				lista.delete(dvizi);
				dvizi=pom;
				
			}
			if(dvizi==null) {
				break;
			}
		}
		System.err.println(lista);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		SLL<Integer> lista=new SLL<Integer>();
		String recenica=input.readLine();
		String [] niza=recenica.split(" ");
		for (int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			lista.insertLast(broj);
		}
		System.out.println(lista);
		
		change(lista);
	}

}
