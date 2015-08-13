package business.entity;

import javafx.scene.control.DatePicker;

//potrebbeessere astratta e cliente estende cliente... cosi puoi aggiungerne tanti
public class Cliente extends Entity{
	
	private String nome;
	private String cognome;
	private String sesso;
	private DatePicker DataEmissPatente;
	private DatePicker datanascita;
	private String Indirizzo;
	private String codFiscale;
	private String numCell;
	private String numTel;
	private String PatenteGuida;
	private DatePicker DataScadPatente;
	private String PartitaIva;
	private String Email;
	
	
	public Cliente(String nome, String cognome, String sesso,DatePicker DataEmissPatente,DatePicker datanascita,
			String Indirizzo, String codFiscale,String numCell,String numTel,String PatenteGuida,
			DatePicker DataScadPatente, String PartitaIva, String Email)
	{
		this.nome = nome; 
		this.cognome= cognome;
		this.sesso= sesso;
		this.datanascita= datanascita;
		this.Indirizzo= Indirizzo;
		this.codFiscale= codFiscale;
		this.numCell= numCell;
		this.numTel= numTel;
		this.PatenteGuida= PatenteGuida;
		this.DataEmissPatente= DataEmissPatente;
		this.DataScadPatente= DataScadPatente;
		this.PartitaIva=PartitaIva;
		this.Email= Email;
	 }


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getIndirizzo() {
		return Indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}


	public String getSesso() {
		return sesso;
	}


	public void setSesso(String sesso) {
		this.sesso = sesso;
	}


	public DatePicker getDatanascita() {
		return datanascita;
	}


	public void setDatanascita(DatePicker datanascita) {
		this.datanascita = datanascita;
	}


	public String getCodFiscale() {
		return codFiscale;
	}


	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}


	public String getNumCell() {
		return numCell;
	}


	public void setNumCell(String numCell) {
		this.numCell = numCell;
	}


	public String getNumTel() {
		return numTel;
	}


	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}


	public String getPatenteGuida() {
		return PatenteGuida;
	}


	public void setPatenteGuida(String patenteGuida) {
		PatenteGuida = patenteGuida;
	}


	public DatePicker getDataEmissPatente() {
		return DataEmissPatente;
	}


	public void setDataEmissPatente(DatePicker dataEmissPatente) {
		DataEmissPatente = dataEmissPatente;
	}


	public DatePicker getDataScadPatente() {
		return DataScadPatente;
	}


	public void setDataScadPatente(DatePicker dataScadPatente) {
		DataScadPatente = dataScadPatente;
	}


	public String getPartitaIva() {
		return PartitaIva;
	}


	public void setPartitaIva(String partitaIva) {
		PartitaIva = partitaIva;
	}

	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}

}
