package business.model;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAmministratore;
import integration.DAO.entity.DAOCliente;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;

public class ModelAmministratore implements Model{
	private DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) {
		// TODO Auto-generated method stub
		
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
	public Entity lettura(int id) {try {
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
		return (Amministratore) ((DAOAmministratore) daofactory.getDao("DAOAmministratore")).lettura(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
