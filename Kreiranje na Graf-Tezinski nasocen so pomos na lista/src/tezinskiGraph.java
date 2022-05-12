import java.util.Iterator;
import java.util.LinkedList;



//Implementcaja na Tezinski graf orientiran
class GraphNodeNeighbor <E>{
	GraphNode <E> node; //Sosedot na dadeno teme
	float weight;
	public GraphNodeNeighbor(GraphNode <E> node,float weight) {
		this.node=node;
		this.weight=weight;
	}
	public GraphNodeNeighbor(GraphNode<E> node) {
		this.node=node;
		this.weight=0;
	}
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
		return pom.node.equals(this.node);
	}
}
class GraphNode<E>{
	private int index;//reden broj na temeto vo grafot\
	private E info; //informacija za temeto
	private LinkedList<GraphNodeNeighbor<E>> neighbors;
	public GraphNode(int index,E info) {
		this.index=index;
		this.info=info;
		this.neighbors=new LinkedList<GraphNodeNeighbor<E>>();
	}
	public boolean containsNeighbor(GraphNode<E> o) { 
		//Provaruva dali dadeno teme e sosed na drugo teme
		GraphNodeNeighbor<E> pom=new GraphNodeNeighbor(o,0);
		return neighbors.contains(o);
	}
	public void addNeighbor(GraphNode<E> o,float weight) {
		GraphNodeNeighbor<E> pom=new GraphNodeNeighbor<E>(o,weight);
		this.neighbors.add(pom);
	}
	public void removeNeighbore(GraphNode<E> o) {
		GraphNodeNeighbor<E> pom=new GraphNodeNeighbor(o,0);
		if(neighbors.contains(pom)) {
			neighbors.remove(pom);
		}
	}
	public void updateNeighborWeight(GraphNode<E> o, float weight){
		Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
		while(i.hasNext()) {
			GraphNodeNeighbor<E> pom = i.next();
			if(pom.node.equals(o)) {
				pom.weight=weight;
			}
		}
	}
	
	@Override
	public String toString() {
		String ret= "INFO:"+info+" SOSEDI:";
		for(int i=0;i<neighbors.size();i++)
		ret+="("+neighbors.get(i).node.info+","+neighbors.get(i).weight+") ";
		return ret;
		
	}
	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		GraphNode<E> pom = (GraphNode<E>)obj;
		return (pom.info.equals(this.info));
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public E getInfo() {
		return info;
	}

	public void setInfo(E info) {
		this.info = info;
	}

	public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
		this.neighbors = neighbors;
	}
	
	
}
class Graph<E>{
	int num_node;// broj teminja
	GraphNode<E> adjList []; //teminjata vo grafot
	public Graph(int num_node) {
		this.num_node=num_node;
		adjList=(GraphNode<E>[]) new Object[num_node];
	}
	public Graph(int num_node, E [] list) {
		this.num_node=num_node;
		adjList=(GraphNode<E>[]) new Object [num_node];
		for(int i=0;i<num_node;i++) {
			adjList[i]=new GraphNode<E> (i,list[i]);
		}
	}
	int adjacent(int x, int y) { 
		// proveruva dali ima vrska od jazelot so
		// indeks x do jazelot so indeks y
		return adjList[x].containsNeighbor(adjList[y])?1:0;
	}
	public void addEdge(int x,int y,float tezina) {
		if(adjList[x].containsNeighbor(adjList[y])) {
			adjList[x].updateNeighborWeight(adjList[y], tezina);
		}else {
			adjList[x].addNeighbor(adjList[y], tezina);
		}
	}
	public void deleteEdge(int x,int y) {
		adjList[x].removeNeighbore(adjList[y]);
	}
}
public class tezinskiGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
