package packet;

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
	 
}
public class glavna	{
	public SLL<Integer> spojListi(SLL<Integer>prva,SLL<Integer> vtora) {
		if(prva.first==null) {
			return vtora;
		}
		if(vtora.first==null) {
			return prva;
		}
		SLLNode<Integer> prvaA=prva.getFirst();
		SLLNode<Integer> vtoraA=vtora.getFirst();
		SLLNode<Integer> prv=null;//ovaj pokazuvac ke bide zamena za first
		SLLNode<Integer> prvaB=null;
		SLLNode<Integer> vtoraB=null;
		
		if(prvaA.element>= vtoraA.element) {
			prv=prvaA;
			vtora.first=null;
			
			}
		else {
			prv=prvaB;
			prva.first=null;
		}
		prvaB=prvaA.succ;
		vtoraB=vtoraA.succ;
		if(prva.lenght()==1) {
			if(prvaA.element>=vtoraA.element) {
				prvaA.succ=vtoraA;
			}else {
				vtoraA.succ=prvaA;
			}
		}
		if(vtora.lenght()==1) {
			if(prvaA.element>=vtoraA.element) {
				prvaA.succ=vtoraA;
		}else {
			vtoraA.succ=prvaA;
		}
		}
		//
		while(vtoraB!=null ||prvaB!=null) {
			if(prvaA.element>=vtoraA.element) {
				prvaA.succ=vtoraA;
				if(prvaB.element>=vtoraB.element) {
					vtoraA.succ=prvaB;
					prvaA=prvaB;
					vtoraA=vtoraB;
					prvaB=prvaB.succ;
					vtoraB=vtoraB.succ;
					prvaA.succ=vtoraA;
					if(vtoraB==null) {
						vtoraA.succ=prvaB;
						break;
					}
					if(prvaB==null) {// i ne e ni potrebno
						vtoraA.succ=vtoraB;
					}
					
				}else {
					vtoraA.succ=vtoraB;
					prvaA=prvaB;
					vtoraA=vtoraB;
					prvaB=prvaB.succ;
					vtoraB=vtoraB.succ;
					vtoraA.succ=prvaA;
					if(prvaB==null) {
						prvaA.succ=vtoraB;
						break;
					}
					if(vtoraB==null) {
						vtoraA.succ=vtoraB;
					}
				}	
			}else {
				vtoraA.succ=prvaA;
				if(prvaB.element>=vtoraB.element) {
					prvaA.succ=prvaB;
					prvaA=prvaB;
					vtoraA=vtoraB;
					prvaB=prvaB.succ;
					vtoraB=vtoraB.succ;
					prvaA.succ=vtoraA;
					if(vtoraB==null) {
						vtoraA.succ=prvaB;
						break;
					}
					if(prvaB==null) {
						vtoraA.succ=vtoraB;
					}
				}else {
					prvaA.succ=vtoraB;
					prvaA=prvaB;
					vtoraA=vtoraB;
					prvaB=prvaB.succ;
					vtoraB=vtoraB.succ;
					vtoraA.succ=prvaA;
					if(vtoraB==null) {
						prvaA.succ=prvaB;
						break;
					}
					if(prvaB==null) {
						prvaA.succ=vtoraB;
					}
				}
				
			}
			if(vtoraB==null || prvaB==null) {
				break;
			}
			
		}
		
		
		//
		if(prva.first==null) {
			return vtora;
		}else {
			return prva;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesete gi elementite na prvata niza");
		String string=input.readLine();
		String []niza=string.split(" ");
		SLL <Integer> prva=new SLL<Integer>();
		for (int i=0;i<niza.length;i++) {
			int broj=Integer.parseInt(niza[i]);
			prva.insertLast(broj);
		}
		System.out.println(prva);
		SLL <Integer> vtora=new SLL<Integer>();
		String recenica=new String();
		System.out.println("Vnesete gi elementite na vtorata lista");
		recenica=input.readLine();
		String []arr=recenica.split(" ");
		for(int i=0;i<arr.length;i++) {
			int br=Integer.parseInt(arr[i]);
			vtora.insertLast(br);
		}
		System.out.println(vtora);
		
		glavna gla=new glavna();
		 System.out.println(gla.spojListi(prva, vtora));
		
	}

}
