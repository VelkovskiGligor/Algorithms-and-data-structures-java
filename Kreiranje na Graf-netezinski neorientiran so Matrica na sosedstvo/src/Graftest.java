
import java.util.Stack;
import java.util.NoSuchElementException;
//Implementacija na: netezinski ne orientiran graf 
//so Matrica na sosedstvo
class Graph<E>{
	int num_nodes;// Pretstavuva kolku jazli(teminja) ke ima grafot
	E nodes[];// Informacii koj ke gi sodrzi sekoe teme
	int adjMat[][];
	public Graph(int num_nodes) {
		this.num_nodes=num_nodes;
		nodes=(E[]) new Object[num_nodes];
		adjMat=new int[num_nodes][num_nodes];
		for (int i = 0; i < this.num_nodes; i++) {
			for(int j=0;j<this.num_nodes;j++) {
				adjMat[i][j]=0;
			}
		}
	}
	public Graph(int num_nodes,E [] nodes) {
		this.num_nodes=num_nodes;
		this.nodes=nodes;
		adjMat=new int [num_nodes][num_nodes];
		for(int i=0;i<this.num_nodes;i++) {
			for (int j=0;j<this.num_nodes;j++) {
				adjMat[i][j]=0;
			}
		}
	}
	public int adjacent(int x,int y) {
		// proveruva dali ima vrska od jazelot so indeks x do jazelot
		//so indeks y

		return (adjMat[x][y]!=0)?1:0;
	}
	public void addEdge(int x,int y) {
		//Dodava rebro vo matricata
			adjMat[x][y]=1;
			adjMat[y][x]=1;
	}
	public void deleteEdge(int x,int y) {
		//Brise rebro
		adjMat[x][y]=0;
		adjMat[y][x]=0;
	}
	public E get_node_value(int x) {
		//Vrakja informacija na temeto na indeks x
		return nodes[x];
	}
	public void set_node_value(int x,E a) {
		nodes[x]=a;
		
	}
	public int getNum_nodes() {
		return this.num_nodes;
	}
	public void setNum_nodes(int num_nodes) {
		this.num_nodes = num_nodes;
		}
	
	void dfsSearch(int node) {
	 boolean visited[]=new boolean[this.num_nodes];
	 for(int i=0;i<visited.length;i++) {
		 visited[i]=false;
	 }
	 dfsRecursive(node, visited);
	}
	void dfsRecursive(int node, boolean visited[]) {
		visited[node]=true;
		System.out.println(node + ": " + nodes[node]);
		for(int i=0;i<this.num_nodes;i++) {
			if(adjacent(node, i)==1) {
				if(!visited[i]) {
					 dfsRecursive(i, visited);
				}
			}
		}
		
		
	}
	void dfsNonrecursive(int node) {
		boolean [] visited=new boolean[this.num_nodes];
		for(int i=0;i<this.num_nodes;i++) {
			visited[i]=false;
		}
		visited[node]=true;
		Stack <Integer> stek=new Stack<Integer>();
		System.out.println(node + ": " + nodes[node]);
		stek.push(node);
		int pom;
		while(!stek.isEmpty()){
			pom=stek.peek();
			int pom2=pom;
			for(int i=0;i<this.num_nodes;i++) {
				if(adjacent(pom,i)==1) {
					pom2=i;
					if(!visited[i]) {
						break;
					}
				}
			}
			if(!visited[pom2]) {
				visited[pom2]=true;
				System.out.println(pom2 + ": " + nodes[pom2]);
				stek.push(pom2);
			}else {
				stek.pop();
			}
			
			
		}
		
	}
	public void bfs(int node) {
		boolean []  visited=new boolean[this.num_nodes];
		for(int i=0;i<this.num_nodes;i++) {
			visited[i]=false;
		}
		visited[node]=true;
		System.out.println(node+": " + nodes[node]);
		LinkedQueue <Integer> redica = new LinkedQueue<Integer>();
		redica.enqueue(node);
		while(!redica.isEmpty()) {
			int pom=redica.dequeue();
			for(int i=0;i<this.num_nodes;i++) {
				if(adjacent(pom,i)==1) {
					if(!visited[i]) {
						visited[i]=true;
						System.out.println(i+": " + nodes[i]);
						redica.enqueue(i);
					}
				}
			}
		}
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

  
public class Graftest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
