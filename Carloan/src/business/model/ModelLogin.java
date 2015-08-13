package business.model;

import java.security.NoSuchAlgorithmException;
import java.util.List;



import business.entity.Entity;
import business.entity.Login;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOLogin;

public class ModelLogin implements Model {
	
	private DaoFactory daofactory;
	private DAOLogin daoLogin;
	
	@Override
	public void Inserimento(Object parameter){
		
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
	
	
	public Entity autenticazione(Object parameter){
		Entity ent=null;
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoLogin= (DAOLogin) daofactory.getDao("DAOLogin");
			
			Login login = (Login) parameter;
			
			ent=daoLogin.autenticazione(login);
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return ent;
	}
	
	/**
	 * <p>Scompatta il parameter ricevuto e istanzia un entity popolata dai valori di quel parameter</p>
	 * @param parameter
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws CommonException
	 */
	private Login populate(Object parameter) throws NoSuchAlgorithmException{
		@SuppressWarnings("unchecked")
		List<String> param = (List<String>) parameter;
		String username = param.get(0);
		String password = param.get(1);
		//controllo se l'utente non è già esistente ovviamente..
		
        Login login = new Login(username,password);
        
        return login;
    }
}
