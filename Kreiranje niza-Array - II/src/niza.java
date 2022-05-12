import java.util.Arrays;



class Array <E>{
	private	E data [];
	private int size;
	public Array(int size){
		this.data=(E[]) new Object[size];
		this.size=size;
	}
	public void set(int position,E	o) {	//Promenuva vrednost na dadena lokacija
		if(position>=0 && position <size) {
			data[position]=o;
		}else {
			ThrowErrorManager("Nevalidna lokacija");
		}	
	}
	public  E  get(int position){	//Vrakja vrednost od dadena lokacija
		if(position>=0 && position <size) {
			return data[position];
		}else {
			ThrowErrorManager("Nevalidna lokacija");
		}
		return null;
	
	}
	public int find(E element) {	//Proveruva dali dadena niza sodrzi element i go vraka indeksot na toj element
			for(int i=0;i<this.size;i++) {
				if(data[i].equals(element)) {
					return i;
				}
			}
			System.err.println("Elementot ne e pronajden");
			return -1;
	}
	public void delete(int position) {//Brise element od dadena pozicija
		if(position>=0 && position<this.size) {
			int j=0;
			E [] pom =(E[]) new Object[this.size-1];
			for( int i=0; i<this.size;i++) {
				if(i!=position) {
					pom[j]=data[i];
					j++;
				}
				
			}
			data=pom;
			this.size--;
		}else {
			ThrowErrorManager("Nevalidna lokacija");
		}
	}
	public void insert(int position,E o) {//Dodava element na dadena lokacija pritoa gi shifta elementite
		if(position >=0 && position <=size) {
			E [] pom=(E[]) new Object[size+1];
			int j=0;
			for(int i=0;i<size+1;i++) {
				if(i!=position){
					pom[i]=data[j];
					j++;
				}else {
					pom[i]=o;
				}
			}
			data=pom;
			size++;
		}else{
			ThrowErrorManager("Nevalidna lokacija");
		}
	}
	public void resize(int newSize) {//Promenue ja goleminata na nizata
		E [] pom=(E[]) new Object[newSize];
		int goTo=this.size;
		if(newSize<this.size) {
			goTo=newSize;
		}
		for(int i=0;i<goTo;i++) {
			pom[i]=data[i];
		}
		this.data=pom;
		this.size=newSize;
		
		
		
	}
	
	private void ThrowErrorManager(String string) {
		System.err.println(string);
		
	}
	@Override
	public String toString() {
		return "Array [data=" + Arrays.toString(data) + "]";
	}
	
	
}
public class niza {

	public static void main(String[] args) {
		int[]  a= {1,2,3,4,5,6};
		for(int i=0;i<6;i++) {
			System.out.println(a[i]);
		}
		Array <Integer> niza=new Array<Integer>(6);
			niza.set(0, 1);
			niza.set(1, 2);
			niza.set(2, 3);
			niza.set(3, 4);
			niza.set(4, 5);
			niza.set(5, 6);
			System.out.println(niza);
			niza.insert(2, 10);
			niza.insert(4, 8);
			System.err.println(niza);
	}

}
