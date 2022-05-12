import java.io.*;
 class merenje{
	String grad;
	double vrednost;
	int brojmerenja;
	public merenje(String grad, double vrednost, int brojmerenja) {
		super();
		this.grad = grad;
		this.vrednost = vrednost;
		this.brojmerenja = brojmerenja;
	}
	
	
}
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
		String pom=(String) key;
		int pr=0;
		for (int i = 0; i < pom.length(); i++) {
			pr+= 31*pom.charAt(i) ^ (pom.length()-1-i);
		}
		return  Math.abs(pr) % buckets.length;
				
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
				merenje star=(merenje)curr.element.value;
				merenje praten=(merenje) val;
				star.vrednost+=praten.vrednost;
				star.brojmerenja++;
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

public class opstini {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,merenje> hesh=new CBHT<String,merenje>(N);
		for (int i = 0; i <N; i++) {
			String s=input.readLine();
			String [] pom=s.split(" ");
			merenje m=new merenje(pom[0],Double.parseDouble(pom[1]),1);
			hesh.insert(pom[0],m);
		}
		String grad=input.readLine();
		SLLNode<MapEntry<String,merenje>> element=hesh.search(grad);	
		System.err.println(element.element.value.vrednost/element.element.value.brojmerenja);
	}

}
