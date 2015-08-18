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
}
