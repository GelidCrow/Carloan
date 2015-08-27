package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAgenzia;
import business.entity.Entity;
import business.entity.Luoghi.Agenzia;
import business.model.Exception.CommonException;

public class ModelAgenzia implements Model{
	private DaoFactory daofactory;
	

	
	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOAgenzia)daofactory.getDao("DAOAgenzia")).creazione(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}




	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Agenzia> getAll(){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAgenzia)daofactory.getDao("DAOAgenzia")).getAll();
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
