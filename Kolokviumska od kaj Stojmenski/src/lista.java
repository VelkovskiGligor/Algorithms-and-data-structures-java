import java.io.*;



//imame SLL lista vo koja dva posledovatelni parni elementi se zbirat
//dodeka dva posedovatelni neparni elementi se odzemat
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
}
public class lista {
	public static void change(SLL<Integer> lista) {
		SLLNode<Integer> dvizi=lista.getFirst();
		SLLNode<Integer> sporedi=null;
		while(dvizi!=null && dvizi.succ!=null) {
			int sum;
			sporedi=dvizi.succ;
			if( dvizi.element %2 == 0 ) {//zberi
				while(sporedi!=null) {
					if(sporedi.element%2==0) {
						sum=dvizi.element+sporedi.element;
						lista.delete(sporedi);
						lista.insertBefore(sum, dvizi);
						SLLNode<Integer>pom=dvizi;
						dvizi=dvizi.succ;
						lista.delete(pom);
						break;
					}else {
						sporedi=sporedi.succ;
						if(sporedi==null) {
							dvizi=dvizi.succ;
						}
					}
				}
			}else {//odzemi
				while(sporedi!=null) {
					if(sporedi.element%2!=0) {
						sum=dvizi.element-sporedi.element;
						lista.delete(sporedi);
						lista.insertBefore(sum, dvizi);
						SLLNode<Integer>pom=dvizi;
						dvizi=dvizi.succ;
						lista.delete(pom);
						break;
					}else {
						sporedi=sporedi.succ;
						if(sporedi==null) {
							dvizi=dvizi.succ;
						}
					}
				}
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		System.out.println("Vnesete gi elementite na nizata");
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		String [] niza=s.split(" ");
		SLL<Integer> lista=new SLL<Integer>();
		for (int i=0;i<niza.length;i++) {
			lista.insertLast(Integer.parseInt(niza[i]));
		}
		System.out.println(lista);
		change(lista);
		System.err.println(lista);
	}

}
