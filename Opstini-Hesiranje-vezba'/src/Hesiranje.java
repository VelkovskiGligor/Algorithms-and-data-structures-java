import java.io.*;
import java.util.Iterator;
class SLLNode<E>{
	E element;
	SLLNode<E> succ;
	public SLLNode(E element, SLLNode<E> succ) {
		this.element = element;
		this.succ = succ;
	}
	
}
class MapEntry<K extends Comparable<K>, E> implements Comparable<K>{
	K key;
	E value;
	
	public MapEntry(K key, E value) {   // Kreirame mapa (par od kluc i vrednost)
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(K that) { //Sporeduva dva kluca;
		MapEntry <K,E> other= (MapEntry<K, E>) that;
		return this.key.compareTo(other.key);
	}

	@Override
	public String toString() {
		return "<" + key + ", " + value + ">";
	}
	
	
}
class CBHT<K extends Comparable<K>,E>{
	//Kreirame niza od listi koi ke idat od tipot MapEntry
	private SLLNode<MapEntry<K,E>> [] buckets;
	
	public CBHT(int m) {	
		//Pravime instaca od klasata
		buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
	}
	//Ostanati funkcii
	private int hash(K key) { // Za dadaen kluc ja vrakja domasnata koficka
		return  Math.abs(key.hashCode()) % buckets.length;
				
	}
	public SLLNode<MapEntry<K,E>> search(K targetKey){
		int indeks=hash(targetKey); // vrakja na koja pozicija se naogja kofickata za dadeniot kluc
		for(SLLNode<MapEntry<K,E>> curr=buckets[indeks];curr!=null;curr=curr.succ  ) {
			if (targetKey.equals(((MapEntry<K, E>) curr.element).key)){
				return curr;
			}
		} 
		return null;
		
	}
	public void insert(K key,E val) {
		MapEntry<K,E> nov=new MapEntry<K,E>(key,val);
		int indeks=hash(key);
		for(SLLNode<MapEntry<K,E>>curr=buckets[indeks];curr!=null;curr=curr.succ) {
			if(key.equals(curr.element.key)) {
				curr.element=nov;
				return;
			}
			
		}
		buckets[indeks] = new SLLNode<MapEntry<K,E>> (nov,buckets[indeks]);
		
	}
	public void delete(K key){ //Brise element spored daden kluc;
		int indeks= hash(key);
		for(SLLNode<MapEntry<K,E>> pred=null, curr=buckets[indeks];curr!=null;pred=curr,curr=curr.succ) {
			if(key.equals(((MapEntry<K,E>) curr.element).key)) {
				if(pred!=null) {
					pred.succ=curr.succ;
				}else {
					buckets[indeks]=curr.succ;
				}
				return;
			}
		}
	}
	public String toString() {
		String temp = "";
		for (int i = 0; i < buckets.length; i++) {
			temp += i + ":";
			for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
				temp += curr.element.toString() + " ";
			}
			temp += "\n";
		}
		return temp;
	}
	
}
class Opstina{
	private String ime;
	private float proekmerenja;
	private int brojmernja;
	public Opstina(String ime, float proekmerenja, int brojmernja) {
		super();
		this.ime = ime;
		this.proekmerenja = proekmerenja;
		this.brojmernja = brojmernja;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public float getProekmerenja() {
		return proekmerenja;
	}
	public void setProekmerenja(float proekmerenja) {
		this.proekmerenja = proekmerenja;
	}
	public int getBrojmernja() {
		return brojmernja;
	}
	public void setBrojmernja(int brojmernja) {
		this.brojmernja = brojmernja;
	}
	
	
}
public class Hesiranje {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int M=Integer.parseInt(input.readLine());
		CBHT <String,Opstina> tabela=new CBHT<String,Opstina>(2*M);
		for (int i = 0; i < M; i++) {
			String red=input.readLine();
			String ime=red.split(" ")[0];
			float merenje=Float.parseFloat(red.split(" ")[1]);	
		
			SLLNode<MapEntry<String,Opstina>> get=tabela.search(ime);
			if(get==null) {
				Opstina opstina=new Opstina(ime, merenje, 1);
				tabela.insert(ime, opstina);
			}else {
				get.element.value.setBrojmernja(get.element.value.getBrojmernja()+1);
				get.element.value.setProekmerenja(get.element.value.getProekmerenja()+merenje);
				tabela.insert(ime, get.element.value);
			}
		}
		String grad=input.readLine();
		SLLNode<MapEntry<String,Opstina>> get=tabela.search(grad);
		if(get!=null) {
			float rezz=get.element.value.getProekmerenja() / get.element.value.getBrojmernja();
			System.out.printf("%.2f",rezz);
		}
		
	
	}

}
