package business.model;

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
}
