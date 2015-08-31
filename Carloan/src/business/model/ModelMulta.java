package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import integration.DAO.entity.DAOMulta;
import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.model.Exception.CommonException;

public class ModelMulta implements Model{
	private DaoFactory daofactory;


	



	@Override
	public void Inserimento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOMulta) daofactory.getDao("DAOMulta")).creazione(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOMulta) daofactory.getDao("DAOMulta")).aggiornamento(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    public List<Multa> getAllMulteByNoleggio(int id){
    	try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return ((DAOMulta) daofactory.getDao("DAOMulta")).getAllMulteByNoleggio(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;	
    }

	@Override
	public List<Entity> ricerca() {
		// TODO Auto-generated method stub
		return null;
	}

}
