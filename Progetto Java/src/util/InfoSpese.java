package util;

public class InfoSpese{
	
	//Attributi della classe
	private int numeroSpeseEffettuate;
	private float importoComplessivoSpeso;
	
	//Costruttore
	public InfoSpese(int numeroSpeseEffettuate, float importoComplessivoSpeso) {
		this.numeroSpeseEffettuate = numeroSpeseEffettuate;
		this.importoComplessivoSpeso = importoComplessivoSpeso;
	}
	
	//Metodi di get
	public int getNumeroSpese() { return numeroSpeseEffettuate; }
	public float getImportoComplessivo() { return importoComplessivoSpeso; }
	
	//Metodi di set
	public void setNumeroSpese(int numeroSpeseEffettuate) { this.numeroSpeseEffettuate = numeroSpeseEffettuate; }
	public void setImportoComplessivo(float importoComplessivoSpeso) { this.importoComplessivoSpeso = importoComplessivoSpeso; }
	
	//Metodo di confronto di uguaglianza (overridato da Object)
	@Override
	public boolean equals(Object infoSpese) {
		InfoSpese info = (InfoSpese) infoSpese;
		if (this.getNumeroSpese() == info.getNumeroSpese() && this.getImportoComplessivo() == info.getImportoComplessivo())
			return true;
		else
			return false;
	}
}
