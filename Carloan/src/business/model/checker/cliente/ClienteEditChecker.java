package business.model.checker.cliente;

import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ClienteEditChecker extends ClienteChecker{
	
	@Override
	public void check(Entity entity) throws CommonException {
		cliente= (Cliente) entity;
        checkIndirizzo();
        checkNumCell();
        checkNumTel();
        checkPartitaIva();
        checkEmail();
	}
	
	@Override
	public void checkNumCell() throws CommonException{
		int length;
		
        length = cliente.getNumCell().length();

        isValid = (length==NUMCELL_VALUE) || (length==0);

        if (!isValid) {
        	throw new CommonException("Numero cellulare non valido");
        }
	}
	
	@Override
	public void checkNumTel() throws CommonException{
		int length;
		
        length = cliente.getNumTel().length();

        isValid = (length==MAX_NUMTEL_VALUE)  || (length==0);

        if (!isValid) {
        	throw new CommonException("Numero telefono non valido");
        }
	}

	@Override
	public void checkIndirizzo() throws CommonException{
		int length;
		
        length = cliente.getIndirizzo().length();

        isValid = (length==0) || ((length >= MIN_INDIRIZZO_VALUE) && (length <= MAX_INDIRIZZO_VALUE));

        if (!isValid) {
        	throw new CommonException("Indirizzo  non valido");
        }
	}
	@Override
	public void checkPartitaIva() throws CommonException{
		int length;
		
        length = cliente.getPartitaIva().length();

        isValid = (length==MAX_PARTITAIVA_VALUE) || (length== MIN_PARTITAIVA_VALUE);

        if (!isValid) {
        	throw new CommonException("Partita iva non valido");
        }
	}
	@Override
	public void checkEmail() throws CommonException{
		int length;
		
        length = cliente.getEmail().length();

        isValid =   ( (length >= MIN_EMAIL_VALUE)
                && (length <= MAX_EMAIL_VALUE)) || (length==0);
        if (!isValid) {
        	throw new CommonException("Email non valido");
        }
	}
}
