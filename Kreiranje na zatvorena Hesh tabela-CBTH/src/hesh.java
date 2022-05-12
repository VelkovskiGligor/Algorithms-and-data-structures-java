import java.util.Arrays;

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

class ChemicalElement implements Comparable<ChemicalElement>{
	private String element;
	
	public ChemicalElement(String element) {
		super();
		this.element = element;
	}

	@Override
	public int compareTo(ChemicalElement arg0) {
		
		return this.element.compareTo(arg0.element);
	}
	public int hashCode() {
		return element.charAt(0) -'A';
	}

	@Override
	public String toString() {
		return element;
	}
	
}
public class hesh {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CBHT<ChemicalElement,Integer> tabela = new CBHT<ChemicalElement,Integer>(26);
		tabela.insert(new ChemicalElement("F"), 9);
		tabela.insert(new ChemicalElement("Ne"),10);
		tabela.insert(new ChemicalElement("Cl"),17);
		tabela.insert(new ChemicalElement("Ar"),18);
		tabela.insert(new ChemicalElement("Br"), 35);
		tabela.insert(new ChemicalElement("Kr"), 36);
		tabela.insert(new ChemicalElement("I"), 53);
		tabela.insert(new ChemicalElement("Xe"), 54);
		//System.out.println(tabela);
		
		CBHT<ChemicalElement,Integer> table2 = new
				CBHT<ChemicalElement,Integer>(26);
				table2.insert(new ChemicalElement("H"), new Integer(1));
				table2.insert(new ChemicalElement("He"), new Integer(2));
				table2.insert(new ChemicalElement("Li"), new Integer(3));
				table2.insert(new ChemicalElement("Be"), new Integer(4));
				table2.insert(new ChemicalElement("Na"), new Integer(11));
				table2.insert(new ChemicalElement("Mg"), new Integer(12));
				table2.insert(new ChemicalElement("K"), new Integer(19));
				table2.insert(new ChemicalElement("Ca"), new Integer(20));
				table2.insert(new ChemicalElement("Rb"), new Integer(37));
				table2.insert(new ChemicalElement("Sr"), new Integer(38));
				table2.insert(new ChemicalElement("Cs"), new Integer(55));
				table2.insert(new ChemicalElement("Ba"), new Integer(56));
				System.out.println ("Tabelata od slajd 6");
				System.out.println(table2);
	}

}
