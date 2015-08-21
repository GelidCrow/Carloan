package business.entity.Noleggio.Optional;

public class Guidatore {
	private Integer id;
private String Nome;
private String Cognome;
private String IDGuidatore;
private String Indirizzo;
private String CodFiscale;
private String PatenteGuida;
public Guidatore(Integer id,String nome, String cognome, String iDGuidatore,
		String indirizzo, String codFiscale, String patenteGuida) {
	super();
	Nome = nome;
	Cognome = cognome;
	IDGuidatore = iDGuidatore;
	Indirizzo = indirizzo;
	CodFiscale = codFiscale;
	PatenteGuida = patenteGuida;
	this.id=id;
}

}
