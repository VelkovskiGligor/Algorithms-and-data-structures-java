import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class SLLNode {
	protected int id;
	protected int plata;
	protected SLLNode succ;

	public SLLNode(int id,int plata, SLLNode succ) {
		this.id = id;
		this.plata=plata;
		this.succ = succ;
	}

	
}

class SLL {
	private SLLNode first;

	public SLL() {
		// Construct an empty SLL
		this.first = null;
	}

	public void deleteList() {
		first = null;
	}

	public int length() {
		int ret;
		if (first != null) {
			SLLNode tmp = first;
			ret = 1;
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret++;
			}
			return ret;
		} else
			return 0;

	}


	public void insertFirst(int id, int plata) {
		SLLNode ins = new SLLNode(id,plata, first);
		first = ins;
	}

	public void insertLast(int id,int plata) {
		if (first != null) {
			SLLNode tmp = first;
			while (tmp.succ != null)
				tmp = tmp.succ;
			SLLNode ins = new SLLNode(id, plata, null);
			tmp.succ = ins;
		} else {
			insertFirst(id,plata);
		}
	}

	public SLLNode getFirst() {
		return first;
	}
	
	
	public SLL brisi_pomali_od(int iznos) {
				SLLNode pred=this.first;
				SLLNode dvizi=pred;
				
				while(dvizi!=null ) {
					
					if(dvizi==this.first) {
							if(pred.plata<iznos) {
								pred=pred.succ;
								this.first=pred;
								dvizi=pred;
							}else {
								dvizi=pred.succ;
							}	
					}else {
						if(dvizi.plata<iznos) {
							dvizi=dvizi.succ;
							pred.succ=dvizi;
						}else {
							dvizi=dvizi.succ;
							pred=pred.succ;
						}
						
					}
				
				}
				if(this.first!=null) {
					return this;
				}else {
					System.out.println("nema");
					return this;
				}
				
				
	}
   
	public SLL sortiraj_opagacki() {
		SLLNode pred1=null;
    	SLLNode pred2=null;
    	SLLNode dvizi=null;
    	
    	for(int i=0;i<length();i++) {
    		 pred1=this.first;
        	 pred2=null;
        	 dvizi=null;
    		if(pred1== null) {
        		return this;
        	}
        	dvizi=pred1.succ;    
        	if(dvizi == null) {
        		return this;
        	}else {
        		if(dvizi.id > pred1.id) {
    				SLLNode tmp=dvizi.succ;
    				this.first=dvizi;
    				dvizi.succ=pred1;
    				pred1.succ=tmp;
    				pred2=dvizi;
    				dvizi=tmp;
    			}else {
    				pred2=pred1;
    				pred1=dvizi;
    				if(dvizi!=null) {
    					dvizi=dvizi.succ;
    				}
    			}
        	}	
    		while(dvizi!=null) {
        		
        		if(dvizi.id > pred1.id) {
    				SLLNode temp=dvizi.succ;
    				pred2.succ=dvizi;
    				dvizi.succ=pred1;
    				pred1.succ=temp;
    				pred2=dvizi;
    				dvizi=temp;
    			}else {
    				pred2=pred1;
    				pred1=dvizi;
    				if(dvizi!=null) {
    					dvizi=dvizi.succ;
    				}
    				
    			}
        		
        	}
    	}
    	
		

    	return this;
	}
    public void pecati (SLL lista)
    {
    	SLLNode p=lista.first;
    	while(p!=null)
    	{
    		System.out.println(p.id+" "+p.plata);
    		p=p.succ;
    	}
    }
	
}
public class SLLKompanija {
	public static void main(String[] args) throws IOException {

		SLL lista1 = new SLL();
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		
		for (int i = 0; i < N; i++) {
			s=stdin.readLine();
			String s1=stdin.readLine();
			lista1.insertLast(Integer.parseInt(s),Integer.parseInt(s1));
		}
		s = stdin.readLine();
		
		lista1=lista1.brisi_pomali_od(Integer.parseInt(s));
		if(lista1!=null)
        {
		    lista1=lista1.sortiraj_opagacki();
		    lista1.pecati(lista1);
        }
		
	}
}
