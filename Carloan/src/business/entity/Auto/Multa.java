package business.entity.Auto;

import java.util.Date;

public class Multa {
	
	private String IDMulta="";
	private Date DataMulta;
	private float importo;
	private String Note;
	
	Multa(Date d,float i){
		this(d,i,"");
	}
	public Multa(Date d, float i, String n) {
		this.setDataMulta(d);
		setImporto(i);
		this.setNote(n);
	}
	public String getIDMulta() {
		return IDMulta;
	}
	public Date getDataMulta() {
		return DataMulta;
	}
	public void setDataMulta(Date dataMulta) {
		DataMulta = dataMulta;
	}
	public float getImporto() {
		return importo;
	}
	public void setImporto(float importo) {
		this.importo = importo;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}

}
