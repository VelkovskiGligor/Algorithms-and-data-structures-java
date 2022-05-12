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
	public int hash(K key) {
		String keyStr= (String)key;
			return (29*(29*(29*0+keyStr.charAt(0))+keyStr.charAt(1))+keyStr.charAt(2))%102780 % buckets.length;
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
class Lek implements Comparable<Lek> {
	private String ime;
	private int postoi;
	private int cena;
	private int zaliha;
	
	
	public Lek(String ime, int postoi, int cena, int zaliha) {
		this.ime = ime;
		this.postoi = postoi;
		this.cena = cena;
		this.zaliha = zaliha;
	}



	public String getIme() {
		return ime;
	}



	public void setIme(String ime) {
		this.ime = ime;
	}



	public int getPostoi() {
		return postoi;
	}



	public void setPostoi(int postoi) {
		this.postoi = postoi;
	}



	public int getCena() {
		return cena;
	}



	public void setCena(int cena) {
		this.cena = cena;
	}



	public int getZaliha() {
		return zaliha;
	}



	public void setZaliha(int zaliha) {
		this.zaliha = zaliha;
	}



	public int hashCode(int m) {
	
		return ime.charAt(m);
	}

	

	@Override
	public int compareTo(Lek arg0) {
		
		return this.ime.compareTo(arg0.ime);
	}



	@Override
	public String toString() {
		return "" + ime + "\n" + postoi + "\n" + cena + "\n" + zaliha + "";
	}
	
}
public class Apteka {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,Lek> tabela=new CBHT<String,Lek>(N+N/2+N/4);
		for(int i=0;i<N;i++) {
			String s=input.readLine();
			String [] pom=s.split(" ");
			String ime=pom[0];
			ime=ime.toUpperCase();
			int postoi=Integer.parseInt(pom[1]);
			int cena=Integer.parseInt(pom[2]);
			int zaliha=Integer.parseInt(pom[3]);
			Lek lek=new Lek(ime,postoi,cena,zaliha);
			tabela.insert(ime, lek);
		}
		while(true) {
			String name=input.readLine();
			if(name.equals("KRAJ")) {
				break;
			}
			 name=name.toUpperCase();
			 int br=Integer.parseInt(input.readLine());
			 SLLNode<MapEntry<String,Lek>> objekt=tabela.search(name);
			 if(objekt!=null) {
				 System.err.println(objekt.element.value.getIme());
				 if(objekt.element.value.getPostoi()==1) {
					 System.out.println("POZ");
				 }else {
					 System.out.println("NEG");
				 }
				 System.out.println(objekt.element.value.getCena());
				 System.out.println(objekt.element.value.getZaliha());
				 if(br<=objekt.element.value.getZaliha()) {
					 System.out.println("Napravena naracka");
					 tabela.insert(name, new Lek(name,objekt.element.value.getPostoi(),objekt.element.value.getCena(),objekt.element.value.getZaliha()-br));
				 }else {
					 System.out.println("Nema dovolno lekovi");
				 }
			 }else {
				 System.out.println("Nema takov lek");
			 }
			
		}
	}

}
