package visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
//import controle.Gerenciador;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class Matrix extends JPanel {
	private int size;
//	private Gerenciador gerent;
	private int clique=0;
	private  List<Botton> paresJaEncotrados = new ArrayList<Botton>();
	public List<Botton> pares = new ArrayList<Botton>();
	private List<Botton> bottons = new ArrayList<Botton>();

	public Matrix(int quadratico, Cliente c) {
		
	//	gerent = new Gerenciador ();
		
		
		this.setLayout(this.creatSizeMigLayout(4));
	
		
		for(int i=0;i<quadratico;i++) {
			for(int j=0;j<quadratico;j++) {
				Botton button = new Botton(i, j,this,c);
				
				bottons.add(button);
				
				this.add(button, "cell "+i+" "+j);
			}
		}
		
	
		
		
	}
	
	public JButton getBotao(int lin, int col) {
		int posicao = lin+col*4;
		
		return bottons.get(posicao);
	}

	
	private MigLayout creatSizeMigLayout(int size) {
		
		String aux = new String("[grow,fill] ");
		String conf = null; 
		
		for(int i=0;i<size;i++) {
			conf+=aux;
			//concatena a minha string de configuracao
			//[grow,fill] [grow,fill] [grow,fill] [grow,fill] linhas quantidade
			//[grow,fill] [grow,fill] [grow,fill] [grow,fill] colunas quantidade 
		
		
		}
		
		//System.out.println(conf);
		
		MigLayout layout = new MigLayout("", conf,conf);
		
		
		return layout;
	}
	
	public boolean euFuiClicado2vezes() {
		
		boolean retorno;
		
		if(this.clique == 2) {
			retorno=true;
			this.clique=0;

		
		}
		else {
			retorno = false;
			this.clique+=1;
		}
	
	
		return retorno;
	
	}
	

//	public void isAcertei() {
//		
//		if(pares.get(0).getText() == pares.get(1).getText()) {
//			JOptionPane.showMessageDialog(null,"ACERTOUU");
//			pares.get(0).setEcontrado(true);
//			pares.get(1).setEcontrado(true);
//			
//		}
//	}
//	

	public  void virarBotoes() {
		
		
		
		pares= new ArrayList<Botton>();
		
		
		for(Botton bot:bottons ) {

			if(!bot.isEcontrado()) {
				bot.setisEntraUmaVez(true);
				bot.isIcon(true);
				bot.setText("");
			}
				
		}
		
		//se ele mandou virar, e porque um 3 botao foi clicado, os outros dois viram e esse continua
		this.clique+=1;
		
		
		
	}

}
