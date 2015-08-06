package integration.DAO;

import java.sql.Connection;

import config.ConfiguratorDBReader;
import config.ConfiguratorReader;

public class MySqlDaoFactory extends DaoFactory{
	
	/**
	 * Oggetto Singletone.
	 */
	private static MySqlDaoFactory dao;
	
	
	private final static String PATH = "src/config/mySql.cfg";
	private static String host;
	private static String db;
	private static String username;
	private static String password;
	private static String connessione;

	private static Connection con;

	private ConfiguratorReader reader;
	
	/**
	 * Costruttore privato per la realizzazione del Singletone.
	 */
	private MySqlDaoFactory() {
		this.reader = new ConfiguratorDBReader(PATH);
		host = reader.getField(ConfiguratorDBReader.HOST);
		db = reader.getField(ConfiguratorDBReader.DATABASE);
		username = reader.getField(ConfiguratorDBReader.USERNAME);
		password = reader.getField(ConfiguratorDBReader.PASSWORD);
		connessione = "jdbc:mysql://" + host + "/" + db;
	}

	
	/**
	 * Fornisce l'unica istanza di MySqlDaoFactory.
	 * 
	 * @return l'istanza di MySqlDaoFactory.
	 */
	public static MySqlDaoFactory getIstance() {
		if (dao == null) {
			dao = new MySqlDaoFactory();
		}
		return dao;
	}	
}
