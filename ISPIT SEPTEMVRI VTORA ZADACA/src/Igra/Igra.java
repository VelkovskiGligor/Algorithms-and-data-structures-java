package Igra;
import java.io.*;
//�������� ��������� ������ 1 (0 / 0)
//������ �������� ����, ��� ��� �� ������ �� ����� ���� �� ���������� (X, Y).

//�� ������� ������ ������������ ���� �� �� ���������� �������� �� 
//����-�����, ���� ��� �� � ���������� �� ����� ���� L ����� �� ����,
//� R ����� �� �����.

//�������� ��������, ��� �� �������� ����� � ��� ������� ���� ��
//�������� (�������༝� �� � ������������ �������), ������� ����� ������
//���� �� �������� ������� �� ������ (.) � ������ (#) ��� ��� �� ���� �� ��������.

//����:

//�� ��������� ��� ���� M, N, ��� �� ���������� ���������� �� ������.

//�� ��������� ��� ���� X, Y (�� 1 �� M, N ���������), ��� �� ���������� 
//��������� ������� �� �������.

//�� ��������� ��� ���� L, R ��� ���������� ��� �� ������ ��� ����� �� 
//�� �������� �� ����, ����� �� ��������� �������.

//����� ������� ������ ���� �� �������� ����� (.) � ������ (#).

//�����:

//���� ��� �� ��� ���, ����� �� ����� ��� ���� �� �� �������.

//������ ����, �� ���������� ����� ��������� �� +.

//������:
//4 5 // �������� �� ���� 
//3 2 // ������� ������� 
//1 2 // ������ ����, ����� ��������� 
// ..... 
// .###. 
// ...## 
// #....
// �����:

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
