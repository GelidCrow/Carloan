package business.entity.Luoghi;

import business.entity.Entity;

public class Sede extends Entity{
	private int IDSede;

	private String Indirizzo;
	private String NumeroTelefono;
	private String nome;
	private String IDAgenzia; 
	public Sede(int iDSede, String indirizzo, String numeroTelefono,String nome,String idagenzia) {
		super();
		IDSede = iDSede;
		Indirizzo = indirizzo;
		NumeroTelefono = numeroTelefono;
		this.nome=nome;
		setIDAgenzia(idagenzia);
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
	public String getIDAgenzia() {
		return IDAgenzia;
	}
	public void setIDAgenzia(String iDAgenzia) {
		IDAgenzia = iDAgenzia;
	}
}
