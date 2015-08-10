package business.entity.Luoghi;

import business.entity.Entity;

public class Agenzia extends Entity{
	private String IDAgenzia;
	private String NumTelefono;
	private String nome;
	private String IDDitta;
	
	public Agenzia(String iDAgenzia, String numTelefono,
			String nome, String iDDitta) {
		super();
		IDAgenzia = iDAgenzia;
		NumTelefono = numTelefono;
		this.nome = nome;
		IDDitta = iDDitta;
	}
	

	public String getIDAgenzia() {
		return IDAgenzia;
	}
	public void setIDAgenzia(String iDAgenzia) {
		IDAgenzia = iDAgenzia;
	}
	public String getNumTelefono() {
		return NumTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		NumTelefono = numTelefono;
	}
	public String getIDDitta() {
		return IDDitta;
	}
	public void setIDDitta(String iDDitta) {
		IDDitta = iDDitta;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
