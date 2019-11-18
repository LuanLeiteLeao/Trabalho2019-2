package controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Gerenciador  {
	
	
	List<int []> linColu= new ArrayList<int []>();
	
	List<AsciiArt> pares = new ArrayList<AsciiArt>(); 
	List<String> listaPares = new ArrayList<String>();
	Random gerador = new Random(10);
     
	public Gerenciador() {
		listaPares.add("A");
		listaPares.add("B");
		listaPares.add("C");
		listaPares.add("D");
		listaPares.add("d");
		listaPares.add("f");
		listaPares.add("g");
		listaPares.add("h");
		
		cargaArrey(linColu);
		embaralhar();
	}
	
	
	public String getAsciiArt(int linha ,int coluna) {
		/* essa funcao e usada pela visao
		   a visao manda as cordenadas da matriz de botoes dela
		   dai, essa funcao vai ver em qual Ascii arte estar essa cordenada,
		   no final ele tem que achar alguma coisa e retornar para a minha visao	
		*/
		String encontrado = null;
		
	
		
		for(AsciiArt a :pares) {
		
			int [] pos1=a.getPosi1();
			int [] pos2 =a.getPosi2();
			  
			System.out.println(pos1[0]+" "+pos1[1]);
			System.out.println(pos2[0]+" "+pos2[1]);
			
			if((pos1[0]== linha && pos1[1]== coluna ) || (pos2[0]== linha && pos2[1]== coluna ) ) {
				 
				encontrado = a.getAsciiart();
				break;
			}
		
		}
	
		return encontrado;
	}
	
	
	private void embaralhar() {
		/*essa funcao cria as cordenadas de cada
		 * AsciiArt, ela pega a lsita de letras, cada letra pode estar em 2 lugares na minha matriz 
		*/
		int aux=0;
		int cont=0;
				
		for(String i:listaPares) {
		
				
				
				pares.add(new AsciiArt(linColu.get(aux),linColu.get(aux+1),i));
				aux+=2;
			
		}
		
		
	}
	
	
	private void cargaArrey(List<int []> linColu) {
		//essa fucao tem o papel de criar a matriz q serar preenchidan pela funcao embaralha
		
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
			
				int[] aux = {i,j};
						
				linColu.add(aux);
		
			}

		}

//		
//		System.out.println("\n\n\n\n\n\n");	
//		
//		for(int[] i:linColu) {
//
//			System.out.println(i[0]+" "+i[1]);	
//		
//		}	
		

		//essa fucao embaralha as posicoes da minha amtriz 
		Collections.shuffle(linColu);
		
		
//		System.out.println("\n\n\n\n\n\n");
//		
//		
//		for(int[] i:linColu) {
//
//			System.out.println(i[0]+" "+i[1]);	
//		
//		}	
//
//		
//		System.out.println("\n\n\n\n\n\n");
	
	}
	
	
	public boolean validar(int[] peca1, int[] peca2) {
		/* essa funcao e usada pela visao
		   a visao manda as cordenadas das dois posicoes matriz de botoes dela
		   dai, essa funcao vai se essa cordenada são pares ou nao,
		   no final ele tem que achar alguma coisa e retornar para a minha visao	
		*/
		boolean encontrado = false;
		
	
		
		for(AsciiArt a :pares) {
		
			int [] pos1=a.getPosi1();
			int [] pos2 =a.getPosi2();
			  
			
			if((pos1[0]== peca1[0] && pos1[1]== peca1[1] ) && (pos2[0]== peca2[0] && pos2[1]== peca2[1] ) ) {
				 
				encontrado = true;
				break;
			}
		
		}
	
		return encontrado;
	}
	
	
}
