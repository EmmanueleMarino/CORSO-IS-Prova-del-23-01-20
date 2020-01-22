package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import control.GestoreClienti;
import control.GestoreProdotti;
import control.GestoreSconti;
import control.GestoreSpesa;
import entity.Cliente;
import entity.StatoSpesa;
import exceptions.NumeroSpeseNegativoException;
import util.Data;
import util.InfoSpese;

class TestFunzionale {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Recupero istanze gestori
		GestoreClienti gestoreClienti = GestoreClienti.getGestore();
		GestoreProdotti gestoreProdotti = GestoreProdotti.getGestore();
		GestoreSpesa gestoreSpesa = GestoreSpesa.getGestore();
		GestoreSconti gestoreSconti = GestoreSconti.getGestore();
		
		// Creazione clienti
		gestoreClienti.registrazione("Erich", "patterns1", 123, 123);
		gestoreClienti.registrazione("Richard", "patterns2", 456, 456);
		gestoreClienti.registrazione("Ralph", "patterns3", 789, 789);
		gestoreClienti.registrazione("John", "patterns4", 012, 012);
		
		// Aggiunta prodotti
		gestoreProdotti.aggiungiProdotto(12345, "DetersivoPiatti", "per pulire i piatti", 5.00f, 50);
		gestoreProdotti.aggiungiProdotto(34567, "CremaMani", "per mani screpolate", 10.00f, 70);
		gestoreProdotti.aggiungiProdotto(56789, "Ammorbidente", "per gli abiti", 6.00f, 20);
		gestoreProdotti.aggiungiProdotto(78910, "Sgrassatore", "per le superfici incrostate", 7.00f, 25);

		// Creazione sconto
		gestoreSconti.inserisciSconto(122334, 25, new Data(25, 1, 20));
		
		// Acquisti di Erich
		gestoreSpesa.aggiungiAlCarrello("Erich", 12345, 2);
		gestoreSpesa.aggiungiAlCarrello("Erich", 34567, 3);
		gestoreSpesa.effettuaAcquisto("Erich", 1, new Data(21, 1, 20));
		gestoreSpesa.richiediSconto(1, 122334);
		gestoreSpesa.effettuaPagamento("Erich", 1);
		gestoreSpesa.registraConsegna(1);
		
		gestoreSpesa.aggiungiAlCarrello("Erich", 56789, 5);
		gestoreSpesa.aggiungiAlCarrello("Erich", 78910, 2);
		gestoreSpesa.effettuaAcquisto("Erich", 2, new Data(21, 1, 20));
		gestoreSpesa.effettuaPagamento("Erich", 2);
		gestoreSpesa.registraConsegna(2);
		
		gestoreSpesa.aggiungiAlCarrello("Erich", 12345, 2);
		gestoreSpesa.aggiungiAlCarrello("Erich", 56789, 1);
		gestoreSpesa.effettuaAcquisto("Erich", 3, new Data(21, 1, 20));
		gestoreSpesa.effettuaPagamento("Erich", 3);
		
		// Acquisti di Richard
		gestoreSpesa.aggiungiAlCarrello("Richard", 12345, 2);
		gestoreSpesa.aggiungiAlCarrello("Richard", 34567, 3);
		gestoreSpesa.effettuaAcquisto("Richard", 4, new Data(21, 1, 20));
		gestoreSpesa.effettuaPagamento("Richard", 4);
		gestoreSpesa.registraConsegna(4);
		
		gestoreSpesa.aggiungiAlCarrello("Richard", 56789, 5);
		gestoreSpesa.aggiungiAlCarrello("Richard", 78910, 2);
		gestoreSpesa.effettuaAcquisto("Richard", 5, new Data(21, 1, 20));
		gestoreSpesa.effettuaPagamento("Richard", 5);
		gestoreSpesa.registraConsegna(5);
		
		// Acquisti di John
		gestoreSpesa.aggiungiAlCarrello("John", 12345, 1);
		gestoreSpesa.effettuaAcquisto("John", 6, new Data(21, 1, 20));
		gestoreSpesa.effettuaPagamento("John", 6);
		gestoreSpesa.registraConsegna(6);
	}


	@AfterEach
	void tearDown() throws Exception {
		if(GestoreClienti.getGestore().trovaCliente("Erich").getSpese().get(2).getStato().equals(StatoSpesa.CONSEGNATA))
			GestoreClienti.getGestore().trovaCliente("Erich").getSpese().get(2).setStato(StatoSpesa.ORDINATA);
	}

	@Test
	void test1() {
		assertThrows(NumeroSpeseNegativoException.class, ()-> GestoreSpesa.getGestore().generaReport(-1));
	}
	
	@Test
	void test2() {
		GestoreSpesa.getGestore().registraConsegna(3);
		
		HashMap<Cliente, InfoSpese> retrievedClients = null;
		
		try {
			retrievedClients = GestoreSpesa.getGestore().generaReport(0);
		} catch (NumeroSpeseNegativoException e) {
			System.out.println(e.getMessage());
		}
		
		HashMap<Cliente, InfoSpese> oracolo = new HashMap<Cliente, InfoSpese>();
		oracolo.put(GestoreClienti.getGestore().trovaCliente("Erich"), new InfoSpese(3, 70.0f));
		oracolo.put(GestoreClienti.getGestore().trovaCliente("Richard"), new InfoSpese(2, 84.0f));
		oracolo.put(GestoreClienti.getGestore().trovaCliente("Ralph"), new InfoSpese(0, 0.0f));
		oracolo.put(GestoreClienti.getGestore().trovaCliente("John"), new InfoSpese(1, 5.0f));
		
		assertTrue(retrievedClients.equals(oracolo));
	}
	
	@Test
	void test3() {
		GestoreSpesa.getGestore().registraConsegna(3);
		
		HashMap<Cliente, InfoSpese> retrievedClients = null;
		
		try {
			retrievedClients = GestoreSpesa.getGestore().generaReport(2);
		} catch (NumeroSpeseNegativoException e) {
			System.out.println(e.getMessage());
		}
		
		HashMap<Cliente, InfoSpese> oracolo = new HashMap<Cliente, InfoSpese>();
		oracolo.put(GestoreClienti.getGestore().trovaCliente("Erich"), new InfoSpese(3, 70.0f));
		oracolo.put(GestoreClienti.getGestore().trovaCliente("Richard"), new InfoSpese(2, 84.0f));
		
		assertTrue(retrievedClients.equals(oracolo));
	}
	
	@Test
	void test4() {
		GestoreSpesa.getGestore().registraConsegna(3);
		
		HashMap<Cliente, InfoSpese> retrievedClients = null;
		
		try {
			retrievedClients = GestoreSpesa.getGestore().generaReport(3);
		} catch (NumeroSpeseNegativoException e) {
			System.out.println(e.getMessage());
		}
		
		HashMap<Cliente, InfoSpese> oracolo = new HashMap<Cliente, InfoSpese>();
		oracolo.put(GestoreClienti.getGestore().trovaCliente("Erich"), new InfoSpese(3, 70.0f));
		
		assertTrue(retrievedClients.equals(oracolo));
	}

	@Test
	void test5() {
		HashMap<Cliente, InfoSpese> retrievedClients = null;
		
		try {
			retrievedClients = GestoreSpesa.getGestore().generaReport(3);
		} catch (NumeroSpeseNegativoException e) {
			System.out.println(e.getMessage());
		}
		
		HashMap<Cliente, InfoSpese> oracolo = new HashMap<Cliente, InfoSpese>();
		
		assertTrue(retrievedClients.equals(oracolo));
	}


}
