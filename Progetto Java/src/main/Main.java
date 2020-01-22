package main;

import control.*;
import util.*;
import exceptions.NumeroSpeseNegativoException;

public class Main {

	public static void main(String args[]) {
		setup();
		
		try {
			GestoreSpesa.getGestore().generaReport(2);
		} catch (NumeroSpeseNegativoException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void setup(){
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
		gestoreSpesa.registraConsegna(3);
		
		
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
	
}
