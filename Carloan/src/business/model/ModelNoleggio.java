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
	private DAONoleggio daoNoleggio;
	
	@Override
	public void Inserimento(Entity parameter) {
		try {
			Noleggio noleggio = (Noleggio) parameter;
			
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoNoleggio= (DAONoleggio) daofactory.getDao("DAONoleggio");
			
			daoNoleggio.creazione(noleggio);
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
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
	
	
	public List<Noleggio> getAll(){
		try {
		daofactory= DaoFactory.getDaoFactory(1);
		
		daoNoleggio= (DAONoleggio) daofactory.getDao("DAONoleggio");
		
		return daoNoleggio.getAll();
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;	
	}


	
		
}
