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

	public void setFirst(SLLNode<E> x) {
		this.first=x;
		
	}
}
public class podeli {

	public static void change(SLL<Integer> lista) {
		SLLNode<Integer> dvizi=lista.getFirst();
		SLL<Integer> pomali=null;
		SLL<Integer> pogolemi=null;
		SLLNode<Integer>dvizipomali=null;
		SLLNode<Integer>dvizipogolemi=null;
		int len=lista.length();
		int prosek=0;
		while(dvizi!=null) {
			prosek+=dvizi.element;
			dvizi=dvizi.succ;
		}
		prosek=prosek/len;
		dvizi=lista.getFirst();
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
					pogolemi=new SLL<Integer>();
					pogolemi.setFirst(dvizi);
					dvizipogolemi=dvizi;
				}else {
					dvizipogolemi.succ=dvizi;
					dvizipogolemi=dvizi;	
				}
				if(dvizi!=null) {
					dvizi=dvizi.succ;
				}
			}else {
				if(pomali==null) {
					pomali=new SLL<Integer>();
					pomali.setFirst(dvizi);
					dvizipomali=dvizi;
							
				}else {
					dvizipomali.succ=dvizi;
					dvizipomali=dvizi;
				}
				if(dvizi!=null) {
					dvizi=dvizi.succ;
				}
			}
		}
		if(dvizipogolemi!=null) {
			dvizipogolemi.succ=null;
		}
		if(dvizipomali!=null) {
			dvizipomali.succ=null;
		}
		System.err.println("Pogolemi: "+ pogolemi);
		System.err.println("Pomali: "+ pomali);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		String [] niza=s.split(" ");
		SLL<Integer> lista=new SLL<Integer>();
		for (int i=0;i<niza.length;i++) {
			lista.insertLast(Integer.parseInt(niza[i]));
		}
			change(lista);

	}


}
