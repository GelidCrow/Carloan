package business.entity.Noleggio.Optional;

import java.util.Collections;
import java.util.List;

public class GuidatoreAggiuntivo extends OptionalNoleggio{
	private List<Guidatore> guidatori;
	private int numero_guidatori;
	public GuidatoreAggiuntivo(Integer id,float prezzo, String descrizione,String nome,List<Guidatore> guidatori) {
		super(id,prezzo, descrizione,nome);
		Collections.copy(this.guidatori, guidatori);
		this.numero_guidatori=this.guidatori.size();
	}
	public int getNumero_guidatori() {
		return numero_guidatori;
	}

}
