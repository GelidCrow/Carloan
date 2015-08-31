package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import integration.DAO.entity.DAONoleggio;
import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.model.Exception.CommonException;

public class ModelNoleggio implements Model{
	private DaoFactory daofactory;
	
	@Override
	public void Inserimento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);	
			((DAONoleggio) daofactory.getDao("DAONoleggio")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Entity> ricerca(Entity entity) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return 	((DAONoleggio) daofactory.getDao("DAONoleggio")).ricerca(entity);

			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	public List<Noleggio> getAll(){
		try {
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
		return 	((DAONoleggio) daofactory.getDao("DAONoleggio")).getAll();

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;	
	}
	
	public List<Noleggio> getNoleggiAperti(int id){
		try {
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
		return 	((DAONoleggio) daofactory.getDao("DAONoleggio")).getNoleggiAperti(id);

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
	public int contaNoleggi(){
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);	
			return ((DAONoleggio) daofactory.getDao("DAONoleggio")).conta();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
		
}
