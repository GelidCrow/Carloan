package business.model.checker;


import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;
import business.model.Exception.NomeClienteNonValido;

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
        checkNome() ;
        //checkCognome();
	}
	
	
	public void checkNome() throws NomeClienteNonValido{
		int length;
		
        length = cliente.getNome().length();

        isValid = (length >= MIN_COGNOME_VALUE)
                && (length <= MAX_COGNOME_VALUE);

        if (!isValid) {
            throw new NomeClienteNonValido("Nome cliente non valido");
        }
		
	}

}
