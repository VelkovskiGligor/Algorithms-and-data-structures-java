package zbir;

public class zbir {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int zbir=0;
		for(int i=1;i<100;i++) {
			int deliteli=0;
			for(int j=1;j<=i/2;j++) {
				if(i%j==0){
					deliteli++;
					
				}
			}
			if(deliteli==1) {
				zbir+=i;
			}
		
		}
			System.out.println("Zbirot e: "+zbir);
	}

}
