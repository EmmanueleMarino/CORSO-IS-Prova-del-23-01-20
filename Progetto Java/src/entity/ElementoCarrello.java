package entity;

public class ElementoCarrello {
	private Prodotto prodotto;
	private int quantit�;
	
	//Costruttore della classe
	public ElementoCarrello(Prodotto prodotto, int quantit�) {
		this.prodotto = prodotto;
		this.quantit� = quantit�;
	}
	
	//Metodi di get
	public Prodotto getProdotto() { return prodotto; }
	public int getQuantit�() { return quantit�; }
	
	//Metodi di set
	public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }
	public void setQuantit�(int quantit�) { this.quantit� = quantit�; }
	
}
