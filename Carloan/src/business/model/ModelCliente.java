package business.model;



import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import business.entity.Cliente;
import business.entity.Entity;

public class ModelCliente implements Model{
	private DaoFactory daofactory;
	private DAOCliente daoCliente;

	@Override
	public void Inserimento(Entity parameter) {
	//	Entity ent=null;
		try {
			Cliente cliente = (Cliente) parameter;
					
			//new LoginChecker().check(login);
			
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoCliente= (DAOCliente) daofactory.getDao("DAOCliente");
			
			daoCliente.creazione(cliente);
		//	ent=daoCliente.autenticazione(login);
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void aggiornamento(Entity parameter) {
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoCliente= (DAOCliente) daofactory.getDao("DAOCliente");
			
			return daoCliente.aggiornamento(parameter);
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lettura() {
		
	}
	
	
	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Cliente> getAll(){
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoCliente= (DAOCliente) daofactory.getDao("DAOCliente");
			
			return daoCliente.getAll();
				
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;	
	}
}
