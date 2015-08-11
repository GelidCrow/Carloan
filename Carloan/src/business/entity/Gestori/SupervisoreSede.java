package business.entity.Gestori;

import java.util.Date;

public class SupervisoreSede extends Supervisore {
	private String IDSupervisoreSede="";
	private String IDSede="";
	SupervisoreSede(String Nome, String Cognome, char Sesso, Date datanascita,String Indirizzo, String codicefiscale, String numcell,String numfisso, boolean assunto, String Username, String Password) {
			super(Nome, Cognome, Sesso, datanascita, Indirizzo, codicefiscale, numcell,numfisso, assunto, Username, Password);
			
		}
	SupervisoreSede(String Nome,String Cognome,char Sesso,String Username,String Password){
		super(Nome,Cognome,Sesso,new Date(),"","","","",true,Username,Password);
	}
	public SupervisoreSede(String Nome,String Cognome,String IDSede){
		super(Nome,Cognome);
		this.setIDSede(IDSede);
	}
	
	
	public String getIDSupervisoreSede() {
		return IDSupervisoreSede;
	}
	
	public void setIDSupervisoreSede(String IDSupervisoreSede) {
		this.IDSupervisoreSede=IDSupervisoreSede;
	}
	public String getIDSede() {
		return IDSede;
	}
	public void setIDSede(String iDSede) {
		IDSede = iDSede;
	}

}

