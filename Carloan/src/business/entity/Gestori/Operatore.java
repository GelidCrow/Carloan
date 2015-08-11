package business.entity.Gestori;

import java.util.Date;

import business.entity.Utente;

public class Operatore extends Utente {
	
	private String IDOperatore="";	
	private String IDSede="";
		
	Operatore(String Nome, String Cognome, char Sesso, Date datanascita,String Indirizzo, String codicefiscale, String numcell,String numfisso, boolean assunto, String Username, String Password) {
			super(Nome, Cognome, Sesso, datanascita, Indirizzo, codicefiscale, numcell,numfisso, assunto, Username, Password);
			
		}
	Operatore(String Nome,String Cognome,char Sesso,String Username,String Password){
		super(Nome,Cognome,Sesso,new Date(),"","","","",true,Username,Password);
	}
	public Operatore(String Nome,String Cognome,String IDSede){
		super(Nome,Cognome);
		this.setIDSede(IDSede);
	}
	
	
	public String getIDOperatore() {
		return IDOperatore;
	}
	
	public void setIDOperatore(String iDOperatore) {
		IDOperatore = iDOperatore;
	}
	public String getIDSede() {
		return IDSede;
	}
	public void setIDSede(String iDSede) {
		IDSede = iDSede;
	}

}

