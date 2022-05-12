package listi;
	class SLLNode<E>{
		E element;
		SLLNode <E> succ;
		public SLLNode(E element,SLLNode <E> succ){
			this.element=element;
			this.succ=succ;
		}
	}
	class SLL <E>{
		SLLNode<E> first;
		public SLL() {
			first=null;
		}
		public void insertFirst(E element) {
			SLLNode <E> nov=new SLLNode<E>(element,first);
			first=nov;
		}
		public void insertLast(E element) {
			if(this.first==null) {
				insertFirst(element);
			}else {
				SLLNode<E> nov=new SLLNode<E>(element,null);
				SLLNode<E> dvizi=this.first;
				while(dvizi.succ!=null) {
					dvizi=dvizi.succ;
				}
				dvizi.succ=nov;
			}
		}
		@Override
		public String toString() {
			SLLNode<E> dvizi=this.first;
			String string=new String();
			while(dvizi!=null) {
				string=string+dvizi.element +"->";
				dvizi=dvizi.succ;
			}
			return string;
		}
			
	}
public class list {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SLL<Integer> lista=new SLL<Integer>();
		lista.insertFirst(5);
		lista.insertFirst(4);
		lista.insertFirst(3);
		lista.insertLast(1);
		lista.insertLast(9);
		lista.insertFirst(2);
		System.out.println(lista);
	}

}
