package control;

public class GestoreNotifiche { //Questa classe è, come le altre classi di gestione, un SINGLETON
	
	private static GestoreNotifiche instance = null;
	
	private GestoreNotifiche() { ; }
	
	public static GestoreNotifiche getGestore() {
		if(instance == null) {
			instance = new GestoreNotifiche();
		}
		return instance;
	}
	
	// I seguenti metodi non hanno ricevuto un'effettiva implementazione,
	// in quanto non richiesto dalla traccia
	public void notificaCliente(int numeroTelefono) {
		//System.out.println("Il seguente telefono mobile è stato notificato: " + numeroTelefono);
	}
	
	public void notificaFattorino() {
		//System.out.println("Il fattorino è stato notificato");
	}
}
