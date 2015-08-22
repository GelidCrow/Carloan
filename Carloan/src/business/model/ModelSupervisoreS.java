package business.model;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOSupervisoreS;
import business.entity.Entity;
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;

public class ModelSupervisoreS implements Model{

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
				return (SupervisoreSede) ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).lettura(id);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}

}
