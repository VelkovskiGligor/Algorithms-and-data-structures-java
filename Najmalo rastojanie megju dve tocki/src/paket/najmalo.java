package paket;
import java.io.*;
class Tocka{
	double x;
	double y;
	public Tocka(double x,double y) {
		this.x=x;
		this.y=y;
	}
}
public class najmalo {
	public double rastojanie(Tocka a,Tocka b) {
		double rast=Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
		return rast;
	}
	public double najmalorastojanie(Tocka [] tocki,int n) {
		double min=111111110;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				min=Math.min(min, rastojanie(tocki[i],tocki[j]));
			}
		}
		return min;
	}
	public static void main(String[] args) throws IOException {
		System.out.println("Vnesete broj na tocki");
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		String N=input.readLine();
		int n=Integer.parseInt(N);
		Tocka [] tocki=new Tocka [n];
		for (int i=0;i<n;i++) {
			double x=Double.parseDouble(input.readLine());
			double y=Double.parseDouble(input.readLine());
			tocki[i]=new Tocka(x,y);
		}
		/*for (int i=0;i<n;i++) {
			System.out.println(tocki[i].x +" A "+ tocki[i].y);
		}*/
		
	}

}
