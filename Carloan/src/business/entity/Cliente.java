package business.entity;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//potrebbeessere astratta e cliente estende cliente... cosi puoi aggiungerne tanti
public class Cliente extends Entity{
	
	private String nome;
	private String cognome;
	private String sesso;
	private  Date DataEmissPatente;
	private  Date datanascita;
	private String Indirizzo;
	private String codFiscale;
	private String numCell;
	private String numTel;
	private String PatenteGuida;
	private  Date DataScadPatente;
	private String PartitaIva;
	private String Email;
	
	
	private StringProperty nomeT;
	private StringProperty cognomeT;
	private StringProperty sessoT;
	private ObjectProperty<Date> DataEmissPatenteT;
	private ObjectProperty<Date>  datanascitaT;
	private StringProperty IndirizzoT;
	private StringProperty codFiscaleT;
	private StringProperty numCellT;
	private StringProperty numTelT;
	private StringProperty PatenteGuidaT;
	private ObjectProperty<Date>  DataScadPatenteT;
	private StringProperty PartitaIvaT;
	private StringProperty EmailT;
	
	public Cliente(){
		
	}
	
	public Cliente(String nome, String cognome, String sesso, Date DataEmissPatente, Date datanascita,
			String Indirizzo, String codFiscale,String numCell,String numTel,String PatenteGuida,
			 Date DataScadPatente, String PartitaIva, String Email)
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
		
		
		this.nomeT = new SimpleStringProperty(nome);
        this.cognomeT = new SimpleStringProperty(cognome);
        this.sessoT= new SimpleStringProperty(sesso);
        this.DataEmissPatenteT =new SimpleObjectProperty<Date>(DataEmissPatente);
        this.datanascitaT =new SimpleObjectProperty<Date>(datanascita);
        this.IndirizzoT = new SimpleStringProperty(Indirizzo);
        this.codFiscaleT = new SimpleStringProperty(codFiscale);
        this.numCellT = new SimpleStringProperty(numCell);
        this.numTelT = new SimpleStringProperty(numTel);
        this.PatenteGuidaT = new SimpleStringProperty(PatenteGuida);
        this.DataScadPatenteT = new SimpleObjectProperty<Date>(DataScadPatente);
        this.PartitaIvaT = new SimpleStringProperty(PartitaIva);
        this.EmailT = new SimpleStringProperty(Email);
	 }

	public void setNome(String nome) {
		this.nome = nome;
		this.nomeT = new SimpleStringProperty(nome);
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
		this.cognomeT = new SimpleStringProperty(cognome);
	}


	public String getIndirizzo() {
		return Indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
		this.IndirizzoT = new SimpleStringProperty(indirizzo);
	}


	public String getSesso() {
		return sesso;
	}


	public void setSesso(String sesso) {
		this.sesso = sesso;
		this.sessoT= new SimpleStringProperty(sesso);
	}


	public  Date getDatanascita() {
		return datanascita;
	}


	public void setDatanascita( Date datanascita) {
		this.datanascita = datanascita;
		  this.datanascitaT =new SimpleObjectProperty<Date>(datanascita);
	}


	public String getCodFiscale() {
		return codFiscale;
	}


	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
		 this.codFiscaleT = new SimpleStringProperty(codFiscale);
	}


	public String getNumCell() {
		return numCell;
	}


	public void setNumCell(String numCell) {
		this.numCell = numCell;
		 this.numCellT = new SimpleStringProperty(numCell);
	}


	public String getNumTel() {
		return numTel;
	}


	public void setNumTel(String numTel) {
		this.numTel = numTel;
		 this.numTelT = new SimpleStringProperty(numTel);
	}


	public String getPatenteGuida() {
		return PatenteGuida;
	}


	public void setPatenteGuida(String patenteGuida) {
		PatenteGuida = patenteGuida;
		 this.PatenteGuidaT = new SimpleStringProperty(patenteGuida);
	}


	public  Date getDataEmissPatente() {
		return DataEmissPatente;
	}


	public void setDataEmissPatente( Date dataEmissPatente) {
		DataEmissPatente = dataEmissPatente;
		this.DataEmissPatenteT =new SimpleObjectProperty<Date>(dataEmissPatente);
	}


	public  Date getDataScadPatente() {
		return DataScadPatente;
	}


	public void setDataScadPatente( Date dataScadPatente) {
		DataScadPatente = dataScadPatente;
		  this.DataScadPatenteT = new SimpleObjectProperty<Date>(dataScadPatente);
	}


	public String getPartitaIva() {
		return PartitaIva;
	}


	public void setPartitaIva(String partitaIva) {
		PartitaIva = partitaIva;
		this.PartitaIvaT = new SimpleStringProperty(partitaIva);
	}

	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
		  this.EmailT = new SimpleStringProperty(email);
	}

	public String getNome() {
		return nome;
	}


	public StringProperty getNomeT() {
		return nomeT;
	}

	public void setNomeT(StringProperty nomeT) {
		this.nomeT = nomeT;
	}

	public StringProperty getCognomeT() {
		return cognomeT;
	}

	public void setCognomeT(StringProperty cognomeT) {
		this.cognomeT = cognomeT;
	}

	public StringProperty getSessoT() {
		return sessoT;
	}

	public void setSessoT(StringProperty sessoT) {
		this.sessoT = sessoT;
	}

	public ObjectProperty<Date> getDataEmissPatenteT() {
		return DataEmissPatenteT;
	}

	public void setDataEmissPatenteT(ObjectProperty<Date> dataEmissPatenteT) {
		DataEmissPatenteT = dataEmissPatenteT;
	}

	public ObjectProperty<Date> getDatanascitaT() {
		return datanascitaT;
	}

	public void setDatanascitaT(ObjectProperty<Date> datanascitaT) {
		this.datanascitaT = datanascitaT;
	}

	public StringProperty getIndirizzoT() {
		return IndirizzoT;
	}

	public void setIndirizzoT(StringProperty indirizzoT) {
		IndirizzoT = indirizzoT;
	}

	public StringProperty getCodFiscaleT() {
		return codFiscaleT;
	}

	public void setCodFiscaleT(StringProperty codFiscaleT) {
		this.codFiscaleT = codFiscaleT;
	}

	public StringProperty getNumCellT() {
		return numCellT;
	}

	public void setNumCellT(StringProperty numCellT) {
		this.numCellT = numCellT;
	}

	public StringProperty getNumTelT() {
		return numTelT;
	}

	public void setNumTelT(StringProperty numTelT) {
		this.numTelT = numTelT;
	}

	public StringProperty getPatenteGuidaT() {
		return PatenteGuidaT;
	}

	public void setPatenteGuidaT(StringProperty patenteGuidaT) {
		PatenteGuidaT = patenteGuidaT;
	}

	public ObjectProperty<Date> getDataScadPatenteT() {
		return DataScadPatenteT;
	}

	public void setDataScadPatenteT(ObjectProperty<Date> dataScadPatenteT) {
		DataScadPatenteT = dataScadPatenteT;
	}

	public StringProperty getPartitaIvaT() {
		return PartitaIvaT;
	}

	public void setPartitaIvaT(StringProperty partitaIvaT) {
		PartitaIvaT = partitaIvaT;
	}

	public StringProperty getEmailT() {
		return EmailT;
	}

	public void setEmailT(StringProperty emailT) {
		EmailT = emailT;
	}	
}
