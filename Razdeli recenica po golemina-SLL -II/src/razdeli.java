import  java.io.*;




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
	public void setFirst(SLLNode<E> f) {
		this.first=f;
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
}

public class razdeli {
	public static void razdeli(SLL<String> lista,int x) {
		SLL<String> pomali=null;
		SLL<String> pogolemi=null;
		SLLNode<String> dvizi=lista.getFirst();
		SLLNode<String> dvizipomali=null;
		SLLNode<String> dvizipogolemi=null;
		if(dvizi.element.length()>x) {
			pogolemi=lista;
			dvizipogolemi=pogolemi.getFirst();
		}else {
			pomali=lista;
			dvizipomali=pomali.getFirst();
		}
		if(dvizi!=null) {
			dvizi=dvizi.succ;
		}
		while(dvizi!=null) {
			if(dvizi.element.length()>x) {//pogolemi
				if(pogolemi==null) {
					pogolemi=new SLL<String>();
					pogolemi.setFirst(dvizi);
					dvizipogolemi=pogolemi.getFirst();
				}else {
					dvizipogolemi.succ=dvizi;
					dvizipogolemi=dvizipogolemi.succ;
				}
				
			}else {//pomali
				if(pomali==null) {
					pomali=new SLL<String> ();
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
		System.err.println("Pomali: "+pomali);		
		System.err.println("Pogolemi: "+pogolemi);
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		String [] niza=s.split("\\W+");
		SLL <String> lista=new SLL<String> ();
		for (int i=0;i<niza.length;i++) {
			lista.insertLast(niza[i]);
		}
		System.out.println(lista);
		razdeli(lista,5);
	} 
}
