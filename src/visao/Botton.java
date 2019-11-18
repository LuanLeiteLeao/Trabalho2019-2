package visao;

import java.awt.Button;
import java.awt.Image;
import java.text.Normalizer.Form;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controle.Gerenciador;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Botton extends JButton{
	private int[] Coordinate= {0,0};
	private Gerenciador gerent;
	private boolean isEntraUmaVez=true;
	private boolean encontrado = false;
	private Matrix matrix;
	
	public Botton(int row,int colum,Gerenciador gerente,Matrix matrix) {
		 
		this.matrix = matrix;
		this.gerent=gerente;
		listern(this);
		
		
		setBackground(SystemColor.info);
		
		
		//esse metodo contrutor tem a responsabilidade de guardas a possicao de um botao
		this.Coordinate[0]=colum;
		this.Coordinate[1]=row;

		
		
		isIcon(true);
		
		
	}

	public void isIcon(boolean state) {
		if(state) {

			ImageIcon icon = new ImageIcon("/home/luanleiteleao/eclipse-workspace/JogoDaMemoria/imag/ico1.jpg"); //Somente esta linha foi alterada
			icon.setImage(icon.getImage().getScaledInstance(50, 50, 100));
			setIcon(icon);
		}else {
			setIcon(null);
			
		}
		
	}

	private void listern(Botton a) {
		ActionListener event = new ActionListener() {
			public void actionPerformed(ActionEvent args) {
				
				
				if(isEntraUmaVez) {
					
					//guardar valor clicado
					int [] aux = {Coordinate[0],Coordinate[1]};
					
					
					
					
					//caso este contador esteja valendo 2, que dizer q foi clicado duas vezes
					if(matrix.euFuiClicado2vezes()){
						
						
						matrix.virarBotoes();
						
						
					}
					
					
					
					
					//so entra uma vez
					isEntraUmaVez=false;
					
					a.isIcon(false);
					//busca uma AsciiArt nesta cordenada, que sera posteriomente colocada no botão
					a.setText(gerent.getAsciiArt(Coordinate[0],Coordinate[1]));
					//System.out.println(gerent.getAsciiArt(Coordinate[0],Coordinate[1]));
						
				
					matrix.pares.add(a);	
					
					
					
					matrix.isAcertei();
				
				}
								
				
				
							}
		};
		
		
		addActionListener(event);

	}

	//retorna a possicao do botao na matriz 	
	public int[] getCoordinate() {
		
		
		return this.Coordinate; 
	}
	
	
	public void setisEntraUmaVez(boolean arg) {
		isEntraUmaVez = arg;
	}
	
	//fazerrrrrrr coisopais aquiui

	public boolean isEcontrado() {
		return encontrado;
	}
	
	public void setEcontrado(boolean var) {
		this.encontrado=var;
	}
}
