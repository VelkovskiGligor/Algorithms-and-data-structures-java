import java.io.*;
class MapEntry<K extends Comparable<K>,E> implements Comparable<K>{
	K key;
	E value;
	
	public MapEntry(K key, E value) {
		super();
		this.key = key;
		this.value = value;
	}
	

	@Override
	public String toString() {
		return "<" + key + "," + value + ">";
	}


	@Override
	public int compareTo(K arg0) {
		// TODO Auto-generated method stub
		MapEntry<K,E> other=(MapEntry<K, E>) arg0;
		return other.key.compareTo(this.key);
	}
	
}
class OBHT<K extends Comparable<K>,E>{
	MapEntry<K,E> buckets[];
	private static final MapEntry former =new MapEntry(null,null);
	private int occupancy=0;
	public OBHT(int m) {
		buckets=(MapEntry<K,E>[])new MapEntry[m];
	}
	int hash(K key) {
		return Math.abs(key.hashCode()) % buckets.length;
	}
	static final int NONE = -1;
	public MapEntry<K,E> atIndex(int z) {
		if(z!=-1) {
			return buckets[z];
		}
		return null;
	}
	public int search(K targetKey) {
		int b=hash(targetKey);
		int n_search=0;
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry==null) {
				return NONE;
			}
			else if(oldEntry.key.equals(targetKey)) {
				return b;
			}else {
				b=(b+1) %buckets.length;
				n_search++;
				if(n_search== buckets.length) {
					return NONE;
				}
			}	
		}
	}
	public void insert(K key, E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int n_search=0;
		int b=hash(key);
		for(;;) {
			MapEntry<K,E> oldEntry=buckets[b];
			if(oldEntry==null) {
				buckets[b]=newEntry;
				return;
			}else if(oldEntry== former ||key.equals(oldEntry.key)) {
				buckets[b]=newEntry;
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
}
public class rutiranje {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		OBHT<String,String> tabela=new OBHT<String,String>(2*N);
		for (int i=0;i<N;i++) {
			String domasen=input.readLine();
			String destinacii=input.readLine();
			tabela.insert(domasen, destinacii);
		}
		int M=Integer.parseInt(input.readLine());
		for (int i = 0; i < M; i++) {
			String domasen=input.readLine();
			String destinacii=input.readLine();
			String pomosna[] =destinacii.split("\\.");
			
			String destinacija= pomosna[0]+"."+pomosna[1]+"."+pomosna[2];
			//System.err.println(destinacija);
			int index=tabela.search(domasen);
			MapEntry<String,String> zapis=tabela.atIndex(index);
			//System.err.println(zapis);
			if(zapis== null) {
				System.err.println("ne postoi");
			}else {
				String site[]=zapis.value.split(",");	
				boolean isPostoi=false;
				for (int j = 0; j < site.length; j++) {
					if(site[j].contains(destinacija)) {
						System.err.println("postoi");
						isPostoi=true;
					}
				}
				if(!isPostoi) {
					System.err.println("ne postoi");
				}
				
			}
			
			
		}
	}

}
