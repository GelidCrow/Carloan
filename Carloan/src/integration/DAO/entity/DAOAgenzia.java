package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import business.entity.Entity;
import business.entity.Luoghi.Agenzia;


import static utility.QueryStringReplacer.queryReplaceFirst;

public class DAOAgenzia implements DAO{

	
	private  DaoFactory daofactory;
	
	
	DAOAgenzia(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) {

		String INSERT = "INSERT INTO Agenzia values('?','?','?','?');";
		String insertQuery = INSERT;
				
		Agenzia agenzia= (Agenzia)x;

        insertQuery = queryReplaceFirst(insertQuery, agenzia.getIDAgenzia());
        
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getNumTelefono());
        
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getNome());
        
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getIDDitta());
        
        Connection connection= Connection.getConnection(daofactory);
        ResultSet idList=null;
		try {
			 idList = connection.executeUpdate(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idList;
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
