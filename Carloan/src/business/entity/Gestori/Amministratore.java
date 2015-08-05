package business.entity.Gestori;

import java.util.Date;

import business.entity.Utente;

public class Amministratore extends Utente {
	private String IDAmministratore="";
	
	Amministratore(String Nome, String Cognome, char Sesso, Date datanascita,String Indirizzo, String codicefiscale, String numcell,String numfisso, boolean assunto, String Username, String Password) {
			super(Nome, Cognome, Sesso, datanascita, Indirizzo, codicefiscale, numcell,numfisso, assunto, Username, Password);
			
		}
	Amministratore(String Nome,String Cognome,char Sesso,String Username,String Password){
		super(Nome,Cognome,Sesso,new Date(),"","","","",true,Username,Password);
	}
	
	
	public String getIDAmministratore() {
		return IDAmministratore;
	}
	
	public void setIDAmministratore(String IDAmministratore) {
		this.IDAmministratore = IDAmministratore;
	}
}
