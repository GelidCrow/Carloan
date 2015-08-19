package business.entity.Noleggio;


import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.Gestori.Operatore;

public class Contratto extends Entity{
	


	private Integer IDContratto;
	private Cliente cliente;
	private Integer IDOperatore=null;
	private Integer IDSupervisoreSede=null;
	private Integer IDSupervisoreAgenzia=null;
	private Integer IDAmministratore=null;
	private String stato; /// questo può esssere enumerativo di regola
	private Date DataCreazione;
	private Date dataChiusura;
	private String Note;
	
	private SimpleIntegerProperty IDContrattoT;
	private SimpleStringProperty datiCliente;


	private IntegerProperty IDOperatoreT;
	private IntegerProperty IDSupervisoreSedeT;
	private IntegerProperty IDSupervisoreAgenziaT;
	private IntegerProperty IDAmministratoreT;
	private StringProperty statoT;
	private ObjectProperty<Date>  DataCreazioneT;
	private ObjectProperty<Date>  dataChiusuraT;
	private StringProperty NoteT;

	public Contratto(){}

	public void setIDContratto(Integer iDContratto) {
		IDContratto = iDContratto;
		IDContrattoT = new SimpleIntegerProperty(iDContratto);
	}



	public void setIDOperatore(Integer iDOperatore) {
		IDOperatore = iDOperatore;
		IDOperatoreT = new SimpleIntegerProperty(iDOperatore);
	}

	public void setIDSupervisoreSede(Integer iDSupervisoreSede) {
		IDSupervisoreSede = iDSupervisoreSede;
		IDSupervisoreSedeT = new SimpleIntegerProperty(iDSupervisoreSede);
	}

	public void setIDSupervisoreAgenzia(Integer iDSupervisoreAgenzia) {
		IDSupervisoreAgenzia = iDSupervisoreAgenzia;
		IDSupervisoreAgenziaT = new SimpleIntegerProperty(iDSupervisoreAgenzia);
	}

	public void setIDAmministratore(Integer iDAmministratore) {
		IDAmministratore = iDAmministratore;
		IDAmministratoreT = new SimpleIntegerProperty(iDAmministratore);
	}

	public void setStato(String stato) {
		this.stato = stato;
		statoT = new SimpleStringProperty(stato.toString());
	}

	public void setDataCreazione(Date dataCreazione) {
		DataCreazione = dataCreazione;
        DataCreazioneT = new SimpleObjectProperty<Date>(dataCreazione);
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
        dataChiusuraT = new SimpleObjectProperty<Date>(dataChiusura);
	}

	public void setNote(String note) {
		Note = note;
		NoteT= new SimpleStringProperty(note);
	}

	public void setIDContrattoT(SimpleIntegerProperty iDContrattoT) {
		IDContrattoT = iDContrattoT;
	}



	public void setIDOperatoreT(IntegerProperty iDOperatoreT) {
		IDOperatoreT = iDOperatoreT;
	}

	public void setIDSupervisoreSedeT(IntegerProperty iDSupervisoreSedeT) {
		IDSupervisoreSedeT = iDSupervisoreSedeT;
	}

	public void setIDAmministratoreT(IntegerProperty iDAmministratoreT) {
		IDAmministratoreT = iDAmministratoreT;
	}

	public void setStatoT(StringProperty statoT) {
		this.statoT = statoT;
	}

	public void setDataCreazioneT(ObjectProperty<Date> dataCreazioneT) {
		DataCreazioneT = dataCreazioneT;
	}

	public void setDataChiusuraT(ObjectProperty<Date> dataChiusuraT) {
		this.dataChiusuraT = dataChiusuraT;
	}

	public void setNoteT(StringProperty noteT) {
		NoteT = noteT;
	}

	public Integer getIDContratto() {
		return IDContratto;
	}


	public Integer getIDOperatore() {
		return IDOperatore;
	}

	public Integer getIDSupervisoreSede() {
		return IDSupervisoreSede;
	}

	public Integer getIDSupervisoreAgenzia() {
		return IDSupervisoreAgenzia;
	}

	public Integer getIDAmministratore() {
		return IDAmministratore;
	}

	public String getStato() {
		return stato;
	}

	public Date getDataCreazione() {
		return DataCreazione;
	}

	public Date getDataChiusura() {
		return dataChiusura;
	}

	public String getNote() {
		return Note;
	}

	public SimpleIntegerProperty getIDContrattoT() {
		return IDContrattoT;
	}


	public IntegerProperty getIDOperatoreT() {
		return IDOperatoreT;
	}

	public IntegerProperty getIDSupervisoreSedeT() {
		return IDSupervisoreSedeT;
	}

	public IntegerProperty getIDSupervisoreAgenziaT() {
		return IDSupervisoreAgenziaT;
	}

	public IntegerProperty getIDAmministratoreT() {
		return IDAmministratoreT;
	}

	public StringProperty getStatoT() {
		return statoT;
	}

	public ObjectProperty<Date> getDataCreazioneT() {
		return DataCreazioneT;
	}

	public ObjectProperty<Date> getDataChiusuraT() {
		return dataChiusuraT;
	}

	public StringProperty getNoteT() {
		return NoteT;
	}
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SimpleStringProperty getDatiCliente() {
		return datiCliente;
	}

	public void setDatiCliente(SimpleStringProperty datiCliente) {
		this.datiCliente = datiCliente;
	}

	public void setIDSupervisoreAgenziaT(IntegerProperty iDSupervisoreAgenziaT) {
		IDSupervisoreAgenziaT = iDSupervisoreAgenziaT;
	}

}


