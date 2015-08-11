package business.entity.Gestori;

import java.util.Date;

public class SupervisoreAgenzia extends Supervisore {
	private String IDSupervisoreAgenzia="";
	private String IDAgenzia="";
	SupervisoreAgenzia(String Nome, String Cognome, char Sesso, Date datanascita,String Indirizzo, String codicefiscale, String numcell,String numfisso, boolean assunto, String Username, String Password) {
			super(Nome, Cognome, Sesso, datanascita, Indirizzo, codicefiscale, numcell,numfisso, assunto, Username, Password);
			 
		}
	SupervisoreAgenzia(String Nome,String Cognome,char Sesso,String Username,String Password){
		super(Nome,Cognome,Sesso,new Date(),"","","","",true,Username,Password);
	}
	public SupervisoreAgenzia(String Nome,String Cognome,String IDAgenzia){
		super(Nome,Cognome);
		this.setIDAgenzia(IDAgenzia);
	}
	
	public String getIDSupervisoreAgenzia() {
		return IDSupervisoreAgenzia;
	}
	
	public void setIDSupervisoreAgenzia(String IDSupervisoreAgenzia) {
		this.IDSupervisoreAgenzia=IDSupervisoreAgenzia;
	}
	public String getIDAgenzia() {
		return IDAgenzia;
	}
	public void setIDAgenzia(String iDAgenzia) {
		IDAgenzia = iDAgenzia;
	}

}

