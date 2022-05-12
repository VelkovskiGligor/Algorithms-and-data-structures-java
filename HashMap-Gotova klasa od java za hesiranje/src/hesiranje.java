import java.util.HashMap;

public class hesiranje {
	public static void main(String[] args) {
		HashMap<Character, Integer> hesh=new HashMap<Character,Integer>();
		String s="АБВГДЃЕЖЗЅИЈКЛЊМНЊОПРСТЌУФХЦЧЏШ";
		for(int i=0;i<s.length();i++) {
			hesh.put(s.charAt(i), i);
		}
		System.err.println(hesh);
		System.err.println(hesh.get(1));//vrakja null poso nema element so kluc 1
		System.err.println(hesh.get('Б'));// vrekaja vrednosta za kluc "Б'
		//containsKey(Object key) и containsValue(Objectvalue)
				//• враќа: true ако мапата содржи мапирање за дадениот клуч (
				//вредност)
		System.err.println(hesh.containsKey('Б'));//Proveruva dali ima element so kluc 'Б' vo hesot  vrakja true ili false
		System.err.println(hesh.containsKey('W'));
		System.err.println(hesh.containsValue(1));//Proveruva dali ima element sto ima vrednost 1 i vrakja true ili false
	}
}
