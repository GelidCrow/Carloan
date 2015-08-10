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
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public char getSesso() {
		return Sesso;
	}
	public void setSesso(char sesso) {
		Sesso = sesso;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public Date getDataNascita() {
		return DataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		DataNascita = dataNascita;
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}
	public String getNumCell() {
		return NumCell;
	}
	public void setNumCell(String numCell) {
		NumCell = numCell;
	}
	public String getNumFisso() {
		return NumFisso;
	}
	public void setNumFisso(String numFisso) {
		NumFisso = numFisso;
	}
	public boolean isAssunto() {
		return Assunto;
	}
	public void setAssunto(boolean assunto) {
		Assunto = assunto;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
