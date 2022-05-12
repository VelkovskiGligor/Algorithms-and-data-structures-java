package polinom;

import java.io.*;

class SLLNode<E>{
	E vrednost;
	E stepen;
	SLLNode<E> succ;
	public SLLNode(E vrednost,E stepen,SLLNode<E> succ) {
				this.vrednost=vrednost;
				this.stepen=stepen;
				this.succ=succ;
	}
}
class SLL<E>{
	SLLNode<E> first;
	public SLL() {
		first=null;
	}
	public void insertFirst(E vrednost,E stepen) {
		SLLNode<E>nov=new SLLNode<E>(vrednost,stepen,first);
		this.first=nov;
	}
	public void insertLast(E vrednost,E stepen) {
		if(this.first==null) {
			insertFirst(vrednost, stepen);
		}else {
			SLLNode<E> nov=new SLLNode<E>(vrednost,stepen,null);
			SLLNode<E> dvizi=this.first;
			while(dvizi.succ!=null) {
				dvizi=dvizi.succ;
			}
			dvizi.succ=nov;
		}
	}
	public String toString() {
		String string=new String();
		SLLNode<E> dvizi=this.first;
		while(dvizi!=null) {
			string=string+dvizi.vrednost+"X^"+dvizi.stepen+" ";
			dvizi=dvizi.succ;
		}
		return string;
	}
}
public class polinok {
	public static void spoj(SLL<Integer> prva,SLL<Integer> vtora) {
		SLL<Integer>nova=new SLL<Integer>();
		SLLNode<Integer> dviziprva=prva.first;
		SLLNode<Integer> dvizivtora=vtora.first;
		SLLNode<Integer> novadvizi=null;

		//if(dviziprva.stepen>=dvizivtora.stepen){
		//	nova=prva;
			//novadvizi=prva.first;
		//}else {
		//	 nova=vtora;
		//	 novadvizi=vtora.first;
		//}
		while(dviziprva!=null && dvizivtora!=null) {
			
			if(dviziprva.stepen==dvizivtora.stepen){
				int vrednost=dviziprva.vrednost+dvizivtora.vrednost;
				nova.insertLast(vrednost,dviziprva.stepen);
				dviziprva=dviziprva.succ;
				dvizivtora=dvizivtora.succ;
			
			}
			else if(dviziprva.stepen>dvizivtora.stepen) {
				nova.insertLast(dviziprva.vrednost, dviziprva.stepen);
				dviziprva=dviziprva.succ;
			}
			else if(dvizivtora.stepen > dviziprva.stepen ) {
				nova.insertLast(dvizivtora.vrednost,dvizivtora.stepen);
				dvizivtora=dvizivtora.succ;
			}
		}
	
		if(dviziprva!=null) {
			while(dviziprva!=null) {
			nova.insertLast(dviziprva.vrednost, dviziprva.stepen);
			dviziprva=dviziprva.succ;
			}
		}
		if(dvizivtora!=null) {
			while(dvizivtora!=null) {
				nova.insertLast(dvizivtora.vrednost,dvizivtora.stepen);
			dvizivtora=dvizivtora.succ;
			}
		}
		System.out.println("Novo dobienata niza e:" +nova);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesi go prviot string");
		String prvarecenica=input.readLine();
		String []prvaniza=prvarecenica.split(" ");
		SLL <Integer> prvalista=new SLL<Integer>();
		SLL<Integer> vtoralista=new SLL<Integer>();
		for (int i=0;i<prvaniza.length;i+=2) {
				int vrednost=Integer.parseInt(prvaniza[i]);
				int stepen=Integer.parseInt(prvaniza[i+1]);
				prvalista.insertLast(vrednost, stepen);
			
		}
		System.out.println("Vnesete ja vtorata lista");
		String vtorarecenica=input.readLine();
		String []vtoraniza=vtorarecenica.split(" ");
		for (int i=0;i<vtoraniza.length;i+=2) {
			int vrednost=Integer.parseInt(vtoraniza[i]);
			int stepen=Integer.parseInt(vtoraniza[i+1]);
			vtoralista.insertLast(vrednost, stepen);
		
	}
		System.out.println(prvalista);
		System.out.println(vtoralista);
		spoj(prvalista, vtoralista);

	}

}