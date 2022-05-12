		import java.io.BufferedReader;
		import java.io.IOException;
		import java.io.InputStreamReader;
		import java.util.Iterator;
		import java.util.NoSuchElementException;
		
		
		
		class SLLNode<E>{
			protected E element;
			protected SLLNode<E> succ;
			public SLLNode(E element , SLLNode<E> succ) {
				this.element=element;
				this.succ=succ;
			}
		}
		class SLL<E>{
			private SLLNode<E> first;
			public SLL() {
				this.first=null;//Kreiranje prazna lista;
			}
			public void insertFirst(E element) {//Dodavanje na prv element
				SLLNode <E> pom=new SLLNode(element, first);
				this.first=pom;
			}
			public void insertLast(E element) {//Dodavanje na posleden element
				if(this.first==null) {
					insertFirst(element);
				}else {
					SLLNode<E> pom=new SLLNode<E>(element,null);
					SLLNode<E> dvizi=first;
					while(dvizi.succ!=null) {
						dvizi=dvizi.succ;
					}
					dvizi.succ=pom;
				}
			}
			public void insertAfret(E element,SLLNode<E> node) {//Dodavanje pose daden jazol
				if(node!=null) {
					SLLNode<E> nov=new SLLNode<E>(element,node.succ);
					node.succ=nov;
				}else {
					System.out.println("Prateniot jazol pokazuva na null");
				}
			}
			public E deleteFirst() {//Go brise samo prviot element i go vrakja
				if(first!=null) {
					SLLNode <E> pom=first;
					first=first.succ;
					return  pom.element;
				}else {
					System.err.println("Listata e prazna");
					return null;
				}
			}
			public void delete(SLLNode<E> node){
				 if (first != null){
				 SLLNode<E> tmp = first;
				 while (tmp.succ != node &&
				tmp.succ.succ != null)
				 tmp = tmp.succ;
				 if (tmp.succ == node){
				 tmp.succ = tmp.succ.succ;
				 return ;
				 }
				 
				 // else throw Exception;
				 }
			
				 //else throw Exception;
				}
		
			public int lenght() {
				if(this.first!=null) {
					int count=0;
					SLLNode<E> dvizi=this.first;
					while(dvizi!=null) {
						count++;
						dvizi=dvizi.succ;
					}
					return count;
				}else {
					return 0;
				}
			}
			public void insertBefore(E o,SLLNode<E> before) {//Postavuva element pred daden jazol
				if(first!=null) {
					SLLNode<E> dvizi=first;
					if(first==before) {
						this.insertFirst(o);
						return;
					}
					while(dvizi!=null && dvizi.succ!=before) {
						dvizi=dvizi.succ;
					}
					if(dvizi.succ==before) {
							SLLNode <E> nov=new SLLNode(o,before);
							dvizi.succ=before;
					}else {
						System.err.println("Ne postoi takoj element");
					}
				}else {
					System.err.println("Listata e prazna");
				}
			}
			public SLLNode find(E e) {
				if(first!=null) {
					SLLNode<E> dvizi=this.first;
					while(dvizi!=null) {
						if(dvizi.element.equals(e)) {
							return dvizi;
						}
						dvizi=dvizi.succ;
					}
				}else {
					System.err.println("Listata e prazna");
					return null;
				}
				return null;
			}
			public void clearList() {
				this.first=null;
			}
			public void merge(SLL<E> list) {//Spojuvanje na listi;
				if(this.first==null) {
					this.first=list.first;
				}else {
					SLLNode<E> dvizi=first;
					while(dvizi.succ!=null) {
						dvizi=dvizi.succ;
					}
					dvizi.succ=list.first;
				}
			}
			public SLLNode<E> getFirst() {
				return first;
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
		
		}
		public class SLLJoinLists {
			public static SLL<Integer> spoj(SLL<Integer> prva,SLL<Integer> vtora) {
				SLLNode<Integer> dviziprva=prva.getFirst();
				SLLNode<Integer> dvizivtora=vtora.getFirst();
				SLL<Integer> rezultatna=null;
				if(dviziprva.element< dvizivtora.element) {
					rezultatna=prva;
					if(dviziprva!=null) {
						dviziprva=dviziprva.succ;
					}
				}else {
					rezultatna=vtora;
					if(dvizivtora!=null) {
						dvizivtora=dvizivtora.succ;
					}
				}
				SLLNode<Integer> tmp=rezultatna.getFirst();
				while(dviziprva!=null && dvizivtora!=null) {
					if(dviziprva.element==dvizivtora.element) {
						tmp.succ=dviziprva;
						dviziprva=dviziprva.succ;
						dvizivtora=dvizivtora.succ;
						tmp=tmp.succ;
					}else { 
						if(dviziprva.element<dvizivtora.element) {
							tmp.succ=dviziprva;
							dviziprva=dviziprva.succ;
						}else {
							tmp.succ=dvizivtora;
							dvizivtora=dvizivtora.succ;
						}
						tmp=tmp.succ;
					}
				}
				if(dviziprva!=null) {
					tmp.succ=dviziprva;
				}
				if(dvizivtora!=null) {
					tmp.succ=dvizivtora;
				}
				return rezultatna;
			}
			public static SLL izbrisiduplikati(SLL<Integer> lista) {
				SLLNode<Integer> dvizi=lista.getFirst();
				SLLNode<Integer> pom=null;;
				SLLNode<Integer> pred=dvizi;
				
				while(dvizi!=null && dvizi.succ!=null) {
					pom=dvizi.succ;
					pred=dvizi;
					while(pom!=null) {
						if(dvizi.element==pom.element) {
							pred.succ=pom.succ;
							pom=pred.succ;
						}else {
							pred=pred.succ;
							if(pom!=null) {
								pom=pom.succ;
									}
						}
					}
					dvizi=dvizi.succ;
				}
				//System.out.println(lista);
				return lista;
			}
			public static void main(String[] args) throws IOException {
		
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				String s = stdin.readLine();
				int N = Integer.parseInt(s);
				s = stdin.readLine();
				SLL<Integer> lista1=new SLL<Integer>();
				String[] pomniza = s.split(" ");
				for (int i = 0; i < N; i++) {
					lista1.insertLast(Integer.parseInt(pomniza[i]));
				}
		
				s = stdin.readLine();
				N = Integer.parseInt(s);
				s = stdin.readLine();
				pomniza = s.split(" ");
				SLL<Integer> lista2=new SLL<Integer>();
				for (int i = 0; i < N; i++) {
					lista2.insertLast(Integer.parseInt(pomniza[i]));
				}
				SLL <Integer>  rezz=spoj(lista1,lista2);
				System.out.println(izbrisiduplikati(rezz));
			}
			
		}
