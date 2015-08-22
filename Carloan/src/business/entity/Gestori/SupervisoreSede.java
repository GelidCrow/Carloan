package business.entity.Gestori;

import java.util.Date;

public class SupervisoreSede extends Supervisore {
	private int IDSede;
	public SupervisoreSede(int idUtente, String nome, String cognome,
			String sesso, Date dataNascita, String indirizzo,
			String codiceFiscale, String numCell, String numFisso,
			boolean assunto,int idSede) {
		super(idUtente, nome, cognome, sesso, dataNascita, indirizzo, codiceFiscale,
				numCell, numFisso, assunto);
		// TODO Auto-generated constructor stub
		this.IDSede=idSede;
	}
	public int getIDSede() {
		return IDSede;
	}
	public void setIDSede(int iDSede) {
		IDSede = iDSede;
	}

	
	

}

