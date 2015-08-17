package business.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClienteTab {
	private StringProperty nome;
	private StringProperty cognome;
	private StringProperty sesso;
	private ObjectProperty<Date> DataEmissPatente;
	private ObjectProperty<Date>  datanascita;
	private StringProperty Indirizzo;
	private StringProperty codFiscale;
	private StringProperty numCell;
	private StringProperty numTel;
	private StringProperty PatenteGuida;
	private ObjectProperty<Date>  DataScadPatente;
	private StringProperty PartitaIva;
	private StringProperty Email;
	
	public ClienteTab(Cliente cliente){
		this.nome = new SimpleStringProperty(cliente.getNome());
        this.cognome = new SimpleStringProperty(cliente.getCognome());
        this.sesso= new SimpleStringProperty(cliente.getSesso());
        this.DataEmissPatente =new SimpleObjectProperty<Date>(cliente.getDataEmissPatente());
        this.datanascita =new SimpleObjectProperty<Date>(cliente.getDatanascita());
        this.Indirizzo = new SimpleStringProperty(cliente.getIndirizzo());
        this.codFiscale = new SimpleStringProperty(cliente.getCodFiscale());
        this.numCell = new SimpleStringProperty(cliente.getNumCell());
        this.numTel = new SimpleStringProperty(cliente.getNumTel());
        this.PatenteGuida = new SimpleStringProperty(cliente.getPatenteGuida());
        this.DataScadPatente = new SimpleObjectProperty<Date>(cliente.getDataScadPatente());
        this.PartitaIva = new SimpleStringProperty(cliente.getPartitaIva());
        this.Email = new SimpleStringProperty(cliente.getEmail());
	}
	
	public List<ClienteTab> converter(List<Cliente> cliente){
		List<ClienteTab> risultato= new ArrayList<ClienteTab>();
		for(Cliente c: cliente){
			risultato.add(new ClienteTab(c));
		}
		return risultato;
	}
	
	public StringProperty getNome() {
		return nome;
	}

	public void setNome(StringProperty nome) {
		this.nome = nome;
	}

	public StringProperty getCognome() {
		return cognome;
	}

	public void setCognome(StringProperty cognome) {
		this.cognome = cognome;
	}

	public StringProperty getSesso() {
		return sesso;
	}

	public void setSesso(StringProperty sesso) {
		this.sesso = sesso;
	}

	public ObjectProperty<Date> getDataEmissPatente() {
		return DataEmissPatente;
	}

	public void setDataEmissPatente(ObjectProperty<Date> dataEmissPatente) {
		DataEmissPatente = dataEmissPatente;
	}

	public ObjectProperty<Date> getDatanascita() {
		return datanascita;
	}

	public void setDatanascita(ObjectProperty<Date> datanascita) {
		this.datanascita = datanascita;
	}

	public StringProperty getIndirizzo() {
		return Indirizzo;
	}

	public void setIndirizzo(StringProperty indirizzo) {
		Indirizzo = indirizzo;
	}

	public StringProperty getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(StringProperty codFiscale) {
		this.codFiscale = codFiscale;
	}

	public StringProperty getNumCell() {
		return numCell;
	}

	public void setNumCell(StringProperty numCell) {
		this.numCell = numCell;
	}

	public StringProperty getNumTel() {
		return numTel;
	}

	public void setNumTel(StringProperty numTel) {
		this.numTel = numTel;
	}

	public StringProperty getPatenteGuida() {
		return PatenteGuida;
	}

	public void setPatenteGuida(StringProperty patenteGuida) {
		PatenteGuida = patenteGuida;
	}

	public ObjectProperty<Date> getDataScadPatente() {
		return DataScadPatente;
	}

	public void setDataScadPatente(ObjectProperty<Date> dataScadPatente) {
		DataScadPatente = dataScadPatente;
	}

	public StringProperty getPartitaIva() {
		return PartitaIva;
	}

	public void setPartitaIva(StringProperty partitaIva) {
		PartitaIva = partitaIva;
	}

	public StringProperty getEmail() {
		return Email;
	}

	public void setEmail(StringProperty email) {
		Email = email;
	}
}
