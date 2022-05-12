	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	
	public class ShakerSort {
		
		static void shakerSort(int a[], int n)
		{	int pom=n;
			int max,min;
			int pozicijamax,pozicijamin;
			boolean sort=false;
			for (int i=0;i<n;i++) {
				sort=false;
				min=a[i];
				pozicijamin=i;
				for(int j=i;j<pom;j++) {
					if(min>a[j]) {
						sort=true;
						min=a[j];
						pozicijamin=j;
					}
				}
				if(sort) {
				for(int x=pozicijamin;x!=i;x--) {
					int tmp=a[x];
					a[x]=a[x-1];
					a[x-1]=tmp;
				}
				System.out.println(printaj(a, n));
				}
				sort=false;
				max=a[i+1];
				pozicijamax=i+1;
				for(int j=i+1;j<pom;j++) {
					if(max<a[j]) {
						sort=true;
						max=a[j];
						pozicijamax=j;
					}
				}
				if(sort) {
				for(int x=pozicijamax;x<pom-1;x++) {
					int tmp=a[x];
					a[x]=a[x+1];
					a[x+1]=tmp;
				}	
				System.out.println(printaj(a, n));
				}
				pom--;
			
				if(i>pom ) {
					break;
				}
				
			}
				
			
		}
	
		public static String printaj(int[] a,int n) {
			String s="";
			for (int i=0;i<n;i++) {
				s=s+a[i]+" ";
			}
			return s;
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
			shakerSort(a,n);
		}
	}