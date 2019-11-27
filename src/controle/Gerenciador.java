package controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

abstract class Gerenciador  {
	public static final int DIM4x4=4;
	private List<int []> linColu= new ArrayList<int []>();
	private List<AsciiArt> pares = new ArrayList<AsciiArt>(); 
	private List<int[]> paresValidar = new ArrayList<int[]>();
    private int contador=0; 
	public Gerenciador() {
		//validar se a lista tem 8 letras
		validarListaPares();
		//carrega a minha matriz
		cargaArrey(linColu);
		//atribui a essa matriz as letras e em seguida embaralha 
		embaralhar();
	}
	
	
	/**
	 * @return Este metodo espera Letras, para preecher a matrix
	 */
	public abstract List<String> listaPares();
			
	public String getAsciiArt(int linha ,int coluna) {
		/* essa funcao e usada pela visao
		   a visao manda as cordenadas da matriz de botoes dela
		   dai, essa funcao vai ver em qual Ascii arte estar essa cordenada,
		   no final ele tem que achar alguma coisa e retornar para a minha visao	
		*/
		String encontrado = null;
		int posicoes[]= {linha,coluna};
				
		
		
		for(AsciiArt a :pares) {
		
			int [] pos1=a.getPosi1();
			int [] pos2 =a.getPosi2();
			  
			System.out.println(pos1[0]+" "+pos1[1]);
			System.out.println(pos2[0]+" "+pos2[1]);
			
			if((pos1[0]== linha && pos1[1]== coluna ) || (pos2[0]== linha && pos2[1]== coluna ) ) {
				 encontrado = a.getAsciiart();
				 paresValidar.add(posicoes);
				 this.contador+=1;
				break;
			}
		}
	
		if(this.contador == 2) {
			this.contador=0;
			this.validar();
		}
		
		return encontrado;
	}
	
	
	private void embaralhar() {
		/*essa funcao cria as cordenadas de cada
		 * AsciiArt, ela pega a lsita de letras, cada letra pode estar em 2 lugares na minha matriz 
		*/
		int aux=0;
		int cont=0;
				
		for(String i:listaPares()) {
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
		//essa fucao embaralha as posicoes da minha amtriz 
		Collections.shuffle(linColu);
	}
	
	
	
	
	public boolean validar() {
		/* essa funcao e usada pela visao
		   a visao manda as cordenadas das dois posicoes matriz de botoes dela
		   dai, essa funcao vai se essa cordenada são pares ou nao,
		   no final ele tem que achar alguma coisa e retornar para a minha visao	
		*/
		boolean encontrado = false;
		for(AsciiArt a :pares) {
		
			int [] pos1=a.getPosi1();
			int [] pos2 =a.getPosi2();
			  
			
			if(((pos1[0]== paresValidar.get(0)[0] && pos1[1]== paresValidar.get(0)[1] ) && (pos2[0]== paresValidar.get(1)[0] && pos2[1]== paresValidar.get(1)[1] )) 
				|| ((pos2[0]== paresValidar.get(0)[0] && pos2[1]== paresValidar.get(0)[1] ) && (pos1[0]== paresValidar.get(1)[0] && pos1[1]== paresValidar.get(1)[1] ))	) {
				 
				encontrado = true;
				System.out.println("ACHEIIIIIIII");
				
			}
			else {
				System.out.println("Errei");
			}
		}
		
		paresValidar.remove(0);
		paresValidar.remove(1);
		return encontrado;
	}
	
	
	private void validarListaPares() {
		//como a minha matriz é quadratica, ela tem que se encaixar em um proporcao
		//ex: 2x2,3x3
		
		//não consguo ver o tamanho da  minhas lista, por isso tive que fazer um for
		int lengh=0;
		for (String is : listaPares()) {
			lengh+=1;
		}
//		System.out.println(lengh);
		if(lengh!=8) {
			System.out.println("Lista de Letras invalidas, a sua lista de letras tem q ter 8 Letras");
			System.exit(1);
		}
	}
	
}
