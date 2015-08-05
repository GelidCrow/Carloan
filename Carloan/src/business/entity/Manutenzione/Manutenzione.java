package business.entity.Manutenzione;

import java.util.Date;

public abstract class Manutenzione{
	
	private String IDManutenzione;
	private Date Datainizio;
	private Date DataFine;
	private String note;
	
	public Manutenzione(String iDManutenzione, Date datainizio, Date dataFine,
			String note) {
		IDManutenzione = iDManutenzione;
		Datainizio = datainizio;
		DataFine = dataFine;
		this.note = note;
	}
	public String getIDManutenzione() {
		return IDManutenzione;
	}
	public void setIDManutenzione(String iDManutenzione) {
		IDManutenzione = iDManutenzione;
	}
	public Date getDatainizio() {
		return Datainizio;
	}
	public void setDatainizio(Date datainizio) {
		Datainizio = datainizio;
	}
	public Date getDataFine() {
		return DataFine;
	}
	public void setDataFine(Date dataFine) {
		DataFine = dataFine;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
