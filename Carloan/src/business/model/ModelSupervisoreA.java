package business.model;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAmministratore;
import integration.DAO.entity.DAOSupervisoreA;
import business.entity.Entity;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;

public class ModelSupervisoreA implements Model{

	private DaoFactory daofactory;


	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Inserimento(Entity parameter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			return ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
