package vtornacin;

import java.io.*;


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


public class zbiranje {
	public static void soberi(SLL<Integer> lista) {
		SLLNode <Integer> dvizi=lista.getFirst();
		SLLNode <Integer> pred=null;
		SLLNode <Integer> parni=null;
		SLLNode <Integer> neparni=null;
		if(dvizi.element %2 == 0 ) {
			parni=dvizi.succ;
			if(parni == null) {
				return;
			}
			int zbir=0;
			while(parni!=null) {
				if(parni.element %2 == 0 ) {
					zbir=zbir+parni.element;
				}
				else {
					break;
				}
				parni=parni.succ;
			
			}
				zbir+=dvizi.element;
				SLLNode <Integer> nov=new SLLNode<Integer>(zbir,parni);
				lista.setFirst(nov);
				pred=nov;
				dvizi=parni;
		}else {
			
			neparni=dvizi.succ;
			if(neparni == null) {
				return;
			}
			int zbir=0;
			while(neparni!=null) {
				if(neparni.element %2 != 0 ) {
					zbir=zbir+neparni.element;
				}
				else {
					break;
				}
				neparni=neparni.succ;
			}
			
			zbir+=dvizi.element;
				SLLNode <Integer> nov=new SLLNode<Integer>(zbir,neparni);
				lista.setFirst(nov);
				pred=nov;
				dvizi=neparni;
		}
		while(dvizi!=null) {
			
		//
			if(dvizi.element %2 == 0 ) {
				parni=dvizi.succ;
				if(parni == null) {
					return;
				}
				int zbir=0;
				while(parni!=null) {
					if(parni.element %2 == 0 ) {
						zbir=zbir+parni.element;
					}
					else {
						break;
					}
					parni=parni.succ;
				}
				zbir+=dvizi.element;
					SLLNode <Integer> nov=new SLLNode<Integer>(zbir,parni);
					pred.succ=nov;
					pred=nov;
					
					dvizi=parni;
				
			}else {
				
				neparni=dvizi.succ;
				if(neparni == null) {
					return;
				}
				int zbir=0;
				while(neparni!=null) {
					if(neparni.element %2 != 0 ) {
						zbir=zbir+neparni.element;
					}
					else {
						break;
					}
					neparni=neparni.succ;
				}
				zbir+=dvizi.element;
					SLLNode <Integer> nov=new SLLNode<Integer>(zbir,neparni);
					pred.succ=nov;
					pred=nov;
					dvizi=neparni;
			}
			//
		}
		
	}
	
			
				
			
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesete ja listata");
		String recenica=input.readLine();
		String [] niza=recenica.split(" ");
		SLL<Integer> lista=new SLL<Integer>();
		for(int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			lista.insertLast(broj);
		}
		System.out.println(lista);
		soberi(lista);
		System.out.println(lista);
	}

}
