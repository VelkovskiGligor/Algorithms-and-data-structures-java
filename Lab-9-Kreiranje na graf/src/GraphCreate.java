import java.io.*;
class Graph<E>{
	int num_node;// indeks na node
	E nodes[];
	int adjMat[][]; //matrica na sosedstvo
	public Graph(int num) {
		this.num_node=num;
		nodes=(E[]) new Object[num];
		adjMat=new int [num][num];
		for(int i=0;i<num;i++) {
			for(int j=0;j<num;j++) {
				adjMat[i][j]=0;
			}
		}
	}
	public Graph(int num_node,E [] nodes) {
		this.num_node=num_node;
		nodes=nodes;
		adjMat=new int [num_node][num_node];
		for(int i=0;i<num_node;i++) {
			for(int j=0;j<num_node;j++) {
				adjMat[i][j]=0;
			}
		}
	}
	public int adjacent(int x,int y) {
		// proveruva dali se sosedi
		return  (adjMat[x][y]==1)?1:0;
	}
	public void addEdge(int x,int y) {
		adjMat[x][y]=1;
		adjMat[y][x]=1;
	}
	public void deleteEdge(int x,int y) {
		adjMat[x][y]=0;
		adjMat[y][x]=0;
	}
	E get_node_value(int x)
	{  // ja vraka informacijata vo jazelot so indeks x
	      return nodes[x];
	}

	// Moze i ne mora?
	void set_node_value(int x, E a)
	{  // ja postavuva informacijata vo jazelot so indeks na a 
	   nodes[x]=a;
	}
	
	public int getNum_nodes() {
		return num_node;
	}

	public void setNum_nodes(int num_nodes) {
		this.num_node = num_nodes;
	}
}
public class GraphCreate {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(input.readLine());
		String s= input.readLine();
		String kreiranje[] =s.split(" ");
		int brojjazli=Integer.parseInt(kreiranje[1]);
		char[] teminja=new char[brojjazli];
		Graph<Character>graf=new Graph<Character>(brojjazli);
		for(int i=0;i<brojjazli;i++) {
			graf.set_node_value(i,(char) ('A'+i));
		}
		for(int i=0;i<N-1;i++) {
			String ss= input.readLine();
			String [] niza=ss.split(" ");
			if(niza[0].equals("ADDEDGE")) {
				graf.addEdge(Integer.parseInt(niza[1]), Integer.parseInt(niza[2]));
			}
			if(niza[0].equals("PRINTMATRIX")) {
				for(int x=0;x<graf.num_node;x++) {
					for(int z=0;z<graf.num_node;z++) {
						System.err.print(graf.adjMat[x][z]+" ");
					}
					System.err.println("");
				}
				
			}
			if(niza[0].equals("PRINTNODE")) {
				System.err.println(graf.get_node_value(Integer.parseInt(niza[1])));
				
			}
			if(niza[0].equals("ADJACENT")) {
				System.err.println(graf.adjMat[Integer.parseInt(niza[1])][Integer.parseInt(niza[2])]);
				
			}
			if(niza[0].equals("DELETEEDGE")) {
				graf.deleteEdge(Integer.parseInt(niza[1]),Integer.parseInt(niza[2]));	
			}
		
		}
	}

}
