package business.model;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import integration.DAO.entity.DAOLogin;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Login;

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
	public void aggiorna(Entity parameter) {
		// TODO Auto-generated method stub
		
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
