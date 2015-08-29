package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAmministratore;
import business.entity.Entity;
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;

public class ModelAmministratore implements Model{
	private DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOAmministratore) daofactory.getDao("DAOAmministratore")).creazione(parameter);
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
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOAmministratore) daofactory.getDao("DAOAmministratore")).aggiornamento(parameter);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
		
	}

	@Override
	public Entity lettura(int id) {
		try {
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
		return (Amministratore) ((DAOAmministratore) daofactory.getDao("DAOAmministratore")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public List<Amministratore> getAll(){
		try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
		return (List<Amministratore>) ((DAOAmministratore) daofactory.getDao("DAOAmministratore")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Entity leggiAmministratoreByCodiceFiscale(String c){
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return ((DAOAmministratore) daofactory.getDao("DAOAmministratore")).leggiAmministratoreByCodiceFiscale(c);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}
}
