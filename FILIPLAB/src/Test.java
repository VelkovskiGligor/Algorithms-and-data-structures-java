import java.util.Scanner;
class MyException extends Exception{
    MyException(){}
    MyException(String s){
        System.out.println(s);
    }
}
class Patuvanje{
    private String agencija;
    private int cena;
    public Patuvanje(){}
    public Patuvanje(String agencija, int cena){
        this.agencija=agencija;
        this.cena=cena;
    }
    public static int vratiMinCena(Patuvanje niza[], int n, Patuvanje zaSporedba) {
        int min=0,flag=1;
        for(int i=0;i< niza.length;i++){
            if(niza[i].vratiVremeVoDenovi()> zaSporedba.vratiVremeVoDenovi()){
                if(flag==1){
                    min=niza[i].cena;
                    flag=0;
                }
                if(niza[i].cena<min){
                    min=niza[i].cena;
                }
            }
        }
        return min;
    }
    public int getCena() {
        return cena;
    }
    public String getAgencija() {
        return agencija;
    }
    public void setAgencija(String agencija) {
        this.agencija = agencija;
    }
    public void setCena(int cena) {
        this.cena = cena;
    }
    int vratiVremeVoDenovi(){
        return 0;
    }
}
class PraznicnoPatuvanje extends Patuvanje{
    int pocetenDatum;
    int pocetenMesec;
    int kraenDatum;
    int kraenMesec;
    public PraznicnoPatuvanje(){}
    public PraznicnoPatuvanje(String agencija, int cena, int pocetenDatum,int pocetenMesec,int kraenDatum, int kraenMesec){
        super(agencija, cena);
        this.pocetenDatum=pocetenDatum;
        this.pocetenMesec=pocetenMesec;
        this.kraenDatum=kraenDatum;
        this.kraenMesec=kraenMesec;
        try{
            if(pocetenMesec>kraenMesec){
            	 this.pocetenDatum=pocetenDatum;
                 this.pocetenMesec=pocetenMesec;
                 this.kraenDatum=kraenDatum;
                 this.kraenMesec=kraenMesec;		
                throw new MyException("Iskluchok");
            }
        }
        catch (MyException e){
            this.pocetenDatum=pocetenDatum;
            this.pocetenMesec=pocetenMesec;
            this.kraenDatum=kraenDatum;
            this.kraenMesec=kraenMesec;
        }
    }
    public int getKraenDatum() {
        return kraenDatum;
    }
    public int getKraenMesec() {
        return kraenMesec;
    }
    public int getPocetenDatum() {
        return pocetenDatum;
    }
    public int getPocetenMesec() {
        return pocetenMesec;
    }
    public void setKraenDatum(int kraenDatum) {
        this.kraenDatum = kraenDatum;
    }
    public void setKraenMesec(int kraenMesec) {
        this.kraenMesec = kraenMesec;
    }
    public void setPocetenMesec(int pocetenMesec) {
        this.pocetenMesec = pocetenMesec;
    }
    public void setPocetenDatum(int pocetenDatum) {
        this.pocetenDatum = pocetenDatum;
    }
    @Override
    int vratiVremeVoDenovi() {
        if(pocetenMesec==kraenMesec){
            return kraenDatum-pocetenDatum;
        }
        else{
            return 30-pocetenDatum+kraenDatum;
        }
    }
}
class GodishenOdmor extends Patuvanje{
    int vremetraenje;
    public GodishenOdmor(){}
    public GodishenOdmor(String agencija,int cena,int vremetraenje){
        super(agencija, cena-1000);
        this.vremetraenje=vremetraenje;
    }
    public int getVremetraenje() {
        return vremetraenje;
    }
    public void setVremetraenje(int vremetraenje) {
        this.vremetraenje = vremetraenje;
    }
    @Override
    int vratiVremeVoDenovi() {
        return this.vremetraenje;
    }
}
public class Test{
    public static void main(String[] args) {
        int n;
        Scanner input= new Scanner(System.in);
        n=input.nextInt();
        Patuvanje nizaPatuvanje[] = new Patuvanje[n];
        for(int i=0;i<n;i++){
            int tip=input.nextInt();
            if(tip==1){
                String ime=input.next();
                int cena=input.nextInt();
                int pocetenDatum=input.nextInt();
                int pocetenMesec=input.nextInt();
                int kraenDatum= input.nextInt();
                int kraenMesec= input.nextInt();
                nizaPatuvanje[i]=new PraznicnoPatuvanje(ime,cena,pocetenDatum,pocetenMesec,kraenDatum,kraenMesec);
            }
            else{
                String ime=input.next();//mora da e next zasho e u cela linija napishano i ako pishesh nextline kje nema da ima nishto u cena i vreme
                int cena=input.nextInt();
                int vreme=input.nextInt();
                nizaPatuvanje[i]=new GodishenOdmor(ime,cena,vreme);
            }
        }
        for(int i=0;i<n;i++){
            if(nizaPatuvanje[i] instanceof PraznicnoPatuvanje){
                if(((PraznicnoPatuvanje)nizaPatuvanje[i]).getPocetenMesec()==6){
                    System.out.print(nizaPatuvanje[i].getAgencija() + " ");
                }
            }
        }
        System.out.println();

        int suma=0;
        for(int i=0;i<n;i++){
            suma+=nizaPatuvanje[i].vratiVremeVoDenovi();
        }
        System.out.println((double)suma/n);
        String agencija=input.next();
        int cena=input.nextInt();
        int vremetraenje=input.nextInt();
        GodishenOdmor odmor=new GodishenOdmor(agencija,cena,vremetraenje);
        int mincena;
        mincena= Patuvanje.vratiMinCena(nizaPatuvanje,n,odmor);
        System.out.println(mincena);
    }
}