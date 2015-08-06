package integration.DAO;

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
	private MySqlDaoFactory() {
		
	}
	
	
	/**
	 * 
	 * @param DaoName Il nome del dao da instanziare
	 * @return Il riferimento alla classe che modella il dao richiesto
	 */
	public static Class<?>  getDao(String DaoName) {
		try {
			return  (Class<?>) Class.forName(DaoName).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find dao: "+DaoName);
		}
		return null;
	}

}
