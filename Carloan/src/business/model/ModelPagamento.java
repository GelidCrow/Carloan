package business.model;


import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOPagamento;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ModelPagamento implements Model{
	private DaoFactory daofactory;
	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub

		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOPagamento)daofactory.getDao("DAOPagamento")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
	


}
