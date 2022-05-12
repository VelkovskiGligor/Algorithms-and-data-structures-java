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

public class RoutingHashJava {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		OBHT <String,String> tabela=new OBHT<String,String>(N +N/2+N/8);
		for(int i=0;i<N;i++) {
			String ruter=input.readLine();
			String pristap=input.readLine();
			tabela.insert(ruter, pristap);
			
		}
		int M=Integer.parseInt(input.readLine());
		for(int i=0;i<M;i++) {
			String ruter=input.readLine();
			String pristap=input.readLine();
			int n=tabela.search(ruter);
			boolean flag=false;
			if(n!=-1) {
				MapEntry<String,String> rezz=tabela.atIndex(n);
				String pristapi=rezz.val;
				String [] pom= pristapi.split(",");
				for(int j=0;j<pom.length;j++) {
					String []x=pom[j].split("\\.");
					String s="";
					for(int w=0;w<x.length-1;w++) {
						s=s+x[w]+".";
					}
					//System.out.println(s);
					if(pristap.contains(s) || pristap.equals(pom[j])) {
						System.out.println("postoi");
						flag=true;
						break;
						
					}
				}
				if(!flag) {
					System.out.println("ne postoi");
				}
			}else {
				System.out.println("ne postoi");
			}
		
			 
		}
		
		
		
	}

}
