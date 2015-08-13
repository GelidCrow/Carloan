package business.model.checker;


import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ClienteChecker implements Checker{
    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 30;
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 30;
    
    
    private Cliente cliente;
    private boolean isValid;
    
	@Override
	public void check(Entity entity) throws CommonException {
		cliente= (Cliente) entity;
        checkNome();
	}
	
	
	public void checkNome() throws CommonException{
		int length;
		
        length = cliente.getNome().length();

        isValid = (length >= MIN_NOME_VALUE)
                && (length <= MAX_NOME_VALUE);

        if (!isValid) {
        	throw new CommonException("Nome cliente non valido");
        }
	}

}
