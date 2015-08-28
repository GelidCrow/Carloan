package business.model;

import integration.DAO.DaoFactory;
import business.entity.Entity;
import business.model.Exception.CommonException;

public class ModelGuidatore implements Model{
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
}
