import java.io.*;
class MapEntry<K extends Comparable<K>,E> implements Comparable<K>{
	K key;
	E val;
	
	public MapEntry(K key, E val) {
		super();
		this.key = key;
		this.val = val;
	}

	@Override
	public String toString() {
		return "" + key + " " + val + "";
	}

	@Override
	public int compareTo(K arg) {
		MapEntry<K,E> other=(MapEntry<K, E>) arg;
		return key.compareTo(other.key);
	}
	
}
class OBHT <K extends Comparable<K>,E>{
	private MapEntry<K,E> [] buckets;
	private static final MapEntry former =new MapEntry(null,null);
	private int occupancy=0;
	public OBHT(int m) {
		buckets=(MapEntry<K,E>[])new MapEntry[m];
	}
	int hash(K key) {
		return Math.abs(key.hashCode()) %buckets.length;
	}
	static final int NONE = -1;
	public int search(K targetKey) {
		int n_search=0;
		int b=hash(targetKey);
		for(;;) {
			MapEntry <K,E> oldEntry=buckets[b];
			if(oldEntry == null) {
				return NONE;
			}else if(oldEntry.key.equals(targetKey)) {
				return b;
			}else {
				b=(b+1) %buckets.length;
				n_search++;
				if(n_search==buckets.length)return NONE;
			}
		}
	}
	public MapEntry<K,E> atIndex(int n){
		return buckets[n];
	}
	
	public void insert(K key,E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int b=hash(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry == null) {
				buckets[b]=newEntry;
				return;
			}else if(oldEntry == former || oldEntry.key.equals(key)) {
				buckets[b]=newEntry;
				return;
			}else {
				b=(b+1) %buckets.length;
				n_search++;
				if(n_search== buckets.length) {
					System.out.println("Hash tabelata e polna!!!");
					break;
				}
			}
		}
		
	}
	public void delete(K key) {
		int b=hash(key);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry== null) {
				return;
			}else if(oldEntry.key.equals(key)) {
				buckets[b]=former;
				return;
			}else {
				b=(b+1)%buckets.length;
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
	
}
class Animals implements Comparable<Animals>{
	String name;
	String ime;
	

	public Animals(String name, String ime) {
		super();
		this.name = name;
		this.ime = ime;
	}

	
	

	@Override
	public int compareTo(Animals arg) {
		// TODO Auto-generated method stub
		return this.name.compareTo(arg.name);
	}

	@Override
	public String toString() {
		return "" + name + "," + ime + "";
	}
	
}
public class Preveduvac {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
			int n=Integer.parseInt(input.readLine());
			OBHT <String,Animals> tabela=new OBHT<String,Animals>(n+n/2+n/8);
			//System.err.println(n+n/2+n/8);
			for(int i=0;i<n;i++) {
				String s=input.readLine();
				String [] pom=s.split(" ");
				tabela.insert(pom[1], new Animals(pom[1], pom[0]));
			}
			//System.err.println(tabela);
			while(true) {
				String s=input.readLine();
				if(s.equals("KRAJ"))break;
				int pozicija=tabela.search(s);
				if(pozicija== -1) {
					System.out.println("/");
				}else {
					System.out.println(tabela.atIndex(pozicija).val.ime);
				}
				
				
			}
	}

}
