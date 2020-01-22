package control;

import entity.Prodotto;
import java.util.ArrayList;
import java.util.Iterator;

public class GestoreProdotti { //Questa classe Ë, come le altre classi di gestione, un SINGLETON

	private ArrayList<Prodotto> elencoProdotti;
	private static GestoreProdotti instance = null;
	
	private GestoreProdotti() {
		elencoProdotti = new ArrayList<Prodotto>();
	}
	
	public static GestoreProdotti getGestore() {
		if(instance == null) {
			instance = new GestoreProdotti();
		}
		return instance;
	}
	
	public void aggiungiProdotto(int codice, String nome, String descrizione, float prezzo, int quantit‡Disponibile) {
		elencoProdotti.add(new Prodotto(codice, nome, descrizione, prezzo, quantit‡Disponibile));
	}
	
	public void modificaProdotto(int codice, String nome, String descrizione, float prezzo, int quantit‡Disponibile) {
		// Recupera il prodotto dall'elenco di prodotti
		Prodotto prodottoDaModificare = trovaProdotto(codice);
		
		// Modifica gli attributi del prodotto
		prodottoDaModificare.setCodice(codice);
		prodottoDaModificare.setNome(nome);
		prodottoDaModificare.setDescrizione(descrizione);
		prodottoDaModificare.setPrezzo(prezzo);
		prodottoDaModificare.setQuantit‡Disponibile(quantit‡Disponibile);
	}
	
	public void rimuoviProdotto(int codice) {
		// Rimuovi il prodotto dall'elenco
		Prodotto prodottoDaRimuovere = trovaProdotto(codice);
		elencoProdotti.remove(prodottoDaRimuovere);
	}
	
	
	public ArrayList<Prodotto> visualizzaProdotti() { return elencoProdotti; }
	
	// Funzione di supporto
	public Prodotto trovaProdotto(int codice) {
		// Trova l'indice del prodotto che ha il codice indicato
		int found_idx = 0;
		for(Iterator<Prodotto> i = elencoProdotti.iterator(); i.hasNext(); ) {
			if(i.next().getCodice() != codice)
				found_idx++;
			else
				break;
		}
		return elencoProdotti.get(found_idx);
	}
}
