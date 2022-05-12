package tabla;

public class tabla {
	public static boolean dali_se_napagjaat(int i,int j,int x,int y) {
		if(i==x) {
			return true;
		}
		if(j==y) {
			return true;
		}
		if( Math.abs(i-x) == Math.abs(j-y)) {
			return true;
		}
		return false;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		int brojac=0;
		for(int i1=0;i1<8;i1++) {
			for(int j1=0;j1<8;j1++) {
				for(int i2=0;i2<8;i2++) {
					for(int j2=0;j2<8;j2++) {
							if(!dali_se_napagjaat(i1,j1,i2,j2)) {
								brojac++;
							}
					}
				}
				
			}
		}
			System.out.println(brojac);
	}

}
