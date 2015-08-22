package business.entity.Gestori;

import java.util.Date;

import business.entity.Utente;

public abstract class Supervisore extends Utente{

	public Supervisore(String idUtente, String nome, String cognome,
			String sesso, Date dataNascita, String indirizzo,
			String codiceFiscale, String numCell, String numFisso,
			boolean assunto) {
		super(idUtente, nome, cognome, sesso, dataNascita, indirizzo, codiceFiscale,
				numCell, numFisso, assunto);
		// TODO Auto-generated constructor stub
	}


}
