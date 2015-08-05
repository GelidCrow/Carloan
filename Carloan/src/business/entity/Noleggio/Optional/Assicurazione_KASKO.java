package business.entity.Noleggio.Optional;

public class Assicurazione_KASKO extends OptionalNoleggio{
private double copertura;
	public Assicurazione_KASKO(float prezzo, String descrizione,double copertura) {
		super(prezzo, descrizione);
		this.setCopertura(copertura);
	}
	public double getCopertura() {
		return copertura;
	}
	public void setCopertura(double copertura) {
		this.copertura = copertura;
	}

}
