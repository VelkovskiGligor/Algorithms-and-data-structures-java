import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {
    public static int Z=0;
    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
    	Z++;
    	if(l == r || l >r) {
    		
    		return 0;
    	}
    	
    		if( c[l] == '+' ) {
    			String pom1="";
    	    	String pom2="";
    			pom1+=c[l-1];
    			pom2+=c[l+1];
    			if( c[l+1] == '(' ) {
    				return  presmetaj(c,l+3,r);
    			}else {
    				int a=Integer.parseInt(pom1);
        			int b=Integer.parseInt(pom2);
        			
        			
        			return a+b + presmetaj(c,l+3,r);
    			}
    			
    		}else if( c[l] == '-' ) {
    			String pom1="";	
    	    	String pom2="";
    			pom1+=c[l-1];
    			pom2+=c[l+1];
    			if( c[l+1] == '(' ) {
    				return  -1* presmetaj(c,l+3,r);
    			}else {
    				int a=Integer.parseInt(pom1);
        			int b=Integer.parseInt(pom2);
        			//return a-b - presmetaj(c,l+3,r);// poslednite dve rabotat drugi tri ne rabotat
        			return a-b + presmetaj(c,l+3,r);// poslednite dva ne rabotat
    			}
    	
    		}else {
    			return presmetaj(c,l+1,r);
    		}
    	
    }
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String expression = br.readLine();
        char exp[] = expression.toCharArray();
        
       
        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);
      //  System.err.println(Z);
        br.close();
        
    }
    
}
