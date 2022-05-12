//Implementacija na Graf orientiran ne tezinski
//So matrica na sosedstvo

class Graph<E>{
	int num_nodes;//broj jazli
	E nodes[];//Informacija na jazli
	int adjMat[][];
	public Graph(int num_nodes) {
		this.num_nodes=num_nodes;
		nodes=(E[]) new Object[num_nodes];
		adjMat=new int [num_nodes][num_nodes];
		for(int i=0;i<this.num_nodes;i++) {
			for(int j=0;j<this.num_nodes;j++) {
				adjMat[i][j]=0;
			}
		}
	}
	public Graph(int num_nodes, E[] nodes) {
		this.num_nodes=num_nodes;
		this.nodes= nodes;
		
	}
	public int adjacent(int x,int y) {
		// proveruva dali ima vrska od jazelot so indeks 
		//x do jazelot so indeks y
		return (adjMat[x][y]==0)? 0:1;
	}
	public void addEdge(int x,int y) {
		// dodava vrska megu jazlite so indeksi x i y
			adjMat[x][y]=1;
	}
	public void deleteEdge(int x,int y) {
		// ja brise vrskata megu jazlite so indeksi x i y
		   adjMat[x][y]=0;
		
	}
	public E get_node_value(int x) {
		//vrakja info na teme so se napgja na indeks x
		return nodes[x];
	}
	public void set_node_value(int x,E a) {
		// ja postavuva informacijata vo jazelot so indeks na a 
		this.nodes[x]=a;
		}
	public int getNum_nodes() {
		return num_nodes;
	}

	public void setNum_nodes(int num_nodes) {
		this.num_nodes = num_nodes;
	}
}
public class GrafTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
