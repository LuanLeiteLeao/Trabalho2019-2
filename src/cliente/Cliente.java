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
	
	public Cliente() throws IOException {
		JLabel lblMessage = new JLabel("Verificar!");
		txtIP = new JTextField("127.0.0.1");
		txtPorta = new JTextField("12345");
		txtNome = new JTextField("Cliente");
		Object[] texts = { lblMessage, txtIP, txtPorta, txtNome };
		JOptionPane.showMessageDialog(null, texts);
		
		
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
	
	public void escutar() throws IOException {

		InputStream in = socket.getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";

		while (!"Sair".equalsIgnoreCase(msg))

			if (bfr.ready()) {
				msg = bfr.readLine();
				if (msg.equals("Sair"))
					texto.append("Servidor caiu! \r\n");
				else
					texto.append(msg + "\r\n");
			}
	}
	
	public void sair() throws IOException {

		//enviarMensagem("Sair");
		bfw.close();
		ouw.close();
		ou.close();
		socket.close();
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
