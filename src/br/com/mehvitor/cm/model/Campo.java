package br.com.mehvitor.cm.model;

import java.util.ArrayList;
import java.util.List;

import br.com.mehvitor.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean campoAberto = false;
	private boolean minado = false;
	private boolean campoMarcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaTotal = deltaColuna + deltaLinha;
		
		if(deltaTotal == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if(deltaTotal == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}
	
	void alterMarcacao() {
		if(!campoAberto) {
			campoMarcado = !campoMarcado; 
		}
	}
	
	boolean abrirCampo() {
		if(!campoAberto && !campoMarcado) {
			campoAberto = true;
			
			if(minado) {
				throw new ExplosaoException();
			}
			
			if(vizinhaSegura()) {
				vizinhos.forEach(v -> v.abrirCampo());
			}
			return true;
		}else {
			return false;
		}
	}
	
	boolean vizinhaSegura() {
		return vizinhos.stream()
				.noneMatch(v -> v.minado);
	}
	
	public boolean isMarcado() {
		return campoMarcado;
	}
	
	void minar() {
		minado = true;
	}
	
	public boolean isAberto() {
		return campoAberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
}
