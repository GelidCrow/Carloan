package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOOperatore;
import integration.DAO.entity.DAOSupervisoreS;
import business.entity.Entity;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreSede;
import business.model.Exception.CommonException;

public class ModelOperatore implements Model{

	private DaoFactory daofactory;



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
		
			if(daofactory==null)
				try {
					daofactory= DaoFactory.getDaoFactory(1);

					return (Operatore) ((DAOOperatore) daofactory.getDao("DAOOperatore")).lettura(id);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return null;
	}

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Operatore> getAll(){
		if(daofactory==null)
			try {
				daofactory= DaoFactory.getDaoFactory(1);

				return  ((DAOOperatore) daofactory.getDao("DAOOperatore")).getAll();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return null;
	}
	public List<Operatore> getAllOperatoriBySede(int idsede){
		if(daofactory==null)
			try {
				daofactory= DaoFactory.getDaoFactory(1);

				return  ((DAOOperatore) daofactory.getDao("DAOOperatore")).getAllOperatoriBySede(idsede);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return null;
	}
}