package business.entity.Auto.Fascia;

public abstract  class Fascia {
	private String IDFascia;
	private float prezzo;

	private String Descrizione;
	public Fascia(String iDFascia, float prezzo,  String descrizione) {
		super();
		IDFascia = iDFascia;
		this.prezzo = prezzo;
		Descrizione = descrizione;
	}
	public String getIDFascia() {
		return IDFascia;
	}
	public void setIDFascia(String iDFascia) {
		IDFascia = iDFascia;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	
}
