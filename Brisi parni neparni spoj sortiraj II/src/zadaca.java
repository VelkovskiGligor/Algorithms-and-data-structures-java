import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

 class SLL<E> {
	private SLLNode<E> first;

	public SLL() {
		// Construct an empty SLL
		this.first = null;
	}

	public void deleteList() {
		first = null;
	}

	public int length() {
		int ret;
		if (first != null) {
			SLLNode<E> tmp = first;
			ret = 1;
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret++;
			}
			return ret;
		} else
			return 0;

	}

	@Override
	public String toString() {
		String ret = new String();
		if (first != null) {
			SLLNode<E> tmp = first;
			ret += tmp + "->";
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret += tmp + "->";
			}
		} else
			ret = "Prazna lista!!!";
		return ret;
	}

	public void insertFirst(E o) {
		SLLNode<E> ins = new SLLNode<E>(o, first);
		first = ins;
	}

	public void insertAfter(E o, SLLNode<E> node) {
		if (node != null) {
			SLLNode<E> ins = new SLLNode<E>(o, node.succ);
			node.succ = ins;
		} else {
			System.out.println("Dadenot jazol e null");
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

	public void insertLast(E o) {
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp.succ != null)
				tmp = tmp.succ;
			SLLNode<E> ins = new SLLNode<E>(o, null);
			tmp.succ = ins;
		} else {
			insertFirst(o);
		}
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

	public SLLNode<E> getFirst() {
		return first;
	}
	
	public SLLNode<E> find(E o) {
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp.element != o && tmp.succ != null)
				tmp = tmp.succ;
			if (tmp.element == o) {
				return tmp;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
		return first;
	}
	
   

	// //////////Inner class ////////////

	
	
	public void mirror(){
		if (first != null) {
			//m=nextsucc, p=tmp,q=next
			SLLNode<E> tmp = first;
			SLLNode<E> newsucc = null;
			SLLNode<E> next;
			
			while(tmp != null){
				next = tmp.succ;
				tmp.succ = newsucc;
				newsucc = tmp;
				tmp = next;
			}
			first = newsucc;
		}
		
	}
	
	public void merge (SLL<E> in){
		if (first != null) {
			SLLNode<E> tmp = first;
			while(tmp.succ != null)
				tmp = tmp.succ;
			tmp.succ = in.getFirst();
		}
		else{
			first = in.getFirst();
		}
	}

	public void setFirst(SLLNode<E> nazad) {
		first=nazad;
		
	}
}

public class zadaca {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String prv=input.readLine();
		SLL<Integer> prva=new SLL<Integer>();
		String []  niza=prv.split(" ");
		for (int i=0;i<niza.length;i++) {
				prva.insertLast(Integer.parseInt(niza[i]));
		}
		
		String vtor=input.readLine();
		SLL<Integer> vtora=new SLL<Integer>();
		String []  pom=vtor.split(" ");
		for (int i=0;i<pom.length;i++) {
				vtora.insertLast(Integer.parseInt(pom[i]));
		}
	
		change(prva,vtora);
	}

	public static void change(SLL<Integer> prva, SLL<Integer> vtora) {
		SLLNode<Integer> dviziprva=prva.getFirst();
		SLLNode<Integer> pom=null;
		while(dviziprva!=null) {
			if(dviziprva.element%2!=0) {
				pom=dviziprva;
				if(dviziprva!=null) {
				dviziprva=dviziprva.succ;
				}
				prva.delete(pom);
			}
			if(dviziprva!=null) {
				dviziprva=dviziprva.succ;
				}
		}
		SLLNode<Integer> dvizivtora=vtora.getFirst();
		while(dvizivtora!=null) {
			if(dvizivtora.element%2==0) {
				pom=dvizivtora;
				if(dvizivtora!=null) {
				dvizivtora=dvizivtora.succ;
				}
				vtora.delete(pom);
			}
			if(dvizivtora!=null) {
				dvizivtora=dvizivtora.succ;
				}
		}
		System.err.println(spoj(prva,vtora));
	}

	public static SLL spoj(SLL<Integer> prva, SLL<Integer> vtora) {
			SLL <Integer> rezz=null;
			SLLNode <Integer> dviziprva=prva.getFirst();
			SLLNode <Integer> dvizivtora=vtora.getFirst();
			SLLNode<Integer> dvizirezz=null;
			if(dviziprva.element>= dvizivtora.element) {
				rezz=prva;
				dvizirezz=dviziprva;
				if(dviziprva!=null) {
					dviziprva=dviziprva.succ;
				}
			}else {
				dvizirezz=dvizivtora;
				rezz=vtora;
				if(dvizivtora!=null) {
					dvizivtora=dvizivtora.succ;
				}
			}
			while(dviziprva!=null && dvizivtora!=null) {
				if(dviziprva.element>= dvizivtora.element) {
					dvizirezz.succ=dviziprva;
					dvizirezz=dvizirezz.succ;
					if(dviziprva!=null) {
						dviziprva=dviziprva.succ;
					}
				}else {
					dvizirezz.succ=dvizivtora;
					dvizirezz=dvizirezz.succ;
					if(dvizivtora!=null) {
						dvizivtora=dvizivtora.succ;
					}
				}
			}
			if(dviziprva!=null) {
				dvizirezz.succ=dviziprva;
			}
			if(dvizivtora!=null) {
				dvizirezz.succ=dvizivtora;
			}
		return rezz;
	}


}
