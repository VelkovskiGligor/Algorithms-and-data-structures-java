import java.io.*;

class SLLNode<E>{
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E elemenet,SLLNode<E> succ) {
		this.element=elemenet;
		this.succ=succ;
	}
	@Override
	public String toString() {
		return "" + element + "";
	}
}
class MapEntry<K extends Comparable<K>,E> implements Comparable<K>{
	K key;
	E vlaue;
	
	public MapEntry(K key,E val) {
		this.key=key;
		this.vlaue=val;
	}
	@Override
	public int compareTo(K that) {
		// TODO Auto-generated method stub
		MapEntry<K,E> other=(MapEntry<K, E>) that;
		return this.key.compareTo(other.key);
	}
	@Override
	public String toString() {
		return "<" + key + ", " + vlaue + ">";
	}
}
class CBHT<K extends Comparable<K>,E>{
	private SLLNode<MapEntry<K,E>> buckets[];
	
	public CBHT(int m) {
		buckets=(SLLNode<MapEntry<K,E>>[])new SLLNode[m];
	}
	private int hash(K key) {
		return Math.abs(key.hashCode()) % buckets.length;
	}
	public SLLNode<MapEntry<K,E>> search(K targetKey){
		int b=hash(targetKey);
		for(SLLNode<MapEntry<K,E>> curr=buckets[b];curr!=null;curr=curr.succ) {
			if(curr.element.key.equals(targetKey)) {
				return curr;
			}
		}
		return null;
	}
	
	public void insert(K key,E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int b=hash(key);
		for(SLLNode<MapEntry<K,E>> curr=buckets[b];curr!=null;curr=curr.succ) {
			if(key.equals(curr.element.key)) {
				curr.element=newEntry;
				return;
			}
		}
		buckets[b]=new SLLNode<MapEntry<K,E>>(newEntry,buckets[b]);
	}
	public void delete(K key) {
		int b=hash(key);
		for(SLLNode<MapEntry<K,E>>pred=null, curr=buckets[b];curr!=null;pred=curr,curr=curr.succ) {
			if(curr.element.key.equals(key)) {
				if(pred==null) {
					buckets[b]=curr.succ;
				}else {
					pred.succ=curr.succ;
				}
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
class accaunt{
	private String name;
	private String password;
	
	public accaunt(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "" + password + "";
	}

	@Override
	public int hashCode() {
		String s=name;
		int broj=1;
		for(int i=0;i<name.length();i++) {
			broj=broj+ name.charAt(i)*31 ^ (name.length()-1-i);
		}
		return broj;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		accaunt other = (accaunt) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
public class Lozinki {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,accaunt> baza=new CBHT<String,accaunt>(2*N);
		for (int i = 0; i < N; i++) {
			String red=input.readLine();
			String [] pom=red.split(" ");
			accaunt nov=new accaunt(pom[0],pom[1]);
			baza.insert(pom[0], nov);
		}
		while(true) {
			String red=input.readLine();
			if(red.equals("KRAJ")) {
				return;
			}else {
				String pom[] =red.split(" ");
				SLLNode<MapEntry<String,accaunt>> zapis=baza.search(pom[0]);
				if(zapis!=null) {
					if(pom[1].equals(zapis.element.vlaue.getPassword())) {
						System.err.println("Najaven");
					}else {
						System.err.println("Nenajaven");
					}
				}else {
					System.err.println("Nenajaven");
				}
				
			}
			
		}
	}

}
