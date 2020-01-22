package util;

public class Data {
	
	//Attributi della classe
	private int giorno;
	private int mese;
	private int anno;
	
	//Costruttore
	public Data(int giorno, int mese, int anno) {
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}
	
	//Metodi di get
	public int getGiorno() { return giorno; }
	public int getMese() { return mese; }
	public int getAnno() { return anno; }
	
	//Metodi di set
	public void setGiorno(int giorno) { this.giorno = giorno; }
	public void setMese(int mese) { this.mese = mese; }
	public void setAnno(int anno) { this.anno = anno; }
	
}
