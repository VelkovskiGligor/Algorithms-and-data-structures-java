class SLLNode<E>{
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E element, SLLNode<E> succ){
		this.element=element;
		this.succ=succ;
	}
	@Override
	public String toString() {
		return this.element.toString();
	}
	
}

class MapEntry<K extends Comparable<K>,E> implements Comparable<K>{
	K key;
	E val;

	public MapEntry(K key, E val) {
		this.key=key;
		this.val=val;
	}
	@Override
	public int compareTo(K that) {
		MapEntry<K,E> other= (MapEntry<K, E>) that;
		return this.key.compareTo(other.key);
	}
	@Override
	public String toString() {
	    return "<" + key + "," + val + ">";
	}
}

class CBHT<K extends Comparable<K>,E>{
	private SLLNode<MapEntry<K,E>> buckets[];
	public CBHT(int m) {
		this.buckets=(SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
	}
	private int hash(K key) {
		return Math.abs(key.hashCode()) % buckets.length;
	}
	public SLLNode<MapEntry<K,E>> search(K targetKey){
		int b=hash(targetKey);
		for(SLLNode<MapEntry<K,E>> curr=buckets[b];curr!=null;curr=curr.succ) {
			if (targetKey.equals(((MapEntry<K, E>) curr.element).key)) {
				return curr;
			}
		}
		return null;
	}
	public void insert(K key, E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int b=hash(key);
		for(SLLNode<MapEntry<K,E>>curr=buckets[b];curr!=null;curr=curr.succ) {
			if (key.equals(((MapEntry<K, E>) curr.element).key)) {
				curr.element=newEntry;
				return;
			}
		}
		buckets[b]=new SLLNode<MapEntry<K,E>>(newEntry,buckets[b]);
	}
	public void delete(K key) {
		int b=hash(key);
		for(SLLNode<MapEntry<K,E>>pred=null,curr=buckets[b];curr!=null;pred=curr,curr=curr.succ) {
			if(curr.element.key.equals(key)) {
				if(pred!=null) {
					pred.succ=curr.succ;
				}else {
					buckets[b]=curr.succ;
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
	String name;

	public ChemicalElement(String name) {
		this.name=name;
	}
	@Override
	public int compareTo(ChemicalElement comp) {
		// TODO Auto-generated method stub
		return this.name.compareTo(comp.name);
	}
	public int hashCode() {
		return name.charAt(0)-'A';
	}
	@Override
	public String toString() {
		return "" + name + "";
	}
	
	
}
public class hesiranje {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CBHT<ChemicalElement,Integer> tabela=new CBHT<ChemicalElement,Integer>(26);
		tabela.insert(new ChemicalElement("F"), 9);
		tabela.insert(new ChemicalElement("Ne"), new Integer(10));
		tabela.insert(new ChemicalElement("Cl"), new Integer(17));
		tabela.insert(new ChemicalElement("Ar"), new Integer(18));
		tabela.insert(new ChemicalElement("Br"), new Integer(35));
		tabela.insert(new ChemicalElement("Kr"), new Integer(36));
		tabela.insert(new ChemicalElement("I"), new Integer(53));
		tabela.insert(new ChemicalElement("Xe"), new Integer(54));
		//System.err.println(tabela);
		CBHT<ChemicalElement,Integer> tabela2=new CBHT<ChemicalElement,Integer>(26);
		tabela2.insert(new ChemicalElement("H"), new Integer(1));
		tabela2.insert(new ChemicalElement("He"), new Integer(2));
		tabela2.insert(new ChemicalElement("Li"), new Integer(3));
		tabela2.insert(new ChemicalElement("Be"), new Integer(4));
		tabela2.insert(new ChemicalElement("Na"), new Integer(11));
		tabela2.insert(new ChemicalElement("Mg"), new Integer(12));
		tabela2.insert(new ChemicalElement("K"), new Integer(19));
		tabela2.insert(new ChemicalElement("Ca"), new Integer(20));
		tabela2.insert(new ChemicalElement("Rb"), new Integer(37));
		tabela2.insert(new ChemicalElement("Sr"), new Integer(38));
		tabela2.insert(new ChemicalElement("Cs"), new Integer(55));
		tabela2.insert(new ChemicalElement("Ba"), new Integer(56));
		System.err.println(tabela2);
		System.err.println(tabela2.search(new ChemicalElement("Ca")));

	}

}
