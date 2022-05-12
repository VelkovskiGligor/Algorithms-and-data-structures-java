	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	
	public class ZigZagSequence {
	    
	    public static int najdiNajdolgaCikCak(int a[]) {
	    	int max=0;
	    	int brojac=1;
	    	boolean positive=false;
	    	if(a[0]>=0) {
	    		positive=true;
	    	}
	    	for(int i=0;i<a.length-1;i++) {
	    		if(positive) {
	    			if(a[i+1]<0) {
	    				brojac++;
	    				positive=false;
	    			}else if( i< a.length-2 && a[i+1]==0){
	    				if(brojac>max) {
	    				max=brojac;
	    				}
	    				brojac=1;
	    				i++;
	    				if(a[i+1]>0) {
	    					positive=true;
	    				}else {
	    					positive=false;
	    				}
	    				}
	    				else {
	    			
	    					positive=true;
	        				if(brojac>max) {
	        					max=brojac;
	        				}
	    				brojac=1;
	    				
	    			}
	    		}else if(!positive) {
	    			if(a[i+1]>0) {
	    				brojac++;
	    				positive=true;
	    				
	    			}
	    			else if(i<a.length-2 && a[i+1]==0){    
	    				if(brojac>max) {
	    					max=brojac;
	    				}
	    				brojac=1;
	    				i++;
	    				if(a[i+1]>0) {
	    					positive=true;
	    				}else {
	    					positive=false;
	    				}
	    				
	    			}
	    				else {
	    			
	    				positive=false;
	    				if(brojac>max) {
	    					max=brojac;
	    				}
	    				brojac=1;
	    			}
	    		}
	    		
	    		
	    		if(brojac>max) {
					max=brojac;
				}
	    	
	    	}
	    	if(brojac>max) {
				max=brojac;
			}
	    	return max;
		}
	    
	    public static void main(String[] args) throws Exception {
	        int i,j,k;
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int N = Integer.parseInt(br.readLine());
	        int a[] = new int[N];
	        for (i=0;i<N;i++)
	            a[i] = Integer.parseInt(br.readLine());
	        
	        int rez = najdiNajdolgaCikCak(a);
	        System.out.println(rez);
	        
	        br.close();
	       	
	    }
	    
	}
