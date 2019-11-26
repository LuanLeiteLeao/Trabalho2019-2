package controle;

import java.util.ArrayList;
import java.util.List;



public class Test extends Gerenciador {

	@Override
	public List<String> listaPares() {
		List<String> pares = new ArrayList<String>();
		
		pares.add("A");
		pares.add("B");
		pares.add("C");
		pares.add("S");
		pares.add("E");
		pares.add("F");
		pares.add("G");
		pares.add("I");
		
		return pares;
	}


	public static void main(String[] args) {
		Test a = new Test();
//		for (String is : a.listaPares()) {
//			System.out.println(is);
//		}
	}
}
