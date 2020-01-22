package entity;

import util.Data;

public class Sconto {
	
	//Attributi della classe
	private int codice;
	private int percentuale;
	private Data dataScadenza;
	
	//Costruttore
	public Sconto(int codice, int percentuale, Data dataScadenza) {
		this.codice = codice;
		this.percentuale = percentuale;
		this.dataScadenza = dataScadenza;
	}
	
	//Metodi di get
	public int getCodice() { return codice; }
	public int getPercentuale() { return percentuale; }
	public Data getDataScadenza() { return dataScadenza; }
	
	//Metodi di set
	public void setCodice(int codice) { this.codice = codice; }
	public void setPercentuale(int percentuale) { this.percentuale = percentuale; }
	public void setdataScadenza(Data dataScadenza) { this.dataScadenza = dataScadenza; }
	
}
