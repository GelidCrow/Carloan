package business.model;

import java.util.List;

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
	public void Inserimento(Entity parameter) throws CommonException {
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				 ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).creazione(parameter);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try{
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
				((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).aggiornamento(parameter);;
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		
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
public List<SupervisoreSede> getAll_bysede(int id){
	try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			return (List<SupervisoreSede>) ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).getAll_bySede(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;
}


public List<SupervisoreSede> getAll(){
	try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			return (List<SupervisoreSede>) ((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;
}


public SupervisoreSede leggiSupervisoreSedeByCodiceFiscale(String c){
	try{
		if(daofactory==null)
			daofactory= DaoFactory.getDaoFactory(1);
			return((DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS")).leggiSupervisoreSedeByCodiceFiscale(c);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;
}
}
