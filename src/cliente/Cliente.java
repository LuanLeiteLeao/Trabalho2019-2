package cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cliente.Cliente;
import net.miginfocom.swing.MigLayout;
import visao.Matrix;

public class Cliente extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private JTextArea texto;
	private JTextField txtMsg;
	private JButton btnSend;
	private JButton btnSair;
	private JLabel lblHistorico;
	private JLabel lblMsg;
	private JPanel pnlContent;
	private Socket socket;
	private OutputStream ou;
	private Writer ouw;
	private BufferedWriter bfw;
	private JTextField txtIP;
	private JTextField txtPorta;
	private JTextField txtNome;
	private JFrame frmJogoDaMemoria;
	private Matrix neo;
	private Integer pontos = 0;
	String verificaAcertoou ="caraca";
	JLabel placar;
	

	public Cliente() throws IOException {
		JLabel lblMessage = new JLabel("Verificar!");
		txtIP = new JTextField("127.0.0.1");
		txtPorta = new JTextField("12345");
		txtNome = new JTextField("Vida Loka");
		Object[] texts = { lblMessage, txtIP, txtPorta, txtNome };
		JOptionPane.showMessageDialog(null, texts);
		
		
		frmJogoDaMemoria = new JFrame();
		frmJogoDaMemoria.setTitle("Jogo Da Memoria");
		frmJogoDaMemoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogoDaMemoria.getContentPane().setLayout(new BorderLayout(0, 0));
		placar = new JLabel();
		placar.setText(pontos.toString());
		placar.setSize(100,50);
		placar.setHorizontalAlignment(pontos);
		frmJogoDaMemoria.getContentPane().add(placar, "North");
		
		 neo = new Matrix(4,this);
		frmJogoDaMemoria.getContentPane().add(neo, "Center");
		frmJogoDaMemoria.setSize(getMaximumSize());
		frmJogoDaMemoria.setVisible(true);
		
	}
	
	

	
	public void conectar() throws IOException {

		socket = new Socket(txtIP.getText(), Integer.parseInt(txtPorta.getText()));
		ou = socket.getOutputStream();
		ouw = new OutputStreamWriter(ou);
		bfw = new BufferedWriter(ouw);
		bfw.write(txtNome.getText() + "\r\n");
		bfw.flush();
	}
	
	
	
	/**
	 * @throws IOException
	 */
	public void escutar() throws IOException {

		InputStream in = socket.getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";
		String info = "";
		String linha = "";
		String coluna = "";
		

		while (!"Sair".equalsIgnoreCase(msg))

			if (bfr.ready()) {
				msg = bfr.readLine();
				String[] split = msg.split(":");
				if(split[0].equalsIgnoreCase("Coord")) {
					info = split[1];
					linha = split[2];
					coluna = split[3];
					
					System.out.println("info"+info);
					
					if(info.equals(verificaAcertoou)) {
						setPontos(2);
					}else {
						verificaAcertoou = info.toString();
						System.out.println("entrous "+verificaAcertoou);
								
					}
					
					JButton jb = neo.getBotao(Integer.parseInt(linha), Integer.parseInt(coluna));
					jb.setText(info);
					placar.setText(pontos.toString());
					frmJogoDaMemoria.getContentPane().repaint();
					
				}
				
			}
	}
	
	public void sair() throws IOException {

		//enviarMensagem("Sair");
		bfw.close();
		ouw.close();
		ou.close();
		socket.close();
	}

	public void enviarMensagem(String msg) throws IOException {

		if (msg.equals("Sair")) {
			bfw.write("Desconectado \r\n");
	
		} else {
			System.out.println(msg);
			bfw.write(msg+"\r\n");
			
		}
		bfw.flush();
		
//		return escutar();
		//txtMsg.setText("");
		 
	}
	
	public Integer getPontos() {
		return pontos;
	}




	public void setPontos(Integer pontos) {
		this.pontos = this.pontos + pontos;
	}
	
	
	
	
	
	@Override

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) throws IOException {

		Cliente app = new Cliente();
		app.conectar();
		app.escutar();
	}
}
