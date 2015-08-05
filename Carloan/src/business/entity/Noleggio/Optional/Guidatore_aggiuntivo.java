package business.entity.Noleggio.Optional;

import java.util.Collections;
import java.util.List;

public class Guidatore_aggiuntivo extends OptionalNoleggio{
	private List<Guidatore> guidatori;
	private int numero_guidatori;
	public Guidatore_aggiuntivo(float prezzo, String descrizione,List<Guidatore> guidatori) {
		super(prezzo, descrizione);
		Collections.copy(this.guidatori, guidatori);
		this.numero_guidatori=this.guidatori.size();
	}
	public int getNumero_guidatori() {
		return numero_guidatori;
	}
}
