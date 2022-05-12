package Polinomi;

import java.util.ArrayList;
import java.util.Iterator;



class Polinom{
		private ArrayList<Integer> list;
			int size;
		public Polinom() {
					this.list= new ArrayList<Integer>();
		}
		public Polinom(ArrayList<Integer> list) {
			this.list=list;
		}
		public int getSize() {
			return this.size;
		}
		public ArrayList<Integer> getList(){
			return this.list;
		}
		@Override
		public String toString() {
				String string="";
					for (int i=this.list.size()-1;i>=0;i--) {
						if(i!=0) {
						string=string+this.list.get(i)+"x^"+i+" + ";
						}else {
							string=string+this.list.get(i)+"x^"+i;
						}
					}
					return string;
		}
		public  ArrayList<Integer> soberiPolinomi(Polinom prvi,Polinom vtori){
			ArrayList<Integer> zbir=new ArrayList<Integer>();
	
			if(prvi.list.size()<vtori.list.size()) {
				Polinom temp=prvi;
				prvi=vtori;
				vtori=temp;
				
			}
			//System.out.println(prvi+" " +vtori);
			int sum;
			//System.out.println(prvi.getList().size());
			for (int i = prvi.getList().size()-1; i>= 0; i--) {
				
						if(i<vtori.getList().size()) {
							sum=prvi.getList().get(i)+vtori.getList().get(i);
						System.out.println("Sumata na "+prvi.getList().get(i)+" i "+vtori.getList().get(i)+" e "+sum);
						zbir.add(sum);
						}else {
							zbir.add(prvi.getList().get(i));
							System.out.println("Se dodava samo: "+prvi.getList().get(i));
						}
			}
			System.out.println("Rezultatnata niza e: "+zbir);
			int size=zbir.size()/2;
			for (int i=0;i<size;i++) {
				System.out.println("a");
				int temp=zbir.get(i);
				zbir.remove(i);
				zbir.add(i, zbir.get(zbir.size()-1-i));
				zbir.remove(zbir.size()-1-i);
				zbir.add(zbir.size()-i, temp);
				System.out.println(zbir);
				
			}
			System.out.println("Obrnetaata "+zbir);
			return zbir;			
		}
		
		
	}
public class Polinomia {

	public static void main(String[] args) {
		int [] nizaa= {1,2,3,4};
			ArrayList<Integer> prva=new ArrayList<Integer>();
			ArrayList<Integer> vtora=new ArrayList<Integer>();
			prva.add(1);
			prva.add(2);
			prva.add(3);
			prva.add(4);
			prva.add(5);
			vtora.add(7);
			vtora.add(0);
			vtora.add(2);
			vtora.add(9);
			System.out.println(prva);
			System.out.println(vtora);
		Polinom prv=new Polinom(prva);
		Polinom vtor=new Polinom(vtora);
		System.out.println(prv.toString());
		System.out.println(vtor.toString());
	
		ArrayList<Integer> rezultat=prv.soberiPolinomi(prv, vtor);
		Polinom fin=new Polinom(rezultat);
		System.out.println(fin.toString());
}
}