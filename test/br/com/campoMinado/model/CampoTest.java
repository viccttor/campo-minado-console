package br.com.campoMinado.model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.campoMinado.exception.ExplosaoException;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CampoTest {
	private Campo  campo; 
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	// Teste Vizinhos
	
	@Order(0)
	@Test
	void testeVizinhoDistanciaUmEsquerdaVerdadeiro() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(1)
	@Test
	void testeVizinhoDistanciaUmDireitaVerdadeiro() {
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(2)
	@Test
	void testeVizinhoDistanciaUmEmCimaVerdadeiro() {
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(3)
	@Test
	void testeVizinhoDistanciaUmEmbaixoDireitaVerdadeiro() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(4)
	@Test
	void testeVizinhoDistanciaFalso() {
		Campo vizinho = new Campo(3,11);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Order(5)
	@Test
	void testeVizinhoDiagonalCimaEsquerdoVerdadeiro() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(6)
	@Test
	void testeVizinhoDiagonalCimaDireitoVerdadeiro() {
		Campo vizinho = new Campo(2,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(7)
	@Test
	void testeVizinhoDiagonalbaixoEsquerdoVerdadeiro() {
		Campo vizinho = new Campo(4,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(8)
	@Test
	void testeVizinhoDiagonalBaixoDireitoVerdadeiro() {
		Campo vizinho = new Campo(4,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Order(9)
	@Test
	void testeVizinhoDiagonalFalso() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	// Vizinho do vizinho
	
	@Order(10)
	@Test
	void testeVizinhoAndVizinhoDoVizinhoTrue() {
		
		Campo campo22 = new Campo(2,2);
		Campo campo11 = new Campo(1,1);
		
		campo.adicionarVizinho(campo22);
		campo22.adicionarVizinho(campo11);
		campo.abrir();
		
		assertTrue(campo22.isOpen() && campo11.isOpen());
	}
	
	@Order(11)
	@Test
	void testeVizinhoAndVizinhoDoVizinhoComMinadoFalse() {
		
		Campo campo22 = new Campo(2,2);
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		
		campo.adicionarVizinho(campo22);
		campo22.adicionarVizinho(campo11);
		campo11.adicionarVizinho(campo12);
		
		campo12.minar();
		campo.abrir();
		
		assertFalse(campo11.isOpen() && campo22.isOpen() && campo12.isOpen());
	}
	
	//Alternar Marcação
	
	@Order(12)
	@Test
	void testeMarcacaoPadraoFalse() {
		assertFalse(campo.isMarcado());
	}
	
	@Order(13)
	@Test
	void testeChamandoMarcacaoTrue() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Order(14)
	@Test
	void testeChamandoMarcacaoDuasVezesFalse() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Order(15)
	@Test
	void testeChamandoMarcacaoTresVezesFalse() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	// Abrir Campo
	
	@Order(16)
	@Test
	void testeAbrirNaoMinadoTrue() {
		assertTrue(campo.abrir());
	}
	
	@Order(17)
	@Test
	void testeAbrirNaoMinadoMarcadoFalse() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Order(18)
	@Test
	void testeAbrirMinadoMarcadoFalse() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Order(19)
	@Test
	void testeAbrirMinadoNaoMarcadoException() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	

	@Order(20)
	@Test
	void testeCampoMinadoCloseTrue() {
		
		Campo campo22 = new Campo(2,2);
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		
		campo.adicionarVizinho(campo22);
		campo22.adicionarVizinho(campo11);
		campo11.adicionarVizinho(campo12);
		
		campo12.minar();
		campo.abrir();
		
		assertTrue(campo12.isClose());
	}

	// isMinado and minar
	
	@Order(21)
	@Test
	void isMinadoFalse() {
		assertFalse(campo.isMinado());
	}
	
	@Order(22)
	@Test
	void isMinadoTrue() {
		campo.minar();
		assertTrue(campo.isMinado());
	}
	

}
