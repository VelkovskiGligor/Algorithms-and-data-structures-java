package zadaca2;

import java.util.Scanner;

class Zadaca{
	private String opis;
	private	int br_casovi;
	private boolean status;
	public Zadaca() {}
	
	public Zadaca(String opis, int br_casovi, boolean status) {
		this.opis = opis;
		this.br_casovi = br_casovi;
		this.status = status;
	}



	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getBr_casovi() {
		return br_casovi;
	}
	public void setBr_casovi(int br_casovi) {
		this.br_casovi = br_casovi;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Zadaca [opis=" + opis + ", br_casovi=" + br_casovi + ", status=" + status + "]";
	}
	
	
}

class Vraboten{
	private String ime;
	private String prezime;
	public static double bod=50;
	private double plata;
	private int staz;
	private int bodovi;
	private Zadaca [] zadaci;
	private int br_zadaci;
	public Vraboten() {
		this.zadaci=new Zadaca[10];
		this.br_zadaci=0;
	}
	public Vraboten(String ime, String prezime, int staz,int bodovi) {
		super();
		this.ime = ime;
		this.bodovi=bodovi;
		this.prezime = prezime;
		this.staz = staz;
		this.plata=bodovi*bod;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public static double getBod() {
		return bod;
	}
	public static void setBod(double bod) {
		Vraboten.bod = bod;
	}
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
	public int getStaz() {
		return staz;
	}
	public void setStaz(int staz) {
		this.staz = staz;
	}
	public int getBodovi() {
		return bodovi;
	}
	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}
	public Zadaca[] getZadaci() {
		return zadaci;
	}
	public void setZadaci(Zadaca[] zadaci) {
		this.zadaci = zadaci;
	}
	public int getBr_zadaci() {
		return br_zadaci;
	}
	public void setBr_zadaci(int br_zadaci) {
		this.br_zadaci = br_zadaci;
	}
	public void dodajnovazadaca(Zadaca zadaca) {
		if(this.br_zadaci==10) {
			System.out.println("Veke ima 10 zadaci i nemoze da se dodade nova zadaca");
		}else {
			zadaci[br_zadaci++]=zadaca;
		}
	}
	public  int vk_casovi() {
		int zbir=0;
		for(int i=0;i<this.br_zadaci;i++) {
			zbir+=zadaci[i].getBr_casovi();
		}
		return zbir;
	}
	public float procentZavrseni() {
		int br_zavrseni=0;
		for (int i=0;i<this.br_zadaci;i++) {
			if(this.zadaci[i].isStatus()) {
				br_zavrseni++;
			}
		}
		return br_zavrseni/this.br_zadaci;
	
	}
	@Override
	public String toString() {
		return "Vraboten ime=" + ime + ", prezime=" + prezime + " ";
	}
	
}
class Kompanija{
	private Vraboten []vraboteni;
	public Kompanija(Vraboten [] vrab) {
			this.vraboteni=vrab;
	}
	public Vraboten najAngaziran() {
		int max=0;
		int index = 0;
		for (int i=0;i<this.vraboteni.length;i++) {
					if(vraboteni[i].vk_casovi()>max) {
						max=vraboteni[i].vk_casovi();
						index=i;
					}
		
			}
		return vraboteni[index];
	}
	public void pecatiPoUspesnost() {
		boolean flag=true;
		while(flag) {
			flag=false;
			for (int j=0;j<vraboteni.length-1;j++) {
				if(vraboteni[j].procentZavrseni()<vraboteni[j+1].procentZavrseni()) {
					Vraboten pom=vraboteni[j];
					vraboteni[j]=vraboteni[j+1];
					vraboteni[j+1]=pom;
					flag=true;
					
				}
			}
		}
		for (int i=0;i<vraboteni.length-1;i++) {
				System.out.printf("Vraboten: " + vraboteni[i].getIme() + " " +vraboteni[i].getPrezime() + " Uspesnost:%.2f\n",(vraboteni[i].procentZavrseni() * 100));

			}

	}
	public void  pecati() {
		for (int i=0;i<vraboteni.length;i++) {
			vraboteni[i].toString();
			
		}
	}
}

public class glavna {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		Vraboten []pom = new Vraboten[n];
		for(int i = 0;i<n;i++){
		Vraboten v = new Vraboten();
		v.setIme(input.next());
		v.setPrezime(input.next());
		v.setStaz(input.nextInt());
		v.setBodovi((input.nextInt()));
		pom[i] = v;
		
		int p = input.nextInt();
		for(int j = 0;j<p;j++){
		Zadaca z = new Zadaca();
		z.setBr_casovi((input.nextInt()));
		z.setOpis(input.next());
		z.setStatus(input.nextBoolean());
		v.dodajnovazadaca(z);
		}
		}
		Kompanija k = new Kompanija(pom);
		k.pecati();
		k.pecatiPoUspesnost();
		System.out.println("Najangaziran vraboten e: " +
		k.najAngaziran());
		}
			
	}


