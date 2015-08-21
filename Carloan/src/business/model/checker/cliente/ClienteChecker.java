package business.model.checker.cliente;


import java.sql.Date;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class ClienteChecker implements Checker{
	
    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 20;
    
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 20;
    
    private static final int MAX_SESSO_VALUE=7;
    
    protected static final int MIN_INDIRIZZO_VALUE=10;
    protected static final int MAX_INDIRIZZO_VALUE=50;
    
    private static final int CODFISCALE_VALUE= 16;

    protected static final int MIN_PARTITAIVA_VALUE=0;
    protected static final int MAX_PARTITAIVA_VALUE=11;
    
    protected static final int MIN_EMAIL_VALUE=5;
    protected static final int MAX_EMAIL_VALUE=20;
    
    protected static final int NUMCELL_VALUE= 10;
    protected static final int MIN_NUM_TEL_VALUE=0;
    protected static final int MAX_NUMTEL_VALUE= 10;

    private static final int NUMPATENTE_VALUE= 10;
    
    private static final DatePicker date = new DatePicker(LocalDate.of(1905,1, 1));
    private static final LocalDate dateLimit= date.getValue();
    protected Cliente cliente;
    protected boolean isValid;
    
    
    
	@Override
	public void check(Entity entity) throws CommonException {
		cliente= (Cliente) entity;
        checkNome();
        checkCognome();
        checkSesso();
        checkDataNascita();
        checkDataEmissPatente();
        checkDataScadPatente();
        checkIndirizzo();
        checkCodFiscale();
        checkNumCell();
        checkNumTel();
        checkPatente();
        checkPartitaIva();
        checkEmail();
	}
	
	public void checkDataNascita() throws CommonException {
		if(cliente.getDatanascita().before(Date.valueOf(dateLimit))){
        	throw new CommonException("Data di nascita non valida");
		}
	}
	
	public void checkDataEmissPatente() throws CommonException {
		LocalDate date = cliente.getDatanascita().toLocalDate();
		DatePicker datepicker= new DatePicker(LocalDate.of(date.getYear()+18, date.getMonth(),date.getDayOfMonth()));
		date= datepicker.getValue();
		if(cliente.getDataEmissPatente()==null || cliente.getDataEmissPatente().before(Date.valueOf(date))){
        	throw new CommonException("Data emissione patente non valida");
		}
	}
	
	public void checkDataScadPatente() throws CommonException {
		if(cliente.getDataEmissPatente()==null || cliente.getDataScadPatente().before(Date.valueOf(LocalDate.now()))){
        	throw new CommonException("Patente Scaduta");
		}
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
	
	public void checkSesso() throws CommonException{
		int length;
		
        length = cliente.getSesso().length();

        isValid =  (length == MAX_SESSO_VALUE);

        if (!isValid) {
        	throw new CommonException("Sesso non valido");
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
        	throw new CommonException("Email non valida");
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

        isValid = (length==MAX_NUMTEL_VALUE) || (length==MIN_NUM_TEL_VALUE);

        if (!isValid) {
        	throw new CommonException("Numero telefono non valido");
        }
	}

	public void checkPatente() throws CommonException{
		int length;
		
        length = cliente.getPatenteGuida().length();

        isValid = (length==NUMPATENTE_VALUE);

        if (!isValid) {
        	throw new CommonException("Patente non valida");
        }
	}
}
