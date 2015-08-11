package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;

import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Crittografia;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;
import business.entity.Entity;
import business.entity.Login;
import business.entity.Luoghi.Agenzia;


public class DAOLogin implements DAO{
	

	private  DaoFactory daofactory;
	
	
	DAOLogin(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	
	@Override
	public void creazione(Entity x){
		String INSERT = "INSERT INTO Credenziali values('?','?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Login login= (Login)x;

        insertQuery = queryReplaceFirst(insertQuery, login.getUsername());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getPassword());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getSupA());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getSupS());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getAmministratore());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getOperatore());
 
        
        Connection connection= Connection.getConnection(daofactory);
        try {
			connection.connetti();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		ResultSet idList = connection.execute(insertQuery);
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean  autenticazione(Entity x){
		Login login= (Login)x;
		String Autenticazione = "select Username,Password from Credenziali";
		
		Connection connection= Connection.getConnection(daofactory);
        try {
			connection.connetti();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		ResultSet idList = connection.execute(Autenticazione);
		
		try {
			while(idList.next()){
				try {
					if(login.getUsername().equals(idList.getString(1))  &&  login.getPassword().equals(idList.getString(2))){
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Entity lettura() {
		// TODO Auto-generated method stub
		return null;
	}

}
