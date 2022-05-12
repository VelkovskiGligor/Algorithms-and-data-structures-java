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


public class reverst {

	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String recenica=input.readLine();
		String [] niza=recenica.split(" ");
		SLL<String> lista=new SLL<String>();
		for (int i=0;i<niza.length;i++) {
				lista.insertLast(niza[i]);
		}
		System.out.println(lista);
		change(lista);
		System.err.println(lista);
	}

	public static void change(SLL<String> lista) {
		SLLNode<String> prv=lista.getFirst();
		SLLNode<String> predposledan=null;
		SLLNode<String> posledan=null;
		if(prv!= null && prv.succ!=null) {
			posledan=prv.succ;
			predposledan=prv;
		}
		while(posledan.succ!=null) {
			predposledan=posledan;
			posledan=posledan.succ;
		}
		lista.setFirst(posledan);
		posledan.succ=prv.succ;
		predposledan.succ=prv;
		prv.succ=null;
		
	}
	public static SLL<Integer> prevrti(SLL<Integer> lista,int n,int m)
    {
        SLLNode<Integer> prv = null;
        SLLNode<Integer> posleden = null;
        SLLNode<Integer> dvizi = lista.getFirst();
        SLLNode<Integer> pret = null;
        SLLNode<Integer> sled = null;
        int brojac=1;
        while(brojac!=m)
        {
            if(brojac==n-1)
            {
                prv = dvizi;
            }
            if(brojac==n)
            {
                pret = dvizi;
            }
            brojac++;
            dvizi = dvizi.succ;
        }
        posleden = dvizi.succ;
       
        SLLNode<Integer> kraj = pret;
        dvizi= pret.succ;
        while(dvizi!=posleden)
        {
            sled = dvizi.succ;
            dvizi.succ = pret;
            pret = dvizi;
            dvizi = sled;
        }
        if(prv!=null)
        {
            prv.succ = pret;
        }
        else
        {
            lista.setFirst(pret);
        }
        kraj.succ = posleden;
        return lista;
    }


}