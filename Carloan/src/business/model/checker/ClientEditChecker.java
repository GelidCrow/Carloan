package business.model.checker;

import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ClientEditChecker extends ClienteChecker{
	
	@Override
	public void check(Entity entity) throws CommonException {
		cliente= (Cliente) entity;
        checkIndirizzo();
        checkNumCell();
        checkNumTel();
        checkPartitaIva();
        checkEmail();
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
}
