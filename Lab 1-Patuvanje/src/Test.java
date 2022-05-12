import java.util.Scanner;



 abstract class Patuvanje{
	private String agencija;
	private int cena;
	
	public Patuvanje(String agencija, int cena) {
		super();
		this.agencija = agencija;
		this.cena = cena;
	}
	public String getAgencija() {
		return agencija;
	}
	public void setAgencija(String agencija) {
		this.agencija = agencija;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public abstract int vratiVremeVoDenovi();
	
}
class PraznicnoPatuvanje extends Patuvanje{
	private int poceten_datum;
	private int poceten_mesec;
	private int kraen_datum;
	private int kraen_mesec;
	
	
	
	public PraznicnoPatuvanje(String agencija, int cena, int poceten_datum, int poceten_mesec, int kraen_datum,
			int kraen_mesec) {
		super(agencija, cena);
		try {
			if(poceten_mesec>kraen_mesec) {
				int pom=poceten_mesec;
				poceten_mesec=kraen_mesec;
				kraen_mesec=pom;
				 ThrowErrorManager ("Iskluchok");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			int pom=poceten_mesec;
			poceten_mesec=kraen_mesec;
			kraen_mesec=pom;
			
		}
		this.poceten_datum = poceten_datum;
		this.poceten_mesec = poceten_mesec;
		this.kraen_datum = kraen_datum;
		this.kraen_mesec = kraen_mesec;
	}



	private void ThrowErrorManager(String string) {
		System.out.println(string);
		
	}



	public int getPoceten_datum() {
		return poceten_datum;
	}
	public void setPoceten_datum(int poceten_datum) {
		this.poceten_datum = poceten_datum;
	}
	public int getPoceten_mesec() {
		return poceten_mesec;
	}
	public void setPoceten_mesec(int poceten_mesec) {
		this.poceten_mesec = poceten_mesec;
	}
	public int getKraen_datum() {
		return kraen_datum;
	}
	public void setKraen_datum(int kraen_datum) {
		this.kraen_datum = kraen_datum;
	}
	public int getKraen_mesec() {
		return kraen_mesec;
	}
	public void setKraen_mesec(int kraen_mesec) {
		this.kraen_mesec = kraen_mesec;
	}



	@Override
	public int vratiVremeVoDenovi() {
		int brmesec=kraen_mesec-poceten_mesec;
		if(brmesec==0) {
			return kraen_datum-poceten_datum;
		}else {
			
			if(kraen_datum-poceten_datum>=0) {
				return kraen_datum-poceten_datum+(30*brmesec);
			}else {
				int raz=kraen_datum-poceten_datum;
				raz=raz*-1;
				return (30*brmesec)-raz;
			}
			
		}
	}
	
}
class GodishenOdmor extends Patuvanje{
	private int denovi;
	
	
	
	public GodishenOdmor(String agencija, int cena, int denovi) {
		super(agencija, cena-1000);
		this.denovi = denovi;
	}
	
	
	public int getDenovi() {
		return denovi;
	}
	public void setDenovi(int denovi) {
		this.denovi = denovi;
	}




	@Override
	public int vratiVremeVoDenovi() {
		return this.denovi;
	}


	@Override
	public String toString() {
		return "GodishenOdmor [denovi=" + denovi + "]";
	}
	
}

public class Test {

	public static int vratiMinCena(Patuvanje [] niza, int n, GodishenOdmor zaSporedba) {
				int min=99999;
			for(int i=0;i<n;i++) {
				if(zaSporedba.vratiVremeVoDenovi()<=niza[i].vratiVremeVoDenovi()) {
					if(niza[i].getCena()<=min) {
						min=niza[i].getCena();
					}
				}
			}
		
		return min;
	}
	public static void main(String[] args) {
		
		
		int n;
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		
		Patuvanje nizaPatuvanje[]=new Patuvanje[n];
		
		for (int i=0;i<n;i++){
			int tip=in.nextInt();
			if (tip==0){
				String ime=in.next();
				int cena =in.nextInt();
				int vreme=in.nextInt();
				nizaPatuvanje[i]=new GodishenOdmor(ime,cena,vreme);
			}
			else {
				String ime=in.next();
				int cena =in.nextInt();
				int pocD=in.nextInt();
                int pocM=in.nextInt();
				int krajD=in.nextInt();
				int krajM=in.nextInt();
				nizaPatuvanje[i]=new PraznicnoPatuvanje(ime,cena,pocD,pocM, krajD,krajM);
				
			}
		}
		
		//решение на барање 1
		for (int i=0;i<n;i++){
			if(nizaPatuvanje[i] instanceof PraznicnoPatuvanje) {
				
				PraznicnoPatuvanje pom=(PraznicnoPatuvanje) nizaPatuvanje[i];
				if(pom.getPoceten_mesec()==6) {
					System.out.print(pom.getAgencija()+" ");
				}
			}
}	
			System.out.println("");
		
        //решение на барање 2
		int sum=0;
		for (int i=0;i<n;i++){
					sum+=nizaPatuvanje[i].vratiVremeVoDenovi();
		}	
		double prosek=(double)sum/n;
		System.out.println(prosek);
		
	
		
        //решение на барање 3   
		GodishenOdmor odmor = null;
		for (int i=0;i<n;i++){
			if(nizaPatuvanje[i] instanceof GodishenOdmor) {
				 odmor= (GodishenOdmor)nizaPatuvanje[i];
				break;
			}
		}
		//решение на барање 4

			System.out.println(vratiMinCena(nizaPatuvanje,n,odmor));
	
		

	}

}
