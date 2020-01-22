package entity;

public class Acquisto {
	private Prodotto prodotto;
	private int quantità;
	
	//Costruttore della classe
	public Acquisto(Prodotto prodotto, int quantità) {
		this.prodotto = prodotto;
		this.quantità = quantità;
	}
	
	//Metodi di get
	public Prodotto getProdotto() { return prodotto; }
	public int getQuantità() { return quantità; }
	
	//Metodi di set
	public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }
	public void setQuantità(int quantità) { this.quantità = quantità; }
	
}
