package business.model;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOOptional;

import java.util.List;

import business.entity.Entity;
import business.entity.Noleggio.Optional.Optional;
import business.model.Exception.CommonException;

public class ModelOptional implements Model{
	private DaoFactory daofactory;
	private DAOOptional daoOptional;
	
	@Override
	public void Inserimento(Entity parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
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
	
	public List<Optional> getAll(){
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoOptional= (DAOOptional) daofactory.getDao("DAOOptional");
			
			return daoOptional.getAll();
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;	
	}
	

}
