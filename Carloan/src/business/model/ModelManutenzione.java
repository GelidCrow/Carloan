package business.model;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOManutenzione;

import business.entity.Entity;
import business.entity.Auto.manutenzione.Manutenzione;
import business.model.Exception.CommonException;

public class ModelManutenzione implements Model{
	private DaoFactory daofactory;
	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
	public void chiusuraManutenzione(Manutenzione manutenzione){
		
	}

	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOManutenzione)daofactory.getDao("DAOManutenzione")).creazione(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		//Non implementata
	}
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
