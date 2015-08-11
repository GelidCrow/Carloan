package integration.DAO;

import integration.DAO.entity.DAO;

import java.lang.reflect.InvocationTargetException;

public class MySqlDaoFactory extends DaoFactory{
	/**
	 * Costruttore privato 
	 * Oggetto Singletone.
	 */
	private static MySqlDaoFactory dao;
	/**
	 * Fornisce l'unica istanza di MySqlDaoFactory.
	 * @return l'istanza di MySqlDaoFactory.
	 */
	public static MySqlDaoFactory getInstance() {
		if (dao == null) {
			dao = new MySqlDaoFactory();
		}
		return dao;
	}	
	/**
	 * Costruttore privato per la realizzazione del Singletone.
	 */
	MySqlDaoFactory() {
		
	}
	/**
	 * 
	 * @param DaoName Il nome del dao da instanziare
	 * @return Il riferimento alla classe che modella il dao richiesto
	 */
	public DAO getDao(String DaoName) {
		try {
			return  (DAO) Class.forName(DaoName).getDeclaredConstructor(DaoName.getClass()).newInstance(dao);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find dao: "+DaoName);
		}
		return null;
	}
}
