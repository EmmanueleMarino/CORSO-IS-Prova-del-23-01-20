package control;

import java.util.ArrayList;
import java.util.Iterator;

import entity.Cliente;

public class GestoreClienti { //Questa classe è, come le altre classi di gestione, un SINGLETON

		private ArrayList<Cliente> elencoClienti;
		private static GestoreClienti instance = null;
		
		private GestoreClienti() {
			elencoClienti = new ArrayList<Cliente>();
		}
		
		public static GestoreClienti getGestore() {
			if(instance == null) {
				instance = new GestoreClienti();
			}
			return instance;
		}
		
		public void registrazione(String nomeUtente, String password, int numeroTelefonico, int numeroCartaCredito) {
			elencoClienti.add(new Cliente(nomeUtente, password, numeroTelefonico, numeroCartaCredito));
		}
		
		public ArrayList<Cliente> getElencoClienti() {
			return elencoClienti;
		}
		
		// Funzione di supporto
		public Cliente trovaCliente(String nomeUtente) {
			// Trova l'indice del cliente col nome utente indicato
			int found_idx = 0;
			for(Iterator<Cliente> i = elencoClienti.iterator(); i.hasNext(); ) {
				if(i.next().getNomeUtente().compareTo(nomeUtente) != 0)
					found_idx++;
				else
					break;
			}
			return elencoClienti.get(found_idx);
		}

}
