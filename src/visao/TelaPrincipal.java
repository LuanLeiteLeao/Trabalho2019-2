package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.FlowLayout;

public class TelaPrincipal {
	private JFrame frmJogoDaMemoria;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmJogoDaMemoria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogoDaMemoria = new JFrame();
		frmJogoDaMemoria.setTitle("Jogo Da Memoria");
		frmJogoDaMemoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogoDaMemoria.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
		JLabel topo = new JLabel("novo");
		frmJogoDaMemoria.getContentPane().add(topo, BorderLayout.NORTH);
		
		//instancia um objeto do tipo Matrix, a onde tem os cartões do jogo da memoria
		Matrix panel = new Matrix();
		frmJogoDaMemoria.getContentPane().add(panel, BorderLayout.CENTER);
		
		//empacota os Frame
		frmJogoDaMemoria.pack();	
		

		//Setar o tamnho da tela 
		// Resolução da tela
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		
		//"A resolução da sua máquina é: " +d.width + " x " +d.height);		
		frmJogoDaMemoria.setSize(d.width,d.height);
				
		
		
				
				
			
	
	}
}
