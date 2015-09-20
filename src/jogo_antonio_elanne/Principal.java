package jogo_antonio_elanne;
import javax.swing.JFrame;


public class Principal {
	public static void main (String[] args){
		InterfacePrincipal ip = new InterfacePrincipal();
		ip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ip.setVisible(true);
	}
}
