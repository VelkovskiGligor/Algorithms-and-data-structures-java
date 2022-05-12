import java.util.Scanner;

public class Newyear {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int N=s.nextInt();
		System.err.println(N);
		elka(N);

	}

	public static void elka(int n) {
		int brojac=1;
			for(int i=0;i<n/2;i++) {
				for(int j=1;j<n;j++) {
					if(j>n/2-brojac && j<n/2+brojac) {
						System.err.print("*");
					}else {
						System.err.print(" ");
					}
								
				}
				System.err.print("\n");
				brojac++;
			}
		
	}

}
