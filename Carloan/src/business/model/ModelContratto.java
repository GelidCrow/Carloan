package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import integration.DAO.entity.DAOContratto;

public class ModelContratto implements Model{
	private DaoFactory daofactory;
	private DAOContratto daoContratto;
	
	@Override
	public void Inserimento(Entity parameter) {
		try {
			Contratto contratto = (Contratto) parameter;
			
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoContratto= (DAOContratto) daofactory.getDao("DAOContratto");
			
			daoContratto.creazione(contratto);
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void aggiornamento(Entity parameter) {
		try {
			Contratto contratto = (Contratto) parameter;
			
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoContratto= (DAOContratto) daofactory.getDao("DAOContratto");
			
			daoContratto.aggiornamento(contratto);
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}	
	}

	
	
	public List<Contratto> getAll(){
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoContratto= (DAOContratto) daofactory.getDao("DAOContratto");
			
			return daoContratto.getAll();
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	@Override
	public void lettura() {
		// TODO Auto-generated method stub	
	}
	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
	}
}
