import java.io.*;
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
			}else if(oldEntry==former ) {
				buckets[indeks]=nov;
				return;
			}else if( key.equals(oldEntry.key)) {
			menager star=	(menager) buckets[indeks].val;
				star.brojvraboreni=star.brojvraboreni+1;	
				buckets[indeks].val=(E) star;
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
	public MapEntry<K,E> getElement(int n){
		return buckets[n];
	}
}
class menager{
	String ime;
	int brojvraboreni;
	public menager(String ime, int brojvraboreni) {
		this.ime = ime;
		this.brojvraboreni = brojvraboreni;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getBrojvraboreni() {
		return brojvraboreni;
	}
	public void setBrojvraboreni(int brojvraboreni) {
		this.brojvraboreni = brojvraboreni;
	}
	@Override
	public String toString() {
		return "menager [ime=" + ime + ", brojvraboreni=" + brojvraboreni + "]";
	}
	
	
}
public class kompanija {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		OBHT<String,menager> tabela=new OBHT<String,menager>(2*N);
		for (int i=0;i<N;i++) {
			String s=input.readLine();
			String nov=s.substring(1,s.length()-1);
			nov=nov.replaceAll(" ", "");
			String [] pom=nov.split(",");
			menager men=new menager(pom[1],1);
			tabela.insert(pom[1], men);
		}
		for(int i=0;i<2*N;i++) {
			MapEntry<String,menager> s=tabela.getElement(i);
			if(s!=null) {
				System.out.println(s.val.ime+" "+s.val.getBrojvraboreni());
			}
			
			
		}
	}

}
