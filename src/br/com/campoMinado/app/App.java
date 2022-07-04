package br.com.campoMinado.app;

import java.util.Scanner;

import br.com.campoMinado.model.Tabuleiro;
import br.com.campoMinado.model.TabuleiroConsole;

public class App {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		int linha = 0;
		int coluna = 0;
		int mina = 0;
		
		System.out.print("Informe a quantidade de Linhas: ");
		linha = entrada.nextInt();
		System.out.print("\nInforme a quantidade de Colunas: ");
		coluna = entrada.nextInt();
		System.out.print("\nInforme a quantidade de Minas: ");
		mina = entrada.nextInt();
		
		Tabuleiro tabuleiro = new Tabuleiro(linha, coluna, mina);
		new TabuleiroConsole(tabuleiro);
	
		entrada.close();

	}
}
