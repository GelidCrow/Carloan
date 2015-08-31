package business.model;

import java.util.List;

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

	@Override
	public List<Entity> ricerca(Entity parameter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<SupervisoreAgenzia> getAll(){
		
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				return ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).getAll();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
	}
	public List<SupervisoreAgenzia> getAllbyAgenzia(int idAgenzia){
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				return ((DAOSupervisoreA)daofactory.getDao("DAOSupervisoreA")).getAllByAgenzia(idAgenzia);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
	}
}
