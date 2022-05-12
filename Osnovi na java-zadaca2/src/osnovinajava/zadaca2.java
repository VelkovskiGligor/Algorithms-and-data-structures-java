package osnovinajava;

import java.util.Arrays;


class zadaca{
	private String opis;
	private int brcasovi;
	private boolean status;
	public zadaca() {}
	public zadaca(String opis, int brcasovi, boolean status) {
		super();
		this.opis = opis;
		this.brcasovi = brcasovi;
		this.status = status;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getBrcasovi() {
		return brcasovi;
	}
	public void setBrcasovi(int brcasovi) {
		this.brcasovi = brcasovi;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "opis=" + opis + ", brcasovi=" + brcasovi + ", status=" + status + "";
	}
	
	
	
}
	
class Vraboten{
	private String ime;
	private String prezime;
	public static double bod=50;
	private double plata;
	private int staz;
	private int brojbodovi;
	private zadaca [] zadaci;
	private int brojzadaci;
	public Vraboten() {
		this.brojzadaci=0;
		this.zadaci=new zadaca[10];
	}
	public Vraboten(String ime, String prezime, int staz, int brojbodovi) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.staz = staz;
		this.brojbodovi = brojbodovi;
		this.plata=brojbodovi * Vraboten.bod;
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
	public int getBrojbodovi() {
		return brojbodovi;
	}
	public void setBrojbodovi(int brojbodovi) {
		this.brojbodovi = brojbodovi;
	}
	public zadaca[] getZadaci() {
		return zadaci;
	}
	public void setZadaci(zadaca[] zadaci) {
		this.zadaci = zadaci;
	}
	public int getBrojzadaci() {
		return brojzadaci;
	}
	public void setBrojzadaci(int brojzadaci) {
		this.brojzadaci = brojzadaci;
	}
	@Override
	public String toString() {
		return "Vraboten ime=" + ime + ", prezime=" + prezime + ", plata=" + plata + ", staz=" + staz + ", brojbodovi="
				+ brojbodovi + ", zadaci=" + Arrays.toString(zadaci) + ", brojzadaci=" + brojzadaci + "";
	}
	public void dodadizadaca(zadaca zadaca) {
		if(zadaci.length==10) {
			ThrowErrorManager("Error");
		}else {
			zadaci[this.brojzadaci]=zadaca;
			this.brojzadaci++;
		}
		
	}
	private void ThrowErrorManager(String string) {
		System.err.println("Kapacitetot na zadaci e potpolnet");
		
	}
	public int zbirCasovi() {

		int sum=0;
		for (zadaca zadaca:zadaci) {
			sum=zadaca.getBrcasovi();
		}
		return sum;
	}
	public double procentzavrseni() {
		int zavrseni=0;
		for (int i=0;i<this.brojzadaci;i++) {
			if(zadaci[i].isStatus()) {
				zavrseni++;
			}
		}
		return (float)zavrseni/this.brojzadaci*100;
	}
	
}
class Kompanija{
	private Vraboten vraboteni [];
	public Kompanija(Vraboten [] vraboteni) {
		this.vraboteni=vraboteni;
	}
	public Vraboten najangaziran() {
		int index=0;
		int max=0;
		for(int i=0;i<vraboteni.length;i++){
			int vk=vraboteni[i].zbirCasovi();
			if(vk>max) {
				max=vk;
				index=i;
			}
			
		}
		return vraboteni[index];
	}
	public void pecatipouspesnost() {
		for (int i=0;i<vraboteni.length;i++) {
			for (int j=0;j<vraboteni.length-1-i;j++) {
				if(vraboteni[j].procentzavrseni()<vraboteni[j+1].procentzavrseni()) {
					Vraboten temp=vraboteni[j];
					vraboteni[j]=vraboteni[j+1];
					vraboteni[j+1]=temp;
				}
				
			}
		}
		for (Vraboten vraboten:vraboteni) {
			vraboten.toString();
		}
		
	}
	@Override
	public String toString() {
		return "Kompanija vraboteni=" + Arrays.toString(vraboteni) + "";
	}
	
}
public class zadaca2 {
	
	public static void main(String[] args) {


	}

}
