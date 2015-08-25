package business.model;

import java.util.ArrayList;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOSede;
import business.entity.Entity;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;

public class ModelSede implements Model{

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
	public ArrayList<Sede> getAll(){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOSede)daofactory.getDao("DAOSede")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Sede> getAllSediByAgenzia(int id){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOSede)daofactory.getDao("DAOSede")).getAllSediByAgenzia(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
