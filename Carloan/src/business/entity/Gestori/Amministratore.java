package business.entity.Gestori;

import java.util.Date;

import business.entity.Utente;

public class Amministratore extends Utente {


	public Amministratore(String idUtente, String nome, String cognome,
			String sesso, Date dataNascita, String indirizzo,
			String codiceFiscale, String numCell, String numFisso,
			boolean assunto) {
		super(idUtente, nome, cognome, sesso, dataNascita, indirizzo, codiceFiscale,
				numCell, numFisso, assunto);
		// TODO Auto-generated constructor stub
	}

	private int IDDitta;


	public int getIDDitta() {
		return IDDitta;
	}

	public void setIDDitta(int iDDitta) {
		IDDitta = iDDitta;
	}



}

