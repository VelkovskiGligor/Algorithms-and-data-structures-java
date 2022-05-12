
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
		return this.key.compareTo(other.key) ;
	}

	@Override
	public String toString() {
		return "" + key + "," + val + "";
	}
	
	
}
 interface DoublyHashable<K> extends
Comparable<K> {
public int hashCode ();
public int stepCode ();
}
 
 class DoublyHashedOBHT <K extends DoublyHashable<K>,E>{
	 private MapEntry<K,E> [] buckets;
	 private static final MapEntry former = new MapEntry(null, null);
	// private int occupancy = 0;
	   static final int NONE = -1;
	public DoublyHashedOBHT(int m) {
		buckets= (MapEntry<K,E>[])   new MapEntry[m];
	}
	private int hesh(K key) {
		return Math.abs((key.hashCode())) % buckets.length;
	}
	 
	private int step (K key) {
		return Math.abs(key.stepCode()) % buckets.length;
	}
	public int search(K targetKey) {
		int b= hesh(targetKey);
		int s= step(targetKey);	
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry == null) {
				return NONE;
			}else if(targetKey.equals(oldEntry.key)) {
				return b;
			}else {
				b=(b+s) % buckets.length;
				n_search++;
				if(n_search==buckets.length) return NONE;
			}
		}
	}
	public void insert(K key,E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int b=hesh(key);
		int s=step(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry==null) {
				buckets[b]=newEntry;
				return;
			}else if(oldEntry == former || oldEntry.key.equals(newEntry.key)) {
				buckets[b]=newEntry;
				return;
			}else {
				b=(b+s)%buckets.length;
				n_search++;
				if(n_search==buckets.length)return;
			}
			
		}
	}
	public void delete(K key) {
		int b=hesh(key);
		int s=step(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry== null) {
				return;
			}else if(oldEntry.key.equals(key)) {
				buckets[b]=former;
				return;
			}else {
				b=(b+s)% buckets.length;
				n_search++;
				if(n_search== buckets.length)return;
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
	  public DoublyHashedOBHT <K,E> clone(K key,E val){
		  DoublyHashedOBHT <K,E> copy=new DoublyHashedOBHT<K,E>(buckets.length);
		  for(int i=0;i<buckets.length;i++) {
			  MapEntry <K,E> e=buckets[i];
			  if(e != null && e!=former ) {
				  copy.buckets[i]=new MapEntry<K,E> (e.key,e.val);
			  }else {
				  copy.buckets[i]=e;
			  }
		  }
		  return copy;
	  }
	 
 }

 class ChemicalElement implements DoublyHashable<ChemicalElement>{
	 String s;
	 
	public ChemicalElement(String s) {
		super();
		this.s = s;
	}

	@Override
	public int compareTo(ChemicalElement arg0) {
		// TODO Auto-generated method stub
		return this.s.compareTo(arg0.s);
	}

	@Override
	public String toString() {
		return "" + s + "";
	}
	public int hashCode() {
		return s.charAt(0) -'A';
	}
	public int stepCode() {
		
		if(s.length()!=1) {
			return s.charAt(1)-'a' +2;
		}else {
			return 1;
		}
	}
	
	 
 }
public class dvojnoHesiranje {

	public static void main (String[] args) {
        DoublyHashedOBHT<ChemicalElement,Integer> table1 = new DoublyHashedOBHT<ChemicalElement,Integer>(23);
        table1.insert(new ChemicalElement("H"),  1);
        table1.insert(new ChemicalElement("He"),2);
        table1.insert(new ChemicalElement("Li"), 3);
        table1.insert(new ChemicalElement("Be"), 4);
        table1.insert(new ChemicalElement("Na"), 11);
        table1.insert(new ChemicalElement("Mg"), 12);
        table1.insert(new ChemicalElement("K"),  19);
        table1.insert(new ChemicalElement("Ca"),20);
        table1.insert(new ChemicalElement("Rb"), 37);
        table1.insert(new ChemicalElement("Sr"), 38);
        table1.insert(new ChemicalElement("Cs"), 55);
        table1.insert(new ChemicalElement("Ba"), 56);

        System.out.println ("Initial table.");
        System.out.println(table1);
        
        table1.insert(new ChemicalElement("Fr"), new Integer(87));
        table1.insert(new ChemicalElement("Ra"), new Integer(88));
        table1.insert(new ChemicalElement("B"),  new Integer(5));

        System.out.println ("Table after inserting Fr, Ra and B.");
        System.out.println(table1);

    }

}
