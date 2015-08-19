package business.entity.Noleggio;

import java.util.ArrayList;
import java.util.List;

public enum StatoContratto { 
	Aperto,Chiuso,Annullato;
	
	public static List<String> getAllStates(){
		List<String> stati= new ArrayList<String>();
		stati.add("Aperto");
		stati.add("Chiuso");
		stati.add("Annullato");
		return stati;
	}
	
}
