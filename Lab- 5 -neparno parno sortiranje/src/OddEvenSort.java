import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {
	
	static void oddEvenSort(int a[], int n)
	{
	 int brojneparni=izbroj(a,n);
	 boolean sort=false;
	 	for(int i=0;i<n;i++) {
	 		sort=false;
	 		for(int j=0;j<n-1;j++) {
	 			if(a[j]%2==0 && a[j+1]%2!=0) {
	 				sort=true;
	 				int tmp=a[j];
	 				a[j]=a[j+1];
	 				a[j+1]=tmp;
	 			}
	 		}
	 		if(!sort)break;
	 	}
	 	for(int i=0;i<brojneparni;i++) {
	 		sort=false;
	 		for(int j=0;j<brojneparni-1;j++) {
	 			if(a[j]>a[j+1]) {
	 				sort=true;
	 				int tmp=a[j];
	 				a[j]=a[j+1];
	 				a[j+1]=tmp;
	 			}
	 		}
	 		if(!sort)break;
	 	}
	 	for(int i=brojneparni;i<n;i++) {
	 		sort=false;
	 		for(int j=brojneparni;j<n-1;j++) {
	 			sort=true;
	 			if(a[j]<a[j+1]) {
	 				int tmp=a[j];
	 				a[j]=a[j+1];
	 				a[j+1]=tmp;
	 			}
	 		}
	 		if(!sort)break;
	 	}
	 	
	}

	private static int izbroj(int[] a, int n) {
		int brojac=0;
		for (int i=0;i<n;i++) {
			if(a[i]%2!=0) {
				brojac++;
			}
		}
		return brojac;
	}

	public static void main(String[] args) throws IOException{
		int i;
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in)); 
		String s = stdin.readLine();
		int n = Integer.parseInt(s);
		
		s = stdin.readLine();
		String [] pom = s.split(" ");
		int [] a = new int[n];
		for(i=0;i<n;i++)
			a[i]=Integer.parseInt(pom[i]);
		oddEvenSort(a,n);
		for(i=0;i<n-1;i++)
			System.out.print(a[i]+" ");
		System.out.print(a[i]);
	}
}