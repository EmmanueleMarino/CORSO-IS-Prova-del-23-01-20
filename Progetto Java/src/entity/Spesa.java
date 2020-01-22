package entity;

import util.Data;
import java.util.ArrayList;

public class Spesa {
	
	//Attributi della classe
	private int id;
	private Data data;
	private float costoTotale;
	private Sconto scontoApplicato;
	private StatoSpesa stato;
	private ArrayList<Acquisto> acquisti;
	
	//Costruttore
	public Spesa(int id, Data data, ArrayList<Acquisto> acquisti) {
		this.id = id;
		this.data = data;
		this.costoTotale = 0;
		this.stato = StatoSpesa.IN_CORSO;
		this.acquisti = acquisti;
	}
	
	//Metodi di get
	public int getId() { return id; }
	public Data getData() { return data; }
	public float getCostoTotale() { return costoTotale; }
	public Sconto getScontoApplicato() { return scontoApplicato; }
	public StatoSpesa getStato() { return stato; }
	public ArrayList<Acquisto> getAcquisti() { return acquisti; }
	
	//Metodi di set
	public void setId(int id) { this.id = id; }
	public void setData(Data data) { this.data = data; }
	public void setCostoTotale(float costoTotale) { this.costoTotale = costoTotale; }
	public void setScontoApplicato(Sconto scontoApplicato) { this.scontoApplicato = scontoApplicato; }
	public void setStato(StatoSpesa stato) { this.stato = stato; }
	
}
