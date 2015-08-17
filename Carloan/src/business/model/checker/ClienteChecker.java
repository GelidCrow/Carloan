package business.model.checker;


import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ClienteChecker implements Checker{
	
    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 20;
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 20;
    private static final int MIN_INDIRIZZO_VALUE=10;
    private static final int MAX_INDIRIZZO_VALUE=50;
    private static final int CODFISCALE_VALUE= 16;

    private static final int MIN_PARTITAIVA_VALUE=0;
    private static final int MAX_PARTITAIVA_VALUE=16;
    
    private static final int MIN_EMAIL_VALUE=10;
    private static final int MAX_EMAIL_VALUE=20;
    
    private static final int NUMCELL_VALUE= 10;

    private static final int MIN_NUMTEL_VALUE= 0;
    private static final int MAX_NUMTEL_VALUE= 10;

    private static final int NUMPATENTE_VALUE= 10;
    
    
    private Cliente cliente;
    private boolean isValid;
    
	@Override
	public void check(Entity entity) throws CommonException {
		cliente= (Cliente) entity;
        checkNome();
        checkCognome();
        checkIndirizzo();
        checkCodFiscale();
        checkNumCell();
        checkNumTel();
        checkPatente();
        checkPartitaIva();
        checkEmail();
	}
	
	
	public void checkNome() throws CommonException{
		int length;
		
        length = cliente.getNome().length();

        isValid = (length >= MIN_NOME_VALUE)
                && (length <= MAX_NOME_VALUE);

        if (!isValid) {
        	throw new CommonException("Nome  non valido");
        }
	}
	
	
	public void checkCognome() throws CommonException{
		int length;
		
        length = cliente.getCognome().length();

        isValid = (length >= MIN_COGNOME_VALUE)
                && (length <= MAX_COGNOME_VALUE);

        if (!isValid) {
        	throw new CommonException("Cognome  non valido");
        }
	}
	
	public void checkIndirizzo() throws CommonException{
		int length;
		
        length = cliente.getIndirizzo().length();

        isValid = (length >= MIN_INDIRIZZO_VALUE)
                && (length <= MAX_INDIRIZZO_VALUE);

        if (!isValid) {
        	throw new CommonException("Indirizzo  non valido");
        }
	}
	
	public void checkCodFiscale() throws CommonException{
		int length;
		
        length = cliente.getCodFiscale().length();

        isValid = (length==CODFISCALE_VALUE);

        if (!isValid) {
        	throw new CommonException("Codice Fiscale non valido");
        }
	}
	

	public void checkPartitaIva() throws CommonException{
		int length;
		
        length = cliente.getPartitaIva().length();

        isValid = (length==MAX_PARTITAIVA_VALUE) || (length== MIN_PARTITAIVA_VALUE);

        if (!isValid) {
        	throw new CommonException("Partita iva non valido");
        }
	}
	

	public void checkEmail() throws CommonException{
		int length;
		
        length = cliente.getEmail().length();

        isValid =    (length >= MIN_EMAIL_VALUE)
                && (length <= MAX_EMAIL_VALUE);

        if (!isValid) {
        	throw new CommonException("Email non valido");
        }
	}

	public void checkNumCell() throws CommonException{
		int length;
		
        length = cliente.getNumCell().length();

        isValid = (length==NUMCELL_VALUE);

        if (!isValid) {
        	throw new CommonException("Numero cellulare non valido");
        }
	}

	public void checkNumTel() throws CommonException{
		int length;
		
        length = cliente.getNumTel().length();

        isValid = (length==MAX_NUMTEL_VALUE) || (length==MIN_NUMTEL_VALUE);

        if (!isValid) {
        	throw new CommonException("Numero telefono non valido");
        }
	}

	public void checkPatente() throws CommonException{
		int length;
		
        length = cliente.getPatenteGuida().length();

        isValid = (length==NUMPATENTE_VALUE);

        if (!isValid) {
        	throw new CommonException("Patente  non valida");
        }
	}
}
