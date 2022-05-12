import java.util.Hashtable;

public class hesiranje {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<Character,Integer> hesh=new Hashtable <Character,Integer>();
		String s="АБВГДЃЕЖЗЅИЈКЛЊМНЊОПРСТЌУФХЦЧЏШ";
		for(int i=0;i<s.length();i++) {
			hesh.put(s.charAt(i), i);//key e rednata bukva a i e vrednosta
		}
		System.err.println(hesh);
		System.err.println("Kofickata vo koja se naogja J e "+ hesh.get('Ј'));
		//containsKey(Object key) и containsValue(Object value)
		//• враќа: true ако мапата содржи мапирање за дадениот клуч ( вредност)
        System.err.println(hesh.containsValue(1));//Prebaruva dali iam element so vrednost 1
        System.err.println(hesh.containsValue(51));// Prebaruva dali ima element so vrednost 51
        System.err.println(hesh.containsKey('Ј'));//Prebaruva dali ima element sto ima kluc 'J'
	}

}
