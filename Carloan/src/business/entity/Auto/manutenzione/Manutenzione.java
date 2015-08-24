package business.entity.Auto.manutenzione;

import java.time.LocalDate;

import business.entity.Entity;
public abstract class Manutenzione extends Entity{
	
	private String IDManutenzione;
	private LocalDate Datainizio;
	private LocalDate DataFine;
	private String note;
	private int IDAuto;
	public Manutenzione(String iDManutenzione, LocalDate localDate, LocalDate dataFine,
			String note,int i) {
		IDManutenzione = iDManutenzione;
		Datainizio = localDate;
		DataFine = dataFine;
		this.note = note;
		i=this.IDAuto;
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
	public int getIDAuto() {
		return IDAuto;
	}
	public void setIDAuto(int iDAuto) {
		IDAuto = iDAuto;
	}
}
