package integration.DAO.connection;

import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import integration.DAO.DaoFactory;
import integration.DAO.DatabaseConnectionException;
import config.ConfiguratorDBReader;
import config.ConfiguratorReader;

public class Connection {
	private final static String PATH = "src/config/mySql.cfg";
	private  String host;
	private  String db;
	private String port;
	private  String username;
	private  String password;
	private  String connessione;
	private  String vendor;
	private DaoFactory daofactory;
	private   Connection con;
	private  java.sql.Connection connessione_remota;
	private ConfiguratorReader reader;	
	private Connection(DaoFactory a){
		reader=new ConfiguratorDBReader(PATH);
		this.daofactory=a;
		host=reader.getField(ConfiguratorDBReader.HOST);
		db=reader.getField(ConfiguratorDBReader.DATABASE);
		port=reader.getField(ConfiguratorDBReader.PORT);
		username=reader.getField(ConfiguratorDBReader.USERNAME);
		password=reader.getField(ConfiguratorDBReader.PASSWORD);
		this.connessione="jdbc:";
		switch(this.daofactory.getClass().getSimpleName()){
		case "MySqlDaoFactory":
			vendor="mysql";
		 default:
			vendor="mysql";
		}
		this.connessione+=vendor+"://"+host+":"+port+"/"+db;
		
	}
	public Connection getConnection(DaoFactory a){
		if(this.con==null)
			this.con=new Connection(a);
		return this.con;
	}
	
	/**
	 * Permette di aprire una connessione al database.
	 * 
	 * @return la connessione creata.
	 * @throws SQLException
	 */
	public  java.sql.Connection connetti() throws SQLException {
		try {
			switch(vendor){
			case "mysql":
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						break;
			default:
					throw new DatabaseConnectionException("Could not find vendor");
			}
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
		catch(InstantiationException | IllegalAccessException| ClassNotFoundException e){
			e.printStackTrace();
		}
		connessione_remota = DriverManager.getConnection(connessione, username, password);
		return connessione_remota;
	}
	
	/**
	 * Chiude la connessione al database.
	 * @throws SQLException 
	 * 
	 */
	public void chiudiConnessione() throws SQLException {
		if (connessione_remota != null && !connessione_remota.isClosed())
			connessione_remota.close();
	}
	
	
	@SuppressWarnings("finally")
	public ResultSet execute(String query){
		ResultSet result=null;
		if(query!=null && !query.isEmpty()){
			PreparedStatement st;
			
			try {
				st=connessione_remota.prepareStatement("use "+db+";");
				st.execute();
				st=connessione_remota.prepareStatement(query);
				result=st.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return result;
			}
		}
	return result;
	}
	
	@SuppressWarnings("finally")
	public ResultSet execute(String query,FileInputStream f){
		ResultSet result=null;
		if(query!=null && !query.isEmpty()){
			PreparedStatement st;
			try {
				st=connessione_remota.prepareStatement("use "+db+";");
				st.execute();
				st.setBinaryStream(1,f,f.available());
				st=connessione_remota.prepareStatement(query);
				result=st.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				return result;
			}
		}
	return result;
	}
}
