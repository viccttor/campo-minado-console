package br.com.campoMinado.app;

import br.com.campoMinado.model.Tabuleiro;
import br.com.campoMinado.model.TabuleiroConsole;

public class App {
	public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
		
	}
}
