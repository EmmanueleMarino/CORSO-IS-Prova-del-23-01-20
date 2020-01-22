package control;

import java.util.ArrayList;
import java.util.Iterator;

import entity.Sconto;
import util.Data;

public class GestoreSconti { //Questa classe è, come le altre classi di gestione, un SINGLETON
	
	
	private ArrayList<Sconto> elencoSconti;
	private static GestoreSconti instance = null;
	
	private GestoreSconti() {
		elencoSconti = new ArrayList<Sconto>();
	}
	
	public static GestoreSconti getGestore() {
		if(instance == null) {
			instance = new GestoreSconti();
		}
		return instance;
	}
	
	
	public void inserisciSconto(int codice, int percentuale, Data dataScadenza) {
		elencoSconti.add(new Sconto(codice, percentuale, dataScadenza));
	}
	
	// Funzione di supporto
	public Sconto trovaSconto (int codiceSconto) {
		// Trova l'indice del prodotto da rimuovere
		int found_idx = 0;
		for(Iterator<Sconto> i = elencoSconti.iterator(); i.hasNext(); ) {
			if(i.next().getCodice() != codiceSconto)
				found_idx++;
			else
				break;
		}
		return elencoSconti.get(found_idx);
	}
}
