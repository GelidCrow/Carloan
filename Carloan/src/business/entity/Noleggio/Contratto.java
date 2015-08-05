package business.entity.Noleggio;

import java.util.Date;

public class Contratto {
	private String IDContratto;
	private String IDCliente;
	private String IDOperatore;
	private StatoContratto stato; /// questo può esssere enumerativo di regola
	private Date DataCreazione;
	private String Note;
	private Date dataChiusura;
	
	Contratto( String IDContratto,String IDCliente, String IDOperatore,StatoContratto stato,Date DataCreazione,String Note,Date dataChiusura){
		this.IDContratto= IDContratto;
		this.IDCliente= IDCliente;
		this.IDOperatore= IDOperatore;
		this.stato= stato;
		this.DataCreazione= DataCreazione;
		this.Note= Note;
		this.dataChiusura= dataChiusura;
	}

	public String getIDContratto() {
		return IDContratto;
	}

	public void setIDContratto(String iDContratto) {
		IDContratto = iDContratto;
	}

	public String getIDCliente() {
		return IDCliente;
	}

	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}

	public String getIDOperatore() {
		return IDOperatore;
	}

	public void setIDOperatore(String iDOperatore) {
		IDOperatore = iDOperatore;
	}

	public String getStato() {
		return stato.toString();
	}

	public void setStato(StatoContratto stato) {
		this.stato = stato;
	}

	public Date getDataCreazione() {
		return DataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		DataCreazione = dataCreazione;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public Date getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
}


