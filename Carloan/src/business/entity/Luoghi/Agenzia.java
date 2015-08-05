package business.entity.Luoghi;

public class Agenzia {
	private String IDAgenzia;
	private String NumTelefono;
	public Agenzia(String iDAgenzia, String numTelefono) {
		super();
		IDAgenzia = iDAgenzia;
		NumTelefono = numTelefono;
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
	
}
