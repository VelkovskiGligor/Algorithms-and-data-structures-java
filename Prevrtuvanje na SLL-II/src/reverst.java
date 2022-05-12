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
		this.first=null;
	}
	public void insertFirst(E element) {
		SLLNode<E> nov=new SLLNode<E>(element,first);
		this.first=nov;
		}
	public void insertLast(E element) {
		if(first==null) {
			insertFirst(element);
		}else {
			SLLNode<E>dvizi=first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			SLLNode<E> nov=new SLLNode<E>(element,null);
			dvizi.succ=nov;
		}
	}
	public String toString() {
		String s="";
		if(first!=null) {
			SLLNode<E> dvizi=first;
			while(dvizi.succ!=null) {
				s=s+ dvizi.element+ "->";
				dvizi=dvizi.succ;
			}
			s=s+ dvizi.element;
			return s;
		}else {
			return "Listata e prazna!";
		}
	}
	public SLLNode<E> getFirst(){
		return this.first;
	}
	public int lenght() {
		int brojac=0;
		SLLNode<E> dvizi=first;
		while(dvizi!=null) {
			brojac++;
			dvizi=dvizi.succ;
		}
		return brojac;
	}
	public void setFirst(SLLNode<E> node) {
		first=node;
	}
}
public class reverst {
	public  static void reverst(SLL<Integer> lista) {
		SLLNode <Integer> dvizi=lista.getFirst();
		if(lista.lenght()>1) {
			SLLNode<Integer> before=dvizi;
			dvizi=dvizi.succ;
			before.succ=null;
			SLLNode<Integer> after=dvizi.succ;
			
			while(dvizi!=null) {
				dvizi.succ=before;
				before=dvizi;
				dvizi=after;
				if(after!=null) {
					after=after.succ;
				}
			}
			lista.setFirst(before);
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String s=input.readLine();
		String[] niza=s.split(" ");
		SLL <Integer> lista=new SLL<Integer>();
		for (int i=0;i<niza.length;i++) {
			lista.insertLast(Integer.parseInt(niza[i]));
		}
		System.out.println(lista);
		reverst(lista);
		System.err.println(lista);
	}

}
