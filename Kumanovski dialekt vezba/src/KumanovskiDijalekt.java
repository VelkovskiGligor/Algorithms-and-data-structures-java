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
		int pom=0;
		String kluc=(String)key;
		for (int i = 0; i < kluc.length(); i++) {
			pom=pom +(kluc.charAt(i)*31 ^(kluc.length()-1-i));
			
		}
		return  Math.abs(pom) % buckets.length;
				
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

public class KumanovskiDijalekt {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,String> recnik=new CBHT<String,String>(2*N);
		for (int i = 0; i < N; i++) {
			String recenica=input.readLine();
			String pom[] =recenica.split(" ");
			recnik.insert(pom[0], pom[1]);
		}
		String kumanovska=input.readLine();
		if(N==0) {
			System.err.println(kumanovska);
			return;
		}
		String makedonska="";
		String pomrecenica[]=kumanovska.split(" ");
		for (int i = 0; i < pomrecenica.length; i++) {
			char c=pomrecenica[i].charAt(pomrecenica[i].length()-1);
			//System.err.println(c);
			String znak="";
			boolean isZnak=false;
			if(c == '.' || c== ',' || c=='!' || c=='?') {
				znak=c+"";
				isZnak=true;
			}else {
				znak="";
				isZnak=false;
			}
			String zatrazenje="";
			if(isZnak) {
				 zatrazenje=pomrecenica[i].substring(0,pomrecenica[i].length()-1);
				//System.err.println(zatrazenje);
			}else {
				zatrazenje=pomrecenica[i];
			}
			String ignorcase=zatrazenje.toLowerCase();
			SLLNode<MapEntry<String,String>> zapis= recnik.search(ignorcase);
			if(zapis!=null) {		
				if(zatrazenje.charAt(0)- 'A'<26) {
					//System.err.println(zatrazenje);
					String get=zapis.element.value;
					get+=znak;
					get=get.substring(0,1).toUpperCase()+ get.substring(1,get.length());
					//System.err.println(get);
					makedonska+=get+" ";
				}else{
					makedonska+=zapis.element.value+znak+ " ";
				}
					
			}else {
				makedonska+=zatrazenje+znak+ " ";
			}
			
		
		}
		System.err.println(makedonska);
	}

}
