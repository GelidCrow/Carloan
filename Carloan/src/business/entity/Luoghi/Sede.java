package business.entity.Luoghi;

public class Sede {
	private String IDSede;
	private String Indirizzo;
	private String NumeroTelefono;
	public Sede(String iDSede, String indirizzo, String numeroTelefono) {
		super();
		IDSede = iDSede;
		Indirizzo = indirizzo;
		NumeroTelefono = numeroTelefono;
	}
	public String getIDSede() {
		return IDSede;
	}
	public void setIDSede(String iDSede) {
		IDSede = iDSede;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public String getNumeroTelefono() {
		return NumeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
}
