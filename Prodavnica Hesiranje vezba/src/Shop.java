import java.io.*;
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
class Proizvod{
	private String ime;
	private int kolicina;
	private int cena;
	public Proizvod(String ime, int kolicina, int cena) {
		super();
		this.ime = ime;
		this.kolicina = kolicina;
		this.cena = cena;
	}
	@Override
	public int hashCode() {
		int result=0;
		for (int i = 0; i < ime.length(); i++) {
			result+= (ime.charAt(i) *31 ^(ime.length()-1-i));
		}
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
		Proizvod other = (Proizvod) obj;
		if (cena != other.cena)
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (kolicina != other.kolicina)
			return false;
		return true;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	
	
}
public class Shop {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,Proizvod> prodavnica=new CBHT<String,Proizvod>(2*N);
		for (int i = 0; i <N; i++) {
			String red=input.readLine();
			String [] pom=red.split(" ");
			String ime=pom[0];
			int cena=Integer.parseInt(pom[2]);
			int kolicina=Integer.parseInt(pom[3]);
			Proizvod proizvod=new Proizvod(ime, kolicina, cena);
			prodavnica.insert(ime, proizvod);
		}
		int M=Integer.parseInt(input.readLine());
		for (int i = 0; i <M; i++) {
			String red=input.readLine();
			String [] pom=red.split(" ");
			String ime=pom[0];
			int cena=Integer.parseInt(pom[2]);
			int kolicina=Integer.parseInt(pom[3]);
			SLLNode<MapEntry<String,Proizvod>> get=prodavnica.search(ime);
			if(get== null) {
				Proizvod proizvod=new Proizvod(ime, kolicina, cena);
				prodavnica.insert(ime, proizvod);
			}else {
				get.element.value.setKolicina(get.element.value.getKolicina()+kolicina);
			}
			
		}
		int vkupno=0;
		while(true) {
			String kupuvac=input.readLine();
			if(kupuvac.equals("TRGOVECKRAJ"))break;
			String ime=kupuvac.split(" ")[0];
			int kolicina=Integer.parseInt(kupuvac.split(" ")[1]);
			SLLNode<MapEntry<String,Proizvod>> get=prodavnica.search(ime);
			if(get== null) {
					System.err.println("Nema takov proizvod");
			}else {
				if(kolicina<= get.element.value.getKolicina()) {
					System.err.println("Kupeno: "+kolicina+" parcinja "+ime+" za kupna cena od "+ kolicina*get.element.value.getCena());
					get.element.value.setKolicina(get.element.value.getKolicina()-kolicina);
					System.err.println("Preostanuvaat uste " + get.element.value.getKolicina()+" parcinja");
					vkupno+=kolicina*get.element.value.getCena();
				}else if(get.element.value.getKolicina() !=0) {
					System.err.println("Kupeno: "+get.element.value.getKolicina()+" parcinja "+ime+" za kupna cena od "+ get.element.value.getKolicina()*get.element.value.getCena());
					vkupno+=get.element.value.getKolicina()*get.element.value.getCena();
					get.element.value.setKolicina(0);
					System.err.println("Preostanuvaat uste " + get.element.value.getKolicina()+" parcinja");
				}else {
					System.err.println("Proizvodot "+ ime+" go nema na zaliha");
				}
			}
			
		}
		System.err.println("Vkupen promet napraven: "+vkupno);
	}

}
