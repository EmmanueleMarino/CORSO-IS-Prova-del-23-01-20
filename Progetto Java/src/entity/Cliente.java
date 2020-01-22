package entity;

import java.util.ArrayList;
import java.util.Iterator;

import util.InfoSpese;

public class Cliente {
	
	// Attributi della classe
	private String nomeUtente;
	private String password;
	private int numeroTelefonico;
	private int numeroCartaCredito;
	private boolean isAbituale;
	private ArrayList<ElementoCarrello> carrello;
	private ArrayList<Spesa> speseEffettuate;

	// Costruttore
	public Cliente(String nomeUtente, String password, int numeroTelefonico, int numeroCartaCredito) {
		this.nomeUtente = nomeUtente;
		this.password = password;
		this.numeroTelefonico = numeroTelefonico;
		this.numeroCartaCredito = numeroCartaCredito;
		isAbituale = false;
		speseEffettuate = new ArrayList<Spesa>();
		carrello = new ArrayList<ElementoCarrello>();
	}
	
	// Metodi di get
	public String getNomeUtente() { return nomeUtente; }
	public String getPassword() { return password; }
	public int getCartaCredito() { return numeroCartaCredito; }
	public boolean getAbitualità() { return isAbituale; }
	public int getTelefono() { return numeroTelefonico; }
	public ArrayList<ElementoCarrello> getCarrello() { return carrello; }
	public ArrayList<Spesa> getSpese() { return speseEffettuate; }
	
	// Questo metodo ricava la coppia numeroSpese-totaleImport relativa al cliente
	// Sebbene non sia una semplice get ma abbia al proprio interno un'elaborazione,
	// ho preferito lasciarla qui e non inserirla nel GestoreClienti
	
	public InfoSpese getInfoSpese() {
		int numeroSpese = 0;
		int totaleImporto = 0;
		for(Iterator<Spesa> i = speseEffettuate.iterator(); i.hasNext(); ) {
			Spesa spesaCorrente = i.next();
			if(spesaCorrente.getStato().compareTo(StatoSpesa.CONSEGNATA) == 0) {
				numeroSpese++;
				totaleImporto += spesaCorrente.getCostoTotale();
			}
		}
		return new InfoSpese(numeroSpese, totaleImporto);
	}
	
	// Metodi di set
	public void setNomeUtente(String nomeUtente) { this.nomeUtente = nomeUtente; }
	public void setPassword(String password) { this.password = password; }
	public void setCartaCredito(int numeroCartaCredito) { this.numeroCartaCredito = numeroCartaCredito; }
	public void setAbitualità(boolean isAbituale) { this.isAbituale = isAbituale; }
	public void setTelefono(int numeroTelefonico) { this.numeroTelefonico = numeroTelefonico; }

}
