package integration.DAO;


public abstract class DaoFactory {
	/**
	 * Data Access Object per DBMS MySql.
	 */
	public static final int MYSQL = 1;
	/**
	 * 
	 * @param i Identifica il factory da istanziare
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static DaoFactory getDaoFactory(int i) throws InstantiationException, IllegalAccessException {
		DaoFactory dao = null;
		switch (i) {
		case MYSQL:
			dao = MySqlDaoFactory.class.newInstance();
			break;
		default:
			dao = null;
			break;
		}
		return dao;
	}
}
