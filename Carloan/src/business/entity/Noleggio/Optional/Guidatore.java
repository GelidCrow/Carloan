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
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNome() {
	return Nome;
}
public void setNome(String nome) {
	Nome = nome;
}
public String getCognome() {
	return Cognome;
}
public void setCognome(String cognome) {
	Cognome = cognome;
}
public String getIDGuidatore() {
	return IDGuidatore;
}
public void setIDGuidatore(String iDGuidatore) {
	IDGuidatore = iDGuidatore;
}
public String getIndirizzo() {
	return Indirizzo;
}
public void setIndirizzo(String indirizzo) {
	Indirizzo = indirizzo;
}
public String getCodFiscale() {
	return CodFiscale;
}
public void setCodFiscale(String codFiscale) {
	CodFiscale = codFiscale;
}
public String getPatenteGuida() {
	return PatenteGuida;
}
public void setPatenteGuida(String patenteGuida) {
	PatenteGuida = patenteGuida;
}

}
