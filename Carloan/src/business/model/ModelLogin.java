package business.model;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.omg.Dynamic.Parameter;

import utility.Crittografia;
import business.entity.Login;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOLogin;

public class ModelLogin implements Model {
	private DaoFactory daofactory;
	private DAOLogin daoLogin;
	
	@Override
	public void Inserimento(Object parameter){
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoLogin= (DAOLogin) daofactory.getDao("DAOLogin");
			
			Login login = populate(parameter);
			
			daoLogin.creazione(login);
			
			
		} catch (InstantiationException | IllegalAccessException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <p>Scompatta il parameter ricevuto e istanzia un entity popolata dai valori di quel parameter</p>
	 * @param parameter
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws CommonException
	 */
	@SuppressWarnings("unused")
	private Login populate(Object parameter) throws NoSuchAlgorithmException{
		@SuppressWarnings("unchecked")
		List<String> param = (List<String>) parameter;
		String username = param.get(0);
		String password = param.get(1);
		String operatore = param.get(2);// qui mi dice se è un opratore, amministratore ecc..
		
		//controllo se l'utente non è già esistente ovviamente..
		
		password = Crittografia.CriptaPassword(password);
		
        Login infermiere = new Login(username,password,operatore);
        
        return infermiere;
    }


	@Override
	public void aggiorna(Object parameter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void lettura() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}

}
