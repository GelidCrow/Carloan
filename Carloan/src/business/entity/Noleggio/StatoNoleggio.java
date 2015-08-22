package business.entity.Noleggio;

public enum StatoNoleggio {
	aperto, uscita, rientro, annullato, chiuso;
	
	public static StatoNoleggio toStatoNoleggio(String stato){
		switch(stato){
			case  "aperto": 
				return StatoNoleggio.aperto;
			case "uscita":
				return StatoNoleggio.uscita;
			case "rientro":
				return StatoNoleggio.rientro;
			case "annullato":
				return StatoNoleggio.annullato;
			case "chiuso":
				return StatoNoleggio.chiuso;
				
		}
		return null;
	}
}
