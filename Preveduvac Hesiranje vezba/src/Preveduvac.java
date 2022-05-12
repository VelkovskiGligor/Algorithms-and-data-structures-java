import java.io.*;
class SLLNode<E>{
	protected E element;
	protected SLLNode<E> succ;
	public SLLNode(E element, SLLNode<E> succ){
		this.element=element;
		this.succ=succ;
	}
	@Override
	public String toString() {
		return this.element.toString();
	}
	
}

class MapEntry<K extends Comparable<K>,E> implements Comparable<K>{
	K key;
	E val;

	public MapEntry(K key, E val) {
		this.key=key;
		this.val=val;
	}
	@Override
	public int compareTo(K that) {
		MapEntry<K,E> other= (MapEntry<K, E>) that;
		return this.key.compareTo(other.key);
	}
	@Override
	public String toString() {
	    return "<" + key + "," + val + ">";
	}
}

class CBHT<K extends Comparable<K>,E>{
	private SLLNode<MapEntry<K,E>> buckets[];
	public CBHT(int m) {
		this.buckets=(SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
	}
	private int hash(K key) {
		return Math.abs(key.hashCode()) % buckets.length;
	}
	public SLLNode<MapEntry<K,E>> search(K targetKey){
		int b=hash(targetKey);
		for(SLLNode<MapEntry<K,E>> curr=buckets[b];curr!=null;curr=curr.succ) {
			if (targetKey.equals(((MapEntry<K, E>) curr.element).key)) {
				return curr;
			}
		}
		return null;
	}
	public void insert(K key, E val) {
		MapEntry<K,E> newEntry=new MapEntry<K,E>(key,val);
		int b=hash(key);
		for(SLLNode<MapEntry<K,E>>curr=buckets[b];curr!=null;curr=curr.succ) {
			if (key.equals(((MapEntry<K, E>) curr.element).key)) {
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
public class Preveduvac {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		CBHT<String,String> tabela=new CBHT<String,String>(N*2);
		for (int i = 0; i < N; i++) {
			String red=input.readLine();
			String [] pom=red.split(" ");
			String mkd=pom[0];
			String eng=pom[1];
			tabela.insert(eng, mkd);
		}
		while(true) {
			String zbor=input.readLine();
			if(zbor.equalsIgnoreCase("KRAJ"))break;
			SLLNode<MapEntry<String,String>> get=tabela.search(zbor);
			if(get!=null) {
				System.err.println(get.element.val);
			}else {
				System.err.println("/");
			}
		}
		
	}

}
