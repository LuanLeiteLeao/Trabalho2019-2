package controle;

import java.awt.EventQueue;

import visao.TelaPrincipal;

public class AsciiArt {
	private int[] posi1= {0,0};
	private int[] posi2= {0,0};
	private String asciiart;
	
	
	
	public AsciiArt(int[] integer, int[] integer2, String asciiart) {
		
		this.posi1 = integer;
		this.posi2 = integer2;
		this.asciiart = asciiart;
	}
	public int[] getPosi1() {
		return posi1;
	}
	public void setPosi1(int[] posi1) {
		this.posi1 = posi1;
	}
	public int[] getPosi2() {
		return posi2;
	}
	public void setPosi2(int[] posi2) {
		this.posi2 = posi2;
	}
	public String getAsciiart() {
		return asciiart;
	}
	public void setAsciiart(String asciiart) {
		this.asciiart = asciiart;
	}
	


	
}




