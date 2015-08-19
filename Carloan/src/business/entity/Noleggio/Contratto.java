package business.entity.Noleggio;


import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.Gestori.Operatore;




public class Contratto extends Entity{
	
	private Integer IDContratto;
	private Integer IDCliente;
	private Integer IDOperatore;
	private Integer IDSupervisoreSede;
	private Integer IDSupervisoreAgenzia;
	private Integer IDAmministratore;
	private StatoContratto stato; /// questo può esssere enumerativo di regola
	private Date DataCreazione;
	private Date dataChiusura;
	private String Note;
	
	private SimpleIntegerProperty IDContrattoT;
	private IntegerProperty IDClienteT;
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

	public void setIDCliente(Integer iDCliente) {
		IDCliente = iDCliente;
		IDClienteT = new SimpleIntegerProperty(iDCliente);
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

	public void setStato(StatoContratto stato) {
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

	public void setIDClienteT(IntegerProperty iDClienteT) {
		IDClienteT = iDClienteT;
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

	public Integer getIDCliente() {
		return IDCliente;
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

	public StatoContratto getStato() {
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

	public IntegerProperty getIDClienteT() {
		return IDClienteT;
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

}


