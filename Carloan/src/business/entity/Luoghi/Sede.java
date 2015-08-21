package business.entity.Luoghi;

import business.entity.Entity;

public class Sede extends Entity{
	private int IDSede;

	private String Indirizzo;
	private String NumeroTelefono;
	private String nome;
	public Sede(int iDSede, String indirizzo, String numeroTelefono) {
		super();
		IDSede = iDSede;
		Indirizzo = indirizzo;
		NumeroTelefono = numeroTelefono;
	}
	public int getIDSede() {
		return IDSede;
	}
	public void setIDSede(int iDSede) {
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
	public String getNome() {
		return nome;
	}
	public void setNome(String  nome) {
		this.nome = nome;
	}
}
