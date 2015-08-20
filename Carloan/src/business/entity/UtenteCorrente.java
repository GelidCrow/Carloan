package business.entity;

public class UtenteCorrente {
	static Entity utente;
	
	public static Entity getUtente(){
		return utente;
	}
	public static void setUtente(Entity entity){
		utente= entity;
	}
}
