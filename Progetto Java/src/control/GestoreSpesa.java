package control;

import entity.*;
import util.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import exceptions.NumeroSpeseNegativoException;

public class GestoreSpesa { //Questa classe Ë, come le altre classi di gestione, un SINGLETON
	
	private static ArrayList<Spesa> elencoSpese;
	private static GestoreSpesa instance = null;
	
	private GestoreSpesa() {
		elencoSpese = new ArrayList<Spesa>();
	}
	
	public static GestoreSpesa getGestore() {
		if(instance == null) {
			instance = new GestoreSpesa();
		}
		return instance;
	}
	
	
	// Aggiunge un prodotto, in una certa quantit‡, al carrello di un cliente
	public void aggiungiAlCarrello(String nomeUtenteCliente, int codiceProdotto, int quantit‡) {
		// Trova il cliente a partire dal nome utente specificato
		Cliente cliente = GestoreClienti.getGestore().trovaCliente(nomeUtenteCliente);
		
		// Trova il prodotto a partire dal codice specificato
		Prodotto prodotto = GestoreProdotti.getGestore().trovaProdotto(codiceProdotto);
		
		ArrayList<ElementoCarrello> carrello = cliente.getCarrello();
		carrello.add(new ElementoCarrello(prodotto, quantit‡));
	}
	
	
	// Rimuove un elemento dal carrello del cliente
	public void rimuoviDalCarrello(String nomeUtenteCliente, int codiceProdotto) {
		// Trova il cliente a partire dal nome utente specificato
		Cliente cliente = GestoreClienti.getGestore().trovaCliente(nomeUtenteCliente);
		
		// Trova il prodotto a partire dal codice specificato
		Prodotto prodotto = GestoreProdotti.getGestore().trovaProdotto(codiceProdotto);

		ArrayList<ElementoCarrello> carrello = cliente.getCarrello();
		
		int found_idx = 0;
		for (Iterator<ElementoCarrello> i = carrello.iterator(); i.hasNext(); ) {
			if (i.next().getProdotto().equals(prodotto))
				found_idx++;
			else
				break;
		}
		
		carrello.remove(found_idx);
	}
	
	// Svuota il carrello e crea la spesa, che dovr‡ poi essere confermata mediante pagamento
	public void effettuaAcquisto(String nomeUtenteCliente, int idSpesa, Data dataSpesa) {
		// Trova il cliente a partire dal nome utente
		Cliente cliente = GestoreClienti.getGestore().trovaCliente(nomeUtenteCliente);
		
		ArrayList<Acquisto> travasoCarrello = new ArrayList<Acquisto>();
		
		// Effettua il travaso dal carrello alla lista di acquisti della spesa
		for(Iterator<ElementoCarrello> i = cliente.getCarrello().iterator(); i.hasNext(); ) {
			ElementoCarrello attualeElemento = i.next();
			travasoCarrello.add(new Acquisto(attualeElemento.getProdotto(), attualeElemento.getQuantit‡()));
		};
		// Svuota il carrello
		cliente.getCarrello().clear();
		
		// Crea una nuova spesa, ne calcola il costo totale e la inserisce nell'elenco delle spese
		// della corrente classe di gestione e del cliente che ha effettuato l'acquisto
		Spesa spesa = new Spesa(idSpesa, dataSpesa, travasoCarrello);
		spesa.setCostoTotale(ricalcolaCostoTotale(spesa));
		
		elencoSpese.add(spesa);
		cliente.getSpese().add(spesa);
		
	}
	
	
	public void effettuaPagamento(String nomeUtenteCliente, int idSpesa) {
		// Trova il cliente a partire dal nome utente specificato
		Cliente cliente = GestoreClienti.getGestore().trovaCliente(nomeUtenteCliente);
		
		// Trova la spesa associata all'ID specificato
		Spesa spesa = trovaSpesa(idSpesa);
		
		// Poni la spesa nello stato "ordinata" e notifica il cliente ed il fattorino
		spesa.setStato(StatoSpesa.ORDINATA);
		GestoreNotifiche notifHandler = GestoreNotifiche.getGestore();
		notifHandler.notificaFattorino();
		notifHandler.notificaCliente(cliente.getTelefono());
		
		// Detrae la quantit‡ acquistata dei diversi prodotti dalle loro rispettive "quantit‡Disponibili"
		for(Iterator<Acquisto> i = spesa.getAcquisti().iterator(); i.hasNext(); ) {
			Acquisto acquistoAttuale = i.next();
			acquistoAttuale.getProdotto().setQuantit‡Disponibile(acquistoAttuale.getProdotto().getQuantit‡Disponibile() - acquistoAttuale.getQuantit‡());
		}
	}
	
