	import java.util.Scanner;
	
	class SLLNode {
	    String index;
	    String name;
	    int points;
	    SLLNode succ;
	
	    public SLLNode(String index, String name, int points, SLLNode succ) {
	        this.index = index;
	        this.name = name;
	        this.points = points;
	        this.succ = succ;
	    }
	
	    @Override
	    public String toString() {
	       return name;
	    }
	}
	
	class SLL {
	    SLLNode first;
	
	    public SLL() {
	        this.first = null;
	    }
	
	    public void insertFirst(String index, String name, int points) {
	        SLLNode ins = new SLLNode(index, name, points, first);
	        first = ins;
	    }
	
	    public void insertLast(String index, String name, int points) {
	        if (first != null) {
	            SLLNode tmp = first;
	            while (tmp.succ != null)
	                tmp = tmp.succ;
	            SLLNode ins = new SLLNode(index, name, points, null);
	            tmp.succ = ins;
	        } else {
	            insertFirst(index, name, points);
	        }
	    }
	
	    @Override
	    public String toString() {
	        StringBuilder ret = new StringBuilder();
	        if (first != null) {
	            SLLNode tmp = first;
	            ret.append(tmp).append("\n");
	            while (tmp.succ != null) {
	                tmp = tmp.succ;
	                ret.append(tmp).append("\n");
	            }
	        } else
	            ret = new StringBuilder("NO ELEMENTS");
	        return ret.toString();
	    }
	}
	
	public class Students {
	
	    public static void removeStudent(SLL students) {
	       //todo: implement function
	    	SLLNode dvizi=students.first;
	    	int min=dvizi.points;
	    	SLLNode brisi=dvizi;
	    	while(dvizi!=null) {
	    		if(dvizi.points<min) {
	    			min=dvizi.points;
	    			brisi=dvizi;
	    		}
	    		dvizi=dvizi.succ;
	    	}
	    	if(brisi==students.first) {
	    		students.first=brisi.succ;
	    		return;
	    	}
	    	dvizi=students.first;
	    	SLLNode pom= dvizi;
	    	dvizi=dvizi.succ;
	    	while(dvizi!=null) {
	    		if(dvizi==brisi) {
	    			pom.succ=dvizi.succ;
	    			return;
	    		}
	    		pom=dvizi;
	    		dvizi=dvizi.succ;
	    	}
	    	
	    }
	
	    public static void main(String[] args) {
	        Scanner scanner =  new Scanner(System.in);
	        int n = Integer.parseInt(scanner.nextLine());
	        SLL students =  new SLL();
	
	        for(int i=0; i<n; i++){
	            String line = scanner.nextLine();
	            String[] parts = line.split("\\s+");
	            students.insertLast(parts[0], parts[1], Integer.parseInt(parts[2]));
	        }
	
	        removeStudent(students);
	        System.out.println(students.toString());
	    }
	}
