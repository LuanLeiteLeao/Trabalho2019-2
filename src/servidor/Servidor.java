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

import controle.Test;

public class Servidor extends Thread {

	private static ArrayList<BufferedWriter> clientes;
	private static ServerSocket server;
	private String nome;
	private Socket con;
	private InputStream in;
	private InputStreamReader inr;
	private BufferedReader bfr;
	//alterado por luan
	private static Test controle=new Test();
	//alterado por luan
	
	/**
	 * M�todo construtor
	 * 
	 * @param com
	 *            do tipo Socket
	 */
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
	/**
	 * M�todo run
	 */
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
				sendToAll(bfw, msg);
				System.out.println(msg);
				mensagensJogo(bfw, msg);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	
	private void mensagensJogo(BufferedWriter bwSaida, String msg) throws IOException {

		BufferedWriter bwS, bwP = null;
		int linha = -1;
		int coluna = -1;
		String retornoBusca=null;
		try {

			 linha = Integer.parseInt((Character.toString(msg.charAt(0))));
			 coluna = Integer.parseInt((Character.toString(msg.charAt(2)))); 
			 retornoBusca=controle.getAsciiArt(linha, coluna);
			 
		} catch (Exception e) {
			retornoBusca="ERRO";
		}
		 if(retornoBusca==null) {
			 retornoBusca="Posicao Ivalido"; 
		 }
		 
		for (BufferedWriter bw : clientes) {
			bwS =  bw;
			try{
			bw.write("Coord:"+retornoBusca+":"+linha+":"+coluna+"\r\n");
			
			bw.flush();
			}catch(Exception e){
				bwP=bwS;
			}
					
		}
		
	}
	
	/***
	 * M�todo usado para enviar mensagem para todos os clients
	 * 
	 * @param bwSaida
	 *            do tipo BufferedWriter
	 * @param msg
	 *            do tipo String
	 * @throws IOException
	 */
	public void sendToAll(BufferedWriter bwSaida, String msg) throws IOException {
		BufferedWriter bwS, bwP = null;

		for (BufferedWriter bw : clientes) {
			bwS =  bw;
			if (!(bwSaida == bwS)) {
				try{
				bw.write(nome + " -> " + msg + "\r\n");
				bw.flush();
				}catch(Exception e){
					bwP=bwS;
				}
			}			
		}
		if(bwP !=null){
			this.removeCliente(bwP);
		}
	}

	/***
	 * M�todo main
	 * 
	 * @param args
	 */
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
} // Fim da classe
