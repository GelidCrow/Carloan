package business.entity.Noleggio;

public enum StatoMulta {
	Aperto,Chiuso;
	
	public static StatoMulta toState(String stato){
		if(stato.equals("Aperto"))
			return StatoMulta.Aperto;
		else 
			return StatoMulta.Chiuso;
	}
}
