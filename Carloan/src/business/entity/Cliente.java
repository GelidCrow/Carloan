package business.entity;

import java.util.Date;
//potrebbeessere astratta e cliente estende cliente... cosi puoi aggiungerne tanti
public class Cliente {
	
	private String nome;
	private String cognome;
	private String sesso;
	private Date datanascita;
	private String Indirizzo;
	private String codFiscale;
	private String numCell;
	private String numTel;
	private String PatenteGuida;
	private Date DataEmissPatente;
	private Date DataScadPatente;
	private String PartitaIva;
	private String IDCliente;
	private String Email;
	private String x;
	
	
	Cliente(String nome, String cognome, String sesso,Date datanascita,
			String Indirizzo, String codFiscale,String numCell,String numTel,String PatenteGuida,
			Date DataEmissPatente, Date DataScadPatente, String PartitaIva, String IDCliente,String Email)
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
		this.IDCliente= IDCliente;
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


	public Date getDatanascita() {
		return datanascita;
	}


	public void setDatanascita(Date datanascita) {
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


	public Date getDataEmissPatente() {
		return DataEmissPatente;
	}


	public void setDataEmissPatente(Date dataEmissPatente) {
		DataEmissPatente = dataEmissPatente;
	}


	public Date getDataScadPatente() {
		return DataScadPatente;
	}


	public void setDataScadPatente(Date dataScadPatente) {
		DataScadPatente = dataScadPatente;
	}


	public String getPartitaIva() {
		return PartitaIva;
	}


	public void setPartitaIva(String partitaIva) {
		PartitaIva = partitaIva;
	}


	public String getIDCliente() {
		return IDCliente;
	}


	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}

}
