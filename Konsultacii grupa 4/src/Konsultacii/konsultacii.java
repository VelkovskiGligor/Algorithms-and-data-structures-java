package Konsultacii;
import java.io.*;
class SLLNode<E>{
	E ime;

	SLLNode<E> succ;
	public SLLNode(E element ,SLLNode <E> succ) {
		this.ime=element;
		this.succ=succ;
	}
	
}
class SLL <E>{
	SLLNode <E> first;
	public SLL() {
		this.first=null;
	}
	public void insertFirst(E ime ) {
		SLLNode <E> nov=new SLLNode<E>(ime,first);
		first=nov;
	}
	public void insertLast(E ime) {
		SLLNode <E> dvizi=first;
		if(first==null) {
			insertFirst(ime);
		}else {
		while(dvizi.succ!=null) {
			dvizi=dvizi.succ;
		}
		SLLNode <E> nov=new SLLNode <E>(ime,null);
		dvizi.succ=nov;
		}
	}
	 public String toString() {
		 String string=new String();
		 if(first==null) {
			 return "Listata e prazna";
		 }else {
			 SLLNode <E> dvizi=first;
			 while(dvizi!=null) {
				 if(dvizi.succ!=null) {
					 string=string+dvizi.ime+"->";
				 }else {
					 string=string+dvizi.ime + " " ;
				 }
				 dvizi=dvizi.succ;
			 }
		 }
		 return string;
	 }
	 public SLLNode<E> getFirst(){
		 return this.first;
	 }
	 public void setFirst(SLLNode<E> here){
		 this.first=here;
		 
	 }
	 public int lenght() {
			SLLNode <E> dvizi=first;
			int lenght=0;
			if(dvizi!=null) {
				while(dvizi!=null) {
					lenght++;
					dvizi=dvizi.succ;
				}
			return lenght;
			}else 
				return 0;
		}
	 public SLLNode<E> getLast(){
		 SLLNode<E> dvizi=this.first;
		 while(dvizi.succ!=null) {
			 dvizi=dvizi.succ;
		 }
		 return dvizi;
	 }
	 
}
public class konsultacii {
	public static void pecati(SLL <String> APS,SLL <String> MMS) {
		SLLNode<String> dvizivtora=MMS.first;
		SLLNode<String> last=null;
		SLLNode<String> dvizi=APS.getFirst();
		SLLNode<String> predvizi=null;
		String pred=new String();
		last=APS.getLast();
		while(dvizi!=null) {
			String []pom=dvizi.ime.split(" ");
			String tip=pom[1];
			if(!tip.equals(pred)) {
				System.out.println(dvizi.ime);
				if(predvizi==null) {
					predvizi=dvizi;
				}else {
					predvizi=predvizi.succ;
				}
				dvizi=dvizi.succ;
			}else {
				if(dvizivtora!=null) {
					System.out.println(dvizivtora.ime);
					dvizivtora=dvizivtora.succ;
					if(dvizi.succ!=null) {
						predvizi.succ=dvizi.succ;
						dvizi.succ=null;
						last=APS.getLast();
					last.succ=dvizi;
					dvizi=predvizi.succ;
					}else {
						System.out.println(dvizi.ime);
						break;
					}
						
				}else {
					break;
				}
				
			}
			pred=tip;
		}
		if(dvizi!=null) {
			System.out.println(dvizi.ime);
			dvizi=dvizi.succ;
		}
		if(dvizivtora!=null) {
			System.out.println(dvizivtora.ime);
			dvizivtora=dvizivtora.succ;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Vnesete kolku elementi ima prvata lista");
		String x=input.readLine();
		int n=Integer.parseInt(x);
		SLL<String> APS =new SLL<String>();
		for (int i=0;i<n;i++) {
			String recenica=input.readLine();
			
			APS.insertLast(recenica);
			
		}
		
		System.out.println("Vnesete kolku elementi ima vtorata lista");
		String z=input.readLine();
		int m=Integer.parseInt(z);
		SLL<String> MMS =new SLL<String>();
		for (int i=0;i<m;i++) {
			String recenica=input.readLine();
			String [] niza =recenica.split(" ");
			MMS.insertLast(recenica);
		}	
		System.out.println(APS);
		System.out.println(MMS);
		pecati(APS,MMS);
	
	}
	
}
