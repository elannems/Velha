package jogo_antonio_elanne;

public class Tabuleiro {
	int[][]tabuleiro;
	
	public Tabuleiro(){
		tabuleiro = new int[3][3];
	}

	public int[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiroJogador(int linha, int coluna) {
		this.tabuleiro[linha][coluna] = 1;
	}
	
	public void setTabuleiroComputador(int linha, int coluna) {
		this.tabuleiro[linha][coluna] = -1;
	}
	
	public void setTabuleiro(int[][] tabuleiro){
		this.tabuleiro = tabuleiro;
	}
	
}
