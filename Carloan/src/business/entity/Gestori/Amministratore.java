package business.entity.Gestori;

import java.util.Date;

import business.entity.Utente;

public class Amministratore extends Utente {

	private int IDDitta;


	public Amministratore(int idUtente, String nome, String cognome,
			String sesso, Date dataNascita, String indirizzo,
			String codiceFiscale, String numCell, String numFisso,
			boolean assunto,int IDDitta) {
		super(idUtente, nome, cognome, sesso, dataNascita, indirizzo, codiceFiscale,
				numCell, numFisso, assunto);
		// TODO Auto-generated constructor stub
		this.IDDitta=IDDitta;
	}


	public int getIDDitta() {
		return IDDitta;
	}

	public void setIDDitta(int iDDitta) {
		IDDitta = iDDitta;
	}



}

