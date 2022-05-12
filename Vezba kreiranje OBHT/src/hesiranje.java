class MapEntry<K extends Comparable<K>,E> implements Comparable<K>{
	protected K key;
	protected E value;
	public MapEntry(K key,E val) {
		this.key=key;
		this.value=val;
	}
	@Override
	public int compareTo(K that) {
		// TODO Auto-generated method stub
		MapEntry<K,E> other= (MapEntry<K, E>) that;
		return this.key.compareTo(other.key);
	}
	@Override
	public String toString() {
		return "<" + key + "," + value + ">";
	}
	
	
}
class OBHT<K extends Comparable<K>,E>{
	private MapEntry<K,E> buckets[];
	private static final MapEntry former = new MapEntry(null, null);
	private int occupancy = 0; //Број на зафатени или поранешно зафатени кофички
	
	
	public OBHT(int m) {
		buckets= (MapEntry<K,E>[])new MapEntry[m];
	}
	private int hash(K key) {
		return Math.abs(key.hashCode()) %buckets.length;
	}
	
	static final int NONE = -1;	//Deka ne postoe nekoj element
	
	public int search(K targetKey) {
		 int n_search=0;
		int b=hash(targetKey);
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry==null) {
				return NONE;
			}else if(oldEntry.key.equals(targetKey)) {
				return b;
			}else {
				b=(b+1) %buckets.length;
				n_search++;
				if(n_search==buckets.length) {
					return NONE;
				}
			}
			
		}
	}
	
	public void insert(K key,E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int b=hash(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry==null) {
				if (++occupancy == buckets.length) {
					System.out.println("Hash tabelata e polna!!!");
					return;
					}
					buckets[b] = newEntry;
					return;
			}else if(oldEntry==former || oldEntry.key.equals(key)) {
				buckets[b]=newEntry;
				return;
			}else {
				b=(b+1)% buckets.length;
				n_search++;
				if(n_search==buckets.length) {
					return ;
				}
			}
		}
	}
	
	public void delete(K key) {
		int b=hash(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry==null) {
				return;
			}else if(oldEntry.key.equals(key)) {
				buckets[b]=former;
				return;
			}else {
				b=(b+1) %buckets.length;
				n_search++;
				if(n_search== buckets.length) {
					return;
				}
			}
		}
	}
	public  MapEntry<K,E> getAtIntex(int n){
		return buckets[n];
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

	public OBHT<K,E> clone() {
		OBHT<K,E> newTable=new OBHT<K,E>(buckets.length);
		for(int i=0;i<buckets.length;i++) {
			MapEntry<K,E> e=buckets[i];
			if(e!=null && e !=former) {
				newTable.buckets[i]=new MapEntry<K,E>(e.key,e.value);
			}else {
				newTable.buckets[i]=e;
			}
		}
		return newTable;
		
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
		OBHT<ChemicalElement,Integer> tabela1=new OBHT<ChemicalElement,Integer>(26);
		tabela1.insert(new ChemicalElement("H"), new Integer(1));
		tabela1.insert(new ChemicalElement("He"), new Integer(2));
		tabela1.insert(new ChemicalElement("Li"), new Integer(3));
		tabela1.insert(new ChemicalElement("Be"), new Integer(4));
		tabela1.insert(new ChemicalElement("Na"), new Integer(11));
		tabela1.insert(new ChemicalElement("Mg"), new Integer(12));
		tabela1.insert(new ChemicalElement("K"), new Integer(19));
		tabela1.insert(new ChemicalElement("Ca"), new Integer(20));
		tabela1.insert(new ChemicalElement("Rb"), new Integer(37));
		tabela1.insert(new ChemicalElement("Sr"), new Integer(38));
		tabela1.insert(new ChemicalElement("Cs"), new Integer(55));
		tabela1.insert(new ChemicalElement("Ba"), new Integer(56));
		System.out.println(tabela1);
		System.err.println(tabela1.getAtIntex(2));
	}

}
