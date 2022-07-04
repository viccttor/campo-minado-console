package br.com.campoMinado.model;

import java.util.ArrayList;
import java.util.List;

import br.com.campoMinado.exception.ExplosaoException;

public class Campo {

	private final int LINHA;
	private final int COLUNA;
 
	private boolean aberto = false;
	private boolean marcado = false;
	private boolean minado = false;

	private List<Campo> vizinhos = new ArrayList<>();

	public Campo(int linha, int coluna) {
		this.LINHA = linha;
		this.COLUNA = coluna;
	}

	boolean adicionarVizinho(Campo vizinho) {

		boolean linhaDiferente = LINHA != vizinho.LINHA;
		boolean colunaDiferente = COLUNA != vizinho.COLUNA;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
		int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA);
		int deltaGeral = deltaColuna + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}

	} 

	void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}

	boolean abrir() {

		if (!aberto && !marcado) {
			aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}

			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}

			return true;
		} else {
			return false;
		}
	}

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	public boolean isMarcado() {
		return marcado;
	}

	public boolean isOpen() {
		return aberto;
	}

	public boolean isClose() {
		return !aberto;
	}

	void minar() {
		if (!minado) {
			minado = true;
		}
	}
	
	 boolean isMinado() {
		return minado;
	}
	 

	 void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protejido = minado && marcado;
		return desvendado || protejido;
	}

	long minasNaVinzinhaca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}

	public String toString() {
		if (marcado) {
			return "x";
		} else if (aberto && minado) {
			return "*";
		} else if (aberto && minasNaVinzinhaca() > 0) {
			return Long.toString(minasNaVinzinhaca());
		} else if (aberto) {
			return " ";
		} else {
			return "?";
		}
	}

	public int getLINHA() {
		return LINHA;
	}

	public int getCOLUNA() {
		return COLUNA;
	}

}
