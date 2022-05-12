package ok;

import java.lang.reflect.Array;
import java.util.Scanner;


public class LDS {
	

	private static int najdolgaOpagackaSekvenca(int[] a) {
        int max=1;
        int brojac=1;
        int [] b=new int[a.length];
       int x=0;
		for(int i=0;i<a.length-1;i++) {
			for(int j=i;j<a.length;j++) {
				if(a[i]>a[j]) {
					b[x]=a[j];
					x++;
				}
			}
			brojac=1;
			for(int c=1;c<x;c++) {
				if(b[0]>=b[c]) {
					if(b[0]>b[c]) {
						brojac++;
					}else {
						if(brojac>max) {
							max=brojac;
						}
						brojac=1;
					}
					if(brojac>max) {
						max=brojac;
					}
				
				}
			}
			x=0;
		}
        // Vasiot kod tuka
        return max;
        
	}
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = stdin.nextInt();
		}
		System.out.println(najdolgaOpagackaSekvenca(a));
	}


}
