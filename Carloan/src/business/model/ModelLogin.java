package business.model;

import business.entity.Entity;
import business.entity.Login;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOLogin;

public class ModelLogin implements Model {
	private DaoFactory daofactory;


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
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);			
			return ((DAOLogin) daofactory.getDao("DAOLogin")).autenticazione(parameter);
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
	public void aggiornamento(Entity parameter) {
		// TODO Auto-generated method stub
		
	}
}
