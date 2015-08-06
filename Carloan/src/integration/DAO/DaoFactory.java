package integration.DAO;


public abstract class DaoFactory {
	/**
	 * Data Access Object per DBMS MySql.
	 */
	public static final int MYSQL = 1;
	
	public static DaoFactory getDaoFactory(int i) {
		DaoFactory dao = null;
		switch (i) {
		case MYSQL:
			dao = MySqlDaoFactory.getIstance();
			break;
		default:
			dao = null;
			break;
		}
		return dao;
	}

	/**
	 * Crea istanze di specifici DAO.
	 * @param c la classe dell'oggetto DAO desiderata.
	 * @return uno specifico DAO.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	protected static Object createDao(Class c) throws InstantiationException,
			IllegalAccessException {
		return c.newInstance();
	}

}
