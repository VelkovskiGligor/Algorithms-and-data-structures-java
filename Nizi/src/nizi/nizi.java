package nizi;


class Niza <E>{
	private E  niza[];
	private int size;
	public Niza(){
		this.size=0;
		this.niza=(E[]) new Object[100];
	}
	public Niza(int size,E []niza) {
		this.size=size;
		niza=(E[])new Object[size];
		for (int i=0;i<this.size;i++) {
			this.niza[i]=niza[i];
		}
	}
	public int getSize() {
		return this.size;
	}
	public E getElement(int position) {
		if(position<0 || position>=this.size) {
			ThrowErrorManager("Vnesovte nevalidna pozicija");
		}
		return this.niza[position];
	}
	private void ThrowErrorManager(String string) {
		System.out.println(string);
		
	}
	public int getIndex(E element) {
		for (int i=0;i<this.size;i++){
			if(element.equals(niza[i])) {
				return i;
			}
		}
			return -1;
		}
	public void push(E element) { 		//dodava element na krajot od nizata!
			if(size==niza.length) {
				ThrowErrorManager("Nizata e vekje polna!");
			}else {
					this.niza[this.size]=element;
					size++;
			}
	}
	public void	pop(E element) {		//dodava na pocetakot na nizata
				if(this.niza.length==this.size) {
					ThrowErrorManager("Nizata e vekje polna!");
				}else {
						this.size++;
					for (int i=this.size;i>0;i--) {
							this.niza[i]=this.niza[i-1];
					}
					niza[0]=element;
				
				}
	}
	public void insert(int position,E element) {
		//dodava element na lokacija [position], dodeka elementot na taa pozicija go brise
		if(position<0 || position>this.size) {
			ThrowErrorManager("Nevalidna pozicija!");
		}else {
			this.niza[position]=element;
		}
	}
	public void delete(E element) {
		boolean flag=false;
		for (int i = 0; i < this.size; i++) {
				if(this.niza[i].equals(element)) {
					flag=true;
				}
				if(flag==true) {
					niza[i]=niza[i+1];
				}
			
		}
			
			if(flag==false) {
				ThrowErrorManager("Nepostoi takov element vo nizata!");
			}else {
				this.size--;
			}
	}
}

public class nizi {

	public static void main(String[] args) {
		int [] n= {1,2,3,4,5};
		Niza <Integer> niza=new Niza <Integer>();
				niza.push(5);
				niza.push(4);
				niza.push(3);
				niza.push(2);
				niza.push(1);
				niza.push(0);
				niza.pop(10);
				niza.insert(10, 100);
				niza.delete(0);
				for (int i = 0; i < niza.getSize(); i++) {
						System.out.println(niza.getElement(i));
					
				}
				System.out.println("Size="+niza.getSize());
	}

}
