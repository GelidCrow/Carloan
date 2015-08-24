package business.entity.Auto.manutenzione;

import java.time.LocalDate;
public abstract class Manutenzione{
	
	private String IDManutenzione;
	private LocalDate Datainizio;
	private LocalDate DataFine;
	private String note;
	
	public Manutenzione(String iDManutenzione, LocalDate localDate, LocalDate dataFine,
			String note) {
		IDManutenzione = iDManutenzione;
		Datainizio = localDate;
		DataFine = dataFine;
		this.note = note;
	}
	public String getIDManutenzione() {
		return IDManutenzione;
	}
	public void setIDManutenzione(String iDManutenzione) {
		IDManutenzione = iDManutenzione;
	}
	public LocalDate getDatainizio() {
		return Datainizio;
	}
	public void setDatainizio(LocalDate datainizio) {
		Datainizio = datainizio;
	}
	public LocalDate getDataFine() {
		return DataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		DataFine = dataFine;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
