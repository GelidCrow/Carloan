package business.model;

import business.entity.Entity;
import business.entity.Auto.Fascia.Fascia;
import business.model.Exception.CommonException;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAutoveicolo;
import integration.DAO.entity.DAOFascia;

import java.util.List;
public class ModelFascia implements Model{
	DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
	public List<Fascia> getAll(){
		try {
			daofactory = DaoFactory.getDaoFactory(1);
			return ((DAOFascia)daofactory.getDao("DAOFascia")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
