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
class Children{
	private String ime;
	private String ulica;
	private String broj;
	private String grad;
	private String drzava;
	
	public Children(String ime, String ulica, String broj, String grad, String drzava) {
		super();
		this.ime = ime;
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
		this.drzava = drzava;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	@Override
	public int hashCode() {
		return 31* ime.charAt(0) ^4  + 31*ulica.charAt(0)^3 +31*broj.charAt(0) ^2 +31*grad.charAt(0) ^1 +31* drzava.charAt(0)^0;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Children other = (Children) obj;
		if (broj == null) {
			if (other.broj != null)
				return false;
		} else if (!broj.equals(other.broj))
			return false;
		if (drzava == null) {
			if (other.drzava != null)
				return false;
		} else if (!drzava.equals(other.drzava))
			return false;
		if (grad == null) {
			if (other.grad != null)
				return false;
		} else if (!grad.equals(other.grad))
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (ulica == null) {
			if (other.ulica != null)
				return false;
		} else if (!ulica.equals(other.ulica))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  ulica + " " + broj + " " + grad + " " + drzava;			
	}
	
	
}

public class dedomraz {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT <String,Children>spisok =new CBHT<String,Children>(N+N/2+N/4);
		for (int i = 0; i < N; i++) {
			String imenadete=input.readLine();
			String celosnoadresa=input.readLine();
			String []pom=celosnoadresa.split(" ");
			Children dete=new Children(imenadete, pom[0], pom[1], pom[2], pom[3]);
			spisok.insert(imenadete, dete);
		}
		int M=Integer.parseInt(input.readLine());
		CBHT <String,String>adresi =new CBHT<String,String>(2*M);
		for (int i = 0; i < M; i++) {
				String adresa=input.readLine();
				String stara=adresa.split(" ")[0];
				String nova=adresa.split(" ")[1];
				adresi.insert(stara, nova);
		}
		while(true) {
			String imenadete=input.readLine();
			if(imenadete.equalsIgnoreCase("kraj")) {
				break;
			}
			SLLNode<MapEntry<String,Children>> rezultat=spisok.search(imenadete);
			if(rezultat!=null) {
				//System.err.println(rezultat.element.value);
				String ulica=rezultat.element.value.getUlica();
				SLLNode<MapEntry<String,String>> ulicanode=adresi.search(ulica);
				if(ulicanode!=null) {
					rezultat.element.value.setUlica(ulicanode.element.value);
					System.err.println(rezultat.element.value.toString());
				}else {
					System.err.println(rezultat.element.value.toString());
				}
				
			}else {
				System.err.println("Nema poklon");
			}
		}
		

	}

}
