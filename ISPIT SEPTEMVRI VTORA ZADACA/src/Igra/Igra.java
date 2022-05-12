package Igra;
import java.io.*;
//Расипана тастатура Задача 1 (0 / 0)
//Играме одредена игра, при што се наоѓаме на некоја мапа на координати (X, Y).

//Во одреден момент забележуваме дека ни се расипуваат копчињата за 
//лево-десно, така што ни е овозможено да одиме уште L места на лево,
//и R места на десно.

//Напишете програма, што ќе пресмета колку и кои позиции може да
//посетиме (вклучувајќи ја и моменталната позиција), доколку имаме дадена
//мапа со слободни позиции на движење (.) и огради (#) низ кои не може да поминеме.

//Влез:

//Се внесуваат два броја M, N, што ја означуваат големината на мапата.

//Се внесуваат два броја X, Y (од 1 до M, N соодветно), што ја означуваат 
//почетната позиција на играчот.

//Се внесуваат два броја L, R што означуваат број на потези што можат да 
//се направат на лево, десно од почетната позиција.

//Потоа следува самата мапа од слободни места (.) и огради (#).

//Излез:

//Еден број во нов ред, бројот на полиња што може да се посетат.

//Истата мапа, со посетените полиња обележани со +.

//Пример:
//4 5 // големина на мапа 
//3 2 // стартна позиција 
//1 2 // потези лево, десно соодветно 
// ..... 
// .###. 
// ...## 
// #....
// Излез:

// 10 
// +++.. 
// +###. 
// +++## 
// #+++. 
public class Igra {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
				int m=Integer.parseInt(input.readLine());
				int n=Integer.parseInt(input.readLine());
				int x=Integer.parseInt(input.readLine());//x kordinata
				int y=Integer.parseInt(input.readLine());//y kordinata
				int levo=Integer.parseInt(input.readLine());
				int desno=Integer.parseInt(input.readLine());
				char niza [] []=new char [m][n];
				for (int i=1;i<=m;i++) {
					String izraz=input.readLine();
					for(int j=1;j<=n;j++) {
						char znak=izraz.charAt(j);
						niza[i][j]=znak;
						
					}
				}
				niza[x][y]='+';
				for (int i=x;i<=m;i++) {
					for (int j=y;;) {
						if(j-1==0 || j> n) {
							break;
						}
							if( levo!=0 &&niza[i][j-1]!='#') {
								niza[i][j-1]='+';
								j=j-1;
							}
					}
				}
	}

}
