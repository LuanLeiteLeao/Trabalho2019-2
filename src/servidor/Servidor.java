package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import servidor.Servidor;

public class Servidor extends Thread {
	
	private static ArrayList<BufferedWriter> clientes;
	private static ServerSocket server;
	private String nome;
	private Socket con;
	private InputStream in;
	private InputStreamReader inr;
	private BufferedReader bfr;
	
	public Servidor(Socket con) {
		this.con = con;
		try {
			in = con.getInputStream();
			inr = new InputStreamReader(in);
			bfr = new BufferedReader(inr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private synchronized void addCliente(BufferedWriter bfw) {
		clientes.add(bfw);
	}
	private synchronized void removeCliente(BufferedWriter bfw) {
		clientes.remove(bfw);
	}
	
	public void run() {

		try {

			String msg;
			OutputStream ou = this.con.getOutputStream();
			Writer ouw = new OutputStreamWriter(ou);
			BufferedWriter bfw = new BufferedWriter(ouw);
			//chama metodo com modificador synchronized para garantir acesso exclusivo
			this.addCliente(bfw);
			nome = msg = bfr.readLine();

			while (!"Sair".equalsIgnoreCase(msg) && msg != null) {
				msg = bfr.readLine();
				//System.out.println(msg);
				System.out.println(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public static void main(String[] args) {

		try {
			// Cria os objetos necess�rio para inst�nciar o servidor
			JLabel lblMessage = new JLabel("Porta do Servidor:");
			JTextField txtPorta = new JTextField("12345");
			Object[] texts = { lblMessage, txtPorta };
			JOptionPane.showMessageDialog(null, texts);
			server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
			clientes = new ArrayList<BufferedWriter>();
			JOptionPane.showMessageDialog(null, "Servidor ativo na porta: " + txtPorta.getText());

			while (true) {
				System.out.println("Aguardando conex�o...");
				Socket con = server.accept();
				System.out.println("Cliente conectado...");
				Thread t = new Servidor(con);
				t.start();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}// Fim do m�todo main
}
