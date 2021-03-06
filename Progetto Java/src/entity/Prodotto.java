package entity;

public class Prodotto {
	
	//Attributi della classe
	private int codice;
	private String nome;
	private String descrizione;
	private float prezzo;
	private int quantitąDisponibile;
	
	//Costruttore
	public Prodotto(int codice, String nome, String descrizione, float prezzo, int quantitąDisponibile) {
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.quantitąDisponibile = quantitąDisponibile;
	}
	
	//Metodi di get
	public int getCodice() { return codice; }
	public String getNome() { return nome; }
	public String getDescrizione() { return descrizione; }
	public float getPrezzo() { return prezzo; }
	public int getQuantitąDisponibile() { return quantitąDisponibile; }
	
	//Metodi di set
	public void setCodice(int codice) { this.codice = codice; }
	public void setNome(String nome) { this.nome = nome; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	public void setPrezzo(float prezzo) { this.prezzo = prezzo; }
	public void setQuantitąDisponibile(int quantitąDisponibile) { this.quantitąDisponibile = quantitąDisponibile; }
	
	//Metodo di confronto di uguaglianza (overridato da Object)
	@Override
	public boolean equals(Object prodotto) {
		Prodotto prod = (Prodotto) prodotto;
		if(this.getCodice() == prod.getCodice())
			return true;
		else
			return false;
	}
}
