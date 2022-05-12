

class MapEntry<K extends Comparable<K>,E> implements Comparable<K>{
	K key;
	E val;
	
	public MapEntry(K key, E val) {
		this.key = key;
		this.val = val;
	}

	@Override
	public int compareTo(K that) {
		MapEntry<K,E> other=(MapEntry<K, E>) that;
		return this.key.compareTo(other.key);
	}

	@Override
	public String toString() {
		return "<" + key + "," + val + ">";
	}
}
class OBHT <K extends Comparable<K>,E>{
	private MapEntry<K,E> [] buckets;
	private static final MapEntry former =new MapEntry(null,null);
	private int occupancy=0;
	public OBHT(int m) {
		buckets=(MapEntry<K, E>[]) new MapEntry[m];
	}
	public int hash(K key) {
		return Math.abs(key.hashCode()) % buckets.length;
	}
	static final int NONE = -1;
	
	public int search(K target) {
		int n_search=0;
		int indeks=hash(target);
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[indeks];
			if(oldEntry==null) {
				return NONE;
			}else if(target.equals(oldEntry.key)) {
				return indeks;
			}else {
				indeks=(indeks+1) %buckets.length;
				n_search++;
				if(n_search==buckets.length) {
					return NONE;
				}
				
			}
			
		}
	}
	public void insert(K key,E val) {
		MapEntry<K,E> nov=new MapEntry(key,val);
		int indeks=hash(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[indeks];
			if(oldEntry==null) {
				buckets[indeks]=nov;
				return;
			}else if(oldEntry==former || key.equals(oldEntry.key)) {
				buckets[indeks]=nov;
				return;
			}else {
				indeks=(indeks+1)%buckets.length;
				n_search++;
				if(n_search==buckets.length) {
					System.out.println("Hash tabelata e polna!!!");
					return;
				}
			}
		}
	}
	public void delete(K key) {
		int indeks=hash(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[indeks];
			if(oldEntry == null) {
				return;
			}else if(key.equals(oldEntry.key)) {
				buckets[indeks]=former;
				return;
			}else {
				indeks=indeks+1 %buckets.length;
				n_search++;
				if(n_search== buckets.length) {
					return;
				}
			}
		}
		
	}
	public String toString () {
    	String temp = "";
    	for (int i = 0; i < buckets.length; i++) {
    	    temp += i + ":";
    	    if (buckets[i] == null)
    	        temp += "\n";
    	    else if (buckets[i] == former)
    	        temp += "former\n";
    	    else
    	        temp += buckets[i] + "\n";
    	}
    	return temp;
    }
	public OBHT<K,E> clone(){
		OBHT<K,E> copy=new OBHT(buckets.length); 
		for(int i=0;i<buckets.length;i++) {
			MapEntry<K,E> pom=buckets[i];
			if(pom!=null &&  pom!=former) {
				copy.buckets[i]=new MapEntry<K,E>(pom.key,pom.val);
			}else {
				copy.buckets[i]=pom;
			}
		}
		
		return copy;
	}
	
}
class ChemicalElement implements Comparable<ChemicalElement>{
	private String element;
	
	public ChemicalElement(String element) {
		this.element = element;
	}

	@Override
	public int compareTo(ChemicalElement el) {
		
		return  element.compareTo(el.element);
	}
	  public int hashCode () {
	        return element.charAt(0) - 'A';
	    }


	@Override
	public String toString() {
		return "" + element + "";
	}
	
	
}
public class klasa {

	public static void main(String[] args) {
		OBHT<ChemicalElement,Integer> table1 = new
				OBHT<ChemicalElement,Integer>(26);
				table1.insert(new ChemicalElement("H"), new Integer(1));
				table1.insert(new ChemicalElement("He"), new Integer(2));
				table1.insert(new ChemicalElement("Li"), new Integer(3));
				table1.insert(new ChemicalElement("Be"), new Integer(4));
				table1.insert(new ChemicalElement("Na"), new Integer(11));
				table1.insert(new ChemicalElement("Mg"), new Integer(12));
				table1.insert(new ChemicalElement("K"), new Integer(19));
				table1.insert(new ChemicalElement("Ca"), new Integer(20));
				table1.insert(new ChemicalElement("Rb"), new Integer(37));
				table1.insert(new ChemicalElement("Sr"), new Integer(38));
				table1.insert(new ChemicalElement("Cs"), new Integer(55));
				table1.insert(new ChemicalElement("Ba"), new Integer(56));
			
				System.out.println(table1);
				System.err.println(table1.search(new ChemicalElement("Sr")));

	}

}