	public void richiediSconto(int idSpesa, int codiceSconto) {
		Spesa spesa = trovaSpesa(idSpesa);
		spesa.setScontoApplicato(GestoreSconti.getGestore().trovaSconto(codiceSconto));
		spesa.setCostoTotale(ricalcolaCostoTotale(spesa));
	}
	
	public void registraConsegna(int idSpesa) {
		// Trova la spesa associata all'ID specificato
		Spesa spesa = trovaSpesa(idSpesa);
		
		// Cambia lo stato della spesa in "CONSEGNATA"
		spesa.setStato(StatoSpesa.CONSEGNATA);
	}

	public HashMap<Cliente, InfoSpese> generaReport(int minimoSpese) throws NumeroSpeseNegativoException {
		
		if(minimoSpese<0)
			throw new NumeroSpeseNegativoException("Nessun cliente puÚ aver effettuato un numero negativo di spese");
		
		// Preparo il report da ritornare
		HashMap<Cliente, InfoSpese> report = new HashMap<Cliente, InfoSpese>();
		
		System.out.println("Report: Clienti che hanno effettuato dalle " + minimoSpese + " spese in su / importo totale speso da questi.\n");
		for(Iterator<Cliente> i = GestoreClienti.getGestore().getElencoClienti().iterator(); i.hasNext(); ) {
			Cliente clienteAttuale = i.next();
			InfoSpese infoSpeseClienteAttuale = clienteAttuale.getInfoSpese();
			if(infoSpeseClienteAttuale.getNumeroSpese() >= minimoSpese) {
				report.put(clienteAttuale, infoSpeseClienteAttuale);
				System.out.println("CLIENTE: " + clienteAttuale.getNomeUtente() + 
				" , SPESE EFFETTUATE: " + infoSpeseClienteAttuale.getNumeroSpese() +
				" , TOTALE IMPORTO SPESO: Ä" + infoSpeseClienteAttuale.getImportoComplessivo());
			}
		}
		
		// Per una migliore resa grafica dell'output
		System.out.println("\n");
		
		return report;
	}
	
	public float ricalcolaCostoTotale(Spesa spesa) {
		float costoTotale = 0;
		
		// Somma i costi dei prodotti
		for(Iterator<Acquisto> i = spesa.getAcquisti().iterator(); i.hasNext();) {
			Acquisto acquistoCorrente = i.next();
			costoTotale += (acquistoCorrente.getProdotto().getPrezzo() * acquistoCorrente.getQuantit‡());
		}
		
		// Applica l'eventuale sconto
		if(spesa.getScontoApplicato() != null) {
			costoTotale *= ( (float) spesa.getScontoApplicato().getPercentuale())/100;
		}
		
		return costoTotale;
	}
	
	// Funzione di supporto
	public Spesa trovaSpesa(int idSpesa) {
		int found_idx = 0;
		for (Iterator<Spesa> i = elencoSpese.iterator(); i.hasNext(); ) {
			if (i.next().getId() != idSpesa)
				found_idx++;
			else
				break;
		}
		return elencoSpese.get(found_idx);
	}
	
}
