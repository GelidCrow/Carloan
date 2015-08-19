package business.entity;

import java.util.Date;

public abstract class Utente extends Entity {
	
	private String Nome;
	private String Cognome;
	private char Sesso;
	private Date DataNascita;
	private String Indirizzo;
	private String CodiceFiscale;
	private String NumCell;
	private String NumFisso;
	private boolean Assunto=true;
	private String UserName;
	private String Password;

	static Entity utente;
	
	protected Utente(String Nome,String Cognome,char Sesso,String Username,String Password){
		this(Nome,Cognome,Sesso,new Date(),"","","","",true,Username,Password);
	}
	
	protected Utente(String Nome,String Cognome,char Sesso,Date datanascita,String Indirizzo,String codicefiscale,String numcell,String numfisso,boolean assunto,String Username,String Password){
		this.Nome=Nome;
		this.Cognome=Cognome;
		this.Sesso=Sesso;
		this.DataNascita=datanascita;
		this.Indirizzo=Indirizzo;
		this.CodiceFiscale=codicefiscale;
		this.NumCell=numcell;
		this.NumFisso=numfisso;
		this.Assunto=assunto;
		this.UserName=Username;
		this.Password=Password;
	}
	protected Utente(String Nome,String Cognome){
		this(Nome,Cognome,'\0',new Date(),"","","","",true,"","");
	}
	
	public static Entity getUtente(){
		return utente;
	}
	public static void setUtente(Entity entity){
		utente= entity;
	}
}
