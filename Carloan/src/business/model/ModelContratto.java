package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.model.Exception.CommonException;
import integration.DAO.entity.DAOContratto;

public class ModelContratto implements Model{
	private DaoFactory daofactory;
	
	@Override
	public void Inserimento(Entity parameter) {
		try {
		   if(daofactory==null)
		 	daofactory= DaoFactory.getDaoFactory(1);
		  ((DAOContratto) daofactory.getDao("DAOContratto")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOContratto) daofactory.getDao("DAOContratto")).aggiornamento(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}	
	}

	public List<Contratto> getAll(){
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return ((DAOContratto) daofactory.getDao("DAOContratto")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
	}

	@Override
	public Entity lettura(int id) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return (Contratto) ((DAOContratto) daofactory.getDao("DAOContratto")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
