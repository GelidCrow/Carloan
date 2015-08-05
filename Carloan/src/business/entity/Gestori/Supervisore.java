package business.entity.Gestori;

import java.util.Date;

import business.entity.Utente;

public abstract class Supervisore extends Utente{

	Supervisore(String Nome, String Cognome, char Sesso, String Username,
			String Password) {
		super(Nome, Cognome, Sesso, Username, Password);
	}

	Supervisore(String Nome, String Cognome, char Sesso, Date datanascita,
			String Indirizzo, String codicefiscale, String numcell,
			String numfisso, boolean assunto, String Username, String Password) {
		super(Nome, Cognome, Sesso, datanascita, Indirizzo, codicefiscale, numcell,
				numfisso, assunto, Username, Password);
	}

}
