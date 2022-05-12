package dvocifrenibroevi;

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
		
  }


public class digit {
	public static void digit(SLL<Integer> lista) {
		SLLNode<Integer>dvizi=lista.getFirst();
		SLLNode<Integer>pred =null;
		if(dvizi.succ!=null) {
			pred=dvizi.succ;
		}else {
			return;
		}
		while(dvizi.succ!=null) {
			if(dvizi.element>=5) {
					int broj=10*dvizi.element+pred.element;
					lista.insertBefore(broj, pred);
					lista.delete(dvizi);
					dvizi=pred;
					if(pred.succ!=null) {
						dvizi=dvizi.succ;
					}
					lista.delete(pred);
					if(dvizi.succ!=null) {
						pred=dvizi.succ;
					}
			}else {
				dvizi=dvizi.succ;
				pred=pred.succ;
			}
		}
		System.out.println(lista);
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesete gi elementite na prvata niza");
		String string=input.readLine();
		String []niza=string.split(" ");
		SLL <Integer> prva=new SLL<Integer>();
		for (int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			prva.insertLast(broj);
		}
		digit(prva);

	}

}
