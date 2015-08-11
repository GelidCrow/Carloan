package business.model;

import integration.DAO.DaoFactory;

public class ModelLogin implements Model {
	DaoFactory daofactory;
	@Override
	public void Inserimento(){
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daofactory.getDao("DAOLogin");
			
			
			
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiorna() {
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
