import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.io.*;
import java.security.spec.MGF1ParameterSpec;

//Implementacija na Netezinski nasocen graf so pomos na lista
class GraphNode<E>{
	private int index;// indeks (reden broj) na temeto vo grafot
	private E info; //informacija za temeto;
	private LinkedList<GraphNode<E>> neighbors;
	public GraphNode( int index, E info) {
		this.index=index;
		this.info=info;
		neighbors=new LinkedList<GraphNode<E>>();
	}
	public boolean containsNeighbor(GraphNode<E> o){
		//Proveruva dali na dadeniot jazol mu e sosed o
		//Sporeduva dva objekta
		return neighbors.contains(o);
	}
	public void addNeighbors(GraphNode<E> o) {
		//dodava sosed
		neighbors.add(o);
	}
	public void removeNeighbors(GraphNode<E> o) {
		//brise sosed
		if(neighbors.contains(o)) {
			neighbors.remove(o);
		}
	}
	public String toString() {
		String ret= "INFO:"+info+" SOSEDI:";
		for(int i=0;i<neighbors.size();i++)
		ret+=neighbors.get(i).info+" ";
		return ret;
		
	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		GraphNode<E> pom = (GraphNode<E>)obj;
		return (pom.info.equals(this.info));
	}
	public int getIndex() {
		return this.index;
	}
	public void setIndex(int index) {
		this.index=index;
	}	
	public E getInfo() {
		return info;
	}

	public void setInfo(E info) {
		this.info = info;
	}

	public LinkedList<GraphNode<E>> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
		this.neighbors = neighbors;
	}
}
interface Queue<E> {

	// Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    	// Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    	// Ja vrakja dolzinata na redicata.

    public E peek ();
    	// Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    	// Ja prazni redicata.

    public void enqueue (E x);
    	// Go dodava x na kraj od redicata.

    public E dequeue ();
    	// Go otstranuva i vrakja pochetniot element na redicata.

}
class LinkedQueue<E> implements Queue<E> {

	// Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
       	// Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
    	// Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
       	// Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
    	// Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
    	// Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
    	// Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}
class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}


class Graph <E>{
	private int num_nodes;// broj teminja
	private GraphNode<E> adjList []; //Teminjata na grafot
	
	public Graph(int num_nodes) {// Se kreira graf kade samo e
		//zadaden brojot na teminjata
		this.num_nodes=num_nodes;
		adjList=(GraphNode<E>[])new GraphNode[num_nodes];
	}
	public Graph(int num_nodes,E [] list) {//Se kreira graf kade e
		// zadaden brojot na teminja no i informaciite na teminjata
		this.num_nodes=num_nodes;
		adjList=(GraphNode<E>[])new GraphNode[num_nodes];
		for(int i=0;i<this.num_nodes;i++) {
			adjList[i]=new GraphNode<E>(i,list[i]);
		}
	}
		public int adjacent(int x,int y) {
		//Proveruva dali ima vrska od temeto so indeks x 
			//do temeto so indeks y
			return(adjList[x].containsNeighbor(adjList[y]))? 1:0;
	}
		public void addEdge(int x,int y) {
			// dodava vrska od jazelot so indeks x do jazelot so indeks y
			if(!adjList[x].containsNeighbor(adjList[y])) {
				adjList[x].addNeighbors(adjList[y]);
			}
		}
		public void deleteEdge(int x,int y) {
			//Brise sosed 
			if(adjList[x].containsNeighbor(adjList[y])) {
				adjList[x].removeNeighbors(adjList[y]);
			}
		}
		public void dfsSearch(int node) {
			boolean []visited=new boolean[this.num_nodes];
			for (int i = 0; i < visited.length; i++) {
				visited[i]=false;
			}
			dfsRecursive(node,visited);
		}
		public void dfsRecursive(int node,boolean [] visited) {
			visited[node]=true;
			System.out.println(node + ": " + adjList[node].getInfo());
			for(int i=0;i<adjList[node].getNeighbors().size();i++) {
					GraphNode<E> pom=adjList[node].getNeighbors().get(i);
				if(visited[pom.getIndex()]) {
					dfsRecursive(pom.getIndex(),visited);
				}
			}
		}
		public void dfsNonrecursive(int node) {
			boolean  [] visited=new boolean[this.num_nodes];
			for (int i = 0; i < visited.length; i++) {
				visited[i]=false;
				
			}
			visited[node]=true;
			System.out.println(node+": " + adjList[node].getInfo());
			Stack <Integer>stek=new Stack<Integer>();
			stek.push(node);
			GraphNode<E> pom;
			while(!stek.isEmpty()) {
				pom=adjList[stek.peek()];
				GraphNode <E> tmp=null;
				for(int i=0;i<pom.getNeighbors().size();i++) {
					tmp = pom.getNeighbors().get(i);
					if (!visited[tmp.getIndex()])
						break;
				}
				if(tmp!=null && !visited[tmp.getIndex()]){
					visited[tmp.getIndex()] = true;
					System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
					stek.push(tmp.getIndex());
				}else {
					stek.pop();
				}
			}
		}
		void bfs(int node){
			boolean visited[] = new boolean[num_nodes];
			for (int i = 0; i < num_nodes; i++)
				visited[i] = false;
			visited[node] = true;
			System.out.println(node+": " + adjList[node].getInfo());
			Queue<Integer> q = new LinkedQueue<Integer>();
			q.enqueue(node);
			
			GraphNode<E> pom;
			
			while(!q.isEmpty()){
				pom = adjList[q.dequeue()];
				GraphNode<E> tmp=null;
				for (int i = 0; i < pom.getNeighbors().size(); i++) {
					tmp = pom.getNeighbors().get(i);
					if (!visited[tmp.getIndex()]){
						visited[tmp.getIndex()] = true;
						System.out.println(tmp.getIndex()+": " + tmp.getInfo());
						q.enqueue(tmp.getIndex());
					}
				}
				
				
			}
			
		}
		

}
public class CountFacebookFriends {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int broj_korisnikci=Integer.parseInt(input.readLine());
		
		GraphNode<String> []pom=new GraphNode[broj_korisnikci];
		for (int i = 0; i < broj_korisnikci; i++) {
			int broj_prijateli=Integer.parseInt(input.readLine());
			pom[i]=new GraphNode<String>(i,"");
			for(int j=0;j<broj_prijateli;j++) {
				String red=input.readLine();
				String [] pomred=red.split(" ");
				GraphNode<String> friend=new GraphNode(Integer.parseInt(pomred[0]),pomred[1]+pomred[2]);
				pom[i].addNeighbors(friend);				
			}								
		}
		Graph<String> graf=new Graph<String>(broj_korisnikci,pom);
		
	}

}
