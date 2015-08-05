package business.entity.Gestori;

import java.util.Date;

public class SupervisoreSede extends Supervisore {
	private String IDSupervisoreSede="";
	
	SupervisoreSede(String Nome, String Cognome, char Sesso, Date datanascita,String Indirizzo, String codicefiscale, String numcell,String numfisso, boolean assunto, String Username, String Password) {
			super(Nome, Cognome, Sesso, datanascita, Indirizzo, codicefiscale, numcell,numfisso, assunto, Username, Password);
			
		}
	SupervisoreSede(String Nome,String Cognome,char Sesso,String Username,String Password){
		super(Nome,Cognome,Sesso,new Date(),"","","","",true,Username,Password);
	}
	
	
	public String getIDSupervisoreSede() {
		return IDSupervisoreSede;
	}
	
	public void setIDSupervisoreSede(String IDSupervisoreSede) {
		this.IDSupervisoreSede=IDSupervisoreSede;
	}

}

