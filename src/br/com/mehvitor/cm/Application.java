package br.com.mehvitor.cm;

import br.com.mehvitor.cm.model.Tabuleiro;

public class Application {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		
		tabuleiro.abrirCampo(3, 3);
		tabuleiro.alterMarcacao(4, 4);
		tabuleiro.alterMarcacao(4, 5);
		
		System.out.println(tabuleiro);

	}

}
