package jogo_antonio_elanne;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfacePrincipal extends JFrame implements ActionListener {
	JPanel jContentPane = null;
	JButton mapaPosicao[][] = new JButton[6][7];
	JLabel mensagem = null;
	JMenu menu = null;
	JMenuBar menuBar = null;
	JMenuItem menuItemIniciar = null;
	Icon preta, branca;
	Tabuleiro tabuleiro;
	boolean jogador1Vez, jogador2Vez;
	ImageIcon imageIcon;
	Icon icon;
	JOptionPane jOptionPane;
	Icon pecaVazia;
	JButton posicao = null;
	boolean jogoAndamento;
	MinMax minmax;

	public InterfacePrincipal() {
		super();
		start();
	}

	private void start() {
		this.setSize(325, 350);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("Antonio e Elanne - Jogo da Velha");
		this.jogoAndamento = true;
		this.tabuleiro = new Tabuleiro();
		this.minmax = new MinMax();
		this.jogador1Vez = true;
		this.jogador2Vez = false;
	}

	private JPanel getJContentPane() {
		preta = new ImageIcon(getClass().getResource("peca-Preta.png"));
		branca = new ImageIcon(getClass().getResource("peca-Branca.png"));
		pecaVazia = new ImageIcon(getClass().getResource("posicaoVazia.png"));
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		int x;
		int y = 40;
		for (int i = 0; i < 3; i++) {
			x = 20;
			for (int j = 0; j < 3; j++) {
				posicao = new JButton();
				posicao.setBounds(new Rectangle(x, y, 90, 70));
				posicao.setIcon(pecaVazia);
				posicao.addActionListener(this);
				jContentPane.add(posicao, null);
				mapaPosicao[i][j] = posicao;
				x += 88;
			}
			y += 70;
		}
		menuBar = new JMenuBar();
		menuBar.add(this.getMenu());
		this.setJMenuBar(menuBar);

		return jContentPane;
	}

	private JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu();
			menu.setText("Jogo");
			menu.setBounds(new Rectangle(1, 0, 57, 21));
			menu.add(getMenuIniciar());

		}
		return menu;
	}

	private JMenuItem getMenuIniciar() {
		if (menuItemIniciar == null) {
			menuItemIniciar = new JMenuItem();
			menuItemIniciar.setText("Iniciar");
			menuItemIniciar.addActionListener(this);
		}
		return menuItemIniciar;
	}

	public void selecionaPosicao(int linha, int coluna) {
		if (tabuleiro.getTabuleiro()[linha][coluna] == 0 && jogoAndamento) {
			if (jogador1Vez){
				mapaPosicao[(linha)][(coluna)].setIcon(preta);
				tabuleiro.setTabuleiroJogador(linha, coluna);
				jogador1Vez = false;
				jogador2Vez = true;
				verificaVencedor(linha, coluna);
				for(int i=0; i<3; i++){
					for(int j=0;j<3;j++){
						System.out.print(tabuleiro.getTabuleiro()[i][j]+"  |  ");
					}
					System.out.println();
					
				}
				//if(!minmax.testaFinal(tabuleiro.getTabuleiro())){
					tabuleiro.setTabuleiro(minmax.decisao(tabuleiro.getTabuleiro()));
				//}
			}
			else{
				//mapaPosicao[(linha)][(coluna)].setIcon(branca);
				//tabuleiro.setTabuleiroComputador(linha, coluna);
				jogador1Vez = true;
				jogador2Vez = false;
				
			}

			verificaVencedor(linha, coluna);
		}
	}

	private void verificaVencedor(int linha, int coluna) {
		int cor;
		if (!jogador1Vez)
			cor = 1;
		else
			cor = 2;

		verificaVertical(linha, coluna, cor);

		verificaHorizontal(linha, coluna, cor);
		
		verificaDiagonalEsquerda(linha, coluna, cor);
		
		verificaDiagonalDireita(linha, coluna, cor);

	}

	private void verificaDiagonalDireita(int linha, int coluna, int cor) {
		int cont = 1;
		int tempC = coluna;
		int tempL = linha;
		boolean corIgual = true;
		
		//Diagonal Direita Superior
		while (corIgual && tempL > 0 && tempC<3) {
			tempL--;
			tempC++;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		
		tempC = coluna;
		tempL = linha;
		corIgual = true;
		
		//Diagonal Esquerda Inferior
		while (corIgual && tempL < 3 && tempC>0) {
			tempL++;
			tempC--;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);
	}
	private void verificaDiagonalEsquerda(int linha, int coluna, int cor) {
		int cont = 1;
		int tempC = coluna;
		int tempL = linha;
		boolean corIgual = true;
		
		//Diagonal Direita Inferior
		while (corIgual && tempL < 2 && tempC<2) {
			tempL++;
			tempC++;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		
		tempC = coluna;
		tempL = linha;
		corIgual = true;
		
		//Diagonal Esquerda Superior
		while (corIgual && tempL > 0 && tempC>0) {
			tempL--;
			tempC--;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);
	}
	


	private void verificaHorizontal(int linha, int coluna, int cor) {
		int cont = 1;
		int temp = coluna;
		boolean corIgual = true;

		// verifica horizontal esquerda
		while (corIgual && temp < 2) {
			temp++;
			if (tabuleiro.getTabuleiro()[linha][temp] == cor)
				cont++;
			else
				corIgual = false;

		}
		temp = coluna;
		corIgual = true;
		
		// verifica horizontal esquerda
		while (corIgual && temp > 0) {
			temp--;
			if (tabuleiro.getTabuleiro()[linha][temp] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);
	}

	private void verificaVertical(int linha, int coluna, int cor) {
		boolean corIgual = true;
		int temp = linha;
		int cont = 1;
		while (corIgual && temp < 2) {
			temp++;
			if (tabuleiro.getTabuleiro()[temp][coluna] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);

	}

	private void verificaQuemVenceu(int cont, int cor) {
		if (cont >= 3 && cor == 1) {
			JOptionPane.showMessageDialog(this, "Você venceu!");
			jogoAndamento = false;
		} else if (cont >= 3 && cor == 2) {
			JOptionPane.showMessageDialog(this, "Computador venceu!");
			jogoAndamento = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItemIniciar) {
			this.start();
			this.pack();
			this.setSize(325, 350);

		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (e.getSource() == mapaPosicao[i][j]) {
					selecionaPosicao(i, j);
				}
			}
		}
	}

}
