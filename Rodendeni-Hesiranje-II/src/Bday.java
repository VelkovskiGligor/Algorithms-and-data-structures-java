import java.io.*;

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
		return  Math.abs(Integer.parseInt((String) key)) % buckets.length;
				
	}
	public SLLNode<MapEntry<K,E>> search(K targetKey){
		int indeks=hash(targetKey); // vrakja na koja pozicija se naogja kofickata za dadeniot kluc
		for(SLLNode<MapEntry<K,E>> curr=buckets[indeks];curr!=null;curr=curr.succ  ) {
			if (targetKey.equals(((MapEntry<K, E>) curr.element).key)){
				return buckets[indeks];
			}
		} 
		return null;
		
	}
	public void insert(K key,E val) {
		MapEntry<K,E> nov=new MapEntry<K,E>(key,val);
		int indeks=hash(key);
		for(SLLNode<MapEntry<K,E>>curr=buckets[indeks];curr!=null;curr=curr.succ) {
			if(val.equals(curr.element.value)) {
				curr.element=nov;
				return;
			}
			
		}
		SLLNode<MapEntry<K,E>>curr=buckets[indeks];
		if(curr==null) {
			buckets[indeks]=new SLLNode<MapEntry<K,E>>(nov,null);
		}else {
			while(curr.succ!=null) {
				curr=curr.succ;
			}
			curr.succ= new SLLNode<MapEntry<K,E>> (nov,null);
		}
		
		
		
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
public class Bday {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,String> spisok=new CBHT<String,String>(31);
		for (int i = 0; i < N; i++) {
			String s=input.readLine();
			String [] pom=s.split(" ");
			String ime=pom[0];
			String []celodato=pom[1].split("\\.");
			String dato= celodato[1];
			//System.err.println(ime +" "+dato);
			spisok.insert(dato, ime);
		}
		//System.err.println(spisok);
		String baraj=input.readLine();
		SLLNode<MapEntry<String,String>> curr=spisok.search(baraj);
		if (curr!=null) {
			while(curr!=null) {
				System.err.println(curr.element.value);
				curr=curr.succ;
			}
		}else {
			System.err.println("Nema!");
		}
	}

}
