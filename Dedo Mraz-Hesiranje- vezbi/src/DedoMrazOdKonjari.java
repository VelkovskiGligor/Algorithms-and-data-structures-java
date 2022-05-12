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
		int pom=0;
		String kluc=(String)key;
		for (int i = 0; i < kluc.length(); i++) {
			pom=pom +(kluc.charAt(i)*31 ^(kluc.length()-1-i));
			
		}
		return  Math.abs(pom) % buckets.length;
				
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
class Adresa{
	private String ime;
	private String broj;
	private String grad;
	private String drzava;
	public Adresa(String ime, String broj, String grad, String drzava) {
		super();
		this.ime = ime;
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
	public String toString() {
		return "" + ime + " " + broj + " " + grad + " " + drzava + "";
	}
	
	
}
public class DedoMrazOdKonjari {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,Adresa> spisok=new CBHT<String,Adresa>(2*N);
		for(int i=0;i<N;i++) {
			String ime=input.readLine();
			String ulica=input.readLine();
			String pom[]=ulica.split(" ");
			Adresa adresa=new Adresa(pom[0],pom[1],pom[2],pom[3]);
			spisok.insert(ime,adresa);
		}
		int M=Integer.parseInt(input.readLine());
		CBHT<String,String> promeneti=new CBHT<String,String>(2*M);
		for (int i = 0; i < M; i++) {
			String ulici=input.readLine();
			String []spliulica=ulici.split(" ");
			promeneti.insert(spliulica[0], spliulica[1]);
		}
		String dete=input.readLine();
		SLLNode<MapEntry<String,Adresa>> zapis=spisok.search(dete);
		if(zapis==null) {
			System.err.println("Nema poklon");
		}else {
			SLLNode<MapEntry<String,String>> novaulica=promeneti.search(zapis.element.value.getIme());
			if(novaulica== null) {
				System.err.println(zapis.element.value);
			}else {
				String nova=novaulica.element.value;
				zapis.element.value.setIme(nova);
				System.err.println(zapis.element.value);
			}
		}
		
	}

}
