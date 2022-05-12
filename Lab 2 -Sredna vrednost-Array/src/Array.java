import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Array<E> {
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
	public static double getProsek(Array <Integer> niza) {
		int sum=0;
		for (int i=0;i<niza.size;i++) {
			sum=sum+niza.get(i);
		}
	
		return sum/niza.size;
	}
	public static int brojDoProsek( Array<Integer> niza){
		double prosek=getProsek(niza);
		//System.out.println(prosek);
		int min=90000;
		int broj=0;
		for (int i=0;i<niza.size;i++) {
			int razlika=(int) Math.abs(prosek-niza.get(i));
			if(razlika<min) {
				min=razlika;
				broj=niza.get(i);
			}
			
		}
		for (int i=0;i<niza.size;i++) {
			int razlika=(int) Math.abs(prosek-niza.get(i));
			if(razlika<=min && niza.get(i)<broj) {
				min=razlika;
				broj=niza.get(i);
			}
		}
		
		//Vashiot kod tuka...
		return broj;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in)); 
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		Array<Integer> niza=new Array<Integer>(N);
        //Vashiot kod tuka...
			for (int i=0;i<N;i++) {
				int o= Integer.parseInt(stdin.readLine());
				niza.set(i, o);
			}
		
      //  System.out.println(niza);
        
		System.out.println(brojDoProsek(niza));		
	}
	
	

}
