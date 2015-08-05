package business.entity.Noleggio.Optional;

public abstract class Optional {
	private float prezzo;
	private String descrizione;
	
	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Optional(float prezzo, String descrizione) {
		super();
		this.prezzo = prezzo;
		this.descrizione = descrizione;
	}
	
	
}
