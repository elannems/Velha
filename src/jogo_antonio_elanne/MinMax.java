package jogo_antonio_elanne;

import java.util.ArrayList;

import javax.swing.JOptionPane;

//Classe elaborada a partir do codigo disponivel em: http://aleph0.info/cursos/ia/trab/luis/3/J2Velha.pdf

public class MinMax {
	ArrayList<PossivelSucessor> possivelSucessor;
	int profundidadeMax;
	
	public MinMax(){
		possivelSucessor = new ArrayList<PossivelSucessor>();
		profundidadeMax = 2;
	}
	
	public int[][] decisao(int[][] tabuleiro){
		possivelSucessor.clear();
		
		int prof = 1;
		int v = max(tabuleiro, true, 1);
		
		for(PossivelSucessor sucessor : possivelSucessor){
			if(sucessor.utilidade == v)
				return sucessor.tabuleiro;
		}
		
		return tabuleiro;
	}
	
	private int max(int[][] tabuleiro, boolean prim, int prof) {
		int v;
		if(prof++ > profundidadeMax || testaFinal(tabuleiro))
			v = utilidade(tabuleiro);
		else{
			v = Integer.MIN_VALUE;
			for(PossivelSucessor sucessor : criaSucessores(tabuleiro, 1)){
				v = Math.max(v, min(sucessor.tabuleiro, prof));
				sucessor.utilidade = v;
				
				if(prim)
					possivelSucessor.add(sucessor);
			}
		}
		return v;
	}

	private int min(int[][] tabuleiro, int prof){
		int v;
		if(prof++ > profundidadeMax || testaFinal(tabuleiro))
			v = utilidade(tabuleiro);
		else{
			v = Integer.MAX_VALUE;
		
			for(PossivelSucessor sucessor : criaSucessores(tabuleiro, 2)){
				v = Math.min(v, max(sucessor.tabuleiro, false, prof));
				sucessor.utilidade = v;
			}
		}
		return v;
	}
	
	private ArrayList<PossivelSucessor> criaSucessores(int[][] tabuleiro, int v){
		ArrayList<PossivelSucessor> sucessores = new ArrayList<PossivelSucessor>();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(tabuleiro[i][j]==0){
					tabuleiro[i][j] = v;
					sucessores.add(new PossivelSucessor(tabuleiro));
					tabuleiro[i][j]=0;
				}
			}
		}
		return sucessores;
	}
	
	private int utilidade(int[][]tabuleiro){
	
		if ( ganhou ( tabuleiro , 1) )
		return 1 ;
		else if ( ganhou ( tabuleiro , 2) )
		return -1;
		else
		return 0 ;
		
	}
	
	public boolean testaFinal(int[][] tabuleiro){

		return ( ganhou( tabuleiro , 2) || semEspaco ( tabuleiro ) ) ;
	}
	
	public boolean ganhou ( int [ ] [ ] tab , int v ){
	for ( int i = 0 ; i < 3; i++)
		if( ganhouLinha ( tab , i , v ) || ganhouColuna ( tab , i , v ) )
			return true ;
		if( ganhouDiag1 ( tab , v ) || ganhouDiag2 ( tab , v ) )
			return true ;
	return false ;
	}
	
	private boolean ganhouLinha ( int [ ] [ ] tab , int l , int v )
	{
	for ( int i = 0 ; i < 3; i++)
	if ( tab [ l ] [ i ] != v )
		return false ;
	return true ;
	}
	
	private boolean ganhouColuna ( int [ ] [ ] tab , int c , int v )
	{
	for ( int i = 0 ; i < 3; i++)
	if ( tab [ i ] [ c ] != v )
	 return false ;
	return true ;
	}
	
	private boolean ganhouDiag1 ( int [ ] [ ] tab , int v )
	{
	for ( int i = 0 ; i < 3; i++)
	if ( tab [ i ] [ i ] != v )
	 return false ;
	return true ;
	}
	
	private boolean ganhouDiag2 ( int [ ] [ ] tab , int v )
	{
	for ( int i = 0 ; i < 3; i++)
	if ( tab [ ( 2)-i ] [ i ] != v )
	return false ;
	return true ;
	}
	
	public boolean semEspaco ( int [ ] [ ] tab )
	{
	for ( int l = 0 ; l < 3; l++)
	for ( int c = 0 ; c < 3; c++)
	if ( tab [ l ] [ c ] == 0)
	return false ;
	 return true ;
	}
	
	
}
