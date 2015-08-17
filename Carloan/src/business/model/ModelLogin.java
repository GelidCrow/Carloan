package business.model;

import presentation.controller.FrontController;
import business.entity.Entity;
import business.entity.Login;
import business.model.Exception.CommonException;
import business.model.checker.LoginChecker;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOLogin;

public class ModelLogin implements Model {
	
	private DaoFactory daofactory;
	private DAOLogin daoLogin;



	@Override
	public void lettura() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Entity autenticazione(Entity parameter){
		Entity ent=null;
		try {
			Login login = (Login) parameter;
					
			//new LoginChecker().check(login);
			
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoLogin= (DAOLogin) daofactory.getDao("DAOLogin");
			
			
			ent=daoLogin.autenticazione(login);
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return ent;
	}

	@Override
	public void Inserimento(Entity parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiorna(Entity parameter) {
		// TODO Auto-generated method stub
		
	}
}
