package business.entity.Noleggio.Optional;

import java.util.List;

public class GuidatoreAggiuntivo extends OptionalNoleggio{
	private List<Integer> guidatori;
	private int numero_guidatori;
	public GuidatoreAggiuntivo(Integer id,float prezzo, String descrizione,String nome) {
		super(id,prezzo, descrizione,nome);
	}
	public int getNumero_guidatori() {
		return numero_guidatori;
	}

}
