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
	public int hash(K key) {
		String keyStr= (String)key;
		int proizvod=1;
		for (int i=0;i< keyStr.length();i++) {
			proizvod+= 31* keyStr.charAt(i)^keyStr.length()-i;
		}
			return proizvod % buckets.length;
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

public class recnik {

	public static void main(String[] args) throws NumberFormatException, IOException  {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(input.readLine());
		CBHT<String,String> tabela=new CBHT <String,String>(N+N/2+N/4);
		for (int i=0;i<N;i++) {
			String red=input.readLine();
			String []pom= red.split(" ");
			pom[0].toLowerCase();
			//System.err.println(pom[0]);
			tabela.insert(pom[0], pom[1]);
		}
		String kumanovskitekst = input.readLine();
		String [] nizakumanovo=kumanovskitekst.split(" ");
		String copy="";
		for (int i=0;i<nizakumanovo.length;i++) {
			boolean golema=false;
			String prati;
			String interpukcija="";
			if(nizakumanovo[i].charAt(nizakumanovo[i].length()-1) == '.') {
				interpukcija=".";
				prati=nizakumanovo[i].substring(0,nizakumanovo[i].length()-1);
			}else if(nizakumanovo[i].charAt(nizakumanovo[i].length()-1) == '!') {
				interpukcija="!";
				prati=nizakumanovo[i].substring(0,nizakumanovo[i].length()-1);
			}else if(nizakumanovo[i].charAt(nizakumanovo[i].length()-1) == '?') {
				interpukcija="?";
				prati=nizakumanovo[i].substring(0,nizakumanovo[i].length()-1);
			}else if(nizakumanovo[i].charAt(nizakumanovo[i].length()-1) == ',') {
				interpukcija=",";
				prati=nizakumanovo[i].substring(0,nizakumanovo[i].length()-1);
			}else {
				interpukcija="";
				prati=nizakumanovo[i];
			}
			if(prati.charAt(0)-'a'<0) {
				golema=true;
				prati.toLowerCase();
			}
		
			SLLNode<MapEntry<String,String>> element=tabela.search(prati);
			if(element!=null) {
				if(golema) {
					StringBuilder bilder=new StringBuilder(element.element.value);
					bilder.setCharAt(0,(char) (bilder.charAt(0)-26));
					copy+=bilder+interpukcija+" ";
				}else {
					copy+=element.element.value+interpukcija+" ";
				}
			}else {
				copy+= nizakumanovo[i]+" ";
			}
			
		}
		System.err.println(copy);
	}

}
