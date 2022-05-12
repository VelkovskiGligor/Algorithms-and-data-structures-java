import java.io.*;
class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E element,SLLNode<E> succ) {
		this.element=element;
		this.succ=succ;
	}
	@Override
	public String toString() {
		return "" + element ;
	}
	
}
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
	public int compareTo(K that) {
		// TODO Auto-generated method stub
		MapEntry<K,E> other=(MapEntry<K, E>) that;
		
		return this.key.compareTo(other.key);
	}
	
}
class CBHT<K extends Comparable<K>,E>{
	SLLNode<MapEntry<K,E>> buckets[];

	public CBHT(int m) {
		super();
		this.buckets = (SLLNode<MapEntry<K,E>>[])new SLLNode[m];
	}
	
	private int hash(K key) {
		String ime=(String)key;
		return Math.abs((29*(29*(29*0+ime.charAt(0))+ime.charAt(1))+ime.charAt(2))%102780) % buckets.length;
	}
	public SLLNode<MapEntry<K,E>> search(K key){
		int b=hash(key);
		for(SLLNode<MapEntry<K,E>> curr=buckets[b];curr!=null;curr=curr.succ) {
			if(curr.element.key.equals(key)) {
				return curr;
			}
		}
		return null;
	}
	public void insert(K key,E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int b=hash(key);
		for(SLLNode<MapEntry<K,E>> curr=buckets[b];curr!=null;curr=curr.succ) {
			if(curr.element.key.equals(key)) {
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
class Lek{
		private String ime;
		private int pozitive;
		private int cena;
		private int kolicina;
		public Lek(String ime, int pozitive, int cena, int kolicina) {
			super();
			this.ime = ime;
			this.pozitive = pozitive;
			this.cena = cena;
			this.kolicina = kolicina;
		}
		@Override
		public String toString() {
			String pom=ime+"\n";
			if(pozitive==0) {
				pom+="NEG"+"\n";
			}else {
				pom+="POZ"+"\n";
			}
			pom+=cena+"\n";
			pom+=kolicina;
			
			return pom;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + cena;
			result = prime * result + ((ime == null) ? 0 : ime.hashCode());
			result = prime * result + kolicina;
			result = prime * result + pozitive;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Lek other = (Lek) obj;
			if (cena != other.cena)
				return false;
			if (ime == null) {
				if (other.ime != null)
					return false;
			} else if (!ime.equals(other.ime))
				return false;
			if (kolicina != other.kolicina)
				return false;
			if (pozitive != other.pozitive)
				return false;
			return true;
		}
		public String getIme() {
			return ime;
		}
		public void setIme(String ime) {
			this.ime = ime;
		}
		public int getPozitive() {
			return pozitive;
		}
		public void setPozitive(int pozitive) {
			this.pozitive = pozitive;
		}
		public int getCena() {
			return cena;
		}
		public void setCena(int cena) {
			this.cena = cena;
		}
		public int getKolicina() {
			return kolicina;
		}
		public void setKolicina(int kolicina) {
			this.kolicina = kolicina;
		}
		
	
}
public class Apteka {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,Lek> aplikacija=new CBHT<String,Lek>(2*N);
		for (int i = 0; i < N; i++) {
			String red=input.readLine();
			String pom[] =red.split(" ");
			String ime=pom[0];
			int  pozitive=Integer.parseInt(pom[1]);
			int cena=Integer.parseInt(pom[2]);
			int kolicina=Integer.parseInt(pom[3]);
			Lek lek=new Lek(ime, pozitive, cena, kolicina);
			//VO SLUCAJ EDEN LEK DA SE DODADE POVEKE PATI SAMO DA SE ZGOLEMI KOLICINATA
			SLLNode<MapEntry<String,Lek>> get=aplikacija.search(ime);
			if(get==null) {
				aplikacija.insert(ime, lek);
			}else {
				Lek vtorlek=new Lek(ime,pozitive,cena,kolicina+get.element.value.getKolicina());
				aplikacija.insert(ime,vtorlek);
			}
		
		}
		
		while(true) {
			String ime=input.readLine();
			if(ime.equalsIgnoreCase("KRAJ"))break;
			int kolicina=Integer.parseInt(input.readLine());
			ime=ime.toUpperCase();
			SLLNode<MapEntry<String,Lek>> get=aplikacija.search(ime);
			if(get==null) {
				System.err.println("Nema takov lek");
			}else {
				if(get.element.value.getKolicina()<kolicina) {
					System.err.println("Nema dovolno lekovi");
				}else {
					System.err.println(get.element.value);
					System.err.println("Napravena naracka");
					get.element.value.setKolicina(get.element.value.getKolicina()-kolicina);
					aplikacija.insert(get.element.value.getIme(),get.element.value);
				}
			}
			
		}
	}

}
